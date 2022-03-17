/**
 * 
 */
package com.sjlh.kunlun.membership.core;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author Administrator
 *
 */
public class LocalDateTimeToTimestampSerializer extends JsonSerializer<LocalDateTime> {
	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if(value==null)return;
		ZoneId zid = ZoneId.systemDefault();
		zid.getRules().getOffset(value);
		ZoneOffset offset = zid.getRules().getOffset(value);
		gen.writeString(String.valueOf(value.toInstant(offset).toEpochMilli()));
	}
}