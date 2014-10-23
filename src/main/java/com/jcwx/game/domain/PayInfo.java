package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

/** 充值信息 */
public class PayInfo implements Serializable {

    private static final long serialVersionUID = -2752639844808859726L;
    /** 服务器ID */
    private Integer areaId;
    /** 充值时间 */
    private Date completeTime;
    // private
    /** 充值金额 */
    private int currency;
    /** 游戏ID */
    private Integer gameId;
    /** 账号ID */
    private String loginName;
    private double MoneyNum;
    /** 充值排行ID */
    private Integer payId;
    /**  */
    private String ptId;
    private double ptMoneyNum;

    public PayInfo() {
    }

    public PayInfo(Integer payId, Integer gameId, String ptId, Integer areaId,
	    String loginName, int currency, Date completeTime) {
	this.payId = payId;
	this.gameId = gameId;
	this.ptId = ptId;
	this.areaId = areaId;
	this.loginName = loginName;
	this.currency = currency;
	this.completeTime = completeTime;
    }

    public Integer getAreaId() {
	return areaId;
    }

    public Date getCompleteTime() {
	return completeTime;
    }

    public int getCurrency() {
	return currency;
    }

    public Integer getGameId() {
	return gameId;
    }

    public String getLoginName() {
	return loginName;
    }

    public double getMoneyNum() {
	return MoneyNum;
    }

    public Integer getPayId() {
	return payId;
    }

    public String getPtId() {
	return ptId;
    }

    public double getPtMoneyNum() {
	return ptMoneyNum;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setCompleteTime(Date completeTime) {
	this.completeTime = completeTime;
    }

    public void setCurrency(int currency) {
	this.currency = currency;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setMoneyNum(double moneyNum) {
	MoneyNum = moneyNum;
    }

    public void setPayId(Integer payId) {
	this.payId = payId;
    }

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

    public void setPtMoneyNum(double ptMoneyNum) {
	this.ptMoneyNum = ptMoneyNum;
    }
}