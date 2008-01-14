package com.bekk.boss.pluto.embedded.jetty.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.pluto.driver.core.PortalServletRequest;
import org.mortbay.jetty.Request;
import org.mortbay.util.MultiMap;

public class CustomDispatcher implements RequestDispatcher {

	private RequestDispatcher dispatcher;

	public CustomDispatcher(RequestDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public void include(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		if (request instanceof PortalServletRequest) {
			Map paramMap = request.getParameterMap();
			request = ((PortalServletRequest) request).getRequest();
			if (request instanceof Request) {
				Request tempReq = (Request) request;
				MultiMap tmpMap = new MultiMap();
				if (paramMap != null) {
					Iterator it = paramMap.entrySet().iterator();
					while(it.hasNext()) {
						Map.Entry entry = (Map.Entry)it.next();
						tmpMap.putValues(entry.getKey(), (String[])entry.getValue());
					}
					tempReq.setParameters(tmpMap);
				}
			}
		}
		dispatcher.include(request, response);
	}

	public void forward(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		dispatcher.forward(arg0, arg1);
	}

}
