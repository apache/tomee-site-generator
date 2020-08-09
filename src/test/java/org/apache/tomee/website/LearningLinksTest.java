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
import org.tomitribe.util.Archive;
import org.tomitribe.util.Files;
import org.tomitribe.util.Join;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class LearningLinksTest {

    @Test
    public void pathToContentRoot() {
        final File file = new File("tomee-site-generator/target/jbake/content/tomee-9.0/pt/examples/injection-of-entitymanager.adoc");
        final String basePath = LearningLinks.pathToContentRoot(file);
        assertEquals("../../../", basePath);
    }

    @Test
    public void pathFromContentRoot() {
        final File file = new File("tomee-site-generator/target/jbake/content/tomee-9.0/pt/examples/injection-of-entitymanager.adoc");
        final String basePath = LearningLinks.pathFromContentRoot(file);
        assertEquals("tomee-9.0/pt/examples/injection-of-entitymanager.adoc", basePath);
    }

    @Test
    public void sortBySize() throws IOException {
        final File dir = Archive.archive()
                .add("target/jbake/content/tomee-9.0/examples/b.adoc", new byte[141])
                .add("target/jbake/content/tomee-9.0/examples/a.adoc", new byte[60])
                .add("target/jbake/content/tomee-9.0/examples/c.adoc", new byte[10])
                .add("target/jbake/content/tomee-8.0/examples/b.adoc", new byte[123])
                .add("target/jbake/content/tomee-8.0/examples/a.adoc", new byte[61])
                .add("target/jbake/content/tomee-8.0/examples/c.adoc", new byte[4])
                .add("target/jbake/content/latest/examples/b.adoc", new byte[12])
                .add("target/jbake/content/latest/examples/a.adoc", new byte[5])
                .add("target/jbake/content/latest/examples/c.adoc", new byte[90])
                .toDir();

        final List<Example> examples = Files.collect(dir).stream()
                .filter(file -> file.getName().endsWith(".adoc"))
                .map(Example::from)
                .peek(example -> example.setDestReadme(example.getSrcReadme()))
                .collect(Collectors.toList());

        Collections.shuffle(examples);

        examples.sort(LearningLinks::compareBySize);

        final List<String> collect = examples.stream()
                .map(Example::getDestReadme)
                .map(LearningLinks::pathFromContentRoot)
                .collect(Collectors.toList());

        assertEquals("tomee-9.0/examples/b.adoc\n" +
                "tomee-8.0/examples/b.adoc\n" +
                "latest/examples/c.adoc\n" +
                "tomee-8.0/examples/a.adoc\n" +
                "tomee-9.0/examples/a.adoc\n" +
                "latest/examples/b.adoc\n" +
                "tomee-9.0/examples/c.adoc\n" +
                "latest/examples/a.adoc\n" +
                "tomee-8.0/examples/c.adoc", Join.join("\n", collect));
    }

    @Test
    public void sortByVersion() throws IOException {
        final File dir = Archive.archive()
                .add("target/jbake/content/tomee-9.0/examples/b.adoc", new byte[141])
                .add("target/jbake/content/tomee-9.0/examples/a.adoc", new byte[60])
                .add("target/jbake/content/tomee-9.0/examples/c.adoc", new byte[10])
                .add("target/jbake/content/tomee-8.0/examples/b.adoc", new byte[123])
                .add("target/jbake/content/tomee-8.0/examples/a.adoc", new byte[61])
                .add("target/jbake/content/tomee-8.0/examples/c.adoc", new byte[4])
                .add("target/jbake/content/latest/examples/b.adoc", new byte[12])
                .add("target/jbake/content/latest/examples/a.adoc", new byte[5])
                .add("target/jbake/content/latest/examples/c.adoc", new byte[90])
                .toDir();

        final List<Example> examples = Files.collect(dir).stream()
                .filter(file -> file.getName().endsWith(".adoc"))
                .map(Example::from)
                .peek(example -> example.setDestReadme(example.getSrcReadme()))
                .collect(Collectors.toList());

        Collections.shuffle(examples);

        examples.sort(LearningLinks::compareBySize); // for stability
        examples.sort(LearningLinks::compareByVersion);

        final List<String> collect = examples.stream()
                .map(Example::getDestReadme)
                .map(LearningLinks::pathFromContentRoot)
                .collect(Collectors.toList());

        assertEquals("" +
                "tomee-9.0/examples/c.adoc\n" +
                "tomee-9.0/examples/b.adoc\n" +
                "tomee-9.0/examples/a.adoc\n" +
                "tomee-8.0/examples/c.adoc\n" +
                "tomee-8.0/examples/b.adoc\n" +
                "tomee-8.0/examples/a.adoc\n" +
                "latest/examples/c.adoc\n" +
                "latest/examples/b.adoc\n" +
                "latest/examples/a.adoc", Join.join("\n", collect));
    }

    @Test
    public void sortByLatest() throws IOException {
        final File dir = Archive.archive()
                .add("target/jbake/content/master/examples/b.adoc", new byte[111])
                .add("target/jbake/content/master/examples/a.adoc", new byte[70])
                .add("target/jbake/content/master/examples/c.adoc", new byte[20])
                .add("target/jbake/content/tomee-9.0/examples/b.adoc", new byte[141])
                .add("target/jbake/content/tomee-9.0/examples/a.adoc", new byte[60])
                .add("target/jbake/content/tomee-9.0/examples/c.adoc", new byte[10])
                .add("target/jbake/content/tomee-8.0/examples/b.adoc", new byte[123])
                .add("target/jbake/content/tomee-8.0/examples/a.adoc", new byte[61])
                .add("target/jbake/content/tomee-8.0/examples/c.adoc", new byte[4])
                .add("target/jbake/content/latest/examples/b.adoc", new byte[12])
                .add("target/jbake/content/latest/examples/a.adoc", new byte[5])
                .add("target/jbake/content/latest/examples/c.adoc", new byte[90])
                .toDir();

        final List<Example> examples = Files.collect(dir).stream()
                .filter(file -> file.getName().endsWith(".adoc"))
                .map(Example::from)
                .peek(example -> example.setDestReadme(example.getSrcReadme()))
                .collect(Collectors.toList());

        Collections.shuffle(examples);

        examples.sort(LearningLinks::compareBySize);
        examples.sort(LearningLinks::compareByLatest);
        assertEquals("" +
                "latest/examples/c.adoc\n" +
                "latest/examples/b.adoc\n" +
                "latest/examples/a.adoc\n" +
                "tomee-9.0/examples/b.adoc\n" +
                "tomee-8.0/examples/b.adoc\n" +
                "tomee-8.0/examples/a.adoc\n" +
                "tomee-9.0/examples/a.adoc\n" +
                "tomee-9.0/examples/c.adoc\n" +
                "tomee-8.0/examples/c.adoc\n" +
                "master/examples/b.adoc\n" +
                "master/examples/a.adoc\n" +
                "master/examples/c.adoc", toPaths(examples));
    }

    @Test
    public void sort() throws IOException {
        final File dir = Archive.archive()
                .add("target/jbake/content/master/examples/b.adoc", new byte[111])
                .add("target/jbake/content/master/examples/a.adoc", new byte[70])
                .add("target/jbake/content/master/examples/c.adoc", new byte[20])
                .add("target/jbake/content/tomee-9.0/examples/b.adoc", new byte[141])
                .add("target/jbake/content/tomee-9.0/examples/a.adoc", new byte[60])
                .add("target/jbake/content/tomee-9.0/examples/c.adoc", new byte[10])
                .add("target/jbake/content/tomee-8.0/examples/b.adoc", new byte[123])
                .add("target/jbake/content/tomee-8.0/examples/a.adoc", new byte[61])
                .add("target/jbake/content/tomee-8.0/examples/c.adoc", new byte[4])
                .add("target/jbake/content/latest/examples/b.adoc", new byte[12])
                .add("target/jbake/content/latest/examples/a.adoc", new byte[5])
                .add("target/jbake/content/latest/examples/c.adoc", new byte[90])
                .toDir();

        final List<Example> examples = Files.collect(dir).stream()
                .filter(file -> file.getName().endsWith(".adoc"))
                .map(Example::from)
                .peek(example -> example.setDestReadme(example.getSrcReadme()))
                .collect(Collectors.toList());

        Collections.shuffle(examples);

        final List<Example> sorted = LearningLinks.sort(examples);

        assertEquals("" +
                "latest/examples/c.adoc\n" +
                "latest/examples/b.adoc\n" +
                "latest/examples/a.adoc\n" +
                "tomee-9.0/examples/c.adoc\n" +
                "tomee-9.0/examples/b.adoc\n" +
                "tomee-9.0/examples/a.adoc\n" +
                "tomee-8.0/examples/c.adoc\n" +
                "tomee-8.0/examples/b.adoc\n" +
                "tomee-8.0/examples/a.adoc\n" +
                "master/examples/c.adoc\n" +
                "master/examples/b.adoc\n" +
                "master/examples/a.adoc", toPaths(sorted));
    }

    private static String toPaths(final List<Example> examples) {
        final List<String> collect = examples.stream()
                .map(Example::getDestReadme)
                .map(LearningLinks::pathFromContentRoot)
                .collect(Collectors.toList());

        final String actual = Join.join("\n", collect);
        return actual;
    }

}
