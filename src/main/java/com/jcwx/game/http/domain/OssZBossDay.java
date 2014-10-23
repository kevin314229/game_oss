package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

public class OssZBossDay implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** boss 统计id */
    private Integer bossDayId;
    /** boss 死亡时间 */
    private Date bossOverTime;
    /** 秒CD次数 */
    private Integer cdNub;
    /** 创建时间 */
    private Date createTime;
    /** 魔晶鼓舞次数 */
    private Integer goldNum;
    /** 参与击杀boss人数 */
    private Integer joinNub;
    /** 最后修改时间 */
    private Date modifyTime;
    /** 击杀boss日期 */
    private Date openTime;
    /** 星石鼓舞次数 */
    private Integer starNum;
    /** boss 血量 */
    private Integer totalBossBlood;

    public Integer getBossDayId() {
	return bossDayId;
    }

    public Date getBossOverTime() {
	return bossOverTime;
    }

    public Integer getCdNub() {
	return cdNub;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Integer getGoldNum() {
	return goldNum;
    }

    public Integer getJoinNub() {
	return joinNub;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public Date getOpenTime() {
	return openTime;
    }

    public Integer getStarNum() {
	return starNum;
    }

    public Integer getTotalBossBlood() {
	return totalBossBlood;
    }

    public void setBossDayId(Integer bossDayId) {
	this.bossDayId = bossDayId;
    }

    public void setBossOverTime(Date bossOverTime) {
	this.bossOverTime = bossOverTime;
    }

    public void setCdNub(Integer cdNub) {
	this.cdNub = cdNub;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setGoldNum(Integer goldNum) {
	this.goldNum = goldNum;
    }

    public void setJoinNub(Integer joinNub) {
	this.joinNub = joinNub;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

    public void setOpenTime(Date openTime) {
	this.openTime = openTime;
    }

    public void setStarNum(Integer starNum) {
	this.starNum = starNum;
    }

    public void setTotalBossBlood(Integer totalBossBlood) {
	this.totalBossBlood = totalBossBlood;
    }

}
