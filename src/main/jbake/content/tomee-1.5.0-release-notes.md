Title: Apache TomEE 1.5.0 Release Notes

<h2>Upgrades</h2>

<ul>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-243">TOMEE-243</a> HSQLDB 2.2.8</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-431">TOMEE-431</a> Shrinkwrap Descriptor 2.0.0-alpha-3</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-378">TOMEE-378</a> Quartz 2.1.6</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-271">TOMEE-271</a> Tomcat 7.0.30</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-365">TOMEE-365</a> OpenWebBeans 1.1.6</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-426">TOMEE-426</a> ActiveMQ 5.6.0</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-428">TOMEE-428</a> MyFaces 2.1.9</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-215">TOMEE-215</a> CXF 2.6.2</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-214">TOMEE-214</a> Arquillian 1.0.1</li>
    <li><a href="https://issues.apache.org/jira/browse/TOMEE-458">TOMEE-458</a> BVal 0.5</li>
    <li><a href="https://issues.apache.org/jira/browse/OPENEJB-1894">OPENEJB-1894</a> karaf 2.2.9</li>
</ul>

<h2>New Features</h2>

<ul>

<li><a href="https://issues.apache.org/jira/browse/TOMEE-347">TOMEE-347</a> Allow switching datasource connection pool</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-331">TOMEE-331</a> Optimized scanning via exclusions.list in WEB-INF allows</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-266">TOMEE-266</a> Internal EJBs can be secured</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-264">TOMEE-264</a> Filter APIs we already provide if they are in a webapp</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-399">TOMEE-399</a> Expose Quartz through JMX</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-259">TOMEE-259</a> Mojarra integration</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-250">TOMEE-250</a> Element &lt;Service&gt; for declaring services generically in the tomee.xml</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-386">TOMEE-386</a> Support for META-INF/module.properties file and overriding</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-387">TOMEE-387</a> Support for META-INF/application.properties file and overriding</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-280">TOMEE-280</a> Deploy-time JPA Enhancement</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-187">TOMEE-187</a> New TomEE JAX-RS Distribution</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-377">TOMEE-377</a> Configuration of CXF Bus</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-421">TOMEE-421</a> JMX management of DataSources</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1228">OPENEJB-1228</a> ShrinkWrap Deployment Support</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1899">OPENEJB-1899</a> Mockito integration</li>
</ul>

<h2>Improvements</h2>

<ul>

<li><a href="https://issues.apache.org/jira/browse/TOMEE-235">TOMEE-235</a> ability to provide a custom server.xml using arquillian adapters</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-194">TOMEE-194</a> managing pathparam at class level</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-346">TOMEE-346</a> providing log4j in a webapp needs to set openejb.skip.log4j=false</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-191">TOMEE-191</a> can't use DataSourceRealm with a DataSource defined in tomee.xml</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-348">TOMEE-348</a> propagate all cxf endpoint properties to the SOAP endpoint</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-190">TOMEE-190</a> Duplicate Libraries in distribution</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-146">TOMEE-146</a> Trim unused code</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-241">TOMEE-241</a> close webappclasslaoder after undeployment</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-336">TOMEE-336</a> ability to skip dbcp pool for @DataSourceDefinition</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-130">TOMEE-130</a> Improve openejb webapp console to match current site look and feel</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-230">TOMEE-230</a> tomee.sh uses old lib folder</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-332">TOMEE-332</a> yank tomee version from arquillian adapter config and use LATEST</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-330">TOMEE-330</a> more relevant error message when using tomee < tomee+ and webservice injection are done</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-269">TOMEE-269</a> use a maven specific logger when running tomee:run from tomee maven plugin</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-328">TOMEE-328</a> Re organise Arquillian sub modules versionning</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-4">TOMEE-4</a> Example demonstrating Arquillian Adapter</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-262">TOMEE-262</a> since TOMEE-261 is done we can check the needed JtaPlatform for hibernate is in the webapp</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-260">TOMEE-260</a> Validate: REST Service has no non-public resources</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-398">TOMEE-398</a> Unified Executor configuration options (@Asynchronous, @Timeout)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-397">TOMEE-397</a> META-INF/resources.xml always needs qualified names</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-391">TOMEE-391</a> config for retry attemps on timer methods</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-257">TOMEE-257</a> replace deployment listener and webdeployementlistener by our brand new observer api</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-251">TOMEE-251</a> extract jpa provide integration in a jar to be able to use it from a war</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-252">TOMEE-252</a> engine name needs to be Catalina</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-253">TOMEE-253</a> updatechecker doesn't handle tomee version properly</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-382">TOMEE-382</a> configuration for asynch task pool</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-385">TOMEE-385</a> Complete application properties scoping</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-205">TOMEE-205</a> Create the skeleton of the new Tomee UI </li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-208">TOMEE-208</a> add servlets to httpcontext (arquillian adapters)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-204">TOMEE-204</a> Don't fail app deployment if a TLD class cannot be loaded</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-203">TOMEE-203</a> Ensure all tomee-* jars and archives use the TomEE version number</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-406">TOMEE-406</a> Support Duration syntax in all known time related properties</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-282">TOMEE-282</a> logging tomee is downloaded from arquillian adapter</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-402">TOMEE-402</a> ScriptLoginModule</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-281">TOMEE-281</a> ignore endorsed lib for java 7</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-184">TOMEE-184</a> using cxf (and not our repackaged version)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-183">TOMEE-183</a> Optimize Arquillian Adapter by avoiding intermediate jar creation</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-189">TOMEE-189</a> EAR and CDI is not well integrated</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-372">TOMEE-372</a> don't load webapp enrichment classes directly from system classloader even if available</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-374">TOMEE-374</a> Embedded TomEE use same defaults as Embedded OpenEJB</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-41">TOMEE-41</a> Overzealous class scanning</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-274">TOMEE-274</a> allowing the user to override the folder where retrieved tomee are cache in arquillian adapters (by default m2 repo is not used to avoid to corrupt it)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-277">TOMEE-277</a> better model to be able to filter cdi beans</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-276">TOMEE-276</a> allow to define the same interceptor/decorator/alternative in multiple beans.xml</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-270">TOMEE-270</a> don't stop deployment during deployment if a noclassdeffound if thrown on a field</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-273">TOMEE-273</a> solder @Requires doesn't work</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-171">TOMEE-171</a> TomEE automatically directs embedded (@DataSourceDefinition) h2 datasource to hsqldb</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-279">TOMEE-279</a> using tomcat default host instead of hardcoded "localhost"</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-278">TOMEE-278</a> AnnotatedType can be null so dont put it in a map</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-220">TOMEE-220</a> revisit runnable tomee-embedded cli</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-361">TOMEE-361</a> skip JSF startup even if our internal faces-config.xml is found (but no more)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-224">TOMEE-224</a> Create Servlet that loads the "JNDI" panel data</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-418">TOMEE-418</a> ability to use fast session generation for dev environment in arquillian adapters (remote) + tomee mvn plugin</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-225">TOMEE-225</a> Create Servlet that loads the "Saved Objects" panel data</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-368">TOMEE-368</a> better handling of myfaces container listener</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-369">TOMEE-369</a> be sure to not exclude too much in tempclassloader (in particular with myfaces)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-366">TOMEE-366</a> delete temp file in arquillian tomee adapter even if deployer lookup fail</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-367">TOMEE-367</a> create webapp classloader even for embedded deployment</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-427">TOMEE-427</a> Shortcurt to debug with tomee maven plugin</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-23">TOMEE-23</a> Ignore .DS_Store files when deploying in Tomcat</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-350">TOMEE-350</a> allow to customize TempClassLoader force skip/load with multiple packages</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-359">TOMEE-359</a> taking into account filtering even for fragments</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-213">TOMEE-213</a> close webappclassloader after undeployment and not in its middle</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-219">TOMEE-219</a> Make jaxrs services managed by cdi when possible</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-358">TOMEE-358</a> activating back MyFacesContainerInitializer and adding StartupServletContextListener by default</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-417">TOMEE-417</a> ability to provide jaxrs providers, interceptors... in the webapp</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-414">TOMEE-414</a> support to provide slf4j in the application</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-415">TOMEE-415</a> use by default openejb classloader to create quartz scheduler</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1900">OPENEJB-1900</a> @LocalBean package and friendly scoped methods</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1901">OPENEJB-1901</a> @LocalClient doesn't work with EJBContainer</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1853">OPENEJB-1853</a> expose basicdatasource writable config through jmx</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1791">OPENEJB-1791</a> managing a conf.d folder as under unix for services</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1843">OPENEJB-1843</a> support @before @after @beforeclass @afterclass in embedded arquillian adapter (classloader is not correct so "BeanManagerProvider" can't work)</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1795">OPENEJB-1795</a> support @Inject for synamic EJB (interface only)</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1845">OPENEJB-1845</a> look in web-inf/classes/meta-inf for persistence.xml for embedded arquillian adapter and check classloaderasset get a better root url</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1863">OPENEJB-1863</a> no need to create a thread we are waiting to create an entitymanagerfactory</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1864">OPENEJB-1864</a> remove openejb-javaagent from openejb-core test since it is not mandatory and buggy (due to openjpa-javaagen) with java 7</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1576">OPENEJB-1576</a> Example: CDI Decorators</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1860">OPENEJB-1860</a> openejb.descriptors.output logging and functional improvements</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1858">OPENEJB-1858</a> be more tolerant on the tx manager type for managedconnection factory</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1823">OPENEJB-1823</a> allow to undeploy resources linked to an app with the app undeployment</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1420">OPENEJB-1420</a> Classloading issue in OSGI</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1840">OPENEJB-1840</a> managing request/session scopes in standalone</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1734">OPENEJB-1734</a> Shell to query and invoke EJBs through commands interactively</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1117">OPENEJB-1117</a> ServiceManager does not work in OSGi environment</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1889">OPENEJB-1889</a> when an EJB implements too many interfaces it fails with the message "TODO"</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1887">OPENEJB-1887</a> &lt;ServiceProvider&gt; inheritance to reduce redundant config in service-jar.xml files</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1888">OPENEJB-1888</a> add a way to hide log messages which are not relevant for openejb/tomee</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1896">OPENEJB-1896</a> Slightly reduce memory footprint of EJBs</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1892">OPENEJB-1892</a> embedded logging format is not applied to OpenJPA</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1891">OPENEJB-1891</a> get duration time of the query when logging sql</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1890">OPENEJB-1890</a> make openejb embedded arquillian adapter working with shrinkwrap maven and libraries which are not on classpath</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1761">OPENEJB-1761</a> improve default JUL logging</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1763">OPENEJB-1763</a> Allow EjbModule to be returned as a part of in-class configuration in ApplicationComposer (@Module)</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1271">OPENEJB-1271</a> Add pofiles to allow JPA provider to be changed</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1867">OPENEJB-1867</a> ability to configure the default job scheduler</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1277">OPENEJB-1277</a> RemoteInitialContextFactory .close() method to logout</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1868">OPENEJB-1868</a> allow to set a ejbtimerservice by ejb</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1865">OPENEJB-1865</a> add lib folder to classpath in openejb standalone like in tomee</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1866">OPENEJB-1866</a> add openejb-jpa-integration to ear libs</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1871">OPENEJB-1871</a> don't use webbeanslogger</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1870">OPENEJB-1870</a> allow to provide server event listener in apps</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1875">OPENEJB-1875</a> New LoginModule based on ServiceProvider</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1874">OPENEJB-1874</a> remove openejb-jsf</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1872">OPENEJB-1872</a> refactor a bit the way we hide internal beans (Comp) since now we have the structure to do it</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1877">OPENEJB-1877</a> refactor datasourcefactory and jdbc package to split it in subpackages for consistency</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1878">OPENEJB-1878</a> ability to create an entitymanager at startup</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1881">OPENEJB-1881</a> Multipoint "broadcast" attribute</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1883">OPENEJB-1883</a> rewrite ScopeHelper to use ContextsService</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1780">OPENEJB-1780</a> Application relative EJB WebService addresses</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1850">OPENEJB-1850</a> groovy jpa test</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1851">OPENEJB-1851</a> groovy spock sample</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1849">OPENEJB-1849</a> adding groovy cdi sample</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1841">OPENEJB-1841</a> basic console colors</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1897">OPENEJB-1897</a> easy way to mock beans injections</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1772">OPENEJB-1772</a> maven plugin to be able to dump info tree and avoid to create it when starting the app</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1514">OPENEJB-1514</a> Example: @Schedule Methods</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1649">OPENEJB-1649</a> Arquillian Tests</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-695">OWB-695</a> Cause missing in AnnotationDB$CrossReferenceException </li>
<li><a href="https://issues.apache.org/jira/browse/OWB-704">OWB-704</a> use method filter in javassist proxies instead of "manual" filtering</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-702">OWB-702</a> Add serialization unit tests to openwebbeans-web to catch future regressions</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-701">OWB-701</a> Support ASM for Bean Proxies</li>
</ul>

<h2>Bugs</h2>

<ul>

<li><a href="https://issues.apache.org/jira/browse/TOMEE-12">TOMEE-12</a> org.apache.openejb.config.AnnotationDeployer throws InstantiationException on com.sun.jersey.api.core.ApplicationAdapter</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-193">TOMEE-193</a> soap webservices are now deployed by default in the webapp context but what if the webservice is not in a webapp?</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-199">TOMEE-199</a> tomcat deployer doesnt work well for cdi apps</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-197">TOMEE-197</a> When running TomEE embedded in Eclipse jsp files do not hot deploy</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-10">TOMEE-10</a> JNDI Browser in the openejb.war does not show @LocalBean views as EJBs</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-198">TOMEE-198</a> JAX-RS and JAX-WS does not work when together in a single application</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-345">TOMEE-345</a> make EjbTimerServiceImpl serializable</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-141">TOMEE-141</a> when using OpenEJBListener with dropinwar approach we should try to fnid the war of the webapp too...</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-249">TOMEE-249</a> NPE on DatatypeConverter</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-349">TOMEE-349</a> ability to use redeploy from tomcat</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-242">TOMEE-242</a> @ManagedBean for rest services</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-240">TOMEE-240</a> wrap tomcat realm in tomeerealm to manage request.login even in a single request</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-339">TOMEE-339</a> @Context Providers is not supported</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-237">TOMEE-237</a> New gui is broken in IE</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-434">TOMEE-434</a> when using DeployerEjb the JNDI tree is the DeployerEjb one and not the deployed app one</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-267">TOMEE-267</a> Default 'type' ignored in <JndiProvider> and related elements</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-395">TOMEE-395</a> TomEEDataSourceCreator.ContantHashCodeHandler  will change the Exception throwed by the original method</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-392">TOMEE-392</a> EJB properties overriding from system.properties, application.properties or module.properties</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-394">TOMEE-394</a> pojo webservice undeployment doesn't clean eveything -> it prevents redeployment</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-258">TOMEE-258</a> pojo webservices doesnt get injections</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-383">TOMEE-383</a> pojo @WebService deployment without sei fails (NPE)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-200">TOMEE-200</a> CDI injections in Pojo JAXRS webservices can lead to memory leak</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-380">TOMEE-380</a> tomeeshutdownport is not respected by tomee maven plugin</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-388">TOMEE-388</a> Use case "Faces Request Generates Non-Faces Response" locks conversation forever (-> BusyConversationException)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-207">TOMEE-207</a> postcontruct is called before injections in pojo rest services</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-389">TOMEE-389</a> quartz prevent tomee to shutdown properly</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-403">TOMEE-403</a> jaxrs subresource are not working</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-401">TOMEE-401</a> don't filter http method (PATCH was not valid in rest for instance)</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-185">TOMEE-185</a> JAXB context can't be created from package</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-362">TOMEE-362</a> service MBeans are not unregistered</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-360">TOMEE-360</a> NPE in BeanManagerImpl scope is null</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-222">TOMEE-222</a> LocalBean can't be serializable</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-221">TOMEE-221</a> IllegalArgumentException: Class 'java.lang.Object' is not annotated with Path</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-419">TOMEE-419</a> JAR/WAR module-name not used</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-422">TOMEE-422</a> JAXRS @Context for HttpServletResponse and ServletConfig</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-424">TOMEE-424</a> [JAXRS] Custom @Context not supported</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-211">TOMEE-211</a> tomee:start command (tomee maven plugin) stay up while tomee is up</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-409">TOMEE-409</a> JAXRS @Context for HttpServletRequest and ServletRequest</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-212">TOMEE-212</a> calling request.login() and ejbcontext.getCallerPrincipal() in the same request is not consistent</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-407">TOMEE-407</a> JavaMail javax.mail.Session resources do not work with authentication</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-218">TOMEE-218</a> RESOURCE_LOCAL entitymanager shouldn't be injected</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-217">TOMEE-217</a> log4j integration issue</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-216">TOMEE-216</a> Changes to InjectionTarget in ProcessInjectionTarget event ignored</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-410">TOMEE-410</a> web.xml validation rejects load-on-startup values having extraneous white spaces</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-411">TOMEE-411</a> Accept spaces in load-on-startup </li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-154">TOMEE-154</a> Deployment fails in ear when injections are done between ejbmodule and webmodule (classloading exception because the webapp classloader is known later)</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1848">OPENEJB-1848</a> Multipoint Automatic Reconnect fails in some situations</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1847">OPENEJB-1847</a> When deploying two ear files in openejb only the first one gets deployed correctly</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1844">OPENEJB-1844</a> annotatedtype are not usable from processAnnotatedType if not already processed</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1720">OPENEJB-1720</a> NPE at at org.apache.openejb.util.AnnotationFinder</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1862">OPENEJB-1862</a> boolean type re not well managed in org.apache.openejb.config.AnnotationDeployer.DiscoverAnnotatedBeans#process</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1855">OPENEJB-1855</a> LinkageError on Mac OS with "sun/security/pkcs11/SunPKCS11"</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1717">OPENEJB-1717</a> When openejb-osgi bundle is restarted, we get an exception (ServiceManager is already initialized)</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1727">OPENEJB-1727</a> couldn't start owb context</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-958">OPENEJB-958</a> logging.properties: DOS line ends and category instead of logger</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1728">OPENEJB-1728</a> Karaf is blocked</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1382">OPENEJB-1382</a> Provide interceptor/thread based context for OWB rather than classloader based context</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1698">OPENEJB-1698</a> EntityBean conflict when a persistent property exists called "deleted"</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1603">OPENEJB-1603</a> InitialContext instantiation fails with ERROR - CDI Beans module deployment failed</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1551">OPENEJB-1551</a> ejb-jar.xml should be optional. </li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1837">OPENEJB-1837</a> WebBeansLogger uses java.util.logging directly and doesn't obey system property openejb.log.factory</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-967">OPENEJB-967</a> NullPointerException during injection into a POJO webservice</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1397">OPENEJB-1397</a> After upgrade to 3.1.3 web services fail with exception</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1643">OPENEJB-1643</a> @Dispose not called</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1438">OPENEJB-1438</a> Wrong jar required for remote client in docs</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1893">OPENEJB-1893</a> @LocalBean references did not survive passivation</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1595">OPENEJB-1595</a> [BUILD FAILED]Compilation error occurs while building openejb trunk</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1126">OPENEJB-1126</a> SAAJ-related test cases no longer work with IBM's SDK</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1876">OPENEJB-1876</a> <ejb-jar id="foo"/> id ignored when ejb-jar contains no child elements</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1879">OPENEJB-1879</a> usage of OWBInjector shall be reworked</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1882">OPENEJB-1882</a> this can't be use in localbeans constructor</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1648">OPENEJB-1648</a> persistence.xml files in WEB-INF/classes/META-INF/ incorrect root url</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1884">OPENEJB-1884</a>  EJBContainer.createEJBContainer(); doesn't register the WebBeansContext correctly</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1886">OPENEJB-1886</a> statsinterceptor should be added before starting the timer if necessary</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-709">OWB-709</a> webbeans-tomcat6 must honour WEB-INF/classes/META-INF/beans.xml</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-694">OWB-694</a> Misleading exception message "Wrong termination object"</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-708">OWB-708</a> PrincipalBean doesnt get found</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-670">OWB-670</a> ProcessInjectionTarget event fired a bit late</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-707">OWB-707</a> tomcat-sample and tomcat7-sample are just broken.</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-703">OWB-703</a> getBeans cache key algorithm must be unique</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-650">OWB-650</a> ContextFactory#destroy*Context have to reset the proxy cache</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-559">OWB-559</a> Method-Injection for methods with more than one parameter fails with OWBInjector</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-699">OWB-699</a> Passivation leads to NPE</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-698">OWB-698</a> InjectableBeanManager not serializable</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-697">OWB-697</a> Non-Static Loggers leads to NonSerizializableException</li>
<li><a href="https://issues.apache.org/jira/browse/OWB-696">OWB-696</a> check for unproxyable API types should get moved to the validateBeans phase</li>
</ul>

<h2>Tasks & Sub-Tasks</h2>

<ul>



<li><a href="https://issues.apache.org/jira/browse/TOMEE-342">TOMEE-342</a> webservice with configured deployment url example</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-335">TOMEE-335</a> Create a new JIRA saying: checking and closing JIRAs</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-129">TOMEE-129</a> Tweak TCK setup for JAX-RS tests</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-371">TOMEE-371</a> add an arquillian test using hibernate</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-275">TOMEE-275</a> review OWB integration to see if some stuff should be pushed to OWB</li>
<li><a href="https://issues.apache.org/jira/browse/TOMEE-272">TOMEE-272</a> add notice for jaxrs tomee distribution</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1688">OPENEJB-1688</a> Build the Arquillian adapters as part of the main OpenEJB build</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1687">OPENEJB-1687</a> Consolidate tests and run against all Arquillian adapter</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1861">OPENEJB-1861</a> remove OWB JMSManager usage from OpenEJB</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1859">OPENEJB-1859</a> cucumber-jvm example</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1857">OPENEJB-1857</a> Example using cdi-query</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1895">OPENEJB-1895</a> Refactored @Asynchronous support</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1869">OPENEJB-1869</a> server event example</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1873">OPENEJB-1873</a> Upgrade to OpenWebBeans-1.1.5</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1885">OPENEJB-1885</a> Simplify EJB proxy code</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1614">OPENEJB-1614</a> Example: @Produces and @Disposes within a @RequestScoped context</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1406">OPENEJB-1406</a> Example: Lookup of EJBs</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1407">OPENEJB-1407</a> Example: Lookup of EJBs with descriptor</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1606">OPENEJB-1606</a> Example: CDI @Decorator and @Delegate</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-361">OPENEJB-361</a> Example: Bean-Managed Transactions</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-352">OPENEJB-352</a> Example: Stateful Bean with Callbacks</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-351">OPENEJB-351</a> Example: Stateless Bean with Callbacks</li>
<li><a href="https://issues.apache.org/jira/browse/OPENEJB-1653">OPENEJB-1653</a> Arquillian: JSF Managed Bean Tests</li>

</ul>
