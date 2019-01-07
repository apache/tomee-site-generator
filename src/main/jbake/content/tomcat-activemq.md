Title: Tomcat and ActiveMQ

Tomcat + Java EE = TomEE, the Java Enterprise Edition of Tomcat.  With TomEE you get Tomcat with ActiveMQ added and integrated and ready to go!

In a plain Servlet, Filter or Listener you can do fun things like injection of JMS Topics or Queues:

    import javax.annotation.Resource;
    import javax.servlet.http.HttpServlet;
    import javax.jms.Topic;
    import javax.jms.Queue;
    import javax.jms.ConnectionFactory;

    public class MyServet extends HttpServlet {

        @Resource(name = "foo")
        private Topic fooTopic;

        @Resource(name = "bar")
        private Queue barQueue;

        @Resource
        private ConnectionFactory connectionFactory;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //...

            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(fooTopic);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Create a message
            TextMessage message = session.createTextMessage("Hello World!");

            // Tell the producer to send the message
            producer.send(message);

            //...
        }

    }

No need to add even a single library!  In the above scenario even the "foo" Topic and the "bar" Queue would be automatically created with no configuration necessary.

[Download](downloads.html) TomEE and you're minutes away from a functioning JMS application on Tomcat.

{include:apache-tomee.mdtext}