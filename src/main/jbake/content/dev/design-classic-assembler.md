Title: Design - Classic Assembler

<a name="Design-ClassicAssembler-ClassicAssembler"></a>
## Classic Assembler

Implementation of [Assembler](design-assembler.html)

<a name="Design-ClassicAssembler-Description"></a>
## Description

The standard assembler supported by the OpenEJB team.  Uses meta-data
supplied via the Configuration Factory to create all components in the
system.  It is assumed the meta-data has been checked and validated, all
links and references will resolve, all classes are present, and all apps
compliant.

<a name="Design-ClassicAssembler-RelatedClasses"></a>
## Related Classes
 * org.apache.openejb.assembler.classic.Assembler
 * org.apache.openejb.assembler.classic.OpenEjbConfiguration

<a name="Design-ClassicAssembler-RelatedPackages"></a>
## Related Packages
 * org.apache.openejb.assembler.classic

<a name="Design-ClassicAssembler-Sub-components"></a>
## Sub-components
 * [Configuration Factory](design-configuration-factory.html)
