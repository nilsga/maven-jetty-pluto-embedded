package com.bekk.boss.pluto.embedded.jetty.util;

import java.lang.reflect.Field;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.pluto.container.impl.PortletSessionImpl;
import org.mortbay.jetty.servlet.HashSessionManager;

public class PlutoJettySessionManager extends HashSessionManager {

	public Cookie access(HttpSession session, boolean secure) {
		if (session instanceof PortletSessionImpl) {
			PortletSessionImpl realSession = (PortletSessionImpl) session;
			try {
				Field sessionField = realSession.getClass().getDeclaredField(
						"httpSession");
				sessionField.setAccessible(true);
				HttpSession sess = (HttpSession) sessionField.get(realSession);
				return super.access(sess, secure);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		} else {
			return super.access(session, secure);
		}
	}

}
