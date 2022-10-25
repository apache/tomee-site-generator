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
import org.tomitribe.util.PrintString;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Checks {

    public void check(final File contentDir, final File generatedDir) {
        final List<String> ignored = getIgnored();

        try {
            final BiPredicate<Path, BasicFileAttributes> sources = (path, attributes) -> {
                if (!path.toFile().isFile()) return false;
                if (path.toString().endsWith(".adoc") || path.toString().endsWith(".md")) return true;
                return false;
            };

            final List<String> missing = Files.find(contentDir.toPath(), 100, sources)
                    .map(Path::toFile)
                    .map(File::getAbsolutePath)
                    .map(s -> s.substring(contentDir.getAbsolutePath().length() + 1))
                    // was the html not generated?
                    .filter(s -> {
                        final String html = s.replaceAll("\\.(adoc|md)$", ".html");
                        final File expected = new File(generatedDir, html);
                        return !expected.exists();
                    })
                    // is this file not in the list of known issues?
                    .filter(s -> !ignored.contains(s))
                    .collect(Collectors.toList());

            if (missing.size() > 0) {
                throw new HtmlGenerationFailedException(missing);
            }
            missing.forEach(System.out::println);
            System.out.println(missing);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private List<String> getIgnored() {
        try {
            final String content = IO.slurp(this.getClass().getClassLoader().getResource("ignored.txt"));

            return Stream.of(content.split(" *\r?\n *"))
                    .filter(s -> s.length() > 0)
                    .filter(s -> !s.startsWith("#"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static class HtmlGenerationFailedException extends RuntimeException {

        private final List<String> missing;

        public HtmlGenerationFailedException(final List<String> missing) {
            this.missing = missing;
        }

        @Override
        public String getMessage() {
            final PrintString message = new PrintString();
            message.printf("%s source files did not generate html%n", missing.size());
            missing.forEach(message::println);
            return message.toString();
        }
    }
}
