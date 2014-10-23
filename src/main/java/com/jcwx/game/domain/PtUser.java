package com.jcwx.game.domain;

import java.util.Date;

import com.jcwx.game.common.domain.BaseDomain;

public class PtUser extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**  */
    private Integer id;
    /** 账号 */
    private String userName;
    /** 平台标识 */
    private String ptFlag;
    /** 手机唯一码 */
    private String phoneId;
    /** 密码(md5),自已平台才有的 */
    private String pw;
    /** 平台的uid */
    private String uid;
    /** 昵称 */
    private String nickName;
    /** 最后登录时间 */
    private Date lastLoginTime;
    /** 最后登录大区 */
    private Integer lastArea;
    /** 激活标识 */
    private Integer actFlag;
    /** 性别(0男，1女) */
    private Integer sex;
    /** email */
    private String email;
    /** 身份证 */
    private String identityCard;
    /** 登录次数 */
    private Integer loginTimes;
    /** 创建时间 */
    private Date createTime;
    /** 最近登录列表 */
    private String recentLogin;
    /** 用户类型(0 默认 1 9c) */
    private Integer userType;
    /** 绑定状态(0 默认 1绑定) */
    private Integer bindFlag;

    public PtUser() {
    }

    public PtUser(Integer id, String userName, String ptFlag, String phoneId,
	    String pw, String uid, String nickName, Date lastLoginTime,
	    Integer lastArea, Integer actFlag, Integer sex, String email,
	    String identityCard, Integer loginTimes, Date createTime,
	    String recentLogin, Integer userType, Integer bindFlag) {
	this.id = id;
	this.userName = userName;
	this.ptFlag = ptFlag;
	this.phoneId = phoneId;
	this.pw = pw;
	this.uid = uid;
	this.nickName = nickName;
	this.lastLoginTime = lastLoginTime;
	this.lastArea = lastArea;
	this.actFlag = actFlag;
	this.sex = sex;
	this.email = email;
	this.identityCard = identityCard;
	this.loginTimes = loginTimes;
	this.createTime = createTime;
	this.recentLogin = recentLogin;
	this.userType = userType;
	this.bindFlag = bindFlag;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPtFlag() {
	return ptFlag;
    }

    public void setPtFlag(String ptFlag) {
	this.ptFlag = ptFlag;
    }

    public String getPhoneId() {
	return phoneId;
    }

    public void setPhoneId(String phoneId) {
	this.phoneId = phoneId;
    }

    public String getPw() {
	return pw;
    }

    public void setPw(String pw) {
	this.pw = pw;
    }

    public String getUid() {
	return uid;
    }

    public void setUid(String uid) {
	this.uid = uid;
    }

    public String getNickName() {
	return nickName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public Date getLastLoginTime() {
	return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
	this.lastLoginTime = lastLoginTime;
    }

    public Integer getLastArea() {
	return lastArea;
    }

    public void setLastArea(Integer lastArea) {
	this.lastArea = lastArea;
    }

    public Integer getActFlag() {
	return actFlag;
    }

    public void setActFlag(Integer actFlag) {
	this.actFlag = actFlag;
    }

    public Integer getSex() {
	return sex;
    }

    public void setSex(Integer sex) {
	this.sex = sex;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getIdentityCard() {
	return identityCard;
    }

    public void setIdentityCard(String identityCard) {
	this.identityCard = identityCard;
    }

    public Integer getLoginTimes() {
	return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
	this.loginTimes = loginTimes;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public String getRecentLogin() {
	return recentLogin;
    }

    public void setRecentLogin(String recentLogin) {
	this.recentLogin = recentLogin;
    }

    public Integer getUserType() {
	return userType;
    }

    public void setUserType(Integer userType) {
	this.userType = userType;
    }

    public Integer getBindFlag() {
	return bindFlag;
    }

    public void setBindFlag(Integer bindFlag) {
	this.bindFlag = bindFlag;
    }

}
