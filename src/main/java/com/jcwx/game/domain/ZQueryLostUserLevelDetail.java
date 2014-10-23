package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class ZQueryLostUserLevelDetail implements Serializable {

    private Integer areaId;
    private Integer gameId;
    private String loginName;
    private String nickName;
    private String playerBaseId;
    private String playerId;
    private Integer playerLevel;
    private Date statisticsDate;

    public Integer getAreaId() {
	return areaId;
    }

    public Integer getGameId() {
	return gameId;
    }

    public String getLoginName() {
	return loginName;
    }

    public String getNickName() {
	return nickName;
    }

    public String getPlayerBaseId() {
	return playerBaseId;
    }

    public String getPlayerId() {
	return playerId;
    }

    public Integer getPlayerLevel() {
	return playerLevel;
    }

    public Date getStatisticsDate() {
	return statisticsDate;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setPlayerBaseId(String playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPlayerId(String playerId) {
	this.playerId = playerId;
    }

    public void setPlayerLevel(Integer playerLevel) {
	this.playerLevel = playerLevel;
    }

    public void setStatisticsDate(Date statisticsDate) {
	this.statisticsDate = statisticsDate;
    }

}
