package com.jcwx.game.domain;

import java.util.Date;

import com.jcwx.game.common.domain.BaseDomain;

public class StaMallRecord  extends BaseDomain {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	/** 主键ID */
	private Integer mallRecordId;
	/** 游戏ID */
	private Integer gameId;
	/** 大区ID */
	private Integer areaId;
	/** 平台标识 */
	private String ptId;
	/** 商品ID */
	private Integer goodId;
	/** 玩家Id */
	private Integer playerId;
	/** 角色ID */
	private Integer playerBaseId;
	/** 商品价格 */
	private double price;
	/** 购买数量 */
	private Integer number;
	/** 花费魔晶数 */
	private double goldSum;
	/** 0-普通价格  1- vip价格  2-神秘商店价格 */
	private Integer type;
	/** 创建时间 */
    private Date createTime;
	/** 修改时间 */
	private Date moldfyTime;

	public StaMallRecord(){ }

	public StaMallRecord(Integer mallRecordId, Integer gameId, Integer areaId, String ptId, Integer goodId, Integer playerId, Integer playerBaseId, double price, Integer number, double goldSum, Integer type, Date createTime, Date moldfyTime) {
		this.mallRecordId = mallRecordId;
		this.gameId = gameId;
		this.areaId = areaId;
		this.ptId = ptId;
		this.goodId = goodId;
		this.playerId = playerId;
		this.playerBaseId = playerBaseId;
		this.price = price;
		this.number = number;
		this.goldSum = goldSum;
		this.type = type;
		this.createTime = createTime;
		this.moldfyTime = moldfyTime;
	}
	

	public Integer getMallRecordId() {
		return mallRecordId;
	}

	public void setMallRecordId(Integer mallRecordId) {
		this.mallRecordId = mallRecordId;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getPtId() {
		return ptId;
	}

	public void setPtId(String ptId) {
		this.ptId = ptId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public Integer getPlayerBaseId() {
		return playerBaseId;
	}

	public void setPlayerBaseId(Integer playerBaseId) {
		this.playerBaseId = playerBaseId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public double getGoldSum() {
		return goldSum;
	}

	public void setGoldSum(double goldSum) {
		this.goldSum = goldSum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getMoldfyTime() {
		return moldfyTime;
	}

	public void setMoldfyTime(Date moldfyTime) {
		this.moldfyTime = moldfyTime;
	}


}
