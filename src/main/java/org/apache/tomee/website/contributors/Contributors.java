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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Contributors {
    private static final Logger log = Logger.getLogger(Github.class.getName());

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
        /*
         * Try getting the full list from Github across all repositories
         */
        try {
            final List<Contributor> contributors = new Github().getContributors();
            return sort(contributors);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Unable to fetch contributors from github.com", e);
        }

        /*
         * Fallback to our cached list
         */
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
        Collections.sort(contributors, Comparator.comparing(Contributor::getName));
        return contributors;
    }

    public static List<Contributor> sort(final List<Contributor> list) {
        final Stats max = list.stream()
                .map(Contributor::getStats)
                .reduce(Stats::max)
                .orElse(new Stats(0, 0, 0));

        list.sort(Comparator.<Contributor, Integer>comparing(contributor -> max.score(contributor.getStats())).reversed());

        final boolean debug = false;
        if (debug){
            for (final Contributor contributor : list) {
                final Stats stats = contributor.getStats();
                System.out.printf("Contributor: %-7s %-20s %5s %7s %7s%n",
                        max.score(contributor.getStats()), contributor.getName(),
                        stats.getCommits(), stats.getLinesAdded(),
                        stats.getLinesRemoved());
            }
        }
        return list;
    }
}