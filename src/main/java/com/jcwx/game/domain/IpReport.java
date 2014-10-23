package com.jcwx.game.domain;

import java.io.Serializable;

public class IpReport implements Serializable {

    /**
     * Ip统计
     */
    private static final long serialVersionUID = 1L;

    // Ip数
    private Integer ipNum;
    // 登录人数
    private Integer loginNum;
    // 平台标识
    private String ptCode;

    public Integer getIpNum() {
	return ipNum;
    }

    public Integer getLoginNum() {
	return loginNum;
    }

    public String getPtCode() {
	return ptCode;
    }

    public void setIpNum(Integer ipNum) {
	this.ipNum = ipNum;
    }

    public void setLoginNum(Integer loginNum) {
	this.loginNum = loginNum;
    }

    public void setPtCode(String ptCode) {
	this.ptCode = ptCode;
    }

}