package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 */
public class SendBaseProperty implements Serializable {
    /**
     * 最大堆叠
     * */
    private Integer maxHeap;
    /**
     * 物品名称
     * */
    private String name;
    /**
     * 数目
     */
    private Integer num;

    /**
     * 物品Id
     * */
    private Integer value;

    private Integer quality;
    public Integer getMaxHeap() {
	return maxHeap;
    }

    public String getName() {
	return name;
    }

    public Integer getNum() {
	return num;
    }

    public Integer getValue() {
	return value;
    }

    public void setMaxHeap(Integer maxHeap) {
	this.maxHeap = maxHeap;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setNum(Integer num) {
	this.num = num;
    }

    public void setValue(Integer value) {
	this.value = value;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }
}
