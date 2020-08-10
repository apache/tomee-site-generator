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

import org.tomitribe.util.PrintString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApisUsed {

    private static final Pattern HEADER = Pattern.compile("\n=+ *apis +used *\n", Pattern.CASE_INSENSITIVE);

    public static String insertHref(String source, final String link, final String linkText) {
        source = normalize(source);

        if (source.contains("\n== APIs Used\n")) {
            return updateApiList(source, link, linkText);
        } else {
            return addApiList(source, link, linkText);
        }
    }

    private static String addApiList(final String source, final String link, final String linkText) {
        final PrintString out = new PrintString();
        out.println("");
        out.println("== APIs Used");
        out.println("");
        out.printf("- link:%s[%s]%n", link, linkText);

        return source + out.toString();
    }

    private static String updateApiList(final String source, final String link, final String linkText) {
        final int start = source.indexOf("\n== APIs Used\n");
        final int end = ExampleLinks.min(source.indexOf("\n=", start + 1), source.length());
        final String content = source.substring(start, end);

        /*
         * Do not add this link if there already one with the same title
         */
        if (content.contains(String.format("[%s]", linkText))) return source;

        final String updated = content + String.format("- link:%s[%s]%n", link, linkText);

        return source.replace(content, updated);
    }

    protected static String normalize(final String source) {
        final Matcher matcher = HEADER.matcher(source);
        if (!matcher.find()) return source;

        return matcher.replaceAll("\n== APIs Used\n");
    }
}
