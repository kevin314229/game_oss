package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

public class ZQueryActiveUser implements Serializable {

    private Integer day1;
    private Integer day2;
    private Integer day3;
    private Integer day4;
    private Integer day5;
    private Integer day6;
    private Integer day7;
    private Date loginTime;
    private Integer month;
    private Integer week;

    public ZQueryActiveUser() {

    }

    public ZQueryActiveUser(Date loginTime, Integer day1, Integer day2,
	    Integer day3, Integer day4, Integer day5, Integer day6,
	    Integer day7, Integer week, Integer month) {
	this.loginTime = loginTime;
	this.day1 = day1;
	this.day2 = day2;
	this.day3 = day3;
	this.day4 = day4;
	this.day5 = day5;
	this.day6 = day6;
	this.day7 = day7;
	this.week = week;
	this.month = month;
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

    public Date getLoginTime() {
	return loginTime;
    }

    public Integer getMonth() {
	return month;
    }

    public Integer getWeek() {
	return week;
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

    public void setLoginTime(Date loginTime) {
	this.loginTime = loginTime;
    }

    public void setMonth(Integer month) {
	this.month = month;
    }

    public void setWeek(Integer week) {
	this.week = week;
    }

}
