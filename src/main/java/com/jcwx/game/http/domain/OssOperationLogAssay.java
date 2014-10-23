package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * 消耗类型统计
 * 
 * @author Administrator
 * 
 */
public class OssOperationLogAssay implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    // 消费人数
    private int nub;
    // 消费类型
    private String operation;
    // 占百分比
    private double ratio;
    // 消费总额
    private long sum;

    public int getNub() {
	return nub;
    }

    public String getOperation() {
	return operation;
    }

    public double getRatio() {
	return ratio;
    }

    public long getSum() {
	return sum;
    }

    public void setNub(int nub) {
	this.nub = nub;
    }

    public void setOperation(String operation) {
	this.operation = operation;
    }

    public void setRatio(double ratio) {
	this.ratio = ratio;
    }

    public void setSum(long sum) {
	this.sum = sum;
    }

}
