Title: Building from source
<a name="Buildingfromsource-Buildingfromsource"></a>
# Building from source

<a name="Buildingfromsource-Checkingoutthesource"></a>
### Checking out the source

To checkout the source, run this command with your subversion client.


      svn checkout
https://svn.apache.org/repos/asf/openejb/trunk/openejb-eclipse-plugin
openejb-eclipse-plugin


<a name="Buildingfromsource-Buildingthesource"></a>
### Building the source

To build the plugin you will need Maven (the build has been tested with
Maven 2.0.7). To run the build, issue this command

      mvn -Dassemble clean install


You should be aware that this will download any dependencies, including a
copy of Eclipse. This will take a while for your first build.

<a name="Buildingfromsource-ImportingtheplugincodeintoanEclipseworkspace"></a>
### Importing the plugin code into an Eclipse workspace

You can generate the Eclipse projects for the plugins by running the
following command


      mvn eclipse:clean eclipse:eclipse


You can add the M2_REPO classpath variable to your Eclipse workspace by
running the following command


      mvn -Declipse.workspace=<path-to-eclipse-workspace>
eclipse:add-maven-repo

