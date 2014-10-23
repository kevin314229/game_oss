package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * 新手步骤 用于与 oss 对接
 * 
 * @author Administrator
 * 
 */
public class OssNewRecord implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 丢失玩家数 */
    private Integer loseNub;
    /** 丢失玩家概率 */
    private Double loseProbability;
    /** 步骤数 */
    private Integer step;
    /** 总玩家人数 */
    private Integer totalNub;

    public Integer getLoseNub() {
	return loseNub;
    }

    public Double getLoseProbability() {
	return loseProbability;
    }

    public Integer getStep() {
	return step;
    }

    public Integer getTotalNub() {
	return totalNub;
    }

    public void setLoseNub(Integer loseNub) {
	this.loseNub = loseNub;
    }

    public void setLoseProbability(Double loseProbability) {
	this.loseProbability = loseProbability;
    }

    public void setStep(Integer step) {
	this.step = step;
    }

    public void setTotalNub(Integer totalNub) {
	this.totalNub = totalNub;
    }

}
