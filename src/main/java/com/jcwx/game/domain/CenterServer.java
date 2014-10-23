package com.jcwx.game.domain;

import com.jcwx.game.common.domain.BaseDomain;

public class CenterServer extends BaseDomain {

   private static final long serialVersionUID = 1691789590510827527L;

   private int areaId;
   
   private String name;
   
   private String ip ;
   
   private String loginKey ;
   
   private Integer socketPort;
   
   private String url;
   
   private Integer projectId;
   
   private String ptId;
   
   private String ptCode;
   
   private String serverProvider;
   
   private String updateUser;

   private int  serverId;
    public int getAreaId() {
        return areaId;
    }
    
    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    public Integer getSocketPort() {
        return socketPort;
    }

    public void setSocketPort(Integer socketPort) {
        this.socketPort = socketPort;
    }

    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getPtId() {
        return ptId;
    }

    public void setPtId(String ptId) {
        this.ptId = ptId;
    }

    public String getPtCode() {
        return ptCode;
    }

    public void setPtCode(String ptCode) {
        this.ptCode = ptCode;
    }

    public String getServerProvider() {
        return serverProvider;
    }

    public void setServerProvider(String serverProvider) {
        this.serverProvider = serverProvider;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }
   

}