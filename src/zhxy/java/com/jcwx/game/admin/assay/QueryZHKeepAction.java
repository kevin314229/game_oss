/**
 * 
 */
package com.jcwx.game.admin.assay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.ZKeep;
import com.jcwx.game.domain.ZQueryActiveUser;
import com.jcwx.game.service.IQueryActiveUserService;


/**
 * @author Administrator 查看留存率
 */
@ParentPackage("base")
@Namespace("/zhxy/assay")
@ResultPath("/")
@Action(value = "queryKeep", results = { @Result(name = "success", location = "/zhxy/assay/queryKeep_zhxy.jsp") })
public class QueryZHKeepAction extends BasalAction {

    private static final long serialVersionUID = 1000L;

    private List<ZKeep> allKeepList;

    private String beginDate;

    private String endDate;

    private String jobJsonStr;

    private List<ZKeep> keepList;

    /** 服务器列表 */
    private List<OssServer> ossServersList;

    @Autowired
    private IQueryActiveUserService queryActiveUserService;
    private Integer selectArray;

    @Override
    @SuppressWarnings("unchecked")
    public String execute() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}
	// //开始时间和结束时间
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginTime = DateService.dateIncreaseByDay(beginTime, -10);// x天前的
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endTime = DateService.dateIncreaseByDay(endTime, -1);
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	// 最大时间间隔限制
	int betweenDay = DateService.DayBetween(beginTime, endTime);
	if (betweenDay > 30) {
	    this.addActionError("最大相隔天数为30天");
	    return "success";
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginDate", beginDate);
	object.put("endDate", endDate);
	object.put("handlerName", "QueryKeepHandler");
	String operator = "";
	Integer serverId = getBaseAdminContext().getServerId();
	// 如果是平台用户
	if (getBaseAdminContext().getOssUser().getIsOperator().equals("1")) {
	    ossServersList = getBaseAdminContext().getOssServers();
	    operator = getBaseAdminContext().getOssUser().getCarrierOperator();
	    object.put("methodName", "queryPt");
	    object.put("operator", operator);
	    // serverId=selectArray;
	}
	object = CONNECTION
		.sendMsg(object);
	keepList = (List<ZKeep>) object.get("zKeeps");
	allKeepList = (List<ZKeep>) object.get("allzkeeps");
	int gameId=getBaseAdminContext().getProject().getProjectId();
	String areaId=getBaseAdminContext().getCurrentOssServer().getId()+ "";
	// 同步数据
	List<ZQueryActiveUser> activeList =null;
	List<ZQueryActiveUser> ptActiveUsers =null;
	try {
	    activeList = queryActiveUserService
	    	.NewRegisterLoginAnalyse(beginTime, endTime, 0, 31,gameId,areaId );
	    ptActiveUsers=queryActiveUserService.
	    	queryRegisterGroupByPtId(beginTime, endTime, 0, 31, gameId, areaId);
	} catch (Exception e) {
	    activeList =null;
	    ptActiveUsers =null;
	}
	if (keepList != null&&activeList!=null) {
	    for (int i = 0; i < keepList.size(); i++) {
		if (i < activeList.size()) {
		    Date tempTime = DateService.getCurrentDayLastUtilDate();
		    ZKeep z1 = keepList.get(i);
		    for (ZQueryActiveUser temp : activeList) {
			if (temp.getActiveDate().getTime() == z1.getId()
				.getTime()) {
			    int sub = DateService.DayBetween(
				    temp.getActiveDate(), tempTime);
			    Integer num = 0;
			    if (sub == 1) {
				num = z1.getDay2();
			    } else if (sub == 2) {
				num = z1.getDay3();
			    } else if (sub == 3) {
				num = z1.getDay4();
			    } else if (sub == 4) {
				num = z1.getDay5();
			    } else if (sub == 5) {
				num = z1.getDay6();
			    } else if (sub == 6) {
				num = z1.getDay7();
			    } else if (sub == 7) {
				num = z1.getDay8();
			    }
			    z1.setDay1(temp.getDay0());
			    z1.setDay2(temp.getDay1());
			    z1.setDay3(temp.getDay2());
			    z1.setDay4(temp.getDay3());
			    z1.setDay5(temp.getDay4());
			    z1.setDay6(temp.getDay5());
			    z1.setDay7(temp.getDay6());
			    z1.setDay8(temp.getDay7());
			    if (sub == 1) {
				z1.setDay2(num);
			    } else if (sub == 2) {
				z1.setDay3(num);
			    } else if (sub == 3) {
				z1.setDay4(num);
			    } else if (sub == 4) {
				z1.setDay5(num);
			    } else if (sub == 5) {
				z1.setDay6(num);
			    } else if (sub == 6) {
				z1.setDay7(num);
			    } else if (sub == 7) {
				z1.setDay8(num);
			    }
			}
		    }
		    keepList.set(i, z1);
		}
	    }
	}
	if (allKeepList != null&&ptActiveUsers!=null) {
	    for (int i = 0; i < allKeepList.size(); i++) {
		if (i < ptActiveUsers.size()) {
		    Date tempTime = DateService.getCurrentDayLastUtilDate();
		    ZKeep z2 = allKeepList.get(i);
		    for (ZQueryActiveUser temp : ptActiveUsers) {
			if (temp.getActiveDate().getTime() == z2.getId()
				.getTime()&&temp.getPtId().equals(z2.getCarrierOperator())) {
			    int sub = DateService.DayBetween(
				    temp.getActiveDate(), tempTime);
			    Integer num = 0;
			    if (sub == 1) {
				num = z2.getDay2();
			    } else if (sub == 2) {
				num = z2.getDay3();
			    } else if (sub == 3) {
				num = z2.getDay4();
			    } else if (sub == 4) {
				num = z2.getDay5();
			    } else if (sub == 5) {
				num = z2.getDay6();
			    } else if (sub == 6) {
				num = z2.getDay7();
			    } else if (sub == 7) {
				num = z2.getDay8();
			    }
			    z2.setDay1(temp.getDay0());
			    z2.setDay2(temp.getDay1());
			    z2.setDay3(temp.getDay2());
			    z2.setDay4(temp.getDay3());
			    z2.setDay5(temp.getDay4());
			    z2.setDay6(temp.getDay5());
			    z2.setDay7(temp.getDay6());
			    z2.setDay8(temp.getDay7());
			    if (sub == 1) {
				z2.setDay2(num);
			    } else if (sub == 2) {
				z2.setDay3(num);
			    } else if (sub == 3) {
				z2.setDay4(num);
			    } else if (sub == 4) {
				z2.setDay5(num);
			    } else if (sub == 5) {
				z2.setDay6(num);
			    } else if (sub == 6) {
				z2.setDay7(num);
			    } else if (sub == 7) {
				z2.setDay8(num);
			    }
			}
		    }
		    allKeepList.set(i, z2);
		}
	    }
	}
	

	// 图表需要的json数据
	JSONArray array = new JSONArray();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	for (ZKeep z : keepList) {
	    String date = formatter.format(z.getId());
	    JSONObject dateObject = new JSONObject();
	    dateObject.put("name", date);
	    // 二维数组
	    // 2
	    JSONArray dataArray2 = new JSONArray();
	    dateObject.put("data", dataArray2);
	    Double total = (double) z.getDay1();
	    dataArray2 = new JSONArray();
	    dataArray2.add(1);
	    dataArray2.add(100);// 第一天设为100%
	    dateObject.getJSONArray("data").add(dataArray2);

	    dataArray2 = new JSONArray();
	    dataArray2.add(2);
	    dataArray2.add(total <= 0 ? 0 : (int) (z.getDay2() * 100 / total));
	    dateObject.getJSONArray("data").add(dataArray2);

	    dataArray2 = new JSONArray();
	    dataArray2.add(3);
	    dataArray2.add(total <= 0 ? 0 : (int) (z.getDay3() * 100 / total));
	    dateObject.getJSONArray("data").add(dataArray2);

	    dataArray2 = new JSONArray();
	    dataArray2.add(4);
	    dataArray2.add(total <= 0 ? 0 : (int) (z.getDay4() * 100 / total));
	    dateObject.getJSONArray("data").add(dataArray2);

	    dataArray2 = new JSONArray();
	    dataArray2.add(5);
	    dataArray2.add(total <= 0 ? 0 : (int) (z.getDay5() * 100 / total));
	    dateObject.getJSONArray("data").add(dataArray2);

	    dataArray2 = new JSONArray();
	    dataArray2.add(6);
	    dataArray2.add(total <= 0 ? 0 : (int) (z.getDay6() * 100 / total));
	    dateObject.getJSONArray("data").add(dataArray2);

	    dataArray2 = new JSONArray();
	    dataArray2.add(7);
	    dataArray2.add(total <= 0 ? 0 : (int) (z.getDay7() * 100 / total));
	    dateObject.getJSONArray("data").add(dataArray2);

	    dataArray2 = new JSONArray();
	    dataArray2.add(8);
	    dataArray2.add(total <= 0 ? 0 : (int) (z.getDay8() * 100 / total));
	    dateObject.getJSONArray("data").add(dataArray2);

	    dataArray2 = new JSONArray();
	    dataArray2.add(14);
	    dataArray2.add(total <= 0 ? 0 : (int) (z.getDay14() * 100 / total));
	    dateObject.getJSONArray("data").add(dataArray2);

	    dataArray2 = new JSONArray();
	    dataArray2.add(30);
	    dataArray2.add(total <= 0 ? 0 : (int) (z.getDay30() * 100 / total));
	    dateObject.getJSONArray("data").add(dataArray2);

	    array.add(dateObject);

	}

	jobJsonStr = array.toJSONString();
	// System.out.println(jobJsonStr);

	return SUCCESS;

    }

    public List<ZKeep> getAllKeepList() {
	return allKeepList;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public String getJobJsonStr() {
	return jobJsonStr;
    }

    public List<ZKeep> getKeepList() {
	return keepList;
    }

    public List<OssServer> getOssServersList() {
	return ossServersList;
    }

    public Integer getSelectArray() {
	return selectArray;
    }

    public void setAllKeepList(List<ZKeep> allKeepList) {
	this.allKeepList = allKeepList;
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

    public void setKeepList(List<ZKeep> keepList) {
	this.keepList = keepList;
    }

    public void setOssServersList(List<OssServer> ossServersList) {
	this.ossServersList = ossServersList;
    }

    public void setSelectArray(Integer selectArray) {
	this.selectArray = selectArray;
    }

}