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

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.RefNotAdvertisedException;

import java.io.File;
import java.io.IOException;

public class Repos {

    public static void download(final Source source) {

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

    private static void clone(final Source source) throws GitAPIException {
        System.out.println("  > git clone " + source.getScmUrl());

        Git.cloneRepository()
                .setURI(source.getScmUrl())
                .setDirectory(source.getDir())
                .setBranch(source.getBranch())
                .call()
                .close();
    }

    private static void pull(final Source source) throws IOException, GitAPIException {
        System.out.println("  > git pull");

        try (Git git = Git.open(source.getDir())) {
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
