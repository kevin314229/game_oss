/**
 * 
 */
package com.jcwx.game.http.domain;

/**
 * @author Administrator
 * 
 */
public class PlayerVipInfor {

    private Integer playerId;

    private Integer vip;

    private Integer vipLevel;

    public Integer getPlayerId() {
	return playerId;
    }

    public Integer getVip() {
	return vip;
    }

    public Integer getVipLevel() {
	return vipLevel;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setVip(Integer vip) {
	this.vip = vip;
    }

    public void setVipLevel(Integer vipLevel) {
	this.vipLevel = vipLevel;
    }
}
