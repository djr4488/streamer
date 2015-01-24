package com.djr.streamer.feed.websocket;

import com.djr.streamer.jms.JMSStream;
import com.djr.streamer.jms.StreamBean;
import org.slf4j.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by IMac on 7/20/2014.
 */
@ServerEndpoint("/api/streamer")
public class FeedWebsocketController {
	@Inject
	private Logger log;
	@Inject
	private JMSStream jmsStream;
	private Session wsSession;
	private HttpSession httpSession;

	@OnOpen
	public void open(Session session, EndpointConfig endpointConfig) {
		this.wsSession = session;
		this.httpSession = (HttpSession)endpointConfig.getUserProperties().get(HttpSession.class.getName());
	}

	@OnMessage
	public void message(byte[] data, Session session) {
		InputStream is = new ByteArrayInputStream(data);
		String uuid = (String)((HttpSession)session.getUserProperties().get(HttpSession.class.getName())).getAttribute("uuid");
		feed(uuid, null, is);
	}

	public void feed(String uuid, String location, InputStream inputStream) {
		log.info("feed() uuid:{}", uuid);
		jmsStream.sendToQueue(new StreamBean(uuid, location, inputStream));
	}
}
