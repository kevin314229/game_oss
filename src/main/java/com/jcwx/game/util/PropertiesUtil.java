/*
 * LocalUtil.java 
 * 创建于  2013-4-10
 * 
 * 版权所有@深圳市精彩无限数码科技有限公司
 */
package com.jcwx.game.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * 
 */
public class PropertiesUtil {

    private static Properties localProperties = null;

    private static Logger logger = LoggerFactory
	    .getLogger(PropertiesUtil.class);

    static {
	synchronized (PropertiesUtil.class) {
	    if (localProperties == null) {
		try {
		    InputStream in = XMLUtil
			    .getXMLInputStream("http_url.properties");
		    localProperties = new Properties();
		    localProperties.load(in);
		} catch (IOException e) {
		    logger.error("init local fail!", e);
		}
	    }
	}
    }

    public static String getValue(String key) {
	String message = (String) localProperties.get(key);
	return message;
    }
}
