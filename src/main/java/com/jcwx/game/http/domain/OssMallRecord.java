package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 商城消费统计
 * 
 * @author 陈世平 2013-11-18
 */
public class OssMallRecord implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public final static int type_vip商店 = 1;
    public final static int type_普通价格 = 0;
    public final static int type_神秘商店 = 2;

    /** 创建时间 */
    private Date createTime;
    /** 花费魔晶数 */
    private double goldSum;
    /** 商品ID */
    private Integer goodId;
    /** 商品名称 */
    private String goodName;
    /** 主键ID */
    private Integer mallRecordId;

    /** 修改时间 */
    private Date moldfyTime;
    /** 普通商店总占百分比 */
    private double normalScale;
    /** 普通购买数量 */
    private int number;

    /** 角色ID */
    private Integer playerBaseId;
    /** 玩家Id */
    private Integer playerId;
    /** 普通商品价格 */
    private double price;

    /** 花费百分比 */
    private double scale;

    /** 神秘购买数量 */
    private int secretNumber;
    /** 神秘商品价格 */
    private double secretPrice;
    /** 花费百分比 */
    private double secretScale;

    /** 神秘商店销售总额 */
    private double secretSum;
    /** 大区名称 */
    private String serverName;
    /** 神秘商店总占百分比 */
    private double totalScale;
    /** 神秘商店总占百分比 */
    private double totalSecretScale;

    /** 0-商店 1-神秘商店 */
    private Integer type;
    /** vip购买数量 */
    private int vipNumber;
    /** vip商品价格 */
    private double vipPrice;
    /** 花费百分比 */
    private double vipScale;

    public Date getCreateTime() {
	return createTime;
    }

    public double getGoldSum() {
	return goldSum;
    }

    public Integer getGoodId() {
	return goodId;
    }

    public String getGoodName() {
	return goodName;
    }

    public Integer getMallRecordId() {
	return mallRecordId;
    }

    public Date getMoldfyTime() {
	return moldfyTime;
    }

    public double getNormalScale() {
	return normalScale;
    }

    public Integer getNumber() {
	return number;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public double getPrice() {
	return price;
    }

    public double getScale() {
	return scale;
    }

    public Integer getSecretNumber() {
	return secretNumber;
    }

    public double getSecretPrice() {
	return secretPrice;
    }

    public double getSecretScale() {
	return secretScale;
    }

    public double getSecretSum() {
	return secretSum;
    }

    public String getServerName() {
	return serverName;
    }

    public double getTotalScale() {
	return totalScale;
    }

    public double getTotalSecretScale() {
	return totalSecretScale;
    }

    public Integer getType() {
	return type;
    }

    public Integer getVipNumber() {
	return vipNumber;
    }

    public double getVipPrice() {
	return vipPrice;
    }

    public double getVipScale() {
	return vipScale;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setGoldSum(double goldSum) {
	this.goldSum = goldSum;
    }

    public void setGoodId(Integer goodId) {
	this.goodId = goodId;
    }

    public void setGoodName(String goodName) {
	this.goodName = goodName;
    }

    public void setMallRecordId(Integer mallRecordId) {
	this.mallRecordId = mallRecordId;
    }

    public void setMoldfyTime(Date moldfyTime) {
	this.moldfyTime = moldfyTime;
    }

    public void setNormalScale(double normalScale) {
	this.normalScale = normalScale;
    }

    public void setNumber(int number) {
	this.number = number;
    }

    public void setNumber(Integer number) {
	this.number = number;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    public void setScale(double scale) {
	this.scale = scale;
    }

    public void setSecretNumber(int secretNumber) {
	this.secretNumber = secretNumber;
    }

    public void setSecretNumber(Integer secretNumber) {
	this.secretNumber = secretNumber;
    }

    public void setSecretPrice(double secretPrice) {
	this.secretPrice = secretPrice;
    }

    public void setSecretScale(double secretScale) {
	this.secretScale = secretScale;
    }

    public void setSecretSum(double secretSum) {
	this.secretSum = secretSum;
    }

    public void setServerName(String serverName) {
	this.serverName = serverName;
    }

    public void setTotalScale(double totalScale) {
	this.totalScale = totalScale;
    }

    public void setTotalSecretScale(double totalSecretScale) {
	this.totalSecretScale = totalSecretScale;
    }

    public void setType(Integer type) {
	this.type = type;
    }

    public void setVipNumber(int vipNumber) {
	this.vipNumber = vipNumber;
    }

    public void setVipNumber(Integer vipNumber) {
	this.vipNumber = vipNumber;
    }

    public void setVipPrice(double vipPrice) {
	this.vipPrice = vipPrice;
    }

    public void setVipScale(double vipScale) {
	this.vipScale = vipScale;
    }

}
