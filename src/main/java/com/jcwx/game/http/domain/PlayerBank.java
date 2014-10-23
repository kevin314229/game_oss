/**
 * 
 */
package com.jcwx.game.http.domain;

import java.util.Date;

/**
 * @author Administrator 封禁帐号传输类
 */
public class PlayerBank {

    private String loginName;

    private Integer loginNumber;

    private Integer onlineStatus;

    private Date playerCreateTime;

    private Integer playerId;

    private Date playerLastLogoutTime;

    private Integer playerStatus;

    public String getLoginName() {
	return loginName;
    }

    public Integer getLoginNumber() {
	return loginNumber;
    }

    public Integer getOnlineStatus() {
	return onlineStatus;
    }

    public Date getPlayerCreateTime() {
	return playerCreateTime;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public Date getPlayerLastLogoutTime() {
	return playerLastLogoutTime;
    }

    public Integer getPlayerStatus() {
	return playerStatus;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setLoginNumber(Integer loginNumber) {
	this.loginNumber = loginNumber;
    }

    public void setOnlineStatus(Integer onlineStatus) {
	this.onlineStatus = onlineStatus;
    }

    public void setPlayerCreateTime(Date playerCreateTime) {
	this.playerCreateTime = playerCreateTime;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setPlayerLastLogoutTime(Date playerLastLogoutTime) {
	this.playerLastLogoutTime = playerLastLogoutTime;
    }

    public void setPlayerStatus(Integer playerStatus) {
	this.playerStatus = playerStatus;
    }

}
