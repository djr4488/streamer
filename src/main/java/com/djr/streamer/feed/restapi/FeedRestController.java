package com.djr.streamer.feed.restapi;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * Created by IMac on 7/20/2014.
 */
@Path("feed")
@ApplicationScoped
public class FeedRestController {
    @Path("input")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response inputStream(@FormParam("inputStream") InputStream inputStream, @FormParam("uuid") String uuid) {
        //put on jms queue for listeners using uuid as the identifier for stream
        //if no listeners, the message just goes away(no persistence)
        //return ok that we got it
        return Response.ok().build();
    }
}
