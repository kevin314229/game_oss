package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class ZQueryActiveUser implements Serializable {

    private Date activeDate;
    private Integer areaId;
    private Integer day0;
    private Integer day1;
    private Integer day2;
    private Integer day3;
    private Integer day4;
    private Integer day5;
    private Integer day6;
    private Integer day7;
    private Integer gameId;
    private Integer month1;
    private Integer week1;
    private Integer week2;
    private String  ptId;

    public Date getActiveDate() {
	return activeDate;
    }

    public Integer getAreaId() {
	return areaId;
    }

    public Integer getDay0() {
	return day0;
    }

    public Integer getDay1() {
	return day1;
    }

    public Integer getDay2() {
	return day2;
    }

    public Integer getDay3() {
	return day3;
    }

    public Integer getDay4() {
	return day4;
    }

    public Integer getDay5() {
	return day5;
    }

    public Integer getDay6() {
	return day6;
    }

    public Integer getDay7() {
	return day7;
    }

    public Integer getGameId() {
	return gameId;
    }

    public Integer getMonth1() {
	return month1;
    }

    public Integer getWeek1() {
	return week1;
    }

    public void setActiveDate(Date activeDate) {
	this.activeDate = activeDate;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setDay0(Integer day0) {
	this.day0 = day0;
    }

    public void setDay1(Integer day1) {
	this.day1 = day1;
    }

    public void setDay2(Integer day2) {
	this.day2 = day2;
    }

    public void setDay3(Integer day3) {
	this.day3 = day3;
    }

    public void setDay4(Integer day4) {
	this.day4 = day4;
    }

    public void setDay5(Integer day5) {
	this.day5 = day5;
    }

    public void setDay6(Integer day6) {
	this.day6 = day6;
    }

    public void setDay7(Integer day7) {
	this.day7 = day7;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setMonth1(Integer month1) {
	this.month1 = month1;
    }

    public void setWeek1(Integer week1) {
	this.week1 = week1;
    }

    public String getPtId() {
        return ptId;
    }

    public void setPtId(String ptId) {
        this.ptId = ptId;
    }

    public Integer getWeek2() {
        return week2;
    }

    public void setWeek2(Integer week2) {
        this.week2 = week2;
    }
}
