/**
 * 
 */
package com.jcwx.game.admin.online;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.PerformanceTimer;
import com.jcwx.game.domain.ZLevelDay;


/** 查看每日玩家等级分布 */
@ParentPackage("base")
@Namespace("/admin/online")
@ResultPath("/")
@Action(value = "queryLevelDaily", results = { @Result(name = "success", location = "/admin/online/queryLevelDaily.jsp") })
public class QueryLevelDailyAction extends BasalAction {

    private static final long serialVersionUID = 1022300L;

    /** 开始时间 */
    private String beginDate;

    List<ZLevelDay> curAssayList;

    private String data;

    /** 结束时间 */
    private String endDate;

    private List<JSONObject> jsonList;

    private StringBuilder sbBuilder;

    @Override
    @SuppressWarnings("unchecked")
    public String execute() throws Exception {

	PerformanceTimer timer = new PerformanceTimer();
	// 开始时间和结束时间
	Date beginTime = new Date();
	Date endTime = new Date();
	if (beginDate == null || "".equals(beginDate)) {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginTime = DateService.dateIncreaseByDay(beginTime, -1);// 3天前的
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	} else {
	    beginTime = DateService.getDateFirstTime(beginDate);
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayFirstUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateFirstTime(endDate);
	}

	// 最大时间间隔限制
	int betweenDay = DateService.DayBetween(beginTime, endTime);
	if (betweenDay > 4) {
	    this.addActionError("最大相隔天数为5天");
	    return "success";
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "QueryLevelDailyHandler");
	object = CONNECTION.sendMsg(object);
	super.handleKryoMap(object);
	curAssayList = (List<ZLevelDay>) object.get("curAssayList");
	JSONObject jsonObject = new JSONObject();
	JSONObject dateObject = null;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	sbBuilder = new StringBuilder();
	// JSONArray dataArray = null;
	Set<String> dateList = new HashSet<String>();
	for (ZLevelDay zLevelDay : curAssayList) {

	    String date = formatter.format(zLevelDay.getIdDate());
	    dateList.add(date);
	    dateObject = jsonObject.getJSONObject(date);
	    if (dateObject == null) {
		dateObject = new JSONObject();
		dateObject.put("name", date);
		// 改成二维数组
		// 2
		JSONArray dataArray2 = new JSONArray();
		dataArray2.add(zLevelDay.getLevel());
		dataArray2.add(zLevelDay.getPeopleNum());
		// 1
		JSONArray dataArray = new JSONArray();
		dataArray.add(dataArray2);
		dateObject.put("data", dataArray);
		jsonObject.put(date, dateObject);
	    } else {
		JSONArray dataArray2 = new JSONArray();
		dataArray2.add(zLevelDay.getLevel());
		dataArray2.add(zLevelDay.getPeopleNum());

		dateObject.getJSONArray("data").add(dataArray2);
	    }
	}
	JSONArray array = new JSONArray();
	Iterator<String> iterator = dateList.iterator();
	while (iterator.hasNext()) {
	    array.add(jsonObject.get(iterator.next()));
	}
	this.data = array.toJSONString();
	// System.out.println(data);

	setLocalRunTime(timer.get());

	return "success";
    }

    public String getBeginDate() {
	return beginDate;
    }

    public List<ZLevelDay> getCurAssayList() {
	return curAssayList;
    }

    public String getData() {
	return data;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<JSONObject> getJsonList() {
	return jsonList;
    }

    public StringBuilder getSbBuilder() {
	return sbBuilder;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setCurAssayList(List<ZLevelDay> curAssayList) {
	this.curAssayList = curAssayList;
    }

    public void setData(String data) {
	this.data = data;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setJsonList(List<JSONObject> jsonList) {
	this.jsonList = jsonList;
    }

    public void setSbBuilder(StringBuilder sbBuilder) {
	this.sbBuilder = sbBuilder;
    }

}
