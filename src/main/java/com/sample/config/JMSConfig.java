package com.sample.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * This class is designed for JMS configuration.  
 * 
 * @author shyam.pareek
 *
 */
@Configuration
@EnableJms
public class JMSConfig {

	private static final String JMS_BROKER_URL = "vm://embedded?broker.persistent=false,useShutdownHook=false";
	public static final String JMS_TOPIC_DESTINATION = "destination.topic";
	public static final String JMS_QUEUE_DESTINATION = "destination.queue";

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Bean
	public ActiveMQConnectionFactory amqConnectionFactory() {

		return new ActiveMQConnectionFactory(JMS_BROKER_URL);

	}

	@Bean
	public CachingConnectionFactory connectionFactory() {

		return new CachingConnectionFactory(amqConnectionFactory());

	}

	@Bean
	public JmsTemplate jmsTemplateTopic() {

		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
		jmsTemplate.setDefaultDestinationName(JMS_TOPIC_DESTINATION);
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
		jmsTemplate.setPubSubDomain(true);
		return jmsTemplate;
	}

	@Bean
	public JmsTemplate jmsTemplateQueue() {

		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
		jmsTemplate.setDefaultDestinationName(JMS_QUEUE_DESTINATION);
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
		return jmsTemplate;
	}

	@Bean
	public JmsListenerContainerFactory<?> jmsTopicFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

		factory.setConnectionFactory(connectionFactory());
		configurer.configure(factory, connectionFactory());
		factory.setPubSubDomain(true);
		return factory;
	}

	@Bean
	public JmsListenerContainerFactory<?> jmsQueueFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

		factory.setConnectionFactory(connectionFactory());
		configurer.configure(factory, connectionFactory());
		return factory;
	}
}
