package com.jcwx.game.admin.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.admin.jsonview.JSONResponse;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.exception.ActionValidateException;
import com.jcwx.game.http.domain.OssChildActivity;
import com.jcwx.game.http.domain.OssSystemActivity;
import com.jcwx.game.service.ISyncMoudle;
import com.jcwx.game.service.oss.impl.OssLogService;
import com.jcwx.game.web.Global;

/**
 * 系统活动 action
 * 
 * @author 2013-11-25
 */
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class SystemActivityAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 系统活动ids类型 */
    private String ids;
    @Autowired
    private OssLogService ossLogService;
    private List<OssServer> ossServersList;

    private OssSystemActivity ossSystemActivity;

    private List<OssSystemActivity> ossSystemActivityList;

    private String serverArray;
    @Autowired
    private ISyncMoudle syncMoudle;

    // 系统子活动列表
    private List<OssChildActivity> systemChildActivityList;

    /** 系统活动ids类型 （新） */
    private String types;

    @Action(value = "systemActivity_add", results = { @Result(name = "success", location = "../../admin/message/systemActivity_add.jsp") })
    public String addSystemActivity() throws Exception {
	systemChildActivityList = Global
		.getOssChildActivityList(getBaseAdminContext()
			.getCurrentOssServer().getUrl());
	return SUCCESS;
    }

    @Action(value = "systemActivity_del", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "systemActivity_index", "namespace",
	    "/admin/message", "actionMsg", "${actionMsg}" }) })
    public String deleteMallActivity() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "SystemActivityHandler");
	object.put("methodName", "doDelete");
	object.put("systemActivityId", ossSystemActivity.getSystemActivityId());
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		setActionMsg(CodeUtil.getActionMsgByMap(object));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return SUCCESS;
    }

    @Action(value = "systemActivity_doAdd", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "systemActivity_index", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", type = "redirectAction", params = {
		    "actionName", "systemActivity_index", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }) })
    public String doAddSystemActivity() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "SystemActivityHandler");
	object.put("methodName", "doAdd");
	// 设置00开启 59结束
	ossSystemActivity.setStartTime(DateService
		.getDateFirstTime(ossSystemActivity.getStartTime()));
	ossSystemActivity.setEndTime(DateService
		.getDateLastTime(ossSystemActivity.getEndTime()));
	systemChildActivityList = Global
		.getOssChildActivityList(getBaseAdminContext()
			.getCurrentOssServer().getUrl());
	object.put("ossSystemActivity", ossSystemActivity);
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		
		setActionMessage(CodeUtil.getCode(object));
		
		ossLogService.createOssLog(OssLogConstant.ADD_SYSTEM_ACTIVITY,
			ToStringBuilder.reflectionToString(ossSystemActivity));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public String getIds() {
	return this.ids;
    }

    public List<OssServer> getOssServersList() {
	return this.ossServersList;
    }

    public OssSystemActivity getOssSystemActivity() {
	return ossSystemActivity;
    }

    public List<OssSystemActivity> getOssSystemActivityList() {
	return ossSystemActivityList;
    }

    public String getServerArray() {
	return this.serverArray;
    }

    public List<OssChildActivity> getSystemChildActivityList() {
	return systemChildActivityList;
    }

    public String getTypes() {
	return types;
    }

    @Action(value = "systemActivity_index", results = { @Result(name = "success", location = "../../admin/message/systemActivity.jsp") })
    public String index() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "SystemActivityHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossSystemActivityList = (List<OssSystemActivity>) object
			.get("ossSystemActivityList");
		// setOssServersList( baseAdminContext.getOssServers());
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "systemActivity_query", results = { @Result(name = "success", location = "../../admin/message/systemActivity_edit.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "SystemActivityHandler");
	object.put("methodName", "query");
	object.put("systemActivityId", ossSystemActivity.getSystemActivityId());
	try {
	    systemChildActivityList = Global
		    .getOssChildActivityList(getBaseAdminContext()
			    .getCurrentOssServer().getUrl());
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossSystemActivity = (OssSystemActivity) object
			.get("ossSystemActivity");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    /** 刷新服务器功能调整 */
    @Action(value = "systemActivity_reflash", results = { @Result(name = "success", type = "chain", location = "systemActivity_index") })
    public String reflashMallActivity() throws Exception {
	setActionMsg("ok");
	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("handlerName", "SystemActivityHandler");
	    object.put("methodName", "refurbish");
	    object = CONNECTION.sendMsg(object);
	    setActionMsg(CodeUtil.getActionMsgByMap(object));
	} catch (Exception e) {
	    setActionMsg("no");
	    e.printStackTrace();
	}

	return SUCCESS;
    }

    private void setActionMessage(int code) {
	if (code == 0) {
	    setActionMsg("OK");
	} else if (code == 2) {
	    throw new ActionValidateException(ActionValidateException.ERROR,
		    this, "Activity is invalid");
	} else if (code == 3) {
	    throw new ActionValidateException(ActionValidateException.ERROR,
		    this, " Time is overlap ");
	} else {
	    throw new ActionValidateException(ActionValidateException.ERROR,
		    this, "ERROR");
	}
    }

    public void setIds(String ids) {
	this.ids = ids;
    }

    public void setOssServersList(List<OssServer> ossServersList) {
	this.ossServersList = ossServersList;
    }

    public void setOssSystemActivity(OssSystemActivity ossSystemActivity) {
	this.ossSystemActivity = ossSystemActivity;
    }

    public void setOssSystemActivityList(
	    List<OssSystemActivity> ossSystemActivityList) {
	this.ossSystemActivityList = ossSystemActivityList;
    }

    public void setServerArray(String serverArray) {
	this.serverArray = serverArray;
    }

    public void setSystemChildActivityList(
	    List<OssChildActivity> systemChildActivityList) {
	this.systemChildActivityList = systemChildActivityList;
    }

    public void setTypes(String types) {
	this.types = types;
    }

    // 同步到其他服务器代码
    @Action(value = "systemActivity_syn", results = { @Result(name = "success", location = "../../admin/message/systemActivity.jsp") })
    public void synSystemActivity() throws Exception {
	JSONResponse jsonResponse = JSONResponse
		.newInstance(ServletActionContext.getResponse());

	Map<String, Object> querySynMap = Maps.newHashMap();
	querySynMap.put("handlerName", "SystemActivityHandler");
	querySynMap.put("methodName", "querySynSystemActivity");
	querySynMap.put("ids", types);

	Map<String, Object> querySynList = Maps.newHashMap();
	querySynList.put("handlerName", "SystemActivityHandler");
	querySynList.put("methodName", "synSystemActivity");
	JSONObject object = new JSONObject();
	object.put("id", serverArray);
	object.put(
		"msg",
		syncMoudle.syncMoudle(querySynMap, querySynList,
			serverArray.split(",")));
	jsonResponse.responseJson(object);
    }

    @Action(value = "systemActivity_update", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "systemActivity_index", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", location = "../../admin/message/systemActivity_add.jsp") })
    public String updateMallActivity() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "SystemActivityHandler");
	object.put("methodName", "doUpdate");
	ossSystemActivity.setStartTime(DateService
		.getDateFirstTime(ossSystemActivity.getStartTime()));
	ossSystemActivity.setEndTime(DateService
		.getDateLastTime(ossSystemActivity.getEndTime()));
	systemChildActivityList = Global
		.getOssChildActivityList(getBaseAdminContext()
			.getCurrentOssServer().getUrl());
	object.put("ossSystemActivity", ossSystemActivity);
	setActionMsg("ok");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		Integer code = (Integer) object.get("code");
		if (code == null || code.intValue() != 0) {
		    setActionMsg("no");
		}
		ossLogService.createOssLog(
			OssLogConstant.MODIFY_SYSTEM_ACTIVITY,
			ToStringBuilder.reflectionToString(ossSystemActivity));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return SUCCESS;
    }

}
