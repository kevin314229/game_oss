package com.jcwx.game.admin.assay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.http.domain.PlayerJobs;


/**
 * 新玩家职位分析
 * 
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/zhxy/assay")
@ResultPath("/")
public class ZHPlayerJobAssayAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 开始时间 */
    private String beginDate;

    /** 结束时间 */
    private String endDate;

    private String jobJsonStr;

    private List<PlayerJobs> playerJobsList;

    private List<Map<String, Object>> totalJobList;

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public String getJobJsonStr() {
	return jobJsonStr;
    }

    public List<PlayerJobs> getPlayerJobsList() {
	return playerJobsList;
    }

    public List<Map<String, Object>> getTotalJobList() {
	return totalJobList;
    }

    /**
     * 新玩家数据分析
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "ZHplayerJobAssay", results = { @Result(name = "success", location = "/zhxy/assay/ZHplayerJobAssay.jsp") })
    public String newPlayerAssay() throws Exception {

	// 开始时间和结束时间
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginTime = DateService.dateIncreaseByDay(beginTime, -3);// x天前的
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	// 最大时间间隔限制
	int betweenDay = DateService.DayBetween(beginTime, endTime);
	if (betweenDay > 10) {
	    this.addActionError("最大相隔天数为10天");
	    return "success";
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "AssayPlayerJobHandler");
	object = CONNECTION.sendMsg(object);

	totalJobList = (List<Map<String, Object>>) object.get("totalList");
	List<Map<String, Object>> jobList = (List<Map<String, Object>>) object
		.get("list");

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	// 表格的的
	playerJobsList = new ArrayList<PlayerJobs>();
	Map<String, PlayerJobs> tempMap = new LinkedHashMap();
	for (Map<String, Object> m : jobList) {
	    String date = formatter.format(m.get("d"));
	    Integer jobs = (Integer) m.get("jobs");
	    Long jobNum = (Long) m.get("jobNum");
	    PlayerJobs pj = null;
	    pj = tempMap.get(date);
	    if (pj == null) {
		pj = new PlayerJobs();
		pj.setDate(date);
		tempMap.put(date, pj);
	    }

	    if (jobs == 1) {
		pj.setJob1Num(jobNum.intValue());
	    } else if (jobs == 2) {
		pj.setJob2Num(jobNum.intValue());
	    } else if (jobs == 3) {
		pj.setJob3Num(jobNum.intValue());
	    }
	}
	playerJobsList = new ArrayList<PlayerJobs>(tempMap.values());

	// 图表需要的json数据
	JSONObject jsonObject = new JSONObject();
	JSONObject dateObject = null;
	List<String> dateList = new ArrayList<String>();// 日期，保留顺序
	for (Map<String, Object> m : jobList) {
	    String date = formatter.format(m.get("d"));

	    Integer jobs = (Integer) m.get("jobs");
	    String jobstr = "";
	    if (jobs == 1) {
		jobstr = "灵猴";
	    } else if (jobs == 2) {
		jobstr = "神君";
	    } else if (jobs == 3) {
		jobstr = "玄女";
	    }
	    Long jobNum = (Long) m.get("jobNum");
	    dateObject = jsonObject.getJSONObject(date);
	    if (dateObject == null) {
		dateList.add(date);
		dateObject = new JSONObject();
		dateObject.put("name", date);
		// 改成二维数组
		// 2
		JSONArray dataArray2 = new JSONArray();
		dataArray2.add(jobstr);
		dataArray2.add(jobNum);
		// 1
		JSONArray dataArray = new JSONArray();
		dataArray.add(dataArray2);
		dateObject.put("data", dataArray);
		jsonObject.put(date, dateObject);
	    } else {
		JSONArray dataArray2 = new JSONArray();
		dataArray2.add(jobstr);
		dataArray2.add(jobNum);
		dateObject.getJSONArray("data").add(dataArray2);
	    }
	}
	JSONArray array = new JSONArray();

	for (String key : dateList) {
	    array.add(jsonObject.get(key));
	}
	jobJsonStr = array.toJSONString();
	// ///////////

	return "success";
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setJobJsonStr(String jobJsonStr) {
	this.jobJsonStr = jobJsonStr;
    }

    public void setPlayerJobsList(List<PlayerJobs> playerJobsList) {
	this.playerJobsList = playerJobsList;
    }

    public void setTotalJobList(List<Map<String, Object>> totalJobList) {
	this.totalJobList = totalJobList;
    }

}
