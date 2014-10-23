package com.jcwx.game.http.domain;

import java.util.Date;

/**
 * 当日运营统计
 * 
 * @author liushang
 */
public class DayOperaStat {
    /** 活跃用户 */
    private Long activeUsers;
    /** 新增付费用户数 */
    private Long addedPayingSubscribers;
    /** 主键ID */
    private String id;
    /** 登陆角色 */
    private Long landingRoles;
    /** 登陆用户 */
    private Long login;
    /** 净活跃用户 */
    private Long netActiveUsers;
    /** 新增付费充值金额 */
    private Long newPayRechargeAmount;
    /** 付费价值 */
    private Long paidValue;
    /** 付费用户 */
    private Long payingUsers;
    /** 付费率 */
    private Long payRate;
    /** 付费次数 */
    private Long payTimes;
    /** 充值金额 */
    private Long rechargeAmount;
    /** 注册用户 */
    private Long registeredUsers;
    /** 注册用户付费率 */
    private Long registeredUsersPayRate;
    /** 注册价值 */
    private Long signupValue;
    /** 统计日期 */
    private Date statDate;

    public Long getActiveUsers() {
	return this.activeUsers;
    }

    public Long getAddedPayingSubscribers() {
	return this.addedPayingSubscribers;
    }

    public String getId() {
	return this.id;
    }

    public Long getLandingRoles() {
	return this.landingRoles;
    }

    public Long getLogin() {
	return this.login;
    }

    public Long getNetActiveUsers() {
	return this.netActiveUsers;
    }

    public Long getNewPayRechargeAmount() {
	return this.newPayRechargeAmount;
    }

    public Long getPaidValue() {
	return this.paidValue;
    }

    public Long getPayingUsers() {
	return this.payingUsers;
    }

    public Long getPayRate() {
	return this.payRate;
    }

    public Long getPayTimes() {
	return this.payTimes;
    }

    public Long getRechargeAmount() {
	return this.rechargeAmount;
    }

    public Long getRegisteredUsers() {
	return this.registeredUsers;
    }

    public Long getRegisteredUsersPayRate() {
	return this.registeredUsersPayRate;
    }

    public Long getSignupValue() {
	return this.signupValue;
    }

    public Date getStatDate() {
	return this.statDate;
    }

    public void setActiveUsers(Long activeUsers) {
	this.activeUsers = activeUsers;
    }

    public void setAddedPayingSubscribers(Long addedPayingSubscribers) {
	this.addedPayingSubscribers = addedPayingSubscribers;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setLandingRoles(Long landingRoles) {
	this.landingRoles = landingRoles;
    }

    public void setLogin(Long login) {
	this.login = login;
    }

    public void setNetActiveUsers(Long netActiveUsers) {
	this.netActiveUsers = netActiveUsers;
    }

    public void setNewPayRechargeAmount(Long newPayRechargeAmount) {
	this.newPayRechargeAmount = newPayRechargeAmount;
    }

    public void setPaidValue(Long paidValue) {
	this.paidValue = paidValue;
    }

    public void setPayingUsers(Long payingUsers) {
	this.payingUsers = payingUsers;
    }

    public void setPayRate(Long payRate) {
	this.payRate = payRate;
    }

    public void setPayTimes(Long payTimes) {
	this.payTimes = payTimes;
    }

    public void setRechargeAmount(Long rechargeAmount) {
	this.rechargeAmount = rechargeAmount;
    }

    public void setRegisteredUsers(Long registeredUsers) {
	this.registeredUsers = registeredUsers;
    }

    public void setRegisteredUsersPayRate(Long registeredUsersPayRate) {
	this.registeredUsersPayRate = registeredUsersPayRate;
    }

    public void setSignupValue(Long signupValue) {
	this.signupValue = signupValue;
    }

    public void setStatDate(Date statDate) {
	this.statDate = statDate;
    }

}
