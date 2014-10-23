/*
 * AES.java 
 * 创建于  2013-4-10
 * 
 * 版权所有@深圳市精彩无限数码科技有限公司
 */
package com.jcwx.game.util;

/**
 * @author Administrator
 *
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcwx.game.domain.OssServer;

public class AES {

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";

    private static final String keyword = "25mnDSghfMV6e1NOmK2PVg==";

    private static final Logger logger = LoggerFactory.getLogger(AES.class);

    /**
     * 实际的加密解密过程
     * 
     * @param in
     * @param out
     * @param mode
     * @throws IOException
     * @throws ShortBufferException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    public static void crypt(InputStream in, OutputStream out, int mode, Key key)
	    throws IOException, ShortBufferException,
	    IllegalBlockSizeException, BadPaddingException,
	    NoSuchAlgorithmException, NoSuchPaddingException,
	    InvalidKeyException {
	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	cipher.init(mode, key);
	int blockSize = cipher.getBlockSize();
	int outputSize = cipher.getOutputSize(blockSize);
	byte[] inBytes = new byte[blockSize];
	byte[] outBytes = new byte[outputSize];

	int inLength = 0;
	boolean more = true;
	while (more) {
	    inLength = in.read(inBytes);
	    if (inLength == blockSize) {
		int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
		out.write(outBytes, 0, outLength);
	    } else {
		more = false;
	    }
	}
	if (inLength > 0)
	    outBytes = cipher.doFinal(inBytes);
	else
	    outBytes = cipher.doFinal();
	out.write(outBytes);
	out.flush();
    }

    /**
     * 解密
     * 
     * @param in
     * @param out
     * @throws InvalidKeyException
     * @throws ShortBufferException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IOException
     */
    public static void decrypt(InputStream in, OutputStream out, Key key)
	    throws InvalidKeyException, ShortBufferException,
	    IllegalBlockSizeException, BadPaddingException,
	    NoSuchAlgorithmException, NoSuchPaddingException, IOException {
	crypt(in, out, Cipher.DECRYPT_MODE, key);
    }

    /**
     * 加密
     * 
     * @param in
     * @param out
     * @throws InvalidKeyException
     * @throws ShortBufferException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IOException
     */
    public static void encrypt(InputStream in, OutputStream out, Key key)
	    throws InvalidKeyException, ShortBufferException,
	    IllegalBlockSizeException, BadPaddingException,
	    NoSuchAlgorithmException, NoSuchPaddingException, IOException {
	crypt(in, out, Cipher.ENCRYPT_MODE, key);
    }

    /**
     * 生成AES对称秘钥
     * 
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static void generateKey(OssServer ossServer) {
	try {
	    synchronized (ossServer) {
		if (ossServer.getKey() == null) {
		    KeyGenerator generator = KeyGenerator
			    .getInstance(KEY_ALGORITHM);
		    SecureRandom secureRandom = SecureRandom
			    .getInstance("SHA1PRNG");
		    byte[] keyBytes = Base64Utils.decode(ossServer
			    .getCommunicateKey());
		    SecretKeySpec keySpec = new SecretKeySpec(keyBytes, 0, 16,
			    "AES");
		    secureRandom.setSeed(keySpec.getEncoded());
		    generator.init(128, secureRandom);
		    ossServer.setKey(generator.generateKey());
		}
	    }
	} catch (Exception e) {
	    logger.error(" 初始化密钥出现异常 ", e);
	}
    }

    public static byte[] hex2byte(String strhex) {
	if (strhex == null) {
	    return null;
	}
	int l = strhex.length();
	if (l % 2 == 1) {
	    return null;
	}
	byte[] b = new byte[l / 2];
	for (int i = 0; i != l / 2; i++) {
	    b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
		    16);
	}
	return b;
    }

    public static void main(String[] args) {
	System.out.println(System.getProperty("file.encoding"));
    }

}