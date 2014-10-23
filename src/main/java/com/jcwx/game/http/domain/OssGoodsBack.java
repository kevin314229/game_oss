package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 物品回滚 oss
 * 
 * @author Administrator
 * 
 */
public class OssGoodsBack implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String createUserId;
    /** 主键ID */
    private Integer goodsBackId;
    /** 物品数量 */
    private Integer itemNub;
    /** 修改时间 */
    private Date modifyTime;
    /** 玩家角色ID */
    private Integer playerBaseId;
    /** 玩家ID */
    private Integer playerId;
    /** 物品类型 */
    private Integer type;

    public OssGoodsBack() {
    }

    public Date getCreateTime() {
	return createTime;
    }

    public String getCreateUserId() {
	return createUserId;
    }

    public Integer getGoodsBackId() {
	return goodsBackId;
    }

    public Integer getItemNub() {
	return itemNub;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public Integer getType() {
	return type;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setCreateUserId(String createUserId) {
	this.createUserId = createUserId;
    }

    public void setGoodsBackId(Integer goodsBackId) {
	this.goodsBackId = goodsBackId;
    }

    public void setItemNub(Integer itemNub) {
	this.itemNub = itemNub;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setType(Integer type) {
	this.type = type;
    }

}
