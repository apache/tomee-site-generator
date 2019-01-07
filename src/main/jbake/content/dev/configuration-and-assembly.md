Title: Configuration and Assembly

Disclaimer that we do tweak and change this code frequently, without
notice.  It is the very heart of OpenEJB.  To keep things tight and clean,
we reserve the right to change it at anytime.  Do not consider it a stable
public API.

<a name="ConfigurationandAssembly-OverviewinCode"></a>
# Overview in Code

First a glimpse of how OpenEJB looks internally.  Here's a test that builds
OpenEJB using it's internal API.  This is somewhat similar to how you might
see people constructing Jetty in code.	All our internal tests look like
this.

This usage involves no xml parsing or classpath scanning.  If you don't
give it to OpenEJB, OpenEJB doesn't know about it.  This is OpenEJB with
all the magic stripped away.  At a high level:

1. You build your app in code using the JAXB tree in code and hand it to the `ConfigurationFactory`.
    1. The `org.apache.openejb.jee` package contains JAXB trees for ejb-jar.xml, beans.xml and all the Java EE deployment descriptors.
1. The `ConfigurationFactory` will produce a fully canonical version of the app called the `Info` tree by:
    1. Merging all sources of meta-data -- xml and annotations
    1. Resolving all ejb, persistence unit, datasource and other references
    1. Validating the app and looking for mistakes
1. The `Info` tree is
    1. The singular source of information about the application from this point forward.
    1. Pure data with no smarts or logic of any kind.
    1. The instruction set of what would be built by the assembler.
1. The `Assembler` will build and start the application exactly as described in the `Info` tree.
    1. When this step completes, you have a running application.
    1. Any failures prior to this point require no cleanup.  Only the assembler builds "live" objects.

An example of what this looks like in code

    import javax.ejb.LocalBean;
    import javax.ejb.Stateful;
    import javax.naming.InitialContext;

    import junit.framework.TestCase;
    import org.apache.openejb.assembler.classic.Assembler;
    import org.apache.openejb.assembler.classic.SecurityServiceInfo;
    import org.apache.openejb.assembler.classic.TransactionServiceInfo;
    import org.apache.openejb.client.LocalInitialContextFactory;
    import org.apache.openejb.config.ConfigurationFactory;
    import org.apache.openejb.jee.EjbJar;
    import org.apache.openejb.jee.StatefulBean;

    public class StatefulTest extends TestCase {

        @Override
        protected void setUp() throws Exception {

            System.setProperty(javax.naming.Context.INITIAL_CONTEXT_FACTORY, LocalInitialContextFactory.class.getName());

            ConfigurationFactory config = new ConfigurationFactory();
            Assembler assembler = new Assembler();

            assembler.createTransactionManager(config.configureService(TransactionServiceInfo.class));
            assembler.createSecurityService(config.configureService(SecurityServiceInfo.class));

            EjbJar ejbJar = new EjbJar();
            ejbJar.addEnterpriseBean(new StatefulBean(MyBean.class));

            assembler.createApplication(config.configureApplication(ejbJar));
        }

        public void test() throws Exception {
            InitialContext context = new InitialContext();
            MyBean myBean = (MyBean) context.lookup("MyBeanLocalBean");

            assertEquals("pan", myBean.echo("nap"));
        }

        @Stateful
        @LocalBean
        public static class MyBean {

            public String echo(String string) {
                StringBuilder sb = new StringBuilder(string);
                return sb.reverse().toString();
            }
        }
    }


<a name="ConfigurationandAssembly-LogicalOverview"></a>
# Logical Overview

Slightly more detailed account of the above.  Our startup and deploy world
is broken into two phases:
  
  1. configuration (app.jar -> AppInfo)  we build up a fully normalized and validated tree.  Some of the steps are
       - read in descriptors
       - process annotations filling in the descriptor tree
       - validating app compliance
       - resolving resource references
       - resolving ejb references
       - turning the descriptor tree into Info objects for final assembly
       - final validation check

  2. assembly (AppInfo -> actual running app)  we assemble a running app as detailed by the AppInfo
       - creating classloaders for the application
       - creating EntityManagers and EntityManagerFactories
       - creating live objects associated with resource-env-refs
       - creating deployment (CoreDeploymentInfo) objects for each ejb
       - creating the jndi enc of each ejb
       - adding method permission objects into the security system (JACC Provider)
       - creating transaction policy objects for each ejb
       - creating interceptor stacks and bindings for each ejb
       - adding ejbs to containers (which may also do things like create pools)
       - adding ejbs to the live ContainerSystem registry of ejbs
       - adding global jndi entries for each ejb



The listings above aren't necesarrily complete or perfectly ordered, but
generally show the nature of the work done in each phase.  

<a name="ConfigurationandAssembly-ConfigurationPhase"></a>
## Configuration Phase

A goal is that nothing gets through configuration and into assembly if it
can't actually be built.  The configuration phase is where we're supposed
to wipe away any ambiguity, fully normalize the app, make sure it's
internally consistent, spec compliant and generally good to go.  If it's
not, no worries as we actually haven't built anything permanent yet. 
Everything in the configuration phase is temporary.  If it fails the
configuration phase we just issue an error and say "App will not be loaded"
and that's it, there's nothing to undo.  

<a name="ConfigurationandAssembly-InfoObjects-DatabetweenConfigurationandAssembly"></a>
## Info Objects - Data between Configuration and Assembly

The output of the configuration phase is what we call Info objects and the
root of that tree is OpenEjbConfiguration.  These objects are all simple,
serializable data types with no methods, no constructors and no code or
logic of any kind.  We even have a test that uses ASM to walk down the Info
tree and check that everything is compliant to these strict rules.

All of the aforementioned configuration phase sits behind this info object
tree and an interface that produces it:

 - org.apache.openejb.assembler.classic.OpenEjbConfiguration
 - org.apache.openejb.assembler.classic.OpenEjbConfigurationFactory

The job of the OpenEjbConfigurationFactory is simply to produce an 
OpenEjbConfiguration tree.  With this simple decoupling when the time comes
we can actually support much different styles of use/topologies.  For
example, a cluster scenario.  We could create an
OpenEjbConfigurationFactory implementation that actually pulled the
OpenEjbConfiguration from a central store or some sort of configuration
server of our creation.  Perhaps, someday we write an
OpenEjbConfigurationFactory implementation to wrap the existing one and
look for any changed files.  If nothing has changed since last boot, we
simple deserialize an OpenEjbConfiguration tree saved from a previous boot
as a way of reducing startup time on very large apps.

<a name="ConfigurationandAssembly-Assembly"></a>
## Assembly

The assembly phase is where real running things are actually built.  This
process is inherently ingrained in the details on how OpenEJB works
internally.  Keeping it separated from descriptor parsing, validation,
resolving, etc. keeps the actual "openejb building" code as simple as
possible.  It also allows for some flexibility and change to take place
architecturally with less chance of it rippling through the entire system. 
However it's also not so generic (like spring, etc.) that becomes very
difficult to get things built in a certain way or in a certain order
requiring you to jump through several hoops just to keep the generic system
as beautiful as possible.  It knows all the details on how to build each
individual part and in what order to build them. 

In OpenEJB, the Assembler is not supposed to be the gem of the project that
we keep clean, motivating us to push complex things out into other areas
for other people (usually users) to worry about.  In fact, it's the
opposite.  The runtime system gets top priority on it's architectural needs
and the assembler gets last priority.  If there's something we can do in
the Assembler that saves the rest of the system from complexity, we gladly
throw the Assembler on that grenade.  Our philosophy is that you can't make
100% of your system "perfect" all the time and sometime the mess has to go
somewhere.  The assembler is where.  It's purposely not over architected so
that it can continue to serve as a place to take up slack and not make all
this stuff harder than it has to be.

