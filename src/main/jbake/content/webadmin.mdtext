Title: Webadmin
The Webadmin is a new addition to OpenEJB 1.0 and very innovative in that
it lets you plug-in your own admin beans.  Here are some screenshots:

- [Main](http://tomee.apache.org/images/webadmin-main.png)
- [EJB Details](http://tomee.apache.org/images/webadmin-ejbdetails.png)
- [List Logs](http://tomee.apache.org/images/webadmin-listlogs.png)
- [System Properties](http://tomee.apache.org/images/webadmin-properties.png)
- [JNDI Viewer](http://tomee.apache.org/images/webadmin-viewjndi.png)
- [EJB Viewer](http://tomee.apache.org/images/webadmin-ejbviewer.png)
- [Object and EJB Invoker](http://tomee.apache.org/images/webadmin-objectinvoker.png)

<a name="Webadmin-EnablingtheWebadminin1.0beta1"></a>
## Enabling the Webadmin in 1.0 beta 1

The Webadmin console is in the 1.0 beta 1 release.  To enable it, simply
move into the openejb.home directory and *copy* the
openejb-webadmin-main.jar from the _beans_ directory into the _lib_
directory.  Then start the server.


    mingus:~/
    $ cd /usr/local/openejb-1.0-beta1
    
    mingus:/usr/local/openejb-1.0-beta1 03:37:33 
    $ cp beans/openejb-webadmin-main.jar lib/
    
    mingus:/usr/local/openejb-1.0-beta1 03:37:52 
    $ ./bin/openejb start
    OPENEJB_HOME = /usr/local/openejb-1.0-beta1
    OpenEJB 1.0-beta1    build: 20050829-2233
    http://www.openejb.org
    resources 1
    OpenEJB ready.
    [init]
 OpenEJB Remote Server
      ** Starting Services **
      NAME		       IP	       PORT  
      admin thread	       127.0.0.1       4200  
      ejbd		       127.0.0.1       4201  
      telnet	       127.0.0.1       4202  
      webadmin	       127.0.0.1       4203  
    -------
    Ready!


Now you can open your browser to go to http://localhost:4203/

<a name="Webadmin-WebAdminBeans"></a>
# WebAdmin Beans

To create an EJB and have it included as part of the WebAdmin, simply
subclass from WebAdminBean and include it in your ejb-jar.xml file as such:

    <session>
      <description>A JNDI viewer</description>
      <ejb-name>webadmin/ViewJndi</ejb-name>
      <home>org.openejb.webadmin.HttpHome</home>
      <remote>org.openejb.webadmin.HttpObject</remote>
      <ejb-class>org.openejb.webadmin.clienttools.ViewJndiBean</ejb-class>
      <session-type>Stateless</session-type>
      <transaction-type>Bean</transaction-type>
    </session>

    
The ejb-name is used to create the menus and should follow the format of
'menu-section/menu-item'. WebAdminBeans are grouped together by the
'menu-section' portion of their ejb-name. The 'menu-item' is the clickable
link that causes the EJB code to be execute. Very simple and makes it
possible to package administrative components with your EJB applications.

# WebAdmin Plugins

Here is a project that already takes advantage of the new feature. [BeanGen|http://beangen.sourceforge.net]

# Developers guide
Below is David Blevins' email on how webadmin worked. Please have a look at
the text below before you start working on porting the existing WebAdmin to
version 3.

Plain old stateless beans were used as the "servlets".	To make a bean that
would show up in the Webadmin Console you simply had to implement the
HttpBean interface (i think it's now called HttpListener) and give your
bean a deploymentId following this format "webadmin/{section}/\{page\}". 
Anyone could add to the Webadmin console by doing both of these things,
which is really cool as people developing EJB apps can also deploy beans
for administering those apps right in the exact same jar.  This is not only
easy for packaging but means new sections can be added/removed on the fly.

Using the described "webadmin/{section}/\{page\}" deploymentId format,
things end up automagically grouped in the JNDI tree.  There's a 'webadmin'
context we grab which will contain any number of "section" contexts
("ClientTools", "EJBGenerator", etc.).	Each of those section subcontexts
will contain several beans which we will use to make the pages.  Making the
menu is pretty easy as we just iterate over the webadmin section of the
global jndi tree.

When an http request came in we just took the path part of the GET or POST
request, prepended "webadmin/" and then went looking for a bean with that
deployment id and invoked it via it's HttpBean (now called HttpListener)
interface passing in a HttpRequest and HttpResponse objects which are
trimmed down versions of similar servlet classes.
There'll be some changes to this as now we support our plain ejb protocol
over our http implementation, so the two will have to find a way to share
the URL space.	See the openejb-http module.

To implement session state, we had a stateful session bean implementing an
HttpSession interface (again, similar to the servlet equivalent) and simply
wrote the internal ID of the bean instance into a Cookie sent to the
browser.  For some reason we would write the javax.ejb.Handle of the
stateful bean's EJBObject to disk and
read it back out on subsequent requests then used it to get a reference to
the EJBObject again.  I'm not sure why we didn't just keep a static hashmap
and put the EJBObject right in it using an ID we could just make up like
UUID (that would have been way simpler).

We had a standard superclass we favored for beans that implemented the
HttpBean (HttpListener) interface that did templating and the
aforementioned menu  construction.  The templating was perhaps too tricky
as we used a non-printable character to determine where to insert data. 
Now we could just use swizzle-stream for some pretty decent templating
ability or even velocity.  I'd be hesitant to endorse velocity as we
already have a dep on swizzle-stream and wouldn't want to see us add
another meg to our distro size if we can avoid it -- we have like 3 times
as many deps as 1.0 did and we should probably start tightening the belt.

To serve static things like images, we had a "default" HttpBean which
searched for the items in the classpath and wrote them into the response
setting the mime type, etc. correctly.	One thing that we never did which
still needs to happen is that the bean didn't have the logic to set the
modification information into the response so the "If modified since"
header which would allow the browser to rely on it's cache instead of
requesting the same images over and over again.

That pretty much covers the way it was put together.
    
    
  - The Jndi Viewer, Class Viewer, Ejb Viewer, and Object Invoker were written by yours truly
  - The EJB Generator was written by Jeremy Whitlock
  - Everything else was written by Tim Urberg.	Tim was "WebAdmin guy" for
    a good long while.  Before Tim came along the webadmin was just some
    experimental code I had in a branch, he did more than he realizes by putting his energy
    into it -- active people attract/ create more active people.  Maybe we can
    convince him to come back and work on it ;)
    
And of course I have to mention our own Paulo Lopes who wrote a really cool
project out in SF.net (http://beangen.sourceforge.net/) which was the first
plugin for the OpenEJB Webadmin.  He wrote it before we even had shipped a
release containing the Webadmin or had any docs at all on it, which in my
mind shows just how neat the idea of using ejb's and simple conventions to
do the console really is.

Some notes going forward is that we have a truck load of meta-data now
available via SystemInstance.get().get (OpenEjbConfiguration.class). 
Certainly JSR-77 is one option, but we could do far better with plain old
java code.  That tree is the primary source of meta-data for OpenEJB, it's
what was used to construct every container, bean, resource adapter,
database connector and *everything* in the system (well, sans the
protocols).  Someone new to the project can look at it and understand it
without having to read any abstract specs.  Something to consider.  The
tree is read only in it's function, however it is possible to copy then
edit and make new containers, etc. based on existing definitions.

Additionally, using this same data structure it's possible to show the
potential services available via the service-jar.xml files in the classpath
that detail containers, resource adapters, database connectors, etc. which
can be configured/created at runtime.  So we could also display a sort of
catalogue of components (aka. services) that someone could click and deploy
via the console.
