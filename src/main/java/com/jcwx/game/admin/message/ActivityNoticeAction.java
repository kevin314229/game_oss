/**
 * 
 */
package com.jcwx.game.admin.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.jcwx.game.http.domain.OssActivityNotice;
import com.jcwx.game.service.ISyncMoudle;


/**
 * 系统活动
 * 
 * @author Administrator 2013-9-3
 */
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class ActivityNoticeAction extends BasalAction {
    private static final long serialVersionUID = 1L;

    /** 同步系统活动的ID列表 （旧） */
    private String ids;
    Log logger = LogFactory.getLog(ActivityNoticeAction.class);

    // 系统公告
    private OssActivityNotice ossActivityNotice;
    // 系统参数 集合
    private List<OssActivityNotice> ossActivityNoticeList;

    private List<OssServer> ossServersList;

    private String serverArray;

    @Autowired
    private ISyncMoudle syncMoudle;

    /** 同步系统活动的ID列表 (新） */
    private String types;

    /** 新增活动区 */
    @Action(value = "activityNotice_addActivityNotice", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "activityNotice_index", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", type = "redirectAction", params = {
		    "actionName", "activityNotice_index", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }) })
    public String addActivityNotice() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	ossActivityNotice.setCreateTime(new java.util.Date());
	ossActivityNotice.setStartTime(DateService
		.getDateFirstTime(ossActivityNotice.getStartTime()));
	ossActivityNotice.setEndTime(DateService
		.getDateLastTime(ossActivityNotice.getEndTime()));
	object.put("ossActivityNotice", ossActivityNotice);
	object.put("handlerName", "ActivityNoticeHandler");
	object.put("methodName", "addActivityNotice");
	int code = 1;
	object = CONNECTION.sendMsg(object);
	code = CodeUtil.getCode(object);
	if (code == 0) {
	    setActionMsg("ok");
	} else if (code == 2) {
	    setErrorInfo("Do not allow to repeat ");
	    return ERROR;
	} else {
	    setErrorInfo("ERROR");
	    return ERROR;
	}
	OSS_LOG_SERVICE.createOssLog(OssLogConstant.ADD_ACTIVITY_NOTICE, ToStringBuilder.reflectionToString(ossActivityNotice));
	return SUCCESS;
    }

    @Action(value = "activityNotice_addActivityNoticeIndex", results = { @Result(name = "success", location = "../../admin/message/addActivityNotice.jsp") })
    public String addModifyActivityIndex() {
	return "success";
    }

    /**
     * 删除活动
     * */
    @Action(value = "activityNotice_deleteActivityNotice", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "activityNotice_index", "namespace",
	    "/admin/message", "actionMsg", "${actionMsg}" }) })
    public String deleteModifyActivity() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("activityNoticeId", ossActivityNotice.getActivityNoticeId());
	object.put("handlerName", "ActivityNoticeHandler");
	object.put("methodName", "deleteActivityNotice");

	setActionMsg(CodeUtil.getActionMsgByMap(CONNECTION.sendMessageX(object)));

	OSS_LOG_SERVICE.createOssLog(OssLogConstant.DEL_ACTIVITY_NOTICE, ToStringBuilder.reflectionToString(ossActivityNotice));

	return "success";
    }

    public String getIds() {
	return this.ids;
    }

    public OssActivityNotice getOssActivityNotice() {
	return ossActivityNotice;
    }

    public List<OssActivityNotice> getOssActivityNoticeList() {
	return ossActivityNoticeList;
    }

    public List<OssServer> getOssServersList() {
	return this.ossServersList;
    }

    public String getServerArray() {
	return this.serverArray;
    }

    public String getTypes() {
	return types;
    }

    /** 刷新服务器活动 */
    @Action(value = "activityNotice_reflash", results = @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "activityNotice_index", "namespace",
	    "/admin/message", "actionMsg", "${actionMsg}" }))
    public String modifyActivityNotice() throws Exception {
	setActionMsg("ok");
	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("handlerName", "ActivityNoticeHandler");
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
    @Action(value = "activityNotice_index", results = { @Result(name = "success", location = "../../admin/message/activityNotice.jsp") })
    public String query() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    addActionMessage(getActionMsg());
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ActivityNoticeHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossActivityNoticeList = (List<OssActivityNotice>) object
			.get("ossActivityNoticeList");
		/** 成功后得到服务器列表 */
		// setOssServersList( baseAdminContext.getOssServers());
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setIds(String ids) {
	this.ids = ids;
    }

    public void setOssActivityNotice(OssActivityNotice ossActivityNotice) {
	this.ossActivityNotice = ossActivityNotice;
    }

    public void setOssActivityNoticeList(
	    List<OssActivityNotice> ossActivityNoticeList) {
	this.ossActivityNoticeList = ossActivityNoticeList;
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
    @Action(value = "activityNotice_syn", results = { @Result(name = "success", location = "../../admin/message/activityNotice.jsp") })
    public void synActivityNotice() throws Exception {
	JSONResponse jsonResponse = JSONResponse
		.newInstance(ServletActionContext.getResponse());

	Map<String, Object> querySynMap = Maps.newHashMap();
	querySynMap.put("handlerName", "ActivityNoticeHandler");
	querySynMap.put("methodName", "querySynActivityNotice");
	querySynMap.put("ids", types);

	Map<String, Object> querySynList = Maps.newHashMap();
	querySynList.put("handlerName", "ActivityNoticeHandler");
	querySynList.put("methodName", "synActivityNotice");

	JSONObject object = new JSONObject();
	object.put("id", serverArray);
	object.put(
		"msg",
		syncMoudle.syncMoudle(querySynMap, querySynList,
			serverArray.split(",")));
	jsonResponse.responseJson(object);
	// jsonResponse.responseJson(syncMoudle.syncMoudle(querySynMap,querySynList,serverArray.split(",")));
    }

    /** 修改系统活动 */
    @Action(value = "activityNotice_updateActivityNotice", results = {
	    @Result(name = "success", type = "chain", params = { "actionName",
		    "activityNotice_index", "namespace", "/admin/message",
		    "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", location = "../../admin/server/addActivityNotice.jsp") })
    public String updateActivityNotice() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	ossActivityNotice.setModifyTime(new java.util.Date());
//	ossActivityNotice.setStartTime(DateService
//		.getDateFirstTime(ossActivityNotice.getStartTime()));
//	ossActivityNotice.setEndTime(DateService
//		.getDateLastTime(ossActivityNotice.getEndTime()));

	object.put("ossActivityNotice", ossActivityNotice);
	object.put("handlerName", "ActivityNoticeHandler");
	object.put("methodName", "updateActivityNotice");

	setActionMsg(CodeUtil.getActionMsgByMap(CONNECTION.sendMessageX(object)));

	OSS_LOG_SERVICE.createOssLog(OssLogConstant.MODIFY_ACTIVITY_NOTICE, ToStringBuilder.reflectionToString(ossActivityNotice));

	return SUCCESS;
    }

    /** 修改游戏加载页面 */
    @SuppressWarnings("unchecked")
    @Action(value = "activityNotice_updateActivityNoticeIndex", results = { @Result(name = "success", location = "../../admin/message/updateActivityNotice.jsp") })
    public String updateModifyActivityIndex() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    addActionMessage(getActionMsg());
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("activityNoticeId", ossActivityNotice.getActivityNoticeId());
	object.put("handlerName", "ActivityNoticeHandler");
	// object.put("methodName", "queryActivityNotice");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		// ossActivityNotice=(OssActivityNotice)object.get("ossActivityNotice");
		ossActivityNoticeList = (List<OssActivityNotice>) object
			.get("ossActivityNoticeList");
		for (OssActivityNotice oss : ossActivityNoticeList) {
		    if (oss.getActivityNoticeId().intValue() == ossActivityNotice
			    .getActivityNoticeId().intValue()) {
			ossActivityNotice = oss;
			break;
		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return "success";
    }

}
