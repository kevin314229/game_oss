package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class PayHistoryNew implements Serializable {

    /** 订单处理完成时间 */
    private Date completeTime;
    /** 订单创建时间 */
    private Date createTime;
    /** 币种 */
    private Integer currency;
    /** 商品ID */
    private Integer goodId;
    /** 商品数量 */
    private Integer goodNum;
    /** 游戏订单号 */
    private Integer id;
    /** 订单状态(0成功，1等待处理,2处理失败的要手动恢复） */
    private Integer orderStatus;
    /** 角色ID */
    private Integer playerBaseId;
    /** 账号ID */
    private Integer playerId;
    /** 平台(游戏)大区号 */
    private Integer ptArea;
    /** 平台标识 */
    private String ptCode;
    /** 平台登录名 */
    private String ptLoginName;
    /** 平台币数量 */
    private Double ptMoney;
    /** 平台订单号 */
    private String ptOrder;
    /** 平台订单源 */
    private String ptOrderSource;
    /** 备注 */
    private String remark;
    /** 对应的人民币 */
    private Double rmb;

    public PayHistoryNew() {
    }

    public Date getCompleteTime() {
	return completeTime;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Integer getCurrency() {
	return currency;
    }

    public Integer getGoodId() {
	return goodId;
    }

    public Integer getGoodNum() {
	return goodNum;
    }

    public Integer getId() {
	return id;
    }

    public Integer getOrderStatus() {
	return orderStatus;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public Integer getPtArea() {
	return ptArea;
    }

    public String getPtCode() {
	return ptCode;
    }

    public String getPtLoginName() {
	return ptLoginName;
    }

    public Double getPtMoney() {
	return ptMoney;
    }

    public String getPtOrder() {
	return ptOrder;
    }

    public String getPtOrderSource() {
	return ptOrderSource;
    }

    public String getRemark() {
	return remark;
    }

    public Double getRmb() {
	return rmb;
    }

    public void setCompleteTime(Date completeTime) {
	this.completeTime = completeTime;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setCurrency(Integer currency) {
	this.currency = currency;
    }

    public void setGoodId(Integer goodId) {
	this.goodId = goodId;
    }

    public void setGoodNum(Integer goodNum) {
	this.goodNum = goodNum;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setOrderStatus(Integer orderStatus) {
	this.orderStatus = orderStatus;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setPtArea(Integer ptArea) {
	this.ptArea = ptArea;
    }

    public void setPtCode(String ptCode) {
	this.ptCode = ptCode;
    }

    public void setPtLoginName(String ptLoginName) {
	this.ptLoginName = ptLoginName;
    }

    public void setPtMoney(Double ptMoney) {
	this.ptMoney = ptMoney;
    }

    public void setPtOrder(String ptOrder) {
	this.ptOrder = ptOrder;
    }

    public void setPtOrderSource(String ptOrderSource) {
	this.ptOrderSource = ptOrderSource;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

    public void setRmb(Double rmb) {
	this.rmb = rmb;
    }

}