package com.djr.streamer.auth.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by IMac on 7/29/2014.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class LoginRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	public String userName;
	@XmlElement
	public String password;

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
