/**
 * 
 */
package com.jcwx.game.domain;

import com.jcwx.game.common.domain.BaseDomain;

/**
 * @author Administrator
 * 
 */
public class OssOperation extends BaseDomain {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 运营商缩写 */
    private String carrierOperator;
    /** 分成比例 */
    private Integer dividendRate;
    /**  */
    private Integer id;
    /** 运营商说明 */
    private String operationDetail;
    /** 运营商名称 */
    private String operationName;

    public OssOperation() {
    }

    public OssOperation(Integer id, String operationName,
	    String carrierOperator, String operationDetail, Integer dividendRate) {
	this.id = id;
	this.operationName = operationName;
	this.carrierOperator = carrierOperator;
	this.operationDetail = operationDetail;
	this.dividendRate = dividendRate;
    }

    public String getCarrierOperator() {
	exeSet();
	return carrierOperator;
    }

    public Integer getDividendRate() {
	exeGet();
	return dividendRate;
    }

    public Integer getId() {
	exeGet();
	return id;
    }

    public String getOperationDetail() {
	exeGet();
	return operationDetail;
    }

    public String getOperationName() {
	exeGet();
	return operationName;
    }

    public void setCarrierOperator(String carrierOperator) {
	this.carrierOperator = carrierOperator;
	exeSet();
    }

    public void setDividendRate(Integer dividendRate) {
	this.dividendRate = dividendRate;
	exeSet();
    }

    public void setId(Integer id) {
	this.id = id;
	exeSet();
    }

    public void setOperationDetail(String operationDetail) {
	this.operationDetail = operationDetail;
	exeSet();
    }

    public void setOperationName(String operationName) {
	this.operationName = operationName;
	exeSet();
    }

}
