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
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.RefNotAdvertisedException;
import org.eclipse.jgit.internal.storage.file.FileRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
 * target/jbake/<name> directory.
 *
 */
public class Sources {

    private final File destination;
    private final File repos;
    private final File mainSource;
    private final List<Source> sources = new ArrayList<>();

    public Sources(final File destination, final File repos, final File mainSource, final Source... sources) {
        this.destination = destination;
        this.repos = repos;
        this.mainSource = mainSource;

        destination.mkdirs();
        repos.mkdirs();

        Collections.addAll(this.sources, sources);
    }

    public void prepare() {
        sources.stream()
                .peek(source -> source.setDir(new File(repos, source.getName())))
                .peek(Sources::download)
                .forEach(Sources::done);
        ;

        try {
            IO.copyDirectory(mainSource, destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void done(final Source source) {
        System.out.println("Done " + source);
    }

    public static void main(String[] args) throws Exception {
        final Sources sources = new Sources(new File("target/jbake"), new File("repos"), new File("src/main/jbake/content"),
                new Source("https://git-wip-us.apache.org/repos/asf/tomee.git", "master", "master"),
                new Source("https://git-wip-us.apache.org/repos/asf/tomee.git", "tomee-7.1.0", "tomee-7.1"),
                new Source("https://git-wip-us.apache.org/repos/asf/tomee.git", "tomee-7.0.5", "tomee-7.0"),
                new Source("https://git-wip-us.apache.org/repos/asf/tomee.git", "tomee-8.0.0-M1", "tomee-8.0", true)
        );

        sources.prepare();
    }

    private static void download(final Source source) {
        if (source.getDir().exists()) {

            try {
                pull(source);
            } catch (Exception e) {
                System.out.println("Pull Failed. " + source);
                e.printStackTrace();
            }

        } else {

            try {
                clone(source);
            } catch (Exception e) {
                System.out.println("Clone Failed. " + source);
                e.printStackTrace();
            }
        }
    }

    private static void clone(final Source source) throws Exception {
        System.out.println("  > git clone " + source.getScmUrl());

        jgit("clone", source.getScmUrl(), "-b", source.getBranch(), source.getDir().getAbsolutePath());
    }

    private static void pull(final Source source) throws IOException, GitAPIException {
        System.out.println("  > git pull");

        final Git git = new Git(new FileRepository(source.getDir().getAbsolutePath() + "/.git"));

        try {
            final PullResult call = git.pull()
                    .setRemote("origin")
                    .setRemoteBranchName(source.getBranch())
                    .call();

            if (call.getMergeResult() != null) {
                System.out.println(call.getMergeResult().getMergeStatus());
            }

            if (!call.isSuccessful()) {
                System.out.println("Pull Failed.  Will Remove and clone.");
                // delete
                deleteDirectory(source.getDir());
                // and try again
                download(source);
            }
        } catch (RefNotAdvertisedException ignore) {
            // we are on a tag
        }
    }

    public static void jgit(final String... args) throws Exception {
        org.eclipse.jgit.pgm.Main.main(args);
    }


    public static class Source {
        private final String name;
        private final String scmUrl;
        private final String branch;
        private final boolean latest;
        private File dir;

        public Source(final String scmUrl, final String branch, final String name) {
            this(scmUrl, branch, name, false);
        }

        public Source(final String scmUrl, final String branch, final String name, final boolean latest) {
            this.scmUrl = scmUrl;
            this.branch = branch;
            this.name = name;
            this.latest = latest;
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

        @Override
        public String toString() {
            return "Source{" +
                    "name='" + name + '\'' +
                    ", scmUrl='" + scmUrl + '\'' +
                    ", branch='" + branch + '\'' +
                    '}';
        }
    }

    private static boolean deleteDirectory(File dir) {
        File[] allContents = dir.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return dir.delete();
    }

}
