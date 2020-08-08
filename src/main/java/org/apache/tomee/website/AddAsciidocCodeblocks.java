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
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class AddAsciidocCodeblocks {

    private final List<String> completed = new ArrayList<String>();
    private Consumer<String> processor = this::findStartCodeblock;

    public static void main(String[] args) throws Exception {

        final File docs = new File("repos/master/examples/");

        Files.walk(docs.toPath())
                .map(Path::toFile)
                .filter(File::isFile)
                .filter(path -> path.getName().endsWith(".adoc"))
                .peek(AddAsciidocCodeblocks::process)
                .forEach(AddAsciidocCodeblocks::fixLanguage);
        ;

    }

    private static void fixLanguage(final File file) {
        try {
            String contents = IO.slurp(file);

            contents = contents.replace("[source,java]\n----\n<", "[source,xml]\n----\n<");
            contents = contents.replace("[source,java]\n----\n----", "[source,console]\n----\n----");

            IO.copy(IO.read(contents), file);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to process file: " + file.getAbsolutePath(), e);
        }
    }

    public static void process(final File destReadme) {
        final AddAsciidocCodeblocks fix = new AddAsciidocCodeblocks();

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

    private void findStartCodeblock(final String line) {
        if (line == null) return;

        if (line.equals("....")) {
            completed.add("[source,java]");
            completed.add("----");
            processor = this::findEndCodeblock;
        } else {
            completed.add(line);
        }
    }

    private void findEndCodeblock(final String line) {
        if (line == null) throw new IllegalStateException("Codeblock not terminated");
        if (line.equals("....")) {
            completed.add("----");
            processor = this::findStartCodeblock;
        } else {
            completed.add(line);
        }
    }
}
