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

public class AsciidocAdjustHeadingLevels {

    private final List<String> completed = new ArrayList<String>();
    private HeadingLevel headingLevel;
    private Consumer<String> processor;

    public AsciidocAdjustHeadingLevels() {
        this.headingLevel = new HeadingLevel(null, "", "");
        this.processor = headingLevel::process;
    }

    public static void main(String[] args) throws Exception {

        final File docs = new File("repos/tomee-8.0/docs/");

        Files.walk(docs.toPath())
                .map(Path::toFile)
                .filter(File::isFile)
                .filter(path -> path.getName().endsWith(".adoc"))
                .forEach(AsciidocAdjustHeadingLevels::process);

    }

    public static void process(final File file) {
        final AsciidocAdjustHeadingLevels fix = new AsciidocAdjustHeadingLevels();

        try {
            final List<String> lines = new ArrayList<>();
            Collections.addAll(lines, IO.slurp(file).split("\n"));

            for (final String line : lines) {
                fix.process(line);
            }

            // Update the destination readme file
            IO.copy(IO.read(Join.join("\n", fix.completed) + "\n"), file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void process(final String line) {
        processor.accept(line);
    }

    public class Codeblock {
        private final String delimiter;

        public Codeblock(final String delimiter) {
            this.delimiter = delimiter;
        }

        public void findEnd(final String line) {
            completed.add(line);

            if (line.equals(delimiter)) {
                processor = headingLevel::process;
            }
        }
    }

    private final Pattern codeblockStart = Pattern.compile("^(----|....|````)$");

    public class HeadingLevel {
        private final HeadingLevel parent;
        private final String used;
        private final String current;

        public HeadingLevel(final HeadingLevel parent, final String used, final String current) {
            this.parent = parent;
            this.used = used;
            this.current = current;
        }

        private void process(String line) {
            if (codeblockStart.matcher(line).matches()) {

                processor = new Codeblock(line)::findEnd;

            } else if (line.startsWith("=") || line.startsWith("#")) {

                final HeadingLevel adjusted = headingLevel.adjust(line);

                if (adjusted != this) {
                    headingLevel = adjusted;
                    headingLevel.process(line);
                    return;
                }

                line = fix(line);
            }

            completed.add(line);
        }


        public HeadingLevel adjust(final String line) {
            final String heading = line.replaceAll("^([=#]*).*", "$1");

            if (heading.length() > used.length()) {
                return new HeadingLevel(this, heading, this.current + "=");
            }

            if (heading.length() < used.length()) {
                return parent.adjust(line);
            }

            return this;
        }

        public String fix(final String line) {
            return line.trim().replaceAll("^[=#]* *", current + " ");
        }
    }
}
