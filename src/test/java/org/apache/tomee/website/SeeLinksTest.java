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
package org.apache.tomee.website;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.apache.tomee.website.Scenario.scenario;
import static org.junit.Assert.assertEquals;

@Ignore
public class SeeLinksTest {

    /**
     * Test we can insert an @see link into some code that already has some javadoc
     */
    @Test
    public void insertHref() throws IOException {
        final Scenario scenario = scenario(SeeLinksTest.class, "insertHref");

        final String input = scenario.get("before.java");

        final String actual = SeeLinks.insertHref(input, "http://example.org/orange.html", "Orange Example");

        assertEquals(scenario.get("after.java"), actual);
    }

    /**
     * Test we can insert an @see link into some code has no javadoc yet
     */
    @Test
    public void noJavadoc() throws IOException {
        final Scenario scenario = scenario(SeeLinksTest.class, "noJavadoc");

        final String input = scenario.get("before.java");

        final String actual = SeeLinks.insertHref(input, "http://example.org/orange.html", "Orange Example");

        assertEquals(scenario.get("after.java"), actual);
    }

    /**
     * Test we can insert several @see links into the same javadoc
     */
    @Test
    public void multipleInserts() throws IOException {
        final Scenario scenario = scenario(SeeLinksTest.class, "multipleInserts");

        final String input = scenario.get("before.java");

        final String after1 = SeeLinks.insertHref(input, "http://example.org/orange.html", "Orange Example");
        assertEquals(scenario.get("after1.java"), after1);

        final String after2 = SeeLinks.insertHref(after1, "http://example.org/red.html", "Red Sample");
        assertEquals(scenario.get("after2.java"), after2);

        final String after3 = SeeLinks.insertHref(after2, "http://example.org/yellow.html", "yellow");
        assertEquals(scenario.get("after3.java"), after3);
    }

    /**
     * Test we can insert several @see links into the same javadoc
     */
    @Test
    public void hasAnnotations() throws IOException {
        final Scenario scenario = scenario(SeeLinksTest.class, "hasAnnotations");

        final String input = scenario.get("before.java");

        final String actual = SeeLinks.insertHref(input, "http://example.org/orange.html", "Orange Example");
        assertEquals(scenario.get("after.java"), actual);
    }

}
