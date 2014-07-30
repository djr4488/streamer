package com.djr.streamer.auth.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IMac on 7/29/2014.
 */
public class ActiveUuidService {
	private static final Map<String, Calendar> activeUuids = new HashMap<>();
	private static final Object lock = new Object();

	public void add(String uuid) {
		synchronized (lock) {
			if (!activeUuids.containsKey(uuid)) {
				activeUuids.put(uuid, Calendar.getInstance());
			}
		}
	}

	public boolean isActiveUuid(String uuid) {
		if (activeUuids.containsKey(uuid)) {
			return true;
		}
		synchronized (lock) {
			if (activeUuids.containsKey(uuid)) {
				return true;
			}
		}
		return false;
	}
}
