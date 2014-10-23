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
 * 在线时长分布统计
 * 
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class OnlineTimeAssayAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 开始时间 */
    private String beginDate;

    private Integer cutNum;

    /** 结束时间 */
    private String endDate;

    /**
     * 端标识(1,页游 2手游戏 ,-1全部)
     * */
    private Integer loginFrom;

    private List<Map<String, Object>> onlineList;

    public String getBeginDate() {
	return beginDate;
    }

    public Integer getCutNum() {
	return cutNum;
    }

    public String getEndDate() {
	return endDate;
    }

    public Integer getLoginFrom() {
	return loginFrom;
    }

    public List<Map<String, Object>> getOnlineList() {
	return onlineList;
    }

    /**
     * 新玩家数据分析
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "onlineTimeAssay", results = { @Result(name = "success", location = "/admin/assay/onlineTimeAssay.jsp") })
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

	if (cutNum == null)
	    cutNum = 7200;
	if (loginFrom == null)
	    loginFrom = -1;

	if (cutNum < 5)
	    cutNum = 7200;

	int betweenDay = DateService.DayBetween(beginTime, endTime);
	if (betweenDay > 30) {
	    this.addActionError("最大相隔天数为30天");
	    return "success";
	}

	if (betweenDay >= 6) {
	    if (cutNum < 3600) {
		this.addActionError("当时间段>=6天时，时长不能少于60分钟");
		return "success";
	    }
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("cutNum", cutNum);
	object.put("loginFrom", loginFrom);
	object.put("handlerName", "AssayOnlineTimeHandler");
	object = CONNECTION.sendMsg(object);

	onlineList = (List<Map<String, Object>>) object.get("list");

	return "success";
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setCutNum(Integer cutNum) {
	this.cutNum = cutNum;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setLoginFrom(Integer loginFrom) {
	this.loginFrom = loginFrom;
    }

    public void setOnlineList(List<Map<String, Object>> onlineList) {
	this.onlineList = onlineList;
    }

}
