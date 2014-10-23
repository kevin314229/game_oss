package com.jcwx.game.http.domain;

import java.io.Serializable;

public class OssPlayerCopyTimes implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Integer buyTimes;
    /** 副本地图id */
    private Integer copyId;
    private Integer copyType;
    private Integer id;
    /** 玩家id */
    private Integer playerBaseId;
    private Integer times;

    public Integer getBuyTimes() {
	return buyTimes;
    }

    public Integer getCopyId() {
	return copyId;
    }

    public Integer getCopyType() {
	return copyType;
    }

    public Integer getId() {
	return id;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Integer getTimes() {
	return times;
    }

    public void setBuyTimes(Integer buyTimes) {
	this.buyTimes = buyTimes;
    }

    public void setCopyId(Integer copyId) {
	this.copyId = copyId;
    }

    public void setCopyType(Integer copyType) {
	this.copyType = copyType;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setTimes(Integer times) {
	this.times = times;
    }

}
