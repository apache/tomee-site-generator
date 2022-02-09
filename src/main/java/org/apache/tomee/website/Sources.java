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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * The goal of this class is to combine several git repositories and branches into
 * one canonical base that can be used with jbake.
 *
 * Repositories are cloned outside of target into a '/repos/' directory so that
 * repeated running can be supported.  Subsequent runs will do a git pull to check
 * for new content.
 *
 * The prepare step will copy relevant asciidoc and markdown sources into the
 * target/jbake/content/<name> directory.
 *
 */
public class Sources {

    /**
     * The directory where all final html files are placed
     * after generation.
     */
    private final File generated;


    /**
     * The directory where all JBake input is aggregated
     * to create one big source tree prior to generation.
     */
    private final File jbake;

    /**
     * The directory where we are keeping a cache of git
     * clones of the repos and branches that contribute
     * to the content of the site.
     */
    private final File repos;

    /**
     * The directory where we are keeping a cache of git
     * clones of the repos and branches that contribute
     * to the content of the site.
     */
    private final File mainSource;


    /**
     * The definition of each repo/branch that contributes
     * to the content of the site.
     */
    private final List<Source> sources = new ArrayList<>();

    public Sources(final File jbake, final File repos, final File mainSource, final File generated, final Source... sources) {
        this(jbake, repos, mainSource, generated, Arrays.asList(sources));
    }

    public Sources(final File jbake, final File repos, final File mainSource, final File generated, final List<Source> sources) {
        this.generated = generated;
        this.jbake = jbake;
        this.repos = repos;
        this.mainSource = mainSource;

        jbake.mkdirs();
        repos.mkdirs();

        this.sources.addAll(sources);
        for (final Source source : sources) {
            if (source.isLatest()) {
                this.sources.add(new Source(source.getScmUrl(), source.getBranch(), "latest", source.isLatest(), source.getVersion()));
                break;
            }
        }
    }

    public File getGenerated() {
        return generated;
    }

    public File getRepos() {
        return repos;
    }

    public File getMainSource() {
        return mainSource;
    }

    public File getJbake() {
        return jbake;
    }

    public List<Source> getSources() {
        return sources;
    }

    /**
     * This is the heart of the code to merge several documentation
     * sources into one tree.
     */
    public void prepare() {
        final Docs docs = new Docs(this);
        final Javadocs javadocs = new Javadocs(this);
        final Examples examples = new Examples(this);
        final VersionIndex versionIndex = new VersionIndex(this);
        final LearningLinks learningLinks = new LearningLinks(examples);
        final Jakartize jakartize = new Jakartize();
        final TomEEVersionReplacement versionReplacement = new TomEEVersionReplacement();

        try {
            IO.copyDirectory(mainSource, jbake);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Download the git repo associated with each Source
        // including any related Source/git repos
        sources.stream()
                .flatMap(Source::stream)
                .peek(source -> source.setDir(new File(repos, source.getName())))
                .forEach(Repos::download);

        // Run any initial steps to process each
        // source root (excluding the related repos)
        sources.stream()
                .peek(jakartize::prepare)
                .peek(versionReplacement::prepare)
                .peek(docs::prepare)
                .peek(javadocs::prepare)
                .peek(examples::prepare)
                .peek(versionIndex::prepare)
                .peek(learningLinks::prepare)
                .forEach(Sources::prepared);

        // Run any final tasks that have been registered
        // with any Source instance during the prepare phase
        sources.stream()
                .flatMap(Source::stream)
                .map(Source::getPerform)
                .flatMap(Collection::stream)
                .peek(runnable -> System.out.println("Running Hook " + runnable))
                .forEach(Runnable::run);

        VersionsIndex.prepare(this);
    }

    /**
     * This is the heart of the code to merge several documentation
     * sources into one tree.
     */
    public void post() {
        final Javadocs javadocs = new Javadocs(this);

        sources.stream()
                .peek(javadocs::prepare)
                .forEach(Sources::prepared);
        ;
    }

    public File getJbakeContentDestFor(final Source source, final String... parts) {
        final File content = new File(jbake, "content");
        File dir = new File(content, source.getName());

        for (final String part : parts) {
            dir = new File(dir, part);
        }

        if (!dir.exists()) {
            if (!dir.mkdirs()) throw new RuntimeException("Could not make directory: " + dir.getAbsolutePath());
        }
        return dir;
    }

    public File getGeneratedDestFor(final Source source, final String... parts) {
        File dir = new File(generated, source.getName());

        for (final String part : parts) {
            dir = new File(dir, part);
        }

        if (!dir.exists()) {
            if (!dir.mkdirs()) throw new RuntimeException("Could not make directory: " + dir.getAbsolutePath());
        }
        return dir;
    }

    private static void prepared(final Source source) {
        System.out.println("Prepared " + source);
    }

}
