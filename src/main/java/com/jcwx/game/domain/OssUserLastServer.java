package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class OssUserLastServer implements Serializable {

    private static final long serialVersionUID = 4412031118411014130L;
    /** 服务器Id */
    private Integer areaId;
    /** 创建时间 */
    private Date createTime;
    /** 游戏ID */
    private Integer gameId;
    /** 最后修改时间 */
    private Date lastLoginTime;
    /** 用户名 */
    private String userName;

    public Integer getAreaId() {
	return areaId;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Integer getGameId() {
	return gameId;
    }

    public Date getLastLoginTime() {
	return lastLoginTime;
    }

    public String getUserName() {
	return userName;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setLastLoginTime(Date lastLoginTime) {
	this.lastLoginTime = lastLoginTime;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    };

}