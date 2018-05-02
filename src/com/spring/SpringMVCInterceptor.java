package com.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SpringMVCInterceptor implements HandlerInterceptor {
	Logger logger = Logger.getLogger(getClass());

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (ex != null) {
			Logger logger = Logger.getLogger(getClass());
			logger.error(ex);
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取请求的URL
		String url = request.getRequestURI();
		if (url.indexOf("login.shtml") >= 0) {
			return true;
		}
		HttpSession session = request.getSession();
		String userName = null;
		if (session.getAttribute("userName") != "" && session.getAttribute("userName") != null) {
			userName = session.getAttribute("userName").toString();
		}
		if (userName != null) {
			return true;
		}
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return false;
	}

}
