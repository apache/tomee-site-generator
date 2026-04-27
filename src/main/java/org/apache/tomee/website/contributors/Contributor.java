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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Contributor {
    private String id;
    private boolean committer;
    private String name;
    private String github;
    private String avatar;
    private Stats stats;

    public Contributor() {
    }

    public Contributor(final String id, final boolean committer, final String name,
                       final String github, final String avatar, final Stats stats) {
        this.id = id;
        this.committer = committer;
        this.name = name;
        this.github = github;
        this.avatar = avatar;
        this.stats = stats;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public boolean isCommitter() {
        return committer;
    }

    public void setCommitter(final boolean committer) {
        this.committer = committer;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(final String github) {
        this.github = github;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(final String avatar) {
        this.avatar = avatar;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(final Stats stats) {
        this.stats = stats;
    }

    public Contributor add(final Contributor that) {
        return new Contributor(id, committer, name, github, avatar, stats.add(that.stats));
    }

    /**
     * Take the specified list of Contributors, which may have duplicates, and
     * combine each duplicate down to one Contributor instance while keeping a
     * total of all their stats.
     */
    public static List<Contributor> unique(final List<Contributor> listWithDuplicates) {
        final Map<String, List<Contributor>> map = listWithDuplicates.stream()
                .collect(Collectors.groupingBy(Contributor::getName));

        return map.values().stream()
                .map(Contributor::reduce)
                .collect(Collectors.toList());
    }

    private static Contributor reduce(final List<Contributor> instances) {
        return instances.stream()
                .reduce(Contributor::add)
                .orElseThrow(IllegalStateException::new);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private boolean committer;
        private String name;
        private String github;
        private String avatar;
        private Stats stats;

        public Builder id(final String id) { this.id = id; return this; }
        public Builder committer(final boolean committer) { this.committer = committer; return this; }
        public Builder name(final String name) { this.name = name; return this; }
        public Builder github(final String github) { this.github = github; return this; }
        public Builder avatar(final String avatar) { this.avatar = avatar; return this; }
        public Builder stats(final Stats stats) { this.stats = stats; return this; }

        public Contributor build() {
            return new Contributor(id, committer, name, github, avatar, stats);
        }
    }
}
