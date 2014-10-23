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
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.http.domain.OssConsumeGuide;
import com.jcwx.game.service.ISyncMoudle;
import com.jcwx.game.service.oss.IOssLogService;


/**
 * 消费指引 action
 * 
 * @author 2014-2-24
 */
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class ConsumeGuideAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 系统活动ids类型 */
    private String ids;

    private OssConsumeGuide ossConsumeGuide;

    private List<OssConsumeGuide> OssConsumeGuideList;

    @Autowired
    private IOssLogService ossLogService;

    private String serverArray;

    @Autowired
    private ISyncMoudle syncMoudle;

    /** 系统活动ids类型 （新） */
    private String types;

    @Action(value = "consumeGuide_add", results = { @Result(name = "success", location = "../../zhxy/message/consumeGuide_add.jsp") })
    public String addConsumeGuide() throws Exception {
	return SUCCESS;
    }

    @Action(value = "consumeGuide_del", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "consumeGuide_index", "namespace", "/admin/message",
	    "actionMsg", "${actionMsg}" }) })
    public String deleteMallNew() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ConsumeGuideHandler");
	object.put("methodName", "deleteConsumeGuide");
	object.put("consumeGuideId", ossConsumeGuide.getConsumeGuideId());
	setActionMsg(CodeUtil.getActionMsgByMap(CONNECTION.sendMessageX(object)));
	ossLogService.createOssLog(OssLogConstant.DEL_MALL_NEW, ToStringBuilder
		.reflectionToString(ossConsumeGuide.getConsumeGuideId()));

	return SUCCESS;
    }

    @Action(value = "consumeGuide_doAdd", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "consumeGuide_index", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", location = "../../zhxy/message/consumeGuide_add.jsp") })
    public String doAddConsumeGuide() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ConsumeGuideHandler");
	object.put("methodName", "addConsumeGuide");
	object.put("ossConsumeGuide", ossConsumeGuide);

	setActionMsg(CodeUtil.getActionMsgByMap(CONNECTION.sendMessageX(object)));
	ossLogService.createOssLog(OssLogConstant.ADD_MALL_NEW,
		ToStringBuilder.reflectionToString(ossConsumeGuide));

	return SUCCESS;
    }

    public String getIds() {
	return ids;
    }

    public OssConsumeGuide getOssConsumeGuide() {
	return ossConsumeGuide;
    }

    public List<OssConsumeGuide> getOssConsumeGuideList() {
	return OssConsumeGuideList;
    }

    public String getServerArray() {
	return serverArray;
    }

    public String getTypes() {
	return types;
    }

    @Action(value = "consumeGuide_index", results = { @Result(name = "success", location = "../../zhxy/message/consumeGuide.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ConsumeGuideHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		OssConsumeGuideList = (List<OssConsumeGuide>) object
			.get("ossConsumeGuideList");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "consumeGuide_query", results = { @Result(name = "success", location = "../../zhxy/message/consumeGuide_edit.jsp") })
    public String queryConsumeGuide() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ConsumeGuideHandler");
	object.put("methodName", "queryConsumeGuide");
	object.put("consumeGuideId", ossConsumeGuide.getConsumeGuideId());
	Map<String, Object> returnMap = CONNECTION.sendMessageX(object);
	ossConsumeGuide = (OssConsumeGuide) returnMap.get("ossConsumeGuide");

	return SUCCESS;
    }

    /** 刷新服务器商品 */
    @Action(value = "consumeGuide_reflash", results = { @Result(name = "success", type = "chain", location = "consumeGuide_index") })
    public String reflashMallActivity() throws Exception {
	setActionMsg("ok");
	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("handlerName", "ConsumeGuideHandler");
	    object.put("methodName", "refurbish");
	    object = CONNECTION.sendMsg(object);
	    setActionMsg(CodeUtil.getActionMsgByMap(object));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setIds(String ids) {
	this.ids = ids;
    }

    public void setOssConsumeGuide(OssConsumeGuide ossConsumeGuide) {
	this.ossConsumeGuide = ossConsumeGuide;
    }

    public void setOssConsumeGuideList(List<OssConsumeGuide> ossConsumeGuideList) {
	OssConsumeGuideList = ossConsumeGuideList;
    }

    public void setServerArray(String serverArray) {
	this.serverArray = serverArray;
    }

    public void setTypes(String types) {
	this.types = types;
    }

    // 同步到其他服务器代码
    @Action(value = "consumeGuide_syn", results = { @Result(name = "success", location = "../../admin/message/systemActivity.jsp") })
    public void synConsumeGuide() throws Exception {
	JSONResponse jsonResponse = JSONResponse
		.newInstance(ServletActionContext.getResponse());

	Map<String, Object> querySynMap = Maps.newHashMap();
	querySynMap.put("handlerName", "ConsumeGuideHandler");
	querySynMap.put("methodName", "querySynConsumeGuide");
	querySynMap.put("ids", types);

	Map<String, Object> querySynList = Maps.newHashMap();
	querySynList.put("handlerName", "ConsumeGuideHandler");
	querySynList.put("methodName", "synConsumeGuide");
	JSONObject object = new JSONObject();
	object.put("id", serverArray);
	object.put(
		"msg",
		syncMoudle.syncMoudle(querySynMap, querySynList,
			serverArray.split(",")));
	jsonResponse.responseJson(object);
    }

    @Action(value = "consumeGuide_update", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "consumeGuide_index", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", location = "../../zhxy/message/consumeGuide_add.jsp") })
    public String updateMallNew() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ConsumeGuideHandler");
	object.put("methodName", "updateConsumeGuide");
	object.put("ossConsumeGuide", ossConsumeGuide);
	setActionMsg(CodeUtil.getActionMsgByMap(CONNECTION.sendMessageX(object)));
	ossLogService.createOssLog(OssLogConstant.MODIFY_MALL_NEW,
		ToStringBuilder.reflectionToString(ossConsumeGuide));
	return SUCCESS;
    }

}
