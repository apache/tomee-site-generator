# Apache TomEE 1.7.0 Released!

Great news for Summer! After many months of hard work and dedicated community commitment Apache TomEE is happy to finally announce the feature release of [Apache TomEE 1.7.0](http://tomee.apache.org/downloads.html), which is based on [Apache Tomcat 7.0.55](http://tomcat.apache.org/tomcat-7.0-doc/index.html). Please do not forget that nearly all of the work that goes into this purely open source project is community driven. Very special thanks go out to all that have dedicated their time and effort to make this release possible. Anyone active on the [mailing lists and/or IRC](http://tomee.apache.org/support.html) will know that an extra special thanks for [Romain Manni-Bucau](http://rmannibucau.wordpress.com) for his lightning fast support is well deserved.

The 1.7.0 feature release is the final Java SE 6 / Java SE 8 compatible Oracle Java EE 6 Web Profile Certified branch. 

So what is new?

###PLUME Profile (Mojarra and EclipseLink)

One the most important new features has to be the new experimental PLUME profile, which is basically TomEE PLUS with [Mojarra](https://javaserverfaces.java.net/) and [EclipseLink](http://www.eclipse.org/eclipselink/) added support.  
This makes the transition to TomEE from a Glassfish environment a lot less painful. So no more excuses, come and join us - We're all yours!

###Java SE 8 Runtime Support

A lot of work has been completed to ensure that Apache TomEE 1.7.x will run within an [Java SE 8 environment](http://www.oracle.com/technetwork/java/javase/downloads/index.html), yet remain backwards compatible with Java SE 6.  Several libraries used have been shaded and patched to achieve this goal and provide users with a stable platform for the transition to a new runtime.

###Bug Fixes and Improvements

In addition to PLUME and Java 8 support there have been a train, plane and truck load of tweaks, improvements and bug fixes to make this release one of the most stable and feature rich versions we have ever released. Just have a quick look at these lists to get an idea of how much has been achieved. Amazing!

A total of [154 TomEE](https://issues.apache.org/jira/secure/IssueNavigator.jspa?reset=true&mode=hide&jqlQuery=project+%3D+TOMEE+AND+fixVersion+%3D+1.7.0) issues have been addressed.  
A total of [237 OpenEJB](https://issues.apache.org/jira/secure/IssueNavigator.jspa?reset=true&mode=hide&jqlQuery=project+%3D+OPENEJB+AND+fixVersion+%3D+4.7.0) issues have been addressed (OK, to be fair, some of these were legacy JIRA's stuck in the cobweb)  

The Apache TomEE 1.7.0 release files can be downloaded from here:

[http://tomee.apache.org/downloads.html](http://tomee.apache.org/downloads.html)

Or just update your existing Maven JavaEE-API, OpenEJB and or TomEE entries to point to the latest versions:

	<dependency>
		<groupId>org.apache.openejb</groupId>
		<artifactId>javaee-api</artifactId>
		<version>6.0-6</version>
		<scope>provided</scope>
	</dependency>
	
	<dependency>
		<groupId>org.apache.openejb</groupId>
		<artifactId>openejb-core</artifactId>
		<version>4.7.0</version>
	</dependency>
	
	<dependency>
		<groupId>org.apache.openejb</groupId>
		<artifactId>tomee</artifactId>
		<version>1.7.0</version>
	</dependency>

A complete [Changelog](tomee-1.7.0-release-notes.html) can be viewed here:

[tomee-1.7.0-release-notes.html](tomee-1.7.0-release-notes.html)

So please feel free to jump in feet first and [get started with TomEE](http://tomee.apache.org/documentation.html). You will nearly always find someone to help you on one of our [support channels](http://tomee.apache.org/support.html).

###Java EE7 and Beyond

From this point on the TomEE community will be focusing most of it's efforts on TomEE 2.0.x, which will be the first TomEE Java EE 7 driven version running on Apache Tomcat 8.x. It is more than likely that support for Java SE 6 will be dropped so that Java SE 7 features can finally be leveraged during the development process. We will of course continue to maintain the 1.7.x branch for the foreseeable future.
