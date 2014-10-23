package com.jcwx.game.service;

import java.util.Map;

public interface ISyncMoudle {

    /** 刷新服务器功能调整 */
    public abstract String modifyActivity(Map<String, Object> object)
	    throws Exception;

    /***
     * 同步到其他服务器代码
     * 
     * @param querySynList
     *            查询同步类名，方法名Map
     * @param synFunctionList
     *            同步功能类名方法名Map
     * @param serverArray
     *            服务器列表
     * 
     * */
    public abstract String syncMoudle(Map<String, Object> querySynList,
	    Map<String, Object> synFunctionList, String[] serverIds);

}