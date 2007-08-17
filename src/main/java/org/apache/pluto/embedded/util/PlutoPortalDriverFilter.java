package org.apache.pluto.embedded.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.pluto.driver.PortalDriverFilter;

public class PlutoPortalDriverFilter implements Filter {

	private PortalDriverFilter portalDriver;
	private String portletId;
	
	public PlutoPortalDriverFilter() {
		this.portalDriver = new PortalDriverFilter();
	}
	
	public void destroy() {
		portalDriver.destroy();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setAttribute("org_apache_pluto_embedded_portletId", portletId);
		portalDriver.doFilter(request, response, chain);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		portalDriver.init(filterConfig);
		portletId = System.getProperty("org.apache.pluto.embedded.portletId");
		if(portletId == null || "".equals(portletId.trim())) {
			throw new ServletException("No portlet id specified. Please set the system property \"org.apache.pluto.embedded.portletId\"");
		}
		String contextPath = filterConfig.getServletContext().getContextPath();
		StringBuffer tempId = new StringBuffer();
		if(!contextPath.startsWith("/")) {
			tempId.append("/");
		}
		tempId.append(contextPath).append(".").append(portletId).append("!");
		portletId = tempId.toString();
	}

}
