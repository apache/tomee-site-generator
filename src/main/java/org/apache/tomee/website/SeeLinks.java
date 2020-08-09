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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to insert additional @see links into java source code.
 * If no Javadoc exists at the class level, some will be added.
 */
public class SeeLinks {

    public static String insertHref(final String source, final String link, final String linkText) {
        final int start = Math.max(source.lastIndexOf("\nimport "), source.indexOf("\npackage "));
        final int end = min(source.indexOf("\npublic "), source.indexOf("\n@"));

        final String header = source.substring(start, end);

        if (header.contains("/**")) {
            return updateComment(source, link, linkText, header);
        } else {
            return addComment(source, link, linkText, header);
        }
    }

    private static int min(final int a, final int b) {
        if (a == -1) return b;
        if (b == -1) return a;
        return Math.min(a, b);
    }

    private static String addComment(final String source, final String link, final String linkText, final String header) {
        final String href = href(link, linkText);
        final String comment = header + "\n/**" + href + "\n */";
        return source.replace(header, comment);
    }

    private static String updateComment(final String source, final String link, final String linkText, final String header) {
        /*
         * If we already have a link to this example, don't add it again
         */
        if (header.contains(String.format(">%s</a>", linkText))) return source;

        final Pattern commentPattern = Pattern.compile("/\\*\\*(.*\n*)*?\n *\\*/");
        final Matcher matcher = commentPattern.matcher(header);
        if (!matcher.find()) return source;

        final String comment = matcher.group(0);

        final String href = href(link, linkText);

        final String updatedComment = comment.replaceFirst("(\n *\\*/)", href + "$1");

        // TODO
        return source.replace(comment, updatedComment);
    }

    private static String href(final String link, final String linkText) {
        final String href = String.format("\n * @see <a href=\"%s\">%s</a>", link, linkText);
        return href;
    }
}
