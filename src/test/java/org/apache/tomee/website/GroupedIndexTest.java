package org.apache.tomee.website;

import org.apache.openejb.loader.Files;
import org.apache.openejb.loader.IO;
import org.apache.openejb.util.Join;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class GroupedIndexTest extends org.junit.Assert {

    @Test
    public void testProcess() throws Exception {
        final File dir = Files.tmpdir();

        md(dir, "orange", "Orange", "Colors");
        html(dir, "red", "Red", "Colors");
        ad(dir, "green", "Green", "Colors");

        md(dir, "square", "Squares wish they were Cubes", "Shapes");
        html(dir, "circle", "Circles wish they were Spheres", "Shapes");

        ad(dir, "cheese", "Shredded Mozzarella", "Pizza");
        md(dir, "pepperoni", "Sliced Peperoni", "Pizza");
        md(dir, "olives", "Black Olives", "Pizza");
        ad(dir, "onion", "White Onions", "Pizza");

        md(dir, "cheese", "Shredded Mozzarella", "Calzone");
        ad(dir, "pepperoni", "Sliced Peperoni", "Calzone");
        ad(dir, "olives", "Black Olives", "Calzone");
        ad(dir, "onion", "White Onions", "Calzone");

        final GroupedIndex groupedIndex = new GroupedIndex(dir, "samples");

        groupedIndex.process();

        final String slurp = IO.slurp(new File(dir, "index.html"));

        assertEquals("type=samples\n" +
                "status=published\n" +
                "~~~~~~\n" +
                "        <div class=\"row\">\n" +
                "          <div class=\"col-md-4\">\n" +
                "            <div class=\"group-title\">Calzone</div>\n" +
                "            <ul class=\"group\">\n" +
                "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"olives.html\">Black Olives</a></li>\n" +
                "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"cheese.html\">Shredded Mozzarella</a></li>\n" +
                "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"pepperoni.html\">Sliced Peperoni</a></li>\n" +
                "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"onion.html\">White Onions</a></li>\n" +
                "            </ul>\n" +
                "          </div>\n" +
                "          <div class=\"col-md-4\">\n" +
                "            <div class=\"group-title\">Pizza</div>\n" +
                "            <ul class=\"group\">\n" +
                "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"olives.html\">Black Olives</a></li>\n" +
                "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"cheese.html\">Shredded Mozzarella</a></li>\n" +
                "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"pepperoni.html\">Sliced Peperoni</a></li>\n" +
                "            </ul>\n" +
                "          </div>\n" +
                "          <div class=\"col-md-4\">\n" +
                "            <div class=\"group-title\">Colors</div>\n" +
                "            <ul class=\"group\">\n" +
                "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"green.html\">Green</a></li>\n" +
                "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"orange.html\">Orange</a></li>\n" +
                "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"red.html\">Red</a></li>\n" +
                "            </ul>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "        <div class=\"row\">\n" +
                "          <div class=\"col-md-4\">\n" +
                "            <div class=\"group-title\">Shapes</div>\n" +
                "            <ul class=\"group\">\n" +
                "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"circle.html\">Circles wish they were Spheres</a></li>\n" +
                "              <li class=\"group-item\"><span class=\"group-item-i\" ><i class=\"fa fa-angle-right\"></i></span><a href=\"square.html\">Squares wish they were Cubes</a></li>\n" +
                "            </ul>\n" +
                "          </div>\n" +
                "          <div class=\"col-md-4\">\n" +
                "          </div>\n" +
                "          <div class=\"col-md-4\">\n" +
                "          </div>\n" +
                "        </div>\n", slurp);
    }

    public static void ad(final File dir, final String file, final String title, final String group) throws IOException {
        content(dir, file + ".ad",
                ":jbake-title: " + title,
                ":jbake-type: page",
                ":jbake-status: published",
                ":index-group: " + group,
                "",
                "# Some other content"
        );
    }

    public static void html(final File dir, final String file, final String title, final String group) throws IOException {
        content(dir, file + ".html",
                "title=" + title,
                "type=page",
                "status=published",
                "index-group=" + group,
                "~~~~~~",
                "<p>Some other content</p>"
        );
    }

    public static void md(final File dir, final String file, final String title, final String group) throws IOException {
        content(dir, file + ".md",
                "title=" + title,
                "type=page",
                "status=published",
                "index-group=" + group,
                "~~~~~~",
                "Some other content"
        );
    }

    public static void content(final File dir, final String child, final String... content) throws IOException {
        IO.copy(IO.read(Join.join("\n", content)), new File(dir, child));
    }
}