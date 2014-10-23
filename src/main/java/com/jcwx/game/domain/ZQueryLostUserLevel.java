package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class ZQueryLostUserLevel implements Serializable {

    private Integer areaId;
    private String finalString;
    private Integer gameId;
    private Date statisticsDate;

    public Integer getAreaId() {
	return areaId;
    }

    public String getFinalString() {
	return finalString;
    }

    public Integer getGameId() {
	return gameId;
    }

    public Date getStatisticsDate() {
	return statisticsDate;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setFinalString(String finalString) {
	this.finalString = finalString;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setStatisticsDate(Date statisticsDate) {
	this.statisticsDate = statisticsDate;
    }

}
