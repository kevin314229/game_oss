package com.jcwx.game.admin.player;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.domain.BlackList;
import com.jcwx.game.service.oss.IAuthorizationLimitService;

@ParentPackage("base")
@Namespace("/admin/player")
@ResultPath("/")
public class AdminBlackIpAction extends BasalAction {

    @Autowired
    private IAuthorizationLimitService authorizationLimitService;

    private List<BlackList> blackList;

    private String playerIP;

    @Action(value = "adminBlackIp", results = { @Result(name = "success", location = "../../admin/player/adminBlackIp.jsp") })
    public String admin() throws Exception {

	resetInfos();

	// IAuthorizationLimitService authorizationLimitService =
	// (IAuthorizationLimitService)SpringService.getBean("authorizationLimitService");
	if (playerIP != null && !"".equals(playerIP)) {
	    // authorizationLimitService.createBlackList(0, playerIP);
	    setSuccessInfo("封禁IP：" + playerIP + "成功。");
	}
	blackList = authorizationLimitService.getAllBlackList();

	return "success";
    }

    @Action(value = "deleteBlackIp", results = { @Result(name = "success", location = "../../admin/player/adminBlackIp.jsp") })
    public String cancel() throws Exception {
	resetInfos();

	if (playerIP != null && !"".equals(playerIP)) {
	    // IAuthorizationLimitService authorizationLimitService =
	    // (IAuthorizationLimitService)SpringService.getBean("authorizationLimitService");
	    BlackList oneBlackList = authorizationLimitService
		    .getBlackListFromCacheByIP(playerIP);
	    oneBlackList.setBanState(0);
	    // authorizationLimitService.updateBlackList(0, oneBlackList);
	    setSuccessInfo("解除IP：" + playerIP + "封禁成功。");
	    blackList = authorizationLimitService.getAllBlackList();
	}

	return "success";
    }

    public List<BlackList> getBlackList() {
	return blackList;
    }

    public String getPlayerIP() {
	return playerIP;
    }

    public void setPlayerIP(String playerIP) {
	this.playerIP = playerIP;
    }

}
