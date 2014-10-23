package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 商城
 * 
 * @author csp
 * 
 */
public class OssMallNew implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 消耗类型: 1-元宝 ... */
    private Integer costType;
    /** 创建时间 */
    private Date createTime;
    /** 商品描述 */
    private String describe;
    /** 下线时间 */
    private Date endTime;
    /** 商城原价 */
    private Integer initPrice;
    /** 是否显示在商城中 0-不显示 1-显示 */
    private int isShow;
    /** 物品ID */
    private Integer itemId;
    /** 商品名称 */
    private String itemName;
    /** 商城ID */
    private Integer mallNewId;
    /** 商品顺序 */
    private Integer mallOrder;
    /** 商城类型：1-道具、2-宝石、3-礼券、4-时装衣服、5-时装武器 */
    private Integer mallType;
    /** 商城当天出售最大数 */
    private Integer maxNubDay;
    /** 玩家可购买数 */
    private Integer maxNubPlayer;
    /** 最后修改时间 */
    private Date moldfyTime;
    /** 活动促销价 */
    private Integer rebatePrice;
    /** 出售类型: 0-无 1-限时 2-限量 3-限时限量 */
    private Integer sellType;
    /** 上线时间 */
    private Date startTime;
    /** 标签: 0-无 1-新 2-热 3-推荐 4-促销 5-促销推荐 */
    private Integer state;
    /** 当前剩余数量 */
    private Integer surplusNubDay;
    /** VIP价格 */
    private Integer vipPrice;

    public Integer getCostType() {
	return costType;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public String getDescribe() {
	return describe;
    }

    public Date getEndTime() {
	return endTime;
    }

    public Integer getInitPrice() {
	return initPrice;
    }

    public int getIsShow() {
	return isShow;
    }

    public Integer getItemId() {
	return itemId;
    }

    public String getItemName() {
	return itemName;
    }

    public Integer getMallNewId() {
	return mallNewId;
    }

    public Integer getMallOrder() {
	return mallOrder;
    }

    public Integer getMallType() {
	return mallType;
    }

    public Integer getMaxNubDay() {
	return maxNubDay;
    }

    public Integer getMaxNubPlayer() {
	return maxNubPlayer;
    }

    public Date getMoldfyTime() {
	return moldfyTime;
    }

    public Integer getRebatePrice() {
	return rebatePrice;
    }

    public Integer getSellType() {
	return sellType;
    }

    public Date getStartTime() {
	return startTime;
    }

    public Integer getState() {
	return state;
    }

    public Integer getSurplusNubDay() {
	return surplusNubDay;
    }

    public Integer getVipPrice() {
	return vipPrice;
    }

    public void setCostType(Integer costType) {
	this.costType = costType;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDescribe(String describe) {
	this.describe = describe;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public void setInitPrice(Integer initPrice) {
	this.initPrice = initPrice;
    }

    public void setIsShow(int isShow) {
	this.isShow = isShow;
    }

    public void setItemId(Integer itemId) {
	this.itemId = itemId;
    }

    public void setItemName(String itemName) {
	this.itemName = itemName;
    }

    public void setMallNewId(Integer mallNewId) {
	this.mallNewId = mallNewId;
    }

    public void setMallOrder(Integer mallOrder) {
	this.mallOrder = mallOrder;
    }

    public void setMallType(Integer mallType) {
	this.mallType = mallType;
    }

    public void setMaxNubDay(Integer maxNubDay) {
	this.maxNubDay = maxNubDay;
    }

    public void setMaxNubPlayer(Integer maxNubPlayer) {
	this.maxNubPlayer = maxNubPlayer;
    }

    public void setMoldfyTime(Date moldfyTime) {
	this.moldfyTime = moldfyTime;
    }

    public void setRebatePrice(Integer rebatePrice) {
	this.rebatePrice = rebatePrice;
    }

    public void setSellType(Integer sellType) {
	this.sellType = sellType;
    }

    public void setStartTime(Date startTime) {
	this.startTime = startTime;
    }

    public void setState(Integer state) {
	this.state = state;
    }

    public void setSurplusNubDay(Integer surplusNubDay) {
	this.surplusNubDay = surplusNubDay;
    }

    public void setVipPrice(Integer vipPrice) {
	this.vipPrice = vipPrice;
    }

}
