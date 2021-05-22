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

import lombok.Data;

/**
 * We add a contributor's commits, lines added and lines removed together
 * across all repos to create an overall score that is used to sort contributors.
 */
@Data
@lombok.Builder(builderClassName = "Builder", toBuilder = true)
public class Stats {
    private final int commits;
    private final int linesAdded;
    private final int linesRemoved;

    /**
     * Aggregate the stats from one git repo with the same
     * contributors stats on another repo
     */
    public Stats add(final Stats that) {
        return new Stats(
                this.commits + that.commits,
                this.linesAdded + that.linesAdded,
                this.linesRemoved + that.linesRemoved);
    }

    public Stats max(final Stats that) {
        return new Stats(
                Math.max(this.commits, that.commits),
                Math.max(this.linesAdded, that.linesAdded),
                Math.max(this.linesRemoved, that.linesRemoved));
    }

    /**
     * Assumes this instance of Stats holds the max (highest)
     * possible values for commits, lines added, and lines removed.
     *
     * The passed in stats will be evaluated against these numbers
     * a percentage (0-100) will be returned.  The return value is
     * intentionally an int so it can easily be used for sorting.
     */
    public int score(final Stats that) {
        final double commitsPercentage = that.commits / (double) this.commits;
        final double linesAddedPercentage = that.linesAdded / (double) this.linesAdded;
        final double linesRemovedPercentage = that.linesRemoved / (double) this.linesRemoved;
        final double average = (commitsPercentage + linesAddedPercentage + linesRemovedPercentage) / 3;

        return (int) Math.round(average * 1000000);
    }
}
