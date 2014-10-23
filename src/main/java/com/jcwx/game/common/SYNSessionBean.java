package com.jcwx.game.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SYNSessionBean implements Serializable {

    /** 标识sessionID,与最后过期时间 */
    private Map<String, Long> sessionIdMap = new HashMap<String, Long>();

    /** 此次请求的时间截 */
    private Long times;

    public Map<String, Long> getSessionIdMap() {
	return sessionIdMap;
    }

    public Long getTimes() {
	return times;
    }

    public void setSessionIdMap(Map<String, Long> sessionIdMap) {
	this.sessionIdMap = sessionIdMap;
    }

    public void setTimes(Long times) {
	this.times = times;
    }

}
