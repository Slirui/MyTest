package com.spring;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String appname;
	public static String apppath;

	public void init(ServletConfig config) throws ServletException {
		appname = config.getServletContext().getContextPath();
		apppath = config.getServletContext().getRealPath("/");
		System.out.println(apppath);
		config.getServletContext().setAttribute("appname", appname);
		super.init(config);
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		super.doService(request, response);
	}
}
