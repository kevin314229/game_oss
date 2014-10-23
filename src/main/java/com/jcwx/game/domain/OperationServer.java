package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 运营商大区关联表
 * 
 * @author 小平 2013-11-6
 */
public class OperationServer implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 创建时间 */
    private Date createTime;
    /** 最后修改时间 */
    private Date modifyTime;
    /** 运营商ID */
    private Integer operationId;
    /** 主键ID */
    private Integer operationServerId;
    /** 服务器ID */
    private Integer serverId;

    public OperationServer() {
    }

    public OperationServer(Integer operationServerId, Integer operationId,
	    Integer serverId, Date createTime, Date modifyTime) {
	this.operationServerId = operationServerId;
	this.operationId = operationId;
	this.serverId = serverId;
	this.createTime = createTime;
	this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public Integer getOperationId() {
	return operationId;
    }

    public Integer getOperationServerId() {
	return operationServerId;
    }

    public Integer getServerId() {
	return serverId;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
    }

    public void setOperationServerId(Integer operationServerId) {
	this.operationServerId = operationServerId;
    }

    public void setServerId(Integer serverId) {
	this.serverId = serverId;
    }

}
