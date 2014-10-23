/**
 * 
 */
package com.jcwx.game.admin.assay;

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
import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.ZKeep;


/**
 * 新增留存付费
 * 
 * @author Administrator
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
@Action(value = "queryKeepPay", results = { @Result(name = "success", location = "/admin/assay/queryKeepPay.jsp") })
public class QueryKeepPayAction extends BasalAction {

    private static final long serialVersionUID = 1000L;

    private List<ZKeep> allKeepList;

    private String beginDate;

    private String endDate;
    private List<ZKeep> keepList;

    /** 服务器列表 */
    private List<OssServer> ossServersList;

    private Integer selectArray;

    @Override
    @SuppressWarnings("unchecked")
    public String execute() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    addActionMessage(getActionMsg());
	}
	// //开始时间和结束时间
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginTime = DateService.dateIncreaseByDay(beginTime, -10);// x天前的
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	// 最大时间间隔限制
	int betweenDay = DateService.DayBetween(beginTime, endTime);
	if (betweenDay > 30) {
	    this.addActionError("最大相隔天数为30天");
	    return "success";
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginDate", beginDate);
	object.put("endDate", endDate);
	object.put("handlerName", "QueryKeepHandler");
	String operator = "";
	BaseAdminContext baseAdminContext = getBaseAdminContext();
	Integer serverId = baseAdminContext.getServerId();
	// 如果是平台用户
	if (baseAdminContext.getOssUser().getIsOperator().equals("1")) {
	    ossServersList = baseAdminContext.getOssServers();
	    operator = baseAdminContext.getOssUser().getCarrierOperator();
	    object.put("methodName", "queryPt");
	    object.put("operator", operator);
	    serverId = selectArray;
	}
	if (serverId == null || serverId == 0) {
	    return SUCCESS;
	}
	object = CONNECTION.sendMsg(object);
	keepList = (List<ZKeep>) object.get("zKeeps");
	allKeepList = (List<ZKeep>) object.get("allzkeeps");

	return "success";

    }

    public List<ZKeep> getAllKeepList() {
	return allKeepList;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<ZKeep> getKeepList() {
	return keepList;
    }

    public List<OssServer> getOssServersList() {
	return ossServersList;
    }

    public Integer getSelectArray() {
	return selectArray;
    }

    public void setAllKeepList(List<ZKeep> allKeepList) {
	this.allKeepList = allKeepList;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setKeepList(List<ZKeep> keepList) {
	this.keepList = keepList;
    }

    public void setOssServersList(List<OssServer> ossServersList) {
	this.ossServersList = ossServersList;
    }

    public void setSelectArray(Integer selectArray) {
	this.selectArray = selectArray;
    }

}