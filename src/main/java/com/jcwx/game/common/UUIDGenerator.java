package com.jcwx.game.common;

import java.util.UUID;

public class UUIDGenerator {

    /**
     * 生成全球唯一ID（32位字符串）
     * 
     * @return
     */
    public static String generatorUUID() {
	return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 生成全球唯一ID（带前缀的）
     * 
     * @param before
     *            字符串前缀
     * @return
     */
    public static String generatorUUID(String before) {
	return before + generatorUUID();
    }

    public static void main(String[] args) {
	long a = System.currentTimeMillis();
	for (int i = 0; i < 1000; i++)
	    System.out.println(generatorUUID("20110926"));
	System.out.println((System.currentTimeMillis() - a));
    }

}
