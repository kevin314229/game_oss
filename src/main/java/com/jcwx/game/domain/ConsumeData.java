package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class ConsumeData implements Serializable {

    private static final long serialVersionUID = -7348465977481474538L;
    /** 服务器ID */
    private Integer areaId;
    /** 职业 */
    private String career;
    /** 充值日期 */
    private Date consumeDate;
    /** 充值ID */
    private Integer consumeId;
    /** 消费时间 */
    private Date createTime;
    /** 游戏ID */
    private Integer gameId;
    /** 账号ID */
    private String loginName;
    /** 折合人民币 */
    private Double moneyNum;
    /** 角色名称 */
    private String nickName;
    /** 消费金额 */
    private Integer number;
    /** 消费点 */
    private String operation;
    /** 消费明细 */
    private String operationDetail;
    /**  */
    private Integer playerBaseId;
    /** 游戏ID */
    private String ptId;
    /** 性别 */
    private String sex;
    private Integer consumeNum;

    public ConsumeData() {
    }

    public ConsumeData(Integer consumeId, Date consumeDate, Integer gameId,
	    Integer areaId, String loginName, String nickName,
	    Integer playerBaseId, String career, String sex, String operation,
	    Integer number, Double moneyNum, Date createTime) {
	this.consumeId = consumeId;
	this.consumeDate = consumeDate;
	this.gameId = gameId;
	this.areaId = areaId;
	this.loginName = loginName;
	this.nickName = nickName;
	this.playerBaseId = playerBaseId;
	this.career = career;
	this.sex = sex;
	this.operation = operation;
	this.number = number;
	this.moneyNum = moneyNum;
	this.createTime = createTime;
    }

    public Integer getAreaId() {
	return areaId;
    }

    public String getCareer() {
	return career;
    }

    public Date getConsumeDate() {
	return consumeDate;
    }

    public Integer getConsumeId() {
	return consumeId;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Integer getGameId() {
	return gameId;
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

    public Integer getNumber() {
	return number;
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

    public String getPtId() {
	return ptId;
    }

    public String getSex() {
	return sex;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setCareer(String career) {
	this.career = career;
    }

    public void setConsumeDate(Date consumeDate) {
	this.consumeDate = consumeDate;
    }

    public void setConsumeId(Integer consumeId) {
	this.consumeId = consumeId;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
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

    public void setNumber(Integer number) {
	this.number = number;
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

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public Integer getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(Integer consumeNum) {
        this.consumeNum = consumeNum;
    }
}