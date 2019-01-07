Title: Writing Validation Tests

<a name="WritingValidationTests-Summary"></a>
## Summary

Validation is a critical and integral part of the project. If you are
writing some code which validates some rules, you should definitely write a
test for it. A little validation test framework is available to write tests
specifically for Validation. This page explains the details of writing such
tests using example snippets.

<a name="WritingValidationTests-TheValidationFramework"></a>
## The Validation Framework

1. `org.apache.openejb.config.ConfigurationFactory` uses a chain of
`org.apache.openejb.config.DynamicDeployer` implementations. One of the
implementations in the chain is
`org.apache.openejb.config.ValidateModules`. `ValidateModules` is
conditionally added to the _chain_ if the property
`openejb.validation.skip=true|false`. If this property is false, then
`ValidateModules` is used to kick off the Validation Framework

[Configuration Factory](https://github.com/apache/openejb/tree/trunk/openejb/container/openejb-core/src/main/java/org/apache/openejb/config/ConfigurationFactory.java)

1. Internally ValidateModules uses the [*AppValidator.validate()* method](https://github.com/apache/openejb/tree/trunk/openejb/container/openejb-core/src/main/java/org/apache/openejb/config/ValidateModules.java)

1. This method then performs validation using a number of rules. _A
validation rule/s is represented by a class implementing ValidationRule.
In fact, all the classes checking the validation rules , extend
ValidationBase, which further implements ValidationRule.

    <img src="../images/ClassDiagram.png" alt="" align="center">

The _list of rules_ being executed can actually be found in the following
method of [AppValidator](https://github.com/apache/openejb/tree/trunk/openejb/container/openejb-core/src/main/java/org/apache/openejb/config/AppValidator.java)
1. The above rules are then executed one by one

1. Each module has an attached ValidationContext , which maintains a list of
failures, warnings and errors. As the above rules are being invoked, the
failure/errors/warnings for a module are being added to its
ValidationContext. Every Validation failure has an associated message which
can be found in `org/apache/openejb/config/rules/messages.properties`. A
message has three levels as explained below:

    Format for the different levels follows this spirit:
    
    1. Should be short and fixed such that someone could search/grep for it
    without having to know/use regular expressions.  These tend to be similar
    to the message key.
    
    2. Intended to contain the issue expressed in 1 with only the essential
    details, should not line wrap if possible.  Be terse.
    
    3. Teacher's assistant.  A much more conversational and possibly more
detailed
    explanation of the issue, should tell the user what to do to fix the
problem.
    I.e. don't just point out what is wrong, also point out what is right.	Use
    several lines if needed.

Here is an *example validation message*

    # 0 - method name
    # 1 - full method
    # 2 - remote|home
    # 3 - interface name
    # 4 - EJB Class name
    1.no.busines.method	  No such business method
    2.no.busines.method	  Business method {0} not implemented.
    3.no.busines.method	  Business method {1} not implemented. The method was declared in the {2} interface {3}, but not implemented in the ejb class {4}

1. The validation framework does not stop processing on the first validation
failure, but keeps going and checking for other validation errors and
reports them all to the user. This allows the user to fix all errors in one
go and re-attempt deploying the application.

<a name="WritingValidationTests-TheValidationTestFramework"></a>
## The Validation Test Framework



1. The test framework is specifically written with the following goals in
mind:
1.    It should be easy to write the test, and the framework should do
the boiler-plate work, the test author just needs to provide the relevant
info
1.    It should report the test coverage i.e. the framework should
generate a report regarding which keys in messages.properties have tests
written for them and what is the corresponding Test class/es which test for
the validation rule associated with that key
1.    It should ensure that if a test is being written for a specific
message key, then that key should exist in the messages.properties file
1. Lets break down the framework by using an [example](https://github.com/apache/openejb/tree/trunk/openejb/container/openejb-core/src/test/java/org/apache/openejb/config/rules/CheckInjectionTargetsTest.java)

    
   1.    The first thing to note is that we are running the test using our
own custom runner i.e. `@RunWith(ValidationRunner.class)`. This runner
ensures that the keys we are testing, actually exist in the
messages.properties file. It does a lot more, as we shall see later    
    2.    The test method
    3.    Can be given any name
    4.    Must be annotated with @Keys and CANNOT be annotated with @Test.
The rest of the JUnit annotations can be used
    5.   Must return one of EjbJar / EjbModule / AppModule. The returned
EjbJar/EjbModule/AppModule will be specifically created to cause one or
more validation errors/warnings/failures.
    1.    Following annotations are provided by the framework
    1.    @Keys : is a collection of zero or more @Key
    1.    @Key : represents a key for which this test is being written. A @Key
can be of type FAILURE or WARNING or ERROR. Default value is FAILURE. As
seen in the example above, the test() method is expecting two warnings for
the key injectionTarget.nameContainsSet. If count is not equal to 2 or some
other Validation Failure/Warning/Error was also thrown from the method,
then the test fails.
###Be Careful
The test must cause a Validation Failure otherwise the test framework does
not get invoked. For example, in the above code, a Key of type WARNING is
being tested, however the test is purposely being failed by putting an
`@AroundInvoke` around the method with zero arguments


1. Once you have written the test and successfully run it, you now need to
generate the report. Simply invoke the following maven command

    mvn test -Dtest=ValidationKeysAuditorTest
-DconfluenceUsername=YourConfluenceUsername
-DconfluencePassword=YourConfluencePassword

The above command will create a complete test coverage report and post it
to this location [OPENEJB:Validation Keys Audit Report](openejb:validation-keys-audit-report.html)

###Quick facts about ValidationRunner and things to keep in mind while writing tests

This class is created specifically to write tests which test OpenEjb
validation code. Specifically, it is used to check the usage of keys
defined in `org.apache.openejb.config.rules.Messages.properties`. To use this
runner, simply annotate your test case with
`@RunWith(ValidationRunner.class`). Here are some things to keep in mind when writing tests:    
    1.    A test method needs to be annotated with    
`org.apache.openejb.config.rules.Keys` instead of the `org.junit.Test`    
    2.    Any usage of the @Test annotation will be ignored    
    3.    If the @Keys and @Test annotation are used together on a test     method,
then the TestCase will error out    
    4.    Every test method should create a EjbJar or EjbModule or AppModule and
return it from the method. It should list the keys being tested in the
@Keys annotation

1.    The runner will invoke the test method and use the Assembler and
ConfigurationFactory to create the application
1.This will kick off validation and this Runner will catch
ValidationFailureException and make sure that all the keys specified in the
@Keys annotation show up
in the ValidationFailureException
1. If the keys listed in the @Keys annotation match the keys found in the
ValidationFailureException, the test passes, else the test fails.
1. This Runner also validates that the keys specified in the @Keys
annotation are also available in the
org.apache.openejb.config.rules.Messages.properties file. If the key is not
found, then the Runner throws and exception resulting in your test case not
being allowed to run.
1. Sometimes you want to write a test where you do not want any
ValidationFailureException to be thrown, in those scenarios, simply
annotate your test with @Keys and do not specify any @Key in it
