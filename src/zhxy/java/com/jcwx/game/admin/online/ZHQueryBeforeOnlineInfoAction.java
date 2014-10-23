package com.jcwx.game.admin.online;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.DataHistory;

import com.jcwx.game.util.ExportExcel;
import com.opensymphony.xwork2.ActionContext;

/**
 * 在线与注册：历史在线数据
 * */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/zhxy/online")
@ResultPath("/")
public class ZHQueryBeforeOnlineInfoAction extends BasalAction {

    private Float allAvgNum;

    private Integer allMaxNum;

    private Integer allMinNum;

    /** 总记录数 */
    private Integer allNum;

    private String beginDate;

    private Float currAvgNum;

    /** 最高值和平均值 */
    private Integer currMaxNum;

    private Integer currMinNum;
    /** 当前页数 */
    private Integer currPageNO;
    private List<DataHistory> dataHistoryList;
    private String endDate;
    /** 每页记录数 */
    private Integer onePageNum;
    /** 排序标记 */
    private String orderFlag;

    /** 总页数 */
    private Integer pages;

    private List<HashMap> data;
    @Override
    @SuppressWarnings({ "unchecked" })
    @Action(value = "zhqueryBeforeOnlineInfo_index", results = { @Result(name = "success", location = "../../zhxy/online/ZHqueryBeforeOnlineInfo.jsp") })
    public String execute() throws Exception {
	// 总记录数
	if (currPageNO == null || currPageNO.intValue() <= 0) {
	    currPageNO = 1;
	}
	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 20; // 每页20条记录
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;
	// IPayHistoryService payHistoryService =
	// (IPayHistoryService)SpringService.getBean("payHistoryService");
	// 排序
	if (orderFlag == null || "".equals(orderFlag)) {
	    orderFlag = "DESC";
	}
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

	Long begin = Long.parseLong(DateService.getDateFormatStr(beginTime,
		"yyyyMMddHHmm"));
	Long end = Long.parseLong(DateService.getDateFormatStr(endTime,
		"yyyyMMddHHmm"));

	// IDataHistoryService dataHistoryService =
	// (IDataHistoryService)SpringService.getBean("dataHistoryService");
	// 数据集合
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("begin", begin);
	object.put("end", end);
	object.put("orderFlag", orderFlag);
	object.put("beginNum", beginNum);
	object.put("onePageNum", onePageNum);
	object.put("handlerName", "QueryBeforeOnlineInfoHandler");
	object = CONNECTION.sendMsg(object);

	if (object == null) {
	    return "success";
	}
	allNum = (Integer) object.get("allNum");
	dataHistoryList = (List<DataHistory>) object.get("dataHistoryList");

	Map<String, Object> currInfo = null;
	Map<String, Object> allInfo = null;
	currInfo = (Map<String, Object>) object.get("currInfo");
	allInfo = (Map<String, Object>) object.get("allInfo");
	if (currInfo != null && currInfo.get("maxNum") != null) {
	    currMaxNum = Integer.parseInt(currInfo.get("maxNum").toString());
	    currMinNum = Integer.parseInt(currInfo.get("minNum").toString());
	    currAvgNum = Math.round(Double.parseDouble(currInfo.get("avgNum")
		    .toString()) * 100) / 100.0f;
	    allMaxNum = Integer.parseInt(allInfo.get("maxNum").toString());
	    allMinNum = Integer.parseInt(allInfo.get("minNum").toString());
	    allAvgNum = Math.round(Double.parseDouble(allInfo.get("avgNum")
		    .toString()) * 100) / 100.0f;
	}
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	return "success";
    }

    @SuppressWarnings("unchecked")
    @Action(value = "zhqueryBeforeOnlineInfo_data")
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
	Long begin = Long.parseLong(DateService.getDateFormatStr(beginTime,
		"yyyyMMddHHmm"));
	Long end = Long.parseLong(DateService.getDateFormatStr(endTime,
		"yyyyMMddHHmm"));
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("begin", begin);
	object.put("end", end);
	object.put("handlerName", "QueryBeforeOnlineInfoHandler");
	object.put("methodName", "queryBeforeOnlineInfoForExportData");
	object = CONNECTION.sendMsg(object);
	List<DataHistory> entityList = (List<DataHistory>) object
		.get("entityList");
	List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
	if (entityList != null) {
	    for (DataHistory dh : entityList) {
		Date date = DateService
			.getDateByStrAndFormat(dh.getDataHistoryID().toString()
				+ "00", "yyyyMMddHHmmss");
		String dateStr = DateService.getDateFormatStr(date,
			"yyyy-MM-dd HH:mm");
		HashMap<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("date", dateStr);
		tempMap.put("value", dh.getOnlinePlayerNum());
		mapList.add(tempMap);
	    }
	}

	String[] titles = { "统计时间", "最大在线人数" };
	String[] elements = { "date", "value" };
	String fileName = beginDate + "-" + endDate + "在线人数";
	String sheetName = "历史登陆统计";
	String FileType = ".xls";
	ActionContext context = ActionContext.getContext();
	HttpServletResponse response = (HttpServletResponse) context
		.get(StrutsStatics.HTTP_RESPONSE);
	response.setCharacterEncoding("utf-8");

	ExportExcel.export(titles, elements, mapList, fileName, sheetName,
		response, Map.class, FileType);

	return null;

    }

    @Action(value = "zhqueryBeforeOnlineInfo_queryPlayerList", results = { @Result(name = "success", location = "../../zhxy/online/onlinePlayerList.jsp") })
    public String queryPlayerList() throws Exception {
	// 总记录数
	if (currPageNO == null || currPageNO.intValue() <= 0) {
	    currPageNO = 1;
	}
	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 20; // 每页20条记录
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;
	// IPayHistoryService payHistoryService =
	// (IPayHistoryService)SpringService.getBean("payHistoryService");
	
	String date = beginDate+":00";
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("date", date);
	object.put("handlerName", "QueryBeforeOnlineInfoHandler");
	object.put("methodName", "queryLoginPlayerList");
	object = CONNECTION.sendMsg(object);
	data = (List<HashMap>) object
		.get("data");
	return "success";
    }
    public Float getAllAvgNum() {
	return allAvgNum;
    }

    public Integer getAllMaxNum() {
	return allMaxNum;
    }

    public Integer getAllMinNum() {
	return allMinNum;
    }

    public Integer getAllNum() {
	return allNum;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public Float getCurrAvgNum() {
	return currAvgNum;
    }

    public Integer getCurrMaxNum() {
	return currMaxNum;
    }

    public Integer getCurrMinNum() {
	return currMinNum;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public List<DataHistory> getDataHistoryList() {
	return dataHistoryList;
    }

    public String getEndDate() {
	return endDate;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public String getOrderFlag() {
	return orderFlag;
    }

    public Integer getPages() {
	return pages;
    }

    public void setAllAvgNum(Float allAvgNum) {
	this.allAvgNum = allAvgNum;
    }

    public void setAllMaxNum(Integer allMaxNum) {
	this.allMaxNum = allMaxNum;
    }

    public void setAllMinNum(Integer allMinNum) {
	this.allMinNum = allMinNum;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setCurrAvgNum(Float currAvgNum) {
	this.currAvgNum = currAvgNum;
    }

    public void setCurrMaxNum(Integer currMaxNum) {
	this.currMaxNum = currMaxNum;
    }

    public void setCurrMinNum(Integer currMinNum) {
	this.currMinNum = currMinNum;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOrderFlag(String orderFlag) {
	this.orderFlag = orderFlag;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public List<HashMap> getData() {
        return data;
    }

    public void setData(List<HashMap> data) {
        this.data = data;
    }

}
