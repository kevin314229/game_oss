package com.jcwx.game.util.transdata;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.jcwx.game.admin.constant.FastjsonConstant;
import com.jcwx.game.util.ConnectionCheckUtil;

public class JsonTrans implements ITransfer {

    @Override
    public ContentTypeEnum contentType() {
	return ContentTypeEnum.JSON;
    }

    @Override
    public boolean isException(InputStream inputStream,
	    String httpHeaderErrClass) throws Exception {
	if (StringUtils.isEmpty(httpHeaderErrClass)) {
	    return false;
	}
	return true;
    }

    @Override
    public Exception readException(InputStream inputStream) throws Exception {
	return JSON.parseObject(IOUtils.toString(inputStream, READ_ENCODING),
		Exception.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> readObject(InputStream inputStream)
	    throws Exception {
	String readObject = IOUtils.toString(inputStream, READ_ENCODING);
	return JSON.parseObject(readObject,
		Map.class);
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

	    IOUtils.write(JSON.toJSONString(paraMap,
		    FastjsonConstant.getSerializeConfig(),
		    FastjsonConstant.getSerializerFeatures()), outputStream,
		    RESPONSE_ENCODING);

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }
}
