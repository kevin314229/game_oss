package com.jcwx.game.common;

import java.util.Random;

public class RandomStringService {

    public static final Random random = new Random();

    public static final String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 获得随机字符串
     * 
     * @param length
     *            字符串长度
     * @return
     */
    public static String getRandomString(int length) {

	StringBuffer stringBuffer = new StringBuffer();
	for (int i = 0; i < length; i++) {
	    stringBuffer.append(str.charAt(random.nextInt(str.length())));
	}

	return stringBuffer.toString();
    }

    public static void main(String[] args) {
	System.out.println(RandomStringService.getRandomString(20));
    }

}
