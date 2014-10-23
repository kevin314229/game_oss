package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class ZCopynubDay implements Serializable {

    /** 参与总人数 */
    private Integer allNum;
    /**  */
    private Date createTime;
    /** 日期 */
    private Date dateTime;
    /** 主键Id */
    private Integer id;
    /**  */
    private Date modifyTime;
    /** 单人开启数量 */
    private Integer singleNum;
    /** 副本开启数量 */
    private Integer startNum;
    /** 三人开启数量 */
    private Integer threeNum;
    /** 两人开启数量 */
    private Integer twoNum;

    public ZCopynubDay() {
    }

    public ZCopynubDay(Integer id, Date dateTime, Integer startNum,
	    Integer singleNum, Integer twoNum, Integer threeNum,
	    Integer allNum, Date createTime, Date modifyTime) {
	this.id = id;
	this.dateTime = dateTime;
	this.startNum = startNum;
	this.singleNum = singleNum;
	this.twoNum = twoNum;
	this.threeNum = threeNum;
	this.allNum = allNum;
	this.createTime = createTime;
	this.modifyTime = modifyTime;
    }

    public Integer getAllNum() {
	return allNum;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Date getDateTime() {
	return dateTime;
    }

    public Integer getId() {
	return id;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public Integer getSingleNum() {
	return singleNum;
    }

    public Integer getStartNum() {
	return startNum;
    }

    public Integer getThreeNum() {
	return threeNum;
    }

    public Integer getTwoNum() {
	return twoNum;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

    public void setSingleNum(Integer singleNum) {
	this.singleNum = singleNum;
    }

    public void setStartNum(Integer startNum) {
	this.startNum = startNum;
    }

    public void setThreeNum(Integer threeNum) {
	this.threeNum = threeNum;
    }

    public void setTwoNum(Integer twoNum) {
	this.twoNum = twoNum;
    }

}