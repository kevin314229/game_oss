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

import com.jcwx.game.admin.BaseInfoAction;
import com.jcwx.game.admin.vo.PageMessage;
import com.jcwx.game.common.DateService;


/**
 * 七天 未登录用户分析
 * 
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class PlayerLoginAssayAction extends BaseInfoAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 开始时间 */
    private String beginDate;
    /** 结束时间 */
    private String endDate;
    // 玩家等级
    private List<Map<String, Object>> gradeList;
    // 在线时长
    private List<Map<String, Object>> onlineTimelist;
    // 任务停留
    private List<Map<String, Object>> taskList;

    private PageMessage pageMessage = PageMessage.getOkMessage();

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<Map<String, Object>> getGradeList() {
	return gradeList;
    }

    public List<Map<String, Object>> getOnlineTimelist() {
	return onlineTimelist;
    }

    public List<Map<String, Object>> getTaskList() {
	return taskList;
    }

    @Action(value = "playerLogin_index", results = { @Result(name = "success", location = "../../admin/assay/playerLoginAssay.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getFirstDayOfLastMonth();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getLastDayOfLastMonth();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "PlayerLoginAssayHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		// 在线时长
		onlineTimelist = (List<Map<String, Object>>) object
			.get("onlineTimelist");
		// 任务停留
		taskList = (List<Map<String, Object>>) object.get("tasklist");
		// 等级统计
		gradeList = (List<Map<String, Object>>) object.get("gradeList");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setGradeList(List<Map<String, Object>> gradeList) {
	this.gradeList = gradeList;
    }

    public void setOnlineTimelist(List<Map<String, Object>> onlineTimelist) {
	this.onlineTimelist = onlineTimelist;
    }

    public void setTaskList(List<Map<String, Object>> taskList) {
	this.taskList = taskList;
    }

    public PageMessage getPageMessage() {
        return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
    }

}
