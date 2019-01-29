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

public class JbakeHeaders {

    public static void addJbakeHeader(final Docs.Doc doc) {

        final String extension = Example.getExtension(doc.getSource());

        if (extension.startsWith("md")) {

            addMarkdownHeader(doc.getSource());

        } else if (extension.startsWith("a")) {

            addAsciidocHeader(doc.getSource());
        }
    }

    public static void addJbakeHeader(final Example example) {

        if (example.getExt().startsWith("md")) {

            addMarkdownHeader(example.getDestReadme());

        } else if (example.getExt().startsWith("a")) {

            addAsciidocHeader(example.getDestReadme());
        }
    }

    private static void addAsciidocHeader(final File destReadme) {
        try {
            String content = IO.slurp(destReadme);
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
            IO.copy(IO.read(content), destReadme);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addMarkdownHeader(final File destReadme) {
        try {
            final File readme = destReadme;
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
            IO.copy(IO.read(content), destReadme);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
