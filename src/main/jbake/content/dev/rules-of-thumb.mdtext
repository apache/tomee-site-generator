Title: Rules of Thumb

<a name="RulesofThumb-Configuration"></a>
### Configuration

* Configuration settings should have configurable defaults at the module,
ear, server and cluster level (when clustering is added).  For example,
security settings should be configurable on a per ejb, ejb jar, ear and
server levels.	

* Settings should have a smooth increase in complexity from very simple to
complex.  For example, a cache setting could start with a simple max-size,
and over time the user could increase the complexity by adding
configuration for disk paging up to specification of complex flushing
rules.

<a name="RulesofThumb-Validation"></a>
### Validation

* Applications should be fully validated before handing it off to the
deployment system.  This vastly simplifies the deployment system because it
assumes that an application is valid.  As a corollary, the deployment
system should not be complicated with code that tests for invalid
deployments or with code to print graceful error messages to a user.
