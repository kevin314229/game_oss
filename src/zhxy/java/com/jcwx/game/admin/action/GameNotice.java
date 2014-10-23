package com.jcwx.game.admin.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.domain.ServerNotice;
import com.jcwx.game.service.oss.impl.ServerNoticeService;

/**
 * 服务器系统维护接口
 * 
 * @author csp 2014-4-17
 */
@ParentPackage("base")
@Namespace("/admin")
@ResultPath("/")
@Action(value = "gameNotice")
public class GameNotice extends BasalAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private ServerNoticeService serverNoticeService;
    private String url;

    @Override
    public String execute() throws Exception {
	ServerNotice serverNotice = serverNoticeService
		.getServerNoticeByUrl(url);
	String notice = serverNotice == null ? "" : serverNotice
		.getNotiveContent();
	Map map = new HashMap<String, String>();
	map.put("notice", notice);
	getJSONResponse().responseJson(map);
	return null;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }
}
