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

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.openejb.loader.IO;
import org.jbake.app.Parser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GroupedIndex {

    private final File directory;
    private final String type;

    public GroupedIndex(final File directory, final String type) {
        this.directory = directory;
        this.type = type;
    }

    public static void process(final File directory, final String type) {
        new GroupedIndex(directory, type).process();
    }

    public void process() {

        final List<String> sectionsFormatted = new ArrayList<>();


        final List<Doc> docs = list(directory);
        final Map<String, List<Doc>> sections = docs.stream().collect(Collectors.groupingBy(Doc::getGroup));

        for (final Map.Entry<String, List<Doc>> entry : sections.entrySet()) {
            final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            final PrintStream out = new PrintStream(bytes);

            out.printf("            <div class=\"group-title\">%s</div>\n", entry.getKey());
            out.printf("            <ul class=\"group\">\n");
            entry.getValue().stream().sorted().forEach(doc -> {
                out.printf("              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"%s\">%s</a></li>\n", doc.getHref(), doc.getTitle());
            });
            out.printf("            </ul>\n");

            sectionsFormatted.add(new String(bytes.toByteArray()));
        }

        try (final PrintStream out = print(directory, "index.html")) {
            out.printf("type=%s\n", type);
            out.printf("status=published\n");
            out.printf("~~~~~~\n");

            final ListIterator<String> iterator = sectionsFormatted.listIterator();
            while (iterator.hasNext()) {
                out.printf("        <div class=\"row\">\n");
                out.printf("          <div class=\"col-md-4\">\n");
                out.printf(iterator.hasNext() ? iterator.next() : "");
                out.printf("          </div>\n");
                out.printf("          <div class=\"col-md-4\">\n");
                out.printf(iterator.hasNext() ? iterator.next() : "");
                out.printf("          </div>\n");
                out.printf("          <div class=\"col-md-4\">\n");
                out.printf(iterator.hasNext() ? iterator.next() : "");
                out.printf("          </div>\n");
                out.printf("        </div>\n");
            }
        }
    }

    public static PrintStream print(final File directory, final String child) {
        try {
            return new PrintStream(IO.write(new File(directory, child)));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Doc> list(final File directory) {
        try {
            return Files.walk(directory.toPath())
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .filter(Docs::isRendered)
                    .map(this::parse)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Doc parse(final File file) {
        final Parser parser = new Parser(new CompositeConfiguration(), file.getAbsolutePath());
        final Map<String, Object> map = parser.processFile(file);

        if (map == null) {
            return new Doc("Unknown", Docs.simpleName(file), Docs.href(directory, file), file);
        }

        final String title = getTitle(map, file);
        final String group = Optional.ofNullable(map.get("index-group")).orElse("Unknown") + "";


        return new Doc(group, title, Docs.href(directory, file), file);
    }

    private String getTitle(final Map<String, Object> map, final File file) {
        if (map.get("short-title") != null) return map.get("short-title") + "";
        if (map.get("title") != null) return map.get("title") + "";
        return Docs.simpleName(file);
    }

    public static class Doc implements Comparable<Doc> {

        private final String group;
        private final String title;
        private final String href;
        private final File source;

        public Doc(final String group, final String title, final String href, final File source) {
            this.group = group;
            this.title = title;
            this.href = href;
            this.source = source;
        }

        public String getGroup() {
            return group;
        }

        public String getTitle() {
            return title;
        }

        public String getHref() {
            return href;
        }

        public File getSource() {
            return source;
        }

        @Override
        public int compareTo(final Doc o) {
            return this.title.toLowerCase().compareTo(o.title.toLowerCase());
        }
    }
}
