package com.djr.streamer.auth.service;

import javax.ejb.Schedule;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IMac on 7/29/2014.
 */
public class ActiveUuidService {
	private static final Map<String, Calendar> activeUuids = new ConcurrentHashMap<>();
	private static final Map<Calendar, List<String>> calendarMapOfUuids = new TreeMap<>();
	private boolean currentlyInCleanUp = false;
	private static final Object lock = new Object();

	public void add(String uuid) {
		if (!activeUuids.containsKey(uuid)) {
			Calendar uuidTimeStamp = Calendar.getInstance();
			activeUuids.put(uuid, uuidTimeStamp);
			if (!calendarMapOfUuids.containsKey(uuidTimeStamp)) {
				calendarMapOfUuids.put(uuidTimeStamp, new ArrayList<String>());
			}
			calendarMapOfUuids.get(uuidTimeStamp).add(uuid);
		}
	}

	public boolean isActiveUuid(String uuid) {
		if (activeUuids.containsKey(uuid)) {
			//rearrange the uuid
			calendarMapOfUuids.get(activeUuids.get(uuid)).remove(uuid);
			add(uuid);
			return true;
		}
		return false;
	}

	@Schedule(minute = "*/5")
	public void cleanUpUuid() {
		synchronized (lock) {
			if (currentlyInCleanUp) {
				return;
			}
			currentlyInCleanUp = true;
		}
		Set<Calendar> unmodifiableCalendarSet = Collections.unmodifiableSet(calendarMapOfUuids.keySet());
		Calendar currentTime = Calendar.getInstance();
		currentTime.set(Calendar.MINUTE, currentTime.get(Calendar.MINUTE) - 5);
		for (Calendar candidateToRemove : unmodifiableCalendarSet) {
			if (candidateToRemove.before(currentTime)) {
				for (String uuid : calendarMapOfUuids.get(candidateToRemove)) {
					activeUuids.remove(uuid);
				}
				calendarMapOfUuids.remove(candidateToRemove);
			} else {
				break;
			}
		}
		synchronized (lock) {
			currentlyInCleanUp = false;
		}
	}
}
