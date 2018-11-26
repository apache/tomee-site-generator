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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Examples2 {

    private final Sources sources;

    public Examples2(final Sources sources) {
        this.sources = sources;
    }

    public void prepare(final Source source) {
        final File srcDir = new File(source.getDir(), "examples");
        final File destDir = sources.getDestinationFor(source, "examples");

        // If we don't have examples in this codebase, skip
        if (!srcDir.exists()) return;

        final List<Example> examples = Stream.of(srcDir.listFiles())
                .filter(File::isDirectory)
                .filter(this::hasReadme)
                .map(this::getReadme)
                .map(Example::from)
                .peek(example -> example.updateDestination(destDir))
                .peek(this::copyToDest)
                .peek(this::addJbakeHeader)
                .collect(Collectors.toList());


        // Add any missing JBake headers


        // Create an index.adoc file
        final StringBuilder index = new StringBuilder();
        for (final Example example : examples) {
            index.append(" - ")
                    .append(example.getHref())
                    .append("[")
                    .append(example.getName())
                    .append("]")
                    .append(File.separator)
            ;
        }

        try {
            IO.copy(IO.read(index.toString()), new File(destDir, "index.adoc"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        https://javaee.github.io/javaee-spec/javadocs/javax/servlet/http/HttpServletMapping.html
    }

    private void addJbakeHeader(final Example example) {

        if (example.getExt().startsWith("md")) {

            addMarkdownHeader(example);

        } else if (example.getExt().startsWith("a")) {

            addAsciidocHeader(example);
        }
    }

    private void addAsciidocHeader(final Example example) {
        try {
            String content = IO.slurp(example.getDestReadme());
            if (content.contains(":jbake-type:")) return;

            String header = "" +
                    ":jbake-type: page\n" +
                    ":jbake-status: published\n";

            // The legacy Apache CMS setup for TomEE allowed a very similar header
            // called "Title: " to specify the title in the created html page.
            // If found, we convert it to the JBake version
            // TODO all docs should be updated and this code removed
            if (content.startsWith("Title:") || content.startsWith("title:")) {
                final List<String> lines = new ArrayList<>();
                Collections.addAll(lines, content.split("\n"));

                // remove the legacy title syntax
                final String titleLine = lines.remove(0);

                // update the header
                header += ":jbake-title:" + titleLine.substring("title:".length()).trim() + "\n";

                // update the content
                content = Join.join("\n", lines);
            }


            // Prepend the JBake header for Asciidoc
            content = header + content;

            // Update the destination readme file
            IO.copy(IO.read(content), example.getDestReadme());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addMarkdownHeader(final Example example) {
        try {
            final File readme = example.getDestReadme();
            String content = IO.slurp(readme);

            if (content.contains("~~~~~~")) return;


            String header = "" +
                    "type=page\n" +
                    "status=published\n";

            // The legacy Apache CMS setup for TomEE allowed a very similar header
            // called "Title: " to specify the title in the created html page.
            // If found, we convert it to the JBake version
            // TODO all docs should be updated and this code removed
            if (content.startsWith("Title:") || content.startsWith("title:")) {
                final List<String> lines = new ArrayList<>();
                Collections.addAll(lines, content.split("\n"));

                // remove the legacy title syntax
                final String titleLine = lines.remove(0);

                // update the header
                header += "title=" + titleLine.substring("title:".length()).trim() + "\n";

                // update the content
                content = Join.join("\n", lines);
            }

            // Prepend the JBake header for Markdown
            content = header + "~~~~~~\n" + content;

            // Update the destination readme file
            IO.copy(IO.read(content), example.getDestReadme());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Copy all the readme.mdtext to examples/foo-bar.mdtext
     */
    private void copyToDest(final Example example) {
        try {
            IO.copy(example.getSrcReadme(), example.getDestReadme());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private boolean hasReadme(final File dir) {
        return getReadme(dir) != null;
    }

    private File getReadme(final File dir) {
        for (final File file : dir.listFiles()) {
            if (file.getName().startsWith("README.")) return file;
        }

        return null;
    }
}
