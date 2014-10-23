/**
 * 
 */
package com.jcwx.game.http.domain;

import java.util.Date;

/**
 * @author Administrator
 * 
 */
public class PlayerClass {
    /** 运营商 **/
    private String carrierOperator;
    /**
     * 魔晶数量
     * */
    private Integer gold;

    // 手机登录key
    private String guestKey;

    private Integer lastLoginId;

    /** 玩家最后登出时间 */
    private Date lastLoginTime;

    /** 用户名 */
    private String loginName;

    /** 在线状态，0-离线，1-在线 */
    private Integer onlineStatus;

    /** 玩家创建时间 */
    private Date playerCreateTime;

    /** 玩家ID */
    private Integer playerId;

    public String getCarrierOperator() {
	return carrierOperator;
    }

    public Integer getGold() {
	return gold;
    }

    public String getGuestKey() {
	return guestKey;
    }

    public Integer getLastLoginId() {
	return lastLoginId;
    }

    public Date getLastLoginTime() {
	return lastLoginTime;
    }

    public String getLoginName() {
	return loginName;
    }

    public Integer getOnlineStatus() {
	return onlineStatus;
    }

    public Date getPlayerCreateTime() {
	return playerCreateTime;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public void setCarrierOperator(String carrierOperator) {
	this.carrierOperator = carrierOperator;
    }

    public void setGold(Integer gold) {
	this.gold = gold;
    }

    public void setGuestKey(String guestKey) {
	this.guestKey = guestKey;
    }

    public void setLastLoginId(Integer lastLoginId) {
	this.lastLoginId = lastLoginId;
    }

    public void setLastLoginTime(Date lastLoginTime) {
	this.lastLoginTime = lastLoginTime;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setOnlineStatus(Integer onlineStatus) {
	this.onlineStatus = onlineStatus;
    }

    public void setPlayerCreateTime(Date playerCreateTime) {
	this.playerCreateTime = playerCreateTime;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }
}
