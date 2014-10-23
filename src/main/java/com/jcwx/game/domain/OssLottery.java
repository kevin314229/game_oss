package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class OssLottery implements Serializable {
    	private static final long serialVersionUID = -7198987377701487879L;
	/**  */
	private Integer lotteryId;
	/**  */
	private Integer itemId;
	/**  */
	private Integer itemNum;
	/**  */
	private Integer areaId;
	/**  */
	private String loginName;
	/**  */
	private String playerBaseId;
	/**  */
	private String gameCode;
	/**  */
	private String activityCode;
	/**  */
	private String serialNo;
	/**  */
	private Date createTime;
	
	private String title;
	
	private String content;

	public OssLottery(){ }

	public OssLottery(Integer lotteryId, Integer itemId, Integer itemNum, Integer areaId, String playerId, String playerBaseId, String gameCode, String activityCode, String serialNo, Date createTime) {
		this.lotteryId = lotteryId;
		this.itemId = itemId;
		this.itemNum = itemNum;
		this.areaId = areaId;
		this.playerBaseId = playerBaseId;
		this.gameCode = gameCode;
		this.activityCode = activityCode;
		this.serialNo = serialNo;
		this.createTime = createTime;
	}
	

	public Integer getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(Integer lotteryId) {
		this.lotteryId = lotteryId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getLoginName() {
	    return loginName;
	}

	public void setLoginName(String loginName) {
	    this.loginName = loginName;
	}

	public String getPlayerBaseId() {
		return playerBaseId;
	}

	public void setPlayerBaseId(String playerBaseId) {
		this.playerBaseId = playerBaseId;
	}

	public String getGameCode() {
		return gameCode;
	}

	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTitle() {
	    return title;
	}

	public void setTitle(String title) {
	    this.title = title;
	}

	public String getContent() {
	    return content;
	}

	public void setContent(String content) {
	    this.content = content;
	}


}