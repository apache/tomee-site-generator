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
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tomee.website.contributors;

import lombok.Builder;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Contributors {
    private Contributors() {
        // no-op
    }

    public static Contributor loadStatic(final String input) {
        final String[] strings = input.split(" *\\| *");
        final String name = strings.length > 0 ? strings[0] : "";
        final String github = strings.length > 1 ? strings[1] : "";
        final String picture = strings.length > 2 ? strings[2] : "../img/noimg.png";
        return Contributor.builder()
                .name(name)
                .id(name)
                .avatar(picture)
                .github(github)
                .build();
    }

    public static Collection<Contributor> load(final String contributorsList) throws IOException { // used in page.gsp
        final List<Contributor> contributors = new ArrayList<>();
        final ExecutorService es = Executors.newFixedThreadPool(16);
        final String rawList = contributorsList.substring(contributorsList.indexOf("<pre>") + "<pre>".length(), contributorsList.indexOf("</pre>"));
        try (final BufferedReader reader = new BufferedReader(new StringReader(rawList))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                final String mail = line;
                es.submit(() -> {
                    Contributor contributor = null;
                    contributor = loadStatic(mail);
                    synchronized (contributors) {
                        contributors.add(contributor);
                    }
                });
            }
        }
        es.shutdown();
        try {
            es.awaitTermination(30, TimeUnit.MINUTES);
        } catch (final InterruptedException e) {
            Thread.interrupted();
            return Collections.emptyList();
        }
        Collections.sort(contributors, Comparator.comparing(o -> o.name));
        return contributors;
    }

    @Data
    @Builder
    public static class Contributor {
        private String id;
        private boolean committer;
        private String name;
        private String github;
        private String avatar;
    }
}