package com.json;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class UtilDateSerializer implements JsonSerializer<java.util.Date> {
	String formatstr = "yyyy-MM-dd HH:mm:ss";

	public UtilDateSerializer(String dateformat) {
		this.formatstr = dateformat;
	}

	public UtilDateSerializer() {

	}
	
	@Override
	public JsonElement serialize(java.util.Date src, Type typeOfSrc, JsonSerializationContext context) {
		SimpleDateFormat format = new SimpleDateFormat(formatstr);
		return new JsonPrimitive(format.format(src));
	}

}
