Title: iTests Overview
<a name="iTestsOverview-Motivation"></a>
#  Motivation

The basic idea behind itests that makes them slightly different than plain
junit tests ran by maven is that it's assumed that there are other systems
(servers, databases, brokers, etc) that need to be started or connected to
before the tests can run.  The tests themselves don't setup the other
systems themselves so that the tests don't become coupled with them and you
can actually swap out various aspects of those systems behind the back of
the tests; server version, protocol implementation, java version, VM
implementation, database provider, operating systems, even using embedded
servers and embedded databases.

The idea came from the tests in OpenEJB that do this.  It took a lot of
work to get them to run as part of the maven build and the itest plugin was
the result.

Definitely give those a look at: 
http://svn.apache.org/viewvc/tomee/tomee/trunk/itests/

<a name="iTestsOverview-Pluggingin"></a>
#  Plugging in

You can have systems started and stopped before and after the tests in a
number of ways.

<a name="iTestsOverview-Inmaven1"></a>
##  In maven 1

This series of articles established a basic technique for Container Driven
Testing with Maven and OpenEJB using preGoals and postGoals to do server
and databse setup and teardown before and after Maven runs the unit tests.  [http://www.theserverside.com/articles/article.tss?l=ContainerDrivenTestingSeries](http://www.theserverside.com/articles/article.tss?l=ContainerDrivenTestingSeries)

This concept evolved from OpenEJB into a reusable maven plugin created by
Dain Sundstrom that gave these integration tests (itests) their own source
directory and added itest:setup and itest:teardown goals to your maven
build rather than just using a preGoal on "test:test" for setup and a
postGoal on "test:test" for teardown.  The advantage to the new goals is
that maven does not call any postGoals on "test:test" if the tests failed;
something the above series of articles hacked around with an odd bit of
jelly code.

The new itest plugin was checked into Geronimo with the expectation that
Geronimo would also use it.  After some time of OpenEJB still being the
only consumer of it, it was moved into the Maven repository where they keep
it in the Maven sandbox. 

 [http://maven.apache.org/plugins-sandbox/itest/](http://maven.apache.org/plugins-sandbox/itest/)

ActiveMQ later started using the itest plugin and now has a large suite of
their own JMS specific integration tests.

<a name="iTestsOverview-Inmaven2"></a>
##  In maven 2

The concept of integration tests (itests) has been built into the lifecycle
of maven 2, so it is no longer required to have the itest plugin around to
get the extra "itest:setup" and "itest:teardown" goals.  See this document
for more info:  [http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)

<a name="iTestsOverview-Injunitsetupandteardown"></a>
##  In junit setup and teardown

The ejb tests in particular require you to plug in facades for the server
and the database so the client (the tests) can say "give me an initial
context" or "create these tables i need" in a generic way.  It's assumed
that those systems were setup and guaranteed in working state in the itest
setup phase.  It's also guaranteed that the server and database facades
know how to contact the server or database to carry out the "give me an
initial context" and "create these tables" calls.

Here are some implementations of the database provider for reference:
  -
http://cvs.openejb.org/viewrep/openejb/openejb/modules/itests/src/itest/org/openejb/test/AxionTestDatabase.java
  -
http://cvs.openejb.org/viewrep/openejb/openejb/modules/itests/src/itest/org/openejb/test/DerbyTestDatabase.java
  -
http://cvs.openejb.org/viewrep/openejb/openejb/modules/itests/src/itest/org/openejb/test/InstantDbTestDatabase.java
  -
http://cvs.openejb.org/viewrep/openejb/openejb/modules/itests/src/itest/org/openejb/test/PostgreSqlTestDatabase.java

Here are some implementations for the server facades:
  -
http://cvs.openejb.org/viewrep/openejb/openejb/modules/itests/src/itest/org/openejb/test/CorbaTestServer.java
  -
http://cvs.openejb.org/viewrep/openejb/openejb/modules/itests/src/itest/org/openejb/test/RemoteTestServer.java
  -
http://cvs.openejb.org/viewrep/openejb/openejb/modules/itests/src/itest/org/openejb/test/IvmTestServer.java
  -
http://cvs.openejb.org/viewrep/openejb/openejb/modules/itests/src/itest/org/openejb/test/RiTestServer.java

Using the itest concept you could setup any system you need before hand,
then just provide an abstraction of that system to the actual tests.  So
it's not limited to just a server or a database.  You could do queue,
topic, clustering heartbeat agent, directory server, etc.

<a name="iTestsOverview-Testingmatrix"></a>
#  Testing matrix

Even with just what we have we can get quite far.  In a perfect world, we
would actually test against the full matrix:

  Server: Local, Database: Axion
  Server: Local, Database: Derby
  Server: Local, Database: PostgreSQL
  Server: Remote, Database: Axion
  Server: Remote, Database: Derby
  Server: Remote, Database: PostgreSQL
  Server: Corba, Database: Axion
  Server: Corba, Database: Derby
  Server: Corba, Database: PostgreSQL

We had that going for a short while years ago but it was just too much
infrastructure for me to keep running.	It would be nice to get Oracle and
MySQL in that list as well.

In an even more perfect world we'd test against not just different Server
and Database combinations, but JVM versions as well.

  Server: Local, Database: Axion, JVM: 1.3
  Server: Local, Database: Axion, JVM: 1.4
  Server: Local, Database: Axion, JVM: 1.5
  Server: Local, Database: Derby, JVM: 1.3
  Server: Local, Database: Derby, JVM: 1.4
  Server: Local, Database: Derby, JVM: 1.5
  Server: Local, Database: PostgreSQL, JVM: 1.3
  Server: Local, Database: PostgreSQL, JVM: 1.4
  Server: Local, Database: PostgreSQL, JVM: 1.5
  Server: Remote, Database: Axion, JVM: 1.3
  Server: Remote, Database: Axion, JVM: 1.4
  Server: Remote, Database: Axion, JVM: 1.5
  Server: Remote, Database: Derby, JVM: 1.3
  Server: Remote, Database: Derby, JVM: 1.4
  Server: Remote, Database: Derby, JVM: 1.5
  Server: Remote, Database: PostgreSQL, JVM: 1.3
  Server: Remote, Database: PostgreSQL, JVM: 1.4
  Server: Remote, Database: PostgreSQL, JVM: 1.5
  Server: Corba, Database: Axion, JVM: 1.3
  Server: Corba, Database: Axion, JVM: 1.4
  Server: Corba, Database: Axion, JVM: 1.5
  Server: Corba, Database: Derby, JVM: 1.3
  Server: Corba, Database: Derby, JVM: 1.4
  Server: Corba, Database: Derby, JVM: 1.5
  Server: Corba, Database: PostgreSQL, JVM: 1.3
  Server: Corba, Database: PostgreSQL, JVM: 1.4
  Server: Corba, Database: PostgreSQL, JVM: 1.5

If you add JVM vendors (Sun, IBM, Apple, BEA) to the list, the combinations
goes up to like 109.  Throw on OS implementations and you get an insane
number of test runs to complete.
