package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

/** 登录统计 */
public class LoginReport implements Serializable {

    private static final long serialVersionUID = 5578162044778884509L;

    /** 账号数 */
    private Integer loginNum;

    /** 统计时间 */
    private Date loginTime;

    /** 角色数 */
    private Integer playerNum;

    /** 大区Id */
    private Integer serverId;

    public Integer getLoginNum() {
	return loginNum;
    }

    public Date getLoginTime() {
	return loginTime;
    }

    public Integer getPlayerNum() {
	return playerNum;
    }

    public Integer getServerId() {
	return serverId;
    }

    public void setLoginNum(Integer loginNum) {
	this.loginNum = loginNum;
    }

    public void setLoginTime(Date loginTime) {
	this.loginTime = loginTime;
    }

    public void setPlayerNum(Integer playerNum) {
	this.playerNum = playerNum;
    }

    public void setServerId(Integer serverId) {
	this.serverId = serverId;
    }
}
