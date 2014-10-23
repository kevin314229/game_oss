package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 获取符文数据分析
 * 
 * @author Administrator
 * 
 */
public class OssZAmuletDay implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 撰写总次数 */
    private Integer amuletCount;
    /** 主键ID */
    private Integer amuletId;
    /** 撰写符文人数 */
    private Integer amuletNub;
    /** 获得蓝色符文次数 */
    private Integer blueNub;
    /** 创建时间 */
    private Date createTime;
    /** 日期 */
    private Date dateTime;
    /** 获得符文精华次数 */
    private Integer essenceNub;
    /** 获得金色符文次数 */
    private Integer goldenNub;
    /** 获得绿色符文次数 */
    private Integer greenNub;
    /** 邀请符文大师次数 */
    private Integer inviteNub;
    /** 最后修改时间 */
    private Date modifyTime;
    /** 获得紫色符文次数 */
    private Integer purpleNub;
    /** 消耗金币 */
    private Integer silverNub;

    public OssZAmuletDay() {
    }

    public Integer getAmuletCount() {
	return amuletCount;
    }

    public Integer getAmuletId() {
	return amuletId;
    }

    public Integer getAmuletNub() {
	return amuletNub;
    }

    public Integer getBlueNub() {
	return blueNub;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Date getDateTime() {
	return dateTime;
    }

    public Integer getEssenceNub() {
	return essenceNub;
    }

    public Integer getGoldenNub() {
	return goldenNub;
    }

    public Integer getGreenNub() {
	return greenNub;
    }

    public Integer getInviteNub() {
	return inviteNub;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public Integer getPurpleNub() {
	return purpleNub;
    }

    public Integer getSilverNub() {
	return silverNub;
    }

    public void setAmuletCount(Integer amuletCount) {
	this.amuletCount = amuletCount;
    }

    public void setAmuletId(Integer amuletId) {
	this.amuletId = amuletId;
    }

    public void setAmuletNub(Integer amuletNub) {
	this.amuletNub = amuletNub;
    }

    public void setBlueNub(Integer blueNub) {
	this.blueNub = blueNub;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
    }

    public void setEssenceNub(Integer essenceNub) {
	this.essenceNub = essenceNub;
    }

    public void setGoldenNub(Integer goldenNub) {
	this.goldenNub = goldenNub;
    }

    public void setGreenNub(Integer greenNub) {
	this.greenNub = greenNub;
    }

    public void setInviteNub(Integer inviteNub) {
	this.inviteNub = inviteNub;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

    public void setPurpleNub(Integer purpleNub) {
	this.purpleNub = purpleNub;
    }

    public void setSilverNub(Integer silverNub) {
	this.silverNub = silverNub;
    }

}
