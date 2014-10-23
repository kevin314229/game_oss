package com.jcwx.game.admin.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.http.domain.CjwzActivityNotice;
import com.jcwx.game.service.oss.impl.ServerNoticeService;


/**
 * 服务器系统维护接口
 * 
 * @author csp 2014-4-17
 */
@ParentPackage("base")
@Namespace("/cjwz/message")
public class ActivityNoticeCJWZAction extends BasalAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private CjwzActivityNotice activityNotice;

    private List<CjwzActivityNotice> list;
    @Autowired
    private ServerNoticeService serverNoticeService;

    /** 新增活动区 */
    @Action(value = "activityNotice_addActivityNotice", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "activityNotice_index", "namespace", "/cjwz/message",
	    "actionMsg", "${actionMsg}" }) })
    public String addActivityNotice() throws Exception {
	// String url = baseAdminContext.getCurrentOssServer().getUrl();
	Map<String, Object> object = new HashMap<String, Object>();
	activityNotice.setStartTime(DateService.getDateFirstTime(activityNotice
		.getStartTime()));
	activityNotice.setEndTime(DateService.getDateLastTime(activityNotice
		.getEndTime()));
	object.put("activityNotice", activityNotice);
	object.put("handlerName", "NoticeHandler");
	object.put("methodName", "addNotice");
	// String jsonStr = BocHttpClient.sendPost(url, object);
	// JSONObject jsonObject = JSON.parseObject(jsonStr);
	// Map<String, Object> object = new HashMap<String, Object>();
	object = CONNECTION.sendMsg(object);
	setActionMsg(CodeUtil.getActionMsgByMap(object));
	OSS_LOG_SERVICE.createOssLog(OssLogConstant.ADD_ACTIVITY_NOTICE, ToStringBuilder.reflectionToString(activityNotice));
	return SUCCESS;
    }

    @Action(value = "activityNotice_addActivityNoticeIndex", results = { @Result(name = "success", location = "/cjwz/message/addActivityNotice.jsp") })
    public String addActivityNoticeIndex() throws Exception {
	return SUCCESS;
    }

    /**
     * 删除活动
     * */
    @Action(value = "activityNotice_deleteActivityNotice", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "activityNotice_index", "namespace", "/cjwz/message",
	    "actionMsg", "${actionMsg}" }) })
    public String deleteActivityNotice() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("activityNoticeId", activityNotice.getActivityNoticeId());
	object.put("handlerName", "NoticeHandler");
	object.put("methodName", "deleteNotice");
	object = CONNECTION.sendMsg(object);
	setActionMsg(CodeUtil.getActionMsgByMap(object));
	OSS_LOG_SERVICE.createOssLog(OssLogConstant.DEL_ACTIVITY_NOTICE, ToStringBuilder.reflectionToString(activityNotice));

	return "success";
    }

    public CjwzActivityNotice getActivityNotice() {
	return activityNotice;
    }

    public List<CjwzActivityNotice> getList() {
	return list;
    }

    @SuppressWarnings("unchecked")
    @Action(value = "activityNotice_index", results = { @Result(name = "success", location = "/cjwz/message/activityNotice.jsp") })
    public String index() throws Exception {
	// String url = baseAdminContext.getCurrentOssServer().getUrl();
	// Map<String,String> paramMap = new HashMap<String,String>();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "NoticeHandler");
	object.put("methodName", "queryNoticeList");
	object = CONNECTION.sendMsg(object);
	list = (List<CjwzActivityNotice>) object.get("list");
	// JSONObject jsonObject = JSON.parseObject(jsonStr);
	// JSONArray array = JSONArray.parseArray(jsonObject.getString("list"));
	// Map<String,Object> resultMap =
	// JSONObject.parseObject(jsonObject,Map.class);
	// list = JSON.parseArray(array.toJSONString(),
	// ActivityNoticeCJWZ.class);
	return SUCCESS;
    }

    public void setActivityNotice(CjwzActivityNotice activityNotice) {
	this.activityNotice = activityNotice;
    }

    public void setList(List<CjwzActivityNotice> list) {
	this.list = list;
    }

    /** 修改系统公告 */
    @Action(value = "activityNotice_updateActivityNotice", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "activityNotice_index", "namespace", "/cjwz/message",
	    "actionMsg", "${actionMsg}" }) })
    public String updateActivityNotice() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();

	activityNotice.setStartTime(DateService.getDateFirstTime(activityNotice
		.getStartTime()));
	activityNotice.setEndTime(DateService.getDateLastTime(activityNotice
		.getEndTime()));
	object.put("activityNotice", activityNotice);
	object.put("handlerName", "NoticeHandler");
	object.put("methodName", "updateNotice");
	// String jsonStr = BocHttpClient.sendPost(url, object);
	OSS_LOG_SERVICE.createOssLog(OssLogConstant.MODIFY_ACTIVITY_NOTICE, ToStringBuilder.reflectionToString(activityNotice));
	object = CONNECTION.sendMsg(object);
	setActionMsg(CodeUtil.getActionMsgByMap(object));
	return SUCCESS;
    }

    @Action(value = "activityNotice_updateActivityNoticeIndex", results = { @Result(name = "success", location = "/cjwz/message/updateActivityNotice.jsp") })
    public String updateActivityNoticeIndex() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("activityNoticeId", activityNotice.getActivityNoticeId());
	object.put("handlerName", "NoticeHandler");
	object.put("methodName", "queryActivityNotice");
	object = CONNECTION.sendMsg(object);
	activityNotice = (CjwzActivityNotice) object.get("activityNotice");
	// activityNotice
	// =JSONObject.parseObject(object.get("activityNotice").toString(),OssActivityNotice.class);
	return SUCCESS;
    }

}
