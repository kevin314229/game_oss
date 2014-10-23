/**
 * 
 */
package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * 
 */
public class OssActivity implements Serializable {

    private static final long serialVersionUID = 1000L;
    // /** 运营商标识 **/
    // private String carrierOperator;
    /** 账号领取人数 */
    private Integer accountNum;
    /** 活动描述 */
    private String activityDesc;
    /** 领取方式 */
    private Integer characteristic;
    /** 活动id */
    private Integer id;
    /** 活动名称 */
    private String name;
    /** 活动开启时间 */
    private Date openTime;
    private List<OssActivityDetail> ossActivityDetails;
    /** 活动结束时间 */
    private Date overTime;
    private Date rewardOverTime;
    /** 角色领取人数 */
    private Integer roleNum;
    /** 活动规则 */
    private String rule;
    /** 领取次数 */
    private Integer times;
    /** 活动类型 */
    private Integer type;

    private Integer rank;

    private Integer winphoneType;
    private String winphoneContent;

    public OssActivity() {
    }

    public OssActivity(Integer id, Integer type, Integer characteristic,
	    Integer times, String name, String activityDesc, String rule,
	    Date openTime, Date overTime) {
	this.id = id;
	this.type = type;
	this.characteristic = characteristic;
	this.times = times;
	this.name = name;
	this.activityDesc = activityDesc;
	this.rule = rule;
	this.openTime = openTime;
	this.overTime = overTime;
	// this.carrierOperator = carrierOperator;
    }

    public Integer getRank() {
	return rank;
    }

    public void setRank(Integer rank) {
	this.rank = rank;
    }

    public Integer getAccountNum() {
	return accountNum;
    }

    public String getActivityDesc() {
	return activityDesc;
    }

    public Integer getCharacteristic() {
	return characteristic;
    }

    public Integer getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public Date getOpenTime() {
	return openTime;
    }

    public List<OssActivityDetail> getOssActivityDetails() {
	return ossActivityDetails;
    }

    public Date getOverTime() {
	return overTime;
    }

    public Date getRewardOverTime() {
	return rewardOverTime;
    }

    public Integer getRoleNum() {
	return roleNum;
    }

    public String getRule() {
	return rule;
    }

    public Integer getTimes() {
	return times;
    }

    public Integer getType() {
	return type;
    }

    public void setAccountNum(Integer accountNum) {
	this.accountNum = accountNum;
    }

    public void setActivityDesc(String activityDesc) {
	this.activityDesc = activityDesc;
    }

    public void setCharacteristic(Integer characteristic) {
	this.characteristic = characteristic;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setOpenTime(Date openTime) {
	this.openTime = openTime;
    }

    public void setOssActivityDetails(List<OssActivityDetail> ossActivityDetails) {
	this.ossActivityDetails = ossActivityDetails;
    }

    public void setOverTime(Date overTime) {
	this.overTime = overTime;
    }

    public void setRewardOverTime(Date rewardOverTime) {
	this.rewardOverTime = rewardOverTime;
    }

    public void setRoleNum(Integer roleNum) {
	this.roleNum = roleNum;
    }

    public void setRule(String rule) {
	this.rule = rule;
    }

    public void setTimes(Integer times) {
	this.times = times;
    }

    public void setType(Integer type) {
	this.type = type;
    }

    public Integer getWinphoneType() {
	return winphoneType;
    }

    public void setWinphoneType(Integer winphoneType) {
	this.winphoneType = winphoneType;
    }

    public String getWinphoneContent() {
	return winphoneContent;
    }

    public void setWinphoneContent(String winphoneContent) {
	this.winphoneContent = winphoneContent;
    }

    // public String getCarrierOperator() {
    // return carrierOperator;
    // }
    //
    // public void setCarrierOperator(String carrierOperator) {
    // this.carrierOperator = carrierOperator;
    // }

}
