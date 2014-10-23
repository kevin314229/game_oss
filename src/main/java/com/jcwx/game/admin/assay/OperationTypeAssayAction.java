package com.jcwx.game.admin.assay;

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

import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.PerformanceTimer;
import com.jcwx.game.common.constants.OperationLogConstant;


/**
 * 操作类型分析
 * 
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class OperationTypeAssayAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 开始时间 */
    private String beginDate;

    /** 结束时间 */
    private String endDate;

    /** json数据-时长分析 */
    private List<JSONObject> jsonList;

    private List<Map<String, Object>> list;

    /**
     * 所查询的表是否存在
     */
    private Boolean tableFlag;

    /**
     * 分析日志的操作类型数量分布
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "allLogOperationAssay", results = { @Result(name = "success", location = "/admin/assay/allLogOperationAssay.jsp") })
    public String allLogOperationAssay() throws Exception {

	PerformanceTimer timer = new PerformanceTimer();
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginDate = DateService.getDateFormatStr(beginTime,
		    "yyyy-MM-dd HH:mm:ss");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime,
		    "yyyy-MM-dd HH:mm:ss");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "AssayOperationLogHandler");
	object.put("methodName", "getAllLogOperationAssay");
	object = CONNECTION.sendMsg(object);
	setRemoteRunTime((Long) object.get("remoteRunTime"));

	tableFlag = (Boolean) object.get("tableFlag");
	list = (List) object.get("list");// 商城销售分析 mallId商品id,c数量

	if (list != null) {
	    // 封装名字 ,如果本地查不到就用原名
	    for (Map<String, Object> m : list) {
		String cnName = OperationLogConstant.maptype.get(m.get("p"));
		if (cnName != null) {
		    m.put("cnName", cnName);
		} else {
		    m.put("cnName", m.get("p"));
		}
	    }

	    // 图表
	    jsonList = new ArrayList<JSONObject>();
	    for (Map<String, Object> m : list) {
		JSONObject json = new JSONObject();
		json.put("t", m.get("cnName")); // 名称
		json.put("n", m.get("c"));// 数值
		jsonList.add(json);
	    }

	}

	setLocalRunTime(timer.get());
	return "success";
    }

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<JSONObject> getJsonList() {
	return jsonList;
    }

    public List<Map<String, Object>> getList() {
	return list;
    }

    public Boolean getTableFlag() {
	return tableFlag;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setJsonList(List<JSONObject> jsonList) {
	this.jsonList = jsonList;
    }

    public void setList(List<Map<String, Object>> list) {
	this.list = list;
    }

    public void setTableFlag(Boolean tableFlag) {
	this.tableFlag = tableFlag;
    }

}
