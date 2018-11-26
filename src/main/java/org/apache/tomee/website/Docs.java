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

import org.apache.openejb.loader.IO;
import org.apache.openejb.util.Join;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Docs {

    private final Sources sources;

    public Docs(final Sources sources) {
        this.sources = sources;
    }

    public void prepare(final Source source) {
        final File srcDocs = new File(source.getDir(), "docs");
        final File destDocs = sources.getDestinationFor(source, "docs");

        if (!srcDocs.exists()) return;

        try {
            IO.copyDirectory(srcDocs, destDocs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!hasIndex(destDocs)) {
            buildIndex(destDocs);
        }
    }

    private boolean hasIndex(final File destDocs) {
        return Stream.of(destDocs.listFiles())
                .filter(File::isFile)
                .filter(file -> file.getName().startsWith("index."))
                .filter(this::isRendered)
                .findFirst().isPresent();
    }

    private void buildIndex(final File destDocs) {
        try {
            final List<String> links = Files.walk(destDocs.toPath())
                    .filter(path -> path.toFile().isFile())
                    .filter(this::isRendered)
                    .map(path -> toLink(destDocs, path))
                    .map(Link::toAdoc)
                    .collect(Collectors.toList());

            final StringBuilder index = new StringBuilder();
            index.append(":jbake-type: page\n")
                    .append(":jbake-status: published\n")
                    .append(":jbake-title: Documentation\n")
                    .append("\n")
                    .append("Documentation\n\n")
            ;

            index.append(Join.join("\n", links));

            IO.copy(IO.read(index.toString()), new File(destDocs, "index.adoc"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Link toLink(final File base, final Path path) {
        final int baseLength = base.getAbsolutePath().length() + 1;

        final String href = path.toFile().getAbsolutePath().substring(baseLength)
                .replace(".adoc", ".html")
                .replace(".mdtext", ".html")
                .replace(".md", ".html");

        final String name = href.replace(".html", "");
        return new Link(href, name);
    }

    public static class Link {
        private final String href;
        private final String name;

        public Link(final String href, final String name) {
            this.href = href;
            this.name = name;
        }

        public String getHref() {
            return href;
        }

        public String getName() {
            return name;
        }

        public String toAdoc() {
            return " - link:" + href + "[" + name + "]";
        }
    }

    private boolean isRendered(final Path path) {
        final File file = path.toFile();
        return isRendered(file);
    }

    private boolean isRendered(final File file) {
        if (file.getName().endsWith(".mdtext")) return true;
        if (file.getName().endsWith(".md")) return true;
        if (file.getName().endsWith(".adoc")) return true;
        if (file.getName().endsWith(".html")) return true;
        return false;
    }
}
