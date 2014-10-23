package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class PayStatistic implements Serializable {

    private static final long serialVersionUID = -931044483704543185L;
    /** 服务器ID */
    private Integer areaId;
    /** 游戏ID */
    private Integer gameId;
    /** 充值总额 */
    private double moneyNum;
    /** 充值日期 */
    private Date payDate;
    /** 充值ID */
    private Integer payId;
    /**  */
    private String ptId;
    /** 平台币数量 */
    private double ptMoneyNum;

    public PayStatistic() {
    }

    public PayStatistic(Integer payId, Date payDate, Integer gameId,
	    String ptId, Integer areaId, double ptMoneyNum, double moneyNum) {
	this.payId = payId;
	this.payDate = payDate;
	this.gameId = gameId;
	this.ptId = ptId;
	this.areaId = areaId;
	this.ptMoneyNum = ptMoneyNum;
	this.moneyNum = moneyNum;
    }

    public Integer getAreaId() {
	return areaId;
    }

    public Integer getGameId() {
	return gameId;
    }

    public double getMoneyNum() {
	return moneyNum;
    }

    public Date getPayDate() {
	return payDate;
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

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setMoneyNum(double moneyNum) {
	this.moneyNum = moneyNum;
    }

    public void setPayDate(Date payDate) {
	this.payDate = payDate;
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
