package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/*
 * 上层key,本层key,名称 描述 创建时间 销毁时间 是否可手动清除 
 * */
public class OssCache implements Serializable {

    private static final long serialVersionUID = 1950022158206877051L;

    private Date createTime;

    private String describe;

    private Date disposeTime;

    /*
     * 1 是 0否
     */
    private Integer isClear;

    private String key;

    private String name;
    private String topKey;

    public Date getCreateTime() {
	return createTime;
    }

    public String getDescribe() {
	return describe;
    }

    public Date getDisposeTime() {
	return disposeTime;
    }

    public Integer getIsClear() {
	return isClear;
    }

    public String getKey() {
	return key;
    }

    public String getName() {
	return name;
    }

    public String getTopKey() {
	return topKey;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDescribe(String describe) {
	this.describe = describe;
    }

    public void setDisposeTime(Date disposeTime) {
	this.disposeTime = disposeTime;
    }

    public void setIsClear(Integer isClear) {
	this.isClear = isClear;
    }

    public void setKey(String key) {
	this.key = key;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setTopKey(String topKey) {
	this.topKey = topKey;
    }

}
