package com.jcwx.game.admin.online;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.http.domain.AverageOnline;


/**
 * 查询平均在线时长
 * */
@ParentPackage("base")
@Namespace("/admin/online")
@ResultPath("/")
@Action(value = "queryOnlineCountTime", results = { @Result(name = "success", location = "../../admin/online/queryOnlineCountTime.jsp") })
public class QueryOnlineCountTimeAction extends BasalAction {

    /**
          * 
          */
    private static final long serialVersionUID = 1L;

    List<AverageOnline> averageList;

    private String beginDate;

    private String endDate;

    @Override
    @SuppressWarnings("unchecked")
    public String execute() throws Exception {

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
	object.put("methodName", "getOnlineCountTime");
	object.put("handlerName", "LoginLogHandler");
	object = CONNECTION.sendMsg(object);

	averageList = (List<AverageOnline>) object.get("averageList");

	return "success";
    }

    public List<AverageOnline> getAverageList() {
	return averageList;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setAverageList(List<AverageOnline> averageList) {
	this.averageList = averageList;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

}
