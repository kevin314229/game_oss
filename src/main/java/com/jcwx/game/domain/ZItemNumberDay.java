package com.jcwx.game.domain;

import java.io.Serializable;

/**
 * 
 * @ClassName: ZItemNumberDay
 * @Description: 玩家背包表
 * @author liushang 364173778@qq.com
 * @date 2013年12月9日 下午2:38:41
 * 
 */
@SuppressWarnings("serial")
public class ZItemNumberDay implements Serializable {

    /** 背包物品ID */
    private Integer itemId;
    /** 背包物品名称 */
    private String itemName;
    /** 背包物品数量 */
    private Integer itemNumber;
    /** 玩家角色ID */
    private Integer playerBaseId;

    public Integer getItemId() {
	return this.itemId;
    }

    public String getItemName() {
	return this.itemName;
    }

    public Integer getItemNumber() {
	return this.itemNumber;
    }

    public Integer getPlayerBaseId() {
	return this.playerBaseId;
    }

    public void setItemId(Integer itemId) {
	this.itemId = itemId;
    }

    public void setItemName(String itemName) {
	this.itemName = itemName;
    }

    public void setItemNumber(Integer itemNumber) {
	this.itemNumber = itemNumber;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

}