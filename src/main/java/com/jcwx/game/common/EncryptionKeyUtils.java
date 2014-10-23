package com.jcwx.game.common;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 加密key工具 */
public class EncryptionKeyUtils {

    /**
     * 解密算法one
     * 
     * @param checkKey
     * @return
     * @throws Exception
     */
    public static HashMap decryptionByOne(String checkKey, String key)
	    throws Exception {
	HashMap map = null;
	if (checkKey != null && !"".equals(checkKey)) {
	    checkKey = checkKey.trim();
	    // 解密
	    byte[] _checkKey = Base64Util.decode(checkKey);
	    String _decode = new String(_checkKey);
	    String[] checkKeys = _decode.split("&");
	    String str1 = checkKeys[0];
	    String verifyKey = checkKeys[1];
	    String verifyTime = checkKeys[2];

	    StringBuffer stringBuffer = new StringBuffer();
	    stringBuffer.append(str1);
	    stringBuffer.append(key);
	    stringBuffer.append(verifyTime);
	    String verify2 = MD5Service.encryptString(stringBuffer.toString());
	    map = new HashMap();
	    map.put("str1", str1);
	    map.put("verifyKey", verifyKey);
	    map.put("verifyTime", Long.valueOf(verifyTime).longValue());
	    map.put("verify2", verify2);
	}

	return map;
    }

    /**
     * 加密算法one
     * 
     * @param str1
     *            第一个字段
     * @param key
     *            KEY
     * @param timer
     *            过期时间
     * @return 加密字符串
     * @throws Exception
     */
    public static String encryptionByOne(String str1, String key, long timer)
	    throws Exception {
	String checkKeyBefore = "";
	String checkKey = ""; // Base64
	checkKeyBefore = str1 + "&"
		+ MD5Service.encryptString(str1 + key + timer) + "&" + timer;
	checkKey = Base64Util.encode(checkKeyBefore.getBytes());
	// 去掉换行，空格符
	Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	Matcher m = p.matcher(checkKey);
	checkKey = m.replaceAll("");
	return checkKey;
    }

}
