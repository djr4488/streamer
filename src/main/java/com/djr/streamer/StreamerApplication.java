package com.djr.streamer;

import com.djr.streamer.auth.restapi.AuthRestController;
import com.djr.streamer.feed.restapi.FeedRestController;
import com.djr.streamer.view.restapi.ViewRestController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IMac on 7/20/2014.
 */
@ApplicationPath("/api")
public class StreamerApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		return new HashSet<Class<?>>(Arrays.asList(FeedRestController.class, ViewRestController.class,
				AuthRestController.class));
	}
}
