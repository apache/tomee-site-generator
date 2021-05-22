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

import lombok.Builder;
import lombok.Data;
import org.apache.johnzon.jaxrs.JohnzonProvider;
import org.apache.openejb.loader.IO;
import org.tomitribe.swizzle.stream.StreamBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private final Client client = ClientBuilder.newClient().register(new JohnzonProvider<>());

    public static void main(String[] args) throws IOException {
        new Main().main();
    }

    private void main() throws IOException {


        // Get Apache list of committers and PMC members
        // Get list of TomEE repos in the Apache org
        // Get Github contributors for each tomee repo
        // Override some specific images
        // Sort by commits

        final List<URI> hrefs = getRepositoryURIs();

    }

    /**
     * List all the repositories for Apache TomEE
     */
    private List<URI> getRepositoryURIs() throws IOException {
        final WebTarget github = client.target("https://github.com");
        final String content = github.path("/apache").queryParam("q", "tomee").request().get(String.class);
        final List<String> links = new ArrayList<String>();
        StreamBuilder.create(IO.read(content))
                .watch("href=\"/apache/tomee", "\"", links::add)
                .run();

        return links.stream()
                .filter(s -> !s.contains("/"))
                .filter(s -> !s.equals("-site-pub"))
                .distinct()
                .map(s -> "https://github.com/apache/tomee" + s)
                .map(URI::create)
                .collect(Collectors.toList());
    }

}
