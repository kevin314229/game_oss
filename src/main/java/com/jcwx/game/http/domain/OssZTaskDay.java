package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

public class OssZTaskDay implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 创建时间 */
    private Date createTime;
    /** 最后修改时间 */
    private Date modifyTime;
    /** 日常任务刷新数量 */
    private Integer refreshNub;
    /** 主键Id */
    private Integer taskDayId;
    /** 日常任务完成量 */
    private Integer taskNub;
    /** 日常任务升星数量 */
    private Integer upgradeNub;

    public Date getCreateTime() {
	return createTime;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public Integer getRefreshNub() {
	return refreshNub;
    }

    public Integer getTaskDayId() {
	return taskDayId;
    }

    public Integer getTaskNub() {
	return taskNub;
    }

    public Integer getUpgradeNub() {
	return upgradeNub;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

    public void setRefreshNub(Integer refreshNub) {
	this.refreshNub = refreshNub;
    }

    public void setTaskDayId(Integer taskDayId) {
	this.taskDayId = taskDayId;
    }

    public void setTaskNub(Integer taskNub) {
	this.taskNub = taskNub;
    }

    public void setUpgradeNub(Integer upgradeNub) {
	this.upgradeNub = upgradeNub;
    }

}
