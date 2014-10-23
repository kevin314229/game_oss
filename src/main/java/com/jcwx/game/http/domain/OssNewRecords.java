package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 新手步骤 用于与 oss 对接
 * 
 * @author Administrator
 * 
 */
public class OssNewRecords implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    // 统计日期
    private Date createDate;
    // 客户流失记录
    private List<OssNewRecord> ossNewRecordList;
    // 玩家留存率
    private Double probability;

    public Date getCreateDate() {
	return createDate;
    }

    public List<OssNewRecord> getOssNewRecordList() {
	return ossNewRecordList;
    }

    public Double getProbability() {
	return probability;
    }

    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
    }

    public void setOssNewRecordList(List<OssNewRecord> ossNewRecordList) {
	this.ossNewRecordList = ossNewRecordList;
    }

    public void setProbability(Double probability) {
	this.probability = probability;
    }

}
