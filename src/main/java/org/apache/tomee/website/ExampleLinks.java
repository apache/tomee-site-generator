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

import org.tomitribe.swizzle.stream.StreamLexer;
import org.tomitribe.util.IO;
import org.tomitribe.util.Join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Utility class to insert additional @example links into java source code.
 * If no Javadoc exists at the class level, some will be added.
 *
 * The @example tag is a custom javadoc tag that creates various "Examples" sections
 * in the generated javadoc.
 */
public class ExampleLinks {

    public static String insertHref(final String source, final String link, final String linkText, final String language) {
        final int start = Math.max(source.lastIndexOf("\nimport "), source.indexOf("\npackage "));
        final int end = min(min(source.indexOf("\npublic "), source.indexOf("\n@")), source.indexOf("\nfinal"));

        final String header = source.substring(start, end);

        if (header.contains("/**")) {
            return updateComment(source, link, linkText, language, header);
        } else {
            return addComment(source, link, linkText, language, header);
        }
    }

    /**
     * Returns the lowest viable index
     */
    public static int min(final int a, final int b) {
        if (a == -1) return b;
        if (b == -1) return a;
        return Math.min(a, b);
    }

    private static String addComment(final String source, final String link, final String linkText, final String language, final String header) {
        final String href = href(link, linkText, language);
        final String comment = header + "\n/**\n" + href + "\n */";
        return source.replace(header, comment);
    }

    private static String updateComment(final String source, final String link, final String linkText, final String language, final String header) {
        /*
         * If we already have a link to this example, don't add it again
         */
        if (exists(linkText, language, header)) return source;

        final StreamLexer lexer = new StreamLexer(IO.read(header));
        final String comment;
        try {
            comment = lexer.readToken("/**", "*/");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        final String href = href(link, linkText, language);

        final List<String> lines = new ArrayList<>(Arrays.asList(comment.split("\n")));
        lines.add(lines.size() - 1, href);

        final String updatedComment = Join.join("\n", lines);

        // TODO
        return source.replace(comment, updatedComment);
    }

    private static boolean exists(final String linkText, final String language, final String header) {
        final long count = Stream.of(header.split("\n"))
                .filter(s -> s.contains("@example." + language))
                .filter(s -> s.contains(">" + linkText + "<"))
                .count();
        return count > 0;
    }

    private static String href(final String link, final String linkText, final String language) {
        final String href = String.format(" * @example.%s <a href=\"%s\">%s</a>", language, link, linkText);
        return href;
    }
}
