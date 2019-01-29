Title: Design - Assembler

<a name="Design-Assembler-Assembler"></a>
## Assembler

Sub-component of [OpenEJB](design.html)

<a name="Design-Assembler-Definition"></a>
## Definition

Instantiates and assembles a configured, runnable, instance of the
container system and all sub-components. Vendors needing extreme control
over the construction of the container system can get it by implementing
this class. Doing this comes with large amounts of resposibility and
complexity and should not be done without a deep understanding of OpenEJB.

<a name="Design-Assembler-Responsibilities"></a>
## Responsibilities
 * Instantiate and initialize all Container implementations
 * Instantiate and initialize TransactionService implementation
 * Instantiate and initialize SecurityService implementation
 * Instantiate and initialize all ResourceManagers
 * Load all deployed beans
 * Populate each deployment's JNDI ENC
 * Populate the IntraVM Server's global, client, JNDI namespace

<a name="Design-Assembler-RelatedPackages"></a>
## Related Packages
 * org.apache.openejb.spi.Assembler

<a name="Design-Assembler-Implementations"></a>
## Implementations
 * [Classic Assembler](design-classic-assembler.html)
