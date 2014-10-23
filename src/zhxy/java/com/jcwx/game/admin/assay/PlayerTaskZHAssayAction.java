package com.jcwx.game.admin.assay;

import java.math.BigDecimal;
import java.util.ArrayList;
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
@Namespace("/zhxy/assay")
@ResultPath("/")
public class PlayerTaskZHAssayAction extends BasalAction {
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

    private List<Map<String, Object>> taskList2;

    private Integer type;

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

    public List<Map<String, Object>> getTaskList2() {
	return taskList2;
    }

    public Integer getType() {
	return type;
    }

    /**
     * 每天(创建)账号停留的任务分析
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "playerTaskAssay", results = { @Result(name = "success", location = "/zhxy/assay/playerTaskAssay_zhxy.jsp") })
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
	type = 1;
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("type", type);
	object.put("handlerName", "AssayPlayerTaskHandler");
	object.put("methodName", "getPlayerTaskAnaly");
	try {
	    object = CONNECTION.sendMsg(object);
	    List<Map<String, Object>> tempList = (List<Map<String, Object>>) object
		    .get("list");
	    taskList2 = tempList;
	    taskList = new ArrayList<Map<String, Object>>();
	    for (int j = 0; j < tempList.size(); j++) {
		Map<String, Object> map = tempList.get(j);
		// BigDecimal sum1 =(BigDecimal) map.get("sum1");
		// BigDecimal sum2 =(BigDecimal) map.get("sum2");
		// BigDecimal sum3 =(BigDecimal) map.get("sum3");
		// BigDecimal sum4 =(BigDecimal) map.get("sum4");
		BigDecimal sum = (BigDecimal) map.get("sum");
		for (int i = 0; i < taskList.size(); i++) {
		    Map taskMap = taskList.get(i);
		    taskMap.put("sum",
			    ((BigDecimal) taskMap.get("sum")).add(sum));
		    // taskMap.put("sum1",((BigDecimal)taskMap.get("sum1")).add(sum));
		    taskMap.put("sum2",
			    ((BigDecimal) taskMap.get("sum2")).add(sum));
		    // taskMap.put("sum3",((BigDecimal)taskMap.get("sum3")).add(sum));
		    // taskMap.put("sum5",((BigDecimal)taskMap.get("sum3")).add(sum));
		    taskMap.put("sum3",
			    ((BigDecimal) taskMap.get("sum3")).add(sum));
		    taskList.set(i, taskMap);
		}
		taskList.add(map);
	    }

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

    public void setTaskList2(List<Map<String, Object>> taskList2) {
	this.taskList2 = taskList2;
    }

    public void setType(Integer type) {
	this.type = type;
    }

}
