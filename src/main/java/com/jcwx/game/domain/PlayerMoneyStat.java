package com.jcwx.game.domain;

import java.io.Serializable;

public class PlayerMoneyStat implements Serializable {

    /** 获得赠送金币数量 */
    private Integer achieveGiftMoney;
    /** 获得充值金币数量 */
    private Integer achievePayMoney;
    /** 消耗赠送金币数量 */
    private Integer consumeGiftMoney;
    /** 消耗充值金币数量 */
    private Integer consumePayMoney;
    /**  */
    private String moneyVerify;
    /** 玩家ID */
    private String playerId;
    /** 玩家金币统计ID */
    private String playerMoneyStatId;
    /** 年月日 */
    private String yearMonthDayStr;

    public PlayerMoneyStat() {
    }

    public PlayerMoneyStat(String playerMoneyStatId, String playerId,
	    String yearMonthDayStr, Integer consumePayMoney,
	    Integer consumeGiftMoney, Integer achievePayMoney,
	    Integer achieveGiftMoney, String moneyVerify) {
	this.playerMoneyStatId = playerMoneyStatId;
	this.playerId = playerId;
	this.yearMonthDayStr = yearMonthDayStr;
	this.consumePayMoney = consumePayMoney;
	this.consumeGiftMoney = consumeGiftMoney;
	this.achievePayMoney = achievePayMoney;
	this.achieveGiftMoney = achieveGiftMoney;
	this.moneyVerify = moneyVerify;
    }

    public Integer getAchieveGiftMoney() {
	return achieveGiftMoney;
    }

    public Integer getAchievePayMoney() {
	return achievePayMoney;
    }

    public Integer getConsumeGiftMoney() {
	return consumeGiftMoney;
    }

    public Integer getConsumePayMoney() {
	return consumePayMoney;
    }

    public String getMoneyVerify() {
	return moneyVerify;
    }

    public String getPlayerId() {
	return playerId;
    }

    public String getPlayerMoneyStatId() {
	return playerMoneyStatId;
    }

    public String getYearMonthDayStr() {
	return yearMonthDayStr;
    }

    public void setAchieveGiftMoney(Integer achieveGiftMoney) {
	this.achieveGiftMoney = achieveGiftMoney;
    }

    public void setAchievePayMoney(Integer achievePayMoney) {
	this.achievePayMoney = achievePayMoney;
    }

    public void setConsumeGiftMoney(Integer consumeGiftMoney) {
	this.consumeGiftMoney = consumeGiftMoney;
    }

    public void setConsumePayMoney(Integer consumePayMoney) {
	this.consumePayMoney = consumePayMoney;
    }

    public void setMoneyVerify(String moneyVerify) {
	this.moneyVerify = moneyVerify;
    }

    public void setPlayerId(String playerId) {
	this.playerId = playerId;
    }

    public void setPlayerMoneyStatId(String playerMoneyStatId) {
	this.playerMoneyStatId = playerMoneyStatId;
    }

    public void setYearMonthDayStr(String yearMonthDayStr) {
	this.yearMonthDayStr = yearMonthDayStr;
    }

}