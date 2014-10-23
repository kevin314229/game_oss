/**
 * 
 */
package com.jcwx.game.http.domain;

/**
 * @author Administrator
 * 
 */
public class OssZHPlayerTask {

    /********* 升星失败标识0-从未失败，1-失败过 **********/
    private Integer failFlag;
    /**** 接任务最低等级 *****/
    private Integer lowestLevel;
    /** 任务状态 0-不可接（不满足任务等级前置条件），1-未接，2-已接 ，3-已完成，4-完成计数 */
    private Integer status;
    /** 任务ID */
    private Integer taskId;
    private String taskName;
    /****** 升星成功次数 ******/
    private Integer upgradeTimes;

    public Integer getFailFlag() {
	return failFlag;
    }

    public Integer getLowestLevel() {
	return lowestLevel;
    }

    public Integer getStatus() {
	return status;
    }

    public Integer getTaskId() {
	return taskId;
    }

    public String getTaskName() {
	return taskName;
    }

    public Integer getUpgradeTimes() {
	return upgradeTimes;
    }

    public void setFailFlag(Integer failFlag) {
	this.failFlag = failFlag;
    }

    public void setLowestLevel(Integer lowestLevel) {
	this.lowestLevel = lowestLevel;
    }

    public void setStatus(Integer status) {
	this.status = status;
    }

    public void setTaskId(Integer taskId) {
	this.taskId = taskId;
    }

    public void setTaskName(String taskName) {
	this.taskName = taskName;
    }

    public void setUpgradeTimes(Integer upgradeTimes) {
	this.upgradeTimes = upgradeTimes;
    }
}
