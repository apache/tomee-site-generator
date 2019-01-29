# Apache TomEE 1.5 Released!

We are excited to announce the release of [Apache TomEE 1.5](http://tomee.apache.org/downloads.html).  The volume of
feedback on the 1.0.0 Final drove such an impressive number of fixes and features into TomEE that the release number
has been updated to reflect [the changes](tomee-1.5.0-release-notes.html).

The team is proud to announce certification of a new Apache TomEE stack that includes the Web Profile plus JAX-RS for
RESTful Web Services. The new 'TomEE JAXRS' distribution shows TomEE's commitment to progressing its certification
efforts beyond the Web Profile and is a great alternative to the TomEE Plus distribution.  See the
[comparison](http://tomee.apache.org/comparison.html) for a view of all Apache TomEE distributions.

Another great feature is the extended support for database connection pools.  In addition to the previously
supported Apache Commons-DBCP, the 1.5 release adds transaction support to the native Apache Tomcat and BoneCP
connection pools.  The two additional pools offer great alternatives to applications under heavy load.  JMX
instrumentation and statistics have also been added generically to all pools and provide a great level of
monitoring and management.

Other major features include deploy-time enhancement for JPA Entities via Apache OpenJPA, support including JPA providers in webapps,
ability to mock and inject mocks in unit tests and a powerful new [TomEE Maven Plugin](maven/index.html) which can
provision servers, install libraries, deploy webapps and more.

We’d like to thank everyone who gave feedback and contibuted to improve Apache TomEE on a daily basis!

