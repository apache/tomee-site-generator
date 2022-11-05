/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tomee.website;

/**
 * This very well could be a json file or some other externalized format.
 *
 * For the moment it is kept in code simply to keep complexity low.  That said,
 * please do not add "logic" here (no loops, if statements, string concatenation, etc)
 * and try to keep the code here limited to simple structure.
 *
 * We may very well turn this into a json or yaml file that defines an array of sources.
 * The simpler we keep this code, the easier that will be (when or if the time is right).
 */
public class Configuration {
    public static Source[] getSources() {

        final Source[] jakartaEE10 = {
                new Source("https://github.com/eclipse-ee4j/jaf.git", "2.1.0", "jakartaee-10.0-repos/activation-api"),
                new Source("https://github.com/eclipse-ee4j/common-annotations-api.git", "2.1.1", "jakartaee-10.0-repos/annotations-api"),
                new Source("https://github.com/jakartaee/authentication.git", "3.0.0-RELEASE", "jakartaee-10.0-repos/authentication-api"),
                new Source("https://github.com/jakartaee/authorization.git", "2.1.0-RELEASE", "jakartaee-10.0-repos/authorization-api"),
                new Source("https://github.com/eclipse-ee4j/batch-api.git", "2.1.1", "jakartaee-10.0-repos/batch-api"),
                new Source("https://github.com/jakartaee/cdi.git", "4.0.1", "jakartaee-10.0-repos/cdi-api"),
                new Source("https://github.com/jakartaee/concurrency.git", "3.0.2", "jakartaee-10.0-repos/concurrency-api"),
                new Source("https://github.com/eclipse-ee4j/jca-api.git", "2.1.0-RELEASE", "jakartaee-10.0-repos/connectors-api"),
                new Source("https://github.com/jakartaee/enterprise-beans.git", "4.0.1-RELEASE", "jakartaee-9.1-repos/enterprise-beans-api"),
                new Source("https://github.com/jakartaee/expression-language.git", "5.0.1-RELEASE-api", "jakartaee-10.0-repos/expression-language-api"),
                // new Source("https://github.com/jakartaee/faces.git", "4.0.1-RELEASE", "jakartaee-10.0-repos/faces-api"), // NO JAVA SOURCE, ONLY DOC.
                new Source("https://github.com/eclipse-ee4j/mojarra.git", "4.0.0-RELEASE", "jakartaee-10.0-repos/faces-impl"), // TAKE API FROM MOJARRA IMPL.
                new Source("https://github.com/eclipse-ee4j/injection-api.git", "2.0.1", "jakartaee-9.1-repos/injection-api"),
                new Source("https://github.com/jakartaee/interceptors.git", "2.1.0-RELEASE", "jakartaee-10.0-repos/interceptors-api"),
                new Source("https://github.com/eclipse-ee4j/jsonb-api.git", "3.0.0", "jakartaee-10.0-repos/json-binding-api"),
                new Source("https://github.com/eclipse-ee4j/jsonp.git", "2.1.1-RELEASE", "jakartaee-10.0-repos/json-processing-api"),
                new Source("https://github.com/eclipse-ee4j/mail.git", "2.1.0", "jakartaee-10.0-repos/mail-api"),
                new Source("https://github.com/jakartaee/messaging.git", "3.1.0-RELEASE", "jakartaee-10.0-repos/messaging-api"),
                // new Source("https://github.com/eclipse-ee4j/mvc-api.git", "2.0.1", "jakartaee-9.1-repos/mvc-api"), // NOT PART OF JAKARTA EE RELEASE
                new Source("https://github.com/jakartaee/pages.git", "3.1.0-RELEASE", "jakartaee-10.0-repos/server-pages-api"),
                new Source("https://github.com/eclipse-ee4j/jpa-api.git", "3.1-3.1.0-RELEASE", "jakartaee-10.0-repos/persistence-api"),
                new Source("https://github.com/jakartaee/rest.git", "3.1.0", "jakartaee-10.0-repos/rest-api"),
                new Source("https://github.com/jakartaee/security.git", "3.0.0-RELEASE", "jakartaee-10.0-repos/security-api"),
                new Source("https://github.com/jakartaee/servlet.git", "6.0.0-RELEASE", "jakartaee-10.0-repos/servlet-api"),
                new Source("https://github.com/eclipse-ee4j/jstl-api.git", "3.0.0-RELEASE", "jakartaee-10.0-repos/jstl-api"),
                new Source("https://github.com/jakartaee/transactions.git", "2.0.1", "jakartaee-9.1-repos/transactions-api"),
                new Source("https://github.com/jakartaee/validation.git", "3.0.2", "jakartaee-9.1-repos/validation-api"),
                new Source("https://github.com/jakartaee/websocket.git", "2.1.0-RELEASE", "jakartaee-10.0-repos/websocket-api"),
                new Source("https://github.com/eclipse-ee4j/jaxb-api.git", "4.0.0", "jakartaee-10.0-repos/xml-binding-api"),
                new Source("https://github.com/eclipse-ee4j/jax-ws-api.git", "4.0.0", "jakartaee-10.0-repos/xml-web-services-api"),
                // new Source("https://github.com/eclipse-ee4j/jws-api.git", "3.0.0", "jakartaee-9.1-repos/xml-ws-annotations-api"), // NOW PART OF JAX-WS API
                new Source("https://github.com/eclipse-ee4j/saaj-api.git", "3.0.0", "jakartaee-10.0-repos/xml-soap-ws-api"),
        };

        final Source[] jakartaEE9 = {
                new Source("https://github.com/eclipse-ee4j/jaf.git", "2.0.1", "jakartaee-9.1-repos/activation-api"),
                new Source("https://github.com/eclipse-ee4j/common-annotations-api.git", "2.0.0", "jakartaee-9.1-repos/annotations-api"),
                new Source("https://github.com/jakartaee/authentication.git", "2.0.0-RELEASE", "jakartaee-9.1-repos/authentication-api"),
                new Source("https://github.com/jakartaee/authorization.git", "2.0.0-RELEASE", "jakartaee-9.1-repos/authorization-api"),
                new Source("https://github.com/eclipse-ee4j/batch-api.git", "2.0.0", "jakartaee-9.1-repos/batch-api"),
                new Source("https://github.com/jakartaee/cdi.git", "3.0.0", "jakartaee-9.1-repos/cdi-api"), // UNABLE TO FIND WHERE IS CDI 3.0.1
                new Source("https://github.com/jakartaee/concurrency.git", "2.0.0", "jakartaee-9.1-repos/concurrency-api"),
                new Source("https://github.com/eclipse-ee4j/jca-api.git", "2.0.0", "jakartaee-9.1-repos/connectors-api"),
                new Source("https://github.com/jakartaee/enterprise-beans.git", "4.0.1-RELEASE", "jakartaee-9.1-repos/enterprise-beans-api"),
                new Source("https://github.com/jakartaee/expression-language.git", "4.0.0-RELEASE", "jakartaee-9.1-repos/expression-language-api"),
                // new Source("https://github.com/jakartaee/faces.git", "3.0.0-RELEASE", "jakartaee-9.1-repos/faces-api"), // NO JAVA SOURCE, ONLY DOC.
                new Source("https://github.com/eclipse-ee4j/mojarra", "3.0.2-RELEASE", "jakartaee-9.1-repos/faces-impl"), // TAKE API FROM MOJARRA IMPL.
                new Source("https://github.com/eclipse-ee4j/injection-api.git", "2.0.1", "jakartaee-9.1-repos/injection-api"),
                new Source("https://github.com/jakartaee/interceptors.git", "2.0.1-RELEASE", "jakartaee-9.1-repos/interceptors-api"),
                new Source("https://github.com/eclipse-ee4j/jsonb-api.git", "2.0.0-RELEASE", "jakartaee-9.1-repos/json-binding-api"),
                new Source("https://github.com/eclipse-ee4j/jsonp.git", "2.0.2-RELEASE", "jakartaee-9.1-repos/json-processing-api"),
                new Source("https://github.com/eclipse-ee4j/mail.git", "2.0.1", "jakartaee-9.1-repos/mail-api"),
                new Source("https://github.com/jakartaee/messaging.git", "3.0.0-RELEASE", "jakartaee-9.1-repos/messaging-api"),
                // new Source("https://github.com/eclipse-ee4j/mvc-api.git", "2.0.1", "jakartaee-9.1-repos/mvc-api"), // NOT PART OF JAKARTA EE RELEASE
                new Source("https://github.com/jakartaee/pages.git", "3.0.0-RELEASE", "jakartaee-9.1-repos/server-pages-api"),
                new Source("https://github.com/eclipse-ee4j/jpa-api.git", "3.0-3.0.0-RELEASE", "jakartaee-9.1-repos/persistence-api"),
                new Source("https://github.com/jakartaee/rest.git", "3.0.0", "jakartaee-9.1-repos/rest-api"),
                new Source("https://github.com/jakartaee/security.git", "2.0.0-RELEASE", "jakartaee-9.1-repos/security-api"),
                new Source("https://github.com/jakartaee/servlet.git", "5.0.0-RELEASE", "jakartaee-9.1-repos/servlet-api"),
                new Source("https://github.com/eclipse-ee4j/jstl-api.git", "2.0.0-RELEASE", "jakartaee-9.1-repos/jstl-api"),
                new Source("https://github.com/jakartaee/transactions.git", "2.0.1", "jakartaee-9.1-repos/transactions-api"),
                new Source("https://github.com/jakartaee/validation.git", "3.0.2", "jakartaee-9.1-repos/validation-api"),
                new Source("https://github.com/jakartaee/websocket.git", "2.0.0-RELEASE", "jakartaee-9.1-repos/websocket-api"),
                new Source("https://github.com/eclipse-ee4j/jaxb-api.git", "3.0.1", "jakartaee-9.1-repos/xml-binding-api"),
                new Source("https://github.com/eclipse-ee4j/jax-ws-api.git", "3.0.1", "jakartaee-9.1-repos/xml-web-services-api"),
                new Source("https://github.com/eclipse-ee4j/jws-api.git", "3.0.0", "jakartaee-9.1-repos/xml-ws-annotations-api"),
                new Source("https://github.com/eclipse-ee4j/saaj-api.git", "2.0.1", "jakartaee-9.1-repos/xml-soap-ws-api"),
        };

        final Source[] jakartaEE8 = {
                new Source("https://github.com/eclipse-ee4j/jaf.git", "1.2.2", "jakartaee-8.0-repos/activation-api"),
                new Source("https://github.com/eclipse-ee4j/common-annotations-api.git", "1.3.5", "jakartaee-8.0-repos/annotations-api"),
                new Source("https://github.com/jakartaee/authentication.git", "1.1.3-RELEASE", "jakartaee-8.0-repos/authentication-api"),
                new Source("https://github.com/jakartaee/authorization.git", "1.5.0-RELEASE", "jakartaee-8.0-repos/authorization-api"),
                new Source("https://github.com/eclipse-ee4j/batch-api.git", "jakartabatch-1021", "jakartaee-8.0-repos/batch-api"),
                new Source("https://github.com/jakartaee/cdi.git", "2.0.2", "jakartaee-8.0-repos/cdi-api"),
                new Source("https://github.com/jakartaee/concurrency.git", "1.1.2", "jakartaee-8.0-repos/concurrency-api"),
                new Source("https://github.com/eclipse-ee4j/jca-api.git", "1.7.4", "jakartaee-8.0-repos/connectors-api"),
                new Source("https://github.com/eclipse-ee4j/enterprise-deployment.git", "1.7.3", "jakartaee-8.0-repos/deployment-api"),
                new Source("https://github.com/jakartaee/enterprise-beans.git", "3.2.6-RELEASE", "jakartaee-8.0-repos/enterprise-beans-api"),
                new Source("https://github.com/jakartaee/expression-language.git", "3.0.3-RELEASE", "jakartaee-8.0-repos/expression-language-api"),
                // new Source("https://github.com/jakartaee/faces.git", "2.3.2-RELEASE", "jakartaee-8.0-repos/faces-api"), // NO JAVA SOURCE, ONLY DOC.
                new Source("https://github.com/eclipse-ee4j/mojarra.git", "2.3.17-RELEASE", "jakartaee-8.0-repos/faces-impl"), // TAKE API FROM MOJARRA IMPL.
                new Source("https://github.com/eclipse-ee4j/injection-api.git", "1.0.5", "jakartaee-8.0-repos/injection-api"),
                new Source("https://github.com/jakartaee/interceptors.git", "1.2.5-RELEASE", "jakartaee-8.0-repos/interceptors-api"),
                new Source("https://github.com/eclipse-ee4j/jsonb-api.git", "1.0-1.0.2-RELEASE", "jakartaee-8.0-repos/json-binding-api"),
                new Source("https://github.com/eclipse-ee4j/jsonp.git", "1.1-1.1.6-RELEASE", "jakartaee-8.0-repos/json-processing-api"),
                new Source("https://github.com/eclipse-ee4j/mail.git", "1.6.7", "jakartaee-8.0-repos/mail-api"),
                new Source("https://github.com/eclipse-ee4j/management-api.git", "1.1.4", "jakartaee-8.0-repos/management-api"),
                new Source("https://github.com/jakartaee/messaging.git", "2.0.3-RELEASE", "jakartaee-8.0-repos/messaging-api"),
                // new Source("https://github.com/eclipse-ee4j/mvc-api.git", "1.1.0-RELEASE", "jakartaee-8.0-repos/mvc-api"), // NOT PART OF JAKARTA EE RELEASE
                new Source("https://github.com/jakartaee/pages.git", "2.3.6-RELEASE", "jakartaee-8.0-repos/server-pages-api"),
                new Source("https://github.com/eclipse-ee4j/jpa-api.git", "2.2-2.2.3-RELEASE", "jakartaee-8.0-repos/persistence-api"),
                new Source("https://github.com/jakartaee/rest.git", "2.1.6", "jakartaee-8.0-repos/rest-api"),
                new Source("https://github.com/eclipse-ee4j/jax-rpc-api.git", "1.1.4", "jakartaee-8.0-repos/rpc-api"),
                new Source("https://github.com/jakartaee/security.git", "1.0.2-RELEASE", "jakartaee-8.0-repos/security-api"),
                new Source("https://github.com/jakartaee/servlet.git", "4.0.4-RELEASE", "jakartaee-8.0-repos/servlet-api"),
                new Source("https://github.com/eclipse-ee4j/jstl-api.git", "1.2.7-RELEASE", "jakartaee-8.0-repos/jstl-api"),
                new Source("https://github.com/jakartaee/transactions.git", "1.3.3", "jakartaee-8.0-repos/transactions-api"),
                new Source("https://github.com/jakartaee/validation.git", "2.0.2", "jakartaee-8.0-repos/validation-api"),
                new Source("https://github.com/jakartaee/websocket.git", "1.1.2-RELEASE", "jakartaee-8.0-repos/websocket-api"),
                new Source("https://github.com/eclipse-ee4j/jaxb-api.git", "2.3.3", "jakartaee-8.0-repos/xml-binding-api"),
                new Source("https://github.com/eclipse-ee4j/jax-ws-api.git", "2.3.3", "jakartaee-8.0-repos/xml-web-services-api"),
                new Source("https://github.com/eclipse-ee4j/jws-api.git", "2.1.0", "jakartaee-8.0-repos/xml-ws-annotations-api"),
                new Source("https://github.com/eclipse-ee4j/saaj-api.git", "1.4.2", "jakartaee-8.0-repos/xml-soap-ws-api"),
                new Source("https://github.com/eclipse-ee4j/jaxr-api.git", "1.0.10", "jakartaee-8.0-repos/xml-registries-api"),
        };

        final Source[] microProfile6 = new Source[]{
                new Source("https://github.com/eclipse/microprofile-config.git", "3.0.2", "microprofile-6.0-repos/config").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-fault-tolerance", "4.0.2", "microprofile-6.0-repos/fault-tolerance").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-health", "4.0.1", "microprofile-6.0-repos/health").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-jwt-auth.git", "2.1-RC6", "microprofile-6.0-repos/jwt").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-metrics", "5.0.0-RC4", "microprofile-6.0-repos/metrics").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-open-api", "3.1-RC4", "microprofile-6.0-repos/open-api").filterJavadoc(".*/api/src/main/java/.*", ""),
                // new Source("https://github.com/eclipse/microprofile-opentracing", "master", "microprofile-6.0-repos/opentracing").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-rest-client", "3.0.1", "microprofile-6.0-repos/rest-client").filterJavadoc(".*/api/src/main/java/.*", ""),
        };

        final Source[] microProfile5 = new Source[]{
                new Source("https://github.com/eclipse/microprofile-config.git", "3.0.2", "microprofile-5.0-repos/config").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-fault-tolerance", "4.0.2", "microprofile-5.0-repos/fault-tolerance").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-health", "4.0.1", "microprofile-5.0-repos/health").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-jwt-auth.git", "2.0", "microprofile-5.0-repos/jwt").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-metrics", "4.0.1", "microprofile-5.0-repos/metrics").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-open-api", "3.0", "microprofile-5.0-repos/open-api").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-opentracing", "3.0", "microprofile-5.0-repos/opentracing").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-rest-client", "3.0.1", "microprofile-5.0-repos/rest-client").filterJavadoc(".*/api/src/main/java/.*", ""),
        };

        final Source[] microProfile4 = new Source[]{
                new Source("https://github.com/eclipse/microprofile-config.git", "2.0.1", "microprofile-4.1-repos/config").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-fault-tolerance", "3.0", "microprofile-4.1-repos/fault-tolerance").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-health", "3.1", "microprofile-4.1-repos/health").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-jwt-auth.git", "1.2.2", "microprofile-4.1-repos/jwt").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-metrics", "3.0.1", "microprofile-4.1-repos/metrics").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-open-api", "2.0.1", "microprofile-4.1-repos/open-api").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-opentracing", "2.0", "microprofile-4.1-repos/opentracing").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-rest-client", "2.0", "microprofile-4.1-repos/rest-client").filterJavadoc(".*/api/src/main/java/.*", ""),
        };

        final Source[] microProfile2 = new Source[]{
                new Source("https://github.com/eclipse/microprofile-config.git", "1.3", "microprofile-2.0-repos/config").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-fault-tolerance", "1.1.4", "microprofile-2.0-repos/fault-tolerance").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-health", "1.0", "microprofile-2.0-repos/health").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-jwt-auth.git", "1.1.1", "microprofile-2.0-repos/jwt").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-metrics", "1.1.5", "microprofile-2.0-repos/metrics").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-open-api", "1.0.1", "microprofile-2.0-repos/open-api").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-opentracing", "1.1.2", "microprofile-2.0-repos/opentracing").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-rest-client", "1.1", "microprofile-2.0-repos/rest-client").filterJavadoc(".*/api/src/main/java/.*", ""),
        };

//        if (1 == 1) return new Source[0]; // uncomment this line to generate only the main site pages.
        return new Source[]{
                // TOMEE NEXT
//                new Source("https://github.com/apache/tomee.git", "main", "tomee-10.0", false).label("milestone").related(microProfile6).related(jakartaEE10).javadoc("^org.apache.(openejb|tomee).*"),
//                new Source("https://github.com/apache/tomee.git", "tomee-9.0", "tomee-9.0", true).related(microProfile5).related(jakartaEE9).javadoc("^org.apache.(openejb|tomee).*"),
//                new Source("https://github.com/apache/tomee.git", "tomee-8.x", "tomee-8.0", false).related(microProfile2).related(jakartaEE8).javadoc("^org.apache.(openejb|tomee).*"),
//                new Source("https://github.com/apache/tomee.git", "tomee-7.1.x", "tomee-7.1", false).javadoc("^org.apache.(openejb|tomee).*"),
//                new Source("https://github.com/apache/tomee.git", "tomee-7.0.x", "tomee-7.0", false).javadoc("^org.apache.(openejb|tomee).*"),
                // TOMEE CURRENT
                new Source("https://github.com/apache/tomee.git", "main", "tomee-9.0", false).label("milestone").related(microProfile5).related(jakartaEE9).javadoc("^org.apache.(openejb|tomee).*"),
                new Source("https://github.com/apache/tomee.git", "tomee-8.x", "tomee-8.0", true).related(microProfile2).related(jakartaEE8).javadoc("^org.apache.(openejb|tomee).*"),
                new Source("https://github.com/apache/tomee.git", "tomee-7.1.x", "tomee-7.1", false).javadoc("^org.apache.(openejb|tomee).*"),
                new Source("https://github.com/apache/tomee.git", "tomee-7.0.x", "tomee-7.0", false).javadoc("^org.apache.(openejb|tomee).*"),
                // JAKARTA EE
                new Source("https://github.com/eclipse-ee4j/jakartaee-platform.git", "v10.0", "jakartaee-10.0").related(jakartaEE10).javadoc("^jakarta.*"),
                new Source("https://github.com/eclipse-ee4j/jakartaee-platform.git", "v9.1", "jakartaee-9.1").related(jakartaEE9).javadoc("^jakarta.*"),
                new Source("https://github.com/eclipse-ee4j/jakartaee-platform.git", "v8", "jakartaee-8.0").related(jakartaEE8).javadoc("^javax.*"),
                // JAVA EE : no git source for v7 or less, javadoc is at https://docs.oracle.com/javaee/7/api/
                // MICRO PROFILE
                new Source("https://github.com/eclipse/microprofile.git", "6.0", "microprofile-6.0").related(microProfile6).label("milestone").javadoc("^org.eclipse.microprofile.*"),
                new Source("https://github.com/eclipse/microprofile.git", "5.0", "microprofile-5.0").related(microProfile5).javadoc("^org.eclipse.microprofile.*"),
                new Source("https://github.com/eclipse/microprofile.git", "4.1", "microprofile-4.1").related(microProfile4).javadoc("^org.eclipse.microprofile.*"),
                new Source("https://github.com/eclipse/microprofile.git", "2.0", "microprofile-2.0").related(microProfile2).javadoc("^org.eclipse.microprofile.*"),
        };
    }
}
