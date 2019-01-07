Title: Tomcat JPA

Tomcat + Java EE = TomEE, the Java Enterprise Edition of Tomcat.  With TomEE you get Tomcat with JPA added and integrated and ready to go!

In a plain Servlet, Filter or Listener you can do fun things like injection of JPA EntityManager or EntityManagerFactory:

    import javax.annotation.Resource;
    import javax.persistence.EntityManager;
    import javax.persistence.PersistenceContext;
    import javax.servlet.http.HttpServlet;
    import javax.transaction.UserTransaction;

    public class MyServet extends HttpServlet {

        @Resource
        private UserTransaction userTransaction;

        @PersistenceContext
        private EntityManager entityManager;


        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //...

            userTransaction.begin();

            try {
                entityManager.persist(new Movie("Quentin Tarantino", "Reservoir Dogs", 1992));
                entityManager.persist(new Movie("Joel Coen", "Fargo", 1996));
                entityManager.persist(new Movie("Joel Coen", "The Big Lebowski", 1998));
            } finally {
                userTransaction.commit();
            }

            //...
        }

    }

No need to add even a single library!  To make the above work all you need is a `WEB-INF/persistence.xml` file in your webapp like the following:

    <persistence xmlns="http://java.sun.com/xml/ns/persistence" version="1.0">

      <persistence-unit name="movie-unit">
        <jta-data-source>movieDatabase</jta-data-source>
        <non-jta-data-source>movieDatabaseUnmanaged</non-jta-data-source>

        <properties>
          <property name="openjpa.jdbc.SynchronizeMappings" value="buildSchema(ForeignKeys=true)"/>
        </properties>
      </persistence-unit>
    </persistence>

DataSources will automatically be created if they haven't be configured explicitly.

[Download](downloads.html) TomEE and you're minutes away from a functioning JPA application on Tomcat.

{include:apache-tomee.mdtext}