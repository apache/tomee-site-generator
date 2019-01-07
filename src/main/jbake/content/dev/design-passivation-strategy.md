Title: Design - Passivation Strategy
<a name="Design-PassivationStrategy-PassivationStrategy"></a>
## Passivation Strategy

Sub-component of [Stateful SessionBean Container](design-stateful-sessionbean-container.html)


<a name="Design-PassivationStrategy-Definition"></a>
## Definition

Used by the Stateful Container to passivate and activate stateful session
beans to a temporary storage.


<a name="Design-PassivationStrategy-Responsibilities"></a>
## Responsibilities
 * Store and retrieve instances

<a name="Design-PassivationStrategy-RelatedClasses"></a>
## Related Classes
 * org.apache.openejb.core.stateful.PassivationStrategy

<a name="Design-PassivationStrategy-Implementations"></a>
## Implementations
 * [Random Access File Passivater](design-random-access-file-passivater.html)
 * [Simple Passivater](design-simple-passivater.html)
