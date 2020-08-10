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

import org.junit.Test;

import static org.apache.tomee.website.Scenario.scenario;
import static org.junit.Assert.assertEquals;

public class ApisUsedTest {


    /**
     * If an "APIs Used" section exists, but uses slightly different
     * formatting, we should normalize it so it is consistent with
     * all the other "APIs Used" sections
     */
    @Test
    public void abnormalHeading() throws Exception {
        final Scenario scenario = scenario(ApisUsedTest.class, "abnormalHeading");

        final String input = scenario.get("before.adoc");

        final String actual = ApisUsed.insertHref(input, "../../../jakartaee-9.0/javadoc/jakarta/persistence/EntityManager.html", "jakarta.persistence.EntityManager");

        assertEquals(scenario.get("after.adoc"), actual);
    }

    /**
     * If an "APIs Used" section exists in the middle of a document
     * and therefore has sections after it, we should update the
     * section where it lives and not move it or mistakenly append
     * another "APIs Used" section.
     */
    @Test
    public void hasOtherSections() throws Exception {
        final Scenario scenario = scenario(ApisUsedTest.class, "hasOtherSections");

        final String input = scenario.get("before.adoc");

        final String actual = ApisUsed.insertHref(input, "../../../jakartaee-9.0/javadoc/jakarta/persistence/EntityManager.html", "jakarta.persistence.EntityManager");

        assertEquals(scenario.get("after.adoc"), actual);
    }

    /**
     * Very basic happy-path test to add an "APIs Used" section
     * to a document that doesn't yet have one.
     */
    @Test
    public void insertHref() throws Exception {
        final Scenario scenario = scenario(ApisUsedTest.class, "insertHref");

        final String input = scenario.get("before.adoc");

        final String actual = ApisUsed.insertHref(input, "../../../jakartaee-9.0/javadoc/jakarta/persistence/Entity.html", "jakarta.persistence.Entity");

        assertEquals(scenario.get("after.adoc"), actual);
    }

    /**
     * We should be able to insert links into the "APIs Used"
     * section in several different calls.  The first call
     * will add the section and the subsequent calls will
     * update the now-existing section.
     */
    @Test
    public void multipleInserts() throws Exception {
        final Scenario scenario = scenario(ApisUsedTest.class, "multipleInserts");

        final String input = scenario.get("before.adoc");

        final String after1 = ApisUsed.insertHref(input,
                "../../../jakartaee-9.0/javadoc/jakarta/persistence/Entity.html",
                "jakarta.persistence.Entity");
        assertEquals(scenario.get("after1.adoc"), after1);

        final String after2 = ApisUsed.insertHref(after1,
                "../../../jakartaee-9.0/javadoc/jakarta/persistence/EntityManager.html",
                "jakarta.persistence.EntityManager");
        assertEquals(scenario.get("after2.adoc"), after2);

        final String after3 = ApisUsed.insertHref(after2,
                "../../../jakartaee-9.0/javadoc/jakarta/persistence/Id.html",
                "jakarta.persistence.Id");
        assertEquals(scenario.get("after3.adoc"), after3);
    }

    /**
     * If an API is already listed in the "APIs Used" section we should
     * not add it again.
     */
    @Test
    public void noDuplicates() throws Exception {
        final Scenario scenario = scenario(ApisUsedTest.class, "noDuplicates");

        final String input = scenario.get("before.adoc");

        final String after1 = ApisUsed.insertHref(input, "../../../jakartaee-9.0/javadoc/jakarta/persistence/Entity.html", "jakarta.persistence.Entity");

        assertEquals(scenario.get("after.adoc"), after1);

        final String after2 = ApisUsed.insertHref(after1, "../../../jakartaee-8.0/javadoc/jakarta/persistence/Entity.html", "jakarta.persistence.Entity");

        assertEquals(scenario.get("after.adoc"), after2);
    }

    @Test
    public void normalize() throws Exception {
        assertEquals("\n== APIs Used\n- link", ApisUsed.normalize("\n====  ApIs usEd\n- link"));
    }

}
