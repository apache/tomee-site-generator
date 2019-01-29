
        Release Notes - TomEE - Version 7.0.0-M2

<h2>        Bug
</h2>
<ul>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1640'>TOMEE-1640</a>] -         TomEE should &quot;scan&quot; a possible CDI beans if a NoClassDefFoundError occurs before registering it
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1644'>TOMEE-1644</a>] -         synchronization ignored for entity managers using extended contexts
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1645'>TOMEE-1645</a>] -         tomee.sh ignored common.loader
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1646'>TOMEE-1646</a>] -         tomee.sh cipher swallows exceptions
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1649'>TOMEE-1649</a>] -         Websockets Memory Leak
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1655'>TOMEE-1655</a>] -         ApplicationComposers not isolating @Configuration for each test class.
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1663'>TOMEE-1663</a>] -         org.apache.openejb.assembler.classic.Assembler#destroyResourceTree doesnt detect resource adapter properly, can lead to bad connection factory shutdown
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1672'>TOMEE-1672</a>] -         user transaction not accessible during startup in webapps
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1681'>TOMEE-1681</a>] -         Upgrade &#39;&lt;cxf.version&gt;&#39; property in openejb.pom to 3.1.3
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1685'>TOMEE-1685</a>] -         Persistence and PersistenceUnit shouldnt be counted as module and lead webapps to be considered as ear in application composer
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1686'>TOMEE-1686</a>] -         org.apache.openejb.core.cmp.CmpContainer#findEJBObject supposes args array is not empty
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1687'>TOMEE-1687</a>] -         ServletContext not accesible during ApplicationScoped Initialized event
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1689'>TOMEE-1689</a>] -         arquillian tomee remote can miss test classes in webapp of ears
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1695'>TOMEE-1695</a>] -         ManagedExecutorService not propagating a request.login() when used in a servlet
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1696'>TOMEE-1696</a>] -         Lazy resources can use app classloader instead of container loader
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1698'>TOMEE-1698</a>] -         BeanManager no more set in ServletContext attributes
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1699'>TOMEE-1699</a>] -         [tomee-maven-plugin] dont quote systemVariables
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1702'>TOMEE-1702</a>] -         BaseEjbProxyHandler live proxy registry can leak for cmp beans
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1703'>TOMEE-1703</a>] -         finder not available for ear webapp making ServletcContextInitializer broken
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1704'>TOMEE-1704</a>] -         makes active config property override working and support placeholders
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1705'>TOMEE-1705</a>] -         Destroy application attempts to initialize lazily loaded resources
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1706'>TOMEE-1706</a>] -         Standalone WAR (autoWar) gets not deployed at / (ROOT)
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1710'>TOMEE-1710</a>] -         resources.xml resource ClassCastException
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1711'>TOMEE-1711</a>] -         cxf-rs doesn&#39;t work in embedded mode if request is wrapped in HttpServletRequestWrapper
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1713'>TOMEE-1713</a>] -         ensure OpenWebBeans services can be extended using application.properties
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1721'>TOMEE-1721</a>] -         no module (webapp here) webservices can lead to NPE
</li>
</ul>

<h2>        Dependency upgrade
</h2>
<ul>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1633'>TOMEE-1633</a>] -         upgrade javamail to 1.9.0-alpha-2
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1659'>TOMEE-1659</a>] -         upgrade to mojarra 2.2.9
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1670'>TOMEE-1670</a>] -         xbean 4.5
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1674'>TOMEE-1674</a>] -         tomcat 8.0.32
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1676'>TOMEE-1676</a>] -         ActiveMQ 5.13
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1679'>TOMEE-1679</a>] -         myfaces 2.2.9
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1680'>TOMEE-1680</a>] -         mojarra 2.2.12
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1684'>TOMEE-1684</a>] -         CXF 3.1.5
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1700'>TOMEE-1700</a>] -         upgrade Johnzon to 0.9.3
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1707'>TOMEE-1707</a>] -         bval 1.1.1
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1716'>TOMEE-1716</a>] -         openjpa 2.4.1
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1717'>TOMEE-1717</a>] -         OpenWebBeans 1.6.3
</li>
</ul>

<h2>        Improvement
</h2>
<ul>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1642'>TOMEE-1642</a>] -         Would be nice that tomee logs JAXRS configuration in use (was: sends an INFO when not found the relative class for pojo-deployment in configuration [openejb-jar.xml])
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1643'>TOMEE-1643</a>] -         XADataSource can leak connections/skip the pool
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1650'>TOMEE-1650</a>] -         ignore tomee webapp and internal application by default
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1656'>TOMEE-1656</a>] -         {jaxrs provider qualifier name}.activated ignored for mandatory providers
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1657'>TOMEE-1657</a>] -         skip ValidationExceptionMapper if the user registers one
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1694'>TOMEE-1694</a>] -         remove workaround for websockets CDI releasing since tomcat cleans server endpoints
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1708'>TOMEE-1708</a>] -         [arquillian] use configured temp dir (arquillian.xml) instead of target to download tomee
</li>
</ul>

<h2>        New Feature
</h2>
<ul>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1651'>TOMEE-1651</a>] -         support char[] password decryption (not String)
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1652'>TOMEE-1652</a>] -         add cdiStereotypes() to @Classes in ApplicationComposer API
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1653'>TOMEE-1653</a>] -         add hooks in tomee:exec runner
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1660'>TOMEE-1660</a>] -         tomee embedded should support web resource cache configuration
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1667'>TOMEE-1667</a>] -         add published-url in cxf.jax*. properties in openejb-jar.xml
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1668'>TOMEE-1668</a>] -         add objectName configuration to @MBean
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1675'>TOMEE-1675</a>] -         TomEE embedded EJBContainer implementation ignores container properties
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1682'>TOMEE-1682</a>] -         support tomee archives without a root folder in tomee maven plugin
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1690'>TOMEE-1690</a>] -         [tomee maven plugin] add jsCustomizers and groovyCustomizers option
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1701'>TOMEE-1701</a>] -         add a single instance ApplicationComposer (SingleApplicationComposerRunner)
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1709'>TOMEE-1709</a>] -         [tomee-embedded-maven-plugin] LiveReload integration
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1714'>TOMEE-1714</a>] -         add TomEEProxyHandler property in datasources to support custom proxying
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1715'>TOMEE-1715</a>] -         basic part support in openejb-http (embedded)
</li>
</ul>
