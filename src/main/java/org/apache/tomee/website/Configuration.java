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

//        final Source[] jakartaEE10 = {
//                new Source("https://github.com/eclipse-ee4j/common-annotations-api.git", "master", "jakartaee-10.0-repos/common-annotations-api"),
//                new Source("https://github.com/eclipse-ee4j/concurrency-api.git", "master", "jakartaee-10.0-repos/concurrency-api"),
//                new Source("https://github.com/eclipse-ee4j/ejb-api.git", "master", "jakartaee-10.0-repos/ejb-api"),
//                new Source("https://github.com/eclipse-ee4j/interceptor-api.git", "master", "jakartaee-10.0-repos/interceptor-api"),
//                new Source("https://github.com/eclipse-ee4j/jax-rpc-api.git", "master", "jakartaee-10.0-repos/jax-rpc-api"),
//                new Source("https://github.com/eclipse-ee4j/jax-ws-api.git", "master", "jakartaee-10.0-repos/jax-ws-api"),
//                new Source("https://github.com/eclipse-ee4j/jaxb-api.git", "master", "jakartaee-10.0-repos/jaxb-api"),
//                new Source("https://github.com/eclipse-ee4j/jaxr-api.git", "master", "jakartaee-10.0-repos/jaxr-api"),
//                new Source("https://github.com/eclipse-ee4j/jaxrs-api.git", "master", "jakartaee-10.0-repos/jaxrs-api"),
//                new Source("https://github.com/eclipse-ee4j/jca-api.git", "master", "jakartaee-10.0-repos/jca-api"),
//                new Source("https://github.com/eclipse-ee4j/jms-api.git", "master", "jakartaee-10.0-repos/jms-api"),
//                new Source("https://github.com/eclipse-ee4j/jpa-api.git", "master", "jakartaee-10.0-repos/jpa-api"),
//                new Source("https://github.com/eclipse-ee4j/jsonb-api.git", "master", "jakartaee-10.0-repos/jsonb-api"),
//                new Source("https://github.com/eclipse-ee4j/jsp-api.git", "master", "jakartaee-10.0-repos/jsp-api"),
//                new Source("https://github.com/eclipse-ee4j/jstl-api.git", "master", "jakartaee-10.0-repos/jstl-api"),
//                new Source("https://github.com/eclipse-ee4j/jta-api.git", "master", "jakartaee-10.0-repos/jta-api"),
//                new Source("https://github.com/eclipse-ee4j/jws-api.git", "master", "jakartaee-10.0-repos/jws-api"),
//                new Source("https://github.com/eclipse-ee4j/management-api.git", "master", "jakartaee-10.0-repos/management-api"),
//                new Source("https://github.com/eclipse-ee4j/saaj-api.git", "master", "jakartaee-10.0-repos/saaj-api"),
//                new Source("https://github.com/eclipse-ee4j/security-api.git", "master", "jakartaee-10.0-repos/security-api"),
//                new Source("https://github.com/eclipse-ee4j/servlet-api.git", "master", "jakartaee-10.0-repos/servlet-api"),
//                new Source("https://github.com/eclipse-ee4j/websocket-api.git", "master", "jakartaee-10.0-repos/websocket-api"),
//        };

        final Source[] jakartaEE9 = {
                new Source("https://github.com/eclipse-ee4j/common-annotations-api.git", "master", "jakartaee-9.1-repos/common-annotations-api"),
                new Source("https://github.com/eclipse-ee4j/concurrency-api.git", "master", "jakartaee-9.1-repos/concurrency-api"),
                new Source("https://github.com/eclipse-ee4j/ejb-api.git", "master", "jakartaee-9.1-repos/ejb-api"),
                new Source("https://github.com/eclipse-ee4j/interceptor-api.git", "master", "jakartaee-9.1-repos/interceptor-api"),
                new Source("https://github.com/eclipse-ee4j/jax-rpc-api.git", "master", "jakartaee-9.1-repos/jax-rpc-api"),
                new Source("https://github.com/eclipse-ee4j/jax-ws-api.git", "master", "jakartaee-9.1-repos/jax-ws-api"),
                new Source("https://github.com/eclipse-ee4j/jaxb-api.git", "master", "jakartaee-9.1-repos/jaxb-api"),
                new Source("https://github.com/eclipse-ee4j/jaxr-api.git", "master", "jakartaee-9.1-repos/jaxr-api"),
                new Source("https://github.com/eclipse-ee4j/jaxrs-api.git", "master", "jakartaee-9.1-repos/jaxrs-api"),
                new Source("https://github.com/eclipse-ee4j/jca-api.git", "master", "jakartaee-9.1-repos/jca-api"),
                new Source("https://github.com/eclipse-ee4j/jms-api.git", "master", "jakartaee-9.1-repos/jms-api"),
                new Source("https://github.com/eclipse-ee4j/jpa-api.git", "master", "jakartaee-9.1-repos/jpa-api"),
                new Source("https://github.com/eclipse-ee4j/jsonb-api.git", "master", "jakartaee-9.1-repos/jsonb-api"),
                new Source("https://github.com/eclipse-ee4j/jsp-api.git", "master", "jakartaee-9.1-repos/jsp-api"),
                new Source("https://github.com/eclipse-ee4j/jstl-api.git", "master", "jakartaee-9.1-repos/jstl-api"),
                new Source("https://github.com/eclipse-ee4j/jta-api.git", "master", "jakartaee-9.1-repos/jta-api"),
                new Source("https://github.com/eclipse-ee4j/jws-api.git", "master", "jakartaee-9.1-repos/jws-api"),
                new Source("https://github.com/eclipse-ee4j/management-api.git", "master", "jakartaee-9.1-repos/management-api"),
                new Source("https://github.com/eclipse-ee4j/saaj-api.git", "master", "jakartaee-9.1-repos/saaj-api"),
                new Source("https://github.com/eclipse-ee4j/security-api.git", "master", "jakartaee-9.1-repos/security-api"),
                new Source("https://github.com/eclipse-ee4j/servlet-api.git", "master", "jakartaee-9.1-repos/servlet-api"),
                new Source("https://github.com/eclipse-ee4j/websocket-api.git", "master", "jakartaee-9.1-repos/websocket-api"),
        };

        final Source[] jakartaEE8 = {
                new Source("https://github.com/eclipse-ee4j/common-annotations-api.git", "EE4J_8", "jakartaee-8.0-repos/common-annotations-api"),
                new Source("https://github.com/eclipse-ee4j/concurrency-api.git", "EE4J_8", "jakartaee-8.0-repos/concurrency-api"),
                new Source("https://github.com/eclipse-ee4j/ejb-api.git", "EE4J_8", "jakartaee-8.0-repos/ejb-api"),
                new Source("https://github.com/eclipse-ee4j/interceptor-api.git", "EE4J_8", "jakartaee-8.0-repos/interceptor-api"),
                new Source("https://github.com/eclipse-ee4j/jax-rpc-api.git", "EE4J_8", "jakartaee-8.0-repos/jax-rpc-api"),
                new Source("https://github.com/eclipse-ee4j/jax-ws-api.git", "EE4J_8", "jakartaee-8.0-repos/jax-ws-api"),
                new Source("https://github.com/eclipse-ee4j/jaxb-api.git", "EE4J_8", "jakartaee-8.0-repos/jaxb-api"),
                new Source("https://github.com/eclipse-ee4j/jaxr-api.git", "EE4J_8", "jakartaee-8.0-repos/jaxr-api"),
                new Source("https://github.com/eclipse-ee4j/jaxrs-api.git", "EE4J_8", "jakartaee-8.0-repos/jaxrs-api"),
                new Source("https://github.com/eclipse-ee4j/jca-api.git", "EE4J_8", "jakartaee-8.0-repos/jca-api"),
                new Source("https://github.com/eclipse-ee4j/jms-api.git", "EE4J_8", "jakartaee-8.0-repos/jms-api"),
                new Source("https://github.com/eclipse-ee4j/jpa-api.git", "EE4J_8", "jakartaee-8.0-repos/jpa-api"),
                new Source("https://github.com/eclipse-ee4j/jsonb-api.git", "EE4J_8", "jakartaee-8.0-repos/jsonb-api"),
                new Source("https://github.com/eclipse-ee4j/jsp-api.git", "EE4J_8", "jakartaee-8.0-repos/jsp-api"),
                new Source("https://github.com/eclipse-ee4j/jstl-api.git", "EE4J_8", "jakartaee-8.0-repos/jstl-api"),
                new Source("https://github.com/eclipse-ee4j/jta-api.git", "EE4J_8", "jakartaee-8.0-repos/jta-api"),
                new Source("https://github.com/eclipse-ee4j/jws-api.git", "EE4J_8", "jakartaee-8.0-repos/jws-api"),
                new Source("https://github.com/eclipse-ee4j/management-api.git", "EE4J_8", "jakartaee-8.0-repos/management-api"),
                new Source("https://github.com/eclipse-ee4j/saaj-api.git", "EE4J_8", "jakartaee-8.0-repos/saaj-api"),
                new Source("https://github.com/eclipse-ee4j/security-api.git", "EE4J_8", "jakartaee-8.0-repos/security-api"),
                new Source("https://github.com/eclipse-ee4j/servlet-api.git", "EE4J_8", "jakartaee-8.0-repos/servlet-api"),
                new Source("https://github.com/eclipse-ee4j/websocket-api.git", "EE4J_8", "jakartaee-8.0-repos/websocket-api"),
        };

//        final Source[] microProfile6 = new Source[]{
//                new Source("https://github.com/eclipse/microprofile-config.git", "master", "microprofile-6.0-repos/config").filterJavadoc(".*/api/src/main/java/.*", ""),
//                new Source("https://github.com/eclipse/microprofile-fault-tolerance", "master", "microprofile-6.0-repos/fault-tolerance").filterJavadoc(".*/api/src/main/java/.*", ""),
//                new Source("https://github.com/eclipse/microprofile-health", "master", "microprofile-6.0-repos/health").filterJavadoc(".*/api/src/main/java/.*", ""),
//                new Source("https://github.com/eclipse/microprofile-jwt-auth.git", "master", "microprofile-6.0-repos/jwt").filterJavadoc(".*/api/src/main/java/.*", ""),
//                new Source("https://github.com/eclipse/microprofile-metrics", "master", "microprofile-6.0-repos/metrics").filterJavadoc(".*/api/src/main/java/.*", ""),
//                new Source("https://github.com/eclipse/microprofile-open-api", "master", "microprofile-6.0-repos/open-api").filterJavadoc(".*/api/src/main/java/.*", ""),
//                new Source("https://github.com/eclipse/microprofile-opentracing", "master", "microprofile-6.0-repos/opentracing").filterJavadoc(".*/api/src/main/java/.*", ""),
//                new Source("https://github.com/eclipse/microprofile-rest-client", "master", "microprofile-6.0-repos/rest-client").filterJavadoc(".*/api/src/main/java/.*", ""),
//        };

        final Source[] microProfile5 = new Source[]{
                new Source("https://github.com/eclipse/microprofile-config.git", "3.0.1", "microprofile-5.0-repos/config").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-fault-tolerance", "4.0", "microprofile-5.0-repos/fault-tolerance").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-health", "4.0", "microprofile-5.0-repos/health").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-jwt-auth.git", "2.0", "microprofile-5.0-repos/jwt").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-metrics", "4.0.1", "microprofile-5.0-repos/metrics").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-open-api", "3.0", "microprofile-5.0-repos/open-api").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-opentracing", "3.0", "microprofile-5.0-repos/opentracing").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-rest-client", "3.0", "microprofile-5.0-repos/rest-client").filterJavadoc(".*/api/src/main/java/.*", ""),
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
//                new Source("https://github.com/apache/tomee.git", "master", "tomee-10.0", false, "10.0.0-M1").label("milestone").related(microProfile6).related(jakartaEE10).javadoc("^org.apache.(openejb|tomee).*"),
//                new Source("https://github.com/apache/tomee.git", "tomee-9.0", "tomee-9.0", true, "9.0.0").related(microProfile5).related(jakartaEE9).javadoc("^org.apache.(openejb|tomee).*"),
//                new Source("https://github.com/apache/tomee.git", "tomee-8.x", "tomee-8.0", false, "8.0.11").related(microProfile2).related(jakartaEE8).javadoc("^org.apache.(openejb|tomee).*"),
//                new Source("https://github.com/apache/tomee.git", "tomee-7.1.x", "tomee-7.1", false, "7.1.4").javadoc("^org.apache.(openejb|tomee).*"),
//                new Source("https://github.com/apache/tomee.git", "tomee-7.0.x", "tomee-7.0", false, "7.0.9").javadoc("^org.apache.(openejb|tomee).*"),
                // TOMEE CURRENT
                new Source("https://github.com/apache/tomee.git", "master", "tomee-9.0", false, "9.0.0-M7").label("milestone").related(microProfile5).related(jakartaEE9).javadoc("^org.apache.(openejb|tomee).*"),
                new Source("https://github.com/apache/tomee.git", "tomee-8.x", "tomee-8.0", true, "8.0.10").related(microProfile2).related(jakartaEE8).javadoc("^org.apache.(openejb|tomee).*"),
                new Source("https://github.com/apache/tomee.git", "tomee-7.1.x", "tomee-7.1", false, "7.1.4").javadoc("^org.apache.(openejb|tomee).*"),
                new Source("https://github.com/apache/tomee.git", "tomee-7.0.x", "tomee-7.0", false, "7.0.9").javadoc("^org.apache.(openejb|tomee).*"),
                // JAKARTA EE
//                new Source("https://github.com/eclipse-ee4j/jakartaee-platform.git", "v10.0", "jakartaee-10.0").related(jakartaEE10).javadoc("^jakarta.*"),
                new Source("https://github.com/eclipse-ee4j/jakartaee-platform.git", "v9.1", "jakartaee-9.1").related(jakartaEE9).javadoc("^jakarta.*"),
                new Source("https://github.com/eclipse-ee4j/jakartaee-platform.git", "v8", "jakartaee-8.0").related(jakartaEE8).javadoc("^javax.*"),
                // MICRO PROFILE
//                new Source("https://github.com/eclipse/microprofile.git", "6.0", "microprofile-6.0").related(microProfile6).javadoc("^org.eclipse.microprofile.*"),
                new Source("https://github.com/eclipse/microprofile.git", "5.0", "microprofile-5.0").related(microProfile5).javadoc("^org.eclipse.microprofile.*"),
                new Source("https://github.com/eclipse/microprofile.git", "4.1", "microprofile-4.1").related(microProfile4).javadoc("^org.eclipse.microprofile.*"),
                new Source("https://github.com/eclipse/microprofile.git", "2.0", "microprofile-2.0").related(microProfile2).javadoc("^org.eclipse.microprofile.*"),
        };
    }
}
