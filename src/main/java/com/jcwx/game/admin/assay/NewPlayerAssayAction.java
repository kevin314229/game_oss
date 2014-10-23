package com.jcwx.game.admin.assay;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;


/**
 * 新玩家数据分析
 * 
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class NewPlayerAssayAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 开始时间 */
    private String beginDate;

    private Integer countPeople;

    /** 结束时间 */
    private String endDate;

    List<Map<String, Object>> firstLoginList;

    private List<JSONObject> jsonList;

    private List<JSONObject> onlineList;

    List<Map<String, Object>> onlineTimeList;

    public String getBeginDate() {
	return beginDate;
    }

    public Integer getCountPeople() {
	return countPeople;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<Map<String, Object>> getFirstLoginList() {
	return firstLoginList;
    }

    public List<JSONObject> getJsonList() {
	return jsonList;
    }

    public List<JSONObject> getOnlineList() {
	return onlineList;
    }

    public List<Map<String, Object>> getOnlineTimeList() {
	return onlineTimeList;
    }

    /**
     * 新玩家数据分析
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Action(value = "newPlayerAssay", results = { @Result(name = "success", location = "/admin/assay/newPlayerAssay.jsp") })
    public String newPlayerAssay() throws Exception {

	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "LoginLogHandler");
	object.put("methodName", "browseNewPlayerAssay");
	object = CONNECTION.sendMsg(object);

	countPeople = (Integer) object.get("countPeople");
	firstLoginList = (List<Map<String, Object>>) object
		.get("firstLoginAssay");
	onlineTimeList = (List<Map<String, Object>>) object
		.get("onlineTimeAssay");

	jsonList = new ArrayList<JSONObject>();
	onlineList = new ArrayList<JSONObject>();
	for (Map<String, Object> map : firstLoginList) {
	    JSONObject json = new JSONObject();
	    json.put("c", map.get("c"));
	    json.put("hh", map.get("hh"));
	    jsonList.add(json);
	}

	for (Map<String, Object> map : onlineTimeList) {
	    JSONObject json = new JSONObject();
	    json.put("temp", map.get("temp1"));
	    json.put("cc", map.get("c"));
	    onlineList.add(json);
	}
	return "success";
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setCountPeople(Integer countPeople) {
	this.countPeople = countPeople;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setFirstLoginList(List<Map<String, Object>> firstLoginList) {
	this.firstLoginList = firstLoginList;
    }

    public void setJsonList(List<JSONObject> jsonList) {
	this.jsonList = jsonList;
    }

    public void setOnlineList(List<JSONObject> onlineList) {
	this.onlineList = onlineList;
    }

    public void setOnlineTimeList(List<Map<String, Object>> onlineTimeList) {
	this.onlineTimeList = onlineTimeList;
    }

}
