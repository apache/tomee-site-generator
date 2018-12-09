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

/**
 * For each git repo (source) it will collects the `src/main/java` contents
 * into one large source tree and use the Javadoc proccessor directly in code
 * to (with Asciidoclet configured) to generate a single javadoc for that repo.
 *
 * The examples/ directory for each repo will be filtered out and not included
 * in the final aggregated javadoc.
 */
public class Javadocs {

    private final Sources sources;

    public Javadocs(final Sources sources) {
        this.sources = sources;
    }

    /**
     * Method is called for each git repo configured
     * as a codebase that contributes to the documentation
     *
     * This is currently active branches of TomEE, but will
     * be other sources in the future such as Sheldon
     * and Chatterbox
     *
     * This method recurssively walks the already cloned
     * git repo (the Source) and collects all `src/main/java`
     * directories.
     *
     * All the files in these directories are aggregated into
     * one single director at `target/<source.name>-java`.
     * For example `target/tomee-8.0-java`
     *
     * We then run the javadoc processor on `target/<source.name>-java`
     * and ensure all generated javadoc is placed at:
     *
     *  - `<sources.generated>/<source.name>/javadoc/`
     *
     * For example if the Source instance represents TomEE 8.0
     * and the Sources.getGenerated() returns `target/site-1.0-SNAPSHOT`
     * we would generate all the javadoc into this location:
     *
     *  - `target/site-1.0-SNAPSHOT/tomee-8.0/javadoc`
     *
     * @param source
     */
    public void prepare(final Source source) {

        // do the magic
    }
}
