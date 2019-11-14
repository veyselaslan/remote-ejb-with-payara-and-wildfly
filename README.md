# remote-ejb-with-payara-and-wildfly
<h3>Access Remote EJB Deployed on WildFly AS from Payara AS</h3>
Hello, this is an example application of accessing Remote WildFly AS from Payara AS. It does not only focus on how remote ejbs are called from remote server, it also covers following practices;

<h4>WildFly-JPAModule</h4>
JPA with Hibernate using Oracle 11g DB<br/>
Stateless Session Bean (DAO)<br/>
<h4>Payara-WebModule</h4>
JNDI Lookup for getting beans from WildFly AS<br/>
Message Driven Bean for receiving messages from JSF page and ScheduledJob(SingletonEJB)<br/>
JMS for sending messages to queue<br/>
EJB TimerService for creating scheduled method<br/>
Basic CRUD Operations<br/>
Monitoring logs<br/>
