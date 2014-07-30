package com.djr.streamer.auth.restapi;

import com.djr.streamer.auth.model.LoginRequest;
import com.djr.streamer.auth.service.ActiveUuidService;
import org.slf4j.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by IMac on 7/29/2014.
 */
@Path("auth")
public class AuthRestController {
	@Inject
	private Logger log;
	@Inject
	private ActiveUuidService activeUuidService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public Response login(LoginRequest loginRequest) {
		return Response.ok().build();
	}
}
