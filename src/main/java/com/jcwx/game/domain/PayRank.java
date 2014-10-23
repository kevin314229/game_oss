package com.jcwx.game.domain;

import java.io.Serializable;

/** 充值排行 */
public class PayRank implements Serializable {

    private static final long serialVersionUID = -2752639844808859726L;
    /** 充值总额 */
    private Double allCurrency;
    /** 服务器ID */
    private Integer areaId;
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

    public PayRank() {
    }

    public PayRank(Integer payId, Integer gameId, String ptId, Integer areaId,
	    String loginName, Double allCurrency) {
	this.payId = payId;
	this.gameId = gameId;
	this.ptId = ptId;
	this.areaId = areaId;
	this.loginName = loginName;
	this.allCurrency = allCurrency;
    }

    public Double getAllCurrency() {
	return allCurrency;
    }

    public Integer getAreaId() {
	return areaId;
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

    public void setAllCurrency(Double allCurrency) {
	this.allCurrency = allCurrency;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
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