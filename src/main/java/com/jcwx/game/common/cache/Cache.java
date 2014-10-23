package com.jcwx.game.common.cache;

/**
 * 缓存接口
 * 
 * @author derek
 */
public interface Cache {

    /**
     * 获得缓存对象
     * 
     * @param key
     */
    public Object get(String key);

    /**
     * put 到缓存
     * 
     * @param key
     * @param value
     */
    public void put(String key, Object value);

    /**
     * 删除缓存
     * 
     * @param key
     */
    public void remove(String key);

    /**
     * 修改缓存
     * 
     * @param key
     * @param value
     */
    public void update(String key, Object value);

}
