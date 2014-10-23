package com.jcwx.game.domain;

import java.io.Serializable;

public class PayHistorySta implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 充值金额,注：目前这个值为金币 */
    private Double amountSum;

    /** ARPU值（保留两位小数） */
    private String arpu;

    /** 充值区间 **/
    private String interval;

    /** 充值次数 */
    private Integer payNum;

    /** 充值人数 */
    private Integer payPlayerNum;

    public Double getAmountSum() {
	return amountSum;
    }

    public String getArpu() {
	return arpu;
    }

    public String getInterval() {
	return interval;
    }

    public Integer getPayNum() {
	return payNum;
    }

    public Integer getPayPlayerNum() {
	return payPlayerNum;
    }

    public void setAmountSum(Double amountSum) {
	this.amountSum = amountSum;
    }

    public void setArpu(String arpu) {
	this.arpu = arpu;
    }

    public void setInterval(String interval) {
	this.interval = interval;
    }

    public void setPayNum(Integer payNum) {
	this.payNum = payNum;
    }

    public void setPayPlayerNum(Integer payPlayerNum) {
	this.payPlayerNum = payPlayerNum;
    }

}
