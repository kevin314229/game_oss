package com.jcwx.game.http.domain;

import java.io.Serializable;

public class AllOnlineDto implements Serializable {

    private Integer allNum;

    private String ptCode;

    /** 所在服务器名字 */
    private String serverName;

    public Integer getAllNum() {
	return allNum;
    }

    public String getPtCode() {
	return ptCode;
    }

    public String getServerName() {
	return serverName;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setPtCode(String ptCode) {
	this.ptCode = ptCode;
    }

    public void setServerName(String serverName) {
	this.serverName = serverName;
    }

}