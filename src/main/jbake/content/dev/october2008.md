Title: October2008
The user base has grown significantly. The primary areas seem to be people
replacing the JBoss Embedded platform with OpenEJB as an embedded container
for either testing or Swing/GUI work and people using OpenEJB in Tomcat for
web work. There have also been some reports of very large applications
getting ported to OpenEJB. External signs of adoption have increased as
well with some OpenEJB users popping up in other communities such as Maven
asking for OpenEJB focused improvements in their tools, a half dozen or so
very favorable blog entries from people outside the project and a recent
thread on TheServerSide where many users expressed they were considering
leaving Spring for OpenEJB/Tomcat or Glassfish.

Development on the OpenEJB Eclipse Plugin continues strong. The
still-in-development Eclipse plugin is attracting some interest and has
already received some contributions from at least two different
individuals. Thanks goes out to Jonathan Gallimore for not succumbing to
post-commit-status burnout as so many people do when first getting commit.
The dedication is noted. Other larger areas of development have been a
total overhaul of the client/server aspect of OpenEJB, first in the request
speed and throughput and secondly in request failover and retry. This had
been a weak area for the project and these improvements will likely
increase the number of people using the standalone version of OpenEJB
(current major areas of use are embedded and Tomcat). Some experimental
work on integrating OpenEJB with Spring has been done which when completed
should prove to be a compelling feature.

Support for EJB 3.1 is underway. Full support for the proposed Singleton
bean type has been added, which to our knowledge is the only implementation
in the market currently. This should drive some EJB 3.1 early adopters to
the project and serve as a good tool for getting feedback for the EJB 3.1
spec.

The OpenEJB 3.1 release is up for a vote and if all goes well will be final
in a few days.	It's been a bit too long since our last release in April. 
Hopefully after 3.1 is released we can get back into the frequent release
rhythm we had throughout the betas and up until 3.0 final.
