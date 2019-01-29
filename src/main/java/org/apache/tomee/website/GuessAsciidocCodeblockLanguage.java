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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class GuessAsciidocCodeblockLanguage {

    private final List<String> completed = new ArrayList<String>();
    private Consumer<String> processor = this::findSourceStart;

    public static void main(String[] args) throws Exception {

        final File docs = new File("repos/tomee-8.0/docs/");

        Files.walk(docs.toPath())
                .map(Path::toFile)
                .filter(File::isFile)
                .filter(path -> path.getName().endsWith(".adoc"))
                .forEach(GuessAsciidocCodeblockLanguage::process);

    }

    public static void process(final File destReadme) {
        final GuessAsciidocCodeblockLanguage fix = new GuessAsciidocCodeblockLanguage();

        try {
            final List<String> lines = new ArrayList<>();
            Collections.addAll(lines, IO.slurp(destReadme).split("\n"));

            for (final String line : lines) {
                fix.process(line);
            }

            try {
                fix.process((String) null);
            } catch (Exception e) {
                System.out.println("Failed " + destReadme.getAbsolutePath() + " " + e.getMessage());
            }

            // Update the destination readme file
            IO.copy(IO.read(Join.join("\n", fix.completed) + "\n"), destReadme);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void process(final String line) {
        processor.accept(line);
    }

    private void findSourceStart(final String line) {
        if (line == null) return;

        if (line.startsWith("[source")) {

            this.processor = new Codeblock(line)::findStartDashes;

        } else {

            completed.add(line);

        }
    }

    private final Pattern commands = Pattern.compile("^(javac?|mvn|cd|ls|svn|git|sudo|tar|unzip|jar|karan.*?|mingus.*?) .*");
    private final Pattern java = Pattern.compile("^(@|public |private |protected |import |package |for |while |do |switch |try ).*");
    private final Pattern properties = Pattern.compile("^[A-Za-z0-9][^ =:]+[ =:].*[^;{]");

    public class Codeblock {

        private String lang;
        private final List<String> code = new ArrayList<>();

        public Codeblock(final String source) {
            this.lang = source.trim().replaceAll("^\\[source,?|\\]$", "");
        }

        private void findStartDashes(final String line) {
            if (!line.equals("----")) throw new IllegalStateException("Expected dashes, found: " + line);
            processor = this::guessLanguage;
        }

        private void guessLanguage(final String line) {
            if (line.equals("----")) {
                findEndDashes(line);
                return;
            }

            final String text = line.trim();
            if (text.startsWith("<")) {

                lang = "xml";

            } else if (text.startsWith("{") || text.startsWith("[")) {

                lang = "json";

            } else if (commands.matcher(text).matches()) {

                lang = "bash";

            } else if (java.matcher(text).matches() || text.endsWith(";")) {

                lang = "java";

            } else if (properties.matcher(text).matches()) {

                lang = "properties";

            }

            code.add(line);

            if (!"".equals(lang)) processor = this::findEndDashes;
        }

        private void findEndDashes(final String line) {
            if (!line.equals("----")) {
                code.add(line);
                return;
            }

            if ("".equals(lang)) {
                completed.add("[source]");
            } else {
                completed.add("[source," + lang + "]");
            }

            completed.add("----");
            completed.addAll(code);
            completed.add("----");

            processor = GuessAsciidocCodeblockLanguage.this::findSourceStart;
        }
    }
}
