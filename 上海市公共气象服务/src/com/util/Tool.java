/**
 * 
 */
package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 分裂状态。
 *
 */
public class Tool {

	public static String dateFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
