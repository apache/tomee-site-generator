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

import org.apache.openejb.loader.IO;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class ContributorDataTest {

    @Test
    public void read() throws Exception {

        final URL resource = this.getClass().getClassLoader().getResource("contributor-data.json");
        final String json = IO.slurp(resource);
        final Jsonb jsonb = JsonbBuilder.create();
        final ContributorData[] data = jsonb.fromJson(json, ContributorData[].class);

        assertEquals(12, data.length);
        assertEquals("djencks", data[3].getAuthor().getLogin());
        assertEquals(36, data[3].getTotal());
        assertEquals(19581, data[3].getWeeks().get(135).getA());
        assertEquals(9998, data[3].getWeeks().get(135).getD());
        assertEquals(6, data[3].getWeeks().get(135).getC());
    }

    @Test
    public void getStats() throws Exception {

        final URL resource = this.getClass().getClassLoader().getResource("contributor-data.json");
        final String json = IO.slurp(resource);
        final Jsonb jsonb = JsonbBuilder.create();
        final ContributorData[] data = jsonb.fromJson(json, ContributorData[].class);

        assertEquals(12, data.length);
        assertEquals("djencks", data[3].getAuthor().getLogin());

        final Stats stats = data[3].getStats();
        assertEquals(36, stats.getCommits());
        assertEquals(35243, stats.getLinesAdded());
        assertEquals(25020, stats.getLinesRemoved());
    }

}
