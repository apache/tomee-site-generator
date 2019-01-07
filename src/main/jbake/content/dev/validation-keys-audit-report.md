Title: Validation Keys Audit Report
{warning:title=Warning}This page is auto-generated. Any manual changes
would be over-written the next time this page is regenerated{warning}
{info:title=Audit Result}h2.Out of a total of 144 keys, 123 have been
tested. Test coverage for keys is 85.41666666666666 %.{info}
{table-plus:autoNumber=true}
  
  
 | abstractAnnotatedAsBean | 
 | ann.ejb.beanClass | 
 | ann.ejb.notInterface | 
 | ann.localRemote.generalconflict | 
 | asynchronous.badExceptionType | 
 | asynchronous.badReturnType | 
 | asynchronous.methodignored | 
 | asynchronous.missing | 
 | callback.missing.possibleTypo | 
 | cannot.validate | 
 | client.missingMainClass | 
 | ignoredClassAnnotation | 
 | interfaceAnnotatedAsBean | 
 | missing.dependent.class | 
 | misslocated.class | 
 | multiplyAnnotatedAsBean | 
 | timeout.badReturnType | 
 | timeout.invalidArguments | 
 | timeout.missing.possibleTypo | 
 | timeout.tooManyMethods | 
 | xml.noEjbClass | 
{table-plus}
<a name="ValidationKeysAuditReport-Listofkeyswhichhavebeentested."></a>
## List of keys which have been tested.
{table-plus:autoNumber=true}
<table>
<tr><th> Key </td><td> Method which tests the key </th></tr>
<tr><td> ann.ejb.ejbLocalObject </td><td> [org/apache/openejb/config/rules/InvalidEjbRefTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidejbreftest.java?revision=head&view=markup-.html)
</tr>
<tr><td> ann.ejb.ejbObject </td><td> [org/apache/openejb/config/rules/InvalidEjbRefTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidejbreftest.java?revision=head&view=markup-.html)
</tr>
<tr><td> ann.invalidConcurrencyAttribute </td><td> [org/apache/openejb/config/rules/CheckInvalidConcurrencyAttributeTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidconcurrencyattributetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> ann.invalidTransactionAttribute </td><td> [org/apache/openejb/config/rules/CheckInvalidTransactionAttributeTest.annotation() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidtransactionattributetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> ann.local.noAttributes </td><td> [org/apache/openejb/config/rules/CheckInvalidAnnotatedInterfacesTest.noAttributes() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidannotatedinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> ann.localRemote.ambiguous </td><td> [org/apache/openejb/config/rules/CheckInvalidAnnotatedInterfacesTest.ambiguous() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidannotatedinterfacestest.java?revision=head&view=markup-.html)
</tr>
  
  
<tr><td> ann.notAnInterface </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> ann.remote.noAttributes </td><td> [org/apache/openejb/config/rules/CheckInvalidAnnotatedInterfacesTest.noAttributes() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidannotatedinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> ann.remoteOrLocal.converse.parent </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.test2() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> ann.remoteOrLocal.ejbHome </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> ann.remoteOrLocal.ejbLocalHome </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> ann.remoteOrLocal.ejbLocalObject </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> ann.remoteOrLocal.ejbObject </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
  
  
  
  
  
  
<tr><td> aroundInvoke.missing.possibleTypo </td><td> [org/apache/openejb/config/rules/CheckInvalidCallbacksTest.test1() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidcallbackstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> aroundInvoke.mustThrowException </td><td> [org/apache/openejb/config/rules/CheckInvalidAroundTimeoutTest.testInvalidAroundTimeoutReturnValue() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidaroundtimeouttest.java?revision=head&view=markup-.html)
</tr>
<tr><td> callback.badModifier </td><td> [org/apache/openejb/config/rules/CheckInvalidCallbacksTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidcallbackstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> callback.badReturnType </td><td> [org/apache/openejb/config/rules/CheckInvalidCallbacksTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidcallbackstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> callback.invalidArguments </td><td> [org/apache/openejb/config/rules/CheckInvalidCallbacksTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidcallbackstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> callback.missing </td><td> [org/apache/openejb/config/rules/CheckInvalidCallbacksTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidcallbackstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> callback.sessionSynchronization.invalidUse </td><td> [org/apache/openejb/config/rules/CheckInvalidCallbacksTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidcallbackstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> callback.sessionbean.invalidusage </td><td> [org/apache/openejb/config/rules/CheckInvalidCallbacksTest.test2() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidcallbackstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> conflictingSecurityAnnotations </td><td> [org/apache/openejb/config/rules/CheckInvalidSecurityAnnotationsTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidsecurityannotationstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> containerTransaction.ejbNameRequired </td><td> [org/apache/openejb/config/rules/CheckInvalidContainerTransactionTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidcontainertransactiontest.java?revision=head&view=markup-.html)
</tr>
<tr><td> containerTransaction.noSuchEjbName </td><td> [org/apache/openejb/config/rules/CheckInvalidContainerTransactionTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidcontainertransactiontest.java?revision=head&view=markup-.html)
</tr>
<tr><td> dependsOn.circuit </td><td> [org/apache/openejb/config/rules/CheckDependsOnTest.dependsOn() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkdependsontest.java?revision=head&view=markup-.html)
</tr>
<tr><td> dependsOn.noSuchEjb </td><td> [org/apache/openejb/config/rules/CheckDependsOnTest.dependsOn() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkdependsontest.java?revision=head&view=markup-.html)
</tr>
<tr><td> ejbAnnotation.onClassWithNoBeanInterface </td><td> [org/apache/openejb/config/rules/InvalidEjbRefTest.test1() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidejbreftest.java?revision=head&view=markup-.html)
</tr>
<tr><td> ejbAnnotation.onClassWithNoName </td><td> [org/apache/openejb/config/rules/InvalidEjbRefTest.test1() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidejbreftest.java?revision=head&view=markup-.html)
</tr>
<tr><td> entity.no.ejb.create </td><td> [org/apache/openejb/config/rules/CheckNoCreateMethodsTest.noCreateMethod() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checknocreatemethodstest.java?revision=head&view=markup-.html)
</tr>
  
  
<tr><td> injectionTarget.nameContainsSet </td><td> [org/apache/openejb/config/rules/CheckInjectionTargetsTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinjectiontargetstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> interceptor.callback.badReturnType </td><td> [org/apache/openejb/config/rules/CheckInvalidInterceptorTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidinterceptortest.java?revision=head&view=markup-.html)
</tr>
<tr><td> interceptor.callback.invalidArguments </td><td> [org/apache/openejb/config/rules/CheckInvalidInterceptorTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidinterceptortest.java?revision=head&view=markup-.html)
</tr>
<tr><td> interceptor.callback.missing </td><td> [org/apache/openejb/config/rules/CheckInvalidInterceptorTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidinterceptortest.java?revision=head&view=markup-.html)
</tr>
<tr><td> interceptor.callback.missing.possibleTypo </td><td> [org/apache/openejb/config/rules/CheckInvalidInterceptorTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidinterceptortest.java?revision=head&view=markup-.html)
</tr>
<tr><td> interceptor.unused </td><td> [org/apache/openejb/config/rules/CheckInvalidInterceptorTest.test1() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidinterceptortest.java?revision=head&view=markup-.html)
</tr>
<tr><td> interceptorBinding.ejbNameRequiredWithMethod </td><td> [org/apache/openejb/config/rules/CheckInvalidInterceptorTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidinterceptortest.java?revision=head&view=markup-.html)
</tr>
<tr><td> interceptorBinding.noSuchEjbName </td><td> [org/apache/openejb/config/rules/CheckInvalidInterceptorTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidinterceptortest.java?revision=head&view=markup-.html)
</tr>
<tr><td> interface.beanOnlyAnnotation </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.test1() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> interfaceMethod.beanOnlyAnnotation </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.test1() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> methodPermission.ejbNameRequired </td><td> [org/apache/openejb/config/rules/CheckInvalidMethodPermissionsTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidmethodpermissionstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> methodPermission.noSuchEjbName </td><td> [org/apache/openejb/config/rules/CheckInvalidMethodPermissionsTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidmethodpermissionstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> missing.class </td><td> [org/apache/openejb/config/rules/CheckMissingClassTest.wrongClassType() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkmissingclasstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> no.busines.method </td><td> [org/apache/openejb/config/rules/CheckNoBusinessMethodTest.noBusinessMethod() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checknobusinessmethodtest.java?revision=head&view=markup-.html)
</tr>
<tr><td> no.busines.method.args </td><td> [org/apache/openejb/config/rules/CheckNoBusinessMethodTest.noBusinessMethod() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checknobusinessmethodtest.java?revision=head&view=markup-.html)
</tr>
<tr><td> no.busines.method.case </td><td> [org/apache/openejb/config/rules/CheckNoBusinessMethodTest.noBusinessMethod() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checknobusinessmethodtest.java?revision=head&view=markup-.html)
</tr>
<tr><td> no.home.create </td><td> [org/apache/openejb/config/rules/CheckNoCreateMethodsTest.noCreateMethod() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checknocreatemethodstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> noInterfaceDeclared.entity </td><td> [org/apache/openejb/config/rules/CheckWrongClassTypeTest.wrongClassType() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkwrongclasstypetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> permitAllAndRolesAllowedOnClass </td><td> [org/apache/openejb/config/rules/CheckInvalidSecurityAnnotationsTest.test() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidsecurityannotationstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceContextAnnotation.onClassWithNoName </td><td> [org/apache/openejb/config/rules/CheckPersistenceContextUsageTest.wrongUsage() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistencecontextusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceContextAnnotation.onEntityManagerFactory </td><td> [org/apache/openejb/config/rules/CheckPersistenceContextUsageTest.wrongUsage() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistencecontextusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceContextAnnotation.onNonEntityManager </td><td> [org/apache/openejb/config/rules/CheckPersistenceContextUsageTest.wrongUsage() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistencecontextusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceContextExtented.nonStateful </td><td> [org/apache/openejb/config/rules/CheckPersistenceContextUsageTest.wrongUsage() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistencecontextusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceContextRef.noMatches </td><td> [org/apache/openejb/config/rules/CheckPersistenceContextUsageTest.noUnitName() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistencecontextusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceContextRef.noPersistenceUnits </td><td> [org/apache/openejb/config/rules/CheckPersistenceContextUsageTest.wrongUsage() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistencecontextusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceContextRef.noUnitName </td><td> [org/apache/openejb/config/rules/CheckPersistenceContextUsageTest.noUnitName() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistencecontextusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceContextRef.vagueMatches </td><td> [org/apache/openejb/config/rules/CheckPersistenceContextUsageTest.vagueMatches() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistencecontextusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceUnitAnnotation.onClassWithNoName </td><td> [org/apache/openejb/config/rules/CheckPersistenceUnitUsageTest.wrongUsage() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistenceunitusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceUnitAnnotation.onEntityManager </td><td> [org/apache/openejb/config/rules/CheckPersistenceUnitUsageTest.wrongUsage() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistenceunitusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceUnitAnnotation.onNonEntityManagerFactory </td><td> [org/apache/openejb/config/rules/CheckPersistenceUnitUsageTest.wrongUsage() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistenceunitusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceUnitRef.noMatches </td><td> [org/apache/openejb/config/rules/CheckPersistenceUnitUsageTest.noUnitName() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistenceunitusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceUnitRef.noPersistenceUnits </td><td> [org/apache/openejb/config/rules/CheckPersistenceUnitUsageTest.wrongUsage() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistenceunitusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceUnitRef.noUnitName </td><td> [org/apache/openejb/config/rules/CheckPersistenceUnitUsageTest.noUnitName() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistenceunitusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> persistenceUnitRef.vagueMatches </td><td> [org/apache/openejb/config/rules/CheckPersistenceUnitUsageTest.vagueMatches() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkpersistenceunitusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> resourceAnnotation.onClassWithNoName </td><td> [org/apache/openejb/config/rules/MistakenResourceRefUsageTest.wrongUsage() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/mistakenresourcerefusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> resourceAnnotation.onClassWithNoType </td><td> [org/apache/openejb/config/rules/MistakenResourceRefUsageTest.wrongUsage() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/mistakenresourcerefusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> resourceRef.onEntityManager </td><td> [org/apache/openejb/config/rules/MistakenResourceRefUsageTest.wrongUsage() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/mistakenresourcerefusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> resourceRef.onEntityManagerFactory </td><td> [org/apache/openejb/config/rules/MistakenResourceRefUsageTest.wrongUsage() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/mistakenresourcerefusagetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> session.no.ejb.create </td><td> [org/apache/openejb/config/rules/CheckNoCreateMethodsTest.noCreateMethod() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checknocreatemethodstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> unused.ejb.create </td><td> [org/apache/openejb/config/rules/CheckNoCreateMethodsTest.noCreateMethod() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checknocreatemethodstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> unused.ejbPostCreate </td><td> [org/apache/openejb/config/rules/CheckNoCreateMethodsTest.noCreateMethod() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checknocreatemethodstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> userTransactionRef.forbiddenForCmtdBeans </td><td> [org/apache/openejb/config/rules/CheckUserTransactionRefsTest.testSLSBwithUserTransaction() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkusertransactionrefstest.java?revision=head&view=markup-.html)
</tr>
<tr><td> wrong.class.type </td><td> [org/apache/openejb/config/rules/CheckWrongClassTypeTest.wrongClassType() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkwrongclasstypetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.businessLocal.beanClass </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBeanClass() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.businessLocal.ejbHome </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBHome() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
  
  
  
  
<tr><td> xml.businessLocal.ejbObject </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBObject() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
  
  
<tr><td> xml.businessRemote.beanClass </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBeanClass() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
  
  
<tr><td> xml.businessRemote.ejbLocalHome </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBLocalHome() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.businessRemote.ejbLocalObject </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBLocalObject() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
  
  
<tr><td> xml.businessRemote.notInterface </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testNotInterface() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.home.beanClass </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBeanClass() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.home.businessLocal </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBusinessLocal() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.home.businessRemote </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBusinessRemote() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.home.ejbLocalHome </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBLocalHome() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.home.ejbLocalObject </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBLocalObject() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.home.ejbObject </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBObject() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.home.notInterface </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testNotInterface() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.home.unknown </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testUnkown() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.invalidTransactionAttribute </td><td> [org/apache/openejb/config/rules/CheckInvalidTransactionAttributeTest.xml() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/checkinvalidtransactionattributetest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.local.beanClass </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBeanClass() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.local.businessLocal </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBusinessLocal() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.local.businessRemote </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBusinessRemote() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.local.ejbHome </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBHome() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.local.ejbLocalHome </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBLocalHome() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.local.ejbObject </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBObject() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.local.notInterface </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testNotInterface() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.local.unknown </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testUnkown() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.localHome.beanClass </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBeanClass() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.localHome.businessLocal </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBusinessLocal() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.localHome.businessRemote </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBusinessRemote() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.localHome.ejbHome </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBHome() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.localHome.ejbLocalObject </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBLocalObject() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.localHome.ejbObject </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBObject() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.localHome.notInterface </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testNotInterface() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.localHome.unknown </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testUnkown() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.localRemote.conflict </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testUnkown() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.remote.beanClass </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBeanClass() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.remote.businessLocal </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBusinessLocal() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.remote.businessRemote </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testBusinessRemote() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.remote.ejbHome </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBHome() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.remote.ejbLocalHome </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBLocalHome() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.remote.ejbLocalObject </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testEJBLocalObject() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.remote.notInterface </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testNotInterface() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
<tr><td> xml.remote.unknown </td><td> [org/apache/openejb/config/rules/InvalidInterfacesTest.testUnkown() ](-https://svn.apache.org/viewvc/tomee/tomee/trunk/container/openejb-core/src/test/java/org/apache/openejb/config/rules/invalidinterfacestest.java?revision=head&view=markup-.html)
</tr>
{table-plus}
