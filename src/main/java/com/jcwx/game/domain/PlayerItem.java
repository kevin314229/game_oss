package com.jcwx.game.domain;

import java.util.Date;

import com.jcwx.game.common.domain.BaseDomain;

public class PlayerItem extends BaseDomain {

    /** 物品CD时间 */
    private Integer cd;
    /** 物品所在CD组别 */
    private Integer cdGroup;
    /** 0-未绑定，1-已绑定 */
    private Integer isBind;
    /** 物品ID */
    private Integer itemId;
    /** 物品等级 */
    private Integer itemLevel;
    /** 物品数量 */
    private Integer itemNumber;
    /** 物品所在地：1，背包；2，装备面板；3，仓库。 11，回购面板 */
    private Integer itemPlace;
    /** 物品位置号码 */
    private Integer itemPosition;
    /** 物品类型1-装备， 2-道具 */
    private Integer itemType;
    /**  */
    private String name;
    /**  */
    private Integer playerBaseId;
    /** 装备属性对象 **/
    private PlayerEquipProp playerEquipProp;
    /**  */
    private Integer playerItemId;
    /** 物品卖出时间 */
    private Date sellTime;
    /** 物品状态0-正常，1-cd不可用 */
    private Integer state;
    /** 1、商城物品、2、武器、装备、3、药品4、配方、材料5、任务物品 */
    private Integer subclass;

    public PlayerItem() {
    }

    public PlayerItem(Integer playerItemId, Integer playerBaseId,
	    Integer itemId, String name, Integer itemType,
	    Integer itemPosition, Integer itemPlace, Integer state,
	    Integer itemNumber, Integer itemLevel, Integer subclass,
	    Integer isBind, Date sellTime, Integer cd, Integer cdGroup) {
	this.playerItemId = playerItemId;
	this.playerBaseId = playerBaseId;
	this.itemId = itemId;
	this.name = name;
	this.itemType = itemType;
	this.itemPosition = itemPosition;
	this.itemPlace = itemPlace;
	this.state = state;
	this.itemNumber = itemNumber;
	this.itemLevel = itemLevel;
	this.subclass = subclass;
	this.isBind = isBind;
	this.sellTime = sellTime;
	this.cd = cd;
	this.cdGroup = cdGroup;
    }

    public Integer getCd() {
	exeGet();
	return cd;
    }

    public Integer getCdGroup() {
	exeGet();
	return cdGroup;
    }

    public Integer getIsBind() {
	exeGet();
	return isBind;
    }

    public Integer getItemId() {
	exeGet();
	return itemId;
    }

    public Integer getItemLevel() {
	exeGet();
	return itemLevel;
    }

    public Integer getItemNumber() {
	exeGet();
	return itemNumber;
    }

    public Integer getItemPlace() {
	exeGet();
	return itemPlace;
    }

    public Integer getItemPosition() {
	exeGet();
	return itemPosition;
    }

    public Integer getItemType() {
	exeGet();
	return itemType;
    }

    public String getName() {
	exeGet();
	return name;
    }

    public Integer getPlayerBaseId() {
	exeGet();
	return playerBaseId;
    }

    public PlayerEquipProp getPlayerEquipProp() {
	return playerEquipProp;
    }

    public Integer getPlayerItemId() {
	exeGet();
	return playerItemId;
    }

    public Date getSellTime() {
	exeGet();
	return sellTime;
    }

    public Integer getState() {
	exeGet();
	return state;
    }

    public Integer getSubclass() {
	exeGet();
	return subclass;
    }

    public void setCd(Integer cd) {
	this.cd = cd;
	exeSet();
    }

    public void setCdGroup(Integer cdGroup) {
	this.cdGroup = cdGroup;
	exeSet();
    }

    public void setIsBind(Integer isBind) {
	this.isBind = isBind;
	exeSet();
    }

    public void setItemId(Integer itemId) {
	this.itemId = itemId;
	exeSet();
    }

    public void setItemLevel(Integer itemLevel) {
	this.itemLevel = itemLevel;
	exeSet();
    }

    public void setItemNumber(Integer itemNumber) {
	this.itemNumber = itemNumber;
	exeSet();
    }

    public void setItemPlace(Integer itemPlace) {
	this.itemPlace = itemPlace;
	exeSet();
    }

    public void setItemPosition(Integer itemPosition) {
	this.itemPosition = itemPosition;
	exeSet();
    }

    public void setItemType(Integer itemType) {
	this.itemType = itemType;
	exeSet();
    }

    public void setName(String name) {
	this.name = name;
	exeSet();
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
	exeSet();
    }

    public void setPlayerEquipProp(PlayerEquipProp playerEquipProp) {
	this.playerEquipProp = playerEquipProp;
    }

    public void setPlayerItemId(Integer playerItemId) {
	this.playerItemId = playerItemId;
	exeSet();
    }

    public void setSellTime(Date sellTime) {
	this.sellTime = sellTime;
	exeSet();
    }

    public void setState(Integer state) {
	this.state = state;
	exeSet();
    }

    public void setSubclass(Integer subclass) {
	this.subclass = subclass;
	exeSet();
    }

}