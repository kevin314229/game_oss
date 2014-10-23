package com.jcwx.game.common.connection.message;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class StandMessage implements Message {
    Map<String, Object> map = Maps.newHashMap();

    @Override
    public Map<String, Object> getContent() {
	return map;
    }

    @Override
    public String getHandlerName() {
	return StringUtils.join(map.get("handlerName"));
    }

    @Override
    public void put(String key, Object value) {
	map.put(key, value);
    }

    @Override
    public void setHandlerName(String handlerName) {
	map.put("handlerName", handlerName);
    }

}
