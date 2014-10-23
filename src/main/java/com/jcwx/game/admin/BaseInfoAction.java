package com.jcwx.game.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.jcwx.game.admin.jsonview.JSONResponse;
import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.IBusiness;
import com.jcwx.game.common.OssContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseInfoAction extends ActionSupport implements
	ServletContextAware, ServletResponseAware, ServletRequestAware,
	SessionAware,IBusiness  {

    private static final long serialVersionUID = 1L;

    private BaseAdminContext baseAdminContext;

    protected HttpServletRequest httpServletRequest;

    protected HttpServletResponse httpServletResponse;

    protected HttpSession httpSession;

    private ServletContext servletContext;

    private Map<String, Object> session;

    protected BaseAdminContext getBaseAdminContext() {
	if (baseAdminContext == null) {
	    baseAdminContext = OssContext.getBaseAdminContext();
	}
	return baseAdminContext;
    }

    protected HttpServletRequest getHttpServletRequest() {
	return this.httpServletRequest;
    }

    protected HttpServletResponse getHttpServletResponse() {
	return this.httpServletResponse;
    }

    protected HttpSession getHttpSession() {
	return this.httpSession;
    }

    protected JSONResponse getJSONResponse() {
	try {
	    return new JSONResponse(getHttpServletResponse());
	} catch (IOException e) {
	    throw new RuntimeException("JSONResponse initial error!", e);
	}
    }

    protected ServletContext getServletContext() {
	return this.servletContext;
    }

    public Map<String, Object> getSession() {
	return this.session;
    }

    protected void setHttpServletRequest(HttpServletRequest httpServletRequest) {
	this.httpServletRequest = httpServletRequest;
    }

    protected void setHttpServletResponse(
	    HttpServletResponse httpServletResponse) {
	this.httpServletResponse = httpServletResponse;
    }

    protected void setHttpSession(HttpSession httpSession) {
	this.httpSession = httpSession;
    }

    @Override
    public void setServletContext(ServletContext context) {
	this.servletContext = context;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
	this.httpServletRequest = request;
	this.httpSession = request.getSession();
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
	this.httpServletResponse = response;
    }

    @Override
    public void setSession(Map<String, Object> session) {
	this.session = session;
    }

}
