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
        final Source[] microProfile2 = new Source[]{
                new Source("https://github.com/eclipse/microprofile-jwt-auth.git", "1.1.1", "microprofile-jwt-1.1").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-config.git", "1.3", "microprofile-config-1.3").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-fault-tolerance", "1.1.4", "microprofile-fault-tolerance-1.1").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-health", "1.0", "microprofile-health-1.0").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-metrics", "1.1.1", "microprofile-metrics-1.1").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-open-api", "1.0.2", "microprofile-open-api-1.0").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-opentracing", "1.1", "microprofile-opentracing-1.1").filterJavadoc(".*/api/src/main/java/.*", ""),
                new Source("https://github.com/eclipse/microprofile-rest-client", "1.1", "microprofile-rest-client-1.1").filterJavadoc(".*/api/src/main/java/.*", ""),
        };

        final Source[] jakartaEE8 = {
                new Source("https://github.com/eclipse-ee4j/common-annotations-api.git", "EE4J_8", "common-annotations-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/concurrency-api.git", "EE4J_8", "concurrency-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/ejb-api.git", "EE4J_8", "ejb-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/interceptor-api.git", "EE4J_8", "interceptor-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/jax-rpc-api.git", "EE4J_8", "jax-rpc-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/jax-ws-api.git", "EE4J_8", "jax-ws-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/jaxb-api.git", "EE4J_8", "jaxb-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/jaxr-api.git", "EE4J_8", "jaxr-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/jaxrs-api.git", "EE4J_8", "jaxrs-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/jca-api.git", "EE4J_8", "jca-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/jms-api.git", "EE4J_8", "jms-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/jpa-api.git", "EE4J_8", "jpa-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/jsonb-api.git", "EE4J_8", "jsonb-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/jsp-api.git", "EE4J_8", "jsp-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/jstl-api.git", "EE4J_8", "jstl-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/jta-api.git", "EE4J_8", "jta-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/jws-api.git", "EE4J_8", "jws-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/management-api.git", "EE4J_8", "management-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/saaj-api.git", "EE4J_8", "saaj-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/security-api.git", "EE4J_8", "security-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/servlet-api.git", "EE4J_8", "servlet-api-EE4J_81.0"),
                new Source("https://github.com/eclipse-ee4j/websocket-api.git", "EE4J_8", "websocket-api-EE4J_81.0")
        };

        final Source[] jakartaEE9 = {
                new Source("https://github.com/eclipse-ee4j/common-annotations-api.git", "master", "common-annotations-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/concurrency-api.git", "master", "concurrency-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/ejb-api.git", "master", "ejb-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/interceptor-api.git", "master", "interceptor-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/jax-rpc-api.git", "master", "jax-rpc-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/jax-ws-api.git", "master", "jax-ws-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/jaxb-api.git", "master", "jaxb-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/jaxr-api.git", "master", "jaxr-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/jaxrs-api.git", "master", "jaxrs-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/jca-api.git", "master", "jca-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/jms-api.git", "master", "jms-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/jpa-api.git", "master", "jpa-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/jsonb-api.git", "master", "jsonb-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/jsp-api.git", "master", "jsp-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/jstl-api.git", "master", "jstl-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/jta-api.git", "master", "jta-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/jws-api.git", "master", "jws-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/management-api.git", "master", "management-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/saaj-api.git", "master", "saaj-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/security-api.git", "master", "security-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/servlet-api.git", "master", "servlet-api-ee9"),
                new Source("https://github.com/eclipse-ee4j/websocket-api.git", "master", "websocket-api-ee9")
        };

        return new Source[]{
//                new Source("https://github.com/apache/tomee.git", "master", "tomee-8.0"),
                new Source("https://github.com/apache/tomee.git", "master", "tomee-9.0").label("milestone").related(microProfile2).related(jakartaEE9).javadoc("^org.apache.(openejb|tomee).*"),
//                new Source("https://github.com/apache/tomee.git", "master", "tomee-8.0", true).related(microProfile2).related(jakartaEE8).javadoc("^org.apache.(openejb|tomee).*"),
//                new Source("https://github.com/apache/tomee.git", "tomee-7.1.0", "tomee-7.1").javadoc("^org.apache.(openejb|tomee).*"),
//                new Source("https://github.com/apache/tomee.git", "tomee-7.0.5", "tomee-7.0").javadoc("^org.apache.(openejb|tomee).*"),
//                new Source("https://github.com/apache/tomee.git", "master", "master").javadoc("^org.apache.(openejb|tomee).*"),
//                new Source("https://github.com/eclipse/microprofile-bom.git", "master", "microprofile-2.0").related(microProfile2).javadoc("^org.eclipse.microprofile.*"),
//                new Source("https://github.com/eclipse-ee4j/jakartaee-platform.git", "v8", "jakartaee-8.0").related(jakartaEE8).javadoc("^javax.*"),
                new Source("https://github.com/eclipse-ee4j/jakartaee-platform.git", "master", "jakartaee-9.0").related(jakartaEE9).javadoc("^jakarta.*")
        };
    }
}
