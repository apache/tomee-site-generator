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

import org.tomitribe.swizzle.stream.StreamBuilder;
import org.tomitribe.tio.Dir;
import org.tomitribe.util.IO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

/**
 * This class simply looks to replace any instances of ${tomee.version} with the version from the source.
 */
public class TomEEVersionReplacement {

    public void prepare(final Source source) {
        final String version = source.getVersion();
        if (version == null || version.trim().length() == 0) return;

        final Dir dir = Dir.from(source.getDir());
        dir.searchFiles()
                .filter(File::isFile)
                .filter(this::isDocsOrExamples)
                .forEach(f -> this.replaceVersions(f, version));
    }

    private boolean isDocsOrExamples(final File file) {
        final String path = file.getAbsolutePath().replace(File.separatorChar, '/');
        return path.contains("/docs/") || path.contains("/examples/");
    }

    private void replaceVersions(final File file, final String version) {
        try {
            final InputStream inputStream = StreamBuilder.create(IO.read(file))
                    .replace("${TOMEE_VERSION}", version)
                    .get();

            final String content = IO.slurp(inputStream);
            IO.copy(IO.read(content), file);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to process file: " + file.getAbsolutePath(), e);
        }

    }
}
