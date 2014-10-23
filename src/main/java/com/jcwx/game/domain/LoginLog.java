package com.jcwx.game.domain;

import java.util.Date;

public class LoginLog {

    /** IP地区 */
    private String address;
    /** IP地址 */
    private String ip;
    /** 从哪端登录的 端标识 1-页游，2-手机 */
    private Integer loginFrom;
    /** 登录日志编号 */
    private Integer loginLogID;
    /** 帐号 */
    private String loginName;
    /** 登录时间 */
    private Date loginTime;
    /** 昵称 */
    private String nickName;
    /** 这次在线时间(秒)，在退出时计算 */
    private Integer onlineTime;
    /** 玩家编号 */
    private Integer playerID;
    /** 玩家等级 **/
    private int playerLevel;
    /** 平台标识 */
    private String ptSymbol;
    /** 登出时间 */
    private Date quitTime;
    private Integer vip;
    /** 退出时的vip等级 */
    private Integer vipLevel;

    public LoginLog() {
    }

    public String getAddress() {
	return address;
    }

    public String getIp() {
	return ip;
    }

    public Integer getLoginFrom() {
	return loginFrom;
    }

    public Integer getLoginLogID() {
	return loginLogID;
    }

    public String getLoginName() {
	return loginName;
    }

    public Date getLoginTime() {
	return loginTime;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getOnlineTime() {
	return onlineTime;
    }

    public Integer getPlayerID() {
	return playerID;
    }

    public int getPlayerLevel() {
	return playerLevel;
    }

    public String getPtSymbol() {
	return ptSymbol;
    }

    public Date getQuitTime() {
	return quitTime;
    }

    public Integer getVip() {
	return vip;
    }

    public Integer getVipLevel() {
	return vipLevel;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public void setIp(String ip) {
	this.ip = ip;
    }

    public void setLoginFrom(Integer loginFrom) {
	this.loginFrom = loginFrom;
    }

    public void setLoginLogID(Integer loginLogID) {
	this.loginLogID = loginLogID;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setLoginTime(Date loginTime) {
	this.loginTime = loginTime;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOnlineTime(Integer onlineTime) {
	this.onlineTime = onlineTime;
    }

    public void setPlayerID(Integer playerID) {
	this.playerID = playerID;
    }

    public void setPlayerLevel(int playerLevel) {
	this.playerLevel = playerLevel;
    }

    public void setPtSymbol(String ptSymbol) {
	this.ptSymbol = ptSymbol;
    }

    public void setQuitTime(Date quitTime) {
	this.quitTime = quitTime;
    }

    public void setVip(Integer vip) {
	this.vip = vip;
    }

    public void setVipLevel(Integer vipLevel) {
	this.vipLevel = vipLevel;
    }

}