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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupedIndex {

    private final File directory;
    private final String type;

    public GroupedIndex(final File directory, final String type) {
        this.directory = directory;//target/jbake/<tomeeBranch> or target/jbake/<tomeeBranch>/docs
        this.type = type;
    }

    public static void process(final File directory, final String type) {
        new GroupedIndex(directory, type).process();
    }


    public void process() {

        final List<Doc> docs = list(directory);

        final List<String> detectedLanguages = new ArrayList<>();
        Set<String> uniqueValues = new HashSet<>();
        for (Doc doc1 : docs) {
            if (uniqueValues.add(doc1.language)) {
                detectedLanguages.add(doc1.language);
            }
        }

        for (String language : detectedLanguages) {
            sortSectionsAndCreateIndexFiles(docs, language);
        }
    }

    private void sortSectionsAndCreateIndexFiles(List<Doc> docs, String language) {

        final Map<String, List<Doc>> sections = new HashMap<>();

        //filtering only documents with the same language for: target/jbake/<tomeeBranch>/docs
        if (this.type.equalsIgnoreCase("docsindex")) {

            for (Doc doc1 : docs) {
                if (doc1.language.equalsIgnoreCase(language)) {
                    sections.computeIfAbsent(doc1.getGroup(), k -> new ArrayList<>()).add(doc1);
                }
            }
        } else {

            if (this.type.equalsIgnoreCase("examplesindex")) {
                for (Doc doc1 : docs) { //filtering only documents with the same language for: target/jbake/<tomeeBranch> and type = examplesindex
                    if (doc1.language.equalsIgnoreCase(language) && doc1.source.getParentFile().getName().equalsIgnoreCase("examples")) {
                        sections.computeIfAbsent(doc1.getGroup(), k -> new ArrayList<>()).add(doc1);
                    }
                }
            } else {//any type (used in GroupedIndexTest.java
                for (Doc doc1 : docs) {
                    if (doc1.language.equalsIgnoreCase(language)) {
                        sections.computeIfAbsent(doc1.getGroup(), k -> new ArrayList<>()).add(doc1);
                    }
                }
            }
        }

        final List<String> sectionsFormatted = new ArrayList<>();

        /**
         * We want to sort the sections with the most entries towards the top.
         * Unless it is crazy big, then we put it in a special section at the bottom.
         */
        sections.entrySet().stream()
                .filter(entry -> entry.getValue().size() < 10)
                .sorted((o1, o2) -> new Integer(o2.getValue().size()).compareTo(o1.getValue().size()))
                .forEach(entry -> {

                    final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    final PrintStream out = new PrintStream(bytes);

                    out.printf("            <div class=\"group-title\">%s</div>\n", entry.getKey());
                    out.printf("            <ul class=\"group\">\n");
                    entry.getValue().stream().sorted().forEach(doc -> {

                        out.printf(
                                "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"%s\">%s</a></li>\n",
                                doc.getHref(), doc.getTitle());


                    });
                    out.printf("            </ul>\n");

                    sectionsFormatted.add(new String(bytes.toByteArray()));

                });

        try (final PrintStream out = print(directory.getAbsolutePath(), "index.html", language, this.type)) {

            out.printf("type=%s\n", type);
            out.printf("status=published\n");
            out.printf("~~~~~~\n");

            {
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

            sections.entrySet().stream()
                    .filter(entry -> entry.getValue().size() >= 10)
                    .sorted((o1, o2) -> new Integer(o1.getValue().size()).compareTo(o2.getValue().size()))
                    .forEach(entry -> {

                        out.printf("        <div class=\"row\">\n");
                        out.printf("          <div class=\"col-md-12\">\n");
                        out.printf("            <div class=\"group-title large\">%s</div>\n", entry.getKey());
                        out.printf("          </div>\n");
                        out.printf("        </div>\n");

                        final ListIterator<Doc> iterator = entry.getValue().stream()
                                .sorted()
                                .collect(Collectors.toList())
                                .listIterator();

                        final int i = (int) Math.ceil(entry.getValue().size() / 3f);

                        out.printf("        <div class=\"row\">\n");
                        while (iterator.hasNext()) {
                            int count = 0;

                            out.printf("          <div class=\"col-md-4\">\n");
                            out.printf("            <ul class=\"group\">\n");
                            while (iterator.hasNext() && count++ < i) {
                                final Doc doc = iterator.next();
                                out.printf(
                                        "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"%s\">%s</a></li>\n",
                                        doc.getHref(), doc.getTitle());

                            }
                            out.printf("            </ul>\n");
                            out.printf("          </div>\n");
                        }
                        out.printf("        </div>\n");
                    });

        } catch (Exception e) {
            throw e; //ToDo: implementing proper logger and catch.
        }
    }

    public static PrintStream print(final String directory, final String child, String language, String type) { //target/jbake/<tomeeBranch> o  tambien //target/jbake/<tomeeBranch>/docs
        try {
            File fileParentFolder = null;

            if (type.equalsIgnoreCase("docsindex")) {
                fileParentFolder = new File(directory);
            } else {
                if (type.equalsIgnoreCase("examplesindex")) {
                    if (language.equalsIgnoreCase("en")) {
                        fileParentFolder = new File(directory + File.separator + "examples");
                    } else {
                        fileParentFolder = new File(directory + File.separator + language + File.separator + "examples");

                    }
                } else {
                    fileParentFolder = new File(directory);
                }
            }

            return new PrintStream(IO.write(new File(fileParentFolder, child)));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Doc> list(final File directory) {//target/jbake/<tomeeBranch>
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

    private static String getLanguageFromPath(File file, String type) { //target/jbake/<tomeeBranch>/fr/examples/index.html
        if (type.equalsIgnoreCase("docsindex")) { //ToDo: this needs to be updated when we are going to proccess docs internationalization too.
            return "";
        } else { //examplesindex

            if (file.getParentFile().getParentFile().getName().length() > 3) {  // target/jbake/<tomeeBranchLengthIsGratherThan3>/examples/index.html
                return "en";
            } else {
                return file.getParentFile().getParentFile().getName();  // target/jbake/<tomeeBranch>/fr/examples/index.html
            }

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

        /*
             Create the document reference to be ready to be added as part of the index files like:
             target/jbake/content/tomee-8.0/docs/index.html
             target/jbake/content/tomee-8.0/docs/maven/index.html
             target/jbake/content/tomee-8.0/fr/examples/index.html
             target/jbake/content/tomee-8.0/examples/index.html

         */
        if (type.equalsIgnoreCase("examplesindex") && file.getParentFile().getName().equalsIgnoreCase("examples")) {
            String detectedLanguage = getLanguageFromPath(file, this.type);

            if (detectedLanguage.equalsIgnoreCase("en")) {
                return new Doc(group, title, Docs.href(new File(directory + File.separator + "examples"), file), file, detectedLanguage);
            } else {
                return new Doc(group, title, Docs.href(new File(directory + File.separator + detectedLanguage + File.separator + "examples"), file), file, detectedLanguage);
            }

        } else {
            // todo: Here we can implement later when doc type is docindex and not examplesindex
            return new Doc(group, title, Docs.href(directory, file), file); //default to english
        }


    }

    private String getTitle(final Map<String, Object> map, final File file) {
        if (hasValue(map.get("short-title"))) return map.get("short-title") + "";
        if (hasValue(map.get("title"))) return map.get("title") + "";
        return Docs.simpleName(file);
    }

    private boolean hasValue(final Object o) {
        if (o == null) return false;
        if ("".equals(o + "")) return false;
        return true;
    }

    public static class Doc implements Comparable<Doc> {

        @Override
        public int hashCode() {
            return Objects.hash(language);
        }

        private final String group;
        private final String title;
        private final String href;
        private final File source;

        private final String language;

        public Doc(final String group, final String title, final String href, final File source) {
            this.group = group;
            this.title = title;
            this.href = href;
            this.source = source;
            this.language = "en";
        }

        public Doc(final String group, final String title, final String href, final File source,
                   final String language) {
            this.group = group;
            this.title = title;
            this.href = href;
            this.source = source;
            this.language = language;
        }

        public String getLanguage() {
            return language;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Doc doc = (Doc) o;
            return Objects.equals(language, doc.language);
        }
    }
}
