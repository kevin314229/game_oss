package com.jcwx.game.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class PageCacheFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	HttpServletResponse httpServletResponse = (HttpServletResponse) response;

	httpServletResponse.setHeader("Pragma", "No-cache");
	httpServletResponse.setHeader("Cache-Control", "no-cache");
	httpServletResponse.setDateHeader("Expires", -10);

	chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
