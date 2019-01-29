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

import org.apache.openejb.loader.IO;

import java.io.File;
import java.io.IOException;

public class AddGroups {

    public static void main(String[] args) throws IOException {
        final String[] lines = IO.slurp(new File("repos/tomee-8.0/examples/index.md")).split("\n");

        String group = "";
        for (final String line : lines) {
            if (line.startsWith("###")) {
                group = line.replace("###", "").trim();
            }


            if (line.startsWith("[")) {
                final String s = line.replaceAll(".*\\(|\\).*", "").replaceAll(".html", ".md");

                final File file = new File("repos/tomee-8.0/examples/" + s);
                final String content = IO.slurp(file);
                final String updated = content.replace("index-group=Unrevised", "index-group=" + group);

                IO.copy(IO.read(updated), file);
            }
        }
    }
}
