package com.jcwx.game.domain;

import com.jcwx.game.common.domain.BaseDomain;

public class BaseEquip extends BaseDomain {

    /** 动作模型 */
    private String actionModule;
    /**  */
    private Integer baseEquipId;
    /** 商店购买价格 */
    private Integer buyPrice;
    /** 装备描述 */
    private String description;
    /** 图标模型 */
    private String iconModule;
    /** 角色等级阶段限制 */
    private Integer levelFlag;
    /**  */
    private String name;
    /** 职业 */
    private Integer occupation;
    /** 品质，1白2绿3蓝4紫5金 */
    private Integer quality;
    /** 是否可以出售 0-可以 1-不可以 */
    private Integer sellFlag;
    /** 商店售价（银两） */
    private Integer sellPrice;
    /** 类型 */
    private Integer type;

    public BaseEquip() {
    }

    public BaseEquip(Integer baseEquipId, String name, Integer type,
	    Integer quality, Integer occupation, Integer levelFlag,
	    Integer sellFlag, Integer buyPrice, Integer sellPrice,
	    String description, String actionModule, String iconModule) {
	this.baseEquipId = baseEquipId;
	this.name = name;
	this.type = type;
	this.quality = quality;
	this.occupation = occupation;
	this.levelFlag = levelFlag;
	this.sellFlag = sellFlag;
	this.buyPrice = buyPrice;
	this.sellPrice = sellPrice;
	this.description = description;
	this.actionModule = actionModule;
	this.iconModule = iconModule;
    }

    public String getActionModule() {
	exeGet();
	return actionModule;
    }

    public Integer getBaseEquipId() {
	exeGet();
	return baseEquipId;
    }

    public Integer getBuyPrice() {
	exeGet();
	return buyPrice;
    }

    public String getDescription() {
	exeGet();
	return description;
    }

    public String getIconModule() {
	exeGet();
	return iconModule;
    }

    public Integer getLevelFlag() {
	exeGet();
	return levelFlag;
    }

    public String getName() {
	exeGet();
	return name;
    }

    public Integer getOccupation() {
	exeGet();
	return occupation;
    }

    public Integer getQuality() {
	exeGet();
	return quality;
    }

    public Integer getSellFlag() {
	exeGet();
	return sellFlag;
    }

    public Integer getSellPrice() {
	exeGet();
	return sellPrice;
    }

    public Integer getType() {
	exeGet();
	return type;
    }

    public void setActionModule(String actionModule) {
	this.actionModule = actionModule;
	exeSet();
    }

    public void setBaseEquipId(Integer baseEquipId) {
	this.baseEquipId = baseEquipId;
	exeSet();
    }

    public void setBuyPrice(Integer buyPrice) {
	this.buyPrice = buyPrice;
	exeSet();
    }

    public void setDescription(String description) {
	this.description = description;
	exeSet();
    }

    public void setIconModule(String iconModule) {
	this.iconModule = iconModule;
	exeSet();
    }

    public void setLevelFlag(Integer levelFlag) {
	this.levelFlag = levelFlag;
	exeSet();
    }

    public void setName(String name) {
	this.name = name;
	exeSet();
    }

    public void setOccupation(Integer occupation) {
	this.occupation = occupation;
	exeSet();
    }

    public void setQuality(Integer quality) {
	this.quality = quality;
	exeSet();
    }

    public void setSellFlag(Integer sellFlag) {
	this.sellFlag = sellFlag;
	exeSet();
    }

    public void setSellPrice(Integer sellPrice) {
	this.sellPrice = sellPrice;
	exeSet();
    }

    public void setType(Integer type) {
	this.type = type;
	exeSet();
    }

}