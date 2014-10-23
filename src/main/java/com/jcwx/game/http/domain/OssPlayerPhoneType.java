package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * 用户手机统计
 * 
 * @author Administrator
 * 
 */
public class OssPlayerPhoneType implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    // 用户数
    private Integer nub;
    // 类型
    private String type;

    public OssPlayerPhoneType() {
    }

    public OssPlayerPhoneType(String type, Integer nub) {
	super();
	this.type = type;
	this.nub = nub;
    }

    public Integer getNub() {
	return nub;
    }

    public String getType() {
	return type;
    }

    public void setNub(Integer nub) {
	this.nub = nub;
    }

    public void setType(String type) {
	this.type = type;
    }

}
