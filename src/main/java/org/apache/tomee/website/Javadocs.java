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

import org.apache.openejb.loader.Files;
import org.apache.openejb.loader.IO;
import org.apache.openejb.util.Pipe;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Supplier;
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

        try {
            java.nio.file.Files.walk(source.getDir().toPath())
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .filter(this::isJava)
                    .filter(this::srcMainJava)
                    .filter(file -> !file.getAbsolutePath().contains("/tck/"))
                    .filter(file -> !file.getAbsolutePath().contains("/itests/"))
                    .filter(file -> !file.getAbsolutePath().contains("/examples/"))
                    .filter(file -> !file.getAbsolutePath().contains("-example/"))
                    .filter(file -> !file.getAbsolutePath().contains("/archetype-resources/"))
                    .forEach(file -> {
                        try {
                            final String relativePath = file.getAbsolutePath().replaceAll(".*/src/main/java/", "");
                            final File dest = new File(javaSources, relativePath);
                            Files.mkdirs(dest.getParentFile());
                            IO.copy(file, dest);
                        } catch (IOException e) {
                            throw new IllegalStateException(e);
                        }
                    });
        } catch (IOException e) {
            throw new IllegalStateException("Failed to aggregate java sources");
        }

        final ProcessBuilder cmd = new ProcessBuilder(
                getJavadocCommand().getAbsolutePath(),
                "-sourcepath",
                javaSources.getAbsolutePath(),
                "-d",
                sources.getGeneratedDestFor(source, "javadoc").getAbsolutePath()
        );

        Stream.of(javaSources.listFiles())
                .filter(File::isDirectory)
                .forEach(file -> {
                    cmd.command().add("-subpackages");
                    cmd.command().add(file.getName());
                });

        try {
            Pipe.pipe(cmd.start());
        } catch (IOException e) {
            throw new IllegalStateException("Command failed");
        }
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

    private boolean srcMainJava(final File file) {
        return file.getAbsolutePath().contains("src/main/java/");
    }

    private boolean isJava(final File file) {
        return file.getName().endsWith(".java");
    }
}
