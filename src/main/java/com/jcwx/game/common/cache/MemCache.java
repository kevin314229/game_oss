package com.jcwx.game.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemCache implements Cache {

    private static Map<String, Object> cache = new ConcurrentHashMap<String, Object>();

    public MemCache() {
    }

    @Override
    public Object get(String key) {
	return cache.get(key);
    }

    @Override
    public void put(String key, Object value) {
	cache.put(key, value);
    }

    @Override
    public void remove(String key) {
	cache.remove(key);
    }

    @Override
    public void update(String key, Object value) {
	cache.put(key, value);
    }
}
