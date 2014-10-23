package com.jcwx.game.http.domain;

import java.io.Serializable;

public class OssStaMallRecord implements Serializable{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public final static int type_vip商店 = 1;
    public final static int type_普通价格 = 0;
    public final static int type_神秘商店 = 2;

    /** 商品ID */
    private Integer goodId;
    /** 商品名称 */
    private String goodName;
   
    
    /** 0-商店 1-神秘商店 */
    private Integer type;

   
    /** 普通购买数量 */
    private int number;
    /** 普通商品价格 */
    private double price;
    

    
    /** 神秘购买数量 */
    private int secretNumber;
    /** 神秘商品价格 */
    private double secretPrice;
    /** 总花费百分比 */
    private double secretScale;
    
    
    /** 大区名称 */
    private String serverName;
  
    /** 神秘商店总占百分比 */
    private double totalSecretScale;

    
    /** vip购买数量 */
    private int vipNumber;
    /** vip商品价格 */
    private double vipPrice;
    

    private double normalSum;
    
    private double vipSum;
    
    /** 神秘商店销售总额 */
    private double secretSum;
    
    
    /** 花费魔晶数 */
    private double goldSum;
    
    /** 普通商店总占百分比 */
    private double normalScale;
    /** 普通百分比 */
    private double scale;
    /** vip花费百分比 */
    private double vipScale;
    /** 总占百分比 */
    private double totalScale;
    
    public Integer getGoodId() {
        return goodId;
    }
    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }
    public String getGoodName() {
        return goodName;
    }
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getSecretNumber() {
        return secretNumber;
    }
    public void setSecretNumber(int secretNumber) {
        this.secretNumber = secretNumber;
    }
    public double getSecretPrice() {
        return secretPrice;
    }
    public void setSecretPrice(double secretPrice) {
        this.secretPrice = secretPrice;
    }
    public double getSecretScale() {
        return secretScale;
    }
    public void setSecretScale(double secretScale) {
        this.secretScale = secretScale;
    }
    public String getServerName() {
        return serverName;
    }
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    public double getTotalScale() {
        return totalScale;
    }
    public void setTotalScale(double totalScale) {
        this.totalScale = totalScale;
    }
    public double getTotalSecretScale() {
        return totalSecretScale;
    }
    public void setTotalSecretScale(double totalSecretScale) {
        this.totalSecretScale = totalSecretScale;
    }
    public int getVipNumber() {
        return vipNumber;
    }
    public void setVipNumber(int vipNumber) {
        this.vipNumber = vipNumber;
    }
    public double getVipPrice() {
        return vipPrice;
    }
    public void setVipPrice(double vipPrice) {
        this.vipPrice = vipPrice;
    }
    public double getNormalSum() {
        return normalSum;
    }
    public void setNormalSum(double normalSum) {
        this.normalSum = normalSum;
    }
    public double getVipSum() {
        return vipSum;
    }
    public void setVipSum(double vipSum) {
        this.vipSum = vipSum;
    }
    public double getSecretSum() {
        return secretSum;
    }
    public void setSecretSum(double secretSum) {
        this.secretSum = secretSum;
    }
    public double getGoldSum() {
        return goldSum;
    }
    public void setGoldSum(double goldSum) {
        this.goldSum = goldSum;
    }
    public double getNormalScale() {
        return normalScale;
    }
    public void setNormalScale(double normalScale) {
        this.normalScale = normalScale;
    }
    public double getVipScale() {
        return vipScale;
    }
    public void setVipScale(double vipScale) {
        this.vipScale = vipScale;
    }
    public double getScale() {
        return scale;
    }
    public void setScale(double scale) {
        this.scale = scale;
    }
    
}
