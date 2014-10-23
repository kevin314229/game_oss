package com.jcwx.game.domain;

import com.jcwx.game.common.domain.BaseDomain;

public class PlayerBag extends BaseDomain {

    /** 背包开启的格子数量 */
    private Integer gridNumber;
    /**  */
    private Integer playerBagId;
    /**  */
    private Integer playerId;

    public PlayerBag() {
    }

    public PlayerBag(Integer playerBagId, Integer playerId, Integer gridNumber) {
	this.playerBagId = playerBagId;
	this.playerId = playerId;
	this.gridNumber = gridNumber;
    }

    public Integer getGridNumber() {
	exeGet();
	return gridNumber;
    }

    public Integer getPlayerBagId() {
	exeGet();
	return playerBagId;
    }

    public Integer getPlayerId() {
	exeGet();
	return playerId;
    }

    public void setGridNumber(Integer gridNumber) {
	this.gridNumber = gridNumber;
	exeSet();
    }

    public void setPlayerBagId(Integer playerBagId) {
	this.playerBagId = playerBagId;
	exeSet();
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
	exeSet();
    }

}