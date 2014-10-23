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
 * 新vip玩家数据分析
 * 
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class NewVipPlayerAssayAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 开始时间 */
    private String beginDate;

    private Integer count;

    /** 结束时间 */
    private String endDate;

    private List<Map<String, Object>> everyDayNewCountList;

    private List<Map<String, Object>> hourList;

    private List<Map<String, Object>> levelList;

    public String getBeginDate() {
	return beginDate;
    }

    public Integer getCount() {
	return count;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<Map<String, Object>> getEveryDayNewCountList() {
	return everyDayNewCountList;
    }

    public List<Map<String, Object>> getHourList() {
	return hourList;
    }

    public List<Map<String, Object>> getLevelList() {
	return levelList;
    }

    /**
     * 新vip玩家数据分析
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "newVipPlayerAssay", results = { @Result(name = "success", location = "/admin/assay/newVipPlayerAssay.jsp") })
    public String newVipPlayerAssay() throws Exception {

	// 要用 23:59:59 数据库为datatime
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentDayLastUtilDate();
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
	object.put("handlerName", "AssayPlayerVipHandler");
	object.put("methodName", "newVipPlayerAssay");
	object = CONNECTION.sendMsg(object);

	count = (Integer) object.get("count");// 这段时间总增加人数
	hourList = (List<Map<String, Object>>) object.get("hourList");// 总体时间段分布
	levelList = (List<Map<String, Object>>) object.get("levelList");// 总体等级分布
	everyDayNewCountList = (List<Map<String, Object>>) object
		.get("everyDayNewCountList");// 每日新增人数分布

	// TODO,详细内容请查看游戏查询中定义

	return "success";
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setCount(Integer count) {
	this.count = count;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setEveryDayNewCountList(
	    List<Map<String, Object>> everyDayNewCountList) {
	this.everyDayNewCountList = everyDayNewCountList;
    }

    public void setHourList(List<Map<String, Object>> hourList) {
	this.hourList = hourList;
    }

    public void setLevelList(List<Map<String, Object>> levelList) {
	this.levelList = levelList;
    }

}
