package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录详细信息
 * 
 * @date 2011-4-14
 * @version V1.0
 */
public class LoginLogInfo implements Serializable {

    private static final long serialVersionUID = -4377901416022853361L;

    /** 历史登录IP数 */
    private Integer historyIPAmount;

    /** 最后登录时间 */
    private Date lastLoginDate;

    /** 最后登录IP */
    private String lastLoginIP;

    /** 登录次数 */
    private Integer loginAmount;

    /** 用户帐号 */
    private String loginName;

    /** 玩家名称 */
    private String nickName;

    /** 玩家编号 */
    private Integer playerID;

    public Integer getHistoryIPAmount() {
	return historyIPAmount;
    }

    public Date getLastLoginDate() {
	return lastLoginDate;
    }

    public String getLastLoginIP() {
	return lastLoginIP;
    }

    public Integer getLoginAmount() {
	return loginAmount;
    }

    public String getLoginName() {
	return loginName;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getPlayerID() {
	return playerID;
    }

    public void setHistoryIPAmount(Integer historyIPAmount) {
	this.historyIPAmount = historyIPAmount;
    }

    public void setLastLoginDate(Date lastLoginDate) {
	this.lastLoginDate = lastLoginDate;
    }

    public void setLastLoginIP(String lastLoginIP) {
	this.lastLoginIP = lastLoginIP;
    }

    public void setLoginAmount(Integer loginAmount) {
	this.loginAmount = loginAmount;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setPlayerID(Integer playerID) {
	this.playerID = playerID;
    }
}
