Title: Tomcat

<a name="Tomcat-Introduction"></a>
# Introduction

The OpenEJB plugin for Tomcat makes all of the OpenEJB features available
to Servlets, including:

 * @Annotations
   ** @Resource
   ** @PersistenceUnit
   ** @PersistenceContext
   ** @EJB
 * JPA - Java Persistence Architecture
 * JMS - Java Messaging Service
 * JTA - Transaction Processing
   ** TransactionManager
   ** Container Managed Transactions
   ** XA Support
 * JavaMail

In addition, WAR files can contain EJB modules and JPA persistence units
eliminating the annoying construction of .ear files.  Adding EJBs and JPA
Persistence beans to your application is as simple as adding the
@Stateless, @Stateful, @MessageDriven or @Entity to a class.  The packaging
is refered to as [OPENEJB:Collapsed EAR](openejb:collapsed-ear.html)
 style as the war file, ejb jar, and persistence unit files are merged into
one archive and share the same classloader.

*Requirements:*
 * OpenEJB 3.x
 * Tomcat 6.x or 5.5
 * Java 1.5 or 1.6

<a name="Tomcat-{anchor:quickinstructions}InstallationfortheImpatient"></a>
# Installation for the Impatient

Assuming you have a [normal working Tomcat 6.x or 5.5 installation](tomcat-installation.html)
:

1. Download [openejb.war](openejb:download.html)
1. Copy openejb.war to $\{catalina.base\}/webapps/openejb.war (Note: the
file *must* be named openejb.war)
1. Start Tomcat if it is not already running
1. (optional) Visit [http://localhost:8080/openejb/installer](http://localhost:8080/openejb/installer)
 and click the 'install' button

<a name="Tomcat-Examples,TutorialsandTests"></a>
# Examples, Tutorials and Tests

<a name="Tomcat-ejb-examples.war"></a>
## ejb-examples.war

See the webapps/ejb-examples/ directory in the [openejb-examples zip](openejb:download.html)
.

<a name="Tomcat-{anchor:problems}Problems?"></a>
#Problems?

<a name="Tomcat-HTTPStatus403"></a>
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
webapps/openejb/META-INF/context.xml file and the webapps/openejb.war file
*while Tomcat is stopped*.  Warning that Tomcat keeps a copy of all
context.xml files under conf/Catalina/localhost/<appname>.xml, so you may
need to delete the conf/Catalina/localhost/openejb.xml file as well.  The
openejb.war file must be removed because some versions of Tomcat will use
the context.xml file packed in the openejb.war file regardless of what is
in the unpacked directory.

<a name="Tomcat-DuplicateDeploymentIdException:"></a>
## DuplicateDeploymentIdException:
If you try to deploy the same ejb in two different web applications, then
you will get the following exception (in conf/openejb.log):

    org.apache.openejb.DuplicateDeploymentIdException: Application cannot be
deployed as it contains deployment-ids which are in use: 

To fix the issue, do the following:
1. Create a file named system.properties under the conf directory
1. Add the following to the system.properties file and save

    openejb.deploymentId.format={moduleId}/{ejbName}


<a name="Tomcat-java.lang.OutOfMemoryError:PermGenspace"></a>
## java.lang.OutOfMemoryError: PermGen space 
By default, the JVM starts with a small PermGen. Tomcat does not increase
this limit, so you may encounter this exception by the time Tomcat deploys
and executes your application. If you get this exception, you should
consider increasing the PermGen allocation for the Tomcat JVM. You can
achieve this by adding "-XX:MaxPermSize=256m" to the CATALINA_OPTS
environment variable before starting Tomcat.

<a name="Tomcat-OtherIssues"></a>
## Other Issues

If you are having problems with the installation, please send a message to
the OpenEJB users [mailing list](openejb:mailing-lists.html)
 containing any error message(s) and the following information:

* OpenEJB Version
* Tomcat Version
* Java Version (execute java -version)
* Operating System Type and Version


<a name="Tomcat-Limitations"></a>
# Limitations

 *JavaAgent* - OpenEJB uses OpenJPA to provide JPA and CMP persistence, and
OpenJPA currently requires a JavaAgent to function properly.  This
requirement is something that the OpenJPA project is working on removing. 
Once removed, the OpenEJB plugin for Tomcat will no longer need to modify
the startup shell scripts and you will not need to restart Tomcat after the
OpenEJB installation.

<a name="Tomcat-Misc"></a>
# Misc

This document is a starting point for using OpenEJB in Tomcat and will
evolve based on user contributions. If you wish to contribute to this
document, feel very welcome to click the 'Edit' link in the lower right and
make changes and add new HOWTO's and other docs.  

<a name="Tomcat-JSFInjectionSupport"></a>
# JSF Injection Support
Now you can inject EJB's into JSF managed beans. Currently we have tested
with JSF 1.2 RI (Mojarra) and MyFaces v1.2.3 . We would definitely
appreciate any feedback on other JSF implementations. 
