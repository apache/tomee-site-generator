Title: Design - Security Service
<a name="Design-SecurityService-SecurityService"></a>
## Security Service

Sub-component of [OpenEJB](-design.html)


<a name="Design-SecurityService-Definition"></a>
## Definition

Provides the container with an authenticated client identity.

<a name="Design-SecurityService-AlsoKnownAs"></a>
## Also Known As
 * Security Provider

<a name="Design-SecurityService-Responsibilities"></a>
## Responsibilities
 * Authenticate the user in an implementation specific way
 * Provides OpenEJB a reference to the current security identity of the
client
 * Assists OpenEJB with role-based authorization control

<a name="Design-SecurityService-RelatedClasses"></a>
## Related Classes
 * org.apache.openejb.spi.SecurityService

