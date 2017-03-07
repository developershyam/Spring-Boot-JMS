package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.sample.config.JMSConfig;
import com.sample.jms.JMSMessage;

/**
 * Business layer for JMS services. Publish Topic or Queue message.
 * 
 * @author shyam.pareek
 *
 */
@Service
public class JMSServiceImpl implements JMSService {

	// Template for publish message on Topic.
	@Autowired
	JmsTemplate jmsTemplateTopic;

	// Template for publish message on Queue.
	@Autowired
	JmsTemplate jmsTemplateQueue;

	/**
	 * Publish JMS message on Topic.
	 */
	@Override
	public void publishJMSTopicMessage() {

		String topic = JMSConfig.JMS_TOPIC_DESTINATION;
		JMSMessage message =  new JMSMessage("Spring Boot JMS is fantastic!!!", true, false);
		jmsTemplateTopic.convertAndSend(topic, message);
		System.out.println("********** Your message is published on Topic: [" + topic + "] "+message+"  **********");
		

	}

	/**
	 * Publish JMS message on Queue.
	 */
	@Override
	public void publishJMSQueueMessage() {

		String queue = JMSConfig.JMS_QUEUE_DESTINATION;
		JMSMessage message =  new JMSMessage("Spring Boot JMS is fantastic!!!", true, false);
		jmsTemplateQueue.convertAndSend(queue, message);
		System.out.println("********** Your message is published on Queue: [" + queue + "] "+message+" **********");
		

	}

}
