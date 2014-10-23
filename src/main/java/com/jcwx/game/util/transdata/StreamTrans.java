package com.jcwx.game.util.transdata;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.shaded.org.objenesis.strategy.StdInstantiatorStrategy;
import com.jcwx.game.util.ConnectionCheckUtil;

public class StreamTrans implements ITransfer {

    private static ThreadLocal<Kryo> converters = new ThreadLocal<Kryo>();

    private static Kryo getConverter() {
	Kryo kryo = converters.get();
	if (kryo == null) {
	    kryo = new Kryo();
	    kryo.setReferences(false);
	    kryo.setRegistrationRequired(false);
	    kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
	    converters.set(kryo);
	}
	return kryo;
    }

    private Object serverObject;

    @Override
    public ContentTypeEnum contentType() {
	return ContentTypeEnum.STREAM;
    }

    @Override
    public boolean isException(InputStream inputStream,
	    String httpHeaderErrClass) throws Exception {
	Input input = null;
	// 要上缓冲，不然会益处
	try {
	    input = new Input(inputStream, 2048);

	    serverObject = Validate.notNull(
		    getConverter().readClassAndObject(input),
		    "server return Object must not be null!");

	    if (serverObject instanceof Exception) {
		throw (Exception) serverObject;
	    }

	} finally {
	    IOUtils.closeQuietly(input);
	}
	return false;
    }

    @Override
    public Exception readException(InputStream inputStream) throws Exception {
	throw new Exception(ReflectionToStringBuilder.toString(serverObject));
    }

    @Override
    public Map<String, Object> readObject(InputStream inputStream)
	    throws Exception {

	return (Map<String, Object>) serverObject;

    }

    @Override
    public void writeObject(OutputStream outputStream,
	    Map<String, Object> paraMap, String key) throws Exception {

	try {
	    // 请求加上时间截加密参数
	    Long time = System.currentTimeMillis();
	    // 新的加密方式
	    // 无需传sig,会自动生成
	    paraMap.remove("sig");
	    // 每次请求加上时间截
	    paraMap.put("sinTime", time);
	    // 计算签名
	    String sig = ConnectionCheckUtil.makeSource(paraMap, key);
	    paraMap.put("sig", sig);

	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	    Output output = new Output(byteArrayOutputStream);

	    getConverter().writeObject(output, paraMap);

	    output.close();

	    /** 2014-01-20改变写出方式 */
	    IOUtils.write(byteArrayOutputStream.toByteArray(), outputStream);

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    IOUtils.closeQuietly(outputStream);
	}

    }

}
