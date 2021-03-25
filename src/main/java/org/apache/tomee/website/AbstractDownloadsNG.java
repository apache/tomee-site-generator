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

// regenerate active download page using mirroring system
@RequiredArgsConstructor(access = PROTECTED)
public class AbstractDownloadsNG {

    private static final long MEGA_RATIO = 1024 * 1024;
    protected static final String BASE_URL = "https://downloads.apache.org/tomee/";
    protected static final String ARCHIVE_BASE_URL = "https://archive.apache.org/dist/tomee/";
    private static final String BASE_MIRROR_URL = "https://www.apache.org/dyn/closer.cgi/tomee/";

    public static void generate(String baseUrl) throws MalformedURLException {
        List<AbstractDownloadsNG.Download> downloads = getDownloadables(baseUrl);

        AtomicReference<String> version1 = new AtomicReference<>();
        AtomicReference<String> version7 = new AtomicReference<>();
        AtomicReference<String> version8 = new AtomicReference<>();
        AtomicReference<String> version9 = new AtomicReference<>();

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

    private static void printRow(AbstractDownloadsNG.Download d) {

        String hash = d.sha512.isEmpty() ? (d.sha256.isEmpty() ? (d.sha1.isEmpty() ? "" : d.sha1 + "[icon:download[] SHA1] ") : d.sha256 + "[icon:download[] SHA256] ") : d.sha512 + "[icon:download[] SHA512] ";

        System.out.println("" +
                "|" + d.mirrorUrl + "[icon:download[] " + d.name.replace("Apache ", "") + " " + d.format.toUpperCase() + "] " +
                "|" + d.version +
                "|" + new SimpleDateFormat("d MMM yyyy").format(Date.from(LocalDateTime.parse(d.date, RFC_1123_DATE_TIME).toInstant(ZoneOffset.UTC))) +
                "|" + d.size + " MB " +
                "|" + d.asc + "[icon:download[] PGP] " + hash);
    }

    private static List<AbstractDownloadsNG.Download> getDownloadables(String base) throws MalformedURLException {
        QuickDistLinkParser quickDistLinkParser = new QuickDistLinkParser();

        Results results = quickDistLinkParser.fetch(new URL(base));

        List<AbstractDownloadsNG.Download> downloads = new ArrayList<>();

        //parse the links, generate download set
        for (Link distLink : results.links) {

            String version = distLink.getUri().replaceAll("tomee-", "").replaceAll("/", "");

            Results distResults = quickDistLinkParser.fetch(new URL(base + distLink.getUri()));

            List<Link> links = distResults.links
                    .stream()
                    .filter(l -> l.getUri().endsWith(".tar.gz")
                            || l.getUri().endsWith(".zip")
                            || l.getUri().endsWith(".war"))
                    .collect(Collectors.toList());

            Set<String> available = distResults.links
                    .stream()
                    .map(l -> distResults.baseUrl + l.getUri())
                    .collect(Collectors.toSet());

            for (Link link : links) {

                String uri = link.getUri();
                String baseName = "";
                String extension = "";

                if (uri.contains(".tar.gz")) {
                    baseName = uri.substring(0, uri.lastIndexOf(".tar.gz"));
                    extension = uri.substring(uri.lastIndexOf("tar.gz"));
                } else if (uri.contains(".zip")) {
                    baseName = uri.substring(0, uri.lastIndexOf(".zip"));
                    extension = uri.substring(uri.lastIndexOf("zip"));
                } else if (uri.contains(".war")) {
                    baseName = uri.substring(0, uri.lastIndexOf(".war"));
                    extension = uri.substring(uri.lastIndexOf("war"));
                } else {
                    System.err.println("This should not happen. New extensions?");
                }

                String url = distResults.baseUrl + uri;
                String ascURL = url + ".asc";
                String sha1URL = url + ".sha1";
                String sha256URL = url + ".sha256";
                String sha512URL = url + ".sha512";

                if (!available.contains(ascURL)) {
                    System.err.println("Release " + ascURL + " does not have a ASC signature. This shouldn't happen!");
                    throw new RuntimeException("Release does not have a ASC signature. This shouldn't happen!");
                }

                if (!available.contains(sha1URL)) {
                    sha1URL = "";
                }

                if (!available.contains(sha256URL)) {
                    sha256URL = "";
                }

                if (!available.contains(sha512URL)) {
                    sha512URL = "";
                }

                if (sha1URL.isEmpty() && sha256URL.isEmpty() && sha512URL.isEmpty()) {
                    System.err.println("Release " + baseName + " does not have a valid hash. This shouldn't happen!");
                    throw new RuntimeException("Release does not have a valid hash. This shouldn't happen!");
                }

                AbstractDownloadsNG.Download dl = toDownload(baseName, version, extension, url, sha1URL, sha256URL, sha512URL, ascURL);

                downloads.add(dl);
            }
        }
        return downloads;
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

    private static void checkMaxVersion(AtomicReference<String> version1, AtomicReference<String> version7, AtomicReference<String> version8, AtomicReference<String> version9, AbstractDownloadsNG.Download o1, int versionComp) {
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
    private static <T> Predicate<T> distinctBy(Function<? super T, ?> keyExtractor) {
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
        private String date; /* "Wed, 05 Aug 2020 16:26:51 GMT" */
        private long size;
    }

    private static class QuickDistLinkParser {

        public Results fetch(URL url) {
            Results results = new Results();

            try (InputStream input = url.openStream()) {

                LinkContentHandler linkHandler = new LinkContentHandler();
                Metadata metadata = new Metadata();
                HtmlParser parser = new HtmlParser();

                ContentHandler textHandler = new BodyContentHandler(-1);

                parser.parse(input,
                        new TeeContentHandler(linkHandler, textHandler),
                        metadata,
                        new ParseContext());


                results.baseUrl = url.toString();
                // keep only <a> tags with non-empty href + avoid duplicates + only links containing "tomee" or "openejb", filtering some other artifacts on the dist server
                results.links = linkHandler.getLinks().stream()
                        .filter(l -> l.isAnchor() &&
                                (l.getUri().contains("tomee-") || l.getUri().contains("openejb-standalone")) &&
                                !l.getUri().isEmpty() &&
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
                                // no need to have hashes of sigs
                                !l.getUri().endsWith("asc.sha1") &&
                                !l.getUri().endsWith("asc.sha256") &&
                                !l.getUri().endsWith("asc.sha512") &&
                                !l.getUri().startsWith("#"))
                        .filter(distinctBy(Link::getUri))
                        .collect(Collectors.toList());

                return results;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    public static class Results {
        public String baseUrl;
        public List<Link> links;

        Results() {
        }

        @Override
        public String toString() {
            return String.format("Results{ %s: %d links }",
                    baseUrl, links.size());
        }
    }


}
