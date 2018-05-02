package com.spring.bean;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

//@Configuration
//@EnableWebSocket
public class AppConfig {
	
	public static boolean staticdebug = false;
	@Value("${debug}")
	public boolean debug = false;

	public static String appPath;

	public static String contextPath = "";

	public static HashMap<String, Object> map = new HashMap<String, Object>();
	
	@PostConstruct
	public void init() {

	}

}
