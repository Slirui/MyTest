package com.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.tag.FormatFunction;

public class DateUtil {

	public static Date toDay() {
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}

	public static Date parse(String s) {

		String[] pattern = new String[] { "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd",
				"yyyyMMddHHmm", "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy年M月d日H时",
				"yyyy年MM月dd日HH时" };

		try {
			Date d = DateUtils.parseDate(s, pattern);
			System.out.println(FormatFunction.formatDate(d, "yyyy年MM月dd日HH时"));
			return d;
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * 
	 */
	public static int daysBetween(Date smdate, Date bdate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static Date getNow() {
		Date date = new Date();
		try {
			return new java.text.SimpleDateFormat("yyyy-MM-dd")
					.parse(FormatFunction.formatDate(new Date(), "yyyy-MM-dd"));
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return null;

	}
}
