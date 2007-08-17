package org.apache.pluto.embedded.util;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.servlet.JspServlet;

public class PortletJspServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4786258619341271660L;
	public JspServlet jspServlet;
	
	public PortletJspServlet() {
		jspServlet = new JspServlet();
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		jspServlet.service(request, response);
	}

	public void destroy() {
		jspServlet.destroy();
		super.destroy();
	}

	public void init(final ServletConfig servletConfig) throws ServletException {
		jspServlet.init(new ServletConfig() {

			public String getInitParameter(String arg0) {
				return servletConfig.getInitParameter(arg0);
			}

			public Enumeration getInitParameterNames() {
				return servletConfig.getInitParameterNames();
			}

			public ServletContext getServletContext() {
				return new WrappedServletContext(servletConfig.getServletContext());
			}

			public String getServletName() {
				return servletConfig.getServletName();
			}
			
		});
		super.init(servletConfig);
	}

}
