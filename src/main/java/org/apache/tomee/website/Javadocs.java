/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tomee.website;

import lombok.Data;
import org.apache.openejb.loader.Files;
import org.apache.openejb.loader.IO;
import org.apache.openejb.util.Join;
import org.apache.openejb.util.Pipe;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.openejb.loader.Files.mkdirs;

/**
 * For each git repo (source) it will collects the `src/main/java` contents
 * into one large source tree and use the Javadoc proccessor directly in code
 * to (with Asciidoclet configured) to generate a single javadoc for that repo.
 *
 * The examples/ directory for each repo will be filtered out and not included
 * in the final aggregated javadoc.
 */
public class Javadocs {

    private final Sources sources;
    private final List<JavadocSource> javadocSources = new ArrayList<>();

    public Javadocs(final Sources sources) {
        this.sources = sources;
    }

    /**
     * Method is called for each git repo configured
     * as a codebase that contributes to the documentation
     *
     * This is currently active branches of TomEE, but will
     * be other sources in the future such as Sheldon
     * and Chatterbox
     *
     * This method recurssively walks the already cloned
     * git repo (the Source) and collects all `src/main/java`
     * directories.
     *
     * All the files in these directories are aggregated into
     * one single director at `target/<source.name>-java`.
     * For example `target/tomee-8.0-java`
     *
     * We then run the javadoc processor on `target/<source.name>-java`
     * and ensure all generated javadoc is placed at:
     *
     *  - `<sources.generated>/<source.name>/javadoc/`
     *
     * For example if the Source instance represents TomEE 8.0
     * and the Sources.getGenerated() returns `target/site-1.0-SNAPSHOT`
     * we would generate all the javadoc into this location:
     *
     *  - `target/site-1.0-SNAPSHOT/tomee-8.0/javadoc`
     *
     * @param source
     */
    public void prepare(final Source source) {
        final File javaSources = mkdirs(new File(String.format("target/javadocs/%s-src", source.getName())));

        copySource(source, source, javaSources);
        for (final Source related : source.getRelated()) {
            copySource(source, related, javaSources);
        }

        // This part will be completed later when the perform stage is executed
        source.addPerform(() -> {
            final File javadocOutput = sources.getGeneratedDestFor(source, "javadoc");
            final ProcessBuilder cmd = new ProcessBuilder(
                    getJavadocCommand().getAbsolutePath(),
                    "-tag", "example.en:a:Examples (en):",
                    "-tag", "example.es:a:Examples (es):",
                    "-tag", "example.pt:a:Examples (pt):",
                    "-sourcepath", javaSources.getAbsolutePath(),
                    "-d", javadocOutput.getAbsolutePath(),
                    "-encoding", "utf-8"
            );

            Stream.of(javaSources.listFiles())
                    .filter(File::isDirectory)
                    .forEach(file -> {
                        cmd.command().add("-subpackages");
                        cmd.command().add(file.getName());
                    });

            try {
                final Process process = cmd.start();
                Pipe.pipe(process);
                process.waitFor();
            } catch (IOException e) {
                throw new IllegalStateException("Command failed");
            } catch (InterruptedException e) {
                Thread.interrupted();
                throw new IllegalStateException("Command failed");
            }

            final String favicon = getFavicon(source);

            // Scrub generated timestamps as it causes 26k needless file updates
            // on the svn commit for every time the generator runs
            try {
                java.nio.file.Files.walk(javadocOutput.toPath())
                        .map(Path::toFile)
                        .filter(File::isFile)
                        .filter(this::isHtml)
                        .map(Content::from)
                        .map(Javadocs::removeGeneratedDate)
                        .map(content -> addFavicon(content, favicon))
                        .forEach(Content::write);
            } catch (IOException e) {
                throw new IllegalStateException("Failed to update generated javadoc html");
            }

        });
    }

    private String getFavicon(final Source source) {
        if (source.getName().startsWith("jakarta")) {
            return "/img/jakarta-favicon.ico";
        } else if (source.getName().startsWith("microprofile")) {
            return "/img/microprofile-favicon.png";
        } else {
            return "/favicon.png";
        }
    }

    public List<JavadocSource> getJavadocSources() {
        return javadocSources;
    }

    private static Content removeGeneratedDate(final Content content) {
        final List<String> lines = Stream.of(content.content.split("\n"))
                .filter(line -> !line.contains("<!-- Generated by javadoc"))
                .filter(line -> !line.contains("<meta name=\"date\" content=\""))
                .collect(Collectors.toList());

        final String updated = Join.join("\n", lines) + "\n";

        return content.modified(updated);
    }

    private static Content addFavicon(final Content content, final String favicon) {
        final String link = String.format("%n<link rel=\"shortcut icon\" href=\"%s\">%n", favicon);
        return content.modified(content.content.replace("</head>", link + "</head>"));
    }

    @Data
    @lombok.AllArgsConstructor
    private static class Content {
        private final File file;
        private final String content;

        public Content modified(final String newContent) {
            return new Content(file, newContent);
        }

        public void write() {
            try {
                IO.copy(IO.read(content), file);
            } catch (IOException e) {
                throw new UncheckedIOException("Unable to write file: " + file.getAbsolutePath(), e);
            }
        }

        public static Content from(final File file) {
            try {
                return new Content(file, IO.slurp(file));
            } catch (IOException e) {
                throw new UncheckedIOException("Unable to read file: " + file.getAbsolutePath(), e);
            }
        }
    }

    private void copySource(final Source parent, final Source source, final File javaSources) {
        final JavadocSources javadocSources = new JavadocSources();
        source.setComponent(JavadocSources.class, javadocSources);

        final Predicate<String> wanted = wanted(parent, source);

        try {
            java.nio.file.Files.walk(source.getDir().toPath())
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .filter(this::isJava)
                    .filter(source.getJavadocFilter()::include)
                    .filter(source.getJavadocFilter()::exclude)
                    .forEach(file -> {
                        try {
                            final String relativePath = file.getAbsolutePath().replace(File.separatorChar, '/').replaceAll(".*/src/main/java/", "");

                            if (!wanted.test(relativePath)) return;

                            final File dest = new File(javaSources, relativePath);
                            Files.mkdirs(dest.getParentFile());
                            IO.copy(file, dest);
                            javadocSources.add(new JavadocSource(relativePath, dest));
                        } catch (IOException e) {
                            throw new IllegalStateException(e);
                        }
                    });
        } catch (IOException e) {
            throw new IllegalStateException("Failed to aggregate java sources");
        }
    }

    private Predicate<String> wanted(final Source parent, final Source source) {
        if (source.getJavadocPackages() != null) return source.getJavadocPackages().asPredicate();
        if (parent.getJavadocPackages() != null) return parent.getJavadocPackages().asPredicate();
        return s -> true;
    }

    public static File getJavadocCommand() {

        final File java_home = System.getenv("JAVA_HOME") != null ? new File(System.getenv("JAVA_HOME")) : new File("");
        final File javaHome = new File(System.getProperty("java.home"));

        final Supplier<File>[] locations = new Supplier[]{
                () -> new File(java_home, "bin/javadoc"),
                () -> new File(java_home, "bin/javadoc.exe"),
                () -> new File(java_home.getParentFile(), "bin/javadoc"),
                () -> new File(java_home.getParentFile(), "bin/javadoc.exe"),
                () -> new File(javaHome, "bin/javadoc"),
                () -> new File(javaHome, "bin/javadoc.exe"),
                () -> new File(javaHome.getParentFile(), "bin/javadoc"),
                () -> new File(javaHome.getParentFile(), "bin/javadoc.exe"),
        };

        return Stream.of(locations)
                .map(Supplier::get)
                .filter(File::exists)
                .filter(File::canExecute)
                .findFirst().orElseThrow(() -> new IllegalStateException("Cannot find javadoc command"));
    }

    public static void main(String[] args) {
    }

    private static void copy(final File file, File javaSources) {
    }

    private boolean isJava(final File file) {
        return file.getName().endsWith(".java");
    }

    private boolean isHtml(final File file) {
        return file.getName().endsWith(".html");
    }
}
