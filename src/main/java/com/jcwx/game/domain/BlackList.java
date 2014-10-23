package com.jcwx.game.domain;

import java.util.Date;

import com.jcwx.game.common.domain.BaseDomain;

public class BlackList extends BaseDomain {

    /** 禁言结束时间 */
    private Date banEndTime;
    /** 查封状态(0:解封状态；1：封禁状态) */
    private Integer banState;
    /** 历史查封次数 */
    private Integer banTime;
    /**  */
    private Integer blackListId;
    /** IP号 */
    private String ip;

    public BlackList() {
    }

    public BlackList(Integer blackListId, String ip, Integer banState,
	    Integer banTime, Date banEndTime) {
	this.blackListId = blackListId;
	this.ip = ip;
	this.banState = banState;
	this.banTime = banTime;
	this.banEndTime = banEndTime;
    }

    public Date getBanEndTime() {
	exeGet();
	return banEndTime;
    }

    public Integer getBanState() {
	exeGet();
	return banState;
    }

    public Integer getBanTime() {
	exeGet();
	return banTime;
    }

    public Integer getBlackListId() {
	exeGet();
	return blackListId;
    }

    public String getIp() {
	exeGet();
	return ip;
    }

    public void setBanEndTime(Date banEndTime) {
	this.banEndTime = banEndTime;
	exeSet();
    }

    public void setBanState(Integer banState) {
	this.banState = banState;
	exeSet();
    }

    public void setBanTime(Integer banTime) {
	this.banTime = banTime;
	exeSet();
    }

    public void setBlackListId(Integer blackListId) {
	this.blackListId = blackListId;
	exeSet();
    }

    public void setIp(String ip) {
	this.ip = ip;
	exeSet();
    }

}