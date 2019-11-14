package com.payara.ejb;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;

import com.payara.util.ServiceUtil;
import com.wildfly.service.LogService;

@Startup
@Singleton
public class ScheduledJob {

	@Inject
	private JMSContext jmsContext;

	@Resource(lookup = "queue/PayaraMessageQueue")
	private Queue queue;

	private LogService logService;
	
	@PostConstruct
	public void init() {
		logService = ServiceUtil.getRemoteBean(LogService.class);
	}

	@Schedule(hour = "*", minute = "*", second = "0", timezone = "UTC")
	public void scheduledMethod() {
		logService.createLog(new Date(), "ScheduledJob", "scheduledMethod has been invoked.");
		sendMessageToQueue("ScheduledJob", "Hello from SingletonEJB");

	}
	@Lock(LockType.WRITE)
	public void sendMessageToQueue(String source, String message) {
		try {
			TextMessage tm = jmsContext.createTextMessage();
			tm.setText(message);
			jmsContext.createProducer().send(queue, tm);
			logService.createLog(new Date(), source, "JMS Message has been sent to queue.");
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
