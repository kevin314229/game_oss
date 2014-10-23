package com.jcwx.game.admin.online;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.PlayerCreateStat;

import com.jcwx.game.util.ExportExcel;
import com.opensymphony.xwork2.ActionContext;

/**
 * 在线与注册：分时注册统计
 * */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/zhxy/online")
@ResultPath("/")
public class ZHQueryPlayerRegisterStatByHourAction extends BasalAction {

    /** 总记录数 */
    private Integer allNum;

    /** 总注册人数 */
    private Integer allRegisterNum;

    /** 开始时间 */
    private String beginDate;

    /** 当前页数 */
    private Integer currPageNO;

    private String data;

    /** 结束时间 */
    private String endDate;

    /** 每页记录数 */
    private Integer onePageNum;

    /** 总页数 */
    private Integer pages;

    private List<PlayerCreateStat> ptList;
    // /** 统计的集合 */
    private List<PlayerCreateStat> statList;

    @Override
    @SuppressWarnings("unchecked")
    @Action(value = "ZHqueryPlayerRegisterStatByHour", results = { @Result(name = "success", location = "../../zhxy/online/queryPlayerRegisterStatByHour.jsp") })
    public String execute() throws Exception {
	// 开始时间和结束时间
	Date beginTime = new Date();
	Date endTime = new Date();
	if (beginDate == null || "".equals(beginDate)) {
	    beginTime = DateService.getCurrentMonthFirstUtilDate();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	} else {
	    beginTime = DateService.getDateFirstTime(beginDate);
	}

	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	// IPlayerService playerService =
	// (IPlayerService)SpringService.getBean("playerService");
	// //总记录数
	// allRegisterNum = playerService.getPlayerNum();修改
	//
	// statList = playerService.getPlayerCreateStatListByHour(beginTime,
	// endTime);修改
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "QueryPlayerRegisterStatByHourHandler");
	object = CONNECTION.sendMsg(object);
	// 总记录数
	if (onePageNum == null || getOnePageNum().intValue() == 0) {
	    onePageNum = 20; // 每页20条记录
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0) {
	    beginNum = 0;
	}
	statList = (List<PlayerCreateStat>) object.get("statList");
	ptList = (List<PlayerCreateStat>) object.get("ptList");
	List<List<PlayerCreateStat>> pageCreateStat = Lists.newArrayList();
	List<PlayerCreateStat> pcsList = Lists.newArrayList();
	for (PlayerCreateStat pcs : statList) {
	    pcsList.add(pcs);
	    if ("100".equals(pcs.getHour())) {
		pageCreateStat.add(pcsList);
		pcsList = Lists.newArrayList();
	    }

	}
	allNum = pageCreateStat.size() * onePageNum;
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	PlayerCreateStat dayStat = (PlayerCreateStat) CollectionUtils.find(
		statList, new BeanPredicate("day", new EqualPredicate("100")));
	PlayerCreateStat monthStat = (PlayerCreateStat) CollectionUtils
		.find(statList, new BeanPredicate("month", new EqualPredicate(
			"100")));
	PlayerCreateStat yearStat = (PlayerCreateStat) CollectionUtils.find(
		statList, new BeanPredicate("year", new EqualPredicate("100")));

	this.data = initialData(statList);
	statList = pageCreateStat.get(currPageNO - 1);
	statList.add(dayStat);
	statList.add(monthStat);
	statList.add(yearStat);

	allRegisterNum = (Integer) object.get("allRegisterNum");

	return "success";
    }

    @Action(value = "ZHqueryPlayerRegisterStatByHourdata")
    public String exportData() throws Exception {
	// 开始时间和结束时间
	Date beginTime = new Date();
	Date endTime = new Date();
	if (beginDate == null || "".equals(beginDate)) {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	} else {
	    beginTime = DateService.getDateFirstTime(beginDate);
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
	object.put("handlerName", "QueryPlayerRegisterStatByHourHandler");
	object.put("methodName", "QueryPlayerRegisterStatByHourForExportData");
	object = CONNECTION.sendMsg(object);
	List<PlayerCreateStat> ptList = (List<PlayerCreateStat>) object
		.get("ptList");

	String[] titles = { "日期", "运营商", "运营商标识", "注册人数" };
	String[] elements = { "dateTime", "carrierOperator2",
		"carrierOperator", "countNum" };
	String fileName = beginDate + "-" + endDate + "分时注册统计";
	String sheetName = "历史登陆统计";
	String FileType = ".xls";
	ActionContext context = ActionContext.getContext();
	HttpServletResponse response = (HttpServletResponse) context
		.get(StrutsStatics.HTTP_RESPONSE);
	response.setCharacterEncoding("utf-8");

	ExportExcel.export(titles, elements, ptList, fileName, sheetName,
		response, PlayerCreateStat.class, FileType);

	return null;
    }

    public Integer getAllNum() {
	return this.allNum;
    }

    public Integer getAllRegisterNum() {
	return allRegisterNum;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public Integer getCurrPageNO() {
	return this.currPageNO;
    }

    public String getData() {
	return this.data;
    }

    public String getEndDate() {
	return endDate;
    }

    public Integer getOnePageNum() {
	return this.onePageNum;
    }

    public Integer getPages() {
	return this.pages;
    }

    public List<PlayerCreateStat> getPtList() {
	return ptList;
    }

    public List<PlayerCreateStat> getStatList() {
	return statList;
    }

    private String initialData(List<PlayerCreateStat> statList) {
	Set<String> dateList = new HashSet<String>();
	JSONObject jsonObject = new JSONObject();
	JSONObject dateObject = null;
	for (PlayerCreateStat createStat : statList) {
	    if (isCondition(createStat.getYear())
		    || isCondition(createStat.getMonth())
		    || isCondition(createStat.getDay())) {
		continue;
	    }
	    String date = createStat.getYear() + "-" + createStat.getMonth()
		    + "-" + createStat.getDay();
	    dateList.add(date);
	    dateObject = jsonObject.getJSONObject(date);
	    if (dateObject == null) {
		dateObject = new JSONObject();
		dateObject.put("name", date);
		// 改成二维数组
		// 2
		JSONArray dataArray2 = new JSONArray();
		dataArray2.add(createStat.getCountNum());
		// 1
		JSONArray dataArray = new JSONArray();
		dataArray.add(dataArray2);
		dateObject.put("data", dataArray);
		jsonObject.put(date, dateObject);
	    } else {
		JSONArray dataArray2 = new JSONArray();
		dataArray2.add(createStat.getCountNum());

		dateObject.getJSONArray("data").add(dataArray2);
	    }
	}
	JSONArray array = new JSONArray();
	Iterator<String> iterator = dateList.iterator();
	while (iterator.hasNext()) {
	    array.add(jsonObject.get(iterator.next()));
	}
	return array.toJSONString();
    }

    private boolean isCondition(String value) {
	if(value==null||"".equals(value)){
	    return false;
	}else if (Integer.valueOf(value).intValue() == 100) {
	    return true;
	}
	return false;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setAllRegisterNum(Integer allRegisterNum) {
	this.allRegisterNum = allRegisterNum;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setData(String data) {
	this.data = data;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setPtList(List<PlayerCreateStat> ptList) {
	this.ptList = ptList;
    }

    public void setStatList(List<PlayerCreateStat> statList) {
	this.statList = statList;
    }

}
