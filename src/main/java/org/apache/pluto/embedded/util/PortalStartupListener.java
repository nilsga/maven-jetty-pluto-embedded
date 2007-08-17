package org.apache.pluto.embedded.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PortalStartupListener implements ServletContextListener {

	private org.apache.pluto.driver.PortalStartupListener realStartupListener;
	
	public PortalStartupListener() {
		this.realStartupListener = new org.apache.pluto.driver.PortalStartupListener();
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		realStartupListener.contextDestroyed(sce);
	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext wrapped = new WrappedServletContext(sce.getServletContext());
		realStartupListener.contextInitialized(new ServletContextEvent(wrapped));
	}

}
