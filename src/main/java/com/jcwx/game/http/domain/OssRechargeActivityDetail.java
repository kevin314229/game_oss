package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * 首充详情
 * 
 * @author csp
 * 
 */
public class OssRechargeActivityDetail implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 奖励道具 */
    private String item;
    /** 职业类型 */
    private Integer occupation;
    /** 活动id-- 对应t_activity主键 */
    private Integer recharegeActivityId;
    /** 活动条件Id--自增主键 */
    private Integer rechargeActivityDetailId;

    public String getItem() {
	return item;
    }

    public Integer getOccupation() {
	return occupation;
    }

    public Integer getRecharegeActivityId() {
	return recharegeActivityId;
    }

    public Integer getRechargeActivityDetailId() {
	return rechargeActivityDetailId;
    }

    public void setItem(String item) {
	this.item = item;
    }

    public void setOccupation(Integer occupation) {
	this.occupation = occupation;
    }

    public void setRecharegeActivityId(Integer recharegeActivityId) {
	this.recharegeActivityId = recharegeActivityId;
    }

    public void setRechargeActivityDetailId(Integer rechargeActivityDetailId) {
	this.rechargeActivityDetailId = rechargeActivityDetailId;
    }
}
