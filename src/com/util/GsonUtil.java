package com.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.json.UtilDateSerializer;

public class GsonUtil {
	public static Gson getNoEscapeGson()
	{
		return new GsonBuilder().disableHtmlEscaping().create();
	}
	public static Gson getNullableGson()
	{
		return new GsonBuilder().serializeNulls().create();
	}
	public static Gson getNullAndDateFormartGson(String dateformat)
	{
		return new GsonBuilder().serializeNulls().registerTypeAdapter(java.util.Date.class, new UtilDateSerializer(dateformat)).create();
	}
}
