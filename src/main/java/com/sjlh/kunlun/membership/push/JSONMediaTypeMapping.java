/**
 * 
 */
package com.sjlh.kunlun.membership.push;

import javax.ws.rs.core.MediaType;

import com.sjlh.hotel.common.net.MediaTypeMapping;

/**
 * @author Administrator
 *
 */
public class JSONMediaTypeMapping implements MediaTypeMapping {
	public MediaType mapping(MediaType mediaType) {
		if(mediaType!=null && mediaType.getSubtype()!=null && !mediaType.getSubtype().startsWith("json"))mediaType = MediaType.APPLICATION_JSON_TYPE;
		return mediaType;
	}
}