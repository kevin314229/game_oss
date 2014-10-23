package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

public class ZPayDayDto implements Serializable {

    /** 活跃付费率 充值账号数/总登陆人数 */
    private Double activePayRate;
    /** 充值总金额/总付费账号人数 */
    private Double arppu;
    /** 单位时间内，充值总金额/总登陆人数 */
    private Double arpu;
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

    /** 所在服务器名字 */
    private String serverName;

    /** 单次付费值 付费金额/付费次数 */
    private Double singlePay;

    /** 是否是临时数据,true1临时数据，false0正式数据 */
    private Boolean tmpFlag;

    public ZPayDayDto() {
    }

    public Double getActivePayRate() {
	return activePayRate;
    }

    public Double getArppu() {
	return arppu;
    }

    public Double getArpu() {
	return arpu;
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

    public String getServerName() {
	return serverName;
    }

    public Double getSinglePay() {
	return singlePay;
    }

    public Boolean getTmpFlag() {
	return tmpFlag;
    }

    public void setActivePayRate(Double activePayRate) {
	this.activePayRate = activePayRate;
    }

    public void setArppu(Double arppu) {
	this.arppu = arppu;
    }

    public void setArpu(Double arpu) {
	this.arpu = arpu;
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

    public void setServerName(String serverName) {
	this.serverName = serverName;
    }

    public void setSinglePay(Double singlePay) {
	this.singlePay = singlePay;
    }

    public void setTmpFlag(Boolean tmpFlag) {
	this.tmpFlag = tmpFlag;
    }

}