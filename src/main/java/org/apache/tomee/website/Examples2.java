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

import java.io.File;
import java.io.IOException;
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
                .peek(JbakeHeaders::addJbakeHeader)
                .peek(FixMarkdown::process)
                .collect(Collectors.toList());


        // Add any missing JBake headers


        // Create an index.adoc file
        final StringBuilder index = new StringBuilder();
        index.append(":jbake-type: page\n")
                .append(":jbake-status: published\n")
                .append(":jbake-title: Examples\n");

        for (final Example example : examples) {
            index.append(" - link:")
                    .append(example.getHref())
                    .append("[")
                    .append(example.getName())
                    .append("]")
                    .append("\n")
            ;
        }

        try {
            IO.copy(IO.read(index.toString()), new File(destDir, "index.adoc"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        https://javaee.github.io/javaee-spec/javadocs/javax/servlet/http/HttpServletMapping.html
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
