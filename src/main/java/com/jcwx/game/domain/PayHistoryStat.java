package com.jcwx.game.domain;

import java.io.Serializable;

public class PayHistoryStat implements Serializable {

    private static final long serialVersionUID = 3776931496627786551L;

    /** 充值金额,注：目前这个值为金币 */
    private Double amountSum;

    /** ARPU值（保留两位小数） */
    private String arpu;

    /** 日 */
    private String day;

    /** 月 */
    private String month;

    /** 充值次数 */
    private Integer payNum;

    /** 充值人数 */
    private Integer payPlayerNum;

    /** 年 */
    private String year;

    public Double getAmountSum() {
	return amountSum;
    }

    public String getArpu() {
	return arpu;
    }

    public String getDay() {
	return day;
    }

    public String getMonth() {
	return month;
    }

    public Integer getPayNum() {
	return payNum;
    }

    public Integer getPayPlayerNum() {
	return payPlayerNum;
    }

    public String getYear() {
	return year;
    }

    public void setAmountSum(Double amountSum) {
	this.amountSum = amountSum;
    }

    public void setArpu(String arpu) {
	this.arpu = arpu;
    }

    public void setDay(String day) {
	this.day = day;
    }

    public void setMonth(String month) {
	this.month = month;
    }

    public void setPayNum(Integer payNum) {
	this.payNum = payNum;
    }

    public void setPayPlayerNum(Integer payPlayerNum) {
	this.payPlayerNum = payPlayerNum;
    }

    public void setYear(String year) {
	this.year = year;
    }

}
