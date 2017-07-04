package org.apache.tomee.website;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.text.WordUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;
import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

// regenerate when needed only, useless to do it for any site update
@RequiredArgsConstructor(access = PRIVATE)
public class Downloads {
    private static final SAXParserFactory FACTORY = SAXParserFactory.newInstance();
    private static final String MVN_BASE = "http://repo.maven.apache.org/maven2/";
    private static final long MEGA_RATIO = 1024 * 1024;

    static {
        FACTORY.setNamespaceAware(false);
        FACTORY.setValidating(false);
    }

    public static void main(final String[] args) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "512");
        Stream.of(
                Stream.of("org/apache/openejb/openejb", "org/apache/tomee/tomee-project")
                        .flatMap(Downloads::toVersions)
                        .map(v -> v.extensions("zip"))
                        .map(v -> v.classifiers("source-release")),
                versionStream("apache-tomee")
                        .map(v -> v.version.startsWith("1.") ?
                                v.classifiers("plus", "plume", "webprofile", "jaxrs") : v.classifiers("plus", "plume", "webprofile"))
                        .map(v -> v.extensions("tar.gz", "zip")),
                versionStream("openejb-standalone")
                        .map(v -> v.extensions("tar.gz", "zip")),
                versionStream("tomee-webapp")
                        .map(v -> v.extensions("war")),
                versionStream("tomee-plus-webapp")
                        .map(v -> v.extensions("war")),
                versionStream("tomee-plume-webapp")
                        .map(v -> v.extensions("war")))
                .flatMap(s -> s)
                .flatMap(Downloads::toDownloadable)
                .parallel()
                .map(Downloads::fillDownloadable)
                .filter(Objects::nonNull /* skipped */)
                .sorted((o1, o2) -> {
                    final int nameComp = o1.name.compareTo(o2.name);
                    if (nameComp != 0) {
                        return nameComp;
                    }

                    final int versionComp = o2.version.compareTo(o1.version);
                    if (versionComp != 0) {
                        if (o2.version.startsWith(o1.version) && o2.version.contains("-M")) { // milestone
                            return -1;
                        }
                        if (o1.version.startsWith(o2.version) && o1.version.contains("-M")) { // milestone
                            return 1;
                        }
                        return versionComp;
                    }

                    final long dateComp = LocalDateTime.parse(o2.date, RFC_1123_DATE_TIME).toInstant(ZoneOffset.UTC).toEpochMilli()
                            - LocalDateTime.parse(o1.date, RFC_1123_DATE_TIME).toInstant(ZoneOffset.UTC).toEpochMilli();
                    if (dateComp != 0) {
                        return (int) dateComp;
                    }

                    return o1.url.compareTo(o2.url);
                })
                .collect(toList())
                .forEach(d ->
                        System.out.println("" +
                                "|" + d.name + (d.classifier.isEmpty() ? "" : (" " + d.classifier)) +
                                "|" + d.version +
                                "|" + d.date +
                                "|" + d.size + " MB " +
                                "|" + d.format +
                                "| " + d.url + "[icon:download[] " + d.format + "] " + d.sha1 + "[icon:download[] sha1] " + d.md5 + "[icon:download[] md5] " + d.asc + "[icon:download[] asc]"));
    }

    private static Download fillDownloadable(final Download download) {
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
            download.setSize(toMega(ofNullable(connection.getHeaderField("Content-Length")).map(Long::parseLong).orElse(0L), ofNullable(connection.getHeaderField("Accept-Ranges")).orElse("bytes")));

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

    private static Stream<Version> versionStream(final String artifactId) {
        return Stream.of(artifactId)
                .flatMap(s -> Stream.of("org/apache/tomee/" + s, "org/apache/openejb/" + s))
                .flatMap(Downloads::toVersions);
    }

    private static Stream<Download> toDownloadable(final Version version) {
        final String base = version.base;
        final String artifactId = base.substring(base.lastIndexOf('/') + 1, base.length());
        final String artifactBase = version.base + "/" + version.version + "/" + artifactId + "-" + version.version;
        return version.extensions.stream()
                .flatMap(e -> (version.classifiers.isEmpty() ? Stream.of(new ArtifactDescription("", e)) : version.classifiers.stream().map(c -> new ArtifactDescription(c, e))))
                .map(a -> toDownload(artifactId, a.classifier, version.version, a.extension, artifactBase + (a.classifier.isEmpty() ? '.' + a.extension : ('-' + a.classifier + '.' + a.extension))));
    }

    private static Download toDownload(final String artifactId, final String classifier, final String version, final String format, final String url) {
        return new Download(
                WordUtils.capitalize(artifactId.replace('-', ' ')).replace("Openejb", "OpenEJB").replace("Tomee", "TomEE"),
                classifier,
                version,
                format,
                url,
                url + ".md5",
                url + ".sha1",
                url + ".asc");
    }

    private static Stream<Version> toVersions(final String baseUrl) {
        final QuickMvnMetadataParser handler = new QuickMvnMetadataParser();
        final String base = MVN_BASE + baseUrl;
        try (final InputStream stream = new URL(base + "/maven-metadata.xml").openStream()) {
            final SAXParser parser = FACTORY.newSAXParser();
            parser.parse(stream, handler);
            return handler.foundVersions.stream().map(v -> new Version(base, v)).parallel();
        } catch (final Exception e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    @AllArgsConstructor
    public static class Version {
        private final String base;
        private final String version;
        private final Collection<String> classifiers = new ArrayList<>();
        private final Collection<String> extensions = new ArrayList<>();

        private Version extensions(final String... values) {
            extensions.addAll(asList(values));
            return this;
        }

        private Version classifiers(final String... values) {
            classifiers.addAll(asList(values));
            return this;
        }
    }

    @Data
    public static class ArtifactDescription {
        private final String classifier;
        private final String extension;
    }

    @Data
    public static class Download {
        private final String name;
        private final String classifier;
        private final String version;
        private final String format;
        private final String url;
        private final String md5;
        private final String sha1;
        private final String asc;
        private String date;
        private long size;
    }

    private static class QuickMvnMetadataParser extends DefaultHandler {
        private boolean versioning = false;
        private boolean versions = false;
        private StringBuilder version;
        private final Collection<String> foundVersions = new ArrayList<>();

        @Override
        public void startElement(final String uri, final String localName,
                                 final String name, final Attributes attributes) throws SAXException {
            if ("versioning".equalsIgnoreCase(name)) {
                versioning = true;
            } else if ("versions".equalsIgnoreCase(name)) {
                versions = true;
            } else if (versioning && versions && "version".equalsIgnoreCase(name)) {
                version = new StringBuilder();
            }
        }

        @Override
        public void characters(final char[] ch, final int start, final int length) throws SAXException {
            if (version != null) {
                version.append(new String(ch, start, length));
            }
        }

        public void endElement(final String uri, final String localName, final String name) throws SAXException {
            if ("versioning".equalsIgnoreCase(name)) {
                versioning = false;
            } else if ("versions".equalsIgnoreCase(name)) {
                versions = false;
            } else if ("version".equalsIgnoreCase(name)) {
                foundVersions.add(version.toString());
            }
        }
    }
}
