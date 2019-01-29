Title: Design - Configuration Factory

<a name="Design-ConfigurationFactory-ConfigurationFactory"></a>
## Configuration Factory

Sub-component of [Classic Assembler](design-classic-assembler.html)

<a name="Design-ConfigurationFactory-Definition"></a>
## Definition

Creates an instance of the OpenEjbConfiguration class that contains all the
data and configuration information the Classic assembler needs to construct
the container system. The object structure in the OpenEjbConfiguration
class is refered to as the InfoObjects. The Configuration Factory can
construct, retreive, or populate the InfoObjects from any data source it
chooses or by any means it chooses.

<a name="Design-ConfigurationFactory-AlsoKnownAs"></a>
## Also Known As
 * Config Factory
 * InfoObject Factory

<a name="Design-ConfigurationFactory-Responsibilities"></a>
## Responsibilities
 * Creates an instance of the OpenEjbConfiguration
 * The data in the InfoObjects must be validated and accurately represent
the system, services, jars, and beans to be constructed

<a name="Design-ConfigurationFactory-RelatedClasses"></a>
## Related Classes
 * org.apache.openejb.assembler.classic.OpenEjbConfigurationFactory
 * org.apache.openejb.assembler.classic.OpenEjbConfiguration

<a name="Design-ConfigurationFactory-Implementations"></a>
## Implementations
 * XML Configuration Factory _(no longer supported)_
 * [Nova Configuration Factory](design-nova-configuration-factory.html)

