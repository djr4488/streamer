package com.djr.streamer.auth.service;

import com.djr.streamer.auth.model.LoginRequest;
import org.slf4j.Logger;
import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by IMac on 7/31/2014.
 */
public class AuthService {
	@Inject
	private ActiveUuidService activeUuidService;
	@Inject
	private Logger log;

	public boolean isActiveToken(String token) {
		if (activeUuidService.isActiveUuid(token)) {
			return true;
		}
		return false;
	}

	public void addToken(String token) {
		activeUuidService.add(token);
	}

	public String doLogin(LoginRequest loginRequest) {
		return UUID.randomUUID().toString();
	}
}
