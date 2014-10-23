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
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.OssActivity;
import com.jcwx.game.http.domain.OssActivityDetail;
import com.jcwx.game.http.domain.OssModifyActivity;
import com.jcwx.game.service.ISyncMoudle;
import com.jcwx.game.service.oss.impl.OssLogService;


/**
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class WinphoneModifyActivityAction extends BasalAction {
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

    /** 活动描述 */
    private String activityDesc;
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

    private OssActivity ossActivity;
    private List<OssActivityDetail> ossActivityDetails = new ArrayList<OssActivityDetail>();

    @Autowired
    private OssLogService ossLogService;

    /** 同步活动列表 */
    private List<OssModifyActivity> ossModifyActivityList;

    /** 同步功能 oss服务器列表 */
    private List<OssServer> ossServersList;

    /** 活动规则 */
    private String rule;

    /** 同步的服务器Id */
    private String serverArray;

    @Autowired
    private ISyncMoudle syncMoudle;
    /** 领取次数 */
    private Integer times;

    /** 活动类型 */
    private Integer type;
    /** 同步活动的ID列表 (新） */
    private String types;
    private Integer winphoneType;
    private String winphoneContent;

    @Action(value = "modifyActivity_winphone_addModifyActivityIndex", results = { @Result(name = "success", location = "../../admin/message/winphone_addModifyActivity.jsp") })
    public String addModifyActivity() {
	return "success";
    }

    /** 新增活动区 */
    @Action(value = "modifyActivity_winphone_addModifyActivity", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "modifyActivity_winphoneindex", "namespace",
		    "/admin/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", location = "../../admin/server/winphone_addModifyActivity.jsp") })
    public String addOssServer() throws Exception {
	HttpSession session = ServletActionContext.getRequest().getSession();

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
	Date openTime = null, overTime = null;
	if (openDate != null && !"".equals(openDate)) {
	    openTime = DateService.getDateByStrAndFormat(openDate,
		    "yyyy-MM-dd HH:mm:ss");
	}/*
	  * else { openDate =
	  * DateService.getDateFormatStr(openTime,"yyyy-MM-dd HH:mm:ss"); }
	  */
	if (endDate != null || !"".equals(endDate)) {
	    overTime = DateService.getDateByStrAndFormat(endDate,
		    "yyyy-MM-dd HH:mm:ss");
	}/*
	  * else { overTime = DateService.getDateLastTime(endDate); }
	  */
	ossActivities = (List<OssModifyActivity>) session
		.getAttribute("ossActivities");
	Integer maxId = 1;
	if (ossActivities != null) {
	    for (int i = 0; i < ossActivities.size(); i++) {
		OssActivity ossActivity = ossActivities.get(i);
		if (ossActivity.getId() > maxId) {
		    maxId = ossActivity.getId();
		}
	    }
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("Id", maxId + 1);
	object.put("type", type);
	object.put("characteristic", characteristic);
	object.put("times", times);
	object.put("name", name);
	object.put("activityDesc", activityDesc);
	object.put("rule", rule);
	// object.put("carrierOperator", carrierOperator);
	object.put("openTime", openTime);
	object.put("overTime", overTime);
	object.put("winphoneType", getWinphoneType());
	object.put("winphoneContent", getWinphoneContent());
	
	object.put("ossActivityDetails", ossActivityDetailList);
	object.put("handlerName", "ModifyActivityHandler");
	object.put("methodName", "addActivity");
	try {
	    object = CONNECTION.sendMsg(object);

	    ossLogService.createOssLog(OssLogConstant.ADD_MODIFY_ACTIVITY,
		    activityStr);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	setActionMsg(CodeUtil.getActionMsgByMap(object));
	return "success";
    }

    public String getActivityDesc() {
	return activityDesc;
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

    public List<OssModifyActivity> getOssActivities() {
	return this.ossActivities;
    }

    public OssActivity getOssActivity() {
	return ossActivity;
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

    public String getTypes() {
	return types;
    }

    @Action(value = "modifyActivity_winphoneindex", results = { @Result(name = "success", location = "../../admin/message/winphone_modifyActivity.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ModifyActivityHandler");
	object = CONNECTION.sendMsg(object);
	ossActivities = (List<OssModifyActivity>) object.get("ossActivities");

	/** 查询成功后,服务器列表赋值 */
	// ossServersList = baseAdminContext.getOssServers();

	HttpSession session = ServletActionContext.getRequest().getSession();
	session.setAttribute("ossActivities", ossActivities);
	return "success";
    }

    public void setActivityDesc(String activityDesc) {
	this.activityDesc = activityDesc;
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

    public void setOssActivity(OssActivity ossActivity) {
	this.ossActivity = ossActivity;
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

    public void setTypes(String types) {
	this.types = types;
    }


    @Action(value = "modifyActivity_winphone_edit", results = { @Result(name = "success", type = "chain", params = {
	    "actionName", "modifyActivity_winphoneindex", "namespace",
	    "/admin/message", "actionMsg", "${actionMsg}" }) })
    public String updateModifyActivity() throws Exception {
	// 登录环境
	if (StringUtils.isNotBlank(getActionMsg())) {
	    addActionMessage(getActionMsg());
	}
	if (ossActivity==null||!StringUtils.isNotBlank(ossActivity.getRule())
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
	Date openTime = null, overTime = null;
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
	// object.put("carrierOperator", ossActivity.getCarrierOperator());
	object.put("openTime", openTime);
	object.put("overTime", overTime);
	object.put("winphoneType", ossActivity.getWinphoneType());
	object.put("winphoneContent", ossActivity.getWinphoneContent());
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
    @Action(value = "modifyActivity_winphone_editModifyActivityIndex", results = { @Result(name = "success", location = "../../admin/message/winphone_editModifyActivity.jsp") })
    public String updateModifyActivityIndex() throws Exception {

	HttpSession session = ServletActionContext.getRequest().getSession();
	if (StringUtils.isNotBlank(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"), "utf-8");
	    addActionMessage(getActionMsg());
	}

	String id = ServletActionContext.getRequest().getParameter("id");
	Integer ID = Integer.parseInt(id);

	List<OssActivity> ossActivities = (List<OssActivity>) session
		.getAttribute("ossActivities");
	for (OssActivity oss : ossActivities) {
	    if (ID.intValue() == oss.getId().intValue()) {
		ossActivity = oss;
		openDate = DateService.getDateFormatStr(
			ossActivity.getOpenTime(), "yyyy-MM-dd HH:mm:ss");
		endDate = DateService.getDateFormatStr(
			ossActivity.getOverTime(), "yyyy-MM-dd HH:mm:ss");
		break;
	    }

	}
	ossActivityDetails = ossActivity.getOssActivityDetails();
	return "success";
    }

    public Integer getWinphoneType() {
        return winphoneType;
    }

    public void setWinphoneType(Integer winphoneType) {
        this.winphoneType = winphoneType;
    }

    public String getWinphoneContent() {
        return winphoneContent;
    }

    public void setWinphoneContent(String winphoneContent) {
        this.winphoneContent = winphoneContent;
    }

    
}
