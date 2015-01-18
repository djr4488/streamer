package com.djr.streamer.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IMac on 7/27/2014.
 */
@ApplicationScoped
public class JMSStream {
	@Inject
	private ConnectionFactory connectionFactory;
	@Inject
	private String userName;
	@Inject
	private String password;
	@Inject
	private Logger log;
	private ConcurrentHashMap<String, JMSBean> jmsBeanMap = new ConcurrentHashMap<>();
	private static final Object lock = new Object();

	public void sendToQueue(StreamBean streamBean) {
		try {
			JMSBean jmsBean = getConnection(streamBean);
			StreamMessage streamMessage = jmsBean.session.createStreamMessage();
			streamMessage.writeObject(streamBean);
			jmsBean.messageProducer.send(streamMessage);
		} catch (JMSException jmsEx) {
			log.error("sendToQueue() failed with ", jmsEx);
		}
	}

	private JMSBean getConnection(StreamBean streamBean) throws JMSException {
		if (jmsBeanMap.contains(streamBean.uuid)) {
			return jmsBeanMap.get(streamBean.uuid);
		}
		synchronized (lock) {
			Connection c = connectionFactory.createConnection(userName, password);
			c.start();
			Session s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination d = s.createQueue(streamBean.uuid);
			MessageProducer mp = s.createProducer(d);
			mp.setDeliveryMode(DeliveryMode.PERSISTENT);
			jmsBeanMap.put(streamBean.uuid, new JMSBean(c, s, d, mp));
		}
		return jmsBeanMap.get(streamBean.uuid);
	}
}
