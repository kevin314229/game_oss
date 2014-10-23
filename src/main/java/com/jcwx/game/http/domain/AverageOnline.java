package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * 统计平均在线时长
 * 
 * @author Administrator
 * 
 */
public class AverageOnline implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    // 当天平均在线时长
    private Integer average;
    // 统计时间
    private String createDate;
    // 当天在线角色数
    private String onlinePlayer;
    // 在线人数
    private String onlineUser;

    public Integer getAverage() {
	return average;
    }

    public String getCreateDate() {
	return createDate;
    }

    public String getOnlinePlayer() {
	return onlinePlayer;
    }

    public String getOnlineUser() {
	return onlineUser;
    }

    public void setAverage(Integer average) {
	this.average = average;
    }

    public void setCreateDate(String createDate) {
	this.createDate = createDate;
    }

    public void setOnlinePlayer(String onlinePlayer) {
	this.onlinePlayer = onlinePlayer;
    }

    public void setOnlineUser(String onlineUser) {
	this.onlineUser = onlineUser;
    }

}
