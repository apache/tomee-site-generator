#Releasing TomEE

	This document is aimed at guiding a release manager through the general release process. You will need either a Linux, Mac, or failing that a Linux Virtual (with at least a 50GB Drive) on Win. 

###Preparation of The Branch
	
Run **ant -f rat.xml > report.txt** on trunk to ensure all licences are in place.

 - Review the report.txt and update/add missing headers until clean.
 - *Tip*, search for **Unapproved licenses:** at the beginning of the report for a list.

Branch the version to release and ensure it builds and passes all tests.

Add a buildbot CI setup for branch here:

[https://svn.apache.org/repos/infra/infrastructure/buildbot/aegis/buildmaster/master1/projects/tomee.conf](https://svn.apache.org/repos/infra/infrastructure/buildbot/aegis/buildmaster/master1/projects/tomee.conf)

Basically search for the following line and it should be obvious how to add a new builder:

	c['builders'].append(tomee_hemera_builder("tomee-trunk-ubuntu", "tomee/tomee/trunk"))
	
An SVN trigger must be added afterwards. This can only be done by someone with admin permissions, such as any PMC chair or an Infra team member.
Just drop an email to *infrastructure@apache.org*

###Create a TCK Branch

Branch the TCK using the same version as the release branch from here:

[https://svn.apache.org/repos/tck/tomee-tck/trunk](https://svn.apache.org/repos/tck/tomee-tck/trunk)

Update the TCK branch files to point to the version branch.

	\tckbranch\plus.properties
	\tckbranch\pom.xml
	\tckbranch\webprofile-plus.properties
	\tckbranch\webprofile.properties

Run **ant -f rat.xml > report.txt** on the branch.

 - Review the report.txt and update/add missing headers until clean.
 - *Tip*, search for **Unapproved licenses:**.

###Check SVN Authentication
 
Pre-authenticate svn repositories to ensure your credentials are cached before using any tools.

	svn mkdir --username [apacheuser] --password [apachepw] -m "Create test dir" https://svn.apache.org/repos/asf/tomee/tomee/branches/testdir1
	svn delete --username [apacheuser] --password [apachepw] -m "Delete test dir" https://svn.apache.org/repos/asf/tomee/tomee/branches/testdir1
	svn mkdir --username [apacheuser] --password [apachepw] -m "Create test dir" https://repository.apache.org/content/repositories/testdir2
	svn delete --username [apacheuser] --password [apachepw] -m "Delete test dir" https://repository.apache.org/content/repositories/testdir2
	svn mkdir --username [apacheuser] --password [apachepw] -m "Create test dir" https://dist.apache.org/repos/dist/dev/tomee/testdir3
	svn delete --username [apacheuser] --password [apachepw] -m "Delete test dir" https://dist.apache.org/repos/dist/dev/tomee/testdir3

###Prepare Maven Authentication

Ensure your maven .m2/settings.xml correct, and be aware that the tools currently require a clear text password:

	<server>
	  <id>apache.snapshots.https</id>
	  <username>un</username>
	  <password>pw.in.clear</password>
	</server>

	<server>
	  <id>apache.releases.https</id>
	  <username>un</username>
	  <password>pw.in.clear</password>
	</server>

	<server>
	  <id>apache.dist.https</id>
	  <username>un</username>
	  <password>pw.in.clear</password>
	</server>

	<profiles>
		<profile>
		...
			<repositories>
				<repository>
				  <id>apache.dist.https</id>
				  <url>https://dist.apache.org/repos/dist</url>
				</repository>
			  </repositories>

###Code Signing Setup

If this is your first release then you will have to ensure that you have a code signing key prepared on the machine from which you perform the release. The process is quite intense. You can find information here:

 - [http://www.apache.org/dev/release-signing.html](http://www.apache.org/dev/release-signing.html)
 - [http://maven.apache.org/developers/release/pmc-gpg-keys.html](http://maven.apache.org/developers/release/pmc-gpg-keys.html)
 
However, the basic steps are:

 - Create a key using **gpg --gen-key**, using size 4096 and answering the questions that command issues.
 - During the process you will have to generate random entropy, this is best achieved in another console and issuing the command **find / > /dev/null** and waiting a minute.
 - List the keys using **gpg --list-keys** and take note of the name

Once you have your key then you will need to append it to the key file here:

 - [http://www.apache.org/dist/tomee/KEYS](http://www.apache.org/dist/tomee/KEYS)

That is best done as the file itself explains, once you open and view it in a UTF-8 safe text editor you will see the description at the top.  
Just follow the instructions there on how to append your key. The basic steps are also here, please read both before you proceed:

 - Save the KEYS file on your local machine and import it using **gpg --import KEYS**
 - Then create the new KEYS file using **(gpg --list-sigs <your name> && gpg --armor --export <your name>) >> KEYS**
 - Check that the new KEYS file contains your key.
 - Log in to people.apache.org and locate /dist/tomee/KEYS
 - Make a backup of the remote KEYS file just in case
 - Overwrite the old /dist/tomee/KEYS file with your new one that now also contains your key.
 - Go to [http://pgp.mit.edu/](http://pgp.mit.edu/) and add your ascii armoured key
 - Take note of your key fingerprint using **gpg --fingerprint <your name>**
 - Go to [https://id.apache.org](https://id.apache.org), log in and fill OpenPGP Public Key Primary Fingerprint: with the value of your fingerprint.
 
###Build the Release Tools

Checkout the release tools using SVN from here [https://svn.apache.org/repos/asf/tomee/sandbox/release-tools](https://svn.apache.org/repos/asf/tomee/sandbox/release-tools)

Really read the README.mdtext and follow the instructions for building the 3rd party libraries.  
Basically SVN checkout and compile [Swizzle](https://svn.codehaus.org/swizzle/trunk) and [Tentacles](https://svn.apache.org/repos/asf/creadur/tentacles/trunk)

Build the release tools, *mvn clean install -DskipTests -DfailIfNoTests=false*

Have a look at **run.sh** to see the entry point.

Understand that the release tools are not polished, and you currently may have to edit source and re-compile.

###Site Staging
<a href="#staging"/>
For some of the release steps you will need to provide documentation on the site. Checkout the site here:

[https://svn.apache.org/repos/asf/tomee/site/trunk](https://svn.apache.org/repos/asf/tomee/site/trunk)

Most of the content can be found under 'content' and subdirectories.

When you commit changes the site should be built automatically by the buildbot, but you can force a build on IRC using:

    **tomee-bot: force build tomee-site-staging**

The buildbot staging result can be seen here:

[http://ci.apache.org/builders/tomee-site-staging](http://ci.apache.org/builders/tomee-site-staging)

And the actual staging site, where you can review your changes, is here:

[http://tomee.staging.apache.org/](http://tomee.staging.apache.org/)

Once you are happy with the staging you can publish to the real site using:

[https://cms.apache.org/tomee/publish](https://cms.apache.org/tomee/publish)

###Begin The Release Process

Ensure TCK is passing all tests, and if so create an SVN tag from the branch.

	Note: It is a future goal to either separate OpenEJB from TomEE or unify the versions so the
	[maven-release-plugin](http://maven.apache.org/maven-release/maven-release-plugin/) can be used.

	Because we cannot use the Maven release tools we currently have to create a an SVN tag manually. The best way to do this is to:
	
	 - Copy the branch to a staging branch using:  
	   > svn copy https://svn.apache.org/repos/asf/tomee/tomee/branches/tomee-[version]  https://svn.apache.org/repos/asf/tomee/tomee/branches/tomee-[version]-staging -m "Staging [version]"
	 - Checkout the staging branch using:  
	   > svn co https://svn.apache.org/repos/asf/tomee/tomee/branches/tomee-[version]-staging tomee-[version]-staging
	 - Update all SNAPSHOT versions to the release versions in the local tomee-[version]-staging and commit.
	 - Create the tag from the staging:  
	   > svn copy https://svn.apache.org/repos/asf/tomee/tomee/branches/tomee-[version]-staging https://svn.apache.org/repos/asf/tomee/tomee/tags/tomee-[version] -m "Tag [version]"
	 - Delete the staging branch using:  
	   > svn rm https://svn.apache.org/repos/asf/tomee/tomee/branches/tomee-[version]-staging -m "Delete staging"

Open a console on the release-tools directory.

Note: Before running any **./run.sh** activity always check the release tools code for the command tomee-release-tools/src/main/java/org/apache/openejb/tools/release/cmd.
At the moment some of the commands need manually editing to work. Eventually the commands should be re-written.

All JIRA actions should be performed on the ASF JIRA here:

[https://issues.apache.org/jira/browse/TOMEE](https://issues.apache.org/jira/browse/TOMEE)

Ensure JIRAs have been filed for commits using **./run.sh reviewcommits**

Update fixVersions for JIRAs used in SVN commits using **./run.sh updatejiras** - *Untested, requires investigation*

Review and bulk Close all JIRAs for the version to be released.

Publish the changed binaries report (if any) using **./run.sh comparelibraries**

Write and publish the release notes preview on the staging site. 

Publish a summary of the RAT report preview on the staging site.

Using the RAT report as a guide update LICENSE and NOTICE files for any changed binaries, and add new ones if required.

Update branch versions. How you do this is up to you at this point in time.

Update trunk versions. How you do this is up to you at this point in time.

Create the next version iterations in JIRA.

###Rolling Out The Preview

	Note: Before running anything below ensure you either have:

	 - A valid tomee-release.properties from the last release in your home directory (Speak to the last release manager).
	 - Or have modified **tomee-release-tools/src/main/java/org/apache/openejb/tools/release/Release.java** with current versions and **mvn clean install**.
	 	 
Ensure the TCK passes with preview repositories by editing and ensuring paths are correct in the following files:

	\tckbranch\plus.properties
	\tckbranch\pom.xml
	\tckbranch\webprofile-plus.properties
	\tckbranch\webprofile.properties

Publish the preview using **./run.sh roll binaries legal releasenotes preview** - You can run these tasks like so, or individually in order.
It will be likely that this will have to be repeated several times before a successful vote.

The *legal* step will create the legal report files in the /tmp/download/staging-[revision]/legal directory. These need to be added to the staging repo.
 - Delete the [legal]/repo and [legal]/content directories, as these are no longer required
    rm -R /tmp/download/staging-[revision]/legal/content
    rm -R /tmp/download/staging-[revision]/legal/repo

 - Perform a non-recursive checkout of the staging repo and add the legal:
	svn co -N https://dist.apache.org/repos/dist/dev/tomee/staging-[revision] /tmp/download/staging
	mv /tmp/download/staging-[revision]/legal /tmp/download/staging
	cd /tmp/download/staging-[revision]
	svn add legal

Once the binaries are in place add the staging repository to the corresponding TCK project and fire off a build.
To fire off a build on EC2 from the TCK directory speak to the last release manager for the **curl** command to use

If the TCK fails then discuss, fix and re-roll.

Publish a [Vote](https://www.apache.org/foundation/voting.html) if, and only if, the TCK passes.

Votes are generally managed and identified using keywords such as [VOTE], [CANCELLED] and [RESULT]

If the vote fails then discuss, fix and re-roll.

###Voted Binaries

Once the vote has passed then release the binaries on Nexus: [https://repository.apache.org/index.html#welcome](https://repository.apache.org/index.html#welcome)

Update both OpenEJB and TomEE JIRA versions as released (Set the release date).

Copy the binaries to the release location (User rights require a PMC to do this)

	From: https://dist.apache.org/repos/dist/dev/tomee/staging-[stagingId]/tomee-[version]
	To: https://dist.apache.org/repos/dist/release/tomee/tomee-[version] 
    

Wait for the binaries to replicate to mirrors. Here is a neat script from David to check the status:

    #!/bin/bash

    RELEASE=${1?Specify a release, such as './mirror_check.sh tomee-1.7.1'}

    function list_mirrors {
        DYN=http://www.apache.org/dyn/closer.cgi/tomee/$RELEASE/
        wget -q -O - $DYN | tr '">< ' '\n' | grep "^http.*$RELEASE/" | sort | uniq
    }

    function status_code {
        wget -v "$1" 2>&1| grep 'awaiting response' | tr ' ' '\n' | grep "[0-9]"
    }

    list_mirrors | while read n; do
        echo "$(status_code $n) $n"
    done | sort | grep 'http'

Commit and publish changes to the site, see [Site Staging](release-tomee.html#staging)

    https://cms.apache.org/tomee/publish


###Blog

Announce to the world that TomEE has new bells and whistles!

[https://blogs.apache.org/roller-ui/login.rol](https://blogs.apache.org/roller-ui/login.rol)  
[http://twitter.com/ApacheTomEE](http://twitter.com/ApacheTomEE)  
[http://facebook.com/ApacheTomEE](http://facebook.com/ApacheTomEE)  
[https://plus.google.com/118203123063829126066](https://plus.google.com/118203123063829126066)  
