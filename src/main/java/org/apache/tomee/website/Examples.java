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
import java.util.ArrayList;
import java.util.List;

public class Examples {

    private final Sources sources;
    private final List<Example> examples = new ArrayList<>();

    public Examples(final Sources sources) {
        this.sources = sources;
    }

    public void prepare(final Source source) {
        final File srcDir = new File(source.getDir(), "examples");
        final File destDir = sources.getJbakeContentDestFor(source, ""); //target/jbake/<tomeeBranch>/

        // If we don't have examples in this codebase, skip
        if (!srcDir.exists()) return;

        for (File file : srcDir.listFiles()) {
            if (file.isDirectory()) {
                if (hasReadme(file)) {

                    for (final File aReadmeFile : file.listFiles()) {
                        if (aReadmeFile.getName().startsWith("README")) {
                            File readme = aReadmeFile;
                            Example example = Example.from(readme);
                            example.updateDestination(destDir);
                            copyToDest(example);
                            JbakeHeaders.addJbakeHeader(example);
                            FixMarkdown.process(example);
                            examples.add(example);
                        }
                    }
                }
            }
        }


        GroupedIndex.process(destDir, "examplesindex"); //Creates the index.html for each example folder
//        https://javaee.github.io/javaee-spec/javadocs/javax/servlet/http/HttpServletMapping.html
    }

    public List<Example> getExamples() {
        return examples;
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
            if (file.getName().startsWith("README")) return file;
        }

        return null;
    }
}
