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

import org.apache.tomee.website.Source;
import org.tomitribe.swizzle.stream.StreamBuilder;
import org.tomitribe.tio.Dir;
import org.tomitribe.util.IO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

/**
 * The TomEE 9.0.0 releases are generated from TomEE 8.0.0 releases
 * via bytecode manipulation.  The purpose of this class is to do the
 * same transformation for the docs.
 *
 * The "tomee-9.0" source is effectively a clone of master (tomee-8.0).
 * To prepare this clone we will search for all docs/ and examples/
 * files and update any contents they may have to account for the
 * javax to jakarta rename.
 *
 * For the purposes of our documentation, it will be as if we have done
 * the rename in source.
 */
public class Jakartize {

    public void prepare(final Source source) {
        if (!source.getName().equals("tomee-9.0")) return;

        final Dir dir = Dir.from(source.getDir());
        dir.searchFiles()
                .filter(File::isFile)
                .filter(this::isDocsOrExamples)
                .forEach(this::jakartize);
    }

    private boolean isDocsOrExamples(final File file) {
        final String path = file.getAbsolutePath().replace(File.separatorChar, '/');
        return path.contains("/docs/") || path.contains("/examples/");
    }

    private void jakartize(final File file) {
        try {
            final InputStream inputStream = StreamBuilder.create(IO.read(file))
                    .replace("javax.activation", "jakarta.activation")
                    .replace("javax.annotation", "jakarta.annotation")
                    .replace("javax.batch", "jakarta.batch")
                    .replace("javax.decorator", "jakarta.decorator")
                    .replace("javax.ejb", "jakarta.ejb")
                    .replace("javax.el", "jakarta.el")
                    .replace("javax.enterprise", "jakarta.enterprise")
                    .replace("javax.faces", "jakarta.faces")
                    .replace("javax.inject", "jakarta.inject")
                    .replace("javax.interceptor", "jakarta.interceptor")
                    .replace("javax.jms", "jakarta.jms")
                    .replace("javax.json", "jakarta.json")
                    .replace("javax.json.bind", "jakarta.json.bind")
                    .replace("javax.jws", "jakarta.jws")
                    .replace("javax.mail", "jakarta.mail")
                    .replace("javax.persistence", "jakarta.persistence")
                    .replace("javax.resource", "jakarta.resource")
                    .replace("javax.security.auth.message", "jakarta.security.auth.message")
                    .replace("javax.security.enterprise", "jakarta.security.enterprise")
                    .replace("javax.security.jacc", "jakarta.security.jacc")
                    .replace("javax.servlet", "jakarta.servlet")
                    .replace("javax.transaction", "jakarta.transaction")
                    .replace("javax.validation", "jakarta.validation")
                    .replace("javax.websocket", "jakarta.websocket")
                    .replace("javax.ws.rs", "jakarta.ws.rs")
                    .replace("javax.xml.bind", "jakarta.xml.bind")
                    .replace("javax.xml.soap", "jakarta.xml.soap")
                    .replace("javax.xml.ws", "jakarta.xml.ws")
                    // There will be some false hits we need to fix. Some of the
                    // sub-packages are excluded from the rename.  Put them back.
                    .replace("jakarta.enterprise.deploy", "javax.enterprise.deploy")
                    .replace("jakarta.annotation.process", "javax.annotation.process")
                    .replace("jakarta.transaction.xa", "javax.transaction.xa")
                    .get();

            final String content = IO.slurp(inputStream);
            IO.copy(IO.read(content), file);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to process file: " + file.getAbsolutePath(), e);
        }

    }
}
