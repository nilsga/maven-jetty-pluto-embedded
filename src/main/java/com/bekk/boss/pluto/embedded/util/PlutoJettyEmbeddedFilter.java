package com.bekk.boss.pluto.embedded.util;

import javax.servlet.*;
import java.io.IOException;


public class PlutoJettyEmbeddedFilter implements Filter {

    String[] styles = new String[0];

    public void init(FilterConfig filterConfig) throws ServletException {
        String extraStyles = System.getProperty("org.apache.pluto.embedded.extraStyles");
        if (extraStyles != null) {
            styles = extraStyles.split(",");
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setAttribute("org_apache_pluto_embedded_extraStyles", styles);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }
}
