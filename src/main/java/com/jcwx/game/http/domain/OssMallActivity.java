package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

public class OssMallActivity implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 附加消耗数量 */
    private Integer costPrice;
    /** 附加消费 0-无 1-金币 2-礼券 3-积分 */
    private Integer costType;
    /** 创建时间 */
    private Date createTime;
    /** 描述 */
    private String describe;
    /** 下线时间 */
    private Date endTime;
    /** 商品原价 */
    private Integer initPrice;
    /** 商品Id */
    private Integer itemId;
    /** 主键Id */
    private Integer mallActivityId;
    /** 商品名称 */
    private String mallName;
    /** 当天出售最大数量 */
    private Integer maxNubDay;
    /** 当天玩家最大购买数量 */
    private Integer maxNubPlayer;
    /** 最后修改时间 */
    private Date moldfyTime;
    /** 商品现价 */
    private Integer rebatePrice;
    /** 上线时间 */
    private Date startTime;
    /** 标签 0-无 1-新 2-热 */
    private Integer state;
    /*** 当天剩余数量 */
    private Integer surplusNubDay;
    /** 购买条件 vip等级 0-无 1-vip1 2-vip2 ... */
    private Integer vipGrade;
    /** 商品vip价格 */
    private Integer vipPrice;

    public Integer getCostPrice() {
	return costPrice;
    }

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

    public Integer getItemId() {
	return itemId;
    }

    public Integer getMallActivityId() {
	return mallActivityId;
    }

    public String getMallName() {
	return mallName;
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

    public Date getStartTime() {
	return startTime;
    }

    public Integer getState() {
	return state;
    }

    public Integer getSurplusNubDay() {
	return surplusNubDay;
    }

    public Integer getVipGrade() {
	return vipGrade;
    }

    public Integer getVipPrice() {
	return vipPrice;
    }

    public void setCostPrice(Integer costPrice) {
	this.costPrice = costPrice;
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

    public void setItemId(Integer itemId) {
	this.itemId = itemId;
    }

    public void setMallActivityId(Integer mallActivityId) {
	this.mallActivityId = mallActivityId;
    }

    public void setMallName(String mallName) {
	this.mallName = mallName;
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

    public void setStartTime(Date startTime) {
	this.startTime = startTime;
    }

    public void setState(Integer state) {
	this.state = state;
    }

    public void setSurplusNubDay(Integer surplusNubDay) {
	this.surplusNubDay = surplusNubDay;
    }

    public void setVipGrade(Integer vipGrade) {
	this.vipGrade = vipGrade;
    }

    public void setVipPrice(Integer vipPrice) {
	this.vipPrice = vipPrice;
    }
}
