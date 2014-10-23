package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;


public class SystemGift implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 唯一id */
	private Long id;
	/** 玩家id列表 */
	private String playerIdList;
	/** 标题 */
	private String title;
	/** 消息内容 */
	private String content;
	/** 礼物 */
	private String gift;
	/** 结束时间 */
	private Date endTime;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;

	public SystemGift(){ }

	public SystemGift(Long id, String playerIdList, String title, String content, String gift, Date endTime, Date createTime, Date updateTime) {
		this.id = id;
		this.playerIdList = playerIdList;
		this.title = title;
		this.content = content;
		this.gift = gift;
		this.endTime = endTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayerIdList() {
		return playerIdList;
	}

	public void setPlayerIdList(String playerIdList) {
		this.playerIdList = playerIdList;
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

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
