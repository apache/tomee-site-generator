Title: Creating itests
<a name="Creatingitests-OpenEJBitests"></a>
# OpenEJB itests

The OpenEJB itests module is a framework to create EJB test cases that are
designed according to the JUnit rules, i.e. they all have setUp, tests and
tearDown methods. Since it's JUnit-based, you can do whatever you could do
in JUnit.

This page describes the steps to create EJB test cases.

<a name="Creatingitests-Howitestswork"></a>
## How itests work

The itests module lives in OpenEJB's repository in the _modules\itests_ directory. Setting up the test environment to execute the itests is based on ([maven-itest-plugin-1.0 plugin](http://svn.apache.org/repos/asf/maven/maven-1/plugins-sandbox/trunk/itest/)
). 

Take a look at maven.xml in modules\itests directory. There you'll see that
the default goal is _ejb:install_, which in turn executes _itest_. When the
EJBs (todo: describe it a bit more) are done, the _itest:setup_ goal is
executed, which starts the real game. First, _org/openejb/Security_
configuration is started. Once it's done, _openejb-itests-xxx.jar_ is
deployed, which is _org/openejb/Itests_ configuration to be started,
afterwards. When the configurations are deployed and started, the
maven-itest-plugin executes junit (see [Ant JUnit task documentation](http://ant.apache.org/manual/OptionalTasks/junit.html)
 and project.properties of the itests module). The project.properties file
configures which itests are run and some other stuff.

The first itest test case is _org.openejb.test.entity.cmp.CmpTestSuite_.
Consult this for more information. Then the others defined in
_maven.itest.includes_ property are executed.

The order in which the itests are executed is important, so the first order
is set up via the maven.itest.includes property, then the test suites add
their tests in some order, and finally the method names in the test classes
put yet another order. So, be careful what name your test method name will
become. It may influence the order.

Some EJBs access database resources. It's even more important for CMPs. The
itests module uses the database as defined in the _openejb.test.database_
property. It's currently defined in the _project.properties_ file of the
module. You can change its value to whatever you wish using the Maven
property setting approaches (-D on the command line, project.properties,
build.properties in your home directory or the project you work in).

So, the last important information is how the junit tests access the server
resources - EJBs. It's done via executing session beans that in turn get at
the test EJBs, mostly CMPs. It's also possible that the CMP itests will be
accessed directly without having to pass on the call through a session
bean.

If itests are part of a larger project structure you can disable executing
it using the _maven.itest.skip_ property. Set it to _true_ and Maven won't
run the itests.

<a name="Creatingitests-SimpleCMP2.1itest"></a>
## Simple CMP 2.1 itest

<a name="Creatingitests-Databasesetup"></a>
### Database setup

The itests default database is Derby. The class -
_org.openejb.test.DerbyTestDatabase_ - is instantiated upon executing
_org.openejb.testTestManager.getDatabase()_ in each test case's _setUp()_
method. Remember, you can define any other database using the
_openejb.test.database_ property or do initialization of your own database
choice in the setUp() method.

The current implementation of database initialization is based on two
DerbyTestDatabse methods: _createCMP2Model()_ and _dropCMP2Model()_ that
create and drop database structure, accordingly.

<a name="Creatingitests-CMP2.1deployment"></a>
### CMP 2.1 deployment

{info:title=Information}
Unless specified, all directories are relative to _modules/itests_
directory and commands are executed in it.
{info}

A Maven project can produce one build artefact. It's very important to keep
in mind whenever your tests are to be based on a EJB that's not built by
default. The default EJBs are defined in
_modules/itests/src/ejb/META-INF/ejb-jar.xml_. The corresponding deployment
plan - the _openejb-jar.xml_ file is in
_modules/itests/src/ejb/META-INF/openejb-jar.xml_.

If you want to test your own EJB, you need to build it yourself, i.e.
describe the build and deployment in _modules/itests/maven.xml_ in the
pregoal of _itest:setup_.

In the following example, Ant's jar builds openejb-cmp2-petstore.jar file,
which in turn is distributed and started in Geronimo. The _id_ attribute of
_deploy:start_ is as specified in the module's deployment plan. See [Geronimo Deployment](http://wiki.apache.org/geronimo/Deployment)
 for more information about Geronimo deployment plans.


    <ant:jar destfile="${basedir}/target/openejb-cmp2-petstore.jar">
      <fileset dir="${basedir}/target/classes">
        <include name="**/cmp2/petstore/*.class"/>
        <include name="**/TestFailureException.class"/>
      </fileset>
      <metainf dir="${basedir}/src/cmp2/petstore" includes="*.xml"/>
    </ant:jar>
    <deploy:distribute
      uri="deployer:geronimo:jmx:rmi://localhost/jndi/rmi:/JMXConnector"
      username="system"
      password="manager"
      module="${basedir}/target/openejb-cmp2-petstore.jar"
    />
    <deploy:start
      uri="deployer:geronimo:jmx:rmi://localhost/jndi/rmi:/JMXConnector"
      username="system"
      password="manager"
      id="org/openejb/cmp2/petstore"/>


<a name="Creatingitests-Execution"></a>
### Execution

When EJB classes, deployment descriptor and plan, maven.xml are all set up,
it's time to execute your tests. In order to run itests you will run Maven
in _modules/itests_ directory.


    ~/openejb/modules/itests
    $ maven


It's also possible to override project properties and run only some test
cases.


    ~/openejb/modules/itests
    $ maven -Dmaven.itest.includes=**/Cmp2TestSuite.java


When a failure occurs, you should take a look at the result file of the
failed test suite in _target/itest-reports_, e.g.


    ~/openejb/modules/itests
    $ maven -Dmaven.itest.includes=**/Cmp2TestSuite.java -o
    ...
     [junit]
 Tests run: 113, Failures: 1, Errors: 0, Time elapsed: 22,132 sec
     [junit]
 [ERROR]
 TEST org.openejb.test.entity.cmp2.Cmp2TestSuite FAILED
    ...
    BUILD FAILED
    File...... C:\Documents and
Settings\root\.maven\cache\maven-itest-plugin-1.0\plugin.jelly
    Element... fail
    Line...... 166
    Column.... 64
    There were test failures.
    Total time: 2 minutes 3 seconds
    Finished at: Sun Jul 17 17:48:36 CEST 2005
    
    $ more
target/itest-reports/TEST-org.openejb.test.entity.cmp2.Cmp2TestSuite.txt
    Testsuite: org.openejb.test.entity.cmp2.Cmp2TestSuite
    Tests run: 113, Failures: 1, Errors: 0, Time elapsed: 22,132 sec
    
    Testcase: PetstoreTests.create: FAILED
    Received Exception class java.lang.NullPointerException : null
    junit.framework.AssertionFailedError: Received Exception class
java.lang.NullPointerException : null
    	at
org.openejb.test.entity.cmp2.PetstoreTests.test01_create(PetstoreTests.java:84)
    	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    	at
sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
    	at
sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
    	at
org.openejb.test.NumberedTestCase.runTestMethod(NumberedTestCase.java:195)
    	at
org.openejb.test.NumberedTestCase$3.protect(NumberedTestCase.java:169)
    	at org.openejb.test.NumberedTestCase.run(NumberedTestCase.java:172)
    	at org.openejb.test.NumberedTestCase.run(NumberedTestCase.java:141)
    	at org.openejb.test.TestSuite.run(TestSuite.java:71)
    ...


Complete execution log is in _target/openejb/var/log/openejb.log_ of the
itests module.

<a name="Creatingitests-RunningtheTestsinEclipse."></a>
#### Running the Tests in Eclipse.

The steps for running the iTests inside of Eclipse are given below. They
are

1) For Local Interface Tests, the class to be run is
_org.apache.openejb.iTest_.
2) For Remote Interface Tests, the class to be run is
_org.apache.openejb.RemoteiTest_.

In both the cases you need to give _'-Dopenejb.home=target/test-classes/'_
as a vm argument 
for the tests to run. 
