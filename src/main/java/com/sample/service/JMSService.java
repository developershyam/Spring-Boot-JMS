package com.sample.service;
/**
 * Abstract layer for JMS services. 
 * 
 * @author shyam.pareek
 *
 */
public interface JMSService {

	void publishJMSTopicMessage();
	
	void publishJMSQueueMessage();
	
}
