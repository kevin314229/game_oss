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
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.PerformanceTimer;
import com.jcwx.game.domain.ZConsumeOutput;


/**
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class OutIncomeAssayAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 开始时间 */
    private String beginDate;

    private List<ZConsumeOutput> consumeOutPutList;

    /**
     * 消耗金币
     * */
    private List<Map<String, Object>> consumSilverList;

    /** 结束时间 */
    private String endDate;

    /**
     * 获得金币
     * */
    private List<Map<String, Object>> gainSilverList;

    /**
     * 拥有金币
     * */
    private List<Map<String, Object>> ownSilverList;

    /**
     * 所查询的表是否存在
     */
    private Boolean tableFlag;

    @Action(value = "outIncomeAssay_equip", results = { @Result(name = "success", location = "../../admin/assay/outIncomeAssayEquip.jsp") })
    public String equip() throws Exception {

	PerformanceTimer timer = new PerformanceTimer();
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
	object.put("handlerName", "OutIncomeAssayHandler");
	object = CONNECTION.sendMsg(object);
	super.handleKryoMap(object);
	if (object != null && object.get("consumeOutPutList") != null) {
	    consumeOutPutList = (List) object.get("consumeOutPutList");
	}
	// super.remoteRunTime = (Long)object.get("remoteRunTime");
	//
	// tableFlag = (Boolean)object.get("tableFlag");

	setLocalRunTime( timer.get());
	return "success";
    }

    public String getBeginDate() {
	return beginDate;
    }

    public List<ZConsumeOutput> getConsumeOutPutList() {
	return consumeOutPutList;
    }

    public List<Map<String, Object>> getConsumSilverList() {
	return consumSilverList;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<Map<String, Object>> getGainSilverList() {
	return gainSilverList;
    }

    public List<Map<String, Object>> getOwnSilverList() {
	return ownSilverList;
    }

    public Boolean getTableFlag() {
	return tableFlag;
    }

    @SuppressWarnings("unchecked")
    @Action(value = "outIncomeAssay_index", results = { @Result(name = "success", location = "/admin/assay/outIncomeAssay.jsp") })
    public String outIncomeAssay() throws Exception {

	PerformanceTimer timer = new PerformanceTimer();
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
	object.put("handlerName", "OutIncomeAssayHandler");
	object = CONNECTION.sendMsg(object);
	super.handleKryoMap(object);
	try {
	    if (object != null && object.get("consumeOutPutList") != null) {
		consumeOutPutList = (List<ZConsumeOutput>) object
			.get("consumeOutPutList");
		gainSilverList = (List<Map<String, Object>>) object
			.get("gainSilverList");
		consumSilverList = (List<Map<String, Object>>) object
			.get("consumSilverList");
		ownSilverList = (List<Map<String, Object>>) object
			.get("ownSilverList");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// super.remoteRunTime = (Long)object.get("remoteRunTime");
	//
	// tableFlag = (Boolean)object.get("tableFlag");

	setLocalRunTime(timer.get());
	return "success";
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setConsumeOutPutList(List<ZConsumeOutput> consumeOutPutList) {
	this.consumeOutPutList = consumeOutPutList;
    }

    public void setConsumSilverList(List<Map<String, Object>> consumSilverList) {
	this.consumSilverList = consumSilverList;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setGainSilverList(List<Map<String, Object>> gainSilverList) {
	this.gainSilverList = gainSilverList;
    }

    public void setOwnSilverList(List<Map<String, Object>> ownSilverList) {
	this.ownSilverList = ownSilverList;
    }

    public void setTableFlag(Boolean tableFlag) {
	this.tableFlag = tableFlag;
    }

}
