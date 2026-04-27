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
import java.util.Objects;

public class JavadocSource {

    private final String className;
    private final File sourceFile;

    public JavadocSource(final String relativePath, final File sourceFile) {
        this.sourceFile = sourceFile;
        this.className = relativePath
                .replaceAll("\\.java$", "")
                .replace("/", ".");
    }

    public String getClassName() {
        return className;
    }

    public File getSourceFile() {
        return sourceFile;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof JavadocSource that)) return false;
        return Objects.equals(className, that.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className);
    }

    @Override
    public String toString() {
        return "JavadocSource(className=" + className + ", sourceFile=" + sourceFile + ")";
    }
}
