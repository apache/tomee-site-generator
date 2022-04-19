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

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * A Source largely maps to a git repo we will checkout and use to build content
 * to publish to the website.
 *
 * Currently we have the following notable sources:
 *
 *  - latest
 *  - tomee-9.0
 *  - tomee-8.0
 *  - tomee-7.1
 *  - tomee-7.0
 *  - jakartaee-9.1
 *  - jakartaee-8.0
 *  - microprofile-5.0
 *  - microprofile-4.1
 *  - microprofile-2.0
 *
 * Each of these sources are given its own section on the website, for example:
 *
 *  - http://tomee.apache.org/jakartaee-8.0/javadoc/
 *  - http://tomee.apache.org/tomee-8.0/javadoc/
 *  - http://tomee.apache.org/tomee-8.0/examples/
 *
 * Sources may include content from other git repos and this act like an aggregator.
 * For example the microprofile-2.0 Source has 8 related Source trees, one for each
 * API in MicroProfile 2.0.  Each git reference includes the right tag information
 * so we are able to get the source (and therefore javadoc) for the exact version in
 * MicroProfile 2.0.
 *
 * We intentionally want the javadoc on our site to keep our links stable and not
 * depending on third-party sites that could change and break our links.  It also
 * allows us to enhance the javadoc to include links to and from our examples.
 * And finally many of these javadocs are not online anywhere, so it allows us to
 * bring some unique value to the world and increase our website traffic.
 */
public class Source {
    private final String name;
    private String label;
    private final String scmUrl;
    private final String branch;
    private final boolean latest;
    private final List<Source> related = new ArrayList<>();
    private File dir;
    private Filter javadocFilter = new Filter(".*/src/main/java/.*", ".*/(tck|itests|examples|archetype-resources|.*-example)/.*");
    private Pattern javadocPackages;
    private String version;

    /**
     * This allows us to attach a handful of finishing actions to
     * each Source that get executed after any 'prepare' methods
     * are called.  This will most likely consist of Lambdas and
     * is effectively a very simple way to split our logic into
     * two phases: prepare, perform.
     */
    private final List<Runnable> perform = new ArrayList<>();

    /**
     * The components map is a simple technique to allow us to attach
     * objects to the Source in various "prepare" phases.  It was initially
     * added to allow {@link Javadocs} to pass data to  {@link LearningLinks}
     * without making the two directly reference on each other.
     *
     * It also allows us to prepare some data that is specific to the Source
     * instance and not see by other Source instances.
     */
    private final Map<Class, Object> components = new HashMap();

    public Source(final String scmUrl, final String branch, final String name) {
        this(scmUrl, branch, name, false);
    }

    public Source(final String scmUrl, final String branch, final String name, final boolean latest) {
        this.scmUrl = scmUrl;
        this.branch = branch;
        this.name = name;
        this.latest = latest;
    }

    public Source(final String scmUrl, final String branch, final String name, final boolean latest, final String version) {
        this(scmUrl, branch, name, latest);
        this.version = version;
    }

    public List<Runnable> getPerform() {
        return perform;
    }

    public boolean addPerform(final Runnable runnable) {
        return perform.add(runnable);
    }

    public <T> T setComponent(Class<T> type, T value) {
        return (T) this.components.put(type, value);
    }

    public <T> Optional<T> getComponent(Class<T> type) {
        return Optional.ofNullable((T) this.components.get(type));
    }

    /**
     * Returns stream of this source and all related sources
     */
    public Stream<Source> stream() {
        return Stream.concat(Stream.of(this), this.getRelated().stream());
    }

    public boolean isLatest() {
        return latest;
    }

    public String getScmUrl() {
        return scmUrl;
    }

    public String getBranch() {
        return branch;
    }

    public String getName() {
        return name;
    }

    public File getDir() {
        return dir;
    }

    public void setDir(final File dir) {
        this.dir = dir;
    }

    public List<Source> getRelated() {
        return related;
    }

    public Source related(final Source... related) {
        Collections.addAll(this.related, related);
        return this;
    }

    public Filter getJavadocFilter() {
        return javadocFilter;
    }

    public Source filterJavadoc(final String include, final String exclude) {
        this.javadocFilter = new Filter(include, exclude);
        return this;
    }

    public Pattern getJavadocPackages() {
        return javadocPackages;
    }

    public Source javadoc(final String include) {
        this.javadocPackages = Pattern.compile(include);
        return this;
    }

    public String getLabel() {
        return this.label;
    }

    public Source label(final String label) {
        this.label = label;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public Source version(final String version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "Source{" +
                "name='" + name + '\'' +
                ", scmUrl='" + scmUrl + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }

    public static class Filter {
        private final Pattern include;
        private final Pattern exclude;

        public Filter(final String include, final String exclude) {
            this.include = Pattern.compile(include);
            this.exclude = Pattern.compile(exclude);
        }

        public Pattern getInclude() {
            return include;
        }

        public Pattern getExclude() {
            return exclude;
        }

        public boolean include(final File file) {
            return include.matcher(file.getAbsolutePath().replace(File.separatorChar, '/')).matches();
        }

        public boolean exclude(final File file) {
            return !exclude.matcher(file.getAbsolutePath().replace(File.separatorChar, '/')).matches();
        }
    }
}
