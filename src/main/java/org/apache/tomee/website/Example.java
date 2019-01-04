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

    private String language="";

    public Example(final File srcReadme, final String name, final String ext, final String href, final String category) {
        this.srcReadme = srcReadme;
        this.name = name;
        this.ext = ext;
        this.href = href;
        this.category = category;
    }
    public Example(final File srcReadme, final String name, final String ext, final String href, final String category, final String language) {
        this.srcReadme = srcReadme;
        this.name = name;
        this.ext = ext;
        this.href = href;
        this.category = category;
        this.language = language;
    }

    public void updateDestination(final File examplesDir) {
        //this.setDestReadme(new File(examplesDir, this.getName() + "." + this.getExt()));
        if(this.language.equalsIgnoreCase("")){
            this.setDestReadme(new File(examplesDir, this.getName() + "." + this.getExt()));
        }else{

            File languageDir = new File(examplesDir+"/es");
            languageDir.mkdirs();

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

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>aqui:"+exampleName);
        if(exampleName.equalsIgnoreCase("")){
            return new Example(readme, exampleName, ext, exampleName + ".html", "Example");
        }else{
            //return new Example(readme, exampleName+"-"+language, ext, exampleName+"-"+language + ".html", "Example/"+language);
            return new Example(readme, exampleName, ext, exampleName + ".html", "Ejemplos",language);
        }
    }

    private static String getLanguage(File readme) {
        Pattern ptn = Pattern.compile("_(.*?)\\.");
        Matcher matcher = ptn.matcher(readme.getName());
        if(matcher.find()){
            return matcher.group(1);
        }else{
            return "";
        }
    }

    public static String getExtension(final File readme) {
        return readme.getName().replaceFirst("[^.]+\\.", "");
    }
}
