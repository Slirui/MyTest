package com.spring;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;
import com.spring.bean.AvoidDuplicateSubmission;

public class AvoidDuplicateSubmissionInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOG = Logger.getLogger(AvoidDuplicateSubmissionInterceptor.class);
	private static final Random RANDOM = new Random();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
		if (annotation != null) {
			boolean needSaveSession = annotation.needSaveToken();
			if (needSaveSession) {
				request.getSession(false).setAttribute("token", generateGUID());
			}
			boolean needRemoveSession = annotation.needRemoveToken();
			if (needRemoveSession) {
				if (isRepeatSubmit(request)) {
					LOG.warn("please don't repeat submit,[ url:" + request.getServletPath() + "]");
					handleInvalidToken(request, response, handler);
					return false;
				}
				request.getSession(false).removeAttribute("token");
			}
		}
		return true;
	}

	/**
	 * 当出现一个非法令牌时调用
	 */
	protected boolean handleInvalidToken(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("flag", 0);
		data.put("msg", "请不要频繁操作！");
		writeMessageUtf8(response, data);
		return false;
	}

	private void writeMessageUtf8(HttpServletResponse response, Map<String, Object> json) throws IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(new Gson().toJson(json));
		} finally {
			response.getWriter().close();
		}
	}

	public static String generateGUID() {
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		if (request.getSession(false) == null) {
			return true;
		}
		String serverToken = (String) request.getSession(false).getAttribute("token");
		if (serverToken == null) {
			return true;
		}
		String clinetToken = request.getParameter("token");
		if (clinetToken == null) {
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}

}