package com.djr.streamer.view.restapi;


import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by IMac on 7/20/2014.
 */
@Path("view")
@RequestScoped
public class ViewRestController {
    @Path("outputStream")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @POST
    public Response outputStream(ViewRequest viewRequest) {
        final byte[] outputArray = null;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(outputArray.length);
        StreamingOutput streamingOutput = new StreamingOutput() {
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                try {
                    byteArrayOutputStream.write(outputArray, 0, outputArray.length);
                } catch (Exception e) {
                    throw new WebApplicationException(e);
                }
            }
        };;
        return Response.ok().entity(streamingOutput).build();
    }
}
