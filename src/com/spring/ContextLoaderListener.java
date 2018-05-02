package com.spring;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;

import com.json.MyGson;
import com.spring.bean.AppConfig;
import com.util.Random;

/**
 * Application Lifecycle Listener implementation class ContextLoaderListener
 *
 */
public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener
		implements ServletContextListener {
	// @Autowired
	// AppConfig appconfig;
	/**
	 * @see org.springframework.web.context.ContextLoaderListener#org.springframework.web.context.ContextLoaderListener()
	 */
	public ContextLoaderListener() {
		super();

	}

	/**
	 * @see org.springframework.web.context.ContextLoaderListener#org.springframework.web.context.ContextLoaderListener(WebApplicationContext)
	 */
	public ContextLoaderListener(WebApplicationContext context) {
		super(context);

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		super.contextInitialized(arg0);
		System.out.println("-----sun.jnu.encoding---=" + System.getProperty("sun.jnu.encoding"));
		System.setProperty("user.timezone", "GMT +08");
		arg0.getServletContext().setAttribute("appname", arg0.getServletContext().getServletContextName());
		arg0.getServletContext().setAttribute("gson", new MyGson());
		arg0.getServletContext().setAttribute("apprandom", new Random());
		AppConfig.appPath = arg0.getServletContext().getRealPath("/");
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		super.contextDestroyed(arg0);
	}

}
