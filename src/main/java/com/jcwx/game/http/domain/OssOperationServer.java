package com.jcwx.game.http.domain;

/**
 * 平台服务器关联
 * 
 * @author Administrator
 * 
 */
public class OssOperationServer {

    /** 服务器ID */
    private Integer serverId;
    /** 服务器名称 */
    private String serverName;

    public Integer getServerId() {
	return serverId;
    }

    public String getServerName() {
	return serverName;
    }

    public void setServerId(Integer serverId) {
	this.serverId = serverId;
    }

    public void setServerName(String serverName) {
	this.serverName = serverName;
    }

}
