Title: Design - Application Server
<a name="Design-ApplicationServer-ApplicationServer"></a>
## Application Server

Sub-component of [OpenEJB](design.html)


<a name="Design-ApplicationServer-Definition"></a>
## Definition

Any component wishing to serve or deliver Enterprise JavaBeans.

<a name="Design-ApplicationServer-AlsoKnownAs"></a>
## Also Known As
 * Server Adapter
 * Server Provider

<a name="Design-ApplicationServer-Responsibilities"></a>
## Responsibilities
 * Remote client access to OpenEJB
 * Implement the bean's remote and home interfaces.
 * Distribute its implementation of the remote and home interfaces.
 * Provide clients with a JNDI name space for looking up beans.
 * Delegate method invocations to the container.

<a name="Design-ApplicationServer-RelatedClasses"></a>
## Related Classes
 * org.apache.openejb.spi.ApplicationServer

<a name="Design-ApplicationServer-Implementations"></a>
## Implementations
 * [Local Server](design-local-server.html)
 * [Remote Server](design-remote-server.html)
