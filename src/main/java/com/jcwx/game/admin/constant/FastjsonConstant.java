package com.jcwx.game.admin.constant;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastjsonConstant {
    private static final SerializeConfig config;

    private static final SerializerFeature[] features = {
	    SerializerFeature.WriteMapNullValue, // 输出空置字段

	    SerializerFeature.WriteNullListAsEmpty,
	    // list字段如果为null，输出为[]，而不是null

	    SerializerFeature.WriteNullNumberAsZero,
	    // 数值字段如果为null，输出为0，而不是null

	    SerializerFeature.WriteNullBooleanAsFalse,
	    // Boolean字段如果为null，输出为false，而不是null

	    SerializerFeature.WriteNullStringAsEmpty,
	    // 字符类型字段如果为null，输出为""，而不是null
	    SerializerFeature.WriteClassName,

	    SerializerFeature.WriteDateUseDateFormat

    };

    static {

	config = new SerializeConfig();

	// config.put(java.util.Date.class, new JSONLibDataFormatSerializer());
	// // 使用和json-lib兼容的日期输出格式

	// config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
	// // 使用和json-lib兼容的日期输出格式

    }

    public static SerializeConfig getSerializeConfig() {
	return config;
    }

    public static SerializerFeature[] getSerializerFeatures() {
	return features;
    }
}
