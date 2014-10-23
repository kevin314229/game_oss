/**
 * 
 */
package com.jcwx.game.admin.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.http.domain.CjwzOssActivity;
import com.jcwx.game.service.oss.impl.OssLogService;


/**
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/cjwz/message")
@ResultPath("/")
public class ActivityCJWZAction extends BasalAction {
    private static final long serialVersionUID = 1L;
    private CjwzOssActivity activity;

    private List<CjwzOssActivity> activityList;

    @Autowired
    private OssLogService ossLogService;

    @Action(value = "activity_addActivity", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "activity_index", "namespace",
		    "/cjwz/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", location = "../../cjwz/message/addActivity.jsp") })
    public String addActivity() throws Exception {
	// 登录环境
	Map<String, Object> object = new HashMap<String, Object>();
	// 开始时间和结束时间
	// 开始时间和结束时间
	object.put("handlerName", "ActivityHandler");
	object.put("methodName", "addActivity");
	activity.setCreateUser(getBaseAdminContext().getOssUser().getUsername());
	object.put("activity", activity);
	try {
	    
	    object = CONNECTION.sendMsg(object);

	    ossLogService.createOssLog(OssLogConstant.ADD_MODIFY_ACTIVITY,
		    ToStringBuilder.reflectionToString(activity));
	} catch (Exception e) {
	    e.printStackTrace();
	}

	// System.out.println("----------------------------------------------------------");
	setActionMsg(CodeUtil.getActionMsgByMap(object));
	return "success";

    }

    @Action(value = "activity_addActivityIndex", results = { @Result(name = "success", location = "../../cjwz/message/addActivity.jsp") })
    public String addModifyActivity() throws Exception {
	return "success";
    }

    /**
     * 删除活动
     * */
    @Action(value = "activity_deleteActivity", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "activity_index", "namespace", "/cjwz/message",
	    "actionMsg", "${actionMsg}" }) })
    public String deleteActivity() throws Exception {
	// 登录环境
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("activityId", activity.getActivityId());
	object.put("handlerName", "ActivityHandler");
	object.put("methodName", "deleteActivity");
	try {
	    object = CONNECTION.sendMsg(object);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	setActionMsg(CodeUtil.getActionMsgByMap(object));
	return "success";
    }

    public CjwzOssActivity getActivity() {
	return activity;
    }

    /*
     * 
     * @Action(value = "modifyActivity_select", results = { @Result(name =
     * "success", location = "../../zhxy/message/modifyActivity_select.jsp") })
     * public String modifyActivity_select() { return SUCCESS; }
     *//** 新增活动区 */
    /*
   

*

*//** 修改游戏区页面 */
    /*
     * @Action(value = "modifyActivity_updateModifyActivityIndex", results = {
     * 
     * @Result(name = "success", location =
     * "../../zhxy/message/updateModifyActivity.jsp") }) public String
     * updateModifyActivityIndex() throws Exception {
     * 
     * HttpSession session = ServletActionContext.getRequest().getSession(); if
     * (StringUtils.isNotBlank(actionMsg)) { // actionMsg = new
     * String(actionMsg.getBytes("ISO8859-1"), "utf-8");
     * addActionMessage(actionMsg); }
     * 
     * String id = ServletActionContext.getRequest().getParameter("id"); Integer
     * ID = Integer.parseInt(id);
     * 
     * List<OssActivity> ossActivities = (List<OssActivity>) session
     * .getAttribute("ossActivities"); for (OssActivity oss : ossActivities) {
     * if (ID.intValue() == oss.getId().intValue()) { ossActivity = oss;
     * if(ossActivity
     * .getOpenTime()!=null&&!"".equals(ossActivity.getOpenTime())) openDate =
     * DateService.getDateFormatStr(ossActivity.getOpenTime(),
     * "yyyy-MM-dd HH:mm:ss");
     * if(ossActivity.getOverTime()!=null&&!"".equals(ossActivity
     * .getOverTime())) endDate =
     * DateService.getDateFormatStr(ossActivity.getOverTime(),
     * "yyyy-MM-dd HH:mm:ss");
     * if(ossActivity.getRewardOverTime()!=null&&!"".equals
     * (ossActivity.getRewardOverTime())) rewardOverDate =
     * DateService.getDateFormatStr(ossActivity.getRewardOverTime(),
     * "yyyy-MM-dd HH:mm:ss"); break; }
     * 
     * } ossActivityDetails = ossActivity.getOssActivityDetails();
     * BaseAdminContext baseAdminContext = (BaseAdminContext) session
     * .getAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY); Map<String,
     * Object> object = new HashMap<String, Object>(); object.put("handlerName",
     * "ModifyActivityHandler"); object.put("methodName", "getJsonObjectList");
     * //object.put("ossFunctionAdjust", ossFunctionAdjust); object =
     * getConnection().sendMsg(baseAdminContext, object); List<JSONObject>
     * listType = (List<JSONObject>) object.get("typeList");
     * reustList=toMapList(listType); return "success"; }
     * 
     * @Action(value = "modifyActivity_updateModifyActivity", results = {
     * 
     * @Result(name = "success", type = "chain", params = { "actionName",
     * "modifyActivity_index", "namespace", "/zhxy/message", "actionMsg",
     * "${actionMsg}" }) }) public String updateModifyActivity() throws
     * Exception { HttpSession session =
     * ServletActionContext.getRequest().getSession(); // 登录环境 BaseAdminContext
     * baseAdminContext = (BaseAdminContext) session
     * .getAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY); if
     * (StringUtils.isNotBlank(actionMsg)) { // actionMsg = new
     * String(actionMsg.getBytes("ISO8859-1"), "UTF-8");
     * addActionMessage(actionMsg); } if
     * (!StringUtils.isNotBlank(ossActivity.getRule()) ||
     * !StringUtils.isNotBlank(ossActivity.getName()) ||
     * !StringUtils.isNotBlank(ossActivity.getActivityDesc())) { actionMsg =
     * "Please input correct format"; return "success"; }
     * if(ossActivityDetails!=null){ for (OssActivityDetail ossActivityDetail :
     * ossActivityDetails) { if (!checkItem(ossActivityDetail.getItem())) {
     * actionMsg = "Please input correct Reward items format"; return "success";
     * } } } // 开始时间和结束时间 Date openTime = null, overTime =
     * null,rewardOverTime=null; if (openDate != null && !"".equals(openDate)) {
     * openTime = DateService.getDateByStrAndFormat(openDate,
     * "yyyy-MM-dd HH:mm:ss") ; } else { openTime =
     * DateService.getCurrentDayFirstUtilDate(); openDate =
     * DateService.getDateFormatStr(openTime, "yyyy-MM-dd"); } if (endDate !=
     * null&&!"".equals(endDate)) {
     * overTime=DateService.getDateByStrAndFormat(endDate,
     * "yyyy-MM-dd HH:mm:ss"); overTime =
     * DateService.getCurrentDayLastUtilDate(); endDate =
     * DateService.getDateFormatStr(overTime, "yyyy-MM-dd");1 } else { overTime
     * = DateService.getDateLastTime(endDate); }
     * 
     * if (rewardOverDate != null&&!"".equals(rewardOverDate)) {
     * rewardOverTime=DateService.getDateByStrAndFormat(rewardOverDate,
     * "yyyy-MM-dd HH:mm:ss"); } int code = 0; Map<String, Object> object = new
     * HashMap<String, Object>();
     * 
     * String id = ServletActionContext.getRequest().getParameter("id"); Integer
     * ID = Integer.parseInt(id); object.put("Id", ID); object.put("type",
     * ossActivity.getType()); object.put("characteristic",
     * ossActivity.getCharacteristic()); object.put("times",
     * ossActivity.getTimes()); object.put("name", ossActivity.getName());
     * object.put("activityDesc", ossActivity.getActivityDesc());
     * object.put("rule", ossActivity.getRule()); //
     * object.put("carrierOperator", ossActivity.getCarrierOperator());
     * object.put("openTime", openTime); object.put("overTime", overTime);
     * object.put("rewardOverTime", rewardOverTime);
     * object.put("ossActivityDetails", ossActivityDetails);
     * object.put("handlerName", "ModifyActivityHandler");
     * object.put("methodName", "updateActivity"); try { object =
     * getConnection().sendMsg(baseAdminContext, object); } catch (Exception e) {
     * e.printStackTrace(); } if (code == 0) { actionMsg="ok"; return "success";
     * } return "success";
     * 
     * }
     * 
     * 
     * 
     * public static boolean checkItem(String item) { if
     * (StringUtils.isBlank(item)) { return false; } if
     * (!StringUtils.contains(item, ",")) { return false; } String[] items =
     * StringUtils.split(item, "#"); if (items.length == 0) { return false; }
     * return true; }
     * 
     * @Autowired private SyncMoudle syncMoudle;
     * 
     * // 同步到其他服务器代码
     * 
     * @Action(value = "modifyActivity_syn", results = { @Result(name =
     * "success", location = "../../zhxy/message/modifyActivity.jsp") }) public
     * void synAdjustActivity() throws Exception {
     * 
     * Map<String,Object> querySynMap = Maps.newHashMap();
     * querySynMap.put("handlerName", getHandleName());
     * querySynMap.put("methodName", "querySynModifyActivity");
     * querySynMap.put("ids", types);
     * 
     * Map<String,Object> querySynList = Maps.newHashMap();
     * querySynList.put("handlerName", getHandleName());
     * querySynList.put("methodName", "synModifyActivity"); JSONObject object =
     * new JSONObject(); object.put("id", serverArray);
     * object.put("msg",syncMoudle.syncMoudle(querySynMap, querySynList,
     * serverArray.split(","))); getJSONResponse().responseJson(object); //
     * getJSONResponse
     * ().responseJson(syncMoudle.syncMoudle(querySynMap,querySynList
     * ,serverArray.split(","))); }
     * 
     * private Object getHandleName() { return "ModifyActivityHandler"; }
     * 
     * public static List<Map<String, Object>> toMapList(List<JSONObject> list)
     * { List<Map<String, Object>> reustList = new
     * ArrayList<Map<String,Object>>(); for(int i=list.size()-1;i>=0;i--){
     * JSONObject jsonObject=list.get(i); Map<String, Object> map =
     * JSONObject.parseObject(jsonObject.toJSONString()); reustList.add(map); }
     * return reustList;
     * 
     * }
     * 
     * public static Map<Integer, String> toMap(List<JSONObject> list) {
     * Map<Integer, String> reust = new HashMap<Integer, String> (); for(int
     * i=0;i<list.size();i++){ JSONObject jsonObject=list.get(i); Map<String,
     * Object> map = JSONObject.parseObject(jsonObject.toJSONString());
     * reust.put((Integer)map.get("type"),(String) map.get("name")); } return
     * reust; }
     * 
     * public static Map<Integer, String> toTypeMap(List<JSONObject> list,int
     * type) { Map<Integer, String> reust = new HashMap<Integer, String> ();
     * for(int i=0;i<list.size();i++){ JSONObject jsonObject=list.get(i);
     * Map<String, Object> map =
     * JSONObject.parseObject(jsonObject.toJSONString()); Integer typeValue =
     * (Integer)map.get("type"); if(type==typeValue.intValue()){
     * reust.put((Integer)map.get("type"),(String) map.get("name"));
     * reust.put(9999,(String) map.get("desc")); }
     * 
     * } return reust; }
     */

    public List<CjwzOssActivity> getActivityList() {
	return activityList;
    }

    // ** 刷新服务器活动 *//*
    @Action(value = "activity_reflash", results = { @Result(name = "success", type = "chain", location = "activity_index") })
    public String modifyActivity() throws Exception {

	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("handlerName", "ModifyActivityHandler");
	    object.put("methodName", "reflashActivity");
	    object = CONNECTION.sendMsg(object);
	    setActionMsg(CodeUtil.getActionMsgByMap(object));
	} catch (Exception e) {
	    setActionMsg("no");
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "activity_index", results = { @Result(name = "success", location = "../../cjwz/message/activityList.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();

	object.put("handlerName", "ActivityHandler");
	object.put("methodName", "queryActivityList");
	object = CONNECTION.sendMsg(object);
	activityList = (List<CjwzOssActivity>) object.get("activityList");
	// ossActivities = (List<OssModifyActivity>)
	// object.get("ossActivities");
	// List<JSONObject> listType = (List<JSONObject>)
	// object.get("typeList");
	// typeMap=toMap(listType);
	// /** 查询成功后,服务器列表赋值 */
	// //ossServersList = baseAdminContext.getOssServers();
	// HttpSession session = ServletActionContext.getRequest().getSession();
	// session.setAttribute("ossActivities", ossActivities);
	return "success";
    }

    public void setActivity(CjwzOssActivity activity) {
	this.activity = activity;
    }

    public void setActivityList(List<CjwzOssActivity> activityList) {
	this.activityList = activityList;
    }

    @Action(value = "activity_updateActivity", results = { @Result(name = "success", type = "chain", params = {
	    "actionName", "activity_index", "namespace", "/cjwz/message",
	    "actionMsg", "${actionMsg}" }) })
    public String updateActivity() throws Exception {
	// 登录环境
	if (StringUtils.isNotBlank(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"), "UTF-8");
	    addActionMessage(getActionMsg());
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("activity", activity);
	object.put("handlerName", "ActivityHandler");
	object.put("methodName", "updateActivity");
	int code = 0;
	try {

	    object = CONNECTION.sendMsg(object);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	if (code == 0) {
	    setActionMsg("ok");
	    return "success";
	}
	return "success";

    }

    @Action(value = "activity_updateActivityIndex", results = { @Result(name = "success", location = "../../cjwz/message/updateActivity.jsp") })
    public String updateModifyActivityIndex() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("activityId", activity.getActivityId());

	object.put("handlerName", "ActivityHandler");
	object.put("methodName", "queryActivityInfo");
	// object.put("ossFunctionAdjust", ossFunctionAdjust);
	object = CONNECTION.sendMsg(object);
	activity = (CjwzOssActivity) object.get("activity");
	// reustList=toMapList(listType);
	return "success";
    }

}
