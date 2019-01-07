
        Release Notes - TomEE - Version 7.0.2

<h2>        Bug
</h2>
<ul>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1545'>TOMEE-1545</a>] -         Problem with parallel deployment + REST
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1778'>TOMEE-1778</a>] -         XAPoolTest fails because lock on XA log file cannot be acquired
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1851'>TOMEE-1851</a>] -         ear classloading can&#39;t be webapp first without a custom classloader
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1852'>TOMEE-1852</a>] -         JAXWS services without CDI can lead to NPE
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1856'>TOMEE-1856</a>] -         Empty log files from AsyncConsoleHandler
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1857'>TOMEE-1857</a>] -         Logger.getInstance leaks
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1858'>TOMEE-1858</a>] -         tomee.bat doesn&#39;t work
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1859'>TOMEE-1859</a>] -         support debug command on tomee.sh
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1860'>TOMEE-1860</a>] -         Potential NPE with DBCP if a shared connection is closed
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1862'>TOMEE-1862</a>] -         TomcatWebAppBuilder: no need to track contextXml inputstream
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1865'>TOMEE-1865</a>] -         NPE when injected request used in bean called from JASPIC SAM
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1866'>TOMEE-1866</a>] -         Authenticators don&#39;t have access to the request in CDI beans (@RequestScoped)
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1871'>TOMEE-1871</a>] -         deactivate tomee gui link since it is no more provided by default
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1875'>TOMEE-1875</a>] -         TomcatWebAppBuilder forces ConfigurationFactory impl
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1878'>TOMEE-1878</a>] -         AlternateDriver should be used only if datasource defines a classpath attribute
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1879'>TOMEE-1879</a>] -         DefinitionEncoding typo in Assembler
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1881'>TOMEE-1881</a>] -         org.apache.openejb.server.httpd.ServerServlet activated init param not respected
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1887'>TOMEE-1887</a>] -         Cipher command exception swalled
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1888'>TOMEE-1888</a>] -         tomcat context.xml datasource can be created twice in a different way when singleton
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1890'>TOMEE-1890</a>] -         arquillian remote tomee adapter should allow to configure options on the provider url (like timeout etc)
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1893'>TOMEE-1893</a>] -         JAXRS undeploys all contexts matching context prefix
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1894'>TOMEE-1894</a>] -         MvnResolver uses openejb.m2.home for settings location
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1896'>TOMEE-1896</a>] -         TomcatWebAppBuilder overwrites the last added event listener when the application doesn&#39;t use CDI
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1898'>TOMEE-1898</a>] -         NPE in PojoEndpoint.java:110 when deploying JAX-WS application
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1899'>TOMEE-1899</a>] -         deny-uncovered-http-methods not supported in web.xml
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1900'>TOMEE-1900</a>] -         XAConnection wrongly pooled when not fully handled in transaction (JPA case)
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1901'>TOMEE-1901</a>] -         @Inject not working for EJB module
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1902'>TOMEE-1902</a>] -         [plume] NPE when CDI is not used
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1903'>TOMEE-1903</a>] -         JTA component lookup fails in CDI extensions
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1904'>TOMEE-1904</a>] -         allow to sort cdi extensions
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1907'>TOMEE-1907</a>] -         tomee arquillian enricher shouldn&#39;t log a warning for junit rules/statements
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1909'>TOMEE-1909</a>] -         CDI not available for JSP
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1911'>TOMEE-1911</a>] -         gradle plugin broken with gradle 3 and gradle wrapper
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1913'>TOMEE-1913</a>] -         CDI.current().getBeanManager() returns BeanManager of another webapp
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1922'>TOMEE-1922</a>] -         openejb-http doesn&#39;t support multiple header values
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1923'>TOMEE-1923</a>] -         @WebXXX ignored with antijarlocking activated
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1925'>TOMEE-1925</a>] -         WebContext#isWeb doesn&#39;t contain all web artifacts
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1928'>TOMEE-1928</a>] -         use src/main/webapp by default as webresource for TomEEEmbeddedSingleRunner
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1933'>TOMEE-1933</a>] -         InputStreamLeak in IO.java
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1935'>TOMEE-1935</a>] -         Leaked streams to files/classloader resources
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1936'>TOMEE-1936</a>] -         JMS message.getBody(String.class) is not working in MDB
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1940'>TOMEE-1940</a>] -         AbstractMethodError: org.apache.activemq.ActiveMQSession.createDurableConsumer(
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1941'>TOMEE-1941</a>] -         Logger.configure should also take into account openejb.logger.external
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1946'>TOMEE-1946</a>] -         TomEE relies on deafult locale
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1951'>TOMEE-1951</a>] -         BeanManager missing from javax.persistence.bean.manager entry in the map passed to JPA
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1952'>TOMEE-1952</a>] -         openejb.ejbd.authenticate-with-request hides authentication exception
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1960'>TOMEE-1960</a>] -         JarFiles are not closed
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1962'>TOMEE-1962</a>] -         deprecate org.apache.openejb.core.LocalInitialContextFactory
</li>
</ul>

<h2>        Dependency upgrade
</h2>
<ul>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1864'>TOMEE-1864</a>] -         tomcat 8.5.6
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1869'>TOMEE-1869</a>] -         upgrade xmlsec to 2.0.6
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1880'>TOMEE-1880</a>] -         johnzon 0.9.4
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1905'>TOMEE-1905</a>] -         ActiveMQ 5.14.1
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1906'>TOMEE-1906</a>] -         CXF 3.1.8
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1920'>TOMEE-1920</a>] -         Upgrade OpenWebBeans to 1.7.0
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1939'>TOMEE-1939</a>] -         myfaces 2.2.11
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1948'>TOMEE-1948</a>] -         batchee 0.4
</li>
</ul>

<h2>        Improvement
</h2>
<ul>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1855'>TOMEE-1855</a>] -         JSP can leak under no luck conditions
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1861'>TOMEE-1861</a>] -         org.apache.tomee.jul.formatter.AsyncConsoleHandler not configurable
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1870'>TOMEE-1870</a>] -         Don&#39;t scan jars if scan.xml is used with classes only
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1882'>TOMEE-1882</a>] -         tomee.sh/tomee.bat ignore tomcat boot classpat (tomcat-juli typically)
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1885'>TOMEE-1885</a>] -         Improve MyFaces default properties
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1886'>TOMEE-1886</a>] -         add tomee.embedded.add-callers flag to tomee embeddded to be able to skip add caller code
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1921'>TOMEE-1921</a>] -         [tomee embedded] support to load configuration from a properties file in the classpath
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1927'>TOMEE-1927</a>] -         Update Maven page for TomEE 7.x
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1929'>TOMEE-1929</a>] -         Do not unnecessarily allocate an array
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1931'>TOMEE-1931</a>] -         InputStreamLeak in CxfRsHttpListener
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1949'>TOMEE-1949</a>] -         remove ejb-jar.xml and openejb-jar.xml from openejb-core
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1950'>TOMEE-1950</a>] -         tomee embedded should support descriptors in classpath for --as-war
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1958'>TOMEE-1958</a>] -         Mockito doesn&#39;t @MockInjector to be set as container properties
</li>
</ul>

<h2>        New Feature
</h2>
<ul>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1810'>TOMEE-1810</a>] -         Default context should be &#39;/&#39; or ROOT if --context is not specified for embedded tomee
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1872'>TOMEE-1872</a>] -         Map tomee embedded Configuration to its main
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1873'>TOMEE-1873</a>] -         Add FatApp main which is designed for fatjar case and delegates to tomee embedded Main with fatjar config
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1883'>TOMEE-1883</a>] -         provide tomee embedded classpath scanner through an event
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1884'>TOMEE-1884</a>] -         provide a component to propagate command line and args of tomee embedded
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1889'>TOMEE-1889</a>] -         EJBd http client BASIC support
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1895'>TOMEE-1895</a>] -         support maven coordinates in classpaths element
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1914'>TOMEE-1914</a>] -         org.apache.openejb.util.ContainerClassesFilter ignores jaxws exclusions
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1915'>TOMEE-1915</a>] -         add --interactive=true or -i options to tomee embedded main to exist typing &quot;exit&quot;
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1916'>TOMEE-1916</a>] -         add conf option to tomee embedded configuration
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1917'>TOMEE-1917</a>] -         Provide means to bypass CountingInputStream/OutputStream
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1918'>TOMEE-1918</a>] -         propagate gradle plugin configuration to gradle extension
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1919'>TOMEE-1919</a>] -         gradle tomee embedde task configuration not working
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1926'>TOMEE-1926</a>] -         add Configurer hook to TomEEEmbeddedSingleRunner to configure the Configuration instance
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1938'>TOMEE-1938</a>] -         @JMSDestinationDefinition ignored
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1944'>TOMEE-1944</a>] -         add --classes-filter to tomee embedded
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1953'>TOMEE-1953</a>] -         TomEE Embedded Mojo should support additional web resource directories
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1957'>TOMEE-1957</a>] -         add @Args to tomee-embedded application runner
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1959'>TOMEE-1959</a>] -         add js/groovy customizers to tomee embedded maven plugin based on tomee standalone ones
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1964'>TOMEE-1964</a>] -         support to always return false for isSameResource on XADataSource
</li>
</ul>
