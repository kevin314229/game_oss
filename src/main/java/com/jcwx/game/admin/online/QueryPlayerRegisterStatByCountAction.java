package com.jcwx.game.admin.online;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.constants.OssServerConstant;
import com.jcwx.game.common.constants.PtServerConstant;
import com.jcwx.game.domain.IpReport;
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.PlayerCreateStat;
import com.jcwx.game.service.oss.IALoginLogService;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.service.oss.IOssServerService;
import com.jcwx.game.service.oss.IPlayerService;
import com.jcwx.game.util.ServerListToMap;

/**
 * 在线与注册：注册数据统计
 * */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin/online")
@ResultPath("/")
public class QueryPlayerRegisterStatByCountAction extends BasalAction {

    public static void main(String[] args) {
	List list = new ArrayList();
	list.add(12);
	Map<String, List> map = new HashMap<String, List>();
	map.put("23", list);
	list.add(13);
	List list2 = map.get("23");
	list2.add(344);
	System.out.println(map.get("23").size());
    }

    /** 总注册人数 */
    private Integer allRegisterNum;

    @Autowired
    private IALoginLogService aLoginLogService;

    // 大区 ID列表多选
    private String[] areas;

    /** 开始时间 */
    private String beginDate;

    private String categories;

    private int days;

    /** 结束时间 */
    private String endDate;

    private String jsonString;

    private String jsonStringPt;

    private Integer operationId;

    /** 查询平台标识 */
    private List<OssOperation> ossOperationList;

    @Autowired
    private IOssOperationService ossOperationService;

    /** 大区Id */
    private String ossServerId;

    /** 服务器列表 */
    private List<OssServer> ossServerList;

    @Autowired
    private IOssServerService ossServerService;

    @Autowired
    private IPlayerService playerService;

    /** 平台标识 */
    private String ptCode;

    private Integer registerCount;

    private Map<String, String> serverMap;

    List<PlayerCreateStat> statList;

    public Integer getAllRegisterNum() {
	return allRegisterNum;
    }

    public IALoginLogService getaLoginLogService() {
	return aLoginLogService;
    }

    public String[] getAreas() {
	return areas;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public String getCategories() {
	return categories;
    }

    public int getDays() {
	return days;
    }

    public String getEndDate() {
	return endDate;
    }

    public String getJsonString() {
	return jsonString;
    }

    public String getJsonStringPt() {
	return jsonStringPt;
    }

    public Integer getOperationId() {
	return operationId;
    }

    public List<OssOperation> getOssOperationList() {
	return ossOperationList;
    }

    public IOssOperationService getOssOperationService() {
	return ossOperationService;
    }

    public String getOssServerId() {
	return ossServerId;
    }

    public List<OssServer> getOssServerList() {
	return ossServerList;
    }

    public IOssServerService getOssServerService() {
	return ossServerService;
    }

    public IPlayerService getPlayerService() {
	return playerService;
    }

    public String getPtCode() {
	return ptCode;
    }

    public Integer getRegisterCount() {
	return registerCount;
    }

    public Map<String, String> getServerMap() {
	return serverMap;
    }

    public List<PlayerCreateStat> getStatList() {
	return statList;
    }

    @Action(value = "queryOssServerList")
    public String queryOssServerList() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	ossServerList = new ArrayList<OssServer>();
	List<OssServer> list = getBaseAdminContext().getOssServers();
	if (operationId != null && !"".equals(operationId)) {
	    for (OssServer ossServer : list) {
		if (ossServer.getServerCode().equals(operationId)) {
		    ossServerList.add(ossServer);
		}
	    }
	} else {
	    ossServerList = list;
	}
	out.print(JSON.toJSON(ossServerList).toString());
	out.close();
	return null;
    }

    @SuppressWarnings("unchecked")
    @Action(value = "queryPlayerRegisterStatByCount", results = { @Result(name = "success", location = "../../admin/online/queryPlayerRegisterStatByCount.jsp") })
    public String queryPlayerRegisterStatByCount() throws Exception {
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

	if (operationId == null || operationId == -1) {
	    operationId = null;
	}

	if (ossServerId == null || ossServerId.equals("-1")) {
	    ossServerId = null;
	}

	if (ptCode == null || ptCode.equals("-1")) {
	    ptCode = null;
	}

	// 给服务器赋值
	ossServerList = new ArrayList<OssServer>();
	List<OssServer> listServer = getBaseAdminContext().getOssServers();
	if (operationId != null && !"".equals(operationId)) {
	    for (OssServer ossServer : listServer) {
		if (ossServer.getServerId().equals(operationId)) {
		    ossServerList.add(ossServer);
		}
	    }
	} else {
	    ossServerList = listServer;
	}

	serverMap = ServerListToMap.convert(ossServerList);

	String areaId = ServerListToMap
		.arrayToString(listServer, areas, ptCode);
	if (areas != null && areas.length == 1) {
	    areas = areas[0].split(",");
	}
	// 获取所有平台标识
	this.ossOperationList = ossOperationService.getOssOperationList();
	// 总记录数
	allRegisterNum = playerService.getPlayerNum(getBaseAdminContext().getProject().getProjectId());
	statList = playerService.getPlayerCreateStatListByDay(beginTime,
		endTime, getBaseAdminContext().getProject().getProjectId(),
		areaId, ptCode);
	Integer num = 0;
	for (int i = 0; i < statList.size(); i++) {
	    if (statList.get(i).getCountNum() != null
		    || !statList.get(i).getCountNum().equals("")) {
		num += statList.get(i).getCountNum();
	    }
	}

	registerCount = num;

	String[] dates = ServerListToMap.dateToStrArr(beginDate, endDate);
	categories = JSON.toJSONString(dates);
	days = dates.length;
	if (areas != null && areas.length > 0) {

	    List<Map<Object, Object>> result = new ArrayList<Map<Object, Object>>();
	    for (int i = 0; i < areas.length; i++) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<Map<String, Object>> serverList = playerService
			.getPlayerCreateStatListByServerId(beginTime, endTime,
				getBaseAdminContext().getProject()
					.getProjectId(), areas[i], ptCode);
		// categories[i]=
		// OssServerConstant.getptTypeMap().get(areas[i]);
		long[] data = new long[dates.length];
		String areaName = OssServerConstant.getptTypeMap()
			.get(areas[i]);
		if (areaName == null) {
		    map.put("name", "" + areas[i]);
		} else {
		    map.put("name", areaName);
		}
		if (areas.length > 10) {
		    // map.put("color", generateColor());
		}
		for (Map temp : serverList) {
		    for (int j = 0; j < dates.length; j++) {

			if (temp.get("everyDay").equals(dates[j])) {
			    data[j] = (Long) temp.get("countNum");
			}
		    }
		}
		map.put("data", data);
		result.add(map);
	    }
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("data", result);
	    jsonString = JSON.toJSONString(result);
	}

	List<IpReport> ipReport = aLoginLogService.ipReport(
		getBaseAdminContext().getProject().getProjectId(), ossServerId,
		ptCode, beginTime, endTime);
	String[] code = new String[ipReport.size()];
	for (int i = 0; i < ipReport.size(); i++) {
	    code[i] = ipReport.get(i).getPtCode();
	}
	if (areas != null && areas.length > 0) {
	    // 获取所有平台数据
	    List<Map<String, Object>> ptList = playerService
		    .getPlayerCreateStatListByPtCode(beginTime, endTime,
			    getBaseAdminContext().getProject().getProjectId(),
			    ossServerId, ptCode);
	    // 按照平台code将其对应的数据保存在Map里面
	    Map<String, List<Map>> resultMap = new HashMap<String, List<Map>>();
	    // 根据平台code保存数据
	    for (int i = 0; i < ptList.size(); i++) {
		String ptCode = (String) ptList.get(i).get("ptCode");
		List tempList = resultMap.get(ptCode);
		// 如果平台code存在于map则直接加入list,不在则进行新增list并添加到Map
		if (tempList == null) {
		    tempList = new ArrayList();
		    tempList.add(ptList.get(i));
		    resultMap.put(ptCode, tempList);
		} else {
		    tempList.add(ptList.get(i));
		}
	    }

	    List<Map<Object, Object>> result2 = new ArrayList<Map<Object, Object>>();
	    // 遍历map
	    for (String ptCode : resultMap.keySet()) {
		List<Map> list = resultMap.get(ptCode);
		long[] data = new long[dates.length];
		// 将平台code转换为name
		Map<Object, Object> map = new HashMap<Object, Object>();
		String ptName = PtServerConstant.ptTypeMap.get(ptCode);
		if (ptName == null) {
		    map.put("name", "" + ptCode);
		} else {
		    map.put("name", ptName);
		}
		for (Map tempMap : list) {
		    for (int j = 0; j < dates.length; j++) {
			if (tempMap.get("everyDay").equals(dates[j])) {
			    data[j] = (Long) tempMap.get("countNum");
			}
		    }
		}
		map.put("data", data);
		result2.add(map);
	    }
	    // JSONObject jsonObject = new JSONObject();
	    // jsonObject.put("data", result2);
	    jsonStringPt = JSON.toJSONString(result2);
	}
	// for(int i=0;i<resultMap.size();i++){
	// List<Map<Object,Object>> result = new
	// ArrayList<Map<Object,Object>>();
	// // List<Map> list =resultMap.
	// }
	// if(code!=null&&code.length>0){
	// List<Map<Object,Object>> result = new
	// ArrayList<Map<Object,Object>>();
	// for(int i=0;i<code.length;i++){
	// Map<Object,Object> map = new HashMap<Object,Object>();
	//
	// long[] data = new long[dates.length];
	// String areaName = OssServerConstant.getptTypeMap().get(code[i]);
	// if(areaName==null){
	// map.put("name", ""+code[i]);
	// }else{
	// map.put("name", areaName);
	// }
	// if(areas.length>10){
	// // map.put("color", generateColor());
	// }
	// for(Map temp:ptList){
	// for(int j=0;j<dates.length;j++){
	//
	// if(temp.get("everyDay").equals(dates[j])){
	// data[j]=(Long) temp.get("countNum");
	// }
	// }
	// }
	// map.put("data", data);
	// result.add(map);
	// }
	// JSONObject jsonObject = new JSONObject();
	// jsonObject.put("data", result);
	// jsonStringPt = JSONArray.toJSONString(result);
	// }

	return "success";
    }

    public void setAllRegisterNum(Integer allRegisterNum) {
	this.allRegisterNum = allRegisterNum;
    }

    public void setaLoginLogService(IALoginLogService aLoginLogService) {
	this.aLoginLogService = aLoginLogService;
    }

    public void setAreas(String[] areas) {
	this.areas = areas;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setCategories(String categories) {
	this.categories = categories;
    }

    public void setDays(int days) {
	this.days = days;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setJsonString(String jsonString) {
	this.jsonString = jsonString;
    }

    public void setJsonStringPt(String jsonStringPt) {
	this.jsonStringPt = jsonStringPt;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
	this.ossOperationList = ossOperationList;
    }

    public void setOssOperationService(IOssOperationService ossOperationService) {
	this.ossOperationService = ossOperationService;
    }

    public void setOssServerId(String ossServerId) {
	this.ossServerId = ossServerId;
    }

    public void setOssServerList(List<OssServer> ossServerList) {
	this.ossServerList = ossServerList;
    }

    public void setOssServerService(IOssServerService ossServerService) {
	this.ossServerService = ossServerService;
    }

    public void setPlayerService(IPlayerService playerService) {
	this.playerService = playerService;
    }

    public void setPtCode(String ptCode) {
	this.ptCode = ptCode;
    }

    public void setRegisterCount(Integer registerCount) {
	this.registerCount = registerCount;
    }

    public void setServerMap(Map<String, String> serverMap) {
	this.serverMap = serverMap;
    }

    public void setStatList(List<PlayerCreateStat> statList) {
	this.statList = statList;
    }
}
