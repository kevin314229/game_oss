package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class OssUserServer implements Serializable {

    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String createUser;
    /**  */
    private Integer id;
    /** 运营商ID */
    private Integer operationId;
    /** 服务器Id */
    private Integer serverId;
    /** 用户名 */
    private String username;

    public OssUserServer() {
    }

    public OssUserServer(Integer id, String username, Integer operationId,
	    Integer serverId, Date createTime, String createUser) {
	this.id = id;
	this.username = username;
	this.operationId = operationId;
	this.serverId = serverId;
	this.createTime = createTime;
	this.createUser = createUser;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public String getCreateUser() {
	return createUser;
    }

    public Integer getId() {
	return id;
    }

    public Integer getOperationId() {
	return operationId;
    }

    public Integer getServerId() {
	return serverId;
    }

    public String getUsername() {
	return username;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setCreateUser(String createUser) {
	this.createUser = createUser;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
    }

    public void setServerId(Integer serverId) {
	this.serverId = serverId;
    }

    public void setUsername(String username) {
	this.username = username;
    }

}