package com.jcwx.game.domain;

import java.util.Date;

import com.jcwx.game.common.domain.BaseDomain;

public class ZKeep extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /** 游戏运营商 */
    private String carrierOperator;
    /** 基础数据，相当天注册人数 */
    private Integer day1;
    /**  */
    private Integer day14;
    /** 隔天留存人数 */
    private Integer day2;
    /**  */
    private Integer day3;
    /**  */
    private Integer day30;
    /**  */
    private Integer day4;
    /**  */
    private Integer day5;
    /**  */
    private Integer day6;
    /**  */
    private Integer day7;
    /**  */
    private Integer day8;
    /** 时间 */
    private Date id;

    /** 第1天新增的充值人数 */
    private Integer newPayDay1;
    /**  */
    private Integer newPayDay14;
    /** 隔天新增的充值人数 */
    private Integer newPayDay2;
    /** 第三天新增的充值人数(首充) */
    private Integer newPayDay3;
    /**  */
    private Integer newPayDay30;
    /**  */
    private Integer newPayDay4;
    /**  */
    private Integer newPayDay5;
    /**  */
    private Integer newPayDay6;
    /**  */
    private Integer newPayDay7;

    public ZKeep() {
    }

    public ZKeep(Date id, Integer day1, Integer day2, Integer day3,
	    Integer day4, Integer day5, Integer day6, Integer day7,
	    Integer day14, Integer day30, Integer newPayDay2,
	    Integer newPayDay3, Integer newPayDay4, Integer newPayDay5,
	    Integer newPayDay6, Integer newPayDay7, Integer newPayDay14,
	    Integer newPayDay30) {
	this.id = id;
	this.day1 = day1;
	this.day2 = day2;
	this.day3 = day3;
	this.day4 = day4;
	this.day5 = day5;
	this.day6 = day6;
	this.day7 = day7;
	this.day14 = day14;
	this.day30 = day30;
	this.newPayDay2 = newPayDay2;
	this.newPayDay3 = newPayDay3;
	this.newPayDay4 = newPayDay4;
	this.newPayDay5 = newPayDay5;
	this.newPayDay6 = newPayDay6;
	this.newPayDay7 = newPayDay7;
	this.newPayDay14 = newPayDay14;
	this.newPayDay30 = newPayDay30;
    }

    public String getCarrierOperator() {
	return carrierOperator;
    }

    public Integer getDay1() {
	return day1;
    }

    public Integer getDay14() {
	return day14;
    }

    public Integer getDay2() {
	return day2;
    }

    public Integer getDay3() {
	return day3;
    }

    public Integer getDay30() {
	return day30;
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

    public Integer getDay8() {
	return day8;
    }

    public Date getId() {
	return id;
    }

    public Integer getNewPayDay1() {
	return newPayDay1;
    }

    public Integer getNewPayDay14() {
	return newPayDay14;
    }

    public Integer getNewPayDay2() {
	return newPayDay2;
    }

    public Integer getNewPayDay3() {
	return newPayDay3;
    }

    public Integer getNewPayDay30() {
	return newPayDay30;
    }

    public Integer getNewPayDay4() {
	return newPayDay4;
    }

    public Integer getNewPayDay5() {
	return newPayDay5;
    }

    public Integer getNewPayDay6() {
	return newPayDay6;
    }

    public Integer getNewPayDay7() {
	return newPayDay7;
    }

    public void setCarrierOperator(String carrierOperator) {
	this.carrierOperator = carrierOperator;
	exeSet();
    }

    public void setDay1(Integer day1) {
	this.day1 = day1;
	exeSet();
    }

    public void setDay14(Integer day14) {
	this.day14 = day14;
	exeSet();
    }

    public void setDay2(Integer day2) {
	this.day2 = day2;
	exeSet();
    }

    public void setDay3(Integer day3) {
	this.day3 = day3;
	exeSet();
    }

    public void setDay30(Integer day30) {
	this.day30 = day30;
	exeSet();
    }

    public void setDay4(Integer day4) {
	this.day4 = day4;
	exeSet();
    }

    public void setDay5(Integer day5) {
	this.day5 = day5;
	exeSet();
    }

    public void setDay6(Integer day6) {
	this.day6 = day6;
	exeSet();
    }

    public void setDay7(Integer day7) {
	this.day7 = day7;
	exeSet();
    }

    public void setDay8(Integer day8) {
	this.day8 = day8;
    }

    public void setId(Date id) {
	this.id = id;
	exeSet();
    }

    public void setNewPayDay1(Integer newPayDay1) {
	this.newPayDay1 = newPayDay1;
	exeSet();
    }

    public void setNewPayDay14(Integer newPayDay14) {
	this.newPayDay14 = newPayDay14;
	exeSet();
    }

    public void setNewPayDay2(Integer newPayDay2) {
	this.newPayDay2 = newPayDay2;
	exeSet();
    }

    public void setNewPayDay3(Integer newPayDay3) {
	this.newPayDay3 = newPayDay3;
	exeSet();
    }

    public void setNewPayDay30(Integer newPayDay30) {
	this.newPayDay30 = newPayDay30;
	exeSet();
    }

    public void setNewPayDay4(Integer newPayDay4) {
	this.newPayDay4 = newPayDay4;
	exeSet();
    }

    public void setNewPayDay5(Integer newPayDay5) {
	this.newPayDay5 = newPayDay5;
	exeSet();
    }

    public void setNewPayDay6(Integer newPayDay6) {
	this.newPayDay6 = newPayDay6;
	exeSet();
    }

    public void setNewPayDay7(Integer newPayDay7) {
	this.newPayDay7 = newPayDay7;
	exeSet();
    }

}