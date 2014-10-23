/*
 * DESTester.java 
 * 创建于  2013-4-3
 * 
 * 版权所有@深圳市精彩无限数码科技有限公司
 */
package com.jcwx.game.util;

/**
 * @author Administrator
 * 
 */
public class DESTester {

    static String key;

    static {
	try {
	    key = DESUtils.getSecretKey("sadfasdfasdfasdfasdfasdfa");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    static void decryptFile() throws Exception {
	String sourceFilePath = "D:/demo_encrypted.mp4";
	String destFilePath = "D:/demo_decrypted.mp4";
	DESUtils.decryptFile(key, sourceFilePath, destFilePath);
    }

    static void encryptFile() throws Exception {
	String sourceFilePath = "D:/demo.mp4";
	String destFilePath = "D:/demo_encrypted.mp4";
	DESUtils.encryptFile(key, sourceFilePath, destFilePath);
    }

    public static void main(String[] args) throws Exception {
	long begin = System.currentTimeMillis();
	// encryptFile();
	// decryptFile();
	test();
	long end = System.currentTimeMillis();
	System.err.println("耗时：" + (end - begin) / 1000 + "秒");
    }

    static void test() throws Exception {
	String source = "这是一行测试DES加密/解密的文字，你看完也等于没看，是不是啊？！";
	System.err.println("原文:\t" + source);
	byte[] inputData = source.getBytes();
	inputData = DESUtils.encrypt(inputData, key);
	System.err.println("加密后:\t" + Base64Utils.encode(inputData));
	byte[] outputData = DESUtils.decrypt(inputData, key);
	String outputStr = new String(outputData);
	System.err.println("解密后:\t" + outputStr);
    }

}
