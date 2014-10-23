package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 练金日常统计
 * 
 * @author Administrator
 * 
 */
public class OssZMetallurgyDay implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 创建时间 */
    private Date createTime;
    /** 统计日期 */
    private Date dateTime;
    /** 付费练金次数 */
    private Integer goldNub;
    /** 魔晶消耗总数 */
    private Integer goldSum;
    /** 练金次数 */
    private Integer metallurgyCount;
    /** 主键id */
    private Integer metallurgyDayId;
    /** 练金人数 */
    private Integer metallurgyNub;
    /** 最后修改时间 */
    private Date modifyTime;

    public OssZMetallurgyDay() {
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Date getDateTime() {
	return dateTime;
    }

    public Integer getGoldNub() {
	return goldNub;
    }

    public Integer getGoldSum() {
	return goldSum;
    }

    public Integer getMetallurgyCount() {
	return metallurgyCount;
    }

    public Integer getMetallurgyDayId() {
	return metallurgyDayId;
    }

    public Integer getMetallurgyNub() {
	return metallurgyNub;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
    }

    public void setGoldNub(Integer goldNub) {
	this.goldNub = goldNub;
    }

    public void setGoldSum(Integer goldSum) {
	this.goldSum = goldSum;
    }

    public void setMetallurgyCount(Integer metallurgyCount) {
	this.metallurgyCount = metallurgyCount;
    }

    public void setMetallurgyDayId(Integer metallurgyDayId) {
	this.metallurgyDayId = metallurgyDayId;
    }

    public void setMetallurgyNub(Integer metallurgyNub) {
	this.metallurgyNub = metallurgyNub;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

}
