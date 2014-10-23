package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

public class OssOperationLog implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 大区ID */
    private Integer areaId;
    /** 创建时间 */
    private Date createTime;
    /** 类型：1-增加；2-减少 */
    private Integer flowType;
    /** 游戏ID */
    private Integer gameId;
    /** 物品ID */
    private Integer itemId;
    /** 物品名称 */
    private String itemName;
    /** 当前角色等级 */
    private Integer level;
    /** 账号 */
    private String loginName;
    /** 角色昵称 */
    private String nickName;
    /** 数量 */
    private Integer number;
    /** 操作 */
    private String operation;
    /** 操作说明 */
    private String operationDetail;
    /** 操作日志编号 */
    private Integer operationLogId;
    /** 角色ID */
    private Integer playerBaseId;
    /** 玩家ID */
    private Integer playerId;
    /** 平台标识 */
    private String ptId;

    public Integer getAreaId() {
	return areaId;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Integer getFlowType() {
	return flowType;
    }

    public Integer getGameId() {
	return gameId;
    }

    public Integer getItemId() {
	return itemId;
    }

    public String getItemName() {
	return itemName;
    }

    public Integer getLevel() {
	return level;
    }

    public String getLoginName() {
	return loginName;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getNumber() {
	return number;
    }

    public String getOperation() {
	return operation;
    }

    public String getOperationDetail() {
	return operationDetail;
    }

    public Integer getOperationLogId() {
	return operationLogId;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public String getPtId() {
	return ptId;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setFlowType(Integer flowType) {
	this.flowType = flowType;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setItemId(Integer itemId) {
	this.itemId = itemId;
    }

    public void setItemName(String itemName) {
	this.itemName = itemName;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setNumber(Integer number) {
	this.number = number;
    }

    public void setOperation(String operation) {
	this.operation = operation;
    }

    public void setOperationDetail(String operationDetail) {
	this.operationDetail = operationDetail;
    }

    public void setOperationLogId(Integer operationLogId) {
	this.operationLogId = operationLogId;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

}