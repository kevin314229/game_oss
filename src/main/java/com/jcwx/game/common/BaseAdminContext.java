package com.jcwx.game.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.jcwx.game.domain.OssMenu;
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.OssUser;
import com.jcwx.game.domain.Project;

/** 管理员登录环境 */
public class BaseAdminContext implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8403789848825928467L;

    private String contentType;

    /**
     * 连接到服务器的url
     */
    private OssServer currentOssServer;
    
    private String menuAutoComplite;

    /** 请求的IP */
    private String ipAddr;

    /** 需要过滤的路径与权限 */
    private Set<String> menuSet = null;

    /** 当前管理员所在服务区的名称 */
    private String name;

    /** 当前管理员所在的运营商ID */
    private int operationId;

    /** 当前管理员所在的运营商 */
    private String operationName;

    private List<OssMenu> OssMenus;
    /** 平台接入口 **/
    private List<OssOperation> ossOperationList;
    /** 管理员当前拥有权限的服务器区信息 **/
    private List<OssServer> ossServers;
    // 暂时启用
    private List<OssServer> OssServersPt;
    /** 管理员 */
    private OssUser ossUser;

    private Project project;

    // 用户管理的项目列表
    private List<Project> projectList;

    /** 平台标识 */
    private String serverCode;

    /** 当前管理员所在的服务器区 **/
    private int serverId;
    /** SSO端的的管理员sessionId */
    private String sessionId;

    /** 判断用户是否有权限 */
    public Boolean allow(String key) {
	if (StringUtils.isEmpty(key)) {
	    return false;
	}
	if (key.endsWith("gif")) {
	    return true;
	}
	if (menuSet == null || menuSet.isEmpty()) {
	    return false;
	}
	return menuSet.contains(key);
    }

    public String getContentType() {
	return this.contentType;
    }

    public OssServer getCurrentOssServer() {
	return currentOssServer;
    }

    public String getHttpUrl() {
	return "http://127.0.0.1:8081/hello";
    }

    public String getIpAddr() {
	return this.ipAddr;
    }

    public Set<String> getMenuSet() {
	return menuSet;
    }

    public String getName() {
	return name;
    }

    public int getOperationId() {
	return operationId;
    }

    public String getOperationName() {
	return operationName;
    }

    public List<OssMenu> getOssMenus() {
	return this.OssMenus;
    }

    public List<OssOperation> getOssOperationList() {
	return ossOperationList;
    }

    public OssServer getOssServerById(Integer id) {
	OssServer oss = null;
	for (OssServer o : this.ossServers) {
	    if (o.getId().intValue() == id) {
		oss = o;
		break;
	    }
	}
	return oss;
    }

    public List<OssServer> getOssServers() {
	return ossServers;
    }

    public List<OssServer> getOssServersPt() {
	return OssServersPt;
    }

    public OssUser getOssUser() {
	return ossUser;
    }

    public Project getProject() {
	return project;
    }

    public List<Project> getProjectList() {
	return projectList;
    }

    public String getServerCode() {
	return serverCode;
    }

    public int getServerId() {
	return serverId;
    }

    public String getSessionId() {
	return sessionId;
    }

    public void setContentType(String contentType) {
	this.contentType = contentType;
    }

    public void setCurrentOssServer(OssServer currentOssServer) {
	this.currentOssServer = currentOssServer;
    }

    public void setIpAddr(String ipAddr) {
	this.ipAddr = ipAddr;
    }

    public void setMenuSet(Set<String> menuSet) {
	this.menuSet = menuSet;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setOperationId(int operationId) {
	this.operationId = operationId;
    }

    public void setOperationName(String operationName) {
	this.operationName = operationName;
    }

    public void setOssMenus(List<OssMenu> ossMenus) {
	this.OssMenus = ossMenus;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
	this.ossOperationList = ossOperationList;
    }

    public void setOssServers(List<OssServer> ossServers) {
	this.ossServers = ossServers;
    }

    public void setOssServersPt(List<OssServer> ossServersPt) {
	OssServersPt = ossServersPt;
    }

    public void setOssUser(OssUser ossUser) {
	this.ossUser = ossUser;
    }

    public void setProject(Project project) {
	this.project = project;
    }

    public void setProjectList(List<Project> projectList) {
	this.projectList = projectList;
    }

    public void setServerCode(String serverCode) {
	this.serverCode = serverCode;
    }

    public void setServerId(int serverId) {
	this.serverId = serverId;
    }

    public void setSessionId(String sessionId) {
	this.sessionId = sessionId;
    }

    public String getMenuAutoComplite() {
        return menuAutoComplite;
    }

    public void setMenuAutoComplite(String menuAutoComplite) {
        this.menuAutoComplite = menuAutoComplite;
    }

    
}
