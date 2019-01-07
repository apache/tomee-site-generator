Title: Tomcat Installation
{composition-setup}
{composition-setup}

<a name="TomcatInstallation-Overview"></a>
# Overview

Tomcat installation is very simple, and can be describes as "Unpack and
Run".  These instructions were written using Tomcat 6.0.14 but any recent
6.x version should work.  If you are comfortable with the CLI, these the
following quick instructions will get you going ASAP; otherwise skip to the [OPENEJB:Download Tomcat](#download.html)
 section.

1. Download Tomcat zip or tar.gz
1. Unpack archive
1. Platform specific setup
** \[OPENEJB:Unix\](openejb:unix\.html)
 If zip was unpacked, *chmod u+x bin/*.sh*
** \[OPENEJB:Windows\](openejb:windows\.html)
 *set JAVA_HOME =C:\your\java\installation* 
1. Run bin/startup.sh or bin/startup.bat
1. Visit http://localhost:8080/
1. Run bin/shutdown.sh or bin/shutdown.bat

<a name="TomcatInstallation-{anchor:download}DownloadTomcat"></a>
# {anchor:download} Download Tomcat

Download Tomcat 6 zip file from [here](http://tomcat.apache.org/download-60.cgi#6.0.14)
.    

<a name="TomcatInstallation-UnpackTomcat"></a>
# Unpack Tomcat

Unpack the Tomcat zip file  which will create a new directory containing
the complete Tomcat installation.

{deck:id=unpack tomcat}
{card:label=Windows}{noformat:nopanel=true}
C:\>jar -xvf apache-tomcat-6.0.14.zip
  created: apache-tomcat-6.0.14/
  created: apache-tomcat-6.0.14/bin/
  created: apache-tomcat-6.0.14/conf/
...snip...

C:\>dir apache-tomcat-6.0.14
 Volume in drive C has no label.
 Volume Serial Number is 0000-0000

 Directory of C:\apache-tomcat-6.0.14

09/20/2007  09:14 PM	<DIR>	       .
09/20/2007  09:14 PM	<DIR>	       ..
09/20/2007  09:15 PM	<DIR>	       bin
09/20/2007  09:15 PM	<DIR>	       conf
09/20/2007  09:15 PM	<DIR>	       lib
07/20/2007  04:20 AM		11,560 LICENSE
09/20/2007  09:14 PM	<DIR>	       logs
07/20/2007  04:20 AM		   556 NOTICE
07/20/2007  04:20 AM		 6,656 RELEASE-NOTES
07/20/2007  04:20 AM		 5,829 RUNNING.txt
09/20/2007  09:14 PM	<DIR>	       temp
09/20/2007  09:14 PM	<DIR>	       webapps
09/20/2007  09:14 PM	<DIR>	       work
	       4 File(s)	 24,601 bytes
	       9 Dir(s)   5,085,085,696 bytes free

    {card:label=Unix}{noformat:nopanel=true}
    $ jar -xvf apache-tomcat-6.0.14.zip 
      created: apache-tomcat-6.0.14/
      created: apache-tomcat-6.0.14/bin/
      created: apache-tomcat-6.0.14/conf/
    ...snip...
    
    $ ls apache-tomcat-6.0.14/  
    LICENSE        RELEASE-NOTES  bin/	     lib/	    temp/	  
work/
    NOTICE	       RUNNING.txt    conf/	     logs/	    webapps/

{deck}

# \[OPENEJB:Windows\](openejb:windows\.html)
 Set JAVA_HOME environment variable

For Windows users, the Tomcat shell scripts must know the location of the
Java installation, and this is done with environment variables.  The
following command will set the JAVA_HOME environment variable:

{deck:id=unpack tomcat}
{card:label=Windows}{noformat:nopanel=true}
C:\>set JAVA_HOME =C:\your\java\installation

    {deck}
    
# \[OPENEJB:Unix\]
 Make shell scripts executable
    
    For Unix users, the shell scripts in the Tomcat installation are not
executable by default, so in order to execute them, you must set mark them
as executable.	If you unpacked the Tomcat tar.gz file, the scripts are
already executable.  The following command will make all shell scripts
executable:
    
    {deck:id=unpack tomcat}
    {card:label=Unix}{noformat:nopanel=true}
    apache-tomcat-6.0.14$ chmod u+x bin/*.sh

{deck}

<a name="TomcatInstallation-StartTomcat"></a>
# Start Tomcat

Execute the following command to start the Tomcat server:

{deck:id=Start Tomcat}
{card:label=Windows}{noformat:nopanel=true}
C:\>cd apache-tomcat-6.0.14\bin

C:\apache-tomcat-6.0.14\bin>startup.bat
Using CATALINA_BASE:   C:\apache-tomcat-6.0.14
Using CATALINA_HOME:   C:\apache-tomcat-6.0.14
Using CATALINA_TMPDIR: C:\apache-tomcat-6.0.14\temp
Using JRE_HOME:        C:\your\java\installation

    {card:label=Unix}{noformat:nopanel=true}
    $ cd apache-tomcat-6.0.14/bin
    
    apache-tomcat-6.0.14/bin$ ./startup.sh 
    Using CATALINA_BASE:   /your/tomcat/installation/apache-tomcat-6.0.14
    Using CATALINA_HOME:   /your/tomcat/installation/apache-tomcat-6.0.14
    Using CATALINA_TMPDIR: /your/tomcat/installation/apache-tomcat-6.0.14/temp
    Using JRE_HOME:        /your/java/installation

{deck}

*NOTE:* Your output will be different from the example above due to
differences in installation location.

<a name="TomcatInstallation-VerifyTomcatisRunning"></a>
# Verify Tomcat is Running

Visit [http://localhost:8080/](http://localhost:8080/)
 and you should see the Tomcat welcome page.


<a name="TomcatInstallation-StopTomcat"></a>
# Stop Tomcat

Shutdown Tomcat by executing the following command:

{deck:id=Start Tomcat}
{card:label=Windows}{noformat:nopanel=true}
C:\apache-tomcat-6.0.14\bin>shutdown.bat
Using CATALINA_BASE:   C:\apache-tomcat-6.0.14
Using CATALINA_HOME:   C:\apache-tomcat-6.0.14
Using CATALINA_TMPDIR: C:\apache-tomcat-6.0.14\temp
Using JRE_HOME:        C:\your\java\installation

    {card:label=Unix}{noformat:nopanel=true}
    apache-tomcat-6.0.14/bin$ ./shutdown.sh 
    Using CATALINA_BASE:   /your/tomcat/installation/apache-tomcat-6.0.14
    Using CATALINA_HOME:   /your/tomcat/installation/apache-tomcat-6.0.14
    Using CATALINA_TMPDIR: /your/tomcat/installation/apache-tomcat-6.0.14/temp
    Using JRE_HOME:        /your/java/installation

{deck}

*NOTE:* Your output will be different from the example above due to
differences in installation locations.
