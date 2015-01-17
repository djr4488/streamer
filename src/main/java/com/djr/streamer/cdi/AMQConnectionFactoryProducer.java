package com.djr.streamer.cdi;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;

/**
 * Created by IMac on 1/17/2015.
 */
@ApplicationScoped
public class AMQConnectionFactoryProducer {
	@Inject
	private String activeMqUrl;
	@Inject
	private String activeMqUser;
	@Inject
	private String activeMqPassword;
	@Inject
	private Logger log;

	@Produces
	public ConnectionFactory produceConnectionFactory(InjectionPoint ip) {
		Class<?> injectingClass = ip.getMember().getDeclaringClass();
		log.debug("getLogger() injectingClass:{}", injectingClass.getName());
		return new ActiveMQConnectionFactory(activeMqUrl);
	}
}
