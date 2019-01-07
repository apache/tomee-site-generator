# Apache TomEE 1.7.4 released, Mar 7th 2016

The Apache TomEE community is pleased to announce the release of [Apache TomEE 1.7.4](http://tomee.apache.org/downloads.html), which is based on [Apache Tomcat 7.0.68](http://tomcat.apache.org/tomcat-7.0-doc/index.html), including numerous fixes and updates.
Part of this release an important security fix is included if EJBd is active on your instance (true by default). More on [CVE-2016-0779](security/tomee.html).

Please note that the TomEE 1.7.4 drop in WAR file will not work on Apache Tomcat 8. If you are interested in a Tomcat 8.x version then please feel free to check out and [contribute to the developer branch](contribute.html) - We are always interested in any help from the community that we can get.

The Apache TomEE 1.7.4 release files can be downloaded from here:

[http://tomee.apache.org/downloads.html](downloads.html)

###Update Maven POM Files

Simply update your existing Maven JavaEE-API, OpenEJB and or TomEE pom.xml entries to point to the latest versions:

	<dependency>
		<groupId>org.apache.openejb</groupId>
		<artifactId>javaee-api</artifactId>
		<version>6.0-6</version>
		<scope>provided</scope>
	</dependency>
	
	<dependency>
		<groupId>org.apache.openejb</groupId>
		<artifactId>openejb-core</artifactId>
		<version>4.7.4</version>
	</dependency>
	
	<dependency>
		<groupId>org.apache.openejb</groupId>
		<artifactId>tomee</artifactId>
		<version>1.7.4</version>
	</dependency>

A complete [Changelog](tomee-1.7.4-release-notes.html) can be viewed here:

[tomee-1.7.4-release-notes.html](tomee-1.7.4-release-notes.html)

Please feel free to jump in feet first and [get started with TomEE](documentation.html). You will nearly always find someone to help you on one of our [support channels](support.html).

###Java EE7 and Beyond

From this point on the TomEE community will be focusing most of it's efforts on TomEE 7.0.0, which will be the first TomEE Java EE 7 driven version running on Apache Tomcat 8.x. It is more than likely that support for Java SE 6 will be dropped so that Java SE 7 features can finally be leveraged during the development process. We will of course continue to maintain the 1.7.x branch for the foreseeable future.
