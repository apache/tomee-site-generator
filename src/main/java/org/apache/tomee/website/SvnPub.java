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
import org.apache.xml.security.exceptions.Base64DecodingException;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;

public class SvnPub {
    private SvnPub() {
        // no-op
    }

    public static void main(final String[] args) throws SVNException, IOException, Base64DecodingException {
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

        final Path sitePath = copy.getAbsoluteFile().toPath();

        final Map<String, File> changed = new HashMap<>();
        int previous = 0;

        do {
            previous = changed.size();

            client.getStatusClient().doStatus(copy, SVNRevision.HEAD, SVNDepth.INFINITY, false, false, false, false, new ISVNStatusHandler() {
                @Override
                public void handleStatus(final SVNStatus status) throws SVNException {
                    final SVNStatusType contentsStatus = status.getContentsStatus();
                    final File file = status.getFile();

                    final String path = sitePath.relativize(file.getAbsoluteFile().toPath()).toString();

                    if (contentsStatus == SVNStatusType.STATUS_UNVERSIONED || contentsStatus == SVNStatusType.STATUS_NONE) {

                        changed.put(path, file);
                        client.getWCClient().doAdd(file, false, false, false, SVNDepth.INFINITY, false, false, true);
                        System.out.println("A " + path);

                    } else if (contentsStatus == SVNStatusType.STATUS_MODIFIED || contentsStatus == SVNStatusType.STATUS_REPLACED) {

                        if (changed.put(path, file) == null) {
                            System.out.println("M " + path);
                        }

                    } // else we don't care
                }
            }, null);

        } while (changed.size() != previous);

        if (changed.size() == 0) {
            System.out.println("Nothing to commit!");
            return;
        }

        // now update it remotely, note: we could use the status output to do it more efficiently
        final String message = ofNullable(args == null || args.length < 2 ? System.getProperty("site.message") : args[1])
                .filter(m -> !"notset".equalsIgnoreCase(m))
                .orElseGet(() -> "Maven update of the website on the " + new Date() + " from " + username);

        final File[] commit = changed.values().stream().toArray(File[]::new);

        final SVNCommitInfo commitInfo = client.getCommitClient().doCommit(commit, false, message, null, null, false, false, SVNDepth.IMMEDIATES);

        if (commitInfo.getErrorMessage() != null) {
            throw new IllegalStateException(commitInfo.getErrorMessage().toString());
        }

        System.out.println(commitInfo.toString());

        // do we want to POST https://cms.apache.org/tomee/publish?diff=1 \
        // message=&submit=Submit&key=commitInfo.getNewRevision()&source_url=https%3A%2F%2Fsvn.apache.org%2Frepos%2Fasf%2Ftomee%2Fsite&referer=
        //
        // to automatically publish on prod?
    }
}
