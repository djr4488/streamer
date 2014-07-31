package com.djr.streamer.view.restapi;

import javax.xml.bind.annotation.*;

/**
 * Created by IMac on 7/20/2014.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ViewRequest {
	@XmlElement
	public Integer streamId; // so as not to expose the internals too much this is a simple integer id
}
