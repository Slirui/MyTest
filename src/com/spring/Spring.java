package com.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

@Component
public class Spring extends ApplicationObjectSupport {
	static Spring springContextHolder;

	public Spring() {
		springContextHolder = this;
	}

	public static ApplicationContext getAppContext() {
		return springContextHolder.getApplicationContext();
	}

	public static <T> T getBean(Class<T> t) {
		return springContextHolder.getApplicationContext().getBean(t);
	}
}
