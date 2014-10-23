package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 召唤日常数据分析
 * 
 * @author 小平 2013-11-1
 */
public class OssZStarDay implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 消耗礼券总数 */
    private Integer bindGoldNum;
    /** 召唤次数 */
    private Integer callCount;
    /** 普通改运次数 */
    private Integer commonCount;
    /** 创建时间 */
    private Date createTime;
    /** 统计日期 */
    private Date dateTime;
    /** 逆天改运次数 */
    private Integer godCount;
    /** 消耗魔晶总数 */
    private Integer goldNum;
    /** 最后修改时间 */
    private Date modifyTime;
    /** 主键Id */
    private Integer starId;
    /** 参与人数 */
    private Integer starNub;
    /** 产生星石总数 */
    private Integer starNum;

    public Integer getBindGoldNum() {
	return bindGoldNum;
    }

    public Integer getCallCount() {
	return callCount;
    }

    public Integer getCommonCount() {
	return commonCount;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Date getDateTime() {
	return dateTime;
    }

    public Integer getGodCount() {
	return godCount;
    }

    public Integer getGoldNum() {
	return goldNum;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public Integer getStarId() {
	return starId;
    }

    public Integer getStarNub() {
	return starNub;
    }

    public Integer getStarNum() {
	return starNum;
    }

    public void setBindGoldNum(Integer bindGoldNum) {
	this.bindGoldNum = bindGoldNum;
    }

    public void setCallCount(Integer callCount) {
	this.callCount = callCount;
    }

    public void setCommonCount(Integer commonCount) {
	this.commonCount = commonCount;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
    }

    public void setGodCount(Integer godCount) {
	this.godCount = godCount;
    }

    public void setGoldNum(Integer goldNum) {
	this.goldNum = goldNum;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

    public void setStarId(Integer starId) {
	this.starId = starId;
    }

    public void setStarNub(Integer starNub) {
	this.starNub = starNub;
    }

    public void setStarNum(Integer starNum) {
	this.starNum = starNum;
    }

}
