package com.djr.streamer.jms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * Created by IMac on 1/17/2015.
 */
public class JMSBean {
	public Connection connection;
	public Session session;
	public Destination destination;
	public MessageProducer messageProducer;

	public JMSBean(Connection connection, Session session, Destination destination, MessageProducer messageProducer) {
		this.connection = connection;
		this.session = session;
		this.destination = destination;
		this.messageProducer = messageProducer;
	}
}
