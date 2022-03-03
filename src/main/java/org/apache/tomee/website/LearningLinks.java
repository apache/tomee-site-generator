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

import org.apache.openejb.loader.IO;
import org.tomitribe.tio.Dir;
import org.tomitribe.tio.Match;
import org.tomitribe.tio.lang.JvmLang;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is responsible for creating cross-links between Javadoc and Examples
 * as detailed in https://issues.apache.org/jira/browse/TOMEE-2346
 *
 * The name "LearningLinks" is perhaps a bit weak.  The spirit of the feature is to
 * make it easier for people to click back and forth between the javadoc and examples
 * which are two primary sources of learning a new API.
 *
 * Ideally someone who searches for an API on the internet find the javadoc and from
 * there can jump to any number of examples that use it.  Once on an example they can
 * jump into other points of Javadoc and continue their learning.
 */
public class LearningLinks {

    private final Examples examples;

    public LearningLinks(final Examples examples) {
        this.examples = examples;
    }

    /**
     * The primary method driving all code in this class. Prepare is called once per Source,
     * after example and javadoc preparation has been done.
     *
     * At this moment all of our examples have been copied to their final locations.  As well,
     * we have created several "buckets" of source code on which we will later run the Javadoc tool.
     *
     * Once we have those two lists we will link the javadocs into the right examples. We will also
     * link the applicable examples into each Javadoc as a @see tag.
     *
     * TODO: Note that we have several versions of the same examples, one per TomEE version. We may
     * want to only link the latest one so we don't appear to have duplicates.  There is a Source
     * called 'latest' which can be helpful in this regard, however, there will be situations where
     * the 'latest' Source no longer refers to a particular API anymore (e.g. javax once we switch
     * to jakarta) so we will want to consult all versions of the examples when we pick the most
     * current matching example.
     *
     * Actual running of the javadoc command does not happen here.
     *
     * @see Source for a deeper description that is very applicable here
     * @see Configuration for the full list of Sources that will be seen here
     */
    public void prepare(final Source source) {
        final Map<String, JavadocSource> sources = getJavadocSources(source.stream());

        final List<Example> examples = sort(this.examples.getExamples());

        for (final Example example : examples) {
            final List<String> apisUsed = getImports(example).stream()
                    .filter(sources::containsKey)
                    .collect(Collectors.toList());

            // If the example does not use any of the APIs from
            // this Source instance (e.g Jakarta EE, MicroProfile, etc)
            // then there is nothing to do.
            if (apisUsed.size() == 0) continue;

            // Add @see link to Javadoc
            for (final String api : apisUsed) {
                addSeeLink(sources.get(api), example);
            }

            // Don't add any links if the source format is markdown
            if (!example.getSrcReadme().getName().endsWith(".adoc")) continue;

            // Add APIs Used links to Example
            addApisUsed(example, apisUsed, sources, source);

        }
    }

    protected static List<Example> sort(final List<Example> list) {
        final List<Example> examples = new ArrayList<>(list);

        // Sort by size of example description (favor better documented examples)
        examples.sort(LearningLinks::compareBySize);

        // Sort by TomEE version
        examples.sort(LearningLinks::compareByVersion);

        // Sort "latest" to the top
        examples.sort(LearningLinks::compareByLatest);

        return examples;
    }

    protected static int compareByLatest(final Example a, final Example b) {
        return Integer.compare(rankLatest(b), rankLatest(a));
    }

    protected static int compareByVersion(final Example a, final Example b) {
        return pathFromContentRoot(b.getDestReadme()).compareTo(pathFromContentRoot(a.getDestReadme()));
    }

    protected static int compareBySize(final Example a, final Example b) {
        return Long.compare(b.getDestReadme().length(), a.getDestReadme().length());
    }

    private static int rankLatest(final Example example) {
        final String path = pathFromContentRoot(example.getDestReadme());
        if (path.startsWith("latest/")) return 1;
        if (path.startsWith("master/")) return -1;
        return 0;
    }

    private void addApisUsed(final Example example, final List<String> apisUsed, final Map<String, JavadocSource> sources, final Source source) {
        Collections.sort(apisUsed);

        String content = null;
        try {
            content = IO.slurp(example.getDestReadme());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        final String basePath = pathToContentRoot(example.getDestReadme());

        final List<JavadocSource> list = apisUsed.stream()
                .map(sources::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        for (JavadocSource javadocSource : list) {
            final String link = String.format("%s%s/javadoc/%s.html",
                    basePath,
                    source.getName(),
                    javadocSource.getClassName().replace(".", "/"));

            content = ApisUsed.insertHref(content, link, javadocSource.getClassName());
        }

        try {
            IO.copy(IO.read(content), example.getDestReadme());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static String pathToContentRoot(final File file) {
        final StringBuilder sb = new StringBuilder();

        File parent = file;
        while ((parent = parent.getParentFile()) != null && !parent.getName().equals("content")) {
            sb.append("../");
        }

        return sb.toString();
    }

    static String pathFromContentRoot(final File file) {
        final String absolutePath = file.getAbsolutePath().replace(File.separatorChar, '/');

        final String content = "/content/";
        final int indexOfContent = absolutePath.indexOf(content);

        if (indexOfContent == -1) {
            throw new IllegalStateException("Expected '/content/' section of path not found: " + absolutePath);
        }

        return absolutePath.substring(indexOfContent + content.length());
    }


    private void addSeeLink(final JavadocSource javadocSource, final Example example) {
        try {
            final String content = IO.slurp(javadocSource.getSourceFile());

            final String toContentRoot = pathToContentRoot(javadocSource.getSourceFile());
            final String fromContentRoot = pathFromContentRoot(example.getDestReadme())
                    .replace(".adoc", ".html")
                    .replace(".md", ".html");

            final String link = toContentRoot + fromContentRoot;

            final String name = example.getName();


            // Update the source contents to include an href link
            final String modified = ExampleLinks.insertHref(content, link, name, example.getLanguage());

            // Overwrite the source with the newly linked version
            IO.copy(IO.read(modified), javadocSource.getSourceFile());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to add link to java source: " + javadocSource.getSourceFile().getAbsolutePath(), e);
        }
    }

    /**
     * Walk over every file in the example directory and look for import statements.
     *
     * Collect each statement and return a unique list of the class names
     * referenced by each import statement.
     */
    private List<String> getImports(final Example example) {
        final Dir dir = Dir.from(example.getSrcReadme().getParentFile());

        // Unfiltered list of imported classes used in this example
        // This list will contain the class names themselves
        return dir.searchFiles()
                .flatMap(JvmLang.imports(dir))
                .map(Match::getMatch)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Return a map of JavadocSource instances that are applicable to this Source
     * and any related Source instances.  If the Javadocs::prepare runs after this
     * method is called an empty map will be returned.
     */
    private Map<String, JavadocSource> getJavadocSources(final Stream<Source> sources) {
        // The stream for the jakartaee-platform.git repo will contain ejb-api.git and all
        // related git repos.  Each repo will be a `Source` instance.

        // The Javadocs class will have set a JavadocSources in each Source instance that
        // we can use to get a list of java files that will be fed to the javadoc processor
        // at a later time.
        return sources.map(source -> source.getComponent(JavadocSources.class))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(JavadocSources::getSources)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toMap(JavadocSource::getClassName, Function.identity()));

    }


}
