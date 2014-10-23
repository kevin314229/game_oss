package com.jcwx.game.admin.message;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.MallActivityProperty;
import com.jcwx.game.http.domain.OssMallActivity;
import com.jcwx.game.service.oss.impl.OssLogService;

import com.jcwx.game.web.Global;

/**
 * 活动商城 action
 * 
 * @author 小平 2013-11-25
 */
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class MallActivityAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<MallActivityProperty> mallActivityList;

    // 同步的 商品ID
    private String malls;

    @Autowired
    private OssLogService ossLogService;

    private OssMallActivity ossMallActivity;
    private List<OssMallActivity> ossMallActivityList;
    private List<OssServer> ossServersList;

    // 同步的服务器Id
    private String serverArray;

    // 同步的 商品ID(新 统一类型统一传值）
    private String types;

    @Action(value = "mallActivity_add", results = { @Result(name = "success", location = "../../admin/message/mallActivity_add.jsp") })
    public String addMallActivity() throws Exception {
	mallActivityList = Global.getMallActivityList(getBaseAdminContext()
		.getCurrentOssServer().getUrl());
	return SUCCESS;
    }

    @Action(value = "mallActivity_del", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "mallActivity_index", "namespace", "/admin/message",
	    "actionMsg", "${actionMsg}" }) })
    public String deleteMallActivity() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "MallActivityHandler");
	object.put("methodName", "deleteMallActivity");
	object.put("mallActivityId", ossMallActivity.getMallActivityId());

	setActionMsg(CodeUtil.getActionMsgByMap(CONNECTION.sendMessageX(object)));

	return SUCCESS;
    }

    @Action(value = "mallActivity_doAdd", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "mallActivity_index", "namespace", "/admin/message",
	    "actionMsg", "${actionMsg}" }) })
    public String doAddMallActivity() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "MallActivityHandler");
	object.put("methodName", "addMallActivity");
	object.put("ossMallActivity", ossMallActivity);

	if (ossMallActivity.getStartTime().compareTo(
		ossMallActivity.getEndTime()) > 0) {
	    this.setActionMsg("time is error ");
	    return SUCCESS;
	}
	setActionMsg(CodeUtil.getActionMsgByMap(CONNECTION.sendMessageX(object)));

	ossLogService.createOssLog(OssLogConstant.ADD_MALL_ACTIVITY,
		ToStringBuilder.reflectionToString(ossMallActivity));

	return SUCCESS;
    }

    public List<MallActivityProperty> getMallActivityList() {
	return mallActivityList;
    }

    public String getMalls() {
	return malls;
    }

    public OssMallActivity getOssMallActivity() {
	return ossMallActivity;
    }

    public List<OssMallActivity> getOssMallActivityList() {
	return ossMallActivityList;
    }

    public List<OssServer> getOssServersList() {
	return ossServersList;
    }

    public String getServerArray() {
	return serverArray;
    }

    public String getTypes() {
	return types;
    }

    @Action(value = "mallActivity_index", results = { @Result(name = "success", location = "../../admin/message/mallActivity.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "MallActivityHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossMallActivityList = (List<OssMallActivity>) object
			.get("ossMallActivityList");
		ossServersList = getBaseAdminContext().getOssServers();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "mallActivity_query", results = { @Result(name = "success", location = "../../admin/message/mallActivity_edit.jsp") })
    public String queryMallActivity() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "MallActivityHandler");
	object.put("methodName", "queryMallActivity");
	object.put("mallActivityId", ossMallActivity.getMallActivityId());
	mallActivityList = Global.getMallActivityList(getBaseAdminContext()
		.getCurrentOssServer().getUrl());

	Map<String, Object> returnMap = CONNECTION.sendMessageX(object);

	ossMallActivity = (OssMallActivity) returnMap.get("ossMallActivity");

	return SUCCESS;
    }

    /** 刷新服务器功能调整 */
    @Action(value = "mallActivity_reflash", results = { @Result(name = "success", type = "chain", location = "mallActivity_index") })
    public String reflashMallActivity() throws Exception {
	setActionMsg("ok");
	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("handlerName", "MallActivityHandler");
	    object.put("methodName", "refurbish");
	    object = CONNECTION.sendMsg(object);
	    setActionMsg(CodeUtil.getActionMsgByMap(object));
	} catch (Exception e) {
	    setActionMsg("no");
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setMallActivityList(List<MallActivityProperty> mallActivityList) {
	this.mallActivityList = mallActivityList;
    }

    public void setMalls(String malls) {
	this.malls = malls;
    }

    public void setOssMallActivity(OssMallActivity ossMallActivity) {
	this.ossMallActivity = ossMallActivity;
    }

    public void setOssMallActivityList(List<OssMallActivity> ossMallActivityList) {
	this.ossMallActivityList = ossMallActivityList;
    }

    public void setOssServersList(List<OssServer> ossServersList) {
	this.ossServersList = ossServersList;
    }

    public void setServerArray(String serverArray) {
	this.serverArray = serverArray;
    }

    public void setTypes(String types) {
	this.types = types;
    }

    // 同步到其他服务器代码
    @Action(value = "mallActivity_syn", results = { @Result(name = "success", location = "../../admin/message/mallActivity.jsp") })
    public String synMallActivity() throws Exception {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	StringBuffer buf = new StringBuffer();
	try {
	    Map<String, Object> obj = new HashMap<String, Object>();
	    obj.put("handlerName", "MallActivityHandler");
	    obj.put("methodName", "querySynMallActivity");
	    obj.put("malls", types);
	    obj = CONNECTION.sendMsg(obj);
	    if (obj != null && !obj.isEmpty()) {
		ossMallActivityList = (List<OssMallActivity>) obj
			.get("ossMallActivityList");
		// 同步的其他服务器 ossServersList=baseAdminContext.getOssServers();
		String serverIds[] = serverArray.split(",");
		for (String serverId : serverIds) {
		    int id = Integer.valueOf(serverId);
		    OssServer ossServer = getBaseAdminContext().getOssServerById(id);
		    if (ossServer == null) {
			buf.append("<span class='color-red'>serverId" + id
				+ " not find  </span>");
			continue;
		    }
		    try {
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("handlerName", "MallActivityHandler");
			object.put("methodName", "synMallActivity");
			object.put("ossMallActivityList", ossMallActivityList);
			object = CONNECTION.interfaceSendMsg(Integer.parseInt(serverId), object);
			buf.append(ossServer.getName() + ":"
				+ object.get("result"));

		    } catch (Exception e) {
			e.printStackTrace();
			buf.append(ossServer.getName() + " 服务器 出现问题，请检查。 ");
		    }
		}
	    }
	} catch (Exception e) {
	    buf.append(" 访问服务器商品出现问题，请检查。 ");
	    e.printStackTrace();
	}
	JSONObject object = new JSONObject();
	object.put("id", serverArray);
	object.put("msg", buf.toString());
	out.print(object.toString());
	out.close();
	return SUCCESS;
    }

    @Action(value = "mallActivity_update", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "mallActivity_index", "namespace", "/admin/message",
	    "actionMsg", "${actionMsg}" }) })
    public String updateMallActivity() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "MallActivityHandler");
	object.put("methodName", "updateMallActivity");
	object.put("ossMallActivity", ossMallActivity);

	setActionMsg(CodeUtil.getActionMsgByMap(CONNECTION.sendMessageX(object)));

	ossLogService.createOssLog(OssLogConstant.MODIFY_MALL_ACTIVITY,
		ToStringBuilder.reflectionToString(ossMallActivity));

	return SUCCESS;
    }

}
