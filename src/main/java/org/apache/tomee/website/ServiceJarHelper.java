package org.apache.tomee.website;

import org.apache.openejb.OpenEJBException;
import org.apache.openejb.config.sys.JaxbOpenejb;
import org.apache.openejb.config.sys.ServiceProvider;
import org.apache.openejb.config.sys.ServicesJar;
import org.apache.openejb.util.SuperProperties;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// will stdout some doc from service-jar.xml
// we could generate it in a template but will likely be manually updated
// so using it as a kick off only
public class ServiceJarHelper {
    public static void main(final String[] args) throws Exception { // based on org.apache.openejb.config.sys.WikiGenerator
        System.out.println();
        System.out.println();
        System.out.println("WARNING");
        System.out.println("WARNING");
        System.out.println("WARNING");
        System.out.println("WARNING these generated contents are generally modified manually to enrich them");
        System.out.println("WARNING don't overwrite the pages without checking you are losing or not any information");
        System.out.println("WARNING");
        System.out.println("WARNING");
        System.out.println("WARNING");
        System.out.println();
        System.out.println();
        new ServiceJarHelper("org.apache.openejb", new PrintWriter(System.out), true).generate();
    }

    private final ServicesJar servicesJar;
    private final PrintWriter out;
    private final boolean resources;

    public ServiceJarHelper(final String providerName, final PrintWriter printWriter, final boolean resources) throws OpenEJBException {
        this.servicesJar = JaxbOpenejb.readServicesJar(providerName);
        this.out = printWriter;
        this.resources = resources;
    }

    public void generate() throws Exception {
        // generate containers
        final List<ServiceProvider> serviceProvider = servicesJar.getServiceProvider();
        if (!resources) {
            Collections.sort(serviceProvider, new Comparator<ServiceProvider>() {
                @Override
                public int compare(final ServiceProvider o1, final ServiceProvider o2) {
                    return grade(o2) - grade(o1);
                }

                private int grade(final ServiceProvider i) {
                    final String name = i.getClassName();
                    if (name.contains("stateless")) {
                        return 10;
                    }
                    if (name.contains("stateful")) {
                        return 9;
                    }
                    if (name.contains("singleton")) {
                        return 8;
                    }
                    if (name.contains("mdb")) {
                        return 7;
                    }
                    if (name.contains("managed")) {
                        return 6;
                    }
                    return 0;
                }
            });

            for (final ServiceProvider provider : serviceProvider) {
                if ("Container".equals(provider.getService())) {
                    generateService(provider);
                }
            }
        } else {

            final List<String> seen = new ArrayList<>();
            for (final ServiceProvider provider : servicesJar.getServiceProvider()) {
                if ("Resource".equals(provider.getService())) {

                    if (seen.containsAll(provider.getTypes())) {
                        continue;
                    }

                    generateService(provider);

                    seen.addAll(provider.getTypes());
                }
            }
        }
        out.println();
        out.flush();
    }

    private void generateService(final ServiceProvider provider) {
        final Map<String, String> defaults = new LinkedHashMap<>();
        final Map<String, String> comments = new LinkedHashMap<>();

        final SuperProperties properties = (SuperProperties) provider.getProperties();
        if (!properties.isEmpty()) {
            for (final Object key : properties.keySet()) {
                if (key instanceof String) {
                    final String name = (String) key;
                    if ("SkipImplicitAttributes".equals(name)) {
                        continue;
                    }

                    final Map<String, String> attributes = properties.getAttributes(name);

                    if (attributes.containsKey("hidden")) {
                        continue;
                    }

                    final String value = properties.getProperty(name);

                    String comment = properties.getComment(name);
                    comment = scrubText(comment);
                    if (comment.isEmpty()) {
                        comment = "FIXME";
                    }

                    defaults.put(name, String.valueOf(value));
                    comments.put(name, comment);
                }
            }
        }

        final String type = provider.getTypes().get(0);
        out.println("=== " + type);
        out.println();
        out.println("Declarable in tomee.xml via");
        out.println();
        out.println("[source,xml]");
        out.println("----");
        out.print("<" + provider.getService() + " id=\"Foo\" type=\"" + type + "\"");
        if (defaults.isEmpty()) {
            out.println(" />");
        } else {
            out.println(">");
            for (final Map.Entry<String, String> entry : defaults.entrySet()) {
                out.print("    ");
                out.print(entry.getKey());
                out.print(" = ");
                out.println(entry.getValue());
            }
            out.println("</" + provider.getService() + ">");
        }
        out.println("----");
        out.println();
        out.println("Declarable in properties via");
        out.println();
        out.println("[source,properties]");
        out.println("----");
        out.println("Foo = new://" + provider.getService() + "?type=" + type);
        for (final Map.Entry<String, String> entry : defaults.entrySet()) {
            out.print("Foo.");
            out.print(entry.getKey());
            out.print(" = ");
            out.println(entry.getValue());
        }
        out.println("----");
        out.println();

        if (!properties.isEmpty()) {
            out.println("==== Configuration");
            out.println();
            for (final Map.Entry<String, String> entry : comments.entrySet()) {
                out.println("===== " + entry.getKey());
                out.println();
                out.println(entry.getValue());
                out.println();
            }
        }

        out.println();
    }

    private String scrubText(String text) {
        if (text == null) {
            text = "";
        }
        return text;
    }
}
