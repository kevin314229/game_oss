package com.jcwx.game.domain;

import com.jcwx.game.common.domain.BaseDomain;

public class PlayerWarehouse extends BaseDomain {

    /** 背包开启的格子数量 */
    private Integer gridNumber;
    /**  */
    private String playerId;
    /**  */
    private String playerWarehouseId;
    /** 银子 */
    private Integer silver;

    public PlayerWarehouse() {
    }

    public PlayerWarehouse(String playerWarehouseId, String playerId,
	    Integer gridNumber, Integer silver) {
	this.playerWarehouseId = playerWarehouseId;
	this.playerId = playerId;
	this.gridNumber = gridNumber;
	this.silver = silver;
    }

    public Integer getGridNumber() {
	exeGet();
	return gridNumber;
    }

    public String getPlayerId() {
	exeGet();
	return playerId;
    }

    public String getPlayerWarehouseId() {
	exeGet();
	return playerWarehouseId;
    }

    public Integer getSilver() {
	exeGet();
	return silver;
    }

    public void setGridNumber(Integer gridNumber) {
	this.gridNumber = gridNumber;
	exeSet();
    }

    public void setPlayerId(String playerId) {
	this.playerId = playerId;
	exeSet();
    }

    public void setPlayerWarehouseId(String playerWarehouseId) {
	this.playerWarehouseId = playerWarehouseId;
	exeSet();
    }

    public void setSilver(Integer silver) {
	this.silver = silver;
	exeSet();
    }

}