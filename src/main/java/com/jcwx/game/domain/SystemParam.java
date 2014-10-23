package com.jcwx.game.domain;

import java.io.Serializable;

/**
 * 系统参数
 * 
 * @author derek
 * 
 */
public class SystemParam implements Serializable {

    private static final long serialVersionUID = -7461548231796588479L;

    /** 系统参数说明 */
    private String systemParamDesc;
    /** 系统参数KEY */
    private String systemParamKey;
    /** 系统参数VALUE */
    private String systemParamValue;

    public String getSystemParamDesc() {
	return systemParamDesc;
    }

    public String getSystemParamKey() {
	return systemParamKey;
    }

    public String getSystemParamValue() {
	return systemParamValue;
    }

    public void setSystemParamDesc(String systemParamDesc) {
	this.systemParamDesc = systemParamDesc;
    }

    public void setSystemParamKey(String systemParamKey) {
	this.systemParamKey = systemParamKey;
    }

    public void setSystemParamValue(String systemParamValue) {
	this.systemParamValue = systemParamValue;
    }

}