package com.jcwx.game.common.security;

import java.security.MessageDigest;

public class SignUtil {
    private static String checkKey = "";
    private static long timeout = 0L;

    public static int checkSign(String sign, long ts, Object[] params) {
	if ((timeout != 0L) && (System.currentTimeMillis() - ts > timeout)) {
	    return -2;
	}

	StringBuffer sb = new StringBuffer();
	for (Object p : params) {
	    sb.append(p.toString());
	    sb.append("&");
	}
	sb.append(checkKey);
	sb.append("&");
	sb.append(ts);

	String md5 = MD5(sb.toString().getBytes());

	if (!md5.equals(sign))
	    return -1;
	return 0;
    }

    public static String MD5(byte[] source) {
	String s = null;
	char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		'A', 'B', 'C', 'D', 'E', 'F' };
	try {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(source);
	    byte[] tmp = md.digest();

	    char[] str = new char[32];

	    int k = 0;
	    for (int i = 0; i < 16; i++) {
		byte byte0 = tmp[i];
		str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];

		str[(k++)] = hexDigits[(byte0 & 0xF)];
	    }
	    s = new String(str);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return s;
    }

    public static void setCheckKey(String key) {
	checkKey = key;
    }

    public static void setTimeout(long t) {
	timeout = t;
    }

    public static String sign(long ts, Object[] params) {
	StringBuffer sb = new StringBuffer();
	for (Object p : params) {
	    sb.append(p.toString());
	    sb.append("&");
	}
	sb.append(checkKey);
	sb.append("&");
	sb.append(ts);

	return MD5(sb.toString().getBytes());
    }
}
