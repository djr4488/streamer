package com.djr.streamer.feed.websocket;

import com.djr.streamer.jms.JMSStream;
import com.djr.streamer.jms.StreamBean;
import org.slf4j.Logger;
import javax.inject.Inject;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

	@Path("feed")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	@POST
	public void feed(String uuid, String location, InputStream inputStream) {
		log.info("feed() uuid:{}", uuid);
		jmsStream.sendToQueue(new StreamBean(uuid, location, inputStream));
	}
}
