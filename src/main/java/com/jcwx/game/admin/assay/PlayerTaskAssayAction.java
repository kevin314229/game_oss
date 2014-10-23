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


/**
 * 新玩家任务分析
 * 
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class PlayerTaskAssayAction extends BasalAction {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 开始时间 */
    private String beginDate;

    /** 结束时间 */
    private String endDate;

    private Map<Integer, String> nameMap;

    private List<Map<String, Object>> taskList;

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public Map<Integer, String> getNameMap() {
	return nameMap;
    }

    public List<Map<String, Object>> getTaskList() {
	return taskList;
    }

    /**
     * 每天(创建)账号停留的任务分析
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "playerTaskAssay", results = { @Result(name = "success", location = "/admin/assay/playerTaskAssay.jsp") })
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
	object.put("handlerName", "AssayPlayerTaskHandler");
	try {
	    object = CONNECTION.sendMsg(object);
	    taskList = (List<Map<String, Object>>) object.get("list");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "success";
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setNameMap(Map<Integer, String> nameMap) {
	this.nameMap = nameMap;
    }

    public void setTaskList(List<Map<String, Object>> taskList) {
	this.taskList = taskList;
    }

}
