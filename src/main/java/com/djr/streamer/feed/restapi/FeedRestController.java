package com.djr.streamer.feed.restapi;

import com.djr.streamer.jms.JMSStream;
import com.djr.streamer.jms.StreamBean;
import org.slf4j.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * Created by IMac on 7/20/2014.
 */
@Path("feed")
@ApplicationScoped
public class FeedRestController {
	@Inject
	private Logger log;
	@Inject
	private JMSStream jmsStream;

    @Path("input")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @POST
    public void inputStream(String uuid, String location, InputStream inputStream) {
		log.info("inputStream() uuid:{}", uuid);
	    jmsStream.sendToQueue(new StreamBean(uuid, location, inputStream));
    }
}
