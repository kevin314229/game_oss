package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class ZPayDay implements Serializable {

    /** 充值魔晶数 */
    private Long goldTotal;
    /** 日期 */
    private Date id;
    /** 登录数 */
    private Integer loginTotal;
    /** 峰值在线人数 */
    private Integer maxOnline;
    /** 充值货币总数 */
    private double moneyTotal;
    /** 充值次数 */
    private Integer payTimes;
    /** 充值账号数 */
    private Integer payUserNum;
    /** 是否是临时数据,true1临时数据，false0正式数据 */
    private Boolean tmpFlag;

    public ZPayDay() {
    }

    public Long getGoldTotal() {
	return goldTotal;
    }

    public Date getId() {
	return id;
    }

    public Integer getLoginTotal() {
	return loginTotal;
    }

    public Integer getMaxOnline() {
	return maxOnline;
    }

    public double getMoneyTotal() {
	return moneyTotal;
    }

    public Integer getPayTimes() {
	return payTimes;
    }

    public Integer getPayUserNum() {
	return payUserNum;
    }

    public Boolean getTmpFlag() {
	return tmpFlag;
    }

    public void setGoldTotal(Long goldTotal) {
	this.goldTotal = goldTotal;
    }

    public void setId(Date id) {
	this.id = id;
    }

    public void setLoginTotal(Integer loginTotal) {
	this.loginTotal = loginTotal;
    }

    public void setMaxOnline(Integer maxOnline) {
	this.maxOnline = maxOnline;
    }

    public void setMoneyTotal(double moneyTotal) {
	this.moneyTotal = moneyTotal;
    }

    public void setPayTimes(Integer payTimes) {
	this.payTimes = payTimes;
    }

    public void setPayUserNum(Integer payUserNum) {
	this.payUserNum = payUserNum;
    }

    public void setTmpFlag(Boolean tmpFlag) {
	this.tmpFlag = tmpFlag;
    }

}