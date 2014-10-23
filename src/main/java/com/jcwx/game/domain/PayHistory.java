package com.jcwx.game.domain;

import java.util.Date;

/**
 * 充值记录查询
 * 
 * @author csp
 * 
 */
public class PayHistory {

    /** 大区ID */
    private Integer areaId;
    /** 订单处理完成时间 */
    private Date completeTime;
    /** 币种(货币类型:1-人民币；2-美元) */
    private Integer currency;
    /** 游戏项目ID */
    private Integer gameId;
    /** 帐号 */
    private String loginName;
    /** 充值币种数量 */
    private double moneyNum;
    private String nickName;
    /** 主键 */
    private Integer payHistoryId;
    /** 角色ID */
    private Integer playerBaseId;
    /** 玩家ID */
    private Integer playerId;
    /** 平台(游戏)大区号 */
    private Integer ptArea;
    /** 平台标识 */
    private String ptId;
    /** 平台币数量 */
    private double ptMoneyNum;

    /** 平台订单号 */
    private String ptOrder;

    public PayHistory() {
    }

    public PayHistory(Integer payHistoryId, Integer gameId, String ptId,
	    Integer areaId, Integer playerId, String loginName,
	    Integer playerBaseId, Integer ptArea, String ptOrder,
	    Integer currency, double ptMoneyNum, double moneyNum,
	    Date completeTime) {
	this.payHistoryId = payHistoryId;
	this.gameId = gameId;
	this.ptId = ptId;
	this.areaId = areaId;
	this.playerId = playerId;
	this.loginName = loginName;
	this.playerBaseId = playerBaseId;
	this.ptArea = ptArea;
	this.ptOrder = ptOrder;
	this.currency = currency;
	this.ptMoneyNum = ptMoneyNum;
	this.moneyNum = moneyNum;
	this.completeTime = completeTime;
    }

    public Integer getAreaId() {
	return areaId;
    }

    public Date getCompleteTime() {
	return completeTime;
    }

    public Integer getCurrency() {
	return currency;
    }

    public Integer getGameId() {
	return gameId;
    }

    public String getLoginName() {
	return loginName;
    }

    public double getMoneyNum() {
	return moneyNum;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getPayHistoryId() {
	return payHistoryId;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public Integer getPtArea() {
	return ptArea;
    }

    public String getPtId() {
	return ptId;
    }

    public double getPtMoneyNum() {
	return ptMoneyNum;
    }

    public String getPtOrder() {
	return ptOrder;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setCompleteTime(Date completeTime) {
	this.completeTime = completeTime;
    }

    public void setCurrency(Integer currency) {
	this.currency = currency;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setMoneyNum(double moneyNum) {
	this.moneyNum = moneyNum;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setPayHistoryId(Integer payHistoryId) {
	this.payHistoryId = payHistoryId;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setPtArea(Integer ptArea) {
	this.ptArea = ptArea;
    }

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

    public void setPtMoneyNum(double ptMoneyNum) {
	this.ptMoneyNum = ptMoneyNum;
    }

    public void setPtOrder(String ptOrder) {
	this.ptOrder = ptOrder;
    }
}
