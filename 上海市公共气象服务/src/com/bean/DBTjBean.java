package com.bean;

public class DBTjBean {
	public String key;
	public Integer num;

	public DBTjBean(String key, Integer num) {
		super();
		this.key = key;
		this.num = num;
	}
	public DBTjBean(Integer key, Integer num) {
		super();
		this.key = key+"";
		this.num = num;
	}
}
