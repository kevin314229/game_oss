package com.jcwx.game.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.jcwx.game.common.domain.BaseDomain;

public class ZLevelDay extends BaseDomain {

    /** id */
    private Integer id;
    /** 记录时间(天) */
    private Date idDate;
    /** 玩家等级 */
    private Integer level;
    /** 人数 */
    private Integer peopleNum;

    public ZLevelDay() {
    }

    public ZLevelDay(Integer id, Date idDate, Integer level, Integer peopleNum) {
	this.id = id;
	this.idDate = idDate;
	this.level = level;
	this.peopleNum = peopleNum;
    }

    @JSONField(serialize = false)
    public Integer getId() {
	return id;
    }

    public Date getIdDate() {
	return idDate;
    }

    public Integer getLevel() {
	return level;
    }

    public Integer getPeopleNum() {
	return peopleNum;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setIdDate(Date idDate) {
	this.idDate = idDate;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setPeopleNum(Integer peopleNum) {
	this.peopleNum = peopleNum;
    }

}