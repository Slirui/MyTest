package com.util;

import java.text.DecimalFormat;

public class MoneyUtil {
	public static double 格式化金额(Double money) {
		if (money == null)
			return 0;
		String s = new DecimalFormat("0.00").format(money);
		// System.out.println(s);
		return Double.valueOf(s);

	}

	public static double 格式化金额00(Double money) {
		if (money == null)
			return 0;

		String s = new DecimalFormat("0.0").format(money);
		// System.out.println(s);
		return Double.valueOf(s);

	}

	public static void main(String[] args) {
		System.out.println(MoneyUtil.格式化金额(3.995222));
	}
}
