package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能调整表
 * 
 * @author 2013-11-23
 */
public class ZHOssFunctionAdjust implements Serializable {
    public final static int functionType_被邀请加入 = 5;

    public final static int functionType_符文大师邀请 = 2;
    public final static int functionType_炼金倍数 = 3;
    public final static int functionType_神秘商店刷新 = 1;
    public final static int functionType_邀请等级 = 6;
    public final static int functionType_邀请数量 = 7;
    public final static int functionType_主邀请加入 = 4;
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 创建时间 */
    private Date createTime;
    /** 描述 */
    private String describe;
    /** 结束时间 */
    private Date endTime;
    /** 功能点名称 */
    private String functionName;
    /** 功能点变更值 */
    private String functionNub;
    /** 功能点类型 1-神秘商店刷新 2-符文大师邀请 3-炼金倍数 */
    private Integer functionType;
    /** 最后更新时间 */
    private Date moldfyTime;
    /** 开始时间 */
    private Date startTime;

    public ZHOssFunctionAdjust() {
    }

    public Date getCreateTime() {
	return createTime;
    }

    public String getDescribe() {
	return describe;
    }

    public Date getEndTime() {
	return endTime;
    }

    public String getFunctionName() {
	return functionName;
    }

    public String getFunctionNub() {
	return functionNub;
    }

    public Integer getFunctionType() {
	return functionType;
    }

    public Date getMoldfyTime() {
	return moldfyTime;
    }

    public Date getStartTime() {
	return startTime;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDescribe(String describe) {
	this.describe = describe;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public void setFunctionName(String functionName) {
	this.functionName = functionName;
    }

    public void setFunctionNub(String functionNub) {
	this.functionNub = functionNub;
    }

    public void setFunctionType(Integer functionType) {
	this.functionType = functionType;
    }

    public void setMoldfyTime(Date moldfyTime) {
	this.moldfyTime = moldfyTime;
    }

    public void setStartTime(Date startTime) {
	this.startTime = startTime;
    }

}
