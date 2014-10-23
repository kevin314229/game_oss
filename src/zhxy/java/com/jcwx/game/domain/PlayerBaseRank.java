package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class PlayerBaseRank implements Serializable {
    	private static final long serialVersionUID = 4827004168313597916L;
	/** 游戏ID */
	private Integer gameId;
	/** 游戏大区ID */
	private Integer areaId;
	/** 平台标志ID */
	private String ptId;
	/** 玩家角色ID */
	private Integer playerBaseId;
	/** 玩家昵称 */
	private String nickName;
	/** 游戏职业 */
	private Integer occupation;
	/** 玩家充值余额 */
	private Integer gold;
	/**  */
	private Integer allGold;
	/** 玩家充值余额 */
	private double firstConsume;
	/** 最后消费点 */
	private String operation;
	/** 最后消费时间 */
	private Date consumeTime;
	/** 最后登录时间 */
	private Date loginTime;
	/** 最后登出时间 */
	private Date logoutTime;
	/** 角色创建时间 */
	private Date createTime;
	private Double allMoney;

	private Date firstConsumeTime;
	
	private String firstOperation;
	public PlayerBaseRank(){ }

	public PlayerBaseRank(Integer gameId, Integer areaId, String ptId, Integer playerBaseId, String nickName, Integer occupation, Integer gold, Integer allGold, double firstConsume, String operation, Date consumeTime, Date loginTime, Date logoutTime, Date createTime) {
		this.gameId = gameId;
		this.areaId = areaId;
		this.ptId = ptId;
		this.playerBaseId = playerBaseId;
		this.nickName = nickName;
		this.occupation = occupation;
		this.gold = gold;
		this.allGold = allGold;
		this.firstConsume = firstConsume;
		this.operation = operation;
		this.consumeTime = consumeTime;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.createTime = createTime;
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

	public Integer getPlayerBaseId() {
		return playerBaseId;
	}

	public void setPlayerBaseId(Integer playerBaseId) {
		this.playerBaseId = playerBaseId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getOccupation() {
		return occupation;
	}

	public void setOccupation(Integer occupation) {
		this.occupation = occupation;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getAllGold() {
		return allGold;
	}

	public void setAllGold(Integer allGold) {
		this.allGold = allGold;
	}

	public double getFirstConsume() {
		return firstConsume;
	}

	public void setFirstConsume(double firstConsume) {
		this.firstConsume = firstConsume;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(Date consumeTime) {
		this.consumeTime = consumeTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getAllMoney() {
	    return allMoney;
	}

	public void setAllMoney(Double allMoney) {
	    this.allMoney = allMoney;
	}

	public Date getFirstConsumeTime() {
	    return firstConsumeTime;
	}

	public void setFirstConsumeTime(Date firstConsumeTime) {
	    this.firstConsumeTime = firstConsumeTime;
	}

	public String getFirstOperation() {
	    return firstOperation;
	}

	public void setFirstOperation(String firstOperation) {
	    this.firstOperation = firstOperation;
	}

}