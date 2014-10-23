package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

public class OssZOnlineDay implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 创建时间 */
    private Date createTime;
    /** 统计日期 */
    private Date dateTime;
    /** 当天领取5挡奖励人数 */
    private Integer fiveNub;
    /** 当天领取4挡奖励人数 */
    private Integer fourNub;
    /** 最后修改时间 */
    private Date modifyTime;
    /** 当天领取1挡奖励人数 */
    private Integer oneNub;
    /** 主键Id */
    private Integer onlineDayId;
    /** 当天登录人数 */
    private Integer onlineNub;
    /** 当天签到人数 */
    private Integer signNub;
    /** 当天领取3挡奖励人数 */
    private Integer threeNub;
    /** 当天领取2挡奖励人数 */
    private Integer twoNub;

    public OssZOnlineDay() {
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Date getDateTime() {
	return dateTime;
    }

    public Integer getFiveNub() {
	return fiveNub;
    }

    public Integer getFourNub() {
	return fourNub;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public Integer getOneNub() {
	return oneNub;
    }

    public Integer getOnlineDayId() {
	return onlineDayId;
    }

    public Integer getOnlineNub() {
	return onlineNub;
    }

    public Integer getSignNub() {
	return signNub;
    }

    public Integer getThreeNub() {
	return threeNub;
    }

    public Integer getTwoNub() {
	return twoNub;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
    }

    public void setFiveNub(Integer fiveNub) {
	this.fiveNub = fiveNub;
    }

    public void setFourNub(Integer fourNub) {
	this.fourNub = fourNub;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

    public void setOneNub(Integer oneNub) {
	this.oneNub = oneNub;
    }

    public void setOnlineDayId(Integer onlineDayId) {
	this.onlineDayId = onlineDayId;
    }

    public void setOnlineNub(Integer onlineNub) {
	this.onlineNub = onlineNub;
    }

    public void setSignNub(Integer signNub) {
	this.signNub = signNub;
    }

    public void setThreeNub(Integer threeNub) {
	this.threeNub = threeNub;
    }

    public void setTwoNub(Integer twoNub) {
	this.twoNub = twoNub;
    }
}
