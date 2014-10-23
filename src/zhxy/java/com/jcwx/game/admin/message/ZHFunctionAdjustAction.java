package com.jcwx.game.admin.message;

import java.util.ArrayList;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.admin.jsonview.JSONResponse;
import com.jcwx.game.common.JSONValidate;
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.exception.ActionValidateException;
import com.jcwx.game.http.domain.ZHOssFunctionAdjust;
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
public class ZHFunctionAdjustAction extends BasalAction {

    private static final String OSS_OBJECT_LIST = "ossObjectList";

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public static Map<Integer, String> toMap(List<JSONObject> list) {
	Map<Integer, String> reust = new HashMap<Integer, String>();
	for (int i = 0; i < list.size(); i++) {
	    JSONObject jsonObject = list.get(i);
	    Map<String, Object> map = JSON.parseObject(jsonObject
		    .toJSONString());
	    reust.put((Integer) map.get("type"), (String) map.get("name"));
	}
	return reust;
    }

    public static List<Map<String, Object>> toMapList(List<JSONObject> list) {
	List<Map<String, Object>> reustList = new ArrayList<Map<String, Object>>();
	for (int i = 0; i < list.size(); i++) {
	    JSONObject jsonObject = list.get(i);
	    Map<String, Object> map = JSON.parseObject(jsonObject
		    .toJSONString());
	    reustList.add(map);
	}
	return reustList;

    }

    public static Map<Integer, String> toTypeMap(List<JSONObject> list, int type) {
	Map<Integer, String> reust = new HashMap<Integer, String>();
	for (int i = 0; i < list.size(); i++) {
	    JSONObject jsonObject = list.get(i);
	    Map<String, Object> map = JSON.parseObject(jsonObject
		    .toJSONString());
	    Integer typeValue = (Integer) map.get("type");
	    if (type == typeValue.intValue()) {
		reust.put((Integer) map.get("type"), (String) map.get("name"));
		reust.put(9999, (String) map.get("desc"));
	    }

	}
	return reust;
    }

    private String functionName;

    private String[] itemId;
    private String itemIds;
    private ZHOssFunctionAdjust ossFunctionAdjust;
    /** 同步功能列表 */
    private List<ZHOssFunctionAdjust> ossFunctionAdjustList;
    @Autowired
    private OssLogService ossLogService;
    /** 同步功能 oss服务器列表 */
    private List<OssServer> ossServersList;
    private List<Map<String, Object>> reustList;

    /** 同步的服务器Id */
    private String serverArray;

    @Autowired
    private ISyncMoudle syncMoudle;

    /** 同步类型 */
    private String syncType;

    private Map<Integer, String> typeMap;

    /** 同步功能点的类型 */
    private String types;

    @Action(value = "zhfunctionAdjust_add", results = { @Result(name = "success", location = "../../zhxy/message/functionAdjust_add.jsp") })
    public String addFunctionAdjust() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "FunctionAdjustHandler");
	// object.put("methodName", "getFunctionAdjustType");
	// object.put("ossFunctionAdjust", ossFunctionAdjust);
	object = CONNECTION.sendMsg(object);
	List<JSONObject> listType = (List<JSONObject>) object.get("typeList");
	reustList = toMapList(listType);
	return SUCCESS;
    }

    @Action(value = "zhfunctionAdjust_del", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "zhfunctionAdjust_index", "namespace",
	    "/admin/message", "actionMsg", "${actionMsg}" }) })
    public String deleteFunctionAdjust() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "FunctionAdjustHandler");
	object.put("methodName", "deleteFunctionAdjust");
	object.put("functionTypes", ossFunctionAdjust.getFunctionType()
		.toString());
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

    @Action(value = "zhfunctionAdjust_delMulti", results = { @Result(name = "success", type = "chain", location = "functionAdjust_reflash", params = {
	    "actionMsg", "${actionMsg}" }) })
    public void delMultipleFunctionAdjust() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	String functionTypes = new String();
	if (itemIds != null) {
	    functionTypes = itemIds;
	}
	object.put("handlerName", "FunctionAdjustHandler");
	object.put("methodName", "deleteFunctionAdjust");
	object.put("functionTypes", functionTypes);
	int code = 0;
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		code = (Integer) object.get("code");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	if (code == 0) {
	    setActionMsg("OK");
	} else {
	    setActionMsg("ERROR");
	}
	getPageMessage().setNavTabId("w_功能点调整");
	getJSONResponse().responseJson(getPageMessage());
    }

    @Action(value = "zhfunctionAdjust_doAdd", results = { @Result(name = "success", type = "chain", location = "zhfunctionAdjust_index") })
    public String doAddFunctionAdjust() throws Exception {

	try {
	    List<String> errorField = JSONValidate.validateJson(
		    ossFunctionAdjust.getFunctionType(),
		    ossFunctionAdjust.getFunctionNub());
	    if (errorField.size() > 0) {
		setActionMsg("errorJsonField:" + errorField);
		return SUCCESS;
	    }
	} catch (IllegalArgumentException e1) {
	    throw ActionValidateException.SUCCESS;
	}
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
	    setActionMsg("OK");
	} else if (code == 2) {
	    setActionMsg("Do not allow to repeat ");
	} else {
	    setActionMsg("ERROR");
	}
	return SUCCESS;
    }

    public String getFunctionName() {
	return functionName;
    }

    /** 查询服务器列表 */
    @Action(value = "zhfunctionAdjust_server", results = { @Result(name = "success", location = "../../zhxy/message/serverSyncList.jsp") })
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

    public String[] getItemId() {
	return getItemId();
    }

    public String getItemIds() {
	return itemIds;
    }

    public ZHOssFunctionAdjust getOssFunctionAdjust() {
	return ossFunctionAdjust;
    }

    public List<ZHOssFunctionAdjust> getOssFunctionAdjustList() {
	return ossFunctionAdjustList;
    }

    public List<OssServer> getOssServersList() {
	return this.ossServersList;
    }

    public List<Map<String, Object>> getReustList() {
	return reustList;
    }

    public String getServerArray() {
	return this.serverArray;
    }

    public String getSyncType() {
	return syncType;
    }

    public Map<Integer, String> getTypeMap() {
	return typeMap;
    }

    public String getTypes() {
	return this.types;
    }

    /** 刷新服务器功能调整 */
    @Action(value = "zhfunctionAdjust_reflash", results = { @Result(name = "success", type = "chain", location = "zhfunctionAdjust_index") })
    public String modifyActivity() throws Exception {
	setActionMsg("ok");
	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("handlerName", "FunctionAdjustHandler");
	    object.put("methodName", "refurbish");
	    object = CONNECTION.sendMsg(object);
	    if (!object.get("code").equals(0)) {
		setActionMsg("no");
	    }
	} catch (Exception e) {
	    setActionMsg("no");
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    @Action(value = "zhfunctionAdjust_index", results = { @Result(name = "success", location = "../../zhxy/message/functionAdjust.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "FunctionAdjustHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossFunctionAdjustList = (List<ZHOssFunctionAdjust>) object
			.get("ossFunctionAdjustList");

		/** 查询成功后,服务器列表赋值 */
		ossServersList = getBaseAdminContext().getOssServers();
		List<JSONObject> listType = (List<JSONObject>) object
			.get("typeList");
		typeMap = toMap(listType);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "zhfunctionAdjust_query", results = { @Result(name = "success", location = "../../zhxy/message/functionAdjust_edit.jsp") })
    public String queryFunctionAdjust() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "FunctionAdjustHandler");
	object.put("methodName", "queryFunctionAdjust");
	object.put("functionType", ossFunctionAdjust.getFunctionType());
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossFunctionAdjust = (ZHOssFunctionAdjust) object
			.get("ossFunctionAdjust");
		List<JSONObject> listType = (List<JSONObject>) object
			.get("typeList");
		typeMap = toTypeMap(listType,
			ossFunctionAdjust.getFunctionType());
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setFunctionName(String functionName) {
	this.functionName = functionName;
    }

    public void setItemId1(String[] itemId1) {
	this.itemId = itemId1;
    }

    public void setItemIds(String itemIds) {
	this.itemIds = itemIds;
    }

    public void setOssFunctionAdjust(ZHOssFunctionAdjust ossFunctionAdjust) {
	this.ossFunctionAdjust = ossFunctionAdjust;
    }

    public void setOssFunctionAdjustList(
	    List<ZHOssFunctionAdjust> ossFunctionAdjustList) {
	this.ossFunctionAdjustList = ossFunctionAdjustList;
    }

    public void setOssServersList(List<OssServer> ossServersList) {
	this.ossServersList = ossServersList;
    }

    public void setReustList(List<Map<String, Object>> reustList) {
	this.reustList = reustList;
    }

    public void setServerArray(String serverArray) {
	this.serverArray = serverArray;
    }

    public void setSyncType(String syncType) {
	this.syncType = syncType;
    }

    public void setTypeMap(Map<Integer, String> typeMap) {
	this.typeMap = typeMap;
    }

    public void setTypes(String types) {
	this.types = types;
    }

    // 同步到其他服务器代码
    @Action(value = "zhfunctionAdjust_syn", results = { @Result(name = "success", location = "../../zhxy/message/functionAdjust.jsp") })
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
	// jsonResponse.responseJson(syncMoudle.syncMoudle(querySynMap,querySynList,
	// serverArray.split(",")));
    }

    @Action(value = "zhfunctionAdjust_update", results = { @Result(name = "success", type = "chain", location = "zhfunctionAdjust_index") })
    public String updateFunctionAdjust() throws Exception {
	/** 取消JSON验证，key格式验证 */
	List<String> errorField = JSONValidate.validateJson(
		ossFunctionAdjust.getFunctionType(),
		ossFunctionAdjust.getFunctionNub());
	if (errorField.size() > 0) {
	    setActionMsg("errorJsonField:" + errorField);
	    return SUCCESS;
	}

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
