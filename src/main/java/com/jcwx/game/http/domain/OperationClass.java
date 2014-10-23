/**
 * 
 */
package com.jcwx.game.http.domain;

import java.util.Date;

/**
 * @author Administrator
 * 
 */
public class OperationClass {
    /** 创建时间 */
    private Date createTime;
    private Integer level;
    /** 登录入口 1-页游，2-手机 */
    private Integer loginFlag;
    /** 注册名称 */
    private String loginName;
    /** 玩家昵称 */
    private String nickName;
    /** 数量 */
    private Integer number;
    /** 数量 */
    private Integer number2;
    /** 操作 */
    private String operation;
    /** 操作细节 */
    private String operationDetail;
    /** 玩家编号 */
    private Integer playerBaseId;

    /** 平台标识 */
    private String ptFlag;

    /** 备注 */
    private String remark;

    /** 目标 */
    private String target;

    /** 目标 */
    private String target2;
    /** 类型：1-增加；2-减少 */
    private Integer type;

    public Date getCreateTime() {
	return createTime;
    }

    public Integer getLevel() {
	return level;
    }

    public Integer getLoginFlag() {
	return loginFlag;
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

    public Integer getNumber2() {
	return number2;
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

    public String getPtFlag() {
	return ptFlag;
    }

    public String getRemark() {
	return remark;
    }

    public String getTarget() {
	return target;
    }

    public String getTarget2() {
	return target2;
    }

    public Integer getType() {
	return type;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setLoginFlag(Integer loginFlag) {
	this.loginFlag = loginFlag;
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

    public void setNumber2(Integer number2) {
	this.number2 = number2;
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

    public void setPtFlag(String ptFlag) {
	this.ptFlag = ptFlag;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

    public void setTarget(String target) {
	this.target = target;
    }

    public void setTarget2(String target2) {
	this.target2 = target2;
    }

    public void setType(Integer type) {
	this.type = type;
    }

}
