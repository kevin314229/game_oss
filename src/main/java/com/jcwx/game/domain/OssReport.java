package com.jcwx.game.domain;

import java.io.Serializable;

public class OssReport implements Serializable {

    private static final long serialVersionUID = -1369142696282669712L;
    /** 统计数量 */
    private Double number;
    /** 统计时间 */
    private String reportTime;

    public Double getNumber() {
	return number;
    }

    public String getReportTime() {
	return reportTime;
    }

    public void setNumber(Double number) {
	this.number = number;
    }

    public void setReportTime(String reportTime) {
	this.reportTime = reportTime;
    }

}
