package org.apache.pluto.embedded.util;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public class WrappedServletContext implements ServletContext {

	private ServletContext realContext;

	public WrappedServletContext(ServletContext realContext) {
		this.realContext = realContext;
	}

	public Object getAttribute(String arg0) {
		return realContext.getAttribute(arg0);
	}

	public Enumeration getAttributeNames() {
		return realContext.getAttributeNames();
	}

	public ServletContext getContext(String arg0) {
		return realContext.getContext(arg0);
	}

	public String getContextPath() {
		return realContext.getContextPath();
	}

	public String getInitParameter(String arg0) {
		return realContext.getInitParameter(arg0);
	}

	public Enumeration getInitParameterNames() {
		return realContext.getInitParameterNames();
	}

	public int getMajorVersion() {
		return realContext.getMajorVersion();
	}

	public String getMimeType(String arg0) {
		return realContext.getMimeType(arg0);
	}

	public int getMinorVersion() {
		return realContext.getMinorVersion();
	}

	public RequestDispatcher getNamedDispatcher(String arg0) {
		return realContext.getNamedDispatcher(arg0);
	}

	public String getRealPath(String arg0) {
		return realContext.getRealPath(arg0);
	}

	public RequestDispatcher getRequestDispatcher(String arg0) {
		return realContext.getRequestDispatcher(arg0);
	}

	public URL getResource(String arg0) throws MalformedURLException {
		URL resource = realContext.getResource(arg0);
		if(resource == null) {
			//resolve from classpath
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			resource = classloader.getResource(arg0);
		}
		return resource;
	}

	public InputStream getResourceAsStream(String arg0) {
		InputStream stream = realContext.getResourceAsStream(arg0);
		if(stream == null) {
			// resolve from classpath
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			stream = classloader.getResourceAsStream(arg0);
		}
		return stream;
	}

	public Set getResourcePaths(String arg0) {
		return realContext.getResourcePaths(arg0);
	}

	public String getServerInfo() {
		return realContext.getServerInfo();
	}

	public Servlet getServlet(String arg0) throws ServletException {
		return realContext.getServlet(arg0);
	}

	public String getServletContextName() {
		return realContext.getServletContextName();
	}

	public Enumeration getServletNames() {
		return realContext.getServletNames();
	}

	public Enumeration getServlets() {
		return realContext.getServlets();
	}

	public void log(Exception arg0, String arg1) {
		realContext.log(arg0, arg1);
	}

	public void log(String arg0, Throwable arg1) {
		realContext.log(arg0, arg1);
	}

	public void log(String arg0) {
		realContext.log(arg0);
	}

	public void removeAttribute(String arg0) {
		realContext.removeAttribute(arg0);
	}

	public void setAttribute(String arg0, Object arg1) {
		realContext.setAttribute(arg0, arg1);
	}
	
	
}