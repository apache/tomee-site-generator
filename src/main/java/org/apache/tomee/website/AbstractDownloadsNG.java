package org.apache.tomee.website;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.Link;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.xml.sax.ContentHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PROTECTED;

@RequiredArgsConstructor(access = PROTECTED)
public class AbstractDownloadsNG {

    private static final long MEGA_RATIO = 1024 * 1024;
    protected static final String BASE_URL = "https://downloads.apache.org/tomee/";
    protected static final String ARCHIVE_BASE_URL = "https://archive.apache.org/dist/tomee/";
    private static final String BASE_MIRROR_URL = "https://www.apache.org/dyn/closer.cgi/tomee/";

    public static void generate(String baseUrl) throws MalformedURLException {
        final List<AbstractDownloadsNG.Download> downloads = getDownloadables(baseUrl);

        final AtomicReference<String> version1 = new AtomicReference<>();
        final AtomicReference<String> version7 = new AtomicReference<>();
        final AtomicReference<String> version8 = new AtomicReference<>();
        final AtomicReference<String> version9 = new AtomicReference<>();

        List<AbstractDownloadsNG.Download> filled = downloads.stream().parallel()
                .map(AbstractDownloadsNG::fillDownloadable)
                .filter(Objects::nonNull /* skipped */)
                .sorted((o1, o2) -> {

                    int versionComp = o2.version.compareTo(o1.version);

                    if (versionComp != 0) {
                        if (o2.version.startsWith(o1.version) && o2.version.contains("-M")) { // milestone
                            versionComp = -1;
                        } else if (o1.version.startsWith(o2.version) && o1.version.contains("-M")) { // milestone
                            versionComp = 1;
                        }

                        checkMaxVersion(version1, version7, version8, version9, o1, versionComp);

                        return versionComp;
                    }

                    final int nameComp = o1.name.compareTo(o2.name);

                    if (nameComp != 0) {

                        checkMaxVersion(version1, version7, version8, version9, o1, nameComp);

                        return nameComp;
                    }

                    final long dateComp = LocalDateTime.parse(o2.date, RFC_1123_DATE_TIME).toInstant(ZoneOffset.UTC).toEpochMilli()
                            - LocalDateTime.parse(o1.date, RFC_1123_DATE_TIME).toInstant(ZoneOffset.UTC).toEpochMilli();
                    if (dateComp != 0) {
                        return (int) dateComp;
                    }

                    return o1.url.compareTo(o2.url);
                })
                .collect(toList());


        String versionCurrent = version9.get();

        System.out.println("|Name|Version|Date|Size|Signatures & Hashes");
        for (AbstractDownloadsNG.Download d : filled) {

            if (!versionCurrent.equals(d.version)) {
                versionCurrent = d.version;
                System.out.println("|||||");
            }
            printRow(d);
        }
    }

    private static void printRow(final AbstractDownloadsNG.Download d) {

        final String hash = d.sha512.isEmpty() ? (d.sha256.isEmpty() ? (d.sha1.isEmpty() ? "" : d.sha1 + "[icon:download[] SHA1] ") : d.sha256 + "[icon:download[] SHA256] ") : d.sha512 + "[icon:download[] SHA512] ";

        System.out.println("" +
                "|" + d.mirrorUrl + "[icon:download[] " + d.name.replace("Apache ", "") + " " + d.format.toUpperCase() + "] " +
                "|" + d.version +
                "|" + new SimpleDateFormat("d MMM yyyy").format(Date.from(LocalDateTime.parse(d.date, RFC_1123_DATE_TIME).toInstant(ZoneOffset.UTC))) +
                "|" + d.size + " MB " +
                "|" + d.asc + "[icon:download[] PGP] " + hash);
    }

    private static List<AbstractDownloadsNG.Download> getDownloadables(final String base) throws MalformedURLException {
        final QuickDistLinkParser quickDistLinkParser = new QuickDistLinkParser();

        final Results results = quickDistLinkParser.fetch(new URL(base));

        final List<AbstractDownloadsNG.Download> downloads = new ArrayList<>();

        //parse the links, generate download set
        for (Link distLink : results.links) {

            final String version = getVersionFromURI(distLink.getUri());

            final Results distResults = quickDistLinkParser.fetch(new URL(base + distLink.getUri()));

            final List<Link> links = distResults.links
                    .stream()
                    .filter(l -> l.getUri().endsWith("tar.gz")
                            || l.getUri().endsWith("zip")
                            || l.getUri().endsWith("war"))
                    .collect(Collectors.toList());

            final Set<String> available = distResults.links
                    .stream()
                    .map(l -> distResults.baseUrl + l.getUri())
                    .collect(Collectors.toSet());

            for (Link link : links) {

                final String url = distResults.baseUrl + link.getUri();
                final String baseName = getBaseNameFromURI(link.getUri());
                final String extension = getExtensionFromURI(link.getUri());
                final String ascURL = checkURLAvailable(url + ".asc", available, true);
                final String sha1URL = checkURLAvailable(url + ".sha1", available, false);
                final String sha256URL = checkURLAvailable(url + ".sha256", available, false);
                final String sha512URL = checkURLAvailable(url + ".sha512", available, false);

                if (sha1URL.isEmpty() && sha256URL.isEmpty() && sha512URL.isEmpty()) {
                    throw new RuntimeException("Release " + baseName + " does not have a valid hash. This shouldn't happen!");
                }

                downloads.add(toDownload(baseName, version, extension, url, sha1URL, sha256URL, sha512URL, ascURL));
            }
        }
        return downloads;
    }

    /**
     * Checks, if we encountered the given url in the parsing results of the web directory content.
     *
     * @param possibleURL   the url to check
     * @param availableURLs a {@link Set} containing the identified links of the web directory content.
     * @param mandatory     if {@code true}, an exception is thrown if the given URL is not contained in the given set.
     * @return {@code ""}, if the given URL is not contained and would be a dead link.
     */
    private static String checkURLAvailable(final String possibleURL, final Set<String> availableURLs, final boolean mandatory) {
        if (!availableURLs.contains(possibleURL)) {
            if (mandatory) {
                throw new RuntimeException("Mandatory file " + possibleURL + " is not available. This shouldn't happen!");
            }
            return "";
        }
        return possibleURL;
    }

    private static String getVersionFromURI(final String uri) {
        return uri.replaceAll("tomee-", "").replaceAll("/", "");
    }

    private static String getExtensionFromURI(final String uri) {
        if (uri.endsWith("tar.gz")) {
            return uri.substring(uri.lastIndexOf("tar.gz"));
        } else if (uri.endsWith("zip")) {
            return uri.substring(uri.lastIndexOf("zip"));
        } else if (uri.endsWith("war")) {
            return uri.substring(uri.lastIndexOf("war"));
        } else {
            throw new RuntimeException("Release links have an unknown extension type. New release extensions???");
        }
    }

    private static String getBaseNameFromURI(final String uri) {
        if (uri.endsWith("tar.gz")) {
            return uri.substring(0, uri.lastIndexOf("tar.gz") - 1);
        } else if (uri.endsWith("zip")) {
            return uri.substring(0, uri.lastIndexOf("zip") - 1);
        } else if (uri.endsWith("war")) {
            return uri.substring(0, uri.lastIndexOf("war") - 1);
        } else {
            throw new RuntimeException("Release links have an unknown extension type. New release extensions???");
        }
    }

    private static AbstractDownloadsNG.Download fillDownloadable(final AbstractDownloadsNG.Download download) {
        try {
            final URL url = new URL(download.url);
            final HttpURLConnection connection = HttpURLConnection.class.cast(url.openConnection());
            connection.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(30));
            final int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                if (HttpURLConnection.HTTP_NOT_FOUND != responseCode) {
                    System.err.println("Got " + responseCode + " for " + download.url);
                }
                return null;
            }

            download.setDate(connection.getHeaderField("Last-Modified").replaceAll(" +", " "));
            download.setSize(toMega(ofNullable(connection.getHeaderField("Content-Length")).map(Long::parseLong).orElse(0L), ofNullable(connection.getHeaderField("Accept-Ranges")).orElse
                    ("bytes")));
            download.setMirrorUrl(download.url.replace(BASE_URL, BASE_MIRROR_URL));
            connection.getInputStream().close();
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
        return download;
    }

    private static long toMega(final long length, final String bytes) {
        if (!"bytes".equalsIgnoreCase(bytes)) {
            throw new IllegalArgumentException("Not handled unit: " + bytes);
        }
        return length / MEGA_RATIO;
    }

    private static AbstractDownloadsNG.Download toDownload(final String name, final String version,
                                                           final String format, final String url, final String sha1, final String sha256, final String sha512, final String asc) {
        return new AbstractDownloadsNG.Download(
                WordUtils.capitalize(name.replace(version, "").replace("--", " ").replace('-', ' ').trim()).replace("Openejb", "OpenEJB").replace("Tomee", "TomEE"),
                version,
                format,
                url,
                sha1,
                sha256,
                sha512,
                asc);
    }

    private static void checkMaxVersion(final AtomicReference<String> version1,
                                        final AtomicReference<String> version7,
                                        final AtomicReference<String> version8,
                                        final AtomicReference<String> version9, final AbstractDownloadsNG.Download o1, final int versionComp) {
        if (versionComp > 0) {
            if (o1.version.startsWith("1.")) {
                version1.set(o1.version);
            } else if (o1.version.startsWith("7.")) {
                version7.set(o1.version);
            } else if (o1.version.startsWith("8.")) {
                version8.set(o1.version);
            } else if (o1.version.startsWith("9.")) {
                version9.set(o1.version);
            }
        }
    }

    // distinct by property in a Java stream
    private static <T> Predicate<T> distinctBy(final Function<? super T, ?> keyExtractor) {
        final Set<Object> seen = new HashSet<>();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @Data
    public static class Download {
        private final String name;
        private final String version;
        private final String format;
        private final String url;
        private final String sha1;
        private final String sha256;
        private final String sha512;
        private final String asc;
        private String mirrorUrl;
        private String date = "Wed, 05 Aug 2020 16:26:51 GMT";
        private long size = 10;
    }

    private static class QuickDistLinkParser {

        public Results fetch(final URL url) {

            try (final InputStream input = url.openStream()) {

                final LinkContentHandler linkHandler = new LinkContentHandler();
                final Metadata metadata = new Metadata();
                final HtmlParser parser = new HtmlParser();

                final ContentHandler textHandler = new BodyContentHandler(-1);

                parser.parse(input,
                        new TeeContentHandler(linkHandler, textHandler),
                        metadata,
                        new ParseContext());

                //TODO Possible Improvement: Filters (see below) as CLI parameters

                // keep only <a> tags with non-empty href + avoid duplicates + only links containing "tomee" or "openejb", filtering some other artifacts on the dist server
                final List<Link> downloadLinks = linkHandler.getLinks().stream()
                        .filter(l -> l.isAnchor() &&
                                !l.getUri().isEmpty() &&
                                !l.getUri().startsWith("#") &&
                                (l.getUri().contains("tomee-") || l.getUri().contains("openejb-standalone")) &&
                                // release >= 1.7.x contains this -> is not linked on website -> filter it
                                !l.getUri().contains("arquillian") &&
                                // release >= 4.7.x contains this -> is not linked on website -> filter it
                                !l.getUri().contains("openejb-standalone-4.7.5") &&
                                // release >= 1.7.x contains this -> is not linked on website -> filter it
                                !l.getUri().contains("openejb-provisionning-4.7.5") &&
                                // release >= 1.7.x contains this -> is not linked on website -> filter it
                                !l.getUri().contains("openejb-ssh-4.7.5") &&
                                // release >= 1.7.x contains this -> is not linked on website -> filter it
                                !l.getUri().contains("tomee-webaccess") &&
                                // release 9.0.0-M3 has no (real) source release -> filter it
                                !l.getUri().contains("apache-tomee-9.0.0-M3-source-release") &&
                                // release 9.0.0-M2 has no (real) source release -> filter it
                                !l.getUri().contains("apache-tomee-9.0.0-M2-source-release") &&
                                // release 9.0.0-M1 has no (real) source release -> filter it
                                !l.getUri().contains("apache-tomee-9.0.0-M1-source-release") &&
                                // no need to have signature hashes
                                !l.getUri().endsWith("asc.sha1") &&
                                !l.getUri().endsWith("asc.sha256") &&
                                !l.getUri().endsWith("asc.sha512"))
                        .filter(distinctBy(Link::getUri))
                        .collect(Collectors.toList());

                return new Results(url.toString(), Collections.unmodifiableList(downloadLinks));
            } catch (Exception e) {
                throw new RuntimeException("Could not obtain download link. See stacktrace for further information.", e);
            }
        }
    }

    @Data
    public static class Results {
        private final String baseUrl;
        private final List<Link> links;

        @Override
        public String toString() {
            return String.format("Results{ %s: %d links }",
                    baseUrl, links.size());
        }
    }


}
