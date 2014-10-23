package com.jcwx.game.domain;

import java.io.Serializable;

public class Hint implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 主键ID */
    private Integer hintId;
    /** 功能节点ID */
    private String menuId;
    /** 提示Key */
    private String hintKey;
    /** 提示Value */
    private String hintValue;
    
    /**用于初始化数据用*/
    private String projectId;

    public Hint() {
    }

    public Hint(Integer hintId, String menuId, String hintKey, String hintValue) {
	this.hintId = hintId;
	this.menuId = menuId;
	this.hintKey = hintKey;
	this.hintValue = hintValue;
    }

    public Integer getHintId() {
	return hintId;
    }

    public void setHintId(Integer hintId) {
	this.hintId = hintId;
    }

    public String getMenuId() {
	return menuId;
    }

    public void setMenuId(String menuId) {
	this.menuId = menuId;
    }

    public String getHintKey() {
	return hintKey;
    }

    public void setHintKey(String hintKey) {
	this.hintKey = hintKey;
    }

    public String getHintValue() {
	return hintValue;
    }

    public void setHintValue(String hintValue) {
	this.hintValue = hintValue;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
