package com.payara.ejb;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.payara.util.ServiceUtil;
import com.wildfly.service.LogService;


@JMSDestinationDefinition(name = "queue/PayaraMessageQueue", interfaceName = "javax.jms.Queue", destinationName = "PayaraMessageQueue")
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/PayaraMessageQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class MDBean implements MessageListener  {

	private LogService logService;
	
	@PostConstruct
	public void init() {
		logService = ServiceUtil.getRemoteBean(LogService.class);
	}
	
	@Override
	public void onMessage(Message msg) {
		try {
			TextMessage message = (TextMessage) msg;
			logService.createLog(new Date(), "MDBean", "JMS Message has been received: " + message.getText());
		}catch(JMSException e) {
			e.printStackTrace();
		}
		
		
	}

}
