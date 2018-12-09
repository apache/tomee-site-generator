package org.apache.tomee.website;

import org.apache.openejb.loader.IO;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class AsciidocAdjustHeadingLevelsTest extends Assert {

    @Test
    public void testProcess() throws Exception {

        final File content = File.createTempFile("content-", ".adoc");

        IO.copy(IO.read("== Colors\n" +
                        "Some random text\n" +
                        "==== Reds\n" +
                        "More random text\n" +
                        "===== Crimson is a kind of Red\n" +
                        "More random text\n" +
                        "===== Ruby Red\n" +
                        "More random text\n" +
                        "More random text\n" +
                        "=== Greens\n" +
                        "===== Emerald\n" +
                        "\n" +
                        "More random text\n" +
                        "\n" +
                        "==== Forrest Green\n" +
                        "More random text\n" +
                        "= Shapes\n" +
                        "More random text\n" +
                        "\n\n"
        ), content);

        AsciidocAdjustHeadingLevels.process(content);

        final String expected = "= Colors\n" +
                "Some random text\n" +
                "== Reds\n" +
                "More random text\n" +
                "=== Crimson is a kind of Red\n" +
                "More random text\n" +
                "=== Ruby Red\n" +
                "More random text\n" +
                "More random text\n" +
                "== Greens\n" +
                "=== Emerald\n" +
                "\n" +
                "More random text\n" +
                "\n" +
                "=== Forrest Green\n" +
                "More random text\n" +
                "= Shapes\n" +
                "More random text\n";

        final String actual = IO.slurp(content);

        assertEquals(expected, actual);
    }

    @Test
    public void testHandlesMixedCharacters() throws Exception {

        final File content = File.createTempFile("content-", ".adoc");

        IO.copy(IO.read("## Colors\n" +
                        "Some random text\n" +
                        "==== Reds\n" +
                        "More random text\n" +
                        "##### Crimson is a kind of Red\n" +
                        "More random text\n" +
                        "===== Ruby Red\n" +
                        "More random text\n" +
                        "More random text\n" +
                        "### Greens\n" +
                        "===== Emerald\n" +
                        "\n" +
                        "More random text\n" +
                        "\n" +
                        "==== Forrest Green\n" +
                        "More random text\n" +
                        "# Shapes\n" +
                        "More random text\n" +
                        "\n\n"
        ), content);

        AsciidocAdjustHeadingLevels.process(content);

        final String expected = "= Colors\n" +
                "Some random text\n" +
                "== Reds\n" +
                "More random text\n" +
                "=== Crimson is a kind of Red\n" +
                "More random text\n" +
                "=== Ruby Red\n" +
                "More random text\n" +
                "More random text\n" +
                "== Greens\n" +
                "=== Emerald\n" +
                "\n" +
                "More random text\n" +
                "\n" +
                "=== Forrest Green\n" +
                "More random text\n" +
                "= Shapes\n" +
                "More random text\n";

        final String actual = IO.slurp(content);

        assertEquals(expected, actual);
    }

    @Test
    public void testIgnoreCodeblocks() throws Exception {


        final File content = File.createTempFile("content-", ".adoc");

        IO.copy(IO.read("== Colors\n" +
                        "Some random text\n" +
                        "==== Reds\n" +
                        "More random text\n" +
                        "----\n" +
                        "===== Crimson is a kind of Red\n" +
                        "More random text\n" +
                        "----\n" +
                        "===== Ruby Red\n" +
                        "More random text\n" +
                        "More random text\n" +
                        "=== Greens\n" +
                        "===== Emerald\n" +
                        "\n" +
                        "More random text\n" +
                        "\n" +
                        "==== Forrest Green\n" +
                        "More random text\n" +
                        "= Shapes\n" +
                        "More random text\n" +
                        "\n\n"
        ), content);

        AsciidocAdjustHeadingLevels.process(content);

        final String expected = "= Colors\n" +
                "Some random text\n" +
                "== Reds\n" +
                "More random text\n" +
                "----\n" +
                "===== Crimson is a kind of Red\n" +
                "More random text\n" +
                "----\n" +
                "=== Ruby Red\n" +
                "More random text\n" +
                "More random text\n" +
                "== Greens\n" +
                "=== Emerald\n" +
                "\n" +
                "More random text\n" +
                "\n" +
                "=== Forrest Green\n" +
                "More random text\n" +
                "= Shapes\n" +
                "More random text\n";

        final String actual = IO.slurp(content);

        assertEquals(expected, actual);
    }

    @Test
    public void testIgnoreCodeblocks2() throws Exception {


        final File content = File.createTempFile("content-", ".adoc");

        IO.copy(IO.read("== Colors\n" +
                        "Some random text\n" +
                        "==== Reds\n" +
                        "More random text\n" +
                        "````\n" +
                        "# Crimson is a kind of Red\n" +
                        "More random text\n" +
                        "````\n" +
                        "===== Ruby Red\n" +
                        "More random text\n" +
                        "More random text\n" +
                        "=== Greens\n" +
                        "===== Emerald\n" +
                        "\n" +
                        "More random text\n" +
                        "\n" +
                        "==== Forrest Green\n" +
                        "More random text\n" +
                        "= Shapes\n" +
                        "More random text\n" +
                        "\n\n"
        ), content);

        AsciidocAdjustHeadingLevels.process(content);

        final String expected = "= Colors\n" +
                "Some random text\n" +
                "== Reds\n" +
                "More random text\n" +
                "````\n" +
                "# Crimson is a kind of Red\n" +
                "More random text\n" +
                "````\n" +
                "=== Ruby Red\n" +
                "More random text\n" +
                "More random text\n" +
                "== Greens\n" +
                "=== Emerald\n" +
                "\n" +
                "More random text\n" +
                "\n" +
                "=== Forrest Green\n" +
                "More random text\n" +
                "= Shapes\n" +
                "More random text\n";

        final String actual = IO.slurp(content);

        assertEquals(expected, actual);
    }

    @Test
    public void testAdjustPadding() throws Exception {

        final File content = File.createTempFile("content-", ".adoc");

        IO.copy(IO.read("==Colors\n" +
                        "Some random text\n" +
                        "==== Reds\n" +
                        "More random text\n" +
                        "===== Crimson is a kind of Red\n" +
                        "More random text\n" +
                        "===== Ruby Red\n" +
                        "More random text\n" +
                        "More random text\n" +
                        "===Greens\n" +
                        "#####Emerald\n" +
                        "\n" +
                        "More random text\n" +
                        "\n" +
                        "==== Forrest Green\n" +
                        "More random text\n" +
                        "= Shapes\n" +
                        "More random text\n" +
                        "\n\n"
        ), content);

        AsciidocAdjustHeadingLevels.process(content);

        final String expected = "= Colors\n" +
                "Some random text\n" +
                "== Reds\n" +
                "More random text\n" +
                "=== Crimson is a kind of Red\n" +
                "More random text\n" +
                "=== Ruby Red\n" +
                "More random text\n" +
                "More random text\n" +
                "== Greens\n" +
                "=== Emerald\n" +
                "\n" +
                "More random text\n" +
                "\n" +
                "=== Forrest Green\n" +
                "More random text\n" +
                "= Shapes\n" +
                "More random text\n";

        final String actual = IO.slurp(content);

        assertEquals(expected, actual);
    }

}