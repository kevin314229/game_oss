package com.jcwx.game.common;

import javax.servlet.ServletContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jcwx.game.service.impl.PlayerItemService;

/**
 * Spring服务
 */

public class SpringService implements ApplicationContextAware{

    private static ServletContext servletContext;
    private static ApplicationContext webApplictionContext;

    public static <T> T getBean(Class<T> name) {
	return webApplictionContext.getBean(name);
    }

    public static Object getBean(String name) {
	return webApplictionContext.getBean(name);
    }

    public static ServletContext getServletContext() {
	return servletContext;
    }

    public static void setServletContext(ServletContext servletContext) {
	SpringService.servletContext = servletContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
	    throws BeansException {
	webApplictionContext = applicationContext;
    }

}
