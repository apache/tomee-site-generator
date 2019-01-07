# Apache TomEE 1.6.0.1 Released!

Apache TomEE is happy to announce the security related release of [Apache TomEE 1.6.0.1](http://tomee.apache.org/downloads.html) which is now based on [Apache Tomcat 7.0.53](http://tomcat.apache.org/tomcat-7.0-doc/index.html) - The principal focus of this release is to provide compatibility for a significant security fix introduced in Tomcat 7.0.51 for the Apache Commons FileUpload. We recommend to all TomEE 1.6.0 users that are affected by this issue to upgrade TomEE to this latest version at the earliest opportunity.

Complete details of the issue can be found at the following link:
 
*Important: Denial of Service* 
CVE-2014-0050<[http://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2014-0050](http://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2014-0050)> 

It goes without saying that everyone has heard of the Heartbleed issue and we have quickly replaced the included Apache Tomcat Native library with version 1.1.30 for this release, should anyone be using it. Note, this is not the default for TomEE and it does not resolve the issue, for that you will still need to update your OpenSSL - It merely enables the use of a more recent OpenSSL version. You can find more information here:

[http://tomcat.apache.org/native-doc/](http://tomcat.apache.org/native-doc/) - You may need to refresh the page if you have been checking the site as it was only recently updated. 

We would like to thank everyone in the community involved in the reporting, documentation and final resolution of this issue. A very special thank you goes out to Romain Manni-Bucau and Jonathan Gallimore for their tireless efforts in enabling us to provide this release so quickly.

The Apache TomEE Release 1.6.0.1 files can be found here:

[http://tomee.apache.org/downloads.html](http://tomee.apache.org/downloads.html)

A complete [Changelog](tomee-1.6.0.1-release-notes.html) can be viewed here:

[tomee-1.6.0.1-release-notes.html](tomee-1.6.0.1-release-notes.html)

To see the full [Changelog](http://tomcat.apache.org/tomcat-7.0-doc/changelog.html) of Apache Tomcat since version 7.0.51 follow this link:

[http://tomcat.apache.org/tomcat-7.0-doc/changelog.html](http://tomcat.apache.org/tomcat-7.0-doc/changelog.html)
