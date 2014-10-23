package com.jcwx.game.common;

import javax.servlet.http.HttpServletRequest;

public class WebUtilService {

    /**
     * 获得真实的IP地址
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
	String ip = request.getHeader("X-Forwarded-For");
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    ip = request.getHeader("Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    ip = request.getHeader("HTTP_CLIENT_IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    ip = request.getRemoteAddr();
	}
	// 通过反向代理的用户可能有多个IP，区最后一个IP
	try {
	    if (ip.length() > 15) {
		ip = ip.replaceAll(" ", "");
		int index = ip.lastIndexOf(",");
		if (index > 0) {
		    ip = ip.substring(index + 1);
		}
	    }
	} catch (Exception e) {

	}

	if (ip.length() > 15) {
	    int length = ip.length();
	    ip = ip.substring(length - 15);
	}
	return ip;
    }
}
