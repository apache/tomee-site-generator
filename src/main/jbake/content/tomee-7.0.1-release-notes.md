        Release Notes - TomEE - Version 7.0.1
                                
<h2>        Bug
</h2>
<ul>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1813'>TOMEE-1813</a>] -         tomee.sh fails on cygwin
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1817'>TOMEE-1817</a>] -         java.lang.NullPointerException in Connector Resource Adapter deploy
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1819'>TOMEE-1819</a>] -         OWB configuration not respected
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1822'>TOMEE-1822</a>] -         SecurityService not available in TomEERealm: Principal can be the default one instead of the logged one
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1827'>TOMEE-1827</a>] -         Possible java.util.ConcurrentModificationException with ValidatingGenericConnectionManager
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1828'>TOMEE-1828</a>] -         OpenEJB application fails to find singleton container when openejb.offline = true
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1829'>TOMEE-1829</a>] -         com.sun.mail doesn&#39;t work in webapp out of the box
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1832'>TOMEE-1832</a>] -         dbcp2 datasource lock contention on createDataSource + illogical locking
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1839'>TOMEE-1839</a>] -         TomEE doesn&#39;t work with Arquillian servlet module 1.1.11.Final
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1841'>TOMEE-1841</a>] -         webapp version ignored
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1844'>TOMEE-1844</a>] -         bval can conflict with JAXRS bval integration
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1845'>TOMEE-1845</a>] -         dbcp2 openejb datasource JMX warning message at datasource unregistration
</li>
</ul>
    
<h2>        Dependency upgrade
</h2>
<ul>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1850'>TOMEE-1850</a>] -         Tomcat 8.5.3
</li>
</ul>
                    
<h2>        Improvement
</h2>
<ul>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1821'>TOMEE-1821</a>] -         Allow to filter CDI extensions
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1823'>TOMEE-1823</a>] -         double johnzon max size (8k) and add a comment in system.properties
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1824'>TOMEE-1824</a>] -         support date configuration for johnzon
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1825'>TOMEE-1825</a>] -         allow to configure converters for johnzon jaxrs provider
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1830'>TOMEE-1830</a>] -         Set TomEEJarScanner TomEEFilter to delegate to standard jar scan filter as default
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1838'>TOMEE-1838</a>] -         (Un)DeployMojo not supporting HTTPS
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1843'>TOMEE-1843</a>] -         resources.xml errors unclear about the file
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1849'>TOMEE-1849</a>] -         JaccProvider hard to override
</li>
</ul>
            
<h2>        New Feature
</h2>
<ul>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1812'>TOMEE-1812</a>] -         add reload command to tomee embedded maven plugin
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1818'>TOMEE-1818</a>] -         add depends-on to Resource
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1826'>TOMEE-1826</a>] -         [CXF] openejb.cxf.monitoring.jmx config entry to activate counter repository
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1831'>TOMEE-1831</a>] -         Enrich failover router to support error handling
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1833'>TOMEE-1833</a>] -         add ExceptionSelector to Router (dynamic datasource routing)
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1834'>TOMEE-1834</a>] -         Add an all in one routed datasource and failover router
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1835'>TOMEE-1835</a>] -         add openshift properties provider for mysql and postgresql
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1836'>TOMEE-1836</a>] -         add create/destroy server events for resource 
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1846'>TOMEE-1846</a>] -         allow to fully configure a resource programmatically
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1847'>TOMEE-1847</a>] -         allow to control API used to export a resource using classpath attribute
</li>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1848'>TOMEE-1848</a>] -         add @Configuration support for TomEEEmbeddedSingleRunner
</li>
</ul>
                                                        
<h2>        Task
</h2>
<ul>
<li>[<a href='https://issues.apache.org/jira/browse/TOMEE-1814'>TOMEE-1814</a>] -         upgrade copyright year to 2016
</li>
</ul>
                