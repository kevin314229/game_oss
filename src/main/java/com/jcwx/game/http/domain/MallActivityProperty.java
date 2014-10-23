package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * 商城物品缓存
 * 
 * @author Administrator
 * 
 */
public class MallActivityProperty implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 商品原价 */
    private Integer initPrice;
    /** 商品Id */
    private Integer itemId;
    /** 商品名称 */
    private String mallName;
    /** 商品现价 */
    private Integer rebatePrice;
    
    /** 商品现价 */
    private Integer vipPrice;

    public Integer getInitPrice() {
	return initPrice;
    }

    public Integer getItemId() {
	return itemId;
    }

    public String getMallName() {
	return mallName;
    }

    public Integer getRebatePrice() {
	return rebatePrice;
    }

    public void setInitPrice(Integer initPrice) {
	this.initPrice = initPrice;
    }

    public void setItemId(Integer itemId) {
	this.itemId = itemId;
    }

    public void setMallName(String mallName) {
	this.mallName = mallName;
    }

    public void setRebatePrice(Integer rebatePrice) {
	this.rebatePrice = rebatePrice;
    }

    public Integer getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Integer vipPrice) {
        this.vipPrice = vipPrice;
    }

}
