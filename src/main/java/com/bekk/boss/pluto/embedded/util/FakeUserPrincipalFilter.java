package com.bekk.boss.pluto.embedded.util;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class FakeUserPrincipalFilter implements Filter {

	private Principal fakePrincipal;
	private List principalRoles;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if (fakePrincipal != null && request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {

				public String getRemoteUser() {
					return fakePrincipal.getName();
				}

				public Principal getUserPrincipal() {
					return fakePrincipal;
				}

				public boolean isUserInRole(String roleName) {
					return principalRoles != null && principalRoles.contains(roleName);
				}

			};
			chain.doFilter(wrapper, response);
		}
		else {
			chain.doFilter(request, response);
		}
	}

	public void init(final FilterConfig config) throws ServletException {
		final String principalName = System.getProperty("org.apache.pluto.embedded.principalName");
		final String principalRolesStr = System.getProperty("org.apache.pluto.embedded.principalRoles");
		if (principalName != null) {
			if(principalRolesStr != null) {
				String[] roles = principalRolesStr.split(",");
				principalRoles = Arrays.asList(roles);
			}
			fakePrincipal = new Principal() {
				public String getName() {
					return principalName;
				}
				
				public String toString() {
					return "PrincipalName: " + principalName + ", Roles: " + principalRoles;
				}
			};
		}
	}

}
