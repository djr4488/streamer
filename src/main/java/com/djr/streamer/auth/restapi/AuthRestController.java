package com.djr.streamer.auth.restapi;

import com.djr.streamer.auth.model.LoginRequest;
import com.djr.streamer.auth.model.LoginResponse;
import com.djr.streamer.auth.service.ActiveUuidService;
import com.djr.streamer.auth.service.AuthService;
import org.slf4j.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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
	private AuthService authService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public Response login(LoginRequest loginRequest) {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.loginRequired = false;
		loginResponse.uuid = authService.doLogin(loginRequest);
		return Response.ok().entity(loginResponse).build();
	}
}
