Title: Xbean usage in OpenEJB
<a name="XbeanusageinOpenEJB-HowXBeanisusedinOpenEJB"></a>
# How XBean is used in OpenEJB

Below is an explanation by David Blevins on the usage of xbean in OpenEJB. This text was taken from an email conversation. To view the full conversation, click&nbsp;[here](http://www.nabble.com/How-is-XBean-used-in-OpenEJB-3--tf2148639.html#a5959172)


<a name="XbeanusageinOpenEJB-xbean-reflect"></a>
## xbean-reflect

 xbean-reflect is a beefed up reflection library.

Earlier all pluggable components had an "init(Properties props)" method?
&nbsp;Same concept except now we throw the component class and the
properties into an "ObjectRecipe" and call create(). &nbsp;The recipe will
take the props out, convert them to the right data types,  and construct
the object using the right constructor and setters.

So our Containers and stuff now use constructors and setters. &nbsp;Same with anything in a &nbsp;[service-jar.xml](http://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/main/resources/META-INF/org.apache.openejb/service-jar.xml?view=markup)
 file.

<a name="XbeanusageinOpenEJB-Somecoderefs:"></a>
#### Some code refs:

1. [Assembler.java](http://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/main/java/org/apache/openejb/assembler/classic/Assembler.java?revision=546308&view=markup)
We also use it to construct Stateful and Stateless session bean instances.

1. [StatefulInstanceManager.java](http://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/main/java/org/apache/openejb/core/stateful/StatefulInstanceManager.java?revision=546308&view=markup)
1. [StatelessInstanceManager.java](http://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/main/java/org/apache/openejb/core/stateless/StatelessInstanceManager.java?revision=546308&view=markup)

<a name="XbeanusageinOpenEJB-xbean-finder"></a>
## xbean-finder

xbean-finder is the second coolest library ever. &nbsp;It's a beefed
up&nbsp; service finder for grabbing stuff in your classpath. &nbsp;We use
it at a couple of places.

<a name="XbeanusageinOpenEJB-COMMANDLINETOOL:"></a>
### COMMAND LINE TOOL:

The available commands are in properties files in
"META-INF/org.openejb.cli/\{name\}", where \{name\} is the name of the
command. &nbsp;See:
1. [openejb cli](http://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/main/resources/META-INF/org.apache.openejb.cli/)
1. [openejb cli for itests](http://svn.apache.org/viewvc/tomee/tomee/trunk/itests/openejb-itests-client/src/main/resources/META-INF/org.openejb.cli/)

Earlier we had the "test"&nbsp; command hardcoded in a script, but the
person may have uninstalled&nbsp; the itests? &nbsp;Well now, if you have
the itests jar, the "test" command&nbsp; will be available. &nbsp;If you
don't have the itests jar, the "test" &nbsp;
command won't be available. &nbsp;The "test" command itself is in the&nbsp;
itests jar. &nbsp;You can put any command in any jar and it will&nbsp;
automatically become available on the command line. &nbsp;Remove the
jar&nbsp; and the command is gone.

When someone types "java \-jar openejb.jar start" this guy will look&nbsp;
for "META-INF/org.openejb.cli/start". &nbsp;If he finds it, he'll
create&nbsp; it and execute it. &nbsp;If he doesn't find it, he'll list the
available&nbsp; commands by enumerating over all the files he see's in the
classpath&nbsp; under the "META-INF/org.openejb.cli/" directory. See [MainImpl.java](http://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/main/java/org/apache/openejb/cli/MainImpl.java?revision=546308&view=markup)

An extra cool thing is that each command has in it's properties a&nbsp;
"description" property. &nbsp;This is localized, so if the VM locale
is&nbsp; "pl" it will look for a "description.pl" property and use its
value&nbsp; when printing command line help.
I'd like to give Jeremy Whitlock a big shout-out for doing such a&nbsp;
bang up job on this. &nbsp;He and I worked out the idea and
white-boarded&nbsp; it in the wiki, then Jeremy went off and coded up the
whole thing\!&nbsp; It was fantastic.

<a name="XbeanusageinOpenEJB-SERVERSERVICES:"></a>
### SERVER SERVICES:

We also use the xbean-finder to create our Server Services (aka.&nbsp;
protocols). &nbsp;Our ServerService implementations are in properties&nbsp;
files under "META-INF/org.openejb.server.ServerService/\{protocolName\}.
See:
1. [OpenEJB Server - ServerService](http://svn.apache.org/viewvc/tomee/tomee/trunk/server/openejb-server/src/main/resources/META-INF/org.apache.openejb.server.ServerService/)
1. [OpenEJB ejbd - ServerService](http://svn.apache.org/viewvc/tomee/tomee/trunk/server/openejb-ejbd/src/main/resources/META-INF/org.apache.openejb.server.ServerService/)
1. [OpenEJB Telnet - ServerService](http://svn.apache.org/viewvc/tomee/tomee/trunk/server/openejb-telnet/src/main/resources/META-INF/org.apache.openejb.server.ServerService/)
1. [OpenEJB HTTP - ServerService](http://svn.apache.org/viewvc/tomee/tomee/trunk/server/openejb-http/src/main/resources/META-INF/org.apache.openejb.server.ServerService/)

The very first time a ServerService is constructed, we squirt the&nbsp;
properties file into the openejb/conf/ directory so the user can edit&nbsp;
it. &nbsp;The properties files for ServerServices are very xinet.d
like.&nbsp; For example, here is the definition of the "admin" server
service:

&nbsp; &nbsp; &nbsp;server &nbsp; &nbsp; &nbsp;=
org.openejb.server.admin.AdminDaemon
&nbsp; &nbsp; &nbsp;bind &nbsp; &nbsp; &nbsp; &nbsp;= 127.0.0.1
&nbsp; &nbsp; &nbsp;port &nbsp; &nbsp; &nbsp; &nbsp;= 4200
&nbsp; &nbsp; &nbsp;disabled &nbsp; &nbsp;= false
&nbsp; &nbsp; &nbsp;threads &nbsp; &nbsp; = 1
&nbsp; &nbsp; &nbsp;only_from &nbsp; = localhost

You can reconfigure the "admin" server service, for example, via the&nbsp;
properties file in openejb/conf/admin.properties. &nbsp;Or you can do
it&nbsp; on the command line as such:

<in-a-shell>
$ ./bin/openejb start \-Dadmin.bind=192.168.42.13
OPENEJB_HOME=/Users/dblevins/work/openejb1/target/openejb-1.1-SNAPSHOT
OpenEJB 1.1-SNAPSHOT &nbsp; &nbsp;build: 20060420-2356
[http://www.openejb.org](http://www.openejb.org)
resources 1
OpenEJB ready.
\[init\](init\.html)
 OpenEJB Remote Server
&nbsp; &nbsp;*\* Starting Services \*\*
&nbsp; &nbsp;NAME &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
IP &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;PORT
&nbsp; &nbsp;webadmin &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 127.0.0.1
&nbsp; &nbsp; &nbsp; 4203
&nbsp; &nbsp;httpejbd &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 127.0.0.1
&nbsp; &nbsp; &nbsp; 4204
&nbsp; &nbsp;telnet &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
127.0.0.1 &nbsp; &nbsp; &nbsp; 4202
&nbsp; &nbsp;ejbd &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
127.0.0.1 &nbsp; &nbsp; &nbsp; 4201
&nbsp; &nbsp;admin &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
&nbsp;192.168.42.13 &nbsp; 4200
\------\-
Ready\!
</in-a-shell>

You can override any server service property in the same way.
&nbsp;Here&nbsp; are a bunch more examples:

&nbsp; Option: \-D<service>.bind=<address>
&nbsp; &nbsp; openejb start \-Dejbd.bind=10.45.67.8
&nbsp; &nbsp; openejb start \-Dejbd.bind=myhost.foo.com
&nbsp; &nbsp; openejb start \-Dtelnet.bind=myhost.foo.com

&nbsp; Option: \-D<service>.port=<port>
&nbsp; &nbsp; openejb start \-Dejbd.port=8765
&nbsp; &nbsp; &nbsp;openejb start \-Dhttpejbd.port=8888

&nbsp; Option: \-D<service>.only_from=<addresses>
&nbsp; &nbsp; &nbsp;openejb start \-Dadmin.only_from=192.168.1.12
&nbsp; &nbsp; &nbsp;openejb start
\-Dadmin.only_from=192.168.1.12,joe.foo.com,robert

&nbsp; Option: \-D<service>.threads=<max>
&nbsp; &nbsp; &nbsp;openejb start \-Dejbd.threads=200

&nbsp; Option: \-D<service>.disabled=<true/false>
&nbsp; &nbsp; &nbsp;openejb start \-Dtelnet.disabled=true

