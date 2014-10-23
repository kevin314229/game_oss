package com.jcwx.game.domain;

import java.io.Serializable;

public class OssUserRole implements Serializable {
    /** 角色ID */
    private Integer ossRoleID;
    /** 用户名 */
    private String username;

    public Integer getOssRoleID() {
	return ossRoleID;
    }

    public String getUsername() {
	return username;
    }

    public void setOssRoleID(Integer ossRoleID) {
	this.ossRoleID = ossRoleID;
    }

    public void setUsername(String username) {
	this.username = username;
    }

}
