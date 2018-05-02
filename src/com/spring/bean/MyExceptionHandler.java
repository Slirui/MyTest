package com.spring.bean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


@Component
public class MyExceptionHandler extends SimpleMappingExceptionResolver {
	Logger logger = Logger.getLogger(getClass());
	public static String 重复请求 = "微信重复请求";

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if (ex.getMessage() != null && ex.getMessage().equals(重复请求)) {
			logger.error("发生错误，用户没有session");
		} else
			logger.error("发生错误", ex);
		String viewName = "/errorpage";// determineViewName(ex, request);
		if (viewName != null) {// JSP格式返回
			if (!(request.getHeader("accept").indexOf("application/json") > -1
					|| (request.getHeader("X-Requested-With") != null
							&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
				// 如果 不是异步请求
				// Apply HTTP status code for error views, if specified.
				// Only apply it if we're processing a top-level request.
				// Integer statusCode = determineStatusCode(request, viewName);
				// if (statusCode != null) {
				// applyStatusCodeIfPossible(request, response, statusCode);
				// }
				return new ModelAndView(viewName, "exception", ex);
			} else {// JSON格式返回
				try {
					PrintWriter writer = response.getWriter();
					writer.write(ex.getMessage());
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		} else {
			return null;
		}

	}
}
