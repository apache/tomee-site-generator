package org.apache.tomee.website;

import org.apache.openejb.loader.IO;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class FixMarkdownTest extends Assert {

    @Test
    public void twoSimpleParagraphs() throws Exception {

        final File srcReadMe = File.createTempFile("example-", ".md");
        final File destReadme = File.createTempFile("example-", ".md");

        final Example example = new Example(srcReadMe, "cdi-basic", "md", "cdi-basic.html", "none");
        example.setDestReadme(destReadme);

        IO.copy(IO.read("~~~~~~\n" +
                        "To use `@Inject`, the first thing you need is a `META-INF/beans.xml` file in the module\n" +
                        "or jar.  This effectively turns on CDI and allows the `@Inject` references to work.\n" +
                        "No `META-INF/beans.xml` no injection, period.  This may seem overly strict,\n" +
                        "but it is not without reason.  The CDI API is a bit greedy and does consume a fair\n" +
                        "about of resources by design.\n" +
                        "\n" +
                        "When the container constructs a bean with an `@Inject` reference,\n" +
                        "it will first find or create the object that will be injected.  For the sake of\n" +
                        "simplicity, the is example has a basic `Faculty` pojo with a no-arg constructor.  Anyone\n" +
                        "referencing `@Inject Faculty` will get their own instance of `Faculty`.  If the desire\n" +
                        "is to share the same instance of `Faculty`, see the concept of `scopes` -- this is\n" +
                        "exactly what scopes are for.\n"
        ), srcReadMe);
        IO.copy(srcReadMe, destReadme);

        FixMarkdown.process(example);

        final String expected = "~~~~~~\n" +
                "To use `@Inject`, the first thing you need is a `META-INF/beans.xml` file in the module " +
                "or jar.  This effectively turns on CDI and allows the `@Inject` references to work. " +
                "No `META-INF/beans.xml` no injection, period.  This may seem overly strict, " +
                "but it is not without reason.  The CDI API is a bit greedy and does consume a fair " +
                "about of resources by design.\n" +
                "\n" +
                "When the container constructs a bean with an `@Inject` reference, " +
                "it will first find or create the object that will be injected.  For the sake of " +
                "simplicity, the is example has a basic `Faculty` pojo with a no-arg constructor.  Anyone " +
                "referencing `@Inject Faculty` will get their own instance of `Faculty`.  If the desire " +
                "is to share the same instance of `Faculty`, see the concept of `scopes` -- this is " +
                "exactly what scopes are for.\n";

        final String actual = IO.slurp(destReadme);

        assertEquals(expected, actual);
    }

    @Test
    public void sectionWithCodeBlock() throws Exception {

        final File srcReadMe = File.createTempFile("example-", ".md");
        final File destReadme = File.createTempFile("example-", ".md");

        final Example example = new Example(srcReadMe, "cdi-basic", "md", "cdi-basic.html", "none");
        example.setDestReadme(destReadme);

        IO.copy(IO.read("~~~~~~\n" +
                "## Faculty <small>a basic injectable pojo</small>\n" +
                "\n" +
                "    public class Faculty {\n" +
                "\n" +
                "        private List<String> facultyMembers;\n" +
                "\n" +
                "        private String facultyName;\n" +
                "\n" +
                "        @PostConstruct\n" +
                "        public void initialize() {\n" +
                "            this.facultyMembers = new ArrayList<String>();\n" +
                "            facultyMembers.add(\"Ian Schultz\");\n" +
                "            facultyMembers.add(\"Diane Reyes\");\n" +
                "            facultyName = \"Computer Science\";\n" +
                "        }\n" +
                "\n" +
                "        public List<String> getFacultyMembers() {\n" +
                "            return facultyMembers;\n" +
                "        }\n" +
                "\n" +
                "        public String getFacultyName() {\n" +
                "            return facultyName;\n" +
                "        }\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "## Course <small>a simple session bean</small>\n"), srcReadMe);
        IO.copy(srcReadMe, destReadme);

        FixMarkdown.process(example);

        // We expect the original content -- nothing should be changed
        final String expected = IO.slurp(srcReadMe);

        final String actual = IO.slurp(destReadme);

        assertEquals(expected, actual);
    }

    @Test
    public void skipJBakeMarkdownHeaders() throws Exception {

        final File srcReadMe = File.createTempFile("example-", ".md");
        final File destReadme = File.createTempFile("example-", ".md");

        final Example example = new Example(srcReadMe, "cdi-basic", "md", "cdi-basic.html", "none");
        example.setDestReadme(destReadme);

        IO.copy(IO.read("type=page\n" +
                        "status=awesome\n" +
                        "title=Awesome\n" +
                        "~~~~~~\n" +
                        "Here we have\n" +
                        "a sentence that needs unwrapping\n" +
                        "\n" +
                        "But the header should not be\n" +
                        "unwrapped.  That would be bad.\n"
        ), srcReadMe);
        IO.copy(srcReadMe, destReadme);

        FixMarkdown.process(example);

        final String expected = "type=page\n" +
                "status=awesome\n" +
                "title=Awesome\n" +
                "~~~~~~\n" +
                "Here we have a sentence that needs unwrapping\n" +
                "\n" +
                "But the header should not be unwrapped.  That would be bad.\n";

        final String actual = IO.slurp(destReadme);

        assertEquals(expected, actual);
    }

}