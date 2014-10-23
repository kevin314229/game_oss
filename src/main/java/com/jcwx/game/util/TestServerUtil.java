/*
 * TestServerUtil.java 
 * 创建于  2013-4-7
 * 
 * 版权所有@深圳市精彩无限数码科技有限公司
 */
package com.jcwx.game.util;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.FileUtils;

/**
 * @author Administrator
 * 
 */
public class TestServerUtil {

    public static void genaralKey() {
	try {
	    File keyFile = new File("c:/key", "mykey.key");
	    KeyGenerator kgen = KeyGenerator.getInstance("AES");
	    kgen.init(128);
	    SecretKey skey = kgen.generateKey();
	    byte[] enc = skey.getEncoded();
	    FileUtils.writeStringToFile(keyFile, Base64Utils.encode(enc),
		    "UTF-8");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	for (int i = 0; i < 5; i++) {
	    try {
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("handlerName", "SendPropertyToPlayerHandler");
		// object =
		// getConnection().sendMsg("http://192.168.1.124:8081","sadfasdfsadf",object);
		System.out.println(object.size());
	    } catch (Exception e) {
		e.printStackTrace();
	    } finally {
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}
	// readKey();
	// System.out.println(31&(1<<4));
    }

    public static void readKey() {
	URL url = TestServerUtil.class.getResource("/");
	File file = new File(url.getPath() + "/config/key", "mykey.key");
	SecretKeySpec keySpec = null;
	try {
	    byte[] keyBytes = Base64Utils.decode(FileUtils.readFileToString(
		    file, "UTF-8"));
	    keySpec = new SecretKeySpec(keyBytes, 0, 16, "AES");
	    byte[] raw = keySpec.getEncoded();
	    System.out.println(raw);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
