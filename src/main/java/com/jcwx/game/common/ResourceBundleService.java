package com.jcwx.game.common;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

//import com.jjl.rzjh.exception.GameException;

/**
 * 用于读取国际化资源文件
 * 
 * @author derek
 * 
 */
public class ResourceBundleService {
    /** 锁对象 */
    private static Object lock = new Object();
    private static Logger logger = Logger
	    .getLogger(ResourceBundleService.class);

    private static final String RESOURCE_PATH = "resource";
    private static ResourceBundle resourceBundle;

    // static {
    // //TODO 语言环境
    // String localeStr = "zh_CN";
    // try {
    // if (resourceBundle == null) {
    // //localeStr = ConfigurationService.getProperty("locale");
    //
    // resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH, new
    // Locale(localeStr.substring(0, 2), localeStr.substring(3, 5)));
    // }
    // }
    // catch (MissingResourceException e) {
    // logger.error("There is no " + localeStr +
    // " resource file found!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    // }
    // }

    /**
     * 根据key获取相应的字符串，使用参数把它格式化后，返回之
     * 
     * @param key
     *            关键字
     * @param arguments
     *            格式化时使用的参数
     * @return 返回结果
     */
    public synchronized static String getFormatString(String key,
	    Object... arguments) {
	try {
	    String value = getResourceBundle().getString(key);
	    return MessageFormat.format(value, arguments);
	} catch (Exception e) {
	    // MissingResourceException:找不到key对应的资源；IllegalArgumentException：传入的参数(arguments)与key对应的资源不匹配;
	    // NullPointerException:传入key为null
	    // throw new GameException(e);
	    return null;
	}
    }

    public static ResourceBundle getResourceBundle() {
	if (resourceBundle == null) {
	    synchronized (lock) {
		if (resourceBundle == null) {
		    String localeStr = "zh_CN";
		    try {
			if (SystemConfig.locale != null
				&& !"".equals(SystemConfig.locale)) {
			    localeStr = SystemConfig.locale;
			}
			resourceBundle = ResourceBundle.getBundle(
				RESOURCE_PATH,
				new Locale(localeStr.substring(0, 2), localeStr
					.substring(3, 5)));
		    } catch (MissingResourceException e) {
			logger.error("There is no "
				+ localeStr
				+ " resource file found!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		    }
		}
	    }
	}
	return resourceBundle;
    }

    /**
     * 根据key获取相应的字符串
     * 
     * @param key
     *            关键字
     * @return 返回结果
     */
    public static String getString(String key) {
	try {
	    return getResourceBundle().getString(key);
	} catch (Exception e) {
	    // MissingResourceException:找不到key对应的资源；NullPointerException:传入key为null
	    // throw new GameException(e);
	    return null;
	}
    }

}
