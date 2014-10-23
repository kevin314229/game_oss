package com.jcwx.game.http.domain;

import java.io.Serializable;

public class PlayerBehavior implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // 统计时间
    private String arrayTime;
    // 登录人数
    private String longinNum;
    // 付费人数
    private String payNum;
    // 付费率
    private String payRate;
    // arppu
    private String arppu;
    // 充值总额
    private String paySum;
    public String getArrayTime() {
        return arrayTime;
    }
    public void setArrayTime(String arrayTime) {
        this.arrayTime = arrayTime;
    }
    public String getLonginNum() {
        return longinNum;
    }
    public void setLonginNum(String longinNum) {
        this.longinNum = longinNum;
    }
    public String getPayNum() {
        return payNum;
    }
    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }
    public String getPayRate() {
        return payRate;
    }
    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }
    public String getPaySum() {
        return paySum;
    }
    public void setPaySum(String paySum) {
        this.paySum = paySum;
    }
    public String getArppu() {
        return arppu;
    }
    public void setArppu(String arppu) {
        this.arppu = arppu;
    }
    
    

}
