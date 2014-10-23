package com.jcwx.game.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class OperationStatistic implements Serializable {

    private static final long serialVersionUID = 6958936719656404558L;
    /** 大区ID */
    private Integer areaId;
    private BigDecimal consumeNum;
    /** 类型：1-增加；2-减少 */
    private Integer flowType;
    /** 游戏ID */
    private Integer gameId;
    /** 物品ID */
    private Integer itemId;
    /** 物品名称 */
    private String itemName;
    /** 数量 */
    private long number;
    /** 操作 */
    private String operation;
    /** 操作日志统计日期 */
    private String operationDate;

    /** 操作日志编号 */
    private Integer operationId;
    private BigDecimal produceNum;
    private String percent;

    public OperationStatistic() {
    }

    public OperationStatistic(String operationDate, Integer operationId,
	    Integer gameId, Integer areaId, Integer itemId, String itemName,
	    Integer flowType, long number, String operation) {
	this.operationDate = operationDate;
	this.operationId = operationId;
	this.gameId = gameId;
	this.areaId = areaId;
	this.itemId = itemId;
	this.itemName = itemName;
	this.flowType = flowType;
	this.number = number;
	this.operation = operation;
    }

    public Integer getAreaId() {
	return areaId;
    }

    public BigDecimal getConsumeNum() {
	return consumeNum;
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

    public long getNumber() {
	return number;
    }

    public String getOperation() {
	return operation;
    }

    public String getOperationDate() {
	return operationDate;
    }

    public Integer getOperationId() {
	return operationId;
    }

    public BigDecimal getProduceNum() {
	return produceNum;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setConsumeNum(BigDecimal consumeNum) {
	this.consumeNum = consumeNum;
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

    public void setNumber(long number) {
	this.number = number;
    }

    public void setOperation(String operation) {
	this.operation = operation;
    }

    public void setOperationDate(String operationDate) {
	this.operationDate = operationDate;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
    }

    public void setProduceNum(BigDecimal produceNum) {
	this.produceNum = produceNum;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

}