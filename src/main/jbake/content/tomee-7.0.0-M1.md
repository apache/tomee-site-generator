# Apache TomEE 7.0.0-M1 released, Dec 11th 2015

The Apache TomEE community is proud to announce the release of [Apache TomEE 7.0.0-M1](download/tomee-7.0.0-M1.html), which is based on [Apache Tomcat 8.0.30](http://tomcat.apache.org/tomcat-8.0-doc/index.html) and is our first milestone release towards EE7.
We know this has been a long time coming, so we thank you for your patience and support.

Please feel free to check out and [contribute to the developer branch](contribute.html) - We are always interested in any help from the community that we can get.

This version is not certified and is provided to the community as a milestone preview of the current development version. It is however an extremly well tested version. So please do test your applications and give us your feedback.

The Apache TomEE 7.0.0-M1 release files can be downloaded from here:

[http://tomee.apache.org/download/tomee-7.0.0-M1.html](download/tomee-7.0.0-M1.html)

###Update Maven POM Files - The OpenEJB version and groupId have been aligned

Simply update your existing Maven JavaEE-API, OpenEJB and or TomEE pom.xml entries to point to the latest versions:

	<dependency>
		<groupId>org.apache.tomee</groupId>
		<artifactId>javaee-api</artifactId>
		<version>7.0</version>
		<scope>provided</scope>
	</dependency>
	
	<dependency>
		<groupId>org.apache.tomee</groupId>
		<artifactId>openejb-core</artifactId>
		<version>7.0.0-M1</version>
	</dependency>
	
	<dependency>
		<groupId>org.apache.tomee</groupId>
		<artifactId>tomee</artifactId>
		<version>7.0.0-M1</version>
	</dependency>

A complete [Changelog](tomee-7.0.0-M1-release-notes.html) can be viewed here:

[tomee-7.0.0-M1-release-notes.html](tomee-7.0.0-M1-release-notes.html)

Please feel free to jump in feet first and [get started with TomEE](documentation.html). You will nearly always find someone to help you on one of our [support channels](support.html).