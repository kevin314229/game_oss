package com.jcwx.game.domain;

import java.util.Date;

import com.jcwx.game.common.domain.BaseDomain;

public class OssLog extends BaseDomain {

    /** 当前管理员所在的运营商ID */
    private Integer businessId;
    /** 当前管理员所在的运营商名称 */
    private String businessName;
    /** 操作日期 */
    private Date createTime;
    /** 系统操作日志ID */
    private Integer id;
    /** ip地区 */
    private String ipAddress;
    /** 录登IP */
    private String loginIp;
    /** 账号 */
    private String name;
    /** 操作类型 */
    private String operationMsg;
    /** 操作内容 */
    private String operationType;
    /** 注备 */
    private String remark;
    /** 当前管理员所在的服务器区id */
    private Integer serverId;
    /** 所在的服务器区名称冗余防止被删 */
    private String serverName;

    public OssLog() {
    }

    public OssLog(Integer id, String name, String loginIp, String ipAddress,
	    String operationType, String operationMsg, Date createTime,
	    String remark, Integer serverId, String serverName,
	    Integer businessId, String businessName) {
	this.id = id;
	this.name = name;
	this.loginIp = loginIp;
	this.ipAddress = ipAddress;
	this.operationType = operationType;
	this.operationMsg = operationMsg;
	this.createTime = createTime;
	this.remark = remark;
	this.serverId = serverId;
	this.serverName = serverName;
	this.businessId = businessId;
	this.businessName = businessName;
    }

    public Integer getBusinessId() {
	return businessId;
    }

    public String getBusinessName() {
	return businessName;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Integer getId() {
	return id;
    }

    public String getIpAddress() {
	return ipAddress;
    }

    public String getLoginIp() {
	return loginIp;
    }

    public String getName() {
	return name;
    }

    public String getOperationMsg() {
	return operationMsg;
    }

    public String getOperationType() {
	return operationType;
    }

    public String getRemark() {
	return remark;
    }

    public Integer getServerId() {
	return serverId;
    }

    public String getServerName() {
	return serverName;
    }

    public void setBusinessId(Integer businessId) {
	this.businessId = businessId;
    }

    public void setBusinessName(String businessName) {
	this.businessName = businessName;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setIpAddress(String ipAddress) {
	this.ipAddress = ipAddress;
    }

    public void setLoginIp(String loginIp) {
	this.loginIp = loginIp;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setOperationMsg(String operationMsg) {
	this.operationMsg = operationMsg;
    }

    public void setOperationType(String operationType) {
	this.operationType = operationType;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

    public void setServerId(Integer serverId) {
	this.serverId = serverId;
    }

    public void setServerName(String serverName) {
	this.serverName = serverName;
    }

}