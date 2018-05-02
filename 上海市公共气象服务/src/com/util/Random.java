package com.util;

public class Random {
	public String get(String s) {
		return s + "pageRandom=" + (int) (Math.random() * 100) + "";
	}

	public String get() {
		return get("?");
	}
}
