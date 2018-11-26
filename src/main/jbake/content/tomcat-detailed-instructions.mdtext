Title: Tomcat Detailed Instructions
{composition-setup}

<a name="TomcatDetailedInstructions-{anchor:detailedinstructions}DetailedInstallationInstructions"></a>
# {anchor:detailed instructions} Detailed Installation Instructions

These instructions assume you have a standard Tomcat installation running
on port 8080.  If you do not have an existing Tomcat installation, or want
to start with a fresh installation for OpenEJB, the [Tomcat Installation](tomcat-installation.html)
 will show you how to setup and verify a Tomcat installation.

<a name="TomcatDetailedInstructions-Addopenejb.wartoTomcat"></a>
## Add openejb.war to Tomcat

The TomEE plugin for Tomcat is distributed as a standalone war file
containing all of the necessary files and an installer Servlet.  The war
can be obtained from the [download page](http://tomee.apache.org/downloads.html)
.  Once downloaded, simply copy the file into the Tomcat webapps directory. 

{deck:id=Copy openejb.war}
{card:label=Windows}{noformat:nopanel=true}
C:\>copy openejb.war apache-tomcat-6.0.14\webapps\openejb.war
	1 file(s) copied.

C:\>dir apache-tomcat-6.0.14\webapps
 Volume in drive C has no label.
 Volume Serial Number is 0000-0000

 Directory of C:\apache-tomcat-6.0.14\webapps

09/20/2007  03:03 PM	<DIR>	       .
09/20/2007  03:03 PM	<DIR>	       ..
09/20/2007  03:02 PM	<DIR>	       docs
09/20/2007  03:01 PM	<DIR>	       examples
09/20/2007  03:01 PM	<DIR>	       host-manager
09/20/2007  03:03 PM	<DIR>	       manager
09/19/2007  09:31 AM	    13,394,733 openejb.war
09/20/2007  03:01 PM	<DIR>	       ROOT
	       1 File(s)     13,394,733 bytes
	       7 Dir(s)   5,100,126,208 bytes free

    {card:label=Unix}{noformat:nopanel=true}
    $ cp openejb.war apache-tomcat-6.0.14/webapps/openejb.war
    
    $ ls apache-tomcat-6.0.14/webapps/
    ROOT/	      docs/	    examples/	  host-manager/ manager/     
openejb.war

{deck}

<a name="TomcatDetailedInstructions-RunInstallerServlet"></a>
## Run Installer Servlet

The OpenEJB Plugin for Tomcat contains an installer servlet which adds the
OpenEJB listener and JavaAgent to the Tomcat installation.  To run the
installer, you must first start Tomcat.

{deck:id=Start Tomcat}
{card:label=Windows}{noformat:nopanel=true}
C:\>set JRE_HOME=C:\Program Files\Java\jre1.5.0_06

C:\>cd apache-tomcat-6.0.14\bin

C:\apache-tomcat-6.0.14\bin>startup.bat
Using CATALINA_BASE:   C:\apache-tomcat-6.0.14
Using CATALINA_HOME:   C:\apache-tomcat-6.0.14
Using CATALINA_TMPDIR: C:\apache-tomcat-6.0.14\temp
Using JRE_HOME:        C:\your\java\installation

    {card:label=Unix}{noformat:nopanel=true}
    $ cd apache-tomcat-6.0.14/bin
    
    apache-tomcat-6.0.14/bin$ chmod u+x *.sh
    
    apache-tomcat-6.0.14/bin$ ./startup.sh 
    Using CATALINA_BASE:   /your/tomcat/installation/apache-tomcat-6.0.14
    Using CATALINA_HOME:   /your/tomcat/installation/apache-tomcat-6.0.14
    Using CATALINA_TMPDIR: /your/tomcat/installation/apache-tomcat-6.0.14/temp
    Using JRE_HOME:        /your/java/installation

{deck}

*NOTE:* Your output will be different from the example above due to
differences in installation locations.

It is a good idea to wait a 5-60 seconds (depending on the speed of your
computer) for Tomcat to fully start.  Once Tomcat is fully started, simply
visit [http://localhost:8080/openejb/installer](http://localhost:8080/openejb/installer)
 and click the 'install' button to run the installer.  The installer should
report that the installation was successful. If it didn't work click [OPENEJB:here|#problems]
.

{warning}
The installer servlet adds the OpenEJB JavaAgent declaration to the
catalina.sh and catalina.bat files.  If you are using an IDE or some other
mechanism to start Tomcat, you will need to [manually](manual-installation#javaagent.html)
 add the JavaAgent declaration to the Java VM options of the launcher you
are using. 
{warning}

<a name="TomcatDetailedInstructions-RestartTomcat"></a>
## Restart Tomcat

OpenEJB uses OpenJPA for persistence and OpenJPA currently requires a
JavaAgent to function.	Unfortunately, there is no way to install a
JavaAgent at runtime, so you will have to restart Tomcat to enable the
JavaAgent.  Simply execute the shutdown command, wait 5-60 seconds
(depending on the speed of your computer) for Tomcat to fully stop, and run
the startup command to restart Tomcat.

{deck:id=Start Tomcat}
{card:label=Windows}{noformat:nopanel=true}
C:\>cd apache-tomcat-6.0.14\bin

C:\apache-tomcat-6.0.14\bin>shutdown.bat
Using CATALINA_BASE:   C:\apache-tomcat-6.0.14
Using CATALINA_HOME:   C:\apache-tomcat-6.0.14
Using CATALINA_TMPDIR: C:\apache-tomcat-6.0.14\temp
Using JRE_HOME:        C:\your\java\installation

C:\apache-tomcat-6.0.14\bin>startup.bat
Using CATALINA_BASE:   C:\apache-tomcat-6.0.14
Using CATALINA_HOME:   C:\apache-tomcat-6.0.14
Using CATALINA_TMPDIR: C:\apache-tomcat-6.0.14\temp
Using JRE_HOME:        C:\your\java\installation

    {card:label=Unix}{noformat:nopanel=true}
    $ cd apache-tomcat-6.0.14/bin
    
    apache-tomcat-6.0.14/bin$ ./shutdown.sh 
    Using CATALINA_BASE:   /your/tomcat/installation/apache-tomcat-6.0.14
    Using CATALINA_HOME:   /your/tomcat/installation/apache-tomcat-6.0.14
    Using CATALINA_TMPDIR: /your/tomcat/installation/apache-tomcat-6.0.14/temp
    Using JRE_HOME:        /your/java/installation
    
    apache-tomcat-6.0.14/bin$ ./startup.sh 
    Using CATALINA_BASE:   /your/tomcat/installation/apache-tomcat-6.0.14
    Using CATALINA_HOME:   /your/tomcat/installation/apache-tomcat-6.0.14
    Using CATALINA_TMPDIR: /your/tomcat/installation/apache-tomcat-6.0.14/temp
    Using JRE_HOME:        /your/java/installation

{deck}

*NOTE:* Your output will be different from the example above due to
differences in installation locations.

Once Tomcat is fully started, simply visit [http://localhost:8080/openejb/installer](http://localhost:8080/openejb/installer)
 to verify the installation is complete.

<a name="TomcatDetailedInstructions-Examples,TutorialsandTests"></a>
# Examples, Tutorials and Tests

<a name="TomcatDetailedInstructions-ejb-examples.war"></a>
## ejb-examples.war

Download the [ejb-examples.war](http://people.apache.org/~dain/openejb-temp/examples)
, copy it into the Tomcat webapps directory, and visit [http://localhost:8080/ejb-examples]
.

<a name="TomcatDetailedInstructions-OpenEJBiTests"></a>
## OpenEJB iTests

OpenEJB uses a large test suite to verify the final server assembly, and
you can use this to verify your OpenEJB installation.  Simply download the [openejb-itests.war and openejb-standalone-client.jar](http://people.apache.org/~dain/openejb-temp/itests)
 and copy it the war into the Tomcat webapps directory.  It will take a bit
to load the application because it contains a huge number of EJBs. 
Finally, run the test client executable jar.

{deck:id=Start Tomcat}
{card:label=Windows}{noformat:nopanel=true}
C:\>java -jar openejb-itests-standalone-client.jar tomcat
_________________________________________________
<table>
<tr><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td><td>_</td></tr>
</table>

Running EJB compliance tests on HTTP/Tomcat Server
_________________________________________________
WARNING: No test suite configuration file specified, assuming system
properties contain all 
needed information.  To specify a test suite configuration file by setting
its location using
the system property "openejb.testsuite.properties" 
test server = org.apache.openejb.test.TomcatRemoteTestServer
entry = java.naming.provider.url:http://127.0.0.1:8080/openejb/ejb
entry =
java.naming.factory.initial:org.apache.openejb.client.RemoteInitialContextFactory
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
.........................................
............................
Time: 20.644

OK (889 tests)


_________________________________________________
CLIENT JNDI PROPERTIES
java.naming.provider.url = http://127.0.0.1:8080/openejb/ejb
java.naming.factory.initial =
org.apache.openejb.client.RemoteInitialContextFactory
_________________________________________________

    {card:label=Unix}{noformat:nopanel=true}
    $ java -jar openejb-itests-standalone-client.jar tomcat
    _________________________________________________
    |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
    
    Running EJB compliance tests on HTTP/Tomcat Server
    _________________________________________________
    WARNING: No test suite configuration file specified, assuming system
properties contain all
    needed information.  To specify a test suite configuration file by setting
its location using
    the system property "openejb.testsuite.properties"
    test server = org.apache.openejb.test.TomcatRemoteTestServer
    entry = java.naming.provider.url:http://127.0.0.1:8080/openejb/ejb
    entry =
java.naming.factory.initial:org.apache.openejb.client.RemoteInitialContextFactory
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    .........................................
    ............................
    Time: 12.186
    
    OK (889 tests)
    
    
    _________________________________________________
    CLIENT JNDI PROPERTIES
    java.naming.provider.url = http://127.0.0.1:8080/openejb/ejb
    java.naming.factory.initial =
org.apache.openejb.client.RemoteInitialContextFactory
    _________________________________________________

{deck}

{tip:title=Failures}The tests should completely pass the first time they
are run.  If you execute the test client a second time, 21 tests fail for
some unknown reason.{tip}

<a name="TomcatDetailedInstructions-{anchor:problems}Problems?"></a>
# {anchor:problems} Problems?

<a name="TomcatDetailedInstructions-HTTPStatus403"></a>
## HTTP Status 403 

Did you get a "HTTP Status 403" error page containing the description
"Access to the specified resource () has been forbidden." when visiting [http://localhost:8080/openejb](http://localhost:8080/openejb)
?

The openejb.war is protected by a Tomcat valve that restricts access to the
application to the computer on which Tomcat is running.  If your browser is
running on the same computer as Tomcat, try accessing OpenEJB using this
link instead [http://127.0.0.1:8080/openejb](http://127.0.0.1:8080/openejb)
.

If you want to access the openejb.war from another computer, you will need
to either remove the valve, or modify the IP list in the valve declaration.
 The easiest way to remove the valve it to simply delete the
webapps/openejb/META-INF/context.xml file and and the webapps/openejb.war
file *while Tomcat is stopped*.  The openejb.war file must be removed
because some versions of Tomcat will use the context.xml file packed in the
openejb.war file regardless of what is in the unpacked directory.

<a name="TomcatDetailedInstructions-OtherIssues"></a>
## Other Issues

If you are having problems with the installation, please send a message to
the OpenEJB users [mailing list](openejb:mailing-lists.html)
 containing any error message(s) and the following information:

* OpenEJB Version
* Tomcat Version
* Java Version (execute java -version)
* Operating System Type and Version


<a name="TomcatDetailedInstructions-Limitations"></a>
# Limitations

 *Tomcat 6.x* - Currently, only Tomcat 6.x is supported due to API
difference between 5.5.x and 6.x.  It is expected that 5.5 will be
supported in the future, but there are no plans to support 5.0.x due to the
lack of annotation support in 5.0.x.

 *Security* - Unfortunately, at this time security with Tomcat/OpenEJB is
not integrated, but is being worked on.

 *EAR Files* - The integration only supports war (and collapsed-ear) files.
 EAR, EJB Jar, and RAR files will be supported in a future release.

 *JavaAgent* - OpenEJB uses OpenJPA to provide JPA and CMP persistence, and
OpenJPA currently requires a JavaAgent to function properly.  This
requirement is something that the OpenJPA project is working on removing. 
Once removed, the OpenEJB plugin for Tomcat will no longer need to modify
the startup shell scripts and you will not need to restart Tomcat after the
OpenEJB installation.

<a name="TomcatDetailedInstructions-Misc"></a>
# Misc

This document is a starting point for using OpenEJB in Tomcat and will
evolve based on user contributions. If you wish to contribute to this
document, feel very welcome to click the 'Edit' link in the upper right and
make changes and add new HOWTO's and other docs.  

