package com.jcwx.game.admin.center;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.service.center.IPtPayHistoryService;
import com.jcwx.game.service.center.impl.PtPayHistoryService;

@ParentPackage("base")
@Namespace("/admin/center")
@ResultPath("/")
public class PtPayHistoryAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 开始时间 */
    private Date beginTime;

    /** 结束时间 */
    private Date endTime;

    private JSONArray registerJsonArray;

    private IPtPayHistoryService ptPayHistoryService = SpringService.getBean(PtPayHistoryService.class);

    /**
     * 每月充值统计
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "ptPayHistory_index", results = { @Result(name = "success", location = "/admin/center/ptPayHistory.jsp") })
    public String PtPayHistorySta() throws Exception {

	if (beginTime == null) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	} else {
	    beginTime = DateService.getDateFirstTime(beginTime);
	}
	if (endTime == null) {
	    endTime = DateService.getDateLastTime(new java.util.Date());
	} else {
	    endTime = DateService.getDateLastTime(endTime);
	}
	List list = ptPayHistoryService.getPtPayHistorySta(beginTime, endTime);
	registerJsonArray = (JSONArray) JSONArray.toJSON(list);
	return SUCCESS;
    }

    /**
     * 当月注册且充值用户统计
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "ptPayHistory_user", results = { @Result(name = "success", location = "/admin/center/ptPayHistoryUser.jsp") })
    public String PtPayHistoryUserSta() throws Exception {
	if (beginTime == null) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	} else {
	    beginTime = DateService.getDateFirstTime(beginTime);
	}
	if (endTime == null) {
	    endTime = DateService.getDateLastTime(new java.util.Date());
	} else {
	    endTime = DateService.getDateLastTime(endTime);
	}
	List list = ptPayHistoryService.getPtPayHistoryUserSta(beginTime,
		endTime);
	registerJsonArray = (JSONArray) JSONArray.toJSON(list);
	return SUCCESS;
    }
    
    /**
     * 当月注册且充值用户统计
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "ptPayHistory_new", results = { @Result(name = "success", location = "/admin/center/ptPayHistoryNew.jsp") })
    public String PtPayHistoryNewSta() throws Exception {
	if (beginTime == null) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	} else {
	    beginTime = DateService.getDateFirstTime(beginTime);
	}
	if (endTime == null) {
	    endTime = DateService.getDateLastTime(new java.util.Date());
	} else {
	    endTime = DateService.getDateLastTime(endTime);
	}
	List list = ptPayHistoryService.getPtPayHistoryUserSta(beginTime,
		endTime);
	registerJsonArray = (JSONArray) JSONArray.toJSON(list);
	return SUCCESS;
    }

    public JSONArray getRegisterJsonArray() {
	return registerJsonArray;
    }

    public void setRegisterJsonArray(JSONArray registerJsonArray) {
	this.registerJsonArray = registerJsonArray;
    }

    public Date getBeginTime() {
	return beginTime;
    }

    public void setBeginTime(Date beginTime) {
	this.beginTime = beginTime;
    }

    public Date getEndTime() {
	return endTime;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

}
