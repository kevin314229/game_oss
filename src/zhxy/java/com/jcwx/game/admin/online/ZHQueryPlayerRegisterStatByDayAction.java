package com.jcwx.game.admin.online;

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
import com.jcwx.game.domain.PlayerCreateStat;

import com.jcwx.game.util.ExportExcel;
import com.opensymphony.xwork2.ActionContext;

/**
 * 在线与注册：注册数据统计
 * */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/zhxy/online")
@ResultPath("/")
public class ZHQueryPlayerRegisterStatByDayAction extends BasalAction {

    /** 总注册人数 */
    private Integer allRegisterNum;

    /** 开始时间 */
    private String beginDate;

    /** 结束时间 */
    private String endDate;

    List<PlayerCreateStat> ptList;

    List<PlayerCreateStat> statList;

    @Override
    @SuppressWarnings("unchecked")
    @Action(value = "ZHqueryPlayerRegisterStatByDay", results = { @Result(name = "success", location = "../../zhxy/online/queryPlayerRegisterStatByDay.jsp") })
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
	// 总记录数
	// allRegisterNum = playerService.getPlayerNum();
	// statList = playerService.getPlayerCreateStatListByDay(beginTime,
	// endTime);

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "QueryPlayerRegisterStatByDayHandler");
	object = CONNECTION.sendMsg(object);
	try {
	    if (object != null) {
		statList = (List<PlayerCreateStat>) object.get("statList");
		ptList = (List<PlayerCreateStat>) object.get("ptList");
		allRegisterNum = (Integer) object.get("allRegisterNum");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "success";
    }

    @Action(value = "ZHqueryPlayerRegisterStatByDaydata")
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
	object.put("handlerName", "QueryPlayerRegisterStatByDayHandler");
	object.put("methodName", "QueryPlayerRegisterStatByDayForExportData");
	object = CONNECTION.sendMsg(object);
	List<PlayerCreateStat> ptList = (List<PlayerCreateStat>) object
		.get("ptList");

	String[] titles = { "日期", "运营商", "运营商标识", "注册人数" };
	String[] elements = { "dateTime", "carrierOperator2",
		"carrierOperator", "countNum" };
	String fileName = beginDate + "-" + endDate + "注册数据统计";
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

    public Integer getAllRegisterNum() {
	return allRegisterNum;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<PlayerCreateStat> getPtList() {
	return ptList;
    }

    public List<PlayerCreateStat> getStatList() {
	return statList;
    }

    public void setAllRegisterNum(Integer allRegisterNum) {
	this.allRegisterNum = allRegisterNum;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setPtList(List<PlayerCreateStat> ptList) {
	this.ptList = ptList;
    }

    public void setStatList(List<PlayerCreateStat> statList) {
	this.statList = statList;
    }

}
