package com.jcwx.game.domain;

import java.security.Key;
import java.util.Date;
import java.util.List;

import com.jcwx.game.common.domain.BaseDomain;

public class OssServer extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /** 通讯key */
    private String communicateKey;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String createUser;
    /** 服务器 */
    private Integer id;
    private Key key;
    /** 服务器名称 */
    private String name;
    private List<OssOperation> ossOperationList;
    /** 所属项目 */
    private Integer projectId;
    /** 平台入口 */
    private String serverCode;
    /** 运营商ID */
    private Integer serverId;
    /** 所属运营商 */
    private String serverProvider;
    /** 修改时间 */
    private Date updateTime;

    /** 修改人 */
    private String updateUser;

    /** 连接服务器http地址 */
    private String url;

    public OssServer() {
    }

    public OssServer(Integer id, Integer serverId, String serverCode,
	    String serverProvider, String name, String url,
	    String communicateKey, Date createTime, String createUser,
	    Date updateTime, String updateUser, Integer projectId) {
	this.id = id;
	this.serverId = serverId;
	this.serverCode = serverCode;
	this.serverProvider = serverProvider;
	this.name = name;
	this.url = url;
	this.communicateKey = communicateKey;
	this.createTime = createTime;
	this.createUser = createUser;
	this.updateTime = updateTime;
	this.updateUser = updateUser;
	this.projectId = projectId;
    }

    public String getCommunicateKey() {
	exeGet();
	return communicateKey;
    }

    public Date getCreateTime() {
	exeGet();
	return createTime;
    }

    public String getCreateUser() {
	exeGet();
	return createUser;
    }

    public Integer getId() {
	exeGet();
	return id;
    }

    public Key getKey() {
	return key;
    }

    public String getName() {
	exeGet();
	return name;
    }

    public List<OssOperation> getOssOperationList() {
	return ossOperationList;
    }

    public Integer getProjectId() {
	exeSet();
	return projectId;
    }

    public String getServerCode() {
	exeGet();
	return serverCode;
    }

    public Integer getServerId() {
	exeGet();
	return serverId;
    }

    public String getServerProvider() {
	exeGet();
	return serverProvider;
    }

    public Date getUpdateTime() {
	exeGet();
	return updateTime;
    }

    public String getUpdateUser() {
	exeGet();
	return updateUser;
    }

    public String getUrl() {
	exeGet();
	return url;
    }

    public void setCommunicateKey(String communicateKey) {
	this.communicateKey = communicateKey;
	exeSet();
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
	exeSet();
    }

    public void setCreateUser(String createUser) {
	this.createUser = createUser;
	exeSet();
    }

    public void setId(Integer id) {
	this.id = id;
	exeSet();
    }

    public void setKey(Key key) {
	this.key = key;
    }

    public void setName(String name) {
	this.name = name;
	exeSet();
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
	this.ossOperationList = ossOperationList;
    }

    public void setProjectId(Integer projectId) {
	this.projectId = projectId;
	exeSet();
    }

    public void setServerCode(String serverCode) {
	this.serverCode = serverCode;
	exeGet();
    }

    public void setServerId(Integer integer) {
	this.serverId = integer;
	exeSet();
    }

    public void setServerProvider(String serverProvider) {
	this.serverProvider = serverProvider;
	exeSet();
    }

    public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
	exeSet();
    }

    public void setUpdateUser(String updateUser) {
	this.updateUser = updateUser;
	exeSet();
    }

    public void setUrl(String url) {
	this.url = url;
	exeSet();
    }

}