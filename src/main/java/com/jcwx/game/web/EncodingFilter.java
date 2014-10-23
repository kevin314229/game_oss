package com.jcwx.game.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
    protected String encoding = null;
    protected FilterConfig filterConfig = null;

    @Override
    public void destroy() {
	this.encoding = null;
	this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	// Select and set (if needed) the character encoding to be used
	String encoding = selectEncoding(request);

	if (encoding != null) {
	    request.setCharacterEncoding(encoding);
	} else {
	    request.setCharacterEncoding(filterConfig
		    .getInitParameter("encoding"));
	}
	// Pass control on to the next filter
	chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	this.filterConfig = filterConfig;
	this.encoding = filterConfig.getInitParameter("encoding");
    }

    protected String selectEncoding(ServletRequest request) {
	return (this.encoding);
    }
}