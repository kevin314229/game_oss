package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

/** IP统计 */
public class IPStats implements Serializable {

    private static final long serialVersionUID = 5578162044778884509L;

    private String ip;

    /** 登录时间 */
    private Date loginTime;

    /** 访问次数 */
    private Integer visitNum;

    public String getIp() {
	return ip;
    }

    public Date getLoginTime() {
	return loginTime;
    }

    public Integer getVisitNum() {
	return visitNum;
    }

    public void setIp(String ip) {
	this.ip = ip;
    }

    public void setLoginTime(Date loginTime) {
	this.loginTime = loginTime;
    }

    public void setVisitNum(Integer visitNum) {
	this.visitNum = visitNum;
    }

}
