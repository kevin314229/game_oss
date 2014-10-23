package com.jcwx.game.domain;

import java.io.Serializable;

public class OssRoleMenu implements Serializable {

    /** 此Id用于修改 */
    private String menuId;
    /** 功能ID */
    private String ossMenuID;

    /** 角色ID */
    private Integer ossRoleID;


    public String getOssMenuID() {
	return ossMenuID;
    }

    public Integer getOssRoleID() {
	return ossRoleID;
    }

    public void setOssMenuID(String ossMenuID) {
	this.ossMenuID = ossMenuID;
    }

    public void setOssRoleID(Integer ossRoleID) {
	this.ossRoleID = ossRoleID;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    
}
