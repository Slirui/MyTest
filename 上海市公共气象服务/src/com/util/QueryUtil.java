package com.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class QueryUtil {
	public static Object[] process(Object[] list) {
		for (int i = 0; i < list.length; i++) {
			if (list[i] != null && list[i] instanceof BigDecimal) {
				list[i] = ((BigDecimal) list[i]).doubleValue();
			}
			if (list[i] != null && list[i] instanceof BigInteger) {
				list[i] = ((BigInteger) list[i]).intValue();
			}
		}
		return list;
	}
}
