package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PayUserLastLoginInfo implements Serializable {
    private Integer currency;
    private Integer gameId;
    private String job;
    private Date lastLoginTime;
    private Integer level;
    private String loginName;
    private String nickName;
    private Integer playerBaseId;
    private Integer playerId;
    private Integer ptArea;
    private String ptCode;
    private Double totalPay;

    public Integer getCurrency() {
	return currency;
    }

    public Integer getGameId() {
	return gameId;
    }

    public String getJob() {
	return job;
    }

    public Date getLastLoginTime() {
	return lastLoginTime;
    }

    public Integer getLevel() {
	return level;
    }

    public String getLoginName() {
	return loginName;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public Integer getPtArea() {
	return ptArea;
    }

    public String getPtCode() {
	return ptCode;
    }

    public Double getTotalPay() {
	return totalPay;
    }

    public void setCurrency(Integer currency) {
	this.currency = currency;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setJob(String job) {
	this.job = job;
    }

    public void setLastLoginTime(Date lastLoginTime) {
	this.lastLoginTime = lastLoginTime;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setPtArea(Integer ptArea) {
	this.ptArea = ptArea;
    }

    public void setPtCode(String ptCode) {
	this.ptCode = ptCode;
    }

    public void setTotalPay(Double totalPay) {
	this.totalPay = totalPay;
    }
}