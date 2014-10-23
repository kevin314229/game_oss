package com.jcwx.game.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Key;

/**
 * 加密解密工具类
 * 
 * @author Administrator
 * 
 */
public class CryptUtil {

    private static final String sKey = "sadfasdfasdfasdfasdfasdfa";

    /**
     * 解密
     * 
     * @param b
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(InputStream inputStream, Key key)
	    throws Exception {
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	byte[] c = null;
	try {
	    AES.decrypt(inputStream, outputStream, key);
	    c = outputStream.toByteArray();
	} finally {
	    outputStream.close();
	}
	return c;

    }

    /**
     * 加密
     * 
     * @param b
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(InputStream inputStream, Key key)
	    throws Exception {
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	AES.encrypt(inputStream, outputStream, key);
	return outputStream.toByteArray();
    }

    public static void main(String[] args) throws Exception {
	// Kryo kryo = new Kryo();
	// kryo.setReferences(false);
	// kryo.setRegistrationRequired(false);
	// kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
	// Map<String,Object> haMap1 = new HashMap<String, Object>();
	// for(int i = 0; i < 100000; i++){
	// haMap1.put("aaa"+i,"bbb"+i);
	// }
	// ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	// Output output = new Output(outputStream);
	// kryo.writeObject(output,haMap1);
	// output.close();
	// byte[] aa = outputStream.toByteArray();
	// System.out.println("加密前："+aa.length);
	// byte[] bb = encrypt(new ByteArrayInputStream(aa));
	// System.out.println("加密后："+bb.length);
	// byte[] cc = decrypt(new ByteArrayInputStream(bb));
	// System.out.println("解密后："+cc.length);
	// Input input = new Input(cc);
	// HashMap<String,Object> hashMap = (HashMap<String, Object>)
	// kryo.readObject(input,HashMap.class);
	// System.out.println(hashMap.size());
    }

}
