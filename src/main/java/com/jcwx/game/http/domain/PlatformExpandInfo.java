/**   
 * @Title: PlatformExpandInfo.java 
 * @Package com.jcwx.game.http.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liushang 364173778@qq.com
 * @date 2013年12月25日 下午9:45:06 
 * @version V1.0   
 */
package com.jcwx.game.http.domain;

import java.util.Date;

/**
 * @ClassName: PlatformExpandInfo
 * @Description: 平台金币，登录等相关信息模型
 * @author liushang 364173778@qq.com
 * @date 2013年12月25日 下午9:45:06
 * 
 */
public class PlatformExpandInfo {
    /** 登录人数 */
    private Integer loginNumber;
    /** 平台日期 */
    private Date ptDate;
    /** 平台名称 */
    private String ptName;
    /** 充值金额 */
    private Integer rechargeAmount;
    /** 充值人数 */
    private Integer rechargeNumber;
    /** 注册人数 */
    private Integer regNumber;

    public Integer getLoginNumber() {
	return this.loginNumber;
    }

    public Date getPtDate() {
	return this.ptDate;
    }

    public String getPtName() {
	return this.ptName;
    }

    public Integer getRechargeAmount() {
	return this.rechargeAmount;
    }

    public Integer getRechargeNumber() {
	return this.rechargeNumber;
    }

    public Integer getRegNumber() {
	return this.regNumber;
    }

    public void setLoginNumber(Integer loginNumber) {
	this.loginNumber = loginNumber;
    }

    public void setPtDate(Date ptDate) {
	this.ptDate = ptDate;
    }

    public void setPtName(String ptName) {
	this.ptName = ptName;
    }

    public void setRechargeAmount(Integer rechargeAmount) {
	this.rechargeAmount = rechargeAmount;
    }

    public void setRechargeNumber(Integer rechargeNumber) {
	this.rechargeNumber = rechargeNumber;
    }

    public void setRegNumber(Integer regNumber) {
	this.regNumber = regNumber;
    }

}
