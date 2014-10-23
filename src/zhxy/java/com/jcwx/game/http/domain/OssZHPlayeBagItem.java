package com.jcwx.game.http.domain;

public class OssZHPlayeBagItem {

    /** 物品等级 */
    private Integer itemLevel;
    /** 物品数量 */
    private Integer itemNumber;
    /** 物品所在地：1，背包；2，装备面板；3，仓库。;4,邮件； 11，回购面板 */
    private Integer itemPlace;
    /** 物品类型1-装备， 2-道具 */
    private Integer itemType;
    /**  */
    private String name;

    public Integer getItemLevel() {
	return itemLevel;
    }

    public Integer getItemNumber() {
	return itemNumber;
    }

    public Integer getItemPlace() {
	return itemPlace;
    }

    public Integer getItemType() {
	return itemType;
    }

    public String getName() {
	return name;
    }

    public void setItemLevel(Integer itemLevel) {
	this.itemLevel = itemLevel;
    }

    public void setItemNumber(Integer itemNumber) {
	this.itemNumber = itemNumber;
    }

    public void setItemPlace(Integer itemPlace) {
	this.itemPlace = itemPlace;
    }

    public void setItemType(Integer itemType) {
	this.itemType = itemType;
    }

    public void setName(String name) {
	this.name = name;
    }

}
