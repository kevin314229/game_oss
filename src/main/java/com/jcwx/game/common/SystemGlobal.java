package com.jcwx.game.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.jcwx.game.common.cache.Cache;
import com.jcwx.game.common.cache.MemCache;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.SystemNotice;
import com.jcwx.game.init.OssServerUtil;
import com.jcwx.game.init.SystemNoticesUtil;

public class SystemGlobal {
    private static Cache cache;

    private static Logger logger = Logger.getLogger(SystemGlobal.class);

    public static final String OSSSERVER_MAP = "ossServerMap";
    /** 系统消息记录集合 */
    public static final String SYSTEM_NOTICES_MAP = "systemNoticesMap";

    /**
     * 获得缓存中的对象
     * 
     * @param key
     * @return
     */
    public static Object get(String key) {
	return cache.get(key);
    }

    /**
     * 获得缓存中的对象
     * 
     * @param key
     * @return
     */
    public static <T> T get(String key, Class<T> c) {
	Object o = cache.get(key);
	if (o == null)
	    return null;
	return c.cast(o);
    }

    /**
     * 获得缓存中Map对象的对象
     * 
     * @param key
     * @param key2
     * @param c
     * @return
     */
    public static <T> T get(String key, Integer key2, Class<T> c) {
	Map map = get(key, Map.class);
	if (map == null)
	    return null;
	try {
	    return c.cast(map.get(key2));
	} catch (Exception e) {
	    return null;
	}
    }

    /**
     * 获得缓存中Map对象的对象
     * 
     * @param key
     * @param key2
     * @param c
     * @return
     */
    public static <T> T get(String key, String key2, Class<T> c) {
	Map map = get(key, Map.class);
	if (map == null)
	    return null;
	try {
	    return c.cast(map.get(key2));
	} catch (Exception e) {
	    return null;
	}
    }

    /**
     * 初始化系统缓存
     */
    public static void init() {

	final PerformanceTimer timer = new PerformanceTimer();

	cache = new MemCache();

	// OssServer数据初始化缓存
	final Map<Integer, OssServer> osMap = new ConcurrentHashMap<Integer, OssServer>();
	OssServerUtil.initOssServersMap(osMap);
	cache.put(OSSSERVER_MAP, osMap);

	// 系统消息缓存
	final Map<Integer, SystemNotice> systemNoticesMap = new ConcurrentHashMap<Integer, SystemNotice>();
	SystemNoticesUtil.initSystemNoticesMap(systemNoticesMap);
	cache.put(SYSTEM_NOTICES_MAP, systemNoticesMap);

	// 将缓存中的系统消息数据加载到延时队列中
	SystemNoticesUtil.initSchedule();

	logger.info("初始化全局缓存对象:" + timer.getStr());
    }
}
