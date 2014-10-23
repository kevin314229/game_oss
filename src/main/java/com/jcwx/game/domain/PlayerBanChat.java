package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class PlayerBanChat implements Serializable {

    private static final long serialVersionUID = 8346095129560099634L;

    /** 禁言时间（分钟） */
    private Integer banChatMinus;
    /** 玩家名称 */
    private String nickName;
    private Integer playerBaseId;
    /** 禁言开始时间 */
    private Date startTime;
    /** 系统提示信息 */
    private String sysInfo;

    public Integer getBanChatMinus() {
	return banChatMinus;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Date getStartTime() {
	return startTime;
    }

    public String getSysInfo() {
	return sysInfo;
    }

    public void setBanChatMinus(Integer banChatMinus) {
	this.banChatMinus = banChatMinus;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setStartTime(Date startTime) {
	this.startTime = startTime;
    }

    public void setSysInfo(String sysInfo) {
	this.sysInfo = sysInfo;
    }
}
