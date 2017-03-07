package com.sample.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.sample.config.JMSConfig;

/**
 * This is JMS message listener component which is receiving at Queue
 * destination. Defined two Topics in same component for testing purpose.
 * Queue#2 is designed for receive message and send it again to next queue.
 * 
 * @author shyam.pareek
 *
 */
@Component
public class JMSListenerQueue {

	@JmsListener(destination = JMSConfig.JMS_QUEUE_DESTINATION, containerFactory = "jmsQueueFactory")
	public void receiveQueueMessage_1(JMSMessage message) {

		message.setJmsReceivedStatus(true);
		System.out.println("########## You message received at Queue@1 " + " JMSListenerQueue#receiveQueueMessage_1 : "
				+ message + " @@@@@ ########## \n");
	}

	// Queue#2 is designed for receive message and send it again to next queue.
	// Put return type of this receiver same as input of next receiver.
	@JmsListener(destination = JMSConfig.JMS_QUEUE_DESTINATION, containerFactory = "jmsQueueFactory")
	@SendTo("sendAgainNext.queue")
	public String receiveQueueMessage_2(JMSMessage message) {

		message.setJmsReceivedStatus(true);
		System.out.println("########## You message received at Queue@2 @@@@@ "
				+ " JMSListenerQueue#receiveQueueMessage_2 : " + message + "-- Again send to next queue. @@@@@ ########## \n");
		return message.getMessage();
	}

	@JmsListener(destination = "sendAgainNext.queue", containerFactory = "jmsQueueFactory")
	public void receiveQueueMessage_3(String message) {

		System.out.println("########## You message received at Queue@3 @@@@@"
				+ " JMSListenerQueue#receiveQueueMessage_3 (sendAgainNext.queue) : " + message + " -- Received at next queue. @@@@@ ########## \n");
	}
}
