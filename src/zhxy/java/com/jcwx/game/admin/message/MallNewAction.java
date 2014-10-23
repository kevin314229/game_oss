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
import com.jcwx.game.http.domain.OssMallNew;
import com.jcwx.game.http.domain.SendBaseProperty;
import com.jcwx.game.service.ISyncMoudle;
import com.jcwx.game.service.oss.IOssLogService;
import com.jcwx.game.web.Global;

/**
 * 商城 action
 * 
 * @author 2014-2-24
 */
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class MallNewAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private IOssLogService ossLogService;

    private OssMallNew ossMallNew;

    private List<OssMallNew> ossMallNewList;

    /** 同步功能 oss服务器列表 */
    private List<OssServer> ossServersList;
    // 道具列表
    private List<SendBaseProperty> propertyList;
    // 装备列表
    private List<SendBaseProperty> quipList;
    /** 同步的服务器Id */
    private String serverArray;
    @Autowired
    private ISyncMoudle syncMoudle;
    /** 同步类型 */
    private String syncType;

    /** 需要同步的Ids **/
    private String types;

    @Action(value = "mallNew_add", results = { @Result(name = "success", location = "../../zhxy/message/mallNew_add.jsp") })
    public String addMallNew() throws Exception {
	try {
	    propertyList = Global.getPropertyList(getBaseAdminContext()
		    .getCurrentOssServer().getUrl(),getBaseAdminContext().getProject().getProjectId());
	    quipList = Global.getEquipList(getBaseAdminContext()
		    .getCurrentOssServer().getUrl());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "mallNew_del", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "mallNew_index", "namespace", "/admin/message",
	    "actionMsg", "${actionMsg}" }) })
    public String deleteMallNew() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "MallNewHandler");
	object.put("methodName", "deleteMallNew");
	object.put("mallNewId", ossMallNew.getMallNewId());
	setActionMsg(CodeUtil.getActionMsgByMap(CONNECTION.sendMessageX(object)));
	ossLogService.createOssLog(OssLogConstant.DEL_MALL_NEW,
		ToStringBuilder.reflectionToString(ossMallNew.getMallNewId()));

	return SUCCESS;
    }

    @Action(value = "mallNew_doAdd", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "mallNew_index", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", location = "../../zhxy/message/mallNew_add.jsp") })
    public String doAddMallNew() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "MallNewHandler");
	object.put("methodName", "addMallNew");
	object.put("ossMallNew", ossMallNew);

	setActionMsg(CodeUtil.getActionMsgByMap(CONNECTION.sendMessageX(object)));
	ossLogService.createOssLog(OssLogConstant.ADD_MALL_NEW,
		ToStringBuilder.reflectionToString(ossMallNew));

	return SUCCESS;
    }

    /** 查询服务器列表 */
    @Action(value = "mallNew_server", results = { @Result(name = "success", location = "../../zhxy/message/serverSyncList.jsp") })
    public String getFunctionServerList() throws Exception {
	/** 查询成功后,服务器列表赋值 */
	ossServersList = getBaseAdminContext().getOssServers();
	return SUCCESS;
    }

    public OssMallNew getOssMallNew() {
	return ossMallNew;
    }

    public List<OssMallNew> getOssMallNewList() {
	return ossMallNewList;
    }

    public List<OssServer> getOssServersList() {
	return ossServersList;
    }

    public List<SendBaseProperty> getPropertyList() {
	return propertyList;
    }

    public List<SendBaseProperty> getQuipList() {
	return quipList;
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

    @Action(value = "mallNew_index", results = { @Result(name = "success", location = "../../zhxy/message/mallNew.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "MallNewHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);

		ossMallNewList = (List<OssMallNew>) object
			.get("ossMallNewList");
		/** 查询成功后,服务器列表赋值 */
		ossServersList = getBaseAdminContext().getOssServers();

		Collections.sort(ossMallNewList, new Comparator<OssMallNew>() {
		    @Override
		    public int compare(OssMallNew p2, OssMallNew p1) {
			return p2.getMallOrder().compareTo(p1.getMallOrder());
		    }
		});
		// 排序一下
		Collections.sort(ossMallNewList, new Comparator<OssMallNew>() {
		    @Override
		    public int compare(OssMallNew p2, OssMallNew p1) {
			return ((Integer) p1.getIsShow()).compareTo(p2
				.getIsShow());
		    }
		});

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "mallNew_query", results = { @Result(name = "success", location = "../../zhxy/message/mallNew_edit.jsp") })
    public String queryMallNew() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "MallNewHandler");
	object.put("methodName", "queryMallNew");
	object.put("mallNewId", ossMallNew.getMallNewId());
	Map<String, Object> returnMap = CONNECTION.sendMessageX(object);
	ossMallNew = (OssMallNew) returnMap.get("ossMallNew");
	try {
	    propertyList = Global.getPropertyList(getBaseAdminContext()
		    .getCurrentOssServer().getUrl(),getBaseAdminContext().getProject().getProjectId());
	    quipList = Global.getEquipList(getBaseAdminContext()
		    .getCurrentOssServer().getUrl());

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    /** 刷新服务器商品 */
    @Action(value = "mallNew_reflash", results = { @Result(name = "success", type = "chain", location = "mallNew_index") })
    public String reflashMallActivity() throws Exception {
	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("handlerName", "MallNewHandler");
	    object.put("methodName", "refurbish");
	    object = CONNECTION.sendMsg(object);
	    setActionMsg(CodeUtil.getActionMsgByMap(object));
	} catch (Exception e) {
	    setActionMsg("no");
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setOssMallNew(OssMallNew ossMallNew) {
	this.ossMallNew = ossMallNew;
    }

    public void setOssMallNewList(List<OssMallNew> ossMallNewList) {
	this.ossMallNewList = ossMallNewList;
    }

    public void setOssServersList(List<OssServer> ossServersList) {
	this.ossServersList = ossServersList;
    }

    public void setPropertyList(List<SendBaseProperty> propertyList) {
	this.propertyList = propertyList;
    }

    public void setQuipList(List<SendBaseProperty> quipList) {
	this.quipList = quipList;
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
    @Action(value = "mallNew_syn", results = { @Result(name = "success", location = "../../zhxy/message/mallNew.jsp") })
    public void synAdjustActivity() throws Exception {

	JSONResponse jsonResponse = JSONResponse
		.newInstance(ServletActionContext.getResponse());

	Map<String, Object> querySynMap = Maps.newHashMap();
	querySynMap.put("handlerName", "MallNewHandler");
	querySynMap.put("methodName", "querySynMallNew");
	querySynMap.put("itemIds", types);

	Map<String, Object> querySynList = Maps.newHashMap();
	querySynList.put("handlerName", "MallNewHandler");
	querySynList.put("methodName", "synMallNew");
	JSONObject object = new JSONObject();
	object.put("id", serverArray);
	object.put(
		"msg",
		syncMoudle.syncMoudle(querySynMap, querySynList,
			serverArray.split(",")));
	jsonResponse.responseJson(object);
    }

    @Action(value = "mallNew_update", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "mallNew_index", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", location = "../../zhxy/message/mallNew_add.jsp") })
    public String updateMallNew() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "MallNewHandler");
	object.put("methodName", "updateMallNew");
	object.put("ossMallNew", ossMallNew);
	setActionMsg(CodeUtil.getActionMsgByMap(CONNECTION.sendMessageX(object)));
	ossLogService.createOssLog(OssLogConstant.MODIFY_MALL_NEW,
		ToStringBuilder.reflectionToString(ossMallNew));
	return SUCCESS;
    }
}
