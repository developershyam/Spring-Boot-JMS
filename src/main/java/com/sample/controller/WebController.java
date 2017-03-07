package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.service.JMSService;

/**
 * This is Rest controller used to expose URL for application access.
 * 
 * @author shyam.pareek
 *
 */
@RestController
public class WebController {

	@Autowired
	private JMSService jmsService;

	String INFO = "{ JMS Topic BaseURL ==> /publishJMSTopicMessage ,  JMS Queue BaseURL ==> /publishJMSQueueMessage }";

	@RequestMapping("/")
	public String home() {
		return INFO;
	}

	@RequestMapping("/publishJMSTopicMessage")
	public String publishJMSTopicMessage() {

		jmsService.publishJMSTopicMessage();
		return "Your message is published on Topic. Please check console/log!!! " + INFO;
	}

	@RequestMapping("/publishJMSQueueMessage")
	public String sendEmail() {

		jmsService.publishJMSQueueMessage();
		return "Your message is published on Queue. Please check console/log!!! " + INFO;
	}

}
