/*
 * DESUtils.java 
 * 创建于  2013-4-3
 * 
 * 版权所有@深圳市精彩无限数码科技有限公司
 */
package com.jcwx.game.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * @author Administrator
 * 
 */
public class DESUtils {

    private static final String ALGORITHM = "AES";
    private static final int CACHE_SIZE = 1024;

    /**
     * <p>
     * 解密
     * </p>
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, String key) throws Exception {
	Key k = getKey(key);
	Cipher cipher = Cipher.getInstance(ALGORITHM);
	cipher.init(Cipher.DECRYPT_MODE, k);
	return cipher.doFinal(data);
    }

    /**
     * <p>
     * 文件解密
     * </p>
     * 
     * @param key
     * @param sourceFilePath
     * @param destFilePath
     * @throws Exception
     */
    public static void decryptFile(String key, String sourceFilePath,
	    String destFilePath) throws Exception {
	File sourceFile = new File(sourceFilePath);
	File destFile = new File(destFilePath);
	if (sourceFile.exists() && sourceFile.isFile()) {
	    if (!destFile.getParentFile().exists()) {
		destFile.getParentFile().mkdirs();
	    }
	    destFile.createNewFile();
	    InputStream in = new FileInputStream(sourceFile);
	    OutputStream out = new FileOutputStream(destFile);
	    Key k = getKey(key);
	    Cipher cipher = Cipher.getInstance(ALGORITHM);
	    cipher.init(Cipher.DECRYPT_MODE, k);
	    CipherOutputStream cout = new CipherOutputStream(out, cipher);
	    byte[] cache = new byte[CACHE_SIZE];
	    int nRead = 0;
	    while ((nRead = in.read(cache)) != -1) {
		cout.write(cache, 0, nRead);
		cout.flush();
	    }
	    cout.close();
	    out.close();
	    in.close();
	}
    }

    /**
     * <p>
     * 加密
     * </p>
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String key) throws Exception {
	Key k = getKey(key);
	Cipher cipher = Cipher.getInstance(ALGORITHM);
	cipher.init(Cipher.ENCRYPT_MODE, k);
	return cipher.doFinal(data);
    }

    /**
     * <p>
     * 文件加密
     * </p>
     * 
     * @param key
     * @param sourceFilePath
     * @param destFilePath
     * @throws Exception
     */
    public static void encryptFile(String key, String sourceFilePath,
	    String destFilePath) throws Exception {
	File sourceFile = new File(sourceFilePath);
	File destFile = new File(destFilePath);
	if (sourceFile.exists() && sourceFile.isFile()) {
	    if (!destFile.getParentFile().exists()) {
		destFile.getParentFile().mkdirs();
	    }
	    destFile.createNewFile();
	    InputStream in = new FileInputStream(sourceFile);
	    OutputStream out = new FileOutputStream(destFile);
	    Key k = getKey(key);
	    Cipher cipher = Cipher.getInstance(ALGORITHM);
	    cipher.init(Cipher.ENCRYPT_MODE, k);
	    CipherInputStream cin = new CipherInputStream(in, cipher);
	    byte[] cache = new byte[CACHE_SIZE];
	    int nRead = 0;
	    while ((nRead = cin.read(cache)) != -1) {
		out.write(cache, 0, nRead);
		out.flush();
	    }
	    out.close();
	    cin.close();
	    in.close();
	}
    }

    public static byte[] fromHexString(String s) {
	int len = s.length();
	byte[] data = new byte[len / 2];
	for (int i = 0; i < len; i += 2) {
	    data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
		    .digit(s.charAt(i + 1), 16));
	}
	return data;
    }

    /**
     * <p>
     * 转换密钥
     * </p>
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static SecretKey getKey(String strKey) {
	try {
	    KeyGenerator _generator = KeyGenerator.getInstance("AES");
	    SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
	    secureRandom.setSeed(strKey.getBytes());
	    _generator.init(128, secureRandom);
	    return _generator.generateKey();
	} catch (Exception e) {
	    throw new RuntimeException(" 初始化密钥出现异常 ");
	}
    }

    /**
     * <p>
     * 生成随机密钥
     * </p>
     * 
     * @return
     * @throws Exception
     */
    public static String getSecretKey() throws Exception {
	return getSecretKey(null);
    }

    /**
     * <p>
     * 生成密钥
     * </p>
     * 
     * @param seed
     *            密钥种子
     * @return
     * @throws Exception
     */
    public static String getSecretKey(String seed) throws Exception {
	SecureRandom secureRandom;
	if (seed != null && !"".equals(seed))
	    secureRandom = new SecureRandom(seed.getBytes());
	else
	    secureRandom = new SecureRandom();
	KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
	keyGenerator.init(secureRandom);
	SecretKey secretKey = keyGenerator.generateKey();
	return Base64Utils.encode(secretKey.getEncoded());
    }

}
