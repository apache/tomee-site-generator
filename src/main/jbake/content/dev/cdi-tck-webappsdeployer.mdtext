Title: CDI TCK status with webapps/ deployer

To run the TCK with the webapps/ directory deployer:

    cd openejb/tck/cdi-tomee/
    mvn clean install -Pwebapp-deployer

See [TOMEE-37](https://issues.apache.org/jira/browse/TOMEE-37)

The easiest way to run just one test is to update the `src/test/resources/passing.xml` file like so:

    <?xml version="1.0" encoding="UTF-8"?>
    <suite name="CDI TCK" verbose="0">
      <test name="CDI TCK">
        <classes>
          <class name="org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest"/>
        </classes>
      </test>
    </suite>

You can enable remote debugging for the server with the following property:

    mvn clean install -Pwebapp-deployer -Dopenejb.server.debug=true

The default port is `5005`.  That can be changed as well:

    mvn clean install -Pwebapp-deployer -Dopenejb.server.debug=true -Dserver.debug.port=5001



# Possible issues in the server

 -   testBeanWithNameJavaxEnterpriseContextConversation(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/builtin.jsf
 -   testBeanWithRequestScope(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/builtin.jsf
 -   testBeginAlreadyLongRunningConversationThrowsException(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/cumulus.jsf
 -   testConversationActiveDuringNonFacesRequest(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/cloud.jsf
 -   testConversationBeginMakesConversationLongRunning(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/cumulus.jsf
 -   testConversationEndMakesConversationTransient(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/cumulus.jsf
 -   testConversationHasDefaultTimeout(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/cumulus.jsf
 -   testConversationIdMayBeSetByApplication(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/cumulus.jsf
 -   testConversationIdMayBeSetByContainer(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/cumulus.jsf
 -   testConversationIdSetByContainerIsUnique(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/storm.jsf
 -   testConversationsDontCrossSessionBoundary1(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest)
 -   testConversationsDontCrossSessionBoundary2(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/rain.jsf
 -   testEndTransientConversationThrowsException(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/cumulus.jsf
 -   testLongRunningConversationInstancesNotDestroyedAtRequestEnd(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/storm.jsf
 -   testSetConversationTimeoutOverride(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/cumulus.jsf
 -   testTransientConversationHasNullId(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/builtin.jsf
 -   testTransientConversationInstancesDestroyedAtRequestEnd(org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ClientConversationContextTest/cloud.jsf
 -   testInvalidatingSessionDestroysConversation(org.jboss.jsr299.tck.tests.context.conversation.InvalidatingSessionDestroysConversationTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.InvalidatingSessionDestroysConversationTest/clouds.jsf
 -   testConversationPropagated(org.jboss.jsr299.tck.tests.context.conversation.LongRunningConversationPropagatedByFacesContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.LongRunningConversationPropagatedByFacesContextTest/storm.jsf
 -   testConversationPropagatedOverRedirect(org.jboss.jsr299.tck.tests.context.conversation.LongRunningConversationPropagatedByFacesContextTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.LongRunningConversationPropagatedByFacesContextTest/storm.jsf
 -   testManualCidPropagation(org.jboss.jsr299.tck.tests.context.conversation.ManualCidPropagationTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.conversation.ManualCidPropagationTest/storm.jsf
 -   testRequestScopeActiveDuringCallToEjbTimeoutMethod(org.jboss.jsr299.tck.tests.context.request.ejb.EJBRequestContextTest): Error launching test org.jboss.jsr299.tck.tests.context.request.ejb.EJBRequestContextTest at http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.request.ejb.EJBRequestContextTest/?outputMode=serializedObject&methodName=testRequestScopeActiveDuringCallToEjbTimeoutMethod. Kept on getting 404s.
 -   testRequestScopeDestroyedAfterCallToEjbTimeoutMethod(org.jboss.jsr299.tck.tests.context.request.ejb.EJBRequestContextTest): Error launching test org.jboss.jsr299.tck.tests.context.request.ejb.EJBRequestContextTest at http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.context.request.ejb.EJBRequestContextTest/?outputMode=serializedObject&methodName=testRequestScopeDestroyedAfterCallToEjbTimeoutMethod. Kept on getting 404s.
 -   testScopeTypeDeclaredInheritedIsIndirectlyInherited(org.jboss.jsr299.tck.tests.definition.scope.enterprise.EnterpriseScopeDefinitionTest): Error launching test org.jboss.jsr299.tck.tests.definition.scope.enterprise.EnterpriseScopeDefinitionTest at http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.definition.scope.enterprise.EnterpriseScopeDefinitionTest/?outputMode=serializedObject&methodName=testScopeTypeDeclaredInheritedIsIndirectlyInherited. Kept on getting 404s.
 -   testScopeTypeDeclaredInheritedIsInherited(org.jboss.jsr299.tck.tests.definition.scope.enterprise.EnterpriseScopeDefinitionTest): Error launching test org.jboss.jsr299.tck.tests.definition.scope.enterprise.EnterpriseScopeDefinitionTest at http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.definition.scope.enterprise.EnterpriseScopeDefinitionTest/?outputMode=serializedObject&methodName=testScopeTypeDeclaredInheritedIsInherited. Kept on getting 404s.
 -   testScopeTypeNotDeclaredInheritedIsNotIndirectlyInherited(org.jboss.jsr299.tck.tests.definition.scope.enterprise.EnterpriseScopeDefinitionTest): Error launching test org.jboss.jsr299.tck.tests.definition.scope.enterprise.EnterpriseScopeDefinitionTest at http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.definition.scope.enterprise.EnterpriseScopeDefinitionTest/?outputMode=serializedObject&methodName=testScopeTypeNotDeclaredInheritedIsNotIndirectlyInherited. Kept on getting 404s.
 -   testScopeTypeNotDeclaredInheritedIsNotInherited(org.jboss.jsr299.tck.tests.definition.scope.enterprise.EnterpriseScopeDefinitionTest): Error launching test org.jboss.jsr299.tck.tests.definition.scope.enterprise.EnterpriseScopeDefinitionTest at http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.definition.scope.enterprise.EnterpriseScopeDefinitionTest/?outputMode=serializedObject&methodName=testScopeTypeNotDeclaredInheritedIsNotInherited. Kept on getting 404s.
 -   testStereotypeDeclaredInheritedIsIndirectlyInherited(org.jboss.jsr299.tck.tests.definition.stereotype.enterprise.EnterpriseStereotypeDefinitionTest)
 -   testStereotypeDeclaredInheritedIsInherited(org.jboss.jsr299.tck.tests.definition.stereotype.enterprise.EnterpriseStereotypeDefinitionTest)
 -   testStereotypeNotDeclaredInheritedIsNotIndirectlyInherited(org.jboss.jsr299.tck.tests.definition.stereotype.enterprise.EnterpriseStereotypeDefinitionTest)
 -   testStereotypeNotDeclaredInheritedIsNotInherited(org.jboss.jsr299.tck.tests.definition.stereotype.enterprise.EnterpriseStereotypeDefinitionTest)
 -   testStereotypeScopeIsOverriddenByIndirectlyInheritedScope(org.jboss.jsr299.tck.tests.definition.stereotype.enterprise.EnterpriseStereotypeDefinitionTest)
 -   testStereotypeScopeIsOverriddenByInheritedScope(org.jboss.jsr299.tck.tests.definition.stereotype.enterprise.EnterpriseStereotypeDefinitionTest)
 -   test(org.jboss.jsr299.tck.tests.deployment.packaging.bundledLibrary.LibraryInEarTest)
 -   testNonStaticObserverMethodIndirectlyInherited(org.jboss.jsr299.tck.tests.event.observer.enterprise.EnterpriseEventInheritenceTest)
 -   testNonStaticObserverMethodInherited(org.jboss.jsr299.tck.tests.event.observer.enterprise.EnterpriseEventInheritenceTest)
 -   testGetEJBName(org.jboss.jsr299.tck.tests.extensions.container.event.ContainerEventTest)
 -   testGetSessionBeanType(org.jboss.jsr299.tck.tests.extensions.container.event.ContainerEventTest)
 -   testProcessAnnotatedTypeFiredForSessionBean(org.jboss.jsr299.tck.tests.extensions.container.event.ContainerEventTest)
 -   testProcessAnnotatedTypeFiredForSessionBeanInterceptor(org.jboss.jsr299.tck.tests.extensions.container.event.ContainerEventTest)
 -   testProcessInjectionTargetFiredForManagedBean(org.jboss.jsr299.tck.tests.extensions.container.event.ContainerEventTest)
 -   testProcessInjectionTargetFiredForSessionBean(org.jboss.jsr299.tck.tests.extensions.container.event.ContainerEventTest)
 -   testProcessInjectionTargetFiredForSessionBeanInterceptor(org.jboss.jsr299.tck.tests.extensions.container.event.ContainerEventTest)
 -   testProcessManagedBeanFired(org.jboss.jsr299.tck.tests.extensions.container.event.ContainerEventTest)
 -   testProcessSessionBeanFiredForStatefulSessionBean(org.jboss.jsr299.tck.tests.extensions.container.event.ContainerEventTest)
 -   testProcessSessionBeanFiredForStatelessSessionBean(org.jboss.jsr299.tck.tests.extensions.container.event.ContainerEventTest)
 -   testTypeOfProcessInjectionTargetParameter(org.jboss.jsr299.tck.tests.extensions.container.event.ContainerEventTest)
 -   testProcessSessionBeanEvent(org.jboss.jsr299.tck.tests.extensions.processBean.ProcessSessionBeanTest)
 -   testDefaultValidatorBean(org.jboss.jsr299.tck.tests.implementation.builtin.BuiltInBeansTest)
 -   testDefaultValidatorFactoryBean(org.jboss.jsr299.tck.tests.implementation.builtin.BuiltInBeansTest)
 -   testPrincipalBean(org.jboss.jsr299.tck.tests.implementation.builtin.BuiltInBeansTest)
 -   testUserTransactionBean(org.jboss.jsr299.tck.tests.implementation.builtin.BuiltInBeansTest)
 -   testBeanTypesAreLocalInterfacesWithoutWildcardTypesOrTypeVariablesWithSuperInterfaces(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanDefinitionTest)
 -   testBeanWithNamedAnnotation(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanDefinitionTest)
 -   testBeanWithQualifiers(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanDefinitionTest)
 -   testBeanWithScopeAnnotation(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanDefinitionTest)
 -   testBeanWithStereotype(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanDefinitionTest)
 -   testConstructorAnnotatedInjectCalled(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanDefinitionTest)
 -   testDefaultName(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanDefinitionTest)
 -   testEnterpriseBeanClassLocalView(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanDefinitionTest)
 -   testObjectIsInAPITypes(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanDefinitionTest)
 -   testRemoteInterfacesAreNotInAPITypes(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanDefinitionTest)
 -   testSingletonWithApplicationScopeOK(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanDefinitionTest)
 -   testSingletonWithDependentScopeOK(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanDefinitionTest)
 -   testStatelessMustBeDependentScoped(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanDefinitionTest)
 -   testEjbDeclaredInXmlNotSimpleBean(org.jboss.jsr299.tck.tests.implementation.enterprise.definition.EnterpriseBeanViaXmlTest)
 -   testCreateSLSB(org.jboss.jsr299.tck.tests.implementation.enterprise.lifecycle.EnterpriseBeanLifecycleTest)
 -   testDependentObjectsDestroyed(org.jboss.jsr299.tck.tests.implementation.enterprise.lifecycle.EnterpriseBeanLifecycleTest)
 -   testDestroyRemovesSFSB(org.jboss.jsr299.tck.tests.implementation.enterprise.lifecycle.EnterpriseBeanLifecycleTest)
 -   testDirectSubClassInheritsPostConstructOnSuperclass(org.jboss.jsr299.tck.tests.implementation.enterprise.lifecycle.EnterpriseBeanLifecycleTest)
 -   testIndirectSubClassInheritsPostConstructOnSuperclass(org.jboss.jsr299.tck.tests.implementation.enterprise.lifecycle.EnterpriseBeanLifecycleTest)
 -   testIndirectSubClassInheritsPreDestroyOnSuperclass(org.jboss.jsr299.tck.tests.implementation.enterprise.lifecycle.EnterpriseBeanLifecycleTest)
 -   testInitializerMethodsCalledWithCurrentParameterValues(org.jboss.jsr299.tck.tests.implementation.enterprise.lifecycle.EnterpriseBeanLifecycleTest)
 -   testRemovedEjbIgnored(org.jboss.jsr299.tck.tests.implementation.enterprise.lifecycle.EnterpriseBeanLifecycleTest)
 -   testSerializeSFSB(org.jboss.jsr299.tck.tests.implementation.enterprise.lifecycle.EnterpriseBeanLifecycleTest)
 -   testSubClassInheritsPreDestroyOnSuperclass(org.jboss.jsr299.tck.tests.implementation.enterprise.lifecycle.EnterpriseBeanLifecycleTest)
 -   testNewBeanHasNoDisposalMethods(org.jboss.jsr299.tck.tests.implementation.enterprise.newBean.NewEnterpriseBeanICTest)
 -   testNewBeanHasNoProducerMethods(org.jboss.jsr299.tck.tests.implementation.enterprise.newBean.NewEnterpriseBeanICTest)
 -   testNewBeanHasSameConstructor(org.jboss.jsr299.tck.tests.implementation.enterprise.newBean.NewEnterpriseBeanICTest)
 -   testNewBeanHasSameInitializers(org.jboss.jsr299.tck.tests.implementation.enterprise.newBean.NewEnterpriseBeanICTest)
 -   testForEachEnterpriseBeanANewBeanExists(org.jboss.jsr299.tck.tests.implementation.enterprise.newBean.NewEnterpriseBeanTest)
 -   testNewBeanHasNoBeanELName(org.jboss.jsr299.tck.tests.implementation.enterprise.newBean.NewEnterpriseBeanTest)
 -   testNewBeanHasNoObservers(org.jboss.jsr299.tck.tests.implementation.enterprise.newBean.NewEnterpriseBeanTest)
 -   testNewBeanHasNoStereotypes(org.jboss.jsr299.tck.tests.implementation.enterprise.newBean.NewEnterpriseBeanTest)
 -   testNewBeanIsDependentScoped(org.jboss.jsr299.tck.tests.implementation.enterprise.newBean.NewEnterpriseBeanTest)
 -   testNewBeanIsHasOnlyNewBinding(org.jboss.jsr299.tck.tests.implementation.enterprise.newBean.NewEnterpriseBeanTest)
 -   testApplicationCannotCallRemoveMethodOnNonDependentScopedSessionEnterpriseBean(org.jboss.jsr299.tck.tests.implementation.enterprise.remove.EnterpriseBeanRemoveMethodTest):
 -   testApplicationMayCallAnyRemoveMethodOnDependentScopedSessionEnterpriseBeans(org.jboss.jsr299.tck.tests.implementation.enterprise.remove.EnterpriseBeanRemoveMethodTest)
 -   testApplicationMayCallRemoveMethodOnDependentScopedSessionEnterpriseBeansButNoParametersArePassed(org.jboss.jsr299.tck.tests.implementation.enterprise.remove.EnterpriseBeanRemoveMethodTest)
 -   testInitializerMethodNotABusinessMethod(org.jboss.jsr299.tck.tests.implementation.initializer.EjbInitializerMethodTest)
 -   testBindingTypeOnInitializerParameter(org.jboss.jsr299.tck.tests.implementation.initializer.InitializerMethodTest)
 -   testMultipleInitializerMethodsAreCalled(org.jboss.jsr299.tck.tests.implementation.initializer.InitializerMethodTest)
 -   testStaticProducerField(org.jboss.jsr299.tck.tests.implementation.producer.field.definition.enterprise.EnterpriseProducerFieldDefinitionTest)
 -   testNonStaticProducerMethodInheritedBySpecializingSubclass(org.jboss.jsr299.tck.tests.implementation.producer.method.definition.enterprise.EnterpriseProducerMethodDefinitionTest)
 -   testNonStaticProducerMethodNotIndirectlyInherited(org.jboss.jsr299.tck.tests.implementation.producer.method.definition.enterprise.EnterpriseProducerMethodDefinitionTest)
 -   testNonStaticProducerMethodNotInherited(org.jboss.jsr299.tck.tests.implementation.producer.method.definition.enterprise.EnterpriseProducerMethodDefinitionTest)
 -   testConstructorHasDisposesParameter(org.jboss.jsr299.tck.tests.implementation.simple.definition.constructorHasDisposesParameter.ConstructorHasDisposesParameterTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testConstructorHasObservesParameter(org.jboss.jsr299.tck.tests.implementation.simple.definition.constructorHasObservesParameter.ConstructorHasObservesParameterTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testNonDependentScopedBeanCanNotHavePublicField(org.jboss.jsr299.tck.tests.implementation.simple.definition.dependentWithPublicField.DependentWithPublicFieldTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testTooManyInitializerAnnotatedConstructor(org.jboss.jsr299.tck.tests.implementation.simple.definition.tooManyInitializerAnnotatedConstructors.TooManyInitializerAnnotatedConstructorsTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testNormalScopedUnproxyableBeanThrowsException(org.jboss.jsr299.tck.tests.implementation.simple.lifecycle.unproxyable.UnproxyableManagedBeanTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testInjectionOfEjbs(org.jboss.jsr299.tck.tests.implementation.simple.resource.ejb.EjbInjectionTest)
 -   testPassivationOfEjbs(org.jboss.jsr299.tck.tests.implementation.simple.resource.ejb.EjbInjectionTest)
 -   testSpecializedBeanNotInstantiated(org.jboss.jsr299.tck.tests.inheritance.specialization.enterprise.EnterpriseBeanSpecializationIntegrationTest)
 -   testSpecializingBeanHasBindingsOfSpecializedAndSpecializingBean(org.jboss.jsr299.tck.tests.inheritance.specialization.enterprise.EnterpriseBeanSpecializationTest)
 -   testSpecializingBeanHasNameOfSpecializedBean(org.jboss.jsr299.tck.tests.inheritance.specialization.enterprise.EnterpriseBeanSpecializationTest)
 -   testInterceptorsDeclaredUsingInterceptorsCalledBeforeInterceptorBinding(org.jboss.jsr299.tck.tests.interceptors.definition.enterprise.interceptorOrder.SessionBeanInterceptorOrderTest)
 -   testNonContextualSessionBeanReferenceIsIntercepted(org.jboss.jsr299.tck.tests.interceptors.definition.enterprise.nonContextualReference.SessionBeanInterceptorOnNonContextualEjbReferenceTest)
 -   testSessionBeanIsIntercepted(org.jboss.jsr299.tck.tests.interceptors.definition.enterprise.simpleInterception.SessionBeanInterceptorDefinitionTest)
 -   testAnnotationMemberWithoutNonBinding(org.jboss.jsr299.tck.tests.lookup.binding.members.annotation.BindingAnnotationWithMemberTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testArrayMemberWithoutNonBinding(org.jboss.jsr299.tck.tests.lookup.binding.members.array.BindingAnnotationWithMemberTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testDuplicateNamedBeans(org.jboss.jsr299.tck.tests.lookup.byname.duplicateNameResolution.DuplicateNameResolutionTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testDuplicateBeanNamePrefix(org.jboss.jsr299.tck.tests.lookup.byname.duplicatePrefixResolution.DuplicateNamePrefixResolutionTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testInjectionPointWithArrayType(org.jboss.jsr299.tck.tests.lookup.clientProxy.unproxyable.array.ArrayTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testInjectionPointWithUnproxyableTypeWhichResolvesToNormalScopedBean(org.jboss.jsr299.tck.tests.lookup.clientProxy.unproxyable.finalClass.FinalClassTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testClassWithFinalMethodCannotBeProxied(org.jboss.jsr299.tck.tests.lookup.clientProxy.unproxyable.finalMethod.FinalMethodTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testInjectionPointWithUnproxyableTypeWhichResolvesToNormalScopedBean(org.jboss.jsr299.tck.tests.lookup.clientProxy.unproxyable.primitive.UnproxyableTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testClassWithPrivateConstructor(org.jboss.jsr299.tck.tests.lookup.clientProxy.unproxyable.privateConstructor.PrivateConstructorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testELResolverRegisteredWithJsf(org.jboss.jsr299.tck.tests.lookup.el.integration.IntegrationWithUnifiedELTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.lookup.el.integration.IntegrationWithUnifiedELTest/JSFTestPage.jsf
 -   testInitializerCalledAfterFieldInjectionOfSuperclass(org.jboss.jsr299.tck.tests.lookup.injection.enterprise.SessionBeanInjectionOrderingTest)
 -   testInitializerCalledAfterResourceInjection(org.jboss.jsr299.tck.tests.lookup.injection.enterprise.SessionBeanInjectionOrderingTest)
 -   testPostConstructCalledAfterInitializerOfSuperclass(org.jboss.jsr299.tck.tests.lookup.injection.enterprise.SessionBeanInjectionOrderingTest)
 -   testFieldDeclaredInSuperclassIndirectlyInjected(org.jboss.jsr299.tck.tests.lookup.injection.enterprise.SessionBeanInjectionTest)
 -   testFieldDeclaredInSuperclassInjected(org.jboss.jsr299.tck.tests.lookup.injection.enterprise.SessionBeanInjectionTest)
 -   testInjectionOnContextualSessionBean(org.jboss.jsr299.tck.tests.lookup.injection.enterprise.SessionBeanInjectionTest)
 -   testInjectionOnEJBInterceptor(org.jboss.jsr299.tck.tests.lookup.injection.enterprise.SessionBeanInjectionTest)
 -   testInjectionOnNonContextualSessionBean(org.jboss.jsr299.tck.tests.lookup.injection.enterprise.SessionBeanInjectionTest)
 -   testInjectionIntoJSFManagedBean(org.jboss.jsr299.tck.tests.lookup.injection.non.contextual.InjectionIntoNonContextualComponentTest): 500 Internal Server Error for http://127.0.0.1:51857/org.jboss.jsr299.tck.tests.lookup.injection.non.contextual.InjectionIntoNonContextualComponentTest/ManagedBeanTestPage.jsf
 -   testPrimitiveInjectionPointResolvesToNullableWebBean(org.jboss.jsr299.tck.tests.lookup.injection.nullableBean.NullableBeanTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testBeanTypesOnSessionBean(org.jboss.jsr299.tck.tests.lookup.typesafe.resolution.EnterpriseResolutionByTypeTest)
 -   testDecoratorNotResolved(org.jboss.jsr299.tck.tests.lookup.typesafe.resolution.decorator.DecoratorNotInjectedTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown

# Issues likely with the deployer itself

 -   testPassivationCapableBeanWithNonPassivatingDecoratorBeanConstructorFails(org.jboss.jsr299.tck.tests.context.passivating.broken.decoratorWithNonPassivatingBeanConstructor.DecoratorWithNonPassivatingBeanConstructorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testPassivationCapableBeanWithNonPassivatingInitializerInDecoratorFails(org.jboss.jsr299.tck.tests.context.passivating.broken.decoratorWithNonPassivatingInitializerMethod.DecoratorWithNonPassivatingInitializerMethodTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testPassivationCapableBeanWithNonPassivatingDecoratorInjectedFieldFails(org.jboss.jsr299.tck.tests.context.passivating.broken.decoratorWithNonPassivatingInjectedField.DecoratorWithNonPassivatingInjectedFieldTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSessionBeanWithNonPassivatingBeanConstructorParamInInterceptorFails(org.jboss.jsr299.tck.tests.context.passivating.broken.enterpriseBeanWithNonPassivatingBeanConstructorParameterInInterceptor.EnterpriseBeanWithNonPassivatingBeanConstructorParameterInInterceptorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSessionBeanWithNonPassivatingConstructorFieldInDecoratorFails(org.jboss.jsr299.tck.tests.context.passivating.broken.enterpriseBeanWithNonPassivatingConstructorFieldInDecorator.EnterpriseBeanWithNonPassivatingFieldInDecoratorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSessionBeanWithNonPassivatingInitializerFieldInDecoratorFails(org.jboss.jsr299.tck.tests.context.passivating.broken.enterpriseBeanWithNonPassivatingInitializerInDecorator.EnterpriseBeanWithNonPassivatingInitializerInDecoratorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSessionBeanWithNonPassivatingInitializerParamInInterceptorFails(org.jboss.jsr299.tck.tests.context.passivating.broken.enterpriseBeanWithNonPassivatingInitializerParameterInInterceptor.EnterpriseBeanWithNonPassivatingInitializerParameterInInterceptorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSessionBeanWithNonPassivatingInjectedFieldInDecoratorFails(org.jboss.jsr299.tck.tests.context.passivating.broken.enterpriseBeanWithNonPassivatingInjectedFieldInDecorator.EnterpriseBeanWithNonPassivatingInjectedFieldInDecoratorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSessionBeanWithNonPassivatingInjectedFieldInInterceptorFails(org.jboss.jsr299.tck.tests.context.passivating.broken.enterpriseBeanWithNonPassivatingInjectedFieldInInterceptor.EnterpriseBeanWithNonPassivatingInjectedFieldInInterceptorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testDependentBeanWithNonSerializableImplementationInStatefulSessionBeanInitializerFails(org.jboss.jsr299.tck.tests.context.passivating.broken.enterpriseBeanWithNonSerializableIntializerMethod.EnterpriseBeanWithNonSerializableIntializerMethodTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testNonPassivationCapableProducerFieldNotOk(org.jboss.jsr299.tck.tests.context.passivating.broken.finalProducerFieldNotPassivationCapable.NonPassivationCapableProducerFieldTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testPassivationCapableBeanWithNonPassivatingInterceptorFails(org.jboss.jsr299.tck.tests.context.passivating.broken.interceptorWithNonPassivatingBeanConstructorParameter.PassivationCapableBeanWithNonPassivatingInterceptorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testPassivationCapableBeanWithNonPassivatingInterceptorFails(org.jboss.jsr299.tck.tests.context.passivating.broken.interceptorWithNonPassivatingInitializerMethodParameter.PassivationCapableBeanWithNonPassivatingInterceptorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testPassivationCapableBeanWithNonPassivatingInterceptorFails(org.jboss.jsr299.tck.tests.context.passivating.broken.interceptorWithNonPassivatingInjectedField.PassivationCapableBeanWithNonPassivatingInterceptorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testManagedBeanWithNonPassivatingDecoratorFails(org.jboss.jsr299.tck.tests.context.passivating.broken.managedBeanWithNonPassivatingDecorator.ManagedBeanWithNonPassivatingDecoratorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testManagedBeanWithNonSerializableInterceptorClassNotOK(org.jboss.jsr299.tck.tests.context.passivating.broken.managedBeanWithNonSerializableInterceptorClass.ManagedBeanWithNonSerializableInterceptorClassTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSimpleWebBeanWithNonSerializableImplementationClassFails(org.jboss.jsr299.tck.tests.context.passivating.broken.nonPassivationCapableManagedBeanHasPassivatingScope.NonPassivationManagedBeanHasPassivatingScopeTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testNonPassivationCapableProducerFieldNotOk(org.jboss.jsr299.tck.tests.context.passivating.broken.nonPassivationCapableProducerField.NonPassivationCapableProducerFieldTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testNonPassivationCapableProducerMethodNotOk(org.jboss.jsr299.tck.tests.context.passivating.broken.nonPassivationCapableProducerMethod.NonPassivationCapableProducerMethodTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSimpleDependentWebBeanWithNonSerializableImplementationInjectedIntoConstructorParameterOfWebBeanWithPassivatingScopeFails(org.jboss.jsr299.tck.tests.context.passivating.broken.passivatingManagedBeanWithNonPassivatingBeanConstructor.PassivatingManagedBeanWithNonPassivatingBeanConstructorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSimpleDependentWebBeanWithNonSerializableImplementationInjectedIntoInitializerParameterOfWebBeanWithPassivatingScopeFails(org.jboss.jsr299.tck.tests.context.passivating.broken.passivatingManagedBeanWithNonPassivatingInitializerMethod.PassivatingManagedBeanWithNonPassivatingInitializerMethodTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSimpleDependentWebBeanWithNonSerializableImplementationInjectedIntoNonTransientFieldOfWebBeanWithPassivatingScopeFails(org.jboss.jsr299.tck.tests.context.passivating.broken.passivatingManagedBeanWithNonPassivatingInjcetedField.PassivatingManagedBeanWithNonPassivatingInjcetedFieldTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSimpleDependentWebBeanWithNonSerializableImplementationInjectedIntoProducerMethodParameterWithPassivatingScopeFails(org.jboss.jsr299.tck.tests.context.passivating.broken.passivatingProducerMethodWithNonPassivatingParameter.PassivatingProducerMethodWithNonPassivatingParameterTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSimpleDependentWebBeanWithNonSerializableImplementationInjectedIntoStatefulSessionBeanFails(org.jboss.jsr299.tck.tests.context.passivating.broken.unserializableSimpleInjectedIntoPassivatingEnterpriseBean.UnserializableSimpleInjectedIntoPassivatingEnterpriseBeanTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testCustomDecoratorDecoratingFinalBean(org.jboss.jsr299.tck.tests.decorators.custom.broken.finalBeanClass.CustomDecoratorMatchingBeanWithFinalClassTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testDecoratorListedTwiceInBeansXmlNotOK(org.jboss.jsr299.tck.tests.decorators.definition.broken.decoratorListedTwiceInBeansXml.DecoratorListedTwiceInBeansXmlTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testEnabledDecoratorNotADecoratorTest(org.jboss.jsr299.tck.tests.decorators.definition.broken.enabledDecoratorIsNotDecorator.EnabledDecoratorNotADecoratorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testAppliesToFinalManagedBeanClass(org.jboss.jsr299.tck.tests.decorators.definition.broken.finalBeanClass.FinalBeanClassTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testAppliesToFinalMethodOnManagedBeanClass(org.jboss.jsr299.tck.tests.decorators.definition.broken.finalBeanMethod.FinalBeanMethodTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testMultipleDelegateInjectionPoints(org.jboss.jsr299.tck.tests.decorators.definition.broken.multipleDelegateInjectionPoints.MultipleDelegateInjectionPointsTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testNoDelegateInjectionPoints(org.jboss.jsr299.tck.tests.decorators.definition.broken.noDelegateInjectionPoints.NoDelegateInjectionPointsTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testNonDecoratorWithDecoratesAnnotationNotOK(org.jboss.jsr299.tck.tests.decorators.definition.broken.nonDecoratorWithDecorates.NonDecoratorWithDecoratesTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testNonExistantDecoratorClassInBeansXmlNotOK(org.jboss.jsr299.tck.tests.decorators.definition.broken.nonExistantClassInBeansXml.NonExistantDecoratorClassInBeansXmlTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testNotAllDecoratedTypesImplemented(org.jboss.jsr299.tck.tests.decorators.definition.broken.notAllDecoratedTypesImplemented.NotAllDecoratedTypesImplementedTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testDecoratorDelegateInjectionPoints(org.jboss.jsr299.tck.tests.decorators.definition.inject.broken.delegateProducerMethod.DelegateInjectionPointTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testNonDependentGenericManagedBeanNotOk(org.jboss.jsr299.tck.tests.definition.bean.genericbroken.GenericManagedBeanTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testTooManyScopesSpecifiedInJava(org.jboss.jsr299.tck.tests.definition.scope.broken.tooManyScopes.TooManyScopesTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testStereotypeWithNonEmptyNamed(org.jboss.jsr299.tck.tests.definition.stereotype.broken.nonEmptyNamed.NonEmptyNamedTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testMultipleIncompatibleScopeStereotypes(org.jboss.jsr299.tck.tests.definition.stereotype.broken.scopeConflict.IncompatibleStereotypesTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testStereotypeWithTooManyScopeTypes(org.jboss.jsr299.tck.tests.definition.stereotype.broken.tooManyScopes.TooManyScopeTypesTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testObserverDefinitionErrorTreatedAsDefinitionError(org.jboss.jsr299.tck.tests.deployment.lifecycle.broken.addDefinitionError.AddDefinitionErrorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testObserverDeploymentProblemTreatedAsDeploymentError(org.jboss.jsr299.tck.tests.deployment.lifecycle.broken.addDeploymentProblem.AddDeploymentProblemTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testThrowsException(org.jboss.jsr299.tck.tests.deployment.lifecycle.broken.beanDiscoveryMethodThrowsException.BeforeBeanDiscoveryThrowsExceptionTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testObserverFailureTreatedAsDefinitionError(org.jboss.jsr299.tck.tests.deployment.lifecycle.broken.exceptionInAfterBeanDiscoveryObserver.AfterBeanDiscoveryObserverExecutionFailureTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testObserverFailureTreatedAsDeploymentError(org.jboss.jsr299.tck.tests.deployment.lifecycle.broken.exceptionInAfterBeanValidationObserver.AfterDeploymentValidationObserverExecutionFailureTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testDeploymentFailsDuringValidation(org.jboss.jsr299.tck.tests.deployment.lifecycle.broken.failsDuringValidation.AfterBeanDiscoveryFailureTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testAddingScopeType(org.jboss.jsr299.tck.tests.deployment.lifecycle.broken.normalScope.AddingNormalScopeTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testAddingScopeType(org.jboss.jsr299.tck.tests.deployment.lifecycle.broken.passivatingScope.AddingPassivatingScopeTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testAnyAnnotationOnEventInjectionPointWithoutTypeParameterFails(org.jboss.jsr299.tck.tests.event.broken.inject.withoutType.EventInjectionWithoutTypeTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testDependentBeanWithConditionalObserverMethodIsDefinitionError(org.jboss.jsr299.tck.tests.event.broken.observer.dependentIsConditionalObserver.DependentIsConditionalObserverTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testObserverMethodWithDisposesParamFails(org.jboss.jsr299.tck.tests.event.broken.observer.isDisposer.ObserverMethodAnnotatedDisposesTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testObserverMethodAnnotatedInitializerFails(org.jboss.jsr299.tck.tests.event.broken.observer.isInitializer.ObserverMethodAnnotatedInitialzerTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testObserverMethodAnnotatedProducesFails(org.jboss.jsr299.tck.tests.event.broken.observer.isProducer.ObserverMethodAnnotatedProducesTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testObserverMethodMustHaveOnlyOneEventParameter(org.jboss.jsr299.tck.tests.event.broken.observer.tooManyParameters.ObserverMethodWithTwoEventParametersTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testProcessAnnotatedTypeEventThrowsExceptionNotOk(org.jboss.jsr299.tck.tests.extensions.annotated.broken.processAnnotatedObserverThrowsException.ProcessAnnotatedTypeEventThrowsExceptionTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testProcessInjectionTargetEventThrowsExceptionNotOk(org.jboss.jsr299.tck.tests.extensions.annotated.broken.processInjectionTargetThrowsException.ProcessInjectionTargetEventThrowsExceptionTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testAddDefinitionError(org.jboss.jsr299.tck.tests.extensions.container.event.broken.processBeanObserverRegistersException.AddDefinitionErrorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testProcessBeanObserverThrowsException(org.jboss.jsr299.tck.tests.extensions.container.event.broken.processBeanObserverThrowsException.ThrowExceptionInProcessBeanObserverTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testAddDefinitionError(org.jboss.jsr299.tck.tests.extensions.observer.broken.definitionError.ProcessObserverMethodErrorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testExceptionIsDefinitionError(org.jboss.jsr299.tck.tests.extensions.observer.broken.exception.ProcessObserverMethodExceptionTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testAddingDefinitionError(org.jboss.jsr299.tck.tests.extensions.producer.broken.injectionTargetError.InjectionTargetDefinitionErrorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testInitializerUnallowed(org.jboss.jsr299.tck.tests.implementation.disposal.method.definition.broken.initializerUnallowed.InitializerUnallowedDefinitionTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testMultipleDisposeParameters(org.jboss.jsr299.tck.tests.implementation.disposal.method.definition.broken.multiParams.MultipleDisposeParametersDefinitionTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testObserverParameterUnallowed(org.jboss.jsr299.tck.tests.implementation.disposal.method.definition.broken.observesUnallowed.ObserverParameterUnallowedDefinitionTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testProducesUnallowed(org.jboss.jsr299.tck.tests.implementation.disposal.method.definition.broken.producesUnallowed.ProducesUnallowedDefinitionTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testUnresolvedDisposalMethod(org.jboss.jsr299.tck.tests.implementation.disposal.method.definition.broken.unresolvedMethod.UnresolvedDisposalMethodDefinitionTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testInitializerMethodAnnotatedProduces(org.jboss.jsr299.tck.tests.implementation.initializer.broken.methodAnnotatedProduces.InitializerMethodAnnotatedProducesTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testInitializerMethodHasParameterAnnotatedDisposes(org.jboss.jsr299.tck.tests.implementation.initializer.broken.parameterAnnotatedDisposes.ParameterAnnotatedDisposesTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testInitializerMethodHasParameterAnnotatedObserves(org.jboss.jsr299.tck.tests.implementation.initializer.broken.parameterAnnotatedObserves.ParameterAnnotatedObservesTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testParameterizedReturnTypeWithWildcard(org.jboss.jsr299.tck.tests.implementation.producer.field.definition.broken.parameterizedReturnTypeWithWildcard.ParameterizedReturnTypeWithWildcardTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testProducerMethodWithParameterAnnotatedDisposes(org.jboss.jsr299.tck.tests.implementation.producer.method.broken.parameterAnnotatedDisposes.ParameterAnnotatedDisposesTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testProducerMethodWithParameterAnnotatedObserves(org.jboss.jsr299.tck.tests.implementation.producer.method.broken.parameterAnnotatedObserves.ParameterAnnotatedObservesTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testParameterizedType(org.jboss.jsr299.tck.tests.implementation.producer.method.broken.parameterizedTypeWithTypeParameter2.ParameterizedTypeWithTypeParameterTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testParameterizedReturnTypeWithWildcard(org.jboss.jsr299.tck.tests.implementation.producer.method.broken.parameterizedTypeWithWildcard.ParameterizedTypeWithWildcardTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSpecializedMethodIndirectlyOverridesAnotherProducerMethod(org.jboss.jsr299.tck.tests.inheritance.specialization.producer.method.broken.indirectOverride.IndirectOverrideTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSpecializedStaticMethod(org.jboss.jsr299.tck.tests.inheritance.specialization.producer.method.broken.specializesStaticMethod.SpecializesStaticMethodTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSpecializingAndSpecializedBeanHasName(org.jboss.jsr299.tck.tests.inheritance.specialization.producer.method.broken.specializingAndSpecializedBeanHaveName.SpecializingAndSpecializedBeanHaveNameTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testInconsistentSpecialization(org.jboss.jsr299.tck.tests.inheritance.specialization.simple.broken.inconsistent.InconsistentSpecializationTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSpecializingAndSpecializedBeanHasName(org.jboss.jsr299.tck.tests.inheritance.specialization.simple.broken.names.SpecializingAndSpecializedBeanHasNameTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSpecializingClassImplementsInterfaceAndExtendsNothing(org.jboss.jsr299.tck.tests.inheritance.specialization.simple.broken.noextend1.SpecializingBeanImplementsInterfaceOnly): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSpecializingClassDirectlyExtendsNothing(org.jboss.jsr299.tck.tests.inheritance.specialization.simple.broken.noextend2.SpecializingBeanExtendsNothingTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSpecializingClassExtendsNonSimpleBean(org.jboss.jsr299.tck.tests.inheritance.specialization.simple.broken.noextend3.SpecializingClassExtendsNonSimpleBeanTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testInterceptorCanNotAlsoBeDecorator(org.jboss.jsr299.tck.tests.interceptors.definition.broken.interceptorCanNotBeDecorator.InterceptorCanNotBeDecoratorTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testInterceptorBindingsWithConflictingAnnotationMembersNotOk(org.jboss.jsr299.tck.tests.interceptors.definition.broken.invalidBindingAnnotations.InvalidInterceptorBindingAnnotationsTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testNonExistantClassInBeansXmlNotOk(org.jboss.jsr299.tck.tests.interceptors.definition.broken.nonExistantClassInBeansXml.NonExistantClassInBeansXmlTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testNonInterceptorClassInBeansXmlNotOk(org.jboss.jsr299.tck.tests.interceptors.definition.broken.nonInterceptorClassInBeansXml.NonInterceptorClassInBeansXmlTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSameInterceptorClassListedTwiceInBeansXmlNotOk(org.jboss.jsr299.tck.tests.interceptors.definition.broken.sameClassListedTwiceInBeansXml.SameClassListedTwiceInBeansXmlTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testAmbiguousDependency(org.jboss.jsr299.tck.tests.lookup.dependency.resolution.broken.ambiguous.AmbiguousDependencyTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testUnsatisfiedDependency(org.jboss.jsr299.tck.tests.lookup.dependency.resolution.broken.unsatisfied.UnsatisfiedDependencyTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testSessionScopedBeanWithInjectionPoint(org.jboss.jsr299.tck.tests.lookup.injectionpoint.broken.normal.scope.NormalScopedBeanWithInjectionPoint): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testDefinitionErrorDetected(org.jboss.jsr299.tck.tests.lookup.injectionpoint.broken.not.bean.InjectionPointTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testPrimitiveInjectionPointResolvedToNonPrimitiveProducerMethod(org.jboss.jsr299.tck.tests.lookup.typesafe.resolution.broken.primitive.PrimitiveInjectionPointTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   testTypeVariableInjectionPoint(org.jboss.jsr299.tck.tests.lookup.typesafe.resolution.broken.type.variable.TypeVariableInjectionPointTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   test(org.jboss.jsr299.tck.tests.policy.broken.incorrect.name.NoClassWithSpecifiedNameTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   test(org.jboss.jsr299.tck.tests.policy.broken.incorrect.name.stereotype.NoAnnotationWithSpecifiedNameTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   test(org.jboss.jsr299.tck.tests.policy.broken.not.policy.ClassIsNotPolicyTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   test(org.jboss.jsr299.tck.tests.policy.broken.not.policy.stereotype.ClassIsNotPolicyTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown
 -   test(org.jboss.jsr299.tck.tests.policy.broken.same.type.twice.SameTypeListedTwiceTest): Expected exception class org.jboss.jsr299.tck.DeploymentFailure but none was thrown


