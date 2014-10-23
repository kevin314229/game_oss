package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class ZArenaDay implements Serializable {

    /** 弓箭手失败场次 */
    private Integer archerLose;
    /** 弓箭手胜利场次 */
    private Integer archerWin;
    /** 消耗战神号角 */
    private Integer consumeLargeHorn;
    /** 消耗号角 */
    private Integer consumeSmartHorn;
    /** 1战意战斗次数 */
    private Integer countFight;
    /** 创建时间 */
    private Date createTime;
    /** 日期 */
    private Date dateTime;
    /** 失败总场次 */
    private Integer failCount;
    /** 主键ID */
    private Integer id;
    /** 法师失败场次 */
    private Integer masterLose;
    /** 法师胜利场次 */
    private Integer masterWin;
    /** 多人匹配真人次数 */
    private Integer mmatchLiveview;
    /** 最后修改时间 */
    private Date modifyTime;
    /** 多人匹配总次数 */
    private Integer multiCountMatch;
    /** 多人匹配总人数 */
    private Integer multiPersonMatch;
    /** 1战意战斗人数 */
    private Integer personFight;
    /** 剩余战神号角 */
    private Integer retentionLargeHorn;
    /** 剩余号角 */
    private Integer retentionSmartHorn;
    /** 单人匹配真人次数 */
    private Integer smatchLiveview;
    /** 3战意战斗次数 */
    private Integer threeCountFight;
    /** 3战意战斗人数 */
    private Integer threePersonFight;
    /** 总次数 */
    private Integer totalCount;
    /** 单人匹配总次数 */
    private Integer totalCountSmatch;
    /** 单人匹配总人数 */
    private Integer totalPersonSmatch;
    /** 战士失败场次 */
    private Integer warriorLose;
    /** 战士胜利场次 */
    private Integer warriorWin;
    /** 胜利总场次 */
    private Integer winCount;

    public ZArenaDay() {
    }

    public ZArenaDay(Integer id, Date dateTime, Integer totalCount,
	    Integer totalPersonSmatch, Integer totalCountSmatch,
	    Integer smatchLiveview, Integer multiPersonMatch,
	    Integer multiCountMatch, Integer mmatchLiveview,
	    Integer personFight, Integer countFight, Integer threePersonFight,
	    Integer threeCountFight, Integer winCount, Integer failCount,
	    Integer warriorWin, Integer archerWin, Integer masterWin,
	    Integer warriorLose, Integer archerLose, Integer masterLose,
	    Integer consumeLargeHorn, Integer consumeSmartHorn,
	    Integer retentionLargeHorn, Integer retentionSmartHorn,
	    Date createTime, Date modifyTime) {
	this.id = id;
	this.dateTime = dateTime;
	this.totalCount = totalCount;
	this.totalPersonSmatch = totalPersonSmatch;
	this.totalCountSmatch = totalCountSmatch;
	this.smatchLiveview = smatchLiveview;
	this.multiPersonMatch = multiPersonMatch;
	this.multiCountMatch = multiCountMatch;
	this.mmatchLiveview = mmatchLiveview;
	this.personFight = personFight;
	this.countFight = countFight;
	this.threePersonFight = threePersonFight;
	this.threeCountFight = threeCountFight;
	this.winCount = winCount;
	this.failCount = failCount;
	this.warriorWin = warriorWin;
	this.archerWin = archerWin;
	this.masterWin = masterWin;
	this.warriorLose = warriorLose;
	this.archerLose = archerLose;
	this.masterLose = masterLose;
	this.consumeLargeHorn = consumeLargeHorn;
	this.consumeSmartHorn = consumeSmartHorn;
	this.retentionLargeHorn = retentionLargeHorn;
	this.retentionSmartHorn = retentionSmartHorn;
	this.createTime = createTime;
	this.modifyTime = modifyTime;
    }

    public Integer getArcherLose() {
	return archerLose;
    }

    public Integer getArcherWin() {
	return archerWin;
    }

    public Integer getConsumeLargeHorn() {
	return consumeLargeHorn;
    }

    public Integer getConsumeSmartHorn() {
	return consumeSmartHorn;
    }

    public Integer getCountFight() {
	return countFight;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Date getDateTime() {
	return dateTime;
    }

    public Integer getFailCount() {
	return failCount;
    }

    public Integer getId() {
	return id;
    }

    public Integer getMasterLose() {
	return masterLose;
    }

    public Integer getMasterWin() {
	return masterWin;
    }

    public Integer getMmatchLiveview() {
	return mmatchLiveview;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public Integer getMultiCountMatch() {
	return multiCountMatch;
    }

    public Integer getMultiPersonMatch() {
	return multiPersonMatch;
    }

    public Integer getPersonFight() {
	return personFight;
    }

    public Integer getRetentionLargeHorn() {
	return retentionLargeHorn;
    }

    public Integer getRetentionSmartHorn() {
	return retentionSmartHorn;
    }

    public Integer getSmatchLiveview() {
	return smatchLiveview;
    }

    public Integer getThreeCountFight() {
	return threeCountFight;
    }

    public Integer getThreePersonFight() {
	return threePersonFight;
    }

    public Integer getTotalCount() {
	return totalCount;
    }

    public Integer getTotalCountSmatch() {
	return totalCountSmatch;
    }

    public Integer getTotalPersonSmatch() {
	return totalPersonSmatch;
    }

    public Integer getWarriorLose() {
	return warriorLose;
    }

    public Integer getWarriorWin() {
	return warriorWin;
    }

    public Integer getWinCount() {
	return winCount;
    }

    public void setArcherLose(Integer archerLose) {
	this.archerLose = archerLose;
    }

    public void setArcherWin(Integer archerWin) {
	this.archerWin = archerWin;
    }

    public void setConsumeLargeHorn(Integer consumeLargeHorn) {
	this.consumeLargeHorn = consumeLargeHorn;
    }

    public void setConsumeSmartHorn(Integer consumeSmartHorn) {
	this.consumeSmartHorn = consumeSmartHorn;
    }

    public void setCountFight(Integer countFight) {
	this.countFight = countFight;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
    }

    public void setFailCount(Integer failCount) {
	this.failCount = failCount;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setMasterLose(Integer masterLose) {
	this.masterLose = masterLose;
    }

    public void setMasterWin(Integer masterWin) {
	this.masterWin = masterWin;
    }

    public void setMmatchLiveview(Integer mmatchLiveview) {
	this.mmatchLiveview = mmatchLiveview;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

    public void setMultiCountMatch(Integer multiCountMatch) {
	this.multiCountMatch = multiCountMatch;
    }

    public void setMultiPersonMatch(Integer multiPersonMatch) {
	this.multiPersonMatch = multiPersonMatch;
    }

    public void setPersonFight(Integer personFight) {
	this.personFight = personFight;
    }

    public void setRetentionLargeHorn(Integer retentionLargeHorn) {
	this.retentionLargeHorn = retentionLargeHorn;
    }

    public void setRetentionSmartHorn(Integer retentionSmartHorn) {
	this.retentionSmartHorn = retentionSmartHorn;
    }

    public void setSmatchLiveview(Integer smatchLiveview) {
	this.smatchLiveview = smatchLiveview;
    }

    public void setThreeCountFight(Integer threeCountFight) {
	this.threeCountFight = threeCountFight;
    }

    public void setThreePersonFight(Integer threePersonFight) {
	this.threePersonFight = threePersonFight;
    }

    public void setTotalCount(Integer totalCount) {
	this.totalCount = totalCount;
    }

    public void setTotalCountSmatch(Integer totalCountSmatch) {
	this.totalCountSmatch = totalCountSmatch;
    }

    public void setTotalPersonSmatch(Integer totalPersonSmatch) {
	this.totalPersonSmatch = totalPersonSmatch;
    }

    public void setWarriorLose(Integer warriorLose) {
	this.warriorLose = warriorLose;
    }

    public void setWarriorWin(Integer warriorWin) {
	this.warriorWin = warriorWin;
    }

    public void setWinCount(Integer winCount) {
	this.winCount = winCount;
    }

}