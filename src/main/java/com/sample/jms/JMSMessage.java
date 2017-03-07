package com.sample.jms;

import java.io.Serializable;

/**
 * This is JMS message which can be published on any destination.
 * 
 * @author shyam.pareek
 *
 */
public class JMSMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	private Boolean jmsPublishedStatus = false;

	private Boolean jmsReceivedStatus = false;

	public JMSMessage() {
	}

	public JMSMessage(String message, Boolean jmsPublishedStatus, Boolean jmsReceivedStatus) {
		this.message = message;
		this.jmsPublishedStatus = jmsPublishedStatus;
		this.jmsReceivedStatus = jmsReceivedStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getJmsPublishedStatus() {
		return jmsPublishedStatus;
	}

	public void setJmsPublishedStatus(Boolean jmsPublishedStatus) {
		this.jmsPublishedStatus = jmsPublishedStatus;
	}

	public Boolean getJmsReceivedStatus() {
		return jmsReceivedStatus;
	}

	public void setJmsReceivedStatus(Boolean jmsReceivedStatus) {
		this.jmsReceivedStatus = jmsReceivedStatus;
	}

	@Override
	public String toString() {
		return String.format("JMS Message {message=%s, jmsPublishedStatus = %b, jmsReceivedStatus = %b}", message,
				jmsPublishedStatus, jmsReceivedStatus);
	}

}