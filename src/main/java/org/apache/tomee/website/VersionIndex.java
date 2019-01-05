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

public class VersionIndex {

    private final Sources sources;

    public VersionIndex(final Sources sources) {
        this.sources = sources;
    }

    public void prepare(final Source source) {
        final File docs = sources.getJbakeContentDestFor(source, "docs");
        final File examples = sources.getJbakeContentDestFor(source, "examples");

        try {
            final StringBuilder index = new StringBuilder();
            index.append(":jbake-type: page\n")
                 .append(":jbake-status: published\n")
                 .append(":jbake-title: ")
                 .append(source.getName())
                 .append(" resources")
                 .append("\n")
                 .append("\n")
            ;

            if (docs.exists() && docs.listFiles().length > 0) {
                //ToDo: add to the doc index file the available languages for documentation.
                index.append(" - link:docs[Documentation]\n");
            }
            if (examples.exists() && examples.listFiles().length > 0) {

                List<String> listOfLanguagesDirs = obtainListOfLanguages(examples);

                index.append(" - link:examples[Examples]");

                for (String LanguageDir : listOfLanguagesDirs) {
                    index.append(" link:examples/" + LanguageDir + "[ [" + LanguageDir + "\\] ]");
                }

                index.append("\n");
            }

            IO.copy(IO.read(index.toString()), new File(docs.getParentFile(), "index.adoc"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> obtainListOfLanguages(File file) {
        List<String> listOfLanguages = new ArrayList<>();

        File[] directories = new File(file.getAbsolutePath()).listFiles(File::isDirectory);

        for (File directory : directories) {
            listOfLanguages.add(directory.getName());
        }

        return listOfLanguages;
    }

}
