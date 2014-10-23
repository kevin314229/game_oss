package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * 系统参数，用于和OSS对接数据传输
 * 
 * @author derek
 */
public class OssSystemParam implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -5923950436235092323L;

    /** 系统参数描述 */
    private String systemDesc;

    /** 系统参数key */
    private String systemKey;

    /** 系统参数Value */
    private String systemValue;

    public String getSystemDesc() {
	return systemDesc;
    }

    public String getSystemKey() {
	return systemKey;
    }

    public String getSystemValue() {
	return systemValue;
    }

    public void setSystemDesc(String systemDesc) {
	this.systemDesc = systemDesc;
    }

    public void setSystemKey(String systemKey) {
	this.systemKey = systemKey;
    }

    public void setSystemValue(String systemValue) {
	this.systemValue = systemValue;
    }

}
