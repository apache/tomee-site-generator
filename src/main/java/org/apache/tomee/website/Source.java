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

public class Source {
    private final String name;
    private final String scmUrl;
    private final String branch;
    private final boolean latest;
    private File dir;

    public Source(final String scmUrl, final String branch, final String name) {
        this(scmUrl, branch, name, false);
    }

    public Source(final String scmUrl, final String branch, final String name, final boolean latest) {
        this.scmUrl = scmUrl;
        this.branch = branch;
        this.name = name;
        this.latest = latest;
    }

    public boolean isLatest() {
        return latest;
    }

    public String getScmUrl() {
        return scmUrl;
    }

    public String getBranch() {
        return branch;
    }

    public String getName() {
        return name;
    }

    public File getDir() {
        return dir;
    }

    public void setDir(final File dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Source{" +
                "name='" + name + '\'' +
                ", scmUrl='" + scmUrl + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }

}
