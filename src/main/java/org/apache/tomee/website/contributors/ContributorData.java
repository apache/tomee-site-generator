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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContributorData {

    @JsonbProperty("total")
    private int total;

    @JsonbProperty("author")
    private Author author;

    @JsonbProperty("weeks")
    private List<Week> weeks;

    public Stats getStats() {
        return weeks.stream()
                .map(week -> new Stats(week.c, week.a, week.d))
                .reduce(Stats::add)
                .orElse(new Stats(0, 0, 0));
    }

    /**
     * Convert the ContributorData to a Contributor instance
     */
    public Contributor asContributor() {
        final ContributorData.Author author = this.getAuthor();

        return Contributor.builder()
                .id(author.getId() + "")
                .name(author.getLogin())
                .github("https://github.com/" + author.getLogin())
                .avatar("https://avatars.githubusercontent.com/u/" + author.getId() + "?v=4")
                .stats(this.getStats())
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Author {
        @JsonbProperty("id")
        private int id;

        @JsonbProperty("login")
        private String login;

        @JsonbProperty("avatar")
        private String avatar;

        @JsonbProperty("path")
        private String path;

        @JsonbProperty("hovercard_url")
        private String hovercardUrl;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Week {
        @JsonbProperty("w")
        private long w;

        @JsonbProperty("a")
        private int a;

        @JsonbProperty("d")
        private int d;

        @JsonbProperty("c")
        private int c;
    }
}
