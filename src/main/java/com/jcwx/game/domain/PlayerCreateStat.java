package com.jcwx.game.domain;

import java.io.Serializable;

public class PlayerCreateStat implements Serializable {

    private static final long serialVersionUID = -4943171518774545592L;

    /** 平台标识 */
    private String carrierOperator;

    /** 人数 */
    private Integer countNum;

    /** 日期 */
    private String dateTime;

    /** 日 */
    private String day;

    /** 小时 */
    private String hour;

    /** 月 */
    private String month;

    /** 年 */
    private String year;

    public String getCarrierOperator() {
	return carrierOperator;
    }

    public Integer getCountNum() {
	return countNum;
    }

    public String getDateTime() {
	return dateTime;
    }

    public String getDay() {
	return day;
    }

    public String getHour() {
	return hour;
    }

    public String getMonth() {
	return month;
    }

    public String getYear() {
	return year;
    }

    public void setCarrierOperator(String carrierOperator) {
	this.carrierOperator = carrierOperator;
    }

    public void setCountNum(Integer countNum) {
	this.countNum = countNum;
    }

    public void setDateTime(String dateTime) {
	this.dateTime = dateTime;
    }

    public void setDay(String day) {
	this.day = day;
    }

    public void setHour(String hour) {
	this.hour = hour;
    }

    public void setMonth(String month) {
	this.month = month;
    }

    public void setYear(String year) {
	this.year = year;
    }

}
