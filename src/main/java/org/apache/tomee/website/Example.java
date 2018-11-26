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

public class Example {
    private final File srcReadme;
    private File destReadme;
    private final String name;
    private final String ext;
    private final String href;
    private final String category;

    public Example(final File srcReadme, final String name, final String ext, final String href, final String category) {
        this.srcReadme = srcReadme;
        this.name = name;
        this.ext = ext;
        this.href = href;
        this.category = category;
    }

    public void updateDestination(final File examplesDir) {
        this.setDestReadme(new File(examplesDir, this.getName() + "." + this.getExt()));
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
        final String ext = readme.getName().replaceFirst("[^.]+\\.", "");
        final String exampleName = readme.getParentFile().getName();

        return new Example(readme, exampleName, ext, exampleName + ".html", "Example");
    }
}
