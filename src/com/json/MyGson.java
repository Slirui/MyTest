package com.json;

import com.google.gson.GsonBuilder;

public class MyGson {
	
	public static String toJson(Object o) {
		return new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(o);
	}

	public static String toJson(Object o, String dateFormat) {
		return new GsonBuilder().serializeNulls().setDateFormat(dateFormat).create().toJson(o);
	}

	public static <T> T fromJson(String json, Class<T> classOfT) {
		return new GsonBuilder().serializeNulls().create().fromJson(json, classOfT);
	}
}
