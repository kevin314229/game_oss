/**
 * 
 */
package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * 角色职业
 * 
 * @author Administrator
 * 
 */
public class PlayerJobs implements Serializable {

    private String date;

    /**
     * 与数据库职业标识相关
     */
    private Integer job1Num;

    private Integer job2Num;

    private Integer job3Num;

    public String getDate() {
	return date;
    }

    public Integer getJob1Num() {
	return job1Num;
    }

    public Integer getJob2Num() {
	return job2Num;
    }

    public Integer getJob3Num() {
	return job3Num;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public void setJob1Num(Integer job1Num) {
	this.job1Num = job1Num;
    }

    public void setJob2Num(Integer job2Num) {
	this.job2Num = job2Num;
    }

    public void setJob3Num(Integer job3Num) {
	this.job3Num = job3Num;
    }

}
