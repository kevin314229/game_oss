package com.jcwx.game.domain;

import java.io.Serializable;

/**
 * 
 * @ClassName: ZPlayerMoneyDay
 * @Description: 玩家价值表
 * @author liushang 364173778@qq.com
 * @date 2013年12月7日 上午10:42:40
 * 
 */
@SuppressWarnings("serial")
public class ZPlayerMoneyDay implements Serializable {

    /** 礼券 */
    private Integer bindGold;
    /** 玩家魔晶 */
    private Integer gold;
    /** 帐号 */
    private String loginName;
    /** 玩家昵称 */
    private String nickName;
    /** 玩家基础ID */
    private Integer playerBaseId;
    /** 玩家ID */
    private Integer playerId;
    /** 玩家金币 */
    private Integer silver;
    /** 星石 */
    private Integer starStone;
    /** vip等级 */
    private Integer vipLevel;
    /** vip经验 */
    private Integer vipScore;

    public Integer getBindGold() {
	return this.bindGold;
    }

    public Integer getGold() {
	return this.gold;
    }

    public String getLoginName() {
	return this.loginName;
    }

    public String getNickName() {
	return this.nickName;
    }

    public Integer getPlayerBaseId() {
	return this.playerBaseId;
    }

    public Integer getPlayerId() {
	return this.playerId;
    }

    public Integer getSilver() {
	return this.silver;
    }

    public Integer getStarStone() {
	return this.starStone;
    }

    public Integer getVipLevel() {
	return this.vipLevel;
    }

    public Integer getVipScore() {
	return this.vipScore;
    }

    public void setBindGold(Integer bindGold) {
	this.bindGold = bindGold;
    }

    public void setGold(Integer gold) {
	this.gold = gold;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setSilver(Integer silver) {
	this.silver = silver;
    }

    public void setStarStone(Integer starStone) {
	this.starStone = starStone;
    }

    public void setVipLevel(Integer vipLevel) {
	this.vipLevel = vipLevel;
    }

    public void setVipScore(Integer vipScore) {
	this.vipScore = vipScore;
    }

}