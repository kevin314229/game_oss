package com.jcwx.game.domain;

import com.jcwx.game.common.domain.BaseDomain;

public class BaseProperty extends BaseDomain {

    /**  */
    private Integer basePropertyId;
    /** 购买价格 */
    private Integer buyPrice;
    /** cd */
    private Integer cd;
    /** 所属cd组 */
    private Integer cdGroup;
    /** 道具描述 */
    private String description;
    /** 0-不可，1-可以 */
    private Integer discardFlag;
    /** 功能函数 */
    private String function;
    /** 等级限制 */
    private Integer levelFlag;
    /** 最大堆叠 */
    private Integer maxHeap;
    /** 最大使用次数0无限制 */
    private Integer maxUse;
    /** 模型名 */
    private String module;
    /**  */
    private String name;
    /** 体力消耗 */
    private Integer powerConsume;
    /** 品质 */
    private Integer quality;
    /** 出售价格 */
    private Integer sellPrice;
    /** 状态限制0无1死亡2击飞3倒地 */
    private Integer statusFlag;
    /** 道具类型 */
    private Integer type;

    public BaseProperty() {
    }

    public BaseProperty(Integer basePropertyId, String module, String name,
	    Integer type, Integer quality, Integer levelFlag,
	    Integer statusFlag, Integer maxUse, Integer cd, Integer cdGroup,
	    Integer powerConsume, Integer discardFlag, Integer maxHeap,
	    Integer buyPrice, Integer sellPrice, String function,
	    String description) {
	this.basePropertyId = basePropertyId;
	this.module = module;
	this.name = name;
	this.type = type;
	this.quality = quality;
	this.levelFlag = levelFlag;
	this.statusFlag = statusFlag;
	this.maxUse = maxUse;
	this.cd = cd;
	this.cdGroup = cdGroup;
	this.powerConsume = powerConsume;
	this.discardFlag = discardFlag;
	this.maxHeap = maxHeap;
	this.buyPrice = buyPrice;
	this.sellPrice = sellPrice;
	this.function = function;
	this.description = description;
    }

    public Integer getBasePropertyId() {
	exeGet();
	return basePropertyId;
    }

    public Integer getBuyPrice() {
	exeGet();
	return buyPrice;
    }

    public Integer getCd() {
	exeGet();
	return cd;
    }

    public Integer getCdGroup() {
	exeGet();
	return cdGroup;
    }

    public String getDescription() {
	exeGet();
	return description;
    }

    public Integer getDiscardFlag() {
	exeGet();
	return discardFlag;
    }

    public String getFunction() {
	exeGet();
	return function;
    }

    public Integer getLevelFlag() {
	exeGet();
	return levelFlag;
    }

    public Integer getMaxHeap() {
	exeGet();
	return maxHeap;
    }

    public Integer getMaxUse() {
	exeGet();
	return maxUse;
    }

    public String getModule() {
	exeGet();
	return module;
    }

    public String getName() {
	exeGet();
	return name;
    }

    public Integer getPowerConsume() {
	exeGet();
	return powerConsume;
    }

    public Integer getQuality() {
	exeGet();
	return quality;
    }

    public Integer getSellPrice() {
	exeGet();
	return sellPrice;
    }

    public Integer getStatusFlag() {
	exeGet();
	return statusFlag;
    }

    public Integer getType() {
	exeGet();
	return type;
    }

    public void setBasePropertyId(Integer basePropertyId) {
	this.basePropertyId = basePropertyId;
	exeSet();
    }

    public void setBuyPrice(Integer buyPrice) {
	this.buyPrice = buyPrice;
	exeSet();
    }

    public void setCd(Integer cd) {
	this.cd = cd;
	exeSet();
    }

    public void setCdGroup(Integer cdGroup) {
	this.cdGroup = cdGroup;
	exeSet();
    }

    public void setDescription(String description) {
	this.description = description;
	exeSet();
    }

    public void setDiscardFlag(Integer discardFlag) {
	this.discardFlag = discardFlag;
	exeSet();
    }

    public void setFunction(String function) {
	this.function = function;
	exeSet();
    }

    public void setLevelFlag(Integer levelFlag) {
	this.levelFlag = levelFlag;
	exeSet();
    }

    public void setMaxHeap(Integer maxHeap) {
	this.maxHeap = maxHeap;
	exeSet();
    }

    public void setMaxUse(Integer maxUse) {
	this.maxUse = maxUse;
	exeSet();
    }

    public void setModule(String module) {
	this.module = module;
	exeSet();
    }

    public void setName(String name) {
	this.name = name;
	exeSet();
    }

    public void setPowerConsume(Integer powerConsume) {
	this.powerConsume = powerConsume;
	exeSet();
    }

    public void setQuality(Integer quality) {
	this.quality = quality;
	exeSet();
    }

    public void setSellPrice(Integer sellPrice) {
	this.sellPrice = sellPrice;
	exeSet();
    }

    public void setStatusFlag(Integer statusFlag) {
	this.statusFlag = statusFlag;
	exeSet();
    }

    public void setType(Integer type) {
	this.type = type;
	exeSet();
    }

}