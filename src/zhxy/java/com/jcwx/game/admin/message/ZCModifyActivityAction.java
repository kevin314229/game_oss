/**
 * 
 */
package com.jcwx.game.admin.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.OssActivityDetail;
import com.jcwx.game.http.domain.OssModifyActivity;
import com.jcwx.game.service.ISyncMoudle;
import com.jcwx.game.service.oss.impl.OssLogService;


/**
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/zhxyzc/message")
@ResultPath("/")
public class ZCModifyActivityAction extends BasalAction {
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

    public static Map<Integer, String> toMap(List<JSONObject> list) {
	Map<Integer, String> result = new HashMap<Integer, String>();
	if (list == null)
	    return result;
	for (int i = 0; i < list.size(); i++) {
	    JSONObject jsonObject = list.get(i);
	    Map<String, Object> map = JSON.parseObject(jsonObject
		    .toJSONString());
	    result.put((Integer) map.get("type"), (String) map.get("name"));
	}
	return result;
    }

    public static List<Map<String, Object>> toMapList(List<JSONObject> list) {
	List<Map<String, Object>> reustList = new ArrayList<Map<String, Object>>();
	for (int i = list.size() - 1; i >= 0; i--) {
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

    /** 活动描述 */
    private String activityDesc;
    private List<Map> activityMap;
    private String activityStr;

    /** 领取方式 */
    private Integer characteristic;

    private String endDate;
    /** 活动id */
    private Integer id;

    /** 同步活动的ID列表 （旧） */
    private String ids;
    /** 活动名称 */
    private String name;

    private String openDate;
    private List<OssModifyActivity> ossActivities;
    

    public List<OssModifyActivity> getOssActivities() {
        return ossActivities;
    }

    private OssModifyActivity ossActivity;

    public OssModifyActivity getOssActivity() {
        return ossActivity;
    }

    public void setOssActivity(OssModifyActivity ossActivity) {
        this.ossActivity = ossActivity;
    }

    private List<OssActivityDetail> ossActivityDetails = new ArrayList<OssActivityDetail>();

    @Autowired
    private OssLogService ossLogService;

    /** 同步活动列表 */
    private List<OssModifyActivity> ossModifyActivityList;

    /** 同步功能 oss服务器列表 */
    private List<OssServer> ossServersList;

    private List<Map<String, Object>> reustList;

    private String rewardOverDate;
    /** 活动规则 */
    private String rule;
    /** 同步的服务器Id */
    private String serverArray;

    @Autowired
    private ISyncMoudle syncMoudle;
    /** 领取次数 */
    private Integer times;
    /**排序 */
    private Integer rank;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /** 活动类型 */
    private Integer type;
    private Map<Integer, String> typeMap;

    /** 同步活动的ID列表 (新） */
    private String types;

    @Action(value = "modifyActivity_addModifyActivityIndex", results = { @Result(name = "success", location = "../../zhxy/message/addModifyActivity_zc.jsp") })
    public String addModifyActivity() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ModifyActivityHandler");
	object.put("methodName", "getJsonObjectList");
	// object.put("ossFunctionAdjust", ossFunctionAdjust);
	object = CONNECTION.sendMsg(object);
	List<JSONObject> listType = (List<JSONObject>) object.get("typeList");
	reustList = toMapList(listType);
	return "success";
    }

    /** 新增活动区 */
    @Action(value = "modifyActivity_addModifyActivity", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "modifyActivity_index", "namespace",
		    "/zhxyzc/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", location = "../../zhxy/server/addModifyActivity_zc.jsp") })
    public String addOssServer() throws Exception {
	HttpSession session = ServletActionContext.getRequest().getSession();
	// 登录环境

	List<OssActivityDetail> ossActivityDetailList = new ArrayList<OssActivityDetail>();
	List<OssActivityDetail> ossActivityDetails = JSON.parseArray(
		activityStr, OssActivityDetail.class);
	for (OssActivityDetail activityDetail : ossActivityDetails) {
	    if (!StringUtils.isNotBlank(activityDetail.getItem())
		    || !StringUtils.isNotBlank(activityDetail.getPoint())
		    || !StringUtils.isNotBlank(activityDetail.getValue())
		    || activityDetail.getNumber() == null) {
		continue;
	    }
	    if (!checkItem(activityDetail.getItem())) {
		setActionMsg("Please input correct Reward items format");
		return "success";
	    }
	    ossActivityDetailList.add(activityDetail);
	}
	// 开始时间和结束时间
	// 开始时间和结束时间
	Date openTime = null, overTime = null, rewardOverTime = null;
	if (openDate != null && !"".equals(openDate)) {
	    openTime = DateService.getDateByStrAndFormat(openDate,
		    "yyyy-MM-dd HH:mm:ss");
	}/*
	  * else { openDate =
	  * DateService.getDateFormatStr(openTime,"yyyy-MM-dd HH:mm:ss"); }
	  */
	if (endDate != null && !"".equals(endDate)) {
	    overTime = DateService.getDateByStrAndFormat(endDate,
		    "yyyy-MM-dd HH:mm:ss");
	}/*
	  * else { overTime = DateService.getDateLastTime(endDate); }
	  */
	if (rewardOverDate != null && !"".equals(rewardOverDate)) {
	    rewardOverTime = DateService.getDateByStrAndFormat(rewardOverDate,
		    "yyyy-MM-dd HH:mm:ss");
	}
	/*ossActivities = (List<OssModifyActivity>) session
		.getAttribute("ossActivities");*/
	// if(ossActivities!=null&&ossActivities.size()>0){
	// OssActivity ddActivity= ossActivities.get(0);
	// System.out.print(ddActivity);
	// }
	
	JSONArray ossActivitiesJson = (JSONArray) session
		.getAttribute("ossActivities");
	Integer maxId = 1;
	/*
	 * for (OssActivity ossActivity : ossActivities) { if
	 * (ossActivity.getId() > maxId) { maxId = ossActivity.getId(); } }
	 */
	if (ossActivitiesJson != null) {
	    
	    for (Object jsonObject : ossActivitiesJson) {
		OssModifyActivity ossActivity =JSON.toJavaObject(JSON.parseObject(jsonObject.toString()), OssModifyActivity.class)  ;
		if (ossActivity.getId() > maxId) {
		    maxId = ossActivity.getId();
		}
	    }
	}
	int code = 0;
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("Id", maxId + 1);
	object.put("type", type);
	object.put("characteristic", characteristic);
	object.put("times", times);
	object.put("name", name);
	object.put("activityDesc", activityDesc);
	object.put("rule", rule);
	object.put("rank", rank);
	// object.put("carrierOperator", carrierOperator);
	object.put("openTime", openTime);
	object.put("overTime", overTime);
	object.put("rewardOverTime", rewardOverTime);
	object.put("ossActivityDetails", ossActivityDetailList);
	object.put("handlerName", "ModifyActivityHandler");
	object.put("methodName", "addActivity");
	try {
	    object = CONNECTION.sendMsg(object);

	    code = (Integer) object.get("code");

	    ossLogService.createOssLog(OssLogConstant.ADD_MODIFY_ACTIVITY,
		    activityStr);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// System.out.println("----------------------------------------------------------");
	if (code == 0) {
	    setActionMsg("OK");
	} else {
	    setActionMsg("ERROR");
	}
	return "success";

    }

    /**
     * 删除活动
     * */
    @Action(value = "modifyActivity_deleteModifyActivity", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "modifyActivity_index", "namespace", "/zhxyzc/message",
	    "actionMsg", "${actionMsg}" }) })
    public String deleteModifyActivity() throws Exception {
	// 登录环境
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("Id", id);
	object.put("handlerName", "ModifyActivityHandler");
	object.put("methodName", "deleteActivity");
	try {
	    object = CONNECTION.sendMsg(object);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	setActionMsg(CodeUtil.getActionMsgByMap(object));
	return "success";
    }

    public String getActivityDesc() {
	return activityDesc;
    }

    public List<Map> getActivityMap() {
	return activityMap;
    }

    public String getActivityStr() {
	return activityStr;
    }

    public Integer getCharacteristic() {
	return characteristic;
    }

    public String getEndDate() {
	return endDate;
    }

    private Object getHandleName() {
	return "ModifyActivityHandler";
    }

    public Integer getId() {
	return id;
    }

    public String getIds() {
	return this.ids;
    }

    public String getName() {
	return name;
    }

    public String getOpenDate() {
	return openDate;
    }

   
 

    public List<OssActivityDetail> getOssActivityDetails() {
	return ossActivityDetails;
    }

    public List<OssModifyActivity> getOssModifyActivityList() {
	return this.ossModifyActivityList;
    }

    public List<OssServer> getOssServersList() {
	return this.ossServersList;
    }

    public List<Map<String, Object>> getReustList() {
	return reustList;
    }

    public String getRewardOverDate() {
	return rewardOverDate;
    }

    public String getRule() {
	return rule;
    }

    public String getServerArray() {
	return this.serverArray;
    }

    public Integer getTimes() {
	return times;
    }

    public Integer getType() {
	return type;
    }

    public Map<Integer, String> getTypeMap() {
	return typeMap;
    }

    public String getTypes() {
	return types;
    }

    /** 刷新服务器活动 */
    @Action(value = "modifyActivity_reflash", results = { @Result(name = "success", type = "chain", location = "modifyActivity_index") })
    public String modifyActivity() throws Exception {

	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("handlerName", "ModifyActivityHandler");
	    object.put("methodName", "reflashActivity");
	    object = CONNECTION.sendMsg(object);
	    setActionMsg(CodeUtil.getActionMsgByMap(object));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "modifyActivity_select", results = { @Result(name = "success", location = "../../zhxy/message/modifyActivity_select.jsp") })
    public String modifyActivity_select() {
	return SUCCESS;
    }

    @Action(value = "modifyActivity_index", results = { @Result(name = "success", location = "../../zhxy/message/modifyActivity_zc.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ModifyActivityHandler");
	object = CONNECTION.sendMsg(object);
	ossActivities = (List<OssModifyActivity>) object.get("ossActivities");
	List<JSONObject> listType = (List<JSONObject>) object.get("typeList");
	typeMap = toMap(listType);
	/** 查询成功后,服务器列表赋值 */
	// ossServersList = baseAdminContext.getOssServers();
	HttpSession session = ServletActionContext.getRequest().getSession();
	session.setAttribute("ossActivities", ossActivities);
	return "success";
    }

    public void setActivityDesc(String activityDesc) {
	this.activityDesc = activityDesc;
    }

    public void setActivityMap(List<Map> activityMap) {
	this.activityMap = activityMap;
    }

    public void setActivityStr(String activityStr) {
	this.activityStr = activityStr;
    }

    public void setCharacteristic(Integer characteristic) {
	this.characteristic = characteristic;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setIds(String ids) {
	this.ids = ids;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setOpenDate(String openDate) {
	this.openDate = openDate;
    }

    public void setOssActivities(List<OssModifyActivity> ossActivities) {
	this.ossActivities = ossActivities;
    }

   

    public void setOssActivityDetails(List<OssActivityDetail> ossActivityDetails) {
	this.ossActivityDetails = ossActivityDetails;
    }

    public void setOssModifyActivityList(
	    List<OssModifyActivity> ossModifyActivityList) {
	this.ossModifyActivityList = ossModifyActivityList;
    }

    public void setOssServersList(List<OssServer> ossServersList) {
	this.ossServersList = ossServersList;
    }

    public void setReustList(List<Map<String, Object>> reustList) {
	this.reustList = reustList;
    }

    public void setRewardOverDate(String rewardOverDate) {
	this.rewardOverDate = rewardOverDate;
    }

    public void setRule(String rule) {
	this.rule = rule;
    }

    public void setServerArray(String serverArray) {
	this.serverArray = serverArray;
    }

    public void setTimes(Integer times) {
	this.times = times;
    }

    public void setType(Integer type) {
	this.type = type;
    }

    public void setTypeMap(Map<Integer, String> typeMap) {
	this.typeMap = typeMap;
    }

    public void setTypes(String types) {
	this.types = types;
    }

    // 同步到其他服务器代码
    @Action(value = "modifyActivity_syn", results = { @Result(name = "success", location = "../../zhxy/message/modifyActivity_zc.jsp") })
    public void synAdjustActivity() throws Exception {

	Map<String, Object> querySynMap = Maps.newHashMap();
	querySynMap.put("handlerName", getHandleName());
	querySynMap.put("methodName", "querySynModifyActivity");
	querySynMap.put("ids", types);

	Map<String, Object> querySynList = Maps.newHashMap();
	querySynList.put("handlerName", getHandleName());
	querySynList.put("methodName", "synModifyActivity");
	JSONObject object = new JSONObject();
	object.put("id", serverArray);
	object.put(
		"msg",
		syncMoudle.syncMoudle(querySynMap, querySynList,
			serverArray.split(",")));
	getJSONResponse().responseJson(object);
	// getJSONResponse().responseJson(syncMoudle.syncMoudle(querySynMap,querySynList,serverArray.split(",")));
    }

    @Action(value = "modifyActivity_updateModifyActivity", results = { @Result(name = "success", type = "chain", params = {
	    "actionName", "modifyActivity_index", "namespace", "/zhxyzc/message",
	    "actionMsg", "${actionMsg}" }) })
    public String updateModifyActivity() throws Exception {
	HttpSession session = ServletActionContext.getRequest().getSession();
	// 登录环境
	if (StringUtils.isNotBlank(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"), "UTF-8");
	    addActionMessage(getActionMsg());
	}
	if (!StringUtils.isNotBlank(ossActivity.getRule())
		|| !StringUtils.isNotBlank(ossActivity.getName())
		|| !StringUtils.isNotBlank(ossActivity.getActivityDesc())) {
	    setActionMsg("Please input correct format");
	    return "success";
	}
	if (ossActivityDetails != null) {
	    for (OssActivityDetail ossActivityDetail : ossActivityDetails) {
		if (!checkItem(ossActivityDetail.getItem())) {
		    setActionMsg("Please input correct Reward items format");
		    return "success";
		}
	    }
	}
	// 开始时间和结束时间
	Date openTime = null, overTime = null, rewardOverTime = null;
	if (openDate != null && !"".equals(openDate)) {
	    openTime = DateService.getDateByStrAndFormat(openDate,
		    "yyyy-MM-dd HH:mm:ss");
	} /*
	   * else { openTime = DateService.getCurrentDayFirstUtilDate();
	   * openDate = DateService.getDateFormatStr(openTime, "yyyy-MM-dd"); }
	   */
	if (endDate != null && !"".equals(endDate)) {
	    overTime = DateService.getDateByStrAndFormat(endDate,
		    "yyyy-MM-dd HH:mm:ss");
	    /*
	     * overTime = DateService.getCurrentDayLastUtilDate(); endDate =
	     * DateService.getDateFormatStr(overTime, "yyyy-MM-dd");1
	     */
	} /*
	   * else { overTime = DateService.getDateLastTime(endDate); }
	   */

	if (rewardOverDate != null && !"".equals(rewardOverDate)) {
	    rewardOverTime = DateService.getDateByStrAndFormat(rewardOverDate,
		    "yyyy-MM-dd HH:mm:ss");
	}
	int code = 0;
	Map<String, Object> object = new HashMap<String, Object>();

	String id = ServletActionContext.getRequest().getParameter("id");
	Integer ID = Integer.parseInt(id);
	object.put("Id", ID);
	object.put("type", ossActivity.getType());
	object.put("characteristic", ossActivity.getCharacteristic());
	object.put("times", ossActivity.getTimes());
	object.put("name", ossActivity.getName());
	object.put("activityDesc", ossActivity.getActivityDesc());
	object.put("rule", ossActivity.getRule());
	object.put("rank", ossActivity.getRank());
	// object.put("carrierOperator", ossActivity.getCarrierOperator());
	object.put("openTime", openTime);
	object.put("overTime", overTime);
	object.put("rewardOverTime", rewardOverTime);
	object.put("ossActivityDetails", ossActivityDetails);
	object.put("handlerName", "ModifyActivityHandler");
	object.put("methodName", "updateActivity");
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

    /** 修改游戏区页面 */
    @Action(value = "modifyActivity_updateModifyActivityIndex", results = { @Result(name = "success", location = "../../zhxy/message/updateModifyActivity_zc.jsp") })
    public String updateModifyActivityIndex() throws Exception {

	HttpSession session = ServletActionContext.getRequest().getSession();
	if (StringUtils.isNotBlank(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"), "utf-8");
	    addActionMessage(getActionMsg());
	}

	String id = ServletActionContext.getRequest().getParameter("id");
	Integer ID = Integer.parseInt(id);

	JSONArray ossActivitiesJson = (JSONArray) session
		.getAttribute("ossActivities");
	for ( Object jsonObject : ossActivitiesJson) {
	    
	    OssModifyActivity oss= JSON.toJavaObject(JSON.parseObject(jsonObject.toString()),OssModifyActivity.class);
	    
	    if (ID.intValue() == oss.getId().intValue()) {
		ossActivity=new OssModifyActivity();
		List<OssActivityDetail> ossActivityDetails=new ArrayList<OssActivityDetail>();
		JSONObject json=JSON.parseObject(jsonObject.toString());
		ossActivityDetails=JSON.parseArray(json.getString("activityDetails") ,OssActivityDetail.class);
		ossActivity = JSON.toJavaObject(JSON.parseObject(jsonObject.toString()),OssModifyActivity.class);
		ossActivity.setOssActivityDetails(ossActivityDetails);
		if (ossActivity.getOpenTime() != null
			&& !"".equals(ossActivity.getOpenTime()))
		    openDate = DateService.getDateFormatStr(
			    ossActivity.getOpenTime(), "yyyy-MM-dd HH:mm:ss");
		if (ossActivity.getOverTime() != null
			&& !"".equals(ossActivity.getOverTime()))
		    endDate = DateService.getDateFormatStr(
			    ossActivity.getOverTime(), "yyyy-MM-dd HH:mm:ss");
		if (ossActivity.getRewardOverTime() != null
			&& !"".equals(ossActivity.getRewardOverTime()))
		    rewardOverDate = DateService.getDateFormatStr(
			    ossActivity.getRewardOverTime(),
			    "yyyy-MM-dd HH:mm:ss");
		break;
	    }

	}
	ossActivityDetails = ossActivity.getOssActivityDetails();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ModifyActivityHandler");
	object.put("methodName", "getJsonObjectList");
	// object.put("ossFunctionAdjust", ossFunctionAdjust);
	object = CONNECTION.sendMsg(object);
	List<JSONObject> listType = (List<JSONObject>) object.get("typeList");
	reustList = toMapList(listType);
	return "success";
    }

}
