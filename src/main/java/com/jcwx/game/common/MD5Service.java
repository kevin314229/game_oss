package com.jcwx.game.common;

import java.security.MessageDigest;

public class MD5Service {

    /**
     * MD5加密字符串
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public static String encryptString(String str) throws Exception {
	MessageDigest digest = MessageDigest.getInstance("MD5");
	byte[] md5 = digest.digest(str.getBytes("UTF-8"));
	StringBuffer md5StringBuffer = new StringBuffer();
	String part = null;
	for (int i = 0; i < md5.length; i++) {
	    part = Integer.toHexString(md5[i] & 0xFF);
	    if (part.length() == 1) {
		part = "0" + part;
	    }
	    md5StringBuffer.append(part);
	}
	return md5StringBuffer.toString().toUpperCase();
    }

    /**
     * MD5加密字符串(小写)
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public static String encryptStringLower(String str) throws Exception {
	MessageDigest digest = MessageDigest.getInstance("MD5");
	byte[] md5 = digest.digest(str.getBytes("UTF-8"));
	StringBuffer md5StringBuffer = new StringBuffer();
	String part = null;
	for (int i = 0; i < md5.length; i++) {
	    part = Integer.toHexString(md5[i] & 0xFF);
	    if (part.length() == 1) {
		part = "0" + part;
	    }
	    md5StringBuffer.append(part);
	}
	return md5StringBuffer.toString().toLowerCase();
    }

    public static Object getVerifyCode(String colunm) {
	try {
	    String md5 = MD5Service.encryptString(SystemConfig.verifyKey
		    + colunm);
	    return md5;
	} catch (Exception e) {
	}
	return "";
    }

    public static void main(String[] args) {
	try {
	    System.out.println();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
