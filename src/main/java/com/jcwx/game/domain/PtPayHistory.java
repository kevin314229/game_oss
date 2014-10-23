package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

import com.jcwx.game.common.domain.BaseDomain;

public class PtPayHistory extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**  */
    private Integer id;
    /** 订单状态0成功，2游戏失败异常（要手动恢复） */
    private Integer orderStatus;
    /** 平台标识 */
    private String ptCode;
    /** 平台(游戏)大区号 */
    private Integer ptArea;
    /** 平台订单号 */
    private String ptOrder;
    /** 平台账号 */
    private String ptLoginName;
    /** 游戏（区）玩家账号ID */
    private Integer playerId;
    /** 游戏（区）玩家角色ID */
    private Integer playerBaseId;
    /** 币种(货币类型:1-人民币；2-美元) */
    private Integer currency;
    /** 平台币数量 */
    private double ptMomey;
    /** 充值金额 */
    private double rmb;
    /** 游戏订单号 */
    private Integer gameOrder;
    /** 商品ID */
    private Integer goodsId;
    /** 商品数量 */
    private Integer goodsNum;
    /** 处理完成时间 */
    private Date gameOrderTime;
    /** 创建时间 */
    private Date createTime;
    /** 备注 */
    private String remark;
    /** 订单源 */
    private String orderSource;
    /** 支付通道(uc) */
    private String payWay;
    /** 通知IP */
    private String notifyIp;
    /** 扩展记录1 */
    private String notes1;
    /** 扩展记录2 */
    private String notes2;
    /** 游戏处理状态(0 默认 1成功 2异常) */
    private Integer gameStatus;
    /** 游戏处理失败备注 */
    private String gameExecmsg;
    /** 充值类型(1 列表充值 2网页充值) */
    private Integer payType;
    /**  */
    private String nickName;

    public PtPayHistory() {
    }

    public PtPayHistory(Integer id, Integer orderStatus, String ptCode,
	    Integer ptArea, String ptOrder, String ptLoginName,
	    Integer playerId, Integer playerBaseId, Integer currency,
	    double ptMomey, double rmb, Integer gameOrder, Integer goodsId,
	    Integer goodsNum, Date gameOrderTime, Date createTime,
	    String remark, String orderSource, String payWay, String notifyIp,
	    String notes1, String notes2, Integer gameStatus,
	    String gameExecmsg, Integer payType, String nickName) {
	this.id = id;
	this.orderStatus = orderStatus;
	this.ptCode = ptCode;
	this.ptArea = ptArea;
	this.ptOrder = ptOrder;
	this.ptLoginName = ptLoginName;
	this.playerId = playerId;
	this.playerBaseId = playerBaseId;
	this.currency = currency;
	this.ptMomey = ptMomey;
	this.rmb = rmb;
	this.gameOrder = gameOrder;
	this.goodsId = goodsId;
	this.goodsNum = goodsNum;
	this.gameOrderTime = gameOrderTime;
	this.createTime = createTime;
	this.remark = remark;
	this.orderSource = orderSource;
	this.payWay = payWay;
	this.notifyIp = notifyIp;
	this.notes1 = notes1;
	this.notes2 = notes2;
	this.gameStatus = gameStatus;
	this.gameExecmsg = gameExecmsg;
	this.payType = payType;
	this.nickName = nickName;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getOrderStatus() {
	return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
	this.orderStatus = orderStatus;
    }

    public String getPtCode() {
	return ptCode;
    }

    public void setPtCode(String ptCode) {
	this.ptCode = ptCode;
    }

    public Integer getPtArea() {
	return ptArea;
    }

    public void setPtArea(Integer ptArea) {
	this.ptArea = ptArea;
    }

    public String getPtOrder() {
	return ptOrder;
    }

    public void setPtOrder(String ptOrder) {
	this.ptOrder = ptOrder;
    }

    public String getPtLoginName() {
	return ptLoginName;
    }

    public void setPtLoginName(String ptLoginName) {
	this.ptLoginName = ptLoginName;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public Integer getCurrency() {
	return currency;
    }

    public void setCurrency(Integer currency) {
	this.currency = currency;
    }

    public double getPtMomey() {
	return ptMomey;
    }

    public void setPtMomey(double ptMomey) {
	this.ptMomey = ptMomey;
    }

    public double getRmb() {
	return rmb;
    }

    public void setRmb(double rmb) {
	this.rmb = rmb;
    }

    public Integer getGameOrder() {
	return gameOrder;
    }

    public void setGameOrder(Integer gameOrder) {
	this.gameOrder = gameOrder;
    }

    public Integer getGoodsId() {
	return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
	this.goodsId = goodsId;
    }

    public Integer getGoodsNum() {
	return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
	this.goodsNum = goodsNum;
    }

    public Date getGameOrderTime() {
	return gameOrderTime;
    }

    public void setGameOrderTime(Date gameOrderTime) {
	this.gameOrderTime = gameOrderTime;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public String getRemark() {
	return remark;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

    public String getOrderSource() {
	return orderSource;
    }

    public void setOrderSource(String orderSource) {
	this.orderSource = orderSource;
    }

    public String getPayWay() {
	return payWay;
    }

    public void setPayWay(String payWay) {
	this.payWay = payWay;
    }

    public String getNotifyIp() {
	return notifyIp;
    }

    public void setNotifyIp(String notifyIp) {
	this.notifyIp = notifyIp;
    }

    public String getNotes1() {
	return notes1;
    }

    public void setNotes1(String notes1) {
	this.notes1 = notes1;
    }

    public String getNotes2() {
	return notes2;
    }

    public void setNotes2(String notes2) {
	this.notes2 = notes2;
    }

    public Integer getGameStatus() {
	return gameStatus;
    }

    public void setGameStatus(Integer gameStatus) {
	this.gameStatus = gameStatus;
    }

    public String getGameExecmsg() {
	return gameExecmsg;
    }

    public void setGameExecmsg(String gameExecmsg) {
	this.gameExecmsg = gameExecmsg;
    }

    public Integer getPayType() {
	return payType;
    }

    public void setPayType(Integer payType) {
	this.payType = payType;
    }

    public String getNickName() {
	return nickName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

}
