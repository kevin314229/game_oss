package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 首充礼包
 * 
 * @author csp
 * 
 */
public class OssRechargeActivity implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 充值说明 */
    private String activityDesc;
    /** 充值说明2 */
    private String activityDescSecond;
    /** 活动开启时间 */
    private Date openTime;
    private List<OssRechargeActivityDetail> ossRechargeActivityDetailList;
    /** 活动结束时间 */
    private Date overTime;
    /** 活动id--自增主键 */
    private Integer recharegeActivityId;

    /** 活动类型 */
    private Integer type;

    public String getActivityDesc() {
	return activityDesc;
    }

    public String getActivityDescSecond() {
	return activityDescSecond;
    }

    public Date getOpenTime() {
	return openTime;
    }

    public List<OssRechargeActivityDetail> getOssRechargeActivityDetailList() {
	return ossRechargeActivityDetailList;
    }

    public Date getOverTime() {
	return overTime;
    }

    public Integer getRecharegeActivityId() {
	return recharegeActivityId;
    }

    public Integer getType() {
	return type;
    }

    public void setActivityDesc(String activityDesc) {
	this.activityDesc = activityDesc;
    }

    public void setActivityDescSecond(String activityDescSecond) {
	this.activityDescSecond = activityDescSecond;
    }

    public void setOpenTime(Date openTime) {
	this.openTime = openTime;
    }

    public void setOssRechargeActivityDetailList(
	    List<OssRechargeActivityDetail> ossRechargeActivityDetailList) {
	this.ossRechargeActivityDetailList = ossRechargeActivityDetailList;
    }

    public void setOverTime(Date overTime) {
	this.overTime = overTime;
    }

    public void setRecharegeActivityId(Integer recharegeActivityId) {
	this.recharegeActivityId = recharegeActivityId;
    }

    public void setType(Integer type) {
	this.type = type;
    }

}
