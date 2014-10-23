package com.jcwx.game.admin.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.GameServer;
import com.jcwx.game.service.oss.IOssServerService;

@ParentPackage("base")
@Namespace("/admin")
@ResultPath("/")
@Action(value = "findServer")
public class FindServer extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private IOssServerService ossServerService;

    @Override
    public String execute() throws Exception {
	List<OssServer> ossServerList = ossServerService.findAllOssServer();
	List<GameServer> gameServerList = new ArrayList<GameServer>();
	for (OssServer ossServer : ossServerList) {
	    GameServer gameServer = new GameServer();
	    gameServer.setServerId(ossServer.getId());
	    gameServer.setDiaplayName(ossServer.getName());
	    gameServerList.add(gameServer);
	}
	getJSONResponse().responseJson(gameServerList);
	return null;
    }

}
