package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class PlayerLastLoginInfo implements Serializable {

    private static final long serialVersionUID = 1984983608161662557L;

    /** 注册时间 */
    private Date createTime;

    /** 最后登录IP */
    private String lastLoginIP;

    /** 最后登录时间 */
    private Date lastLoginTime;

    /** 角色等级 */
    private Integer level;

    /** 当前在线时间（分钟） */
    private Integer onlineMinutes;

    /** 玩家编号 */
    private String playerID;

    /** 玩家名称 */
    private String playerName;

    /** 玩家状态：在PlayerStateConstant中定义 */
    private Integer state;

    /** 用户帐号 */
    private String userName;

    public Date getCreateTime() {
	return createTime;
    }

    public String getLastLoginIP() {
	return lastLoginIP;
    }

    public Date getLastLoginTime() {
	return lastLoginTime;
    }

    public Integer getLevel() {
	return level;
    }

    public Integer getOnlineMinutes() {
	return onlineMinutes;
    }

    public String getPlayerID() {
	return playerID;
    }

    public String getPlayerName() {
	return playerName;
    }

    public Integer getState() {
	return state;
    }

    public String getUserName() {
	return userName;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setLastLoginIP(String lastLoginIP) {
	this.lastLoginIP = lastLoginIP;
    }

    public void setLastLoginTime(Date lastLoginTime) {
	this.lastLoginTime = lastLoginTime;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setOnlineMinutes(Integer onlineMinutes) {
	this.onlineMinutes = onlineMinutes;
    }

    public void setPlayerID(String playerID) {
	this.playerID = playerID;
    }

    public void setPlayerName(String playerName) {
	this.playerName = playerName;
    }

    public void setState(Integer state) {
	this.state = state;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

}
