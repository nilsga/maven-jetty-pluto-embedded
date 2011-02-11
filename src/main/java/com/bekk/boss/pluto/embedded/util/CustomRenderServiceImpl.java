package com.bekk.boss.pluto.embedded.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.pluto.driver.*;
import org.apache.pluto.driver.config.DriverConfigurationException;
import org.apache.pluto.driver.services.impl.resource.RenderConfigServiceImpl;
import org.apache.pluto.driver.services.impl.resource.ResourceConfig;
import org.apache.pluto.driver.services.portal.PageConfig;

public class CustomRenderServiceImpl extends RenderConfigServiceImpl {

	private List pages;
	
    public CustomRenderServiceImpl(ResourceConfig config) {
        super(config);
        pages = new ArrayList();
        init(org.apache.pluto.driver.PortalStartupListener.getServletContext());
    }

    public void addPage(PageConfig arg0) {
		pages.add(arg0);
	}

	public PageConfig getDefaultPage() {
		return (PageConfig)pages.get(0);
	}

	public PageConfig getPage(String arg0) {
		Iterator it = pages.iterator();
		while(it.hasNext()) {
			PageConfig page = (PageConfig)it.next();
			if(page.getName().equals(arg0)) {
				return page;
			}
		}
		return (PageConfig) pages.get(0);
	}

	public List getPages() {
		return pages;
	}

	public void removePage(PageConfig arg0) {
		pages.remove(arg0);
	}


	public void init(ServletContext arg0) throws DriverConfigurationException {
		PageConfig page = new PageConfig();
		page.setName("Embedded Portlets");
		page.setUri("/WEB-INF/themes/pluto-default-theme.jsp");
		String[] portletIds = System.getProperty("org.apache.pluto.embedded.portletIds").split(",");
		for(int i = 0; i < portletIds.length; i++) {
			page.addPortlet(arg0.getContextPath(), portletIds[i]);
		}
		pages.add(page);
	}

}
