package com.djr.streamer.jms;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.io.InputStream;

/**
 * Created by IMac on 7/27/2014.
 */
public class StreamBean {
	public String uuid;
	public String location;
	public InputStream inputStream;

	public StreamBean() {

	}

	public StreamBean(String uuid, String location, InputStream inputStream) {
		this.uuid = uuid;
		this.location = location;
		this.inputStream = inputStream;
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
