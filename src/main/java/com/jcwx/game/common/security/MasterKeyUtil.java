package com.jcwx.game.common.security;

import org.jasypt.util.text.BasicTextEncryptor;

public class MasterKeyUtil {

    public static String decKey(String src) {
	BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	textEncryptor.setPassword(getMasterKey());
	return textEncryptor.decrypt(src);
    }

    public static String decStr(String src) {
	int index = src.indexOf("ENC(");
	if (index == 0) {
	    src = src.substring(4, src.length() - 1);
	    return decKey(src);
	} else {
	    return src;
	}
    }

    public static String encKey(String src) {
	BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	textEncryptor.setPassword(getMasterKey());
	return textEncryptor.encrypt(src);
    }

    public static String getMasterKey() {
	return "JKJJL&W%3^E%9@SRS$JDS5KO2";
    }

}
