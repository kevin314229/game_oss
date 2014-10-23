package com.jcwx.game.common.cache;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * OSCache 实现缓存接口
 * 
 * @author derek
 */
public class OSCache implements Cache {

    private static GeneralCacheAdministrator cache = new GeneralCacheAdministrator();

    public OSCache() {
    }

    @Override
    public Object get(String key) {
	try {
	    return cache.getFromCache(key);
	} catch (NeedsRefreshException e) {
	    cache.cancelUpdate(key);
	    return null;
	}
    }

    @Override
    public void put(String key, Object value) {
	cache.putInCache(key, value);
    }

    @Override
    public void remove(String key) {
	cache.removeEntry(key);
    }

    @Override
    public void update(String key, Object value) {
	cache.putInCache(key, value);
    }

}
