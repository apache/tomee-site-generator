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
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class AddBacktickToAnnotations {

    private static final String REGEX = "([^=*].*)([^`]@\\w+)(.*)";

    private static final Pattern annotation = Pattern.compile(REGEX);

    private static final AtomicBoolean isCodeBlock = new AtomicBoolean(false);

    public static void main(String[] args) throws Exception {

        final File docs = new File("repos/tomee-8.0/docs/");

        Files.walk(docs.toPath())
             .map(Path::toFile)
             .filter(File::isFile)
             .filter(path -> path.getName().endsWith(".adoc"))
             .forEach(AddBacktickToAnnotations::process);

    }

    public static void process(final File destReadme) {
        try {
            final List<String> completed = new ArrayList<>();
            for (String line : IO.slurp(destReadme).split("\n")) {
                if (line == null) { return; }
                if (line.equals("----")) {
                    if (!isCodeBlock.get()) {
                        isCodeBlock.set(true);
                    } else {
                        isCodeBlock.set(false);
                    }
                    completed.add(line);
                } else if (!isCodeBlock.get()) {
                    completed.add(replace(line));
                } else {
                    completed.add(line);
                }
            }
            // Update the destination readme file
            IO.copy(IO.read(Join.join("\n", completed) + "\n"), destReadme);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String replace(final String line) {
        if (annotation.matcher(line).matches()) {
            return line.replaceAll("([^a-z|`])(@\\w+)", "$1`$2`");
        } else {
            return line;
        }
    }
}
