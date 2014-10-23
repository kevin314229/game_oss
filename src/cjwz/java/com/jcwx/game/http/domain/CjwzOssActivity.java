package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

public class CjwzOssActivity implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 936486006254305077L;
    /** 活动条件 */
    private String activityCondition;
    /** 活动描述 */
    private String activityDesc;
    /** 活动id */
    private Integer activityId;
    /** 钮按名字 */
    private String btnName;
    /** 创建时间 */
    private Date createTime;
    /** 创建用户 */
    private String createUser;
    /** 活动结束时间 */
    private Date endTime;
    /** 活动名称 */
    private String name;
    /** 显示时间 */
    private Date openTime;
    /** 奖励道具 */
    private String reward;
    /** 活动规则 */
    private String rule;
    /** 活动开启时间 */
    private Date startTime;
    /** 活动类型 */
    private Integer type;
    /** 更新时间 */
    private Date updateTime;
    /** 更新用户 */
    private String updateUser;
    
    private Integer icon;

    public CjwzOssActivity() {
    }

    public CjwzOssActivity(Integer activityId, Integer type, String name,
	    String activityDesc, String rule, String activityCondition,
	    String reward, String btnName, Date openTime, Date startTime,
	    Date endTime, Date createTime, Date updateTime, String createUser,
	    String updateUser) {
	this.activityId = activityId;
	this.type = type;
	this.name = name;
	this.activityDesc = activityDesc;
	this.rule = rule;
	this.activityCondition = activityCondition;
	this.reward = reward;
	this.btnName = btnName;
	this.openTime = openTime;
	this.startTime = startTime;
	this.endTime = endTime;
	this.createTime = createTime;
	this.updateTime = updateTime;
	this.createUser = createUser;
	this.updateUser = updateUser;
    }

    public String getActivityCondition() {
	return activityCondition;
    }

    public String getActivityDesc() {
	return activityDesc;
    }

    public Integer getActivityId() {
	return activityId;
    }

    public String getBtnName() {
	return btnName;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public String getCreateUser() {
	return createUser;
    }

    public Date getEndTime() {
	return endTime;
    }

    public String getName() {
	return name;
    }

    public Date getOpenTime() {
	return openTime;
    }

    public String getReward() {
	return reward;
    }

    public String getRule() {
	return rule;
    }

    public Date getStartTime() {
	return startTime;
    }

    public Integer getType() {
	return type;
    }

    public Date getUpdateTime() {
	return updateTime;
    }

    public String getUpdateUser() {
	return updateUser;
    }

    public void setActivityCondition(String activityCondition) {
	this.activityCondition = activityCondition;
    }

    public void setActivityDesc(String activityDesc) {
	this.activityDesc = activityDesc;
    }

    public void setActivityId(Integer activityId) {
	this.activityId = activityId;
    }

    public void setBtnName(String btnName) {
	this.btnName = btnName;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setCreateUser(String createUser) {
	this.createUser = createUser;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setOpenTime(Date openTime) {
	this.openTime = openTime;
    }

    public void setReward(String reward) {
	this.reward = reward;
    }

    public void setRule(String rule) {
	this.rule = rule;
    }

    public void setStartTime(Date startTime) {
	this.startTime = startTime;
    }

    public void setType(Integer type) {
	this.type = type;
    }

    public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
    }

    public void setUpdateUser(String updateUser) {
	this.updateUser = updateUser;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }
    
}