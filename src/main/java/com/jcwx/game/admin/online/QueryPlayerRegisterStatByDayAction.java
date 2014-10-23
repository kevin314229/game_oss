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
import com.jcwx.game.domain.PlayerCreateStat;


/**
 * 在线与注册：注册数据统计
 * */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin/online")
@ResultPath("/")
@Action(value = "queryPlayerRegisterStatByDay", results = { @Result(name = "success", location = "../../admin/online/queryPlayerRegisterStatByDay.jsp") })
public class QueryPlayerRegisterStatByDayAction extends BasalAction {

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

    public Integer getAllRegisterNum() {
	return allRegisterNum;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
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

    public void setStatList(List<PlayerCreateStat> statList) {
	this.statList = statList;
    }

    public List<PlayerCreateStat> getPtList() {
        return ptList;
    }

    public void setPtList(List<PlayerCreateStat> ptList) {
        this.ptList = ptList;
    }

    
}
