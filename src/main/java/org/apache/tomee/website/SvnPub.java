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
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tomee.website;

import org.apache.commons.io.FileUtils;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.util.jna.SVNJNAUtil;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.ISVNStatusHandler;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNStatus;
import org.tmatesoft.svn.core.wc.SVNStatusType;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import org.tmatesoft.svn.core.wc2.SvnRevert;
import org.tmatesoft.svn.core.wc2.SvnTarget;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public class SvnPub {
    private SvnPub() {
        // no-op
    }

    public static void main(final String[] args) throws SVNException, IOException {
        SVNJNAUtil.setJNAEnabled(false); // svnkit and java 8 == easy sigsev on ubuntu (fixed when upgrading svnkit version)

        final String username = System.getProperty("site.username", System.getenv("USER"));
        final String password = args == null || args.length == 0 ?
                System.getProperty("site.password") : ofNullable(args[0]).filter(s -> !"notset".equalsIgnoreCase(s)).orElse(null);
        if (password == null) {
            throw new IllegalArgumentException("No site.password system property set");
        }

        final ISVNAuthenticationManager authenticationManager = SVNWCUtil.createDefaultAuthenticationManager(username, password.toCharArray());
        final SVNClientManager client = SVNClientManager.newInstance(new DefaultSVNOptions(), authenticationManager);
        final SVNUpdateClient update = client.getUpdateClient();
        update.setIgnoreExternals(true);
        final File copy = new File(".content-site-checkout");
        if (copy.exists()) {
            System.out.println("Cleaning up local copy");
            client.getWCClient().doCleanup(copy);

            System.out.println("Reverting local copy to start from a clean one");
            final SvnRevert revert = update.getOperationsFactory().createRevert();
            revert.setPreserveModifiedCopies(false);
            revert.setRevertMissingDirectories(true);
            revert.setDepth(SVNDepth.INFINITY);
            revert.addTarget(SvnTarget.fromFile(copy));
            revert.run();

            System.out.println("SVN site revision #" + update.doUpdate(copy, SVNRevision.HEAD, SVNDepth.INFINITY, false, true));
        } else {
            System.out.println("Doing a site checkout, can be a bit long the first time but it is cached, grab a coffee ;)");
            System.out.println("SVN site revision #" + update.doCheckout(
                    SVNURL.parseURIEncoded("https://svn.apache.org/repos/asf/tomee/site/trunk/content/"),
                    copy, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY, true));
        }

        // synchronize the generated site and the copy
        final File[] site = new File("target").listFiles(pathname -> pathname.getName().startsWith("site-") && pathname.isDirectory());
        if (site == null || site.length != 1) {
            throw new IllegalArgumentException("Can't find the site: " + asList(site));
        }
        FileUtils.copyDirectory(site[0], copy);

        final Collection<File> added = new ArrayList<>();
        final Collection<File> updated = new ArrayList<>();
        client.getStatusClient().doStatus(copy, SVNRevision.HEAD, SVNDepth.INFINITY, false, false, false, false, new ISVNStatusHandler() {
            @Override
            public void handleStatus(final SVNStatus status) throws SVNException {
                final SVNStatusType contentsStatus = status.getContentsStatus();
                if (contentsStatus == SVNStatusType.STATUS_UNVERSIONED) {
                    added.add(status.getFile());
                } else if (contentsStatus == SVNStatusType.STATUS_MODIFIED || contentsStatus == SVNStatusType.STATUS_REPLACED) {
                    updated.add(status.getFile());
                } // else we don't care
            }
        }, null);

        final Collection<File> copies = Stream.concat(added.stream(), updated.stream()).collect(toList());
        // remove the .pdf without their .html, likely means there is no real update
        added.removeIf(f -> f.getName().endsWith(".pdf") && !copies.contains(new File(f.getParentFile(), f.getName().replace(".pdf", ".html"))));
        updated.removeIf(f -> f.getName().endsWith(".pdf") && !copies.contains(new File(f.getParentFile(), f.getName().replace(".pdf", ".html"))));

        if (updated.size() + added.size() == 0) {
            System.out.println("Nothing to commit!");
            return;
        }

        added.forEach(f -> {
            try {
                client.getWCClient().doAdd(f, false, false, false, SVNDepth.INFINITY, false, false, true);
            } catch (final SVNException e) {
                throw new IllegalStateException(e);
            }
        });

        final Path sitePath = copy.getAbsoluteFile().toPath();
        added.forEach(f -> System.out.println("A " + sitePath.relativize(f.getAbsoluteFile().toPath())));
        updated.forEach(f -> System.out.println("M " + sitePath.relativize(f.getAbsoluteFile().toPath())));

        // now update it remotely, note: we could use the status output to do it more efficiently
        final String message = ofNullable(args == null || args.length < 2 ? System.getProperty("site.message") : args[1])
                .filter(m -> !"notset".equalsIgnoreCase(m))
                .orElseGet(() -> "Maven update of the website on the " + new Date() + " from " + username);
        final SVNCommitInfo commitInfo = client.getCommitClient().doCommit(
                Stream.concat(added.stream(), updated.stream()).toArray(File[]::new), false, message,
                null, null, false, false, SVNDepth.IMMEDIATES);
        if (commitInfo.getErrorMessage() != null) {
            throw new IllegalStateException(commitInfo.getErrorMessage().toString());
        }
        System.out.println(commitInfo.toString());
    }
}
