package com.jcwx.game.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.jcwx.game.admin.constant.AdminSystemConstant;
import com.jcwx.game.common.BaseAdminContext;

public class AdminSessionFilter implements Filter {
    private static final String LOGIN_ACTION = "/admin/login_toLogin.action";
    private static final String NO_PURVIEW = "/admin/nopurview.jsp";
    protected FilterConfig filterConfig = null;

    @Override
    public void destroy() {
	this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
	    ServletResponse servletResponse, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) servletRequest;
	HttpServletResponse response = (HttpServletResponse) servletResponse;
	String currentURL = request.getRequestURI();
	String realUrl = StringUtils.remove(currentURL,
		request.getContextPath());
	boolean isCheck = true;
	NoCheckUrlEnum[] noCheckUrlEnums = NoCheckUrlEnum.values();
	for (NoCheckUrlEnum noCheckUrlEnum : noCheckUrlEnums) {
	    if (noCheckUrlEnum.getCode().equals(realUrl)) {
		isCheck = false;
		break;
	    }
	}
	if (isCheck) {
	    HttpSession session = request.getSession(false);
	    if (session == null) {
		response.sendRedirect(request.getContextPath() + LOGIN_ACTION);
		return;
	    }
	    BaseAdminContext baseAdminContext = (BaseAdminContext) session
		    .getAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY);
	    if (baseAdminContext == null) {
		response.sendRedirect(request.getContextPath() + LOGIN_ACTION);
		return;
	    } else {
		realUrl = StringUtils.remove(realUrl, ".action");
		int i = StringUtils.indexOf(realUrl, "_");
		if (i != -1) {
		    realUrl = StringUtils.substring(realUrl, 0, i + 1);
		}
		if (!baseAdminContext.allow(realUrl)) {
		    response.sendRedirect(request.getContextPath() + NO_PURVIEW);
		    return;
		}
	    }
	}
	chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	this.filterConfig = filterConfig;
    }
}
