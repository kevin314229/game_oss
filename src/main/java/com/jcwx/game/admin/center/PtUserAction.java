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
import com.jcwx.game.service.center.IPtUserService;

/**
 * 商城分析
 * 
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/center")
@ResultPath("/")
public class PtUserAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 开始时间 */
    private Date beginTime;

    /** 结束时间 */
    private Date endTime;

    private JSONArray registerJsonArray;

    @Autowired
    private IPtUserService ptUserService;

    /**
     * 平台注册人数
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "ptUser_register", results = { @Result(name = "success", location = "/admin/center/register.jsp") })
    public String register() throws Exception {

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
	List list = ptUserService.getPtregister(beginTime, endTime);
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
