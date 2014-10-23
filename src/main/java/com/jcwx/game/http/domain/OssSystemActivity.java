package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统活动
 * 
 * @author Administrator
 * 
 */
public class OssSystemActivity implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 活动详情读取配置活动id：id#id#id */
    private String activityDeploy;
    /** 活动名称 */
    private String activityName;
    /** 创建时间 */
    private Date createTime;
    /** 活动描述 */
    private String describe;
    /** 活动结束时间 */
    private Date endTime;
    /** 活动等级要求 */
    private Integer grade;
    /** 修改时间 */
    private Date moldfyTime;
    /** 活动开启时间 */
    private Date startTime;
    /** 主键ID */
    private Integer systemActivityId;

    public OssSystemActivity() {
    }

    public String getActivityDeploy() {
	return activityDeploy;
    }

    public String getActivityName() {
	return activityName;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public String getDescribe() {
	return describe;
    }

    public Date getEndTime() {
	return endTime;
    }

    public Integer getGrade() {
	return grade;
    }

    public Date getMoldfyTime() {
	return moldfyTime;
    }

    public Date getStartTime() {
	return startTime;
    }

    public Integer getSystemActivityId() {
	return systemActivityId;
    }

    public void setActivityDeploy(String activityDeploy) {
	this.activityDeploy = activityDeploy;
    }

    public void setActivityName(String activityName) {
	this.activityName = activityName;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDescribe(String describe) {
	this.describe = describe;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public void setGrade(Integer grade) {
	this.grade = grade;
    }

    public void setMoldfyTime(Date moldfyTime) {
	this.moldfyTime = moldfyTime;
    }

    public void setStartTime(Date startTime) {
	this.startTime = startTime;
    }

    public void setSystemActivityId(Integer systemActivityId) {
	this.systemActivityId = systemActivityId;
    }

}
