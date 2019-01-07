Title: Tomcat CDI

Tomcat + Java EE = TomEE, the Java Enterprise Edition of Tomcat.  With TomEE you get Tomcat with CDI added and integrated and ready to go!

    import javax.annotation.Resource;
    import javax.enterprise.inject.spi.BeanManager;
    import javax.inject.Inject;
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;

    public class MyServlet extends HttpServlet {

        @Inject
        private Car car;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            car.drive();

        }
    }

Where `Car` could be any POJO with a no-arg constructor.  Additionally it's quite easy to create a factory to create the car you want injected.  Simply do as follows:

    import javax.enterprise.inject.Produces;

    public class AssemblyLine {

        @Produces
        public Car createFancyCar() {
            return new Car("Ferrari", "458 Italia", 2012);
        }
    }

So when will the `Car` be created?  In the above it will be created when `MyServlet` is created.  If you annotate `createFancyCar` with `@RequestScoped` it will be created once
per http request and shared by everyone in the request.  If you annotate `createFancyCar` with `@SessionScoped` it will be created once
per `HttpSession` and shared by everyone in the same session.  So no more repeatedly putting and getting into the `HttpSession` by hand, just let the container do it!

No need to add even a single library!  To make the above work all you need is a `META-INF/beans.xml` file in your webapp like the following:

    <beans/>

Any jar in your webapp with the above will automatically be CDI enabled and all those beans can be injected with `@Inject` with no need to make them EJBs or third party frameworks.

All this is setup and ready to go!  Spend your time learning and having fun and writing your app, don't
spend it chasing down libraries and integrating things the hard way.

[Download](downloads.html) TomEE and you're minutes away from having fun with CDI on Tomcat.

{include:apache-tomee.mdtext}