/**
 * 
 */
package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * @author Administrator 当前在线用户传输类
 */
public class OnlinePlayer implements Serializable {

    private static final long serialVersionUID = 1984983608161662557L;

    /** 地区 */
    private String address;

    /** 运营商平台标识 */
    private String carrierOperator;

    /** 最后登录IP */
    private String lastLoginIP;

    /** 当前在线时间（分钟） */
    private Integer onlineMinutes;

    /** 玩家名称 */
    private String playerName;

    /** 用户帐号 */
    private String userName;

    public String getAddress() {
	return address;
    }

    public String getCarrierOperator() {
	return carrierOperator;
    }

    public String getLastLoginIP() {
	return lastLoginIP;
    }

    public Integer getOnlineMinutes() {
	return onlineMinutes;
    }

    public String getPlayerName() {
	return playerName;
    }

    public String getUserName() {
	return userName;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public void setCarrierOperator(String carrierOperator) {
	this.carrierOperator = carrierOperator;
    }

    public void setLastLoginIP(String lastLoginIP) {
	this.lastLoginIP = lastLoginIP;
    }

    public void setOnlineMinutes(Integer onlineMinutes) {
	this.onlineMinutes = onlineMinutes;
    }

    public void setPlayerName(String playerName) {
	this.playerName = playerName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

}
