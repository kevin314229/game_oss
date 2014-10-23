package com.jcwx.game.admin.message;

import java.util.Collections;
import java.util.Comparator;
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
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.OssFunctionAdjust;
import com.jcwx.game.service.ISyncMoudle;
import com.jcwx.game.service.oss.impl.OssLogService;


/**
 * 功能调整
 * 
 * @author 2013-11-23
 */
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class FunctionAdjustAction extends BasalAction {

    private static final String OSS_OBJECT_LIST = "ossObjectList";

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String functionId;
    private String[] itemId1;

    private OssFunctionAdjust ossFunctionAdjust;

    /** 同步功能列表 */
    private List<OssFunctionAdjust> ossFunctionAdjustList;
    @Autowired
    private OssLogService ossLogService;

    /** 同步功能 oss服务器列表 */
    private List<OssServer> ossServersList;

    /** 同步的服务器Id */
    private String serverArray;
    @Autowired
    private ISyncMoudle syncMoudle;

    /** 同步内容类型1为功能点 2为修改活动 3为系统活动公告4为活动商城 5为系统活动 6为每日首充 */
    private String syncType;

    /** 同步功能点的类型 */
    private String types;

    @Action(value = "functionAdjust_add", results = { @Result(name = "success", location = "../../admin/message/functionAdjust_add.jsp") })
    public String addFunctionAdjust() throws Exception {
	return SUCCESS;
    }

    @Action(value = "functionAdjust_del", results = { @Result(name = "success", type = "chain", location = "functionAdjust_reflash", params = {
	    "actionMsg", "${actionMsg}" }) })
    public String deleteFunctionAdjust() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	String functionIds = new String();
	if (ossFunctionAdjust != null) {
	    functionIds = ossFunctionAdjust.getFunctionId().toString();
	}
	object.put("handlerName", "FunctionAdjustHandler");
	object.put("methodName", "deleteFunctionAdjust");
	object.put("functionIds", functionIds);
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	setActionMsg(CodeUtil.getActionMsgByMap(object));
	return SUCCESS;
    }

    @Action(value = "functionAdjust_delMulti", results = { @Result(name = "success", type = "chain", location = "functionAdjust_reflash", params = {
	    "actionMsg", "${actionMsg}" }) })
    public void delMultipleFunctionAdjust() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	String functionIds = new String();
	if (itemId1 != null && itemId1.length > 0) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < itemId1.length; i++) {
		sb.append(itemId1[i] + ",");
	    }
	    sb.deleteCharAt(sb.length() - 1);
	    functionIds = sb.toString();
	} else if (ossFunctionAdjust != null) {
	    functionIds = ossFunctionAdjust.getFunctionId().toString();
	}

	object.put("handlerName", "FunctionAdjustHandler");
	object.put("methodName", "deleteFunctionAdjust");
	object.put("functionIds", functionIds);
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	setActionMsg(CodeUtil.getActionMsgByMap(object));
	getPageMessage().setNavTabId("w_功能点调整");
	getJSONResponse().responseJson(getPageMessage());
    }

    @Action(value = "functionAdjust_doAdd", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "functionAdjust_index", "namespace",
	    "/admin/message", "actionMsg", "${actionMsg}" }) })
    public String doAddFunctionAdjust() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "FunctionAdjustHandler");
	object.put("methodName", "addFunctionAdjust");
	object.put("ossFunctionAdjust", ossFunctionAdjust);
	int code = 1;
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		code = (Integer) object.get("code");
		ossLogService.createOssLog(OssLogConstant.ADD_FUNCTION_ADJUST,
			ToStringBuilder.reflectionToString(ossFunctionAdjust));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	if (code == 0) {
	    setActionMsg("ok");
	} else if (code == 2) {
	    setActionMsg("Do not allow to repeat ");
	} else {
	    setActionMsg("ERROR");
	}
	return SUCCESS;
    }

    public String getFunctionId() {
	return functionId;
    }

    /** 查询服务器列表 */
    @Action(value = "functionAdjust_server", results = { @Result(name = "success", location = "../../admin/message/serverSyncList.jsp") })
    public String getFunctionServerList() throws Exception {
	// Map<String, Object> object = new HashMap<String, Object>();
	// object.put("handlerName", "FunctionAdjustHandler");
	// object.put("methodName", "queryFunctionAdjust");
	// object.put("functionId", ossFunctionAdjust.getFunctionId());
	// try {
	// if (object != null && !object.isEmpty()) {
	// object = getConnection().sendMsg(baseAdminContext, object);
	// ossFunctionAdjust = (OssFunctionAdjust) object
	// .get("ossFunctionAdjust");
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	/** 查询成功后,服务器列表赋值 */
	ossServersList = getBaseAdminContext().getOssServers();
	// OssServer ossServer = baseAdminContext.getCurrentOssServer();
	// OssServer delServer = new OssServer();
	// for(OssServer temp:ossServersList){
	// if(temp.getId().intValue()==ossServer.getId().intValue()){
	// delServer=temp;
	// }
	// }
	// ossServersList.remove(delServer);
	return SUCCESS;
    }

    private String getHandleName() {
	return "FunctionAdjustHandler";
    }

    public String[] getItemId1() {
	return itemId1;
    }

    public OssFunctionAdjust getOssFunctionAdjust() {
	return ossFunctionAdjust;
    }

    public List<OssFunctionAdjust> getOssFunctionAdjustList() {
	return ossFunctionAdjustList;
    }

    public List<OssServer> getOssServersList() {
	return this.ossServersList;
    }

    public String getServerArray() {
	return this.serverArray;
    }

    public String getSyncType() {
	return syncType;
    }

    public String getTypes() {
	return this.types;
    }

    /** 刷新服务器功能调整 */
    @Action(value = "functionAdjust_reflash", results = { @Result(name = "success", type = "chain", location = "functionAdjust_index") })
    public String modifyActivity() throws Exception {
	setActionMsg("ok");
	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("handlerName", "FunctionAdjustHandler");
	    object.put("methodName", "refurbish");
	    object = CONNECTION.sendMsg(object);
	    setActionMsg(CodeUtil.getActionMsgByMap(object));
	} catch (Exception e) {
	    setActionMsg("no");
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    @Action(value = "functionAdjust_index", results = { @Result(name = "success", location = "../../admin/message/functionAdjust.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "FunctionAdjustHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossFunctionAdjustList = (List<OssFunctionAdjust>) object
			.get("ossFunctionAdjustList");
		// 排序一下
		Collections.sort(ossFunctionAdjustList,
			new Comparator<OssFunctionAdjust>() {
			    @Override
			    public int compare(OssFunctionAdjust p2,
				    OssFunctionAdjust p1) {
				return p2.getFunctionType().compareTo(
					p1.getFunctionType());
			    }
			});
		/** 查询成功后,服务器列表赋值 */
		// ossServersList = baseAdminContext.getOssServers();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "functionAdjust_query", results = { @Result(name = "success", location = "../../admin/message/functionAdjust_edit.jsp") })
    public String queryFunctionAdjust() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "FunctionAdjustHandler");
	object.put("methodName", "queryFunctionAdjust");
	object.put("functionId", ossFunctionAdjust.getFunctionId());
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossFunctionAdjust = (OssFunctionAdjust) object
			.get("ossFunctionAdjust");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setFunctionId(String functionId) {
	this.functionId = functionId;
    }

    public void setItemId1(String[] itemId1) {
	this.itemId1 = itemId1;
    }

    public void setOssFunctionAdjust(OssFunctionAdjust ossFunctionAdjust) {
	this.ossFunctionAdjust = ossFunctionAdjust;
    }

    public void setOssFunctionAdjustList(
	    List<OssFunctionAdjust> ossFunctionAdjustList) {
	this.ossFunctionAdjustList = ossFunctionAdjustList;
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

    // 同步到其他服务器代码
    @Action(value = "functionAdjust_syn", results = { @Result(name = "success", location = "../../admin/message/functionAdjust.jsp") })
    public void synAdjustActivity() throws Exception {

	JSONResponse jsonResponse = JSONResponse
		.newInstance(ServletActionContext.getResponse());

	Map<String, Object> querySynMap = Maps.newHashMap();
	querySynMap.put("handlerName", getHandleName());
	querySynMap.put("methodName", "querySynFunctionAdjust");
	querySynMap.put("types", types);

	Map<String, Object> querySynList = Maps.newHashMap();
	querySynList.put("handlerName", getHandleName());
	querySynList.put("methodName", "synFunctionAdjust");
	JSONObject object = new JSONObject();
	object.put("id", serverArray);
	object.put(
		"msg",
		syncMoudle.syncMoudle(querySynMap, querySynList,
			serverArray.split(",")));
	jsonResponse.responseJson(object);
    }

    @Action(value = "functionAdjust_update", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "functionAdjust_index", "namespace",
	    "/admin/message", "actionMsg", "${actionMsg}" }) })
    public String updateFunctionAdjust() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "FunctionAdjustHandler");
	object.put("methodName", "updateFunctionAdjust");
	object.put("ossFunctionAdjust", ossFunctionAdjust);
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	setActionMsg(CodeUtil.getActionMsgByMap(object));
	return SUCCESS;
    }

}
