package com.jcwx.game.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcwx.game.common.MD5Service;

public class ConnectionCheckUtil {

    private static final Logger logger = LoggerFactory
	    .getLogger(ConnectionCheckUtil.class);

    public static String makeSource(Map<String, Object> params, String key)
	    throws Exception {
	// Object[] keys = params.keySet().toArray();
	// Arrays.sort(keys);
	// StringBuilder buffer = new StringBuilder();
	// for (int i = 0; i < keys.length; i++) {
	// //TODO 当前不处理
	// // buffer.append(keys[i]).append("=").append(params.get(keys[i]));
	// // if (i != keys.length - 1) {
	// // buffer.append("&");
	// // }
	// }

	long time = (Long) params.get("sinTime");
	String handlerName = (String) params.get("handlerName");
	String methodName = (String) params.get("methodName");

	StringBuilder buf = new StringBuilder();
	buf.append(time + "&");
	if (handlerName != null)
	    buf.append(handlerName + "&");
	if (methodName != null)
	    buf.append(methodName + "&");
	buf.append(key);

	String md5 = MD5Service.encryptStringLower(buf.toString());
	return md5;
    }

}
