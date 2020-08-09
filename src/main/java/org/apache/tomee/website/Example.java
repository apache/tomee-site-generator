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

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Example {
    private final File srcReadme;
    private File destReadme;
    private final String name;
    private final String ext;
    private final String href;
    private final String category;

    public String getLanguage() {
        return language;
    }

    private String language = "";

    public Example(final File srcReadme, final String name, final String ext, final String href,
                   final String category) {
        this.srcReadme = srcReadme;
        this.name = name;
        this.ext = ext;
        this.href = href;
        this.category = category;
    }

    public Example(final File srcReadme, final String name, final String ext, final String href, final String category,
                   final String language) {
        this.srcReadme = srcReadme;
        this.name = name;
        this.ext = ext;
        this.href = href;
        this.category = category;
        this.language = language;
    }

    public void updateDestination(final File examplesDir) { //target/jbake/<tomeeBranch>


        if (this.language.equalsIgnoreCase("") || this.language.equalsIgnoreCase("en")) {
            File languageDir = new File(examplesDir + File.separator + "examples");
            if (!languageDir.exists()) {
                languageDir.mkdirs();    //tomee8.0/examples
            }
            this.setDestReadme(new File(examplesDir + File.separator + "examples", this.getName() + "." + this.getExt()));
        } else {

            File languageDir = new File(examplesDir + File.separator + getLanguage() + File.separator + "examples");
            if (!languageDir.exists()) {
                languageDir.mkdirs();    // tomee8.0/es/examples/
            }

            this.setDestReadme(new File(languageDir.getAbsolutePath(), this.getName() + "." + this.getExt()));
        }
    }

    public File getDestReadme() {
        return destReadme;
    }

    public void setDestReadme(final File destReadme) {
        this.destReadme = destReadme;
    }

    public String getName() {
        return name;
    }

    public String getHref() {
        return href;
    }

    public File getSrcReadme() {
        return srcReadme;
    }

    public String getExt() {
        return ext;
    }

    public String getCategory() {
        return category;
    }

    public static Example from(final File readme) {
        final String ext = getExtension(readme);
        final String exampleName = readme.getParentFile().getName();
        final String language = getLanguage(readme);

        if (language.equalsIgnoreCase("") || language.equalsIgnoreCase("en")) {

            return new Example(readme, exampleName, ext, exampleName + ".html", "Examples", "en");

        } else if (language.equalsIgnoreCase("es")) {

            return new Example(readme, exampleName, ext, exampleName + ".html", "Ejemplos", language);

        } else if (language.equalsIgnoreCase("pt")) {

            return new Example(readme, exampleName, ext, exampleName + ".html", "Exemplos", language);

        } else {

            return new Example(readme, exampleName, ext, exampleName + ".html", "Examples", language);

        }
    }

    private static String getLanguage(File readme) {
        Pattern ptn = Pattern.compile("_(.*?)\\.");
        Matcher matcher = ptn.matcher(readme.getName());
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    public static String getExtension(final File readme) {
        return readme.getName().replaceFirst("[^.]+\\.", "");
    }

    @Override
    public String toString() {
        return "Example{" +
                "name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", category='" + category + '\'' +
                ", destReadme=" + destReadme +
                '}';
    }
}
