package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class ConsumeFirstData implements Serializable {

    private static final long serialVersionUID = -8678833188520736758L;
    /** 服务器ID */
    private Integer areaId;
    /** 充值日期 */
    private Date consumeDate;
    /** 充值ID */
    private Integer consumeId;
    /** 首次登陆时间 */
    private Date firstLoginTime;
    private Integer flowType;
    /** 距首登陆时间 */
    private Integer fromFirstDays;
    /** 游戏ID */
    private Integer gameId;
    /** 等级 */
    private Integer level;
    /** 账号ID */
    private String loginName;
    private Double moneyNum;
    /** 角色名称 */
    private String nickName;
    /** 消费点 */
    private String operation;
    private String operationDetail;
    /** 角色ID */
    private Integer playerBaseId;
    /** 道具名称 */
    private String propsName;
    /** 游戏ID */
    private String ptId;

    public ConsumeFirstData() {
    }

    public ConsumeFirstData(Integer consumeId, Date consumeDate,
	    Integer gameId, Integer areaId, String loginName, String nickName,
	    Integer playerBaseId, Integer level, String operation,
	    Date firstLoginTime, Integer fromFirstDays, String propsName) {
	this.consumeId = consumeId;
	this.consumeDate = consumeDate;
	this.gameId = gameId;
	this.areaId = areaId;
	this.loginName = loginName;
	this.nickName = nickName;
	this.playerBaseId = playerBaseId;
	this.level = level;
	this.operation = operation;
	this.firstLoginTime = firstLoginTime;
	this.fromFirstDays = fromFirstDays;
	this.propsName = propsName;
    }

    public Integer getAreaId() {
	return areaId;
    }

    public Date getConsumeDate() {
	return consumeDate;
    }

    public Integer getConsumeId() {
	return consumeId;
    }

    public Date getFirstLoginTime() {
	return firstLoginTime;
    }

    public Integer getFlowType() {
	return flowType;
    }

    public Integer getFromFirstDays() {
	return fromFirstDays;
    }

    public Integer getGameId() {
	return gameId;
    }

    public Integer getLevel() {
	return level;
    }

    public String getLoginName() {
	return loginName;
    }

    public Double getMoneyNum() {
	return moneyNum;
    }

    public String getNickName() {
	return nickName;
    }

    public String getOperation() {
	return operation;
    }

    public String getOperationDetail() {
	return operationDetail;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public String getPropsName() {
	return propsName;
    }

    public String getPtId() {
	return ptId;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setConsumeDate(Date consumeDate) {
	this.consumeDate = consumeDate;
    }

    public void setConsumeId(Integer consumeId) {
	this.consumeId = consumeId;
    }

    public void setFirstLoginTime(Date firstLoginTime) {
	this.firstLoginTime = firstLoginTime;
    }

    public void setFlowType(Integer flowType) {
	this.flowType = flowType;
    }

    public void setFromFirstDays(Integer fromFirstDays) {
	this.fromFirstDays = fromFirstDays;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setMoneyNum(Double moneyNum) {
	this.moneyNum = moneyNum;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOperation(String operation) {
	this.operation = operation;
    }

    public void setOperationDetail(String operationDetail) {
	this.operationDetail = operationDetail;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPropsName(String propsName) {
	this.propsName = propsName;
    }

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

}