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
package org.apache.tomee.website.contributors;

import org.apache.johnzon.jaxrs.JohnzonProvider;
import org.apache.openejb.loader.IO;
import org.tomitribe.swizzle.stream.StreamBuilder;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Github {

    private static final Logger log = Logger.getLogger(Github.class.getName());
    private final Client client = ClientBuilder.newClient().register(new JohnzonProvider<>());

    public List<Contributor> getContributors() {
        final List<URI> repositoryURIs = getRepositoryURIs();
        final List<Contributor> list = repositoryURIs.stream()
                .map(this::getContributorsForRepository)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return Contributor.unique(list);
    }

    /**
     * List all the repositories for Apache TomEE
     */
    public List<URI> getRepositoryURIs() {
        try {
            final WebTarget github = client.target("https://github.com");
            final String content = github.path("/apache").queryParam("q", "tomee").request().get(String.class);
            final List<String> links = new ArrayList<String>();
            StreamBuilder.create(IO.read(content))
                    .watch("href=\"/apache/tomee", "\"", links::add)
                    .run();

            return links.stream()
                    .filter(s -> !s.contains("/"))
                    .filter(s -> !s.equals("-site-pub"))
                    .filter(s -> !s.equals("-site"))
                    .distinct()
                    .map(s -> "https://github.com/apache/tomee" + s)
                    .map(URI::create)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to list TomEE repositories", e);
        }
    }

    /**
     * Get the contributor-data json for the specified repository
     */
    private List<Contributor> getContributorsForRepository(final URI repositoryUri) {
        final Response response = client.target(repositoryUri.toASCIIString())
                .path("graphs/contributors-data")
                .request()
                .header("referer", repositoryUri.toASCIIString() + "/graphs/contributors")
                .header("authority", "github.com")
                .header("pragma", "no-cache")
                .header("cache-control", "no-cache")
                .header("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"90\", \"Google Chrome\";v=\"90\"")
                .header("accept", "application/json")
                .header("sec-ch-ua-mobile", "?0")
                .header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36")
                .header("sec-fetch-site", "same-origin")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-dest", "empty")
                .header("accept-language", "en-US,en;q=0.9,es;q=0.8")
                .get();

        if (response.getStatus() != 200) {
            log.severe("Unexpected status from " + repositoryUri + ": " + response.getStatus());
            return Collections.EMPTY_LIST;
        }

        if (!response.getHeaderString("content-type").startsWith("application/json")) {
            log.severe("Unexpected content-type from " + repositoryUri + ": " + response.getHeaderString("content-type"));
            return Collections.EMPTY_LIST;
        }
        
        final String json;
        try {
            json = IO.slurp((InputStream) response.getEntity());
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to read response from " + repositoryUri, e);
        }
        
        final ContributorData[] data;
        try {
            final Jsonb jsonb = JsonbBuilder.create();
            data = jsonb.fromJson(json, ContributorData[].class);
        } catch (final Exception e) {
            throw new IllegalStateException("Unable to unmarshal response from " + repositoryUri + "\n\n" + json, e);
        }
        return Stream.of(data)
                .map(ContributorData::asContributor)
                .collect(Collectors.toList());
    }

}
