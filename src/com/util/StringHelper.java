package com.util;

public class StringHelper {
	public static String join(String[] array) {
		return join(array, ",");
	}

	public static String join(String[] array, String delimiter) {
		 
		if (array.length == 0)
			return "";
		if (array.length == 1) {
			if (array[0] == null)
				return "";
			return array[0];
		}
		return join(new StringBuffer(),array,delimiter);
	}

	// of the resulting string. int length = 0; for (int i=0; inull. * @return
	// Joined string. */
	public static String join(final StringBuffer buff, final Object array[], final String delim) {
		boolean haveDelim = (delim != null);
		for (int i = 0; i < array.length; i++) {
			buff.append(array[i]);
			// if this is the last element then don't append delim
			if (haveDelim && (i + 1) < array.length) {
				buff.append(delim);
			}
		}
		return buff.toString();
	}

}
