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

import org.junit.Test;

import static org.junit.Assert.*;

public class StatsTest {

    @Test
    public void add() {
        final Stats a = new Stats(3, 5, 7);
        final Stats b = new Stats(11, 13, 17);

        final Stats c = a.add(b);
        assertEquals(14, c.getCommits());
        assertEquals(18, c.getLinesAdded());
        assertEquals(24, c.getLinesRemoved());
    }

    @Test
    public void max() {
        final Stats a = new Stats(157, 241, 37);
        final Stats b = new Stats(211, 113, 23);

        final Stats c = a.max(b);
        assertEquals(211, c.getCommits());
        assertEquals(241, c.getLinesAdded());
        assertEquals(37, c.getLinesRemoved());
    }

    @Test
    public void score() {
        final Stats max = new Stats(157, 241, 37);

        assertEquals(0, max.score(new Stats(0, 0, 0)));
        assertEquals(333333, max.score(new Stats(157, 0, 0)));
        assertEquals(666667, max.score(new Stats(157, 241, 0)));
        assertEquals(1000000, max.score(new Stats(157, 241, 37)));
        assertEquals(431752, max.score(new Stats(50, 40, 30)));
    }
}
