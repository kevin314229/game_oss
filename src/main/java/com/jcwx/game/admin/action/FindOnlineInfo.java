package com.jcwx.game.admin.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.service.oss.IOssServerService;

@ParentPackage("base")
@Namespace("/admin")
@ResultPath("/")
public class FindOnlineInfo extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private IOssServerService ossServerService;
    private Integer serverId;

    @Action(value = "findOnlineInfo")
    public void executes() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	OssServer ossServer = ossServerService.getOssServerByID(serverId);
	Map<String, Object> returnMap = new HashMap<String, Object>();
	if (ossServer == null) {
	    returnMap.put("day_max", 0);
	    returnMap.put("current", 0);
	    returnMap.put("server", "找不到服务器");
	} else {
	    object.put("handlerName", "FindOnlineInfoHandler");
	    object = CONNECTION.interfaceSendMsg(serverId, object);
	    returnMap.put("day_max", object.get("day_max"));
	    returnMap.put("current", object.get("current"));
	    returnMap.put("server", ossServer.getName());
	}
	getJSONResponse().responseJson(returnMap);
    }

    public Integer getServerId() {
	return serverId;
    }

    public void setServerId(Integer serverId) {
	this.serverId = serverId;
    }
}
