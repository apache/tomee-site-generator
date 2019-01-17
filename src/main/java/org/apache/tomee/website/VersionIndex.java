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

    /**
     * Add the available languages to the /target/jbake/content/tomee-8.0/index.html
     * Add the available languages to the /target/jbake/content/tomee-8.0/es/index.html
     *
     * @param source
     */
    public void prepare(final Source source) {
        final File docs = sources.getJbakeContentDestFor(source, "docs");
        final File examples = sources.getJbakeContentDestFor(source, ""); // target/jbake/content/tomee-8.0/

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
            List<String> listOfLanguagesDirs = obtainListOfExamplesLanguages(examples);

            if (listOfLanguagesDirs.size() > 0) {

                index.append(" - link:examples[Examples]");

                for (String LanguageDir : listOfLanguagesDirs) {
                    if (!LanguageDir.equalsIgnoreCase("en")) {
                        index.append(" link:" + LanguageDir + "/examples" + "[ [" + LanguageDir + "\\] ]");
                    }
                }

                index.append("\n");
            }
            index.append(" - link:javadoc[Javadoc]\n");

            IO.copy(IO.read(index.toString()), new File(docs.getParentFile(), "index.adoc"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> obtainListOfExamplesLanguages(File file) { // target/jbake/content/tomee-8.0/
        List<String> listOfLanguages = new ArrayList<>();

        File[] directories = new File(file.getAbsolutePath()).listFiles(File::isDirectory);

        File temp = null;

        //Obtain the list of languages different than English
        for (File directory : directories) {
            temp = new File(directory.getAbsolutePath() + File.separator + "examples");
            if (temp.exists()) {
                listOfLanguages.add(directory.getName());
            }
        }

        //check if English language exist
        temp = new File(file.getAbsolutePath()+File.separator+"examples");
        if(temp.exists()){
            listOfLanguages.add("en");
        }

        return listOfLanguages;
    }

}
