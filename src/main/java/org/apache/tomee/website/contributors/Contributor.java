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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class Contributor {
    private String id;
    private boolean committer;
    private String name;
    private String github;
    private String avatar;
    private Stats stats;

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


}
