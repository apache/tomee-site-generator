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
public class ExampleLinksTest {

    /**
     * Test we can insert an @see link into some code that already has some javadoc
     */
    @Test
    public void insertHref() throws IOException {
        final Scenario scenario = scenario(ExampleLinksTest.class, "insertHref");

        final String input = scenario.get("before.java");

        final String actual = ExampleLinks.insertHref(input, "http://example.org/orange.html", "Orange Example", "en");

        assertEquals(scenario.get("after.java"), actual);
    }

    /**
     * Test we can insert an @see link into some code has no javadoc yet
     */
    @Test
    public void noJavadoc() throws IOException {
        final Scenario scenario = scenario(ExampleLinksTest.class, "noJavadoc");

        final String input = scenario.get("before.java");

        final String actual = ExampleLinks.insertHref(input, "http://example.org/orange.html", "Orange Example", "en");

        assertEquals(scenario.get("after.java"), actual);
    }

    /**
     * Test we can insert several @see links into the same javadoc
     */
    @Test
    public void multipleInserts() throws IOException {
        final Scenario scenario = scenario(ExampleLinksTest.class, "multipleInserts");

        final String input = scenario.get("before.java");

        final String after1 = ExampleLinks.insertHref(input, "http://example.org/orange.html", "Orange Example", "en");
        assertEquals(scenario.get("after1.java"), after1);

        final String after2 = ExampleLinks.insertHref(after1, "http://example.org/red.html", "Red Sample", "en");
        assertEquals(scenario.get("after2.java"), after2);

        final String after3 = ExampleLinks.insertHref(after2, "http://example.org/yellow.html", "yellow", "en");
        assertEquals(scenario.get("after3.java"), after3);
    }

    /**
     * Test we can insert several @see links into the same javadoc
     */
    @Test
    public void multipleLanguages() throws IOException {
        final Scenario scenario = scenario(ExampleLinksTest.class, "multipleLanguages");

        final String input = scenario.get("before.java");

        final String after1 = ExampleLinks.insertHref(input, "http://example.org/orange.html", "Orange Example", "en");
        assertEquals(scenario.get("after1.java"), after1);

        final String after2 = ExampleLinks.insertHref(after1, "http://example.org/es/orange.html", "Orange Example", "es");
        assertEquals(scenario.get("after2.java"), after2);

        final String after3 = ExampleLinks.insertHref(after2, "http://example.org/pt/orange.html", "Orange Example", "pt");
        assertEquals(scenario.get("after3.java"), after3);
    }

    /**
     * Test we can insert several @see links into the same javadoc
     */
    @Test
    public void noDuplicates() throws IOException {
        final Scenario scenario = scenario(ExampleLinksTest.class, "noDuplicates");

        final String input = scenario.get("before.java");

        final String after1 = ExampleLinks.insertHref(input, "http://example.org/orange.html", "Orange Example", "en");
        assertEquals(scenario.get("after.java"), after1);

        // The second insert should be ignored as it has the same title "Orange Example"
        final String after2 = ExampleLinks.insertHref(after1, "http://example.org/foo.html", "Orange Example", "en");
        assertEquals(scenario.get("after.java"), after2);
    }

    /**
     * Test we can insert several @see links into the same javadoc
     */
    @Test
    public void hasAnnotations() throws IOException {
        final Scenario scenario = scenario(ExampleLinksTest.class, "hasAnnotations");

        final String input = scenario.get("before.java");

        final String actual = ExampleLinks.insertHref(input, "http://example.org/orange.html", "Orange Example", "en");
        assertEquals(scenario.get("after.java"), actual);
    }

}
