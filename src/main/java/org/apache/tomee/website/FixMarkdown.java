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
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class FixMarkdown {

    private final List<String> completed = new ArrayList<String>();
    private Consumer<String> processor = this::findJbakeHeader;

    public static void process(final Example example) {
        process(example.getDestReadme());
    }

    public static void process(final Docs.Doc doc) {
        process(doc.getSource());
    }

    public static void process(final File destReadme) {
        if (!Example.getExtension(destReadme).startsWith("md")) return;

        final FixMarkdown fixMarkdown = new FixMarkdown();

        try {
            final List<String> lines = new ArrayList<>();
            Collections.addAll(lines, IO.slurp(destReadme).split("\n"));

            for (final String line : lines) {
                fixMarkdown.process(line);
            }

            fixMarkdown.process("");

            // Update the destination readme file
            IO.copy(IO.read(Join.join("\n", fixMarkdown.completed)), destReadme);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void process(final String line) {
        processor.accept(line);
    }

    private void findJbakeHeader(final String line) {
        completed.add(line);

        if (line.equals("~~~~~~")) {
            processor = this::findWrappableLines;
        }
    }

    private void findWrappableLines(final String line) {
        if (isWrappable(line)) {
            processor = new Unwrap()::process;
            processor.accept(line);
        } else {
            completed.add(line);
        }
    }

    public class Unwrap {
        private final List<String> lines = new ArrayList<>();

        public void process(final String line) {
            if (!isWrappable(line)) {
                completed.add(Join.join(" ", lines));

                processor = FixMarkdown.this::findWrappableLines;
                processor.accept(line);
                return;
            }

            lines.add(line);
        }
    }

    private final Pattern alpha = Pattern.compile("^[a-zA-Z].*");

    private boolean isWrappable(final String line) {
        return alpha.matcher(line).matches();
    }
}
