# Release Notes - TomEE - Version 1.7.2
                
<h2>Upgrades</h2>

<ul>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-1426">TOMEE-1426</a> xbean 4.1</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-1554">TOMEE-1554</a> Upgrade OpenJPA to 2.4.0</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-1391">TOMEE-1391</a> Use maven-filtering:1.2 to fix MSHARED-319 when compiling under JDK8</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-1405">TOMEE-1405</a> tomcat 7.0.62</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-1370">TOMEE-1370</a> symbolic links not supported by tomee for @WebXXX</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-1536">TOMEE-1536</a> xbean 4.2</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-1469">TOMEE-1469</a> cxf 2.6.16</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-1368">TOMEE-1368</a> upgrade arquillian-transaction-impl-base to 1.0.1.Final</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-1363">TOMEE-1363</a> myfaces 2.1.16</li>
</ul>

<h2>New Features</h2>

<ul>

<li><a href="https://issues.apache.org/jira/browse/TOMEE-1552">TOMEE-1552</a> add openejb.activemq.deploymentId-as-clientId flag to not force AMQ clientId to ejb deploymentId</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1444">TOMEE-1444</a> allow extensions through openejb extensions of cxf-rs</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1440">TOMEE-1440</a> support singleDeploymentByArchiveName in tomee arquillian adapters (remote/embedded)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1526">TOMEE-1526</a> global (conf/system.properties) openejb.datasource.pool support</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1421">TOMEE-1421</a> support persistence of javaagent config in tomee maven plugin</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1388">TOMEE-1388</a> add @JaxrsProviders to ApplicationComposers to add provider classes quickly on WebApp</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1420">TOMEE-1420</a> tomee-maven-plugin customizers configuration to be able to call java code to customize an instance</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1347">TOMEE-1347</a> allow to provide a default global properties provider for resources</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1473">TOMEE-1473</a> @SimpleLog for ApplicationComposer</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1472">TOMEE-1472</a> @RandomPort for applicationComposer</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1479">TOMEE-1479</a> HttpRequestImpl login backed by security service by default</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1487">TOMEE-1487</a> CDI Event based realm</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1483">TOMEE-1483</a> support destinationLookup activation spec with activemq</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1482">TOMEE-1482</a> add ability to log all CDI beans found (OWB scanner)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1446">TOMEE-1446</a> add event BeforeStartEjbs otherwise for timer beans it is surely too late if you want to modify it</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1451">TOMEE-1451</a> notify through BusCreated event when cxf bus is created</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1458">TOMEE-1458</a> provide cxf configuration support for @WebServiceRef</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1346">TOMEE-1346</a> support ciphered values in resources</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1345">TOMEE-1345</a> basic support of Tomcat embedded in tomee-embedded</li>
</ul>

<h2>Improvements</h2>

<ul>

<li><a href="https://issues.apache.org/jira/browse/TOMEE-1425">TOMEE-1425</a> better JMX naming for cxf jaxrs endpoint</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1434">TOMEE-1434</a> wire roles/users to tomee embedded arquillian adapter</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1430">TOMEE-1430</a> add support for users/roles for tomee embedded</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1392">TOMEE-1392</a> META-INF/org.apache.openejb.extension doesn't support multiple lines</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1439">TOMEE-1439</a> @Context SecurityContext doesn't use SecurityService</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1443">TOMEE-1443</a> support cxf.jaxws.wsFeatures</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1529">TOMEE-1529</a> BaseEjbProxyHandler#equals is super slow when parameter is not a proxy</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1411">TOMEE-1411</a> allow to create an application composer webapp using all inner classes of the test</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1530">TOMEE-1530</a> (arquillian) OpenEJBEnricher: if app context is null try to find it from classloader</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1374">TOMEE-1374</a> basic detection that container loader can't create a datasource and fallback on app one</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1373">TOMEE-1373</a> AlternativeDriver leaks when used (by default) from applications (resources.xml)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1372">TOMEE-1372</a> when trying to find persistence unit datasources ensure to try exact name first without required property constraint</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1371">TOMEE-1371</a> if using kahadb or leveldb or any persistence adapter force broker to be persistent</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1418">TOMEE-1418</a> Add Classpath discovery in REST annotations with virtual class path</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1387">TOMEE-1387</a> tomee embedded arquillian adapter doesn't delete temp folder as fast as it should/could</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1386">TOMEE-1386</a> skip org.apache.wink.common.internal. @Provider when using CXF</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1354">TOMEE-1354</a> Add 'openejb.deployer.binaries.use' automatically for arquillian test on remote machine</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1489">TOMEE-1489</a> Allow ActiveMQ scheduler activation when using kahadb persistence</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1367">TOMEE-1367</a> add singleDumpByArchiveName parameter to arquillian tomee adapters</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1486">TOMEE-1486</a> add to jaxws events close to jaxrs ones for consistency (ServerCreated/Destroyed)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1567">TOMEE-1567</a> allow to override persistence-unit properties from application.properties (as we already support system props)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1454">TOMEE-1454</a> add openejb.force-unit-type property to workaround 8.2.1.5 of JPA 2.0 spec</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1500">TOMEE-1500</a> MultiPulse bad URI event only fires once</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1466">TOMEE-1466</a> Apply WS-Security config (cxf interceptor) when use @WebService with javax.xml.ws.Service</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1463">TOMEE-1463</a> support WebServiceFeature for @WebServiceRef as well</li>
</ul>

<h2>Bugs</h2>

<ul>

<li><a href="https://issues.apache.org/jira/browse/TOMEE-1433">TOMEE-1433</a> Prevent error output on tomee:run<Enter></li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1553">TOMEE-1553</a> EJBContainerRunner broken with junit 4.12</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1551">TOMEE-1551</a> URLClassLoaderFirst uses its own lock and not classloader one</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1438">TOMEE-1438</a> cxf classloader doesn't delegate properly in equals/hashcode (lookup are broken in tomcat)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1435">TOMEE-1435</a> flushable datasources not destroyed</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1566">TOMEE-1566</a> [tomee-maven-plugin] Allow for name customizations for .rar apps</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1283">TOMEE-1283</a> old commons-lang3 dependency in lib folder</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1409">TOMEE-1409</a> Invalid configuration in module openejb-junit</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1408">TOMEE-1408</a> Incorrect assertions within the testcode</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1528">TOMEE-1528</a> add LogSqlPackages and openejb.log.sql.packages</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1407">TOMEE-1407</a> Invalid filtering configuration for assembly/openejb-standalone which causes a compile error</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1406">TOMEE-1406</a> Compile error in openejb-core due to an encoding issue</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1379">TOMEE-1379</a> TransactionSynchronizationRegistry not found in JNDI for EJB Timer started transactions</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1499">TOMEE-1499</a> connector modules are not destroyed properly</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1497">TOMEE-1497</a> NPE When deploy genericjmsra.rar </li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1495">TOMEE-1495</a> TomEE won't load Taglibs from WEB-INF/lib/</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1491">TOMEE-1491</a> add a LazyValve</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1490">TOMEE-1490</a> Arquillian Test and Local context.xml file not loading correctly</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1538">TOMEE-1538</a> ProvisioningUtil#fallback has replace String in wrong location, causing invalid path on Win platforms</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1535">TOMEE-1535</a> JAX-RS Subresource paths are chosen incorrectly</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1414">TOMEE-1414</a> @Jars works only with a single jar and not all matching jars</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1534">TOMEE-1534</a> in JAXRS ExceptionException are not always unwrapped</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1423">TOMEE-1423</a> Packaging contains duplicate artifacts with snapshot timestamp</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1389">TOMEE-1389</a> ApplicationComposer should try all possible BeanManager for injections</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1540">TOMEE-1540</a> tomee.sh doesn't support missing JAVA_HOME var</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1265">TOMEE-1265</a> Can not start tomee when using conf/catalina.policy</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1385">TOMEE-1385</a> in embedded mode with embedded JUL logger allow to change consoleHandlerClazz</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1384">TOMEE-1384</a> tolerate serialization of undeployed EJB</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1382">TOMEE-1382</a> allow to override LoaderService of openwebbeans by app in application.properties</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1381">TOMEE-1381</a> wrong property passed as ValidatorFactory to JPA</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1509">TOMEE-1509</a> PropertyPlaceHolderHelper does not work with cipher:</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1507">TOMEE-1507</a> openejb-rest leaks deployed apps</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1505">TOMEE-1505</a> shutdown cxf bus when exiting services (rs/ws)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1504">TOMEE-1504</a> undeploy doesn't always clean up correctly Deployments</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1348">TOMEE-1348</a> [JAXRS] ensure static resources are handled through servlet chain (jsp case for instance)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1468">TOMEE-1468</a> ApplicationComposer + CDI for a WebApp breaks startup</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1357">TOMEE-1357</a> tomee forces new StandardManager()</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1477">TOMEE-1477</a> TomEE wont start if added maven-properties to <args></li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1353">TOMEE-1353</a> [regression] jsonproviuder no more added </li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1352">TOMEE-1352</a> can't start if there is a space in tomee folder path</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1351">TOMEE-1351</a> jaxws doesn't support ROOT context</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1350">TOMEE-1350</a> arquillian class discovering algorithm should ignore test classes which can't be EJBs</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1071">TOMEE-1071</a> NoClassDefFoundError - SVGDocument</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1519">TOMEE-1519</a> SetupCommand fails to delete file/dir on Windows</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1513">TOMEE-1513</a> catalina.sh does not quote javaagent argument correctly</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1359">TOMEE-1359</a> TomEEInjectionEnricher does not always return correct AppContext</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1358">TOMEE-1358</a> openejb.additional.include not respected</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1521">TOMEE-1521</a> Duplicate App Deployment when autoDeploy="true"</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1400">TOMEE-1400</a> Potential NPE in TomeeAnnotationProvider</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1520">TOMEE-1520</a> A service as a singleton is not working</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1366">TOMEE-1366</a> UnsupportedOperationException in CxfEndpoint.doServiceCreate</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1364">TOMEE-1364</a> When using the tomee-maven-plugins stop goal tomee seems to hang forever</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1362">TOMEE-1362</a> WsRsTest is not correct</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1481">TOMEE-1481</a> web-fragment.xml FacesServlet declaration not supported</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1480">TOMEE-1480</a> make possibility to provide conf.d-files for arquillian-tomee-remote adapter</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1449">TOMEE-1449</a> allow to deploy twice (several hosts) the same app</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1569">TOMEE-1569</a> openejb Logger (util package) should use container loader to create JUL loggers</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1448">TOMEE-1448</a> container CDI classes shouldn't be filtered from CDI context</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1568">TOMEE-1568</a> support overriding of a failed deployment in tomcat webappdeployer</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1333">TOMEE-1333</a> NPE in releasing deployed artifacts with ArquillianSuiteExtension</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1452">TOMEE-1452</a> reloading doesn't work with jaxws services for ears</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1450">TOMEE-1450</a> Unable to shutdown with whitespace in path because of javaagent error</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1570">TOMEE-1570</a> OpenEJBLogRecord misses logger name</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1457">TOMEE-1457</a> OpenEJBLoginValidator shouldn't associate if in tomee</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1467">TOMEE-1467</a> embedded http layer doesn't support repeated query parameters</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1344">TOMEE-1344</a> URLClassLoader are not closed during scanning and with app undeployment</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1583">TOMEE-1583</a> ProvisioningUtil fails to check for alternate repository location</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1462">TOMEE-1462</a> ApplicationComposer hides assert errors</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1460">TOMEE-1460</a> deploying cxf webservice in host other than localhost</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1580">TOMEE-1580</a> Datasource JNDI Name Context not available to eclipselink non jta data source</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-2106">OPENEJB-2106</a> Invalid schema location in test xml</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-2112">OPENEJB-2112</a> 'Tweaks' to a (native) query are lost when running without a tx</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-2111">OPENEJB-2111</a> Unchecked application exceptions, thrown in asynchronous business methods, are always wrapped in EJBException</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-2109">OPENEJB-2109</a> Call to ThreadContext.enter and ThreadContext.exit is uneven in EjbObjectProxyHandler.businessMethod</li>
</ul>

<h2>Tasks & Sub-Tasks</h2>

<ul>



<li><a href="https://issues.apache.org/jira/browse/TOMEE-1410">TOMEE-1410</a> Cleanup poms to update the filtering of EXE files.</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-1356">TOMEE-1356</a> tomcat resources are not always well created and can return a Context instead of the real instance</li>

</ul>