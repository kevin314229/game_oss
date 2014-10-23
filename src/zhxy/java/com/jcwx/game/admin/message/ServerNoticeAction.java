package com.jcwx.game.admin.message;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.domain.ServerNotice;
import com.jcwx.game.service.oss.impl.ServerNoticeService;

/**
 * 系统维护公告
 * 
 * @author csp
 * 
 */
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class ServerNoticeAction extends BasalAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ServerNotice serverNotice;

    private List<ServerNotice> serverNoticeList;

    @Autowired
    private ServerNoticeService serverNoticeService;

    @Action(value = "serverNotice_doAdd", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "serverNotice_index", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", type = "redirectAction", params = {
		    "actionName", "serverNotice_index", "namespace",
		    "/admin/message", "errorInfo", "${errorInfo}" }) })
    public String doAdd() throws Exception {
	serverNotice.setServerId(getBaseAdminContext().getCurrentOssServer()
		.getId());
	serverNotice.setCreateTime(new java.util.Date());
	serverNotice.setCreateUserId(getBaseAdminContext().getOssUser()
		.getUsername());
	try {

	    serverNoticeService.createServerNotice(serverNotice);
	    this.setActionMsg("ok");
	} catch (Exception e) {
	    this.setErrorInfo("error");
	    return ERROR;
	}
	return SUCCESS;
    }

    @Action(value = "serverNotice_doDelete", results = { @Result(name = "success", location = "../../zhxy/message/serverNotice.jsp") })
    public String doDelete() throws Exception {
	try {

	    serverNoticeService.deleteServerNoticeByID(serverNotice
		    .getNoticeId().toString());
	    serverNoticeList = serverNoticeService
		    .getServerNoticeByServerID(getBaseAdminContext()
			    .getCurrentOssServer().getId());
	    this.setActionMsg("ok");
	} catch (Exception e) {
	    this.setErrorInfo("error");
	}

	return SUCCESS;
    }

    @Action(value = "serverNotice_doUpdate", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "serverNotice_index", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", type = "redirectAction", params = {
		    "actionName", "serverNotice_index", "namespace",
		    "/admin/message", "errorInfo", "${errorInfo}" }) })
    public String doUpdate() throws Exception {
	try {
	    ServerNotice notice = serverNoticeService
		    .getServerNoticeByID(serverNotice.getNoticeId().toString());
	    notice.setNotiveContent(serverNotice.getNotiveContent());
	    notice.setUpdateTime(new java.util.Date());
	    notice.setUpdateUserId(getBaseAdminContext().getOssUser()
		    .getUsername());
	    serverNoticeService.updateServerNotice(notice);
	    this.setActionMsg("ok");
	} catch (Exception e) {
	    this.setErrorInfo("error");
	    return ERROR;
	}
	return SUCCESS;
    }

    public ServerNotice getServerNotice() {
	return serverNotice;
    }

    public List<ServerNotice> getServerNoticeList() {
	return serverNoticeList;
    }

    @Action(value = "serverNotice_index", results = { @Result(name = "success", location = "../../zhxy/message/serverNotice.jsp") })
    public String index() throws Exception {
	serverNoticeList = serverNoticeService
		.getServerNoticeByServerID(getBaseAdminContext()
			.getCurrentOssServer().getId());
	return SUCCESS;
    }

    public void setServerNotice(ServerNotice serverNotice) {
	this.serverNotice = serverNotice;
    }

    public void setServerNoticeList(List<ServerNotice> serverNoticeList) {
	this.serverNoticeList = serverNoticeList;
    }

    @Action(value = "serverNotice_toAdd", results = { @Result(name = "success", location = "../../zhxy/message/serverNotice_add.jsp") })
    public String toAdd() throws Exception {
	return SUCCESS;
    }

    @Action(value = "serverNotice_toUpdate", results = { @Result(name = "success", location = "../../zhxy/message/serverNotice_edit.jsp") })
    public String toUpdate() throws Exception {
	serverNotice = serverNoticeService.getServerNoticeByID(serverNotice
		.getNoticeId().toString());
	return SUCCESS;
    }

}
