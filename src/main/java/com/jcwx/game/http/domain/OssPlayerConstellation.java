package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * 星座类型等级统计
 * 
 * @author Administrator 2013-10-31
 */
public class OssPlayerConstellation implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 星座 等级 ***/
    private Integer level;
    /** 星座数量 ***/
    private Integer nub;
    /** 星座类型 ***/
    private Integer type;

    public Integer getLevel() {
	return level;
    }

    public Integer getNub() {
	return nub;
    }

    public Integer getType() {
	return type;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setNub(Integer nub) {
	this.nub = nub;
    }

    public void setType(Integer type) {
	this.type = type;
    }

}
