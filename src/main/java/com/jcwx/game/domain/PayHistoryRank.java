package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class PayHistoryRank implements Serializable {

    private static final long serialVersionUID = 7878853633235027306L;

    /** 充值rmb */
    private Integer amountSum;

    /** 第一次充值时间 */
    private Date firstPayTime;

    /** 最后充值时间 */
    private Date lastPayTime;

    /** 最后充值时间到目前的天数 */
    private Integer lastPayToNowDays;

    /** 玩家名 */
    private String loginName;

    /** 没登录游戏天数 */
    private Integer noLoginDays;

    /** 充值次数 */
    private Integer payNum;

    /** 付费周期 */
    private Integer payPeriods;

    private Integer playerId;

    /** 排名 */
    private Integer rank;

    public Integer getAmountSum() {
	return amountSum;
    }

    public Date getFirstPayTime() {
	return firstPayTime;
    }

    public Date getLastPayTime() {
	return lastPayTime;
    }

    public Integer getLastPayToNowDays() {
	return lastPayToNowDays;
    }

    public String getLoginName() {
	return loginName;
    }

    public Integer getNoLoginDays() {
	return noLoginDays;
    }

    public Integer getPayNum() {
	return payNum;
    }

    public Integer getPayPeriods() {
	return payPeriods;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public Integer getRank() {
	return rank;
    }

    public void setAmountSum(Integer amountSum) {
	this.amountSum = amountSum;
    }

    public void setFirstPayTime(Date firstPayTime) {
	this.firstPayTime = firstPayTime;
    }

    public void setLastPayTime(Date lastPayTime) {
	this.lastPayTime = lastPayTime;
    }

    public void setLastPayToNowDays(Integer lastPayToNowDays) {
	this.lastPayToNowDays = lastPayToNowDays;
    }

    public void setLogimName(String loginName) {
	this.loginName = loginName;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNoLoginDays(Integer noLoginDays) {
	this.noLoginDays = noLoginDays;
    }

    public void setPayNum(Integer payNum) {
	this.payNum = payNum;
    }

    public void setPayPeriods(Integer payPeriods) {
	this.payPeriods = payPeriods;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setRank(Integer rank) {
	this.rank = rank;
    }

}
