# remote-ejb-with-payara-and-wildfly
<h3>Accessing Remote EJB Deployed on WildFly from Payara</h3>
This is an example application of using remote EJBs deployed on different application server. It does not only focus on how remote ejbs are called from remote server, it also covers following topics;

<h4>WildFly-JPAModule</h4>
JPA with Hibernate using Oracle 11g DB<br/>
Stateless Session Bean as DAO<br/>
<h4>Payara-WebModule</h4>
JNDI Lookup for getting beans from WildFly AS<br/>
JMS for sending messages to queue<br/>
Message-Driven Bean for receiving messages from JSF page and ScheduledJob(SingletonEJB)<br/>
EJB TimerService for creating scheduled job<br/>
Basic CRUD Operations<br/>

