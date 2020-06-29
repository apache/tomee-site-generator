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

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertNotNull;

/**
 * Simple utility class to make it easier to load test files
 * for certain test methods
 */
public class Scenario {

    private final ClassLoader loader;
    private final String basePath;

    public Scenario(final ClassLoader loader, final String basePath) {
        this.loader = loader;
        this.basePath = basePath;
    }

    public String get(final String resource) throws IOException {
        final String path = basePath + resource;
        final URL url = loader.getResource(path);
        assertNotNull("Resource not found: " + path, url);
        return IO.slurp(url);
    }

    public static Scenario scenario(final Class testClass, final String method) {
        final ClassLoader loader = testClass.getClassLoader();
        final String basePath = testClass.getSimpleName() + "/" + method + "/";

        return new Scenario(loader, basePath);
    }
}
