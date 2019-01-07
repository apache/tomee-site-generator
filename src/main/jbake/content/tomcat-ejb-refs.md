Title: Tomcat ejb-refs
<a name="Tomcatejb-refs-Via@EJBAnnotation"></a>
# Via @EJB Annotation

Which an be as simple as adding this to your Servlet, Filter, or Listener:


    @EJB
    private HelloLocal helloLocal;


See the [@EJB Injection Example](injection-of-other-ejbs-example.html)
 for a running example.  The example uses one ejb to refer to another ejb,
but the same rules apply for servlets.

<a name="Tomcatejb-refs-Addingejb-refinyourweb.xml"></a>
# Adding ejb-ref in your web.xml

Or on the older xml-style:


    <ejb-ref>
     <description> EJB Reference to the bean deployed to OpenEJB </description>
     <ejb-ref-name>ejb/hello</ejb-ref-name>
     <ejb-ref-type>Session</ejb-ref-type>
     <home>org.acme.HelloHome</home>
     <remote>org.acme.Hello</remote> 
    </ejb-ref>

