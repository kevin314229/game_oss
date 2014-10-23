package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * 物品消耗数据传递
 * 
 * @author Administrator
 * 
 */
public class OssZItemUse implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 使用次数 **/
    private Integer cNub;
    /** 使用次数百分比 **/
    private double cNubScale;
    /** 物品名称 **/
    private String itemName;
    /*** 使用物品总量 **/
    private Integer itemSum;
    /** 使用百分比 **/
    private double playerBaseScale;

    /** 玩家数 **/
    private Integer playerNub;
    /** 玩家角色数 **/
    private Integer plyaerBaseNub;

    /** 物品单价 **/
    private Integer price;

    /** 大区名称 **/
    private String serverName;
    /** 物品总价 **/
    private Integer totleFee;

    /** 使用百分比 **/
    private double totleScale;

    public Integer getcNub() {
	return cNub;
    }

    public double getcNubScale() {
	return cNubScale;
    }

    public String getItemName() {
	return itemName;
    }

    public Integer getItemSum() {
	return itemSum;
    }

    public double getPlayerBaseScale() {
	return playerBaseScale;
    }

    public Integer getPlayerNub() {
	return playerNub;
    }

    public Integer getPlyaerBaseNub() {
	return plyaerBaseNub;
    }

    public Integer getPrice() {
	return price;
    }

    public String getServerName() {
	return serverName;
    }

    public Integer getTotleFee() {
	return totleFee;
    }

    public double getTotleScale() {
	return totleScale;
    }

    public void setcNub(Integer cNub) {
	this.cNub = cNub;
    }

    public void setcNubScale(double cNubScale) {
	this.cNubScale = cNubScale;
    }

    public void setItemName(String itemName) {
	this.itemName = itemName;
    }

    public void setItemSum(Integer itemSum) {
	this.itemSum = itemSum;
    }

    public void setPlayerBaseScale(double playerBaseScale) {
	this.playerBaseScale = playerBaseScale;
    }

    public void setPlayerNub(Integer playerNub) {
	this.playerNub = playerNub;
    }

    public void setPlyaerBaseNub(Integer plyaerBaseNub) {
	this.plyaerBaseNub = plyaerBaseNub;
    }

    public void setPrice(Integer price) {
	this.price = price;
    }

    public void setServerName(String serverName) {
	this.serverName = serverName;
    }

    public void setTotleFee(Integer totleFee) {
	this.totleFee = totleFee;
    }

    public void setTotleScale(double totleScale) {
	this.totleScale = totleScale;
    }

}
