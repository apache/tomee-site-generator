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
package org.apache.tomee.website;

import lombok.Builder;
import lombok.Data;
import org.apache.johnzon.jaxrs.JohnzonProvider;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public class Contributors {
    private static final String GRAVATAR_BASE = "http://fr.gravatar.com/";

    private Contributors() {
        // no-op
    }

    public static Contributor singleLoad(final WebTarget target, final String input) throws IOException {
        try {
            return ofNullable(loadGravatar(target, input)).orElse(loadStatic(input));
        } catch (Exception e) {
            e.printStackTrace();
            return loadStatic(input);
        }
    }

    public static Contributor loadStatic(final String input) {
        final String[] strings = input.split(" *\\| *");
        final String mail = strings[0].replaceAll("\\*$", "");
        final boolean committer = strings[0].endsWith("*");
        final String name = strings.length > 1 ? strings[1] : mail.replaceAll("@.*", "");
        final String picture = strings.length > 2 ? strings[2] : "../img/noimg.png";
        return Contributor.builder()
                .name(name)
                .id(mail)
                .committer(committer)
                .gravatar(picture)
                .build();
    }

    public static Contributor loadGravatar(final WebTarget target, final String input) throws IOException {
        final String[] strings = input.split(" *\\| *");
        final boolean committer = strings[0].endsWith("*");
        final String mail = committer ? strings[0].substring(0, strings[0].length() - 1) : strings[0];
        final String hash = gravatarHash(mail);
        final Response gravatar = target.path(hash + ".json").request(MediaType.APPLICATION_JSON_TYPE).get();
        if (gravatar.getStatus() != HttpsURLConnection.HTTP_OK) {
            System.err.println("[ERROR] No gravatar for " + mail);
            return null;
        }
        final Contributor contributor = ofNullable(gravatar.readEntity(Gravatar.class).getEntry())
                .map(e -> e[0])
                .map(e -> Contributor.builder()
                        .id(e.getId())
                        .name(
                                ofNullable(e.getName())
                                        .map(n -> ofNullable(n.getFormatted()).orElse(ofNullable(n.getGivenName()).orElse("") + ofNullable(n.getFamilyName()).orElse("")))
                                        .orElseGet(() -> ofNullable(e.getDisplayName()).orElse(ofNullable(e.getPreferredUsername()).orElse(mail))))
                        .description(e.getAboutMe())
                        .link(
                                Stream.concat(
                                        ofNullable(e.getAccounts())
                                                .map(a -> Stream.of(a).map(l -> Link.builder().name(l.getShortname()).url(l.getUrl()).build()).collect(toList()))
                                                .orElse(emptyList()).stream(),
                                        ofNullable(e.getUrls())
                                                .map(a -> Stream.of(a).map(l -> Link.builder().name(l.getTitle()).url(l.getValue()).build()).collect(toList()))
                                                .orElse(emptyList()).stream())
                                        .collect(toList()))
                        .gravatar("http://www.gravatar.com/avatar/" + hash + "?s=140")
                        .build())
                .orElse(Contributor.builder().name(mail).id(mail).build());
        contributor.setCommitter(committer);
        ofNullable(contributor.getLink()).ifPresent(l -> Collections.sort(l, (o1, o2) -> o1.getName().compareTo(o2.getName())));
        return contributor;
    }

    public static Collection<Contributor> load(final String contributorsList) throws IOException { // used in page.gsp
        final WebTarget target = ClientBuilder.newClient().register(new JohnzonProvider()).target(GRAVATAR_BASE);
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
                    try {
                        contributor = singleLoad(target, mail);
                    } catch (final IOException e) {
                        throw new IllegalStateException(e);
                    }
                    if (contributor != null) {
                        synchronized (contributors) {
                            contributors.add(contributor);
                        }
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
        Collections.sort(contributors, (o1, o2) -> o1.name.compareTo(o2.id));
        return contributors;
    }

    private static String gravatarHash(final String mail) {
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] cp1252s = md.digest(mail.getBytes("CP1252"));
            final StringBuilder sb = new StringBuilder();
            for (final byte anArray : cp1252s) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (final NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    @Data
    @Builder
    public static class Link {
        private String name;
        private String url;
    }

    @Data
    @Builder
    public static class Contributor {
        private String id;
        private boolean committer;
        private String name;
        private String description;
        private String gravatar;
        private List<Link> link;
    }

    @Data
    public static class GravatarName {
        private String formatted;
        private String givenName;
        private String familyName;
    }

    @Data
    public static class GravatarUrl {
        private String value;
        private String title;
    }

    @Data
    public static class GravatarAccount {
        private String shortname;
        private String url;
    }

    @Data
    public static class Gravatar {
        private GravatarEntry[] entry;
    }

    @Data
    public static class GravatarEntry {
        private String id;
        private String hash;
        private String aboutMe;
        private String requestHash;
        private String profileUrl;
        private String preferredUsername;
        private String thumbnailUrl;
        private GravatarName name;
        private GravatarUrl[] urls;
        private GravatarAccount[] accounts;
        private String displayName;
    }
}
