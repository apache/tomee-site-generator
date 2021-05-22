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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ContributorTest {

    @Test
    public void unique() throws IOException {
        final URL resource = this.getClass().getClassLoader().getResource("contributor-data.json");
        final String json = IO.slurp(resource);
        final Jsonb jsonb = JsonbBuilder.create();
        final ContributorData[] data = jsonb.fromJson(json, ContributorData[].class);

        final List<ContributorData> list = new ArrayList<>(Arrays.asList(data));
        list.add(data[3]); // djencks
        list.add(data[5]); // rzo1
        list.add(data[5]); // rzo1
        list.add(data[5]); // rzo1
        list.add(data[10]); // dblevins

        final List<Contributor> duplicates = list.stream()
                .map(ContributorData::asContributor)
                .collect(Collectors.toList());

        final List<Contributor> unique = Contributor.unique(duplicates);
        final Map<String, Contributor> map = unique.stream().collect(Collectors.toMap(Contributor::getName, Function.identity()));
        
        assertEquals(17, duplicates.size());
        assertEquals(12, unique.size());
        assertEquals(12, map.size());

        final Contributor djencks = map.get("djencks");
        assertEquals("djencks", djencks.getName());
        assertEquals(72, djencks.getStats().getCommits());
        assertEquals(70486, djencks.getStats().getLinesAdded());
        assertEquals(50040, djencks.getStats().getLinesRemoved());

        final Contributor rzo1 = map.get("rzo1");
        assertEquals("rzo1", rzo1.getName());
        assertEquals(48, rzo1.getStats().getCommits());
        assertEquals(5952, rzo1.getStats().getLinesAdded());
        assertEquals(4164, rzo1.getStats().getLinesRemoved());

        final Contributor dblevins = map.get("dblevins");
        assertEquals("dblevins", dblevins.getName());
        assertEquals(158, dblevins.getStats().getCommits());
        assertEquals(88898, dblevins.getStats().getLinesAdded());
        assertEquals(139256, dblevins.getStats().getLinesRemoved());

        final Contributor cesarhernandezgt = map.get("cesarhernandezgt");
        assertEquals("cesarhernandezgt", cesarhernandezgt.getName());
        assertEquals(14, cesarhernandezgt.getStats().getCommits());
        assertEquals(1073, cesarhernandezgt.getStats().getLinesAdded());
        assertEquals(356, cesarhernandezgt.getStats().getLinesRemoved());
    }
}
