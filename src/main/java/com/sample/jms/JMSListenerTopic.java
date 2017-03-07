package com.sample.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.sample.config.JMSConfig;

/**
 * This is JMS message listener component which is receiving at Topic
 * destination. Defined two Topics in same component for testing purpose.
 * 
 * @author shyam.pareek
 *
 */
@Component
public class JMSListenerTopic {

	@JmsListener(destination = JMSConfig.JMS_TOPIC_DESTINATION, containerFactory = "jmsTopicFactory")
	public void receiveTopicMessage_1(JMSMessage message) {

		message.setJmsReceivedStatus(true);
		System.out.println("########## You message received at Topic@1 @@@@@@ "
				+ "JMSListenerTopic#receiveTopicMessage_1 : " + message + "@@@@@@ ########## \n");
	}

	@JmsListener(destination = JMSConfig.JMS_TOPIC_DESTINATION, containerFactory = "jmsTopicFactory")
	public void receiveTopicMessage_2(JMSMessage message) {

		message.setJmsReceivedStatus(true);
		System.out.println("########## You message received at Topic@2 @@@@@ "
				+ " JMSListenerTopic#receiveTopicMessage_2 : " + message + " @@@@@ ########## \n");
	}
}
