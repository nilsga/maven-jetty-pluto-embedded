/*
 * $Id: $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
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
