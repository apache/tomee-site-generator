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

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class ContributorData {

    @JsonbProperty("total")
    private int total;

    @JsonbProperty("author")
    private Author author;

    @JsonbProperty("weeks")
    private List<Week> weeks;

    public ContributorData() {
    }

    public int getTotal() { return total; }
    public void setTotal(final int total) { this.total = total; }

    public Author getAuthor() { return author; }
    public void setAuthor(final Author author) { this.author = author; }

    public List<Week> getWeeks() { return weeks; }
    public void setWeeks(final List<Week> weeks) { this.weeks = weeks; }

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

        public Author() {
        }

        public int getId() { return id; }
        public void setId(final int id) { this.id = id; }

        public String getLogin() { return login; }
        public void setLogin(final String login) { this.login = login; }

        public String getAvatar() { return avatar; }
        public void setAvatar(final String avatar) { this.avatar = avatar; }

        public String getPath() { return path; }
        public void setPath(final String path) { this.path = path; }

        public String getHovercardUrl() { return hovercardUrl; }
        public void setHovercardUrl(final String hovercardUrl) { this.hovercardUrl = hovercardUrl; }
    }

    public static class Week {
        @JsonbProperty("w")
        private long w;

        @JsonbProperty("a")
        private int a;

        @JsonbProperty("d")
        private int d;

        @JsonbProperty("c")
        private int c;

        public Week() {
        }

        public long getW() { return w; }
        public void setW(final long w) { this.w = w; }

        public int getA() { return a; }
        public void setA(final int a) { this.a = a; }

        public int getD() { return d; }
        public void setD(final int d) { this.d = d; }

        public int getC() { return c; }
        public void setC(final int c) { this.c = c; }
    }
}
