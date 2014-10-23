package com.jcwx.game.admin.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.admin.jsonview.JSONResponse;
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.OssRechargeActivity;
import com.jcwx.game.http.domain.OssRechargeActivityDetail;
import com.jcwx.game.service.ISyncMoudle;
import com.jcwx.game.service.oss.impl.OssLogService;


/**
 * 首充奖励
 * 
 * @author csp
 * 
 */
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class RechargeActivityAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static boolean checkItem(String item) {
	if (StringUtils.isBlank(item)) {
	    return false;
	}
	if (!StringUtils.contains(item, ",")) {
	    return false;
	}
	String[] items = StringUtils.split(item, "#");
	if (items.length == 0) {
	    return false;
	}
	return true;
    }

    private String activityStr;

    private String[] itemId;
    private String itemIds;

    @Autowired
    private OssLogService ossLogService;
    private OssRechargeActivity ossRechargeActivity;
    private List<OssRechargeActivity> ossRechargeActivityList;

    /** 同步功能 oss服务器列表 */
    private List<OssServer> ossServersList;

    /** 同步的服务器Id */
    private String serverArray;

    @Autowired
    private ISyncMoudle syncMoudle;

    /** 同步类型 */
    private String syncType;

    /** 同步功能点的类型 */
    private String types;

    @Action(value = "rechargeActivity_delete", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "rechargeActivity_index", "namespace",
	    "/admin/message", "actionMsg", "${actionMsg}" }) })
    public String delete() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "RechargeActivityHandler");
	object.put("methodName", "deleteRechargeActivity");
	object.put("rechargeActivityId",
		ossRechargeActivity.getRecharegeActivityId());
	if (object != null && !object.isEmpty()) {
	    object = CONNECTION.sendMsg(object);

	    setActionMsg(CodeUtil.getActionMsgByMap(object));

	    ossLogService.createOssLog(OssLogConstant.ADD_MODIFY_ACTIVITY,
		    activityStr);

	}
	return SUCCESS;
    }

    @Action(value = "rechargeActivity_doAdd", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "rechargeActivity_index", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", type = "redirectAction", params = {
		    "actionName", "rechargeActivity_index", "namespace",
		    "/admin/message", "errorInfo", "${errorInfo}" }) })
    public String doAdd() throws Exception {
	int code = 0;
	List<OssRechargeActivityDetail> ossRechargeActivityDetailList = new ArrayList<OssRechargeActivityDetail>();
	List<OssRechargeActivityDetail> ossRechargeActivityDetails = JSON
		.parseArray(activityStr, OssRechargeActivityDetail.class);
	for (OssRechargeActivityDetail ossRechargeActivityDetail : ossRechargeActivityDetails) {
	    if (!StringUtils.isNotBlank(ossRechargeActivityDetail.getItem())) {
		continue;
	    }
	    if (!checkItem(ossRechargeActivityDetail.getItem())) {
		setActionMsg("Please input correct Reward items format");
		return "success";
	    }
	    ossRechargeActivityDetailList.add(ossRechargeActivityDetail);
	}
	ossRechargeActivity
		.setOssRechargeActivityDetailList(ossRechargeActivityDetailList);
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "RechargeActivityHandler");
	object.put("methodName", "addRechargeActivity");
	object.put("ossRechargeActivity", ossRechargeActivity);
	if (object != null && !object.isEmpty()) {
	    object = CONNECTION.sendMsg(object);
	    code = (Integer) object.get("code");
	    ossLogService.createOssLog(OssLogConstant.ADD_RECHARGE_ACTIVITY,
		    activityStr);
	    if (code == 0) {
		setActionMsg("OK");
	    } else if (code == 2) {
		setErrorInfo("Do not allow to repeat ");
		return ERROR;
	    } else {
		setErrorInfo("ERROR");
		return ERROR;
	    }
	}
	return SUCCESS;
    }

    @Action(value = "rechargeActivity_doUpdate", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "rechargeActivity_index", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", location = "../../admin/message/rechargeActivity_edit.jsp") })
    public String doUpdate() throws Exception {
	List<OssRechargeActivityDetail> ossRechargeActivityDetailList = new ArrayList<OssRechargeActivityDetail>();
	List<OssRechargeActivityDetail> ossRechargeActivityDetails = JSON
		.parseArray(activityStr, OssRechargeActivityDetail.class);
	for (OssRechargeActivityDetail ossRechargeActivityDetail : ossRechargeActivityDetails) {
	    if (!StringUtils.isNotBlank(ossRechargeActivityDetail.getItem())) {
		continue;
	    }
	    if (!checkItem(ossRechargeActivityDetail.getItem())) {
		setActionMsg("Please input correct Reward items format");
		return "ERROR";
	    }
	    ossRechargeActivityDetailList.add(ossRechargeActivityDetail);
	}
	ossRechargeActivity
		.setOssRechargeActivityDetailList(ossRechargeActivityDetailList);
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "RechargeActivityHandler");
	object.put("methodName", "updateRechargeActivity");
	object.put("ossRechargeActivity", ossRechargeActivity);
	if (object != null && !object.isEmpty()) {
	    object = CONNECTION.sendMsg(object);
	    ossLogService.createOssLog(OssLogConstant.ADD_RECHARGE_ACTIVITY,
		    activityStr);
	    setActionMsg(CodeUtil.getActionMsgByMap(object));
	}
	return SUCCESS;
    }

    public String getActivityStr() {
	return activityStr;
    }

    public OssRechargeActivity getOssRechargeActivity() {
	return ossRechargeActivity;
    }

    public List<OssRechargeActivity> getOssRechargeActivityList() {
	return ossRechargeActivityList;
    }

    public List<OssServer> getOssServersList() {
	return ossServersList;
    }

    public String getServerArray() {
	return serverArray;
    }

    public String getSyncType() {
	return syncType;
    }

    public String getTypes() {
	return types;
    }

    /** 刷新服务器活动 */
    @Action(value = "rechargeActivity_reflash", results = { @Result(name = "success", type = "chain", location = "rechargeActivity_index") })
    public String modifyActivity() throws Exception {
	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("handlerName", "RechargeActivityHandler");
	    object.put("methodName", "reflashActivity");
	    object = CONNECTION.sendMsg(object);
	    if (!object.get("code").equals(0)) {
		setActionMsg("no");
	    }
	} catch (Exception e) {
	    setActionMsg("no");
	    e.printStackTrace();
	}
	setActionMsg("ok");
	return SUCCESS;
    }

    @Action(value = "rechargeActivity_index", results = { @Result(name = "success", location = "../../admin/message/rechargeActivity.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "RechargeActivityHandler");
	if (object != null && !object.isEmpty()) {
	    object = CONNECTION.sendMsg(object);
	    ossRechargeActivityList = (List<OssRechargeActivity>) object
		    .get("ossRechargeActivityList");
	}
	return SUCCESS;
    }

    /**
     * 同步活动
     * 
     * @throws Exceptions
     */
    @Action(value = "rechargeActivity_syn", results = { @Result(name = "success", location = "../../admin/message/rechargeActivity.jsp") })
    public String recharegeActivitySyn() throws Exception {
	JSONResponse jsonResponse = JSONResponse
		.newInstance(ServletActionContext.getResponse());

	Map<String, Object> querySynMap = Maps.newHashMap();
	querySynMap.put("handlerName", "RechargeActivityHandler");
	querySynMap.put("methodName", "querySynRechargeActivity");
	querySynMap.put("types", types);

	Map<String, Object> querySynList = Maps.newHashMap();
	querySynList.put("handlerName", "RechargeActivityHandler");
	querySynList.put("methodName", "synRechargeActivity");
	JSONObject object = new JSONObject();
	object.put("id", serverArray);
	object.put(
		"msg",
		syncMoudle.syncMoudle(querySynMap, querySynList,
			serverArray.split(",")));
	jsonResponse.responseJson(object);
	return null;
    }

    public void setActivityStr(String activityStr) {
	this.activityStr = activityStr;
    }

    public void setOssRechargeActivity(OssRechargeActivity ossRechargeActivity) {
	this.ossRechargeActivity = ossRechargeActivity;
    }

    public void setOssRechargeActivityList(
	    List<OssRechargeActivity> ossRechargeActivityList) {
	this.ossRechargeActivityList = ossRechargeActivityList;
    }

    public void setOssServersList(List<OssServer> ossServersList) {
	this.ossServersList = ossServersList;
    }

    public void setServerArray(String serverArray) {
	this.serverArray = serverArray;
    }

    public void setSyncType(String syncType) {
	this.syncType = syncType;
    }

    public void setTypes(String types) {
	this.types = types;
    }

    /** 获取同步活动服务器 */
    @Action(value = "rechargeActivity_server", results = { @Result(name = "success", location = "../../admin/message/serverSyncList.jsp") })
    public String snyActivity() {

	/** 查询成功后,服务器列表赋值 */
	ossServersList = getBaseAdminContext().getOssServers();
	return SUCCESS;
    }

    @Action(value = "rechargeActivity_toAdd", results = { @Result(name = "success", location = "../../admin/message/rechargeActivity_add.jsp") })
    public String toAdd() throws Exception {
	return SUCCESS;
    }

    @Action(value = "rechargeActivity_toUpdate", results = { @Result(name = "success", location = "../../admin/message/rechargeActivity_edit.jsp") })
    public String toUpdate() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "RechargeActivityHandler");
	object.put("methodName", "queryRechargeActivity");
	object.put("recharegeActivityId",
		ossRechargeActivity.getRecharegeActivityId());
	if (object != null && !object.isEmpty()) {
	    object = CONNECTION.sendMsg(object);
	    ossRechargeActivity = (OssRechargeActivity) object
		    .get("ossRechargeActivity");
	}
	return SUCCESS;
    }

}
