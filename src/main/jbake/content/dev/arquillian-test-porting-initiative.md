Title: Arquillian Test Porting Initiative

First things, first.  You can contribute your ideas by clicking the blue pencile icon in the upper right.  Edit this page and help shape the initiative!

# Unite the Armies to fight the Bugs!

Testing is the most crucial part of any project.  A project that incorporates as much functionality as TomEE does needs a *lot* of tests.

There's a saying that if it isn't documented it doesn't exist.  Likely more true is that if it isn't tested it it might as well not exist,
documentation or not.

The simple truth is many of the critical tests that apply to functionality in TomEE actuall exist, but are spread across the numberous projects
that TomEE incorporates.  Just as TomEE is about unifying and integrating all these projects together, the same vision and initative
must be given into unifying and integrating all these tests.

If we could port them all to a common testing framework like Arquillian and consolidate them all into one codebase, the result would be nothing short
of a marvel.  An unparalleled feat.

Such a thing has never been done at the ASF.  Be ready to blaze some trails and be a pioneer.

# The Kingdoms

There are far more than 3,000 test classes we could port across the various projects, each using it's own flavor of home-made setup code.
The coverage is also not what you'd expect.

 - Activemq 1281
 - CXF 979
 - TomEE 802
 - OpenEJB 215
 - MyFaces 171
 - OpenWebBeans 165
 - Bval 56
 - OpenJPA 33
 - Tomcat 20

The above results are no dount eye-opening.  In all fairness, the projects with so few test are not as "untested" as they appear, they simply rely more heavily
on the proprietary Java EE TCK which is closed source.  This is adequate, but not fantastic.  **An open source project should have open tests.**

# Generals Needed

This is no small feet.  With over 3,000 tests porting them all is not realistc.  If we had 10 people working full time, that's till 300 tests each person
would need to port.  This simply is not realistic.  More realistic would be for a person to port say 10 tests before they get an injury and need to leave the
battlefield with hopes of joining the glorious fight another day -- aka. they get busy :)

Even with 300 people each contributing 10 tests each, it's still quite a lot of patches for a small team to apply.  Organizing 300 people and shaping an initiative
like this is als no small feat.  What we need are Generals.  Individuals to survey the land and plan attacks with small groups of soldiers.

Porting 50 tests yourself is impressive.  Leading the charge on 500 tests being ported is astonishing.

# Early stage

The tests in question are located more or less here:

 - svn co http://svn.apache.org/repos/asf/activemq/trunk
 - svn co http://svn.apache.org/repos/asf/bval/trunk
 - svn co http://svn.apache.org/repos/asf/cxf/trunk
 - svn co http://svn.apache.org/repos/asf/myfaces/core/trunk
 - svn co http://svn.apache.org/repos/asf/tomee/tomee/trunk/itests
 - svn co http://svn.apache.org/repos/asf/openjpa/branches/2.2.x
 - svn co http://svn.apache.org/repos/asf/openwebbeans/trunk
 - svn co http://svn.apache.org/repos/asf/tomcat/tc7.0.x/trunk

While not part of TomEE, Wink has some wonderful JAX-RS tests we could use:

 - svn co http://svn.apache.org/repos/asf/wink/trunk

At this stage there's still quite a lot of information needed to mobilize the troops.  How to write Arquillian tests, which tests are most important, how should
they be divided?

The call goes out to all you would-be Generals!

Any mode of cooperation is acceptable.  Discussions on the `dev (at) tomee.apache.org` list welcome.  This document can be edited by anyone.  As well there's a
JIRA we can use to move the discussion along:

 - https://issues.apache.org/jira/browse/TOMEE-746

