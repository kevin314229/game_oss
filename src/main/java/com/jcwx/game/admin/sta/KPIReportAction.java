package com.jcwx.game.admin.sta;

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
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.constants.DictTypeConstant;
import com.jcwx.game.domain.KPIDayReport;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.Project;
import com.jcwx.game.service.oss.IKPIReportService;
import com.jcwx.game.service.oss.IProjectService;
import com.jcwx.game.service.oss.impl.OssUserServerService;
import com.jcwx.game.util.ServerListToMap;

/** 统计查询KPI */
@SuppressWarnings("unused")
@ParentPackage("base")
@Namespace("/admin/kpiReport")
@ResultPath("/")
public class KPIReportAction extends BasalAction {

    private static final long serialVersionUID = -8911270861604805583L;

    /** 总记录数 */
    private Integer allNum;
    private String areaId;
    private String[] areas;
    /** 查询kpi日报参数对象 */
    private String beginDate;
    /** 起始页号 */
    private Integer beginNum;
    /** 当前页数 */
    private Integer currPageNO;
    private List<KPIDayReport> dayReports;
    private String endDate;
    private int gameId;

    @Autowired
    private IKPIReportService kpiService;

    /** 每页记录数 */
    private Integer onePageNum;

    /** 服务区列表 */
    private List<OssServer> ossServerList;

    /** 平台列表 */
    private List<OssServer> OssServersPt;

    @Autowired
    private OssUserServerService ossUserServerService;
    /** 总页数 */
    private Integer pages;
    // 用户管理的项目列表
    private List<Project> projectList;

    @Autowired
    private IProjectService projectService;
    private String ptCode;
    private int ptId;
    private Integer[] selectArray;
    private Map<String, String> serverMap;
    private int sex;
    private List<KPIDayReport> sumDayReports;
    private Map<String, Object> loginSum;
    private Map<String, Object> paySum;
    public Integer getAllNum() {
	return allNum;
    }

    public String getAreaId() {
	return areaId;
    }

    public String[] getAreas() {
	return areas;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public Integer getBeginNum() {
	return beginNum;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public List<KPIDayReport> getDayReports() {
	return dayReports;
    }

    public String getEndDate() {
	return endDate;
    }

    @Action(value = "query_OssServerListByServerCode")
    public String getEquipPropertyList() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	ossServerList = new ArrayList<OssServer>();
	List<OssServer> list = getBaseAdminContext().getOssServers();
	if (ptCode != null && !"".equals(ptCode)) {
	    for (OssServer ossServer : list) {
		if (ossServer.getServerCode().equals(ptCode)) {
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

    public int getGameId() {
	return gameId;
    }

    @Action(value = "query_kpi_day_report", results = { @Result(name = "success", location = "../../admin/sta/kpi_day_report.jsp") })
    public String getKPIDayReport() throws Exception {

	BaseAdminContext baseAdminContext = getBaseAdminContext();

	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	if (getOnePageNum() == null || getOnePageNum().intValue() == 0) {
	    setOnePageNum(20);
	}
	Date beginTime = new Date();
	Date endTime = new Date();
	if (beginDate == null || "".equals(beginDate)) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	}
	// 初始化项目ID
	List<OssServer> list = new ArrayList<OssServer>();
	gameId = baseAdminContext.getProject().getProjectId();
	OssServersPt = baseAdminContext.getOssServersPt();
	list = baseAdminContext.getOssServers();
	// if(gameId==0){
	// gameId = baseAdminContext.getProject().getProjectId();
	// OssServersPt =baseAdminContext.getOssServersPt();
	// list= baseAdminContext.getOssServers();
	//
	// }else{
	// OssServersPt=ossUserServerService.getOssServerPtListByUserAndProjectId(
	// baseAdminContext.getOssUser().getUsername(),gameId);
	// list=ossUserServerService.getOssServerListByUserAndProjectId(
	// baseAdminContext.getOssUser().getUsername(),gameId);
	// }
	if (ptCode != null && !"".equals(ptCode)) {
	    // for (OssServer ossServer : list) {
	    // if(ossServer.getServerCode().equals(ptId)){
	    // ossServerList.add(ossServer);
	    // }
	    // }
	    serverMap = ServerListToMap.convert(list, ptCode);
	} else {
	    serverMap = ServerListToMap.convert(list);
	}
	// if(areaId!=null&&!"null".equals(areaId)&&!"".equals(areaId)){
	// areas=areaId.split(",");
	// }
	areaId = ServerListToMap.arrayToString(list, areas, ptCode);
	if (areas != null && areas.length == 1) {
	    areas = ServerListToMap.strToArr(areaId);
	}
	// projectList = new ArrayList<Project>();
	// projectList =
	// projectService.getProjectListbyUserName(baseAdminContext.getOssUser()
	// .getUsername());
	beginNum = (currPageNO - 1) * onePageNum;
	dayReports = kpiService.getKPIDayReportList(beginNum, onePageNum,
		beginDate, endDate, gameId, areaId, ptCode, selectArray);
	allNum = kpiService.getKPIDayReportCount(beginNum, onePageNum,
		beginDate, endDate, gameId, areaId, ptCode);

	sumDayReports = kpiService.sumKPIDayData(beginDate, endDate, gameId,
		areaId, ptCode);
	Map<String,Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	params.put("areaIds", areaId);
	params.put("ptCode", ptCode);
	
	loginSum = kpiService.querySumLogin(params);
	paySum=kpiService.querySumPay(params);
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	
	// // 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}

	return SUCCESS;
    }

    public IKPIReportService getKpiService() {
	return kpiService;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public List<OssServer> getOssServerList() {
	return ossServerList;
    }

    public List<OssServer> getOssServersPt() {
	return OssServersPt;
    }

    public Integer getPages() {
	return pages;
    }

    public List<Project> getProjectList() {
	return projectList;
    }

    public String getPtCode() {
	return ptCode;
    }

    public int getPtId() {
	return ptId;
    }

    public Integer[] getSelectArray() {
	return selectArray;
    }

    public Map<String, String> getServerMap() {
	return serverMap;
    }

    public int getSex() {
	return sex;
    }

    public List<KPIDayReport> getSumDayReports() {
	return sumDayReports;
    }

    @Action(value = "query_queryOssPtlist")
    public String queryOssPtlist() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	OssServersPt = new ArrayList<OssServer>();
	if (gameId != -1) {
	    List<OssServer> list = ossUserServerService
		    .getOssServerPtListByUserAndProjectId(getBaseAdminContext()
			    .getOssUser().getUsername(), gameId);
	    for (OssServer ossServer : list) {
		if (ossServer.getProjectId().equals(gameId)) {
		    OssServersPt.add(ossServer);
		}
	    }
	} else {
	    OssServersPt = getBaseAdminContext().getOssServersPt();
	}
	out.print(JSON.toJSON(OssServersPt).toString());
	out.close();
	return null;
    }

    @Action(value = "query_queryOssServerByPtCode")
    public String queryOssServerByPtCode() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	Map<String, String> dataMap = ServerListToMap.queryDictMap(
		getBaseAdminContext().getProject().getProjectId(),
		DictTypeConstant.AREA_TYPE);
	PrintWriter out = reponse.getWriter();
	ossServerList = new ArrayList<OssServer>();
	List<Map<String, String>> result = new ArrayList<Map<String, String>>();

	if (ptCode != null && !"".equals(ptCode)) {
	    List<OssServer> list = ossUserServerService
		    .getOssServerListByUserAndProjectId(getBaseAdminContext()
			    .getOssUser().getUsername(), gameId);
	    for (OssServer ossServer : list) {
		if (ossServer.getServerCode().equals(ptCode)) {
		    Map<String, String> temp = new HashMap<String, String>();
		    temp.put("name", ossServer.getName());
		    String id = dataMap.get(ossServer.getName());
		    if (id == null)
			temp.put("id", ossServer.getId() + "");
		    else {
			temp.put("id", id);
		    }
		    result.add(temp);
		}
	    }
	} else {
	    // ossServerList=ossUserServerService.getOssServerListByUserAndProjectId(
	    // baseAdminContext.getOssUser().getUsername(),gameId);
	    List<OssServer> list = ossUserServerService
		    .getOssServerListByUserAndProjectId(getBaseAdminContext()
			    .getOssUser().getUsername(), gameId);
	    for (OssServer ossServer : list) {
		Map<String, String> temp = new HashMap<String, String>();
		temp.put("name", ossServer.getName());
		String id = dataMap.get(ossServer.getName());
		if (id == null)
		    temp.put("id", ossServer.getId() + "");
		else {
		    temp.put("id", id);
		}
		result.add(temp);
	    }
	}
	out.print(JSON.toJSON(result).toString());
	out.close();
	return null;
    }

    @Action(value = "query_queryOssServerByPtId")
    public String queryOssServerByPtId() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	Map<String, String> dataMap = ServerListToMap.queryDictMap(
		getBaseAdminContext().getProject().getProjectId(),
		DictTypeConstant.AREA_TYPE);
	List<Map<String, String>> result = new ArrayList<Map<String, String>>();
	ossServerList = new ArrayList<OssServer>();
	if (ptId != -1) {
	    List<OssServer> list = ossUserServerService
		    .getOssServerListByUserAndProjectId(getBaseAdminContext()
			    .getOssUser().getUsername(), gameId);
	    for (OssServer ossServer : list) {
		if (ossServer.getServerId() == ptId) {
		    Map<String, String> temp = new HashMap<String, String>();
		    temp.put("name", ossServer.getName());
		    String id = dataMap.get(ossServer.getName());
		    if (id == null)
			temp.put("id", ossServer.getId() + "");
		    else {
			temp.put("id", id);
		    }
		    result.add(temp);
		}
	    }
	} else {
	    List<OssServer> list = ossUserServerService
		    .getOssServerListByUserAndProjectId(getBaseAdminContext()
			    .getOssUser().getUsername(), gameId);
	    for (OssServer ossServer : list) {
		Map<String, String> temp = new HashMap<String, String>();
		temp.put("name", ossServer.getName());
		String id = dataMap.get(ossServer.getName());
		if (id == null)
		    temp.put("id", ossServer.getId() + "");
		else {
		    temp.put("id", id);
		}
		result.add(temp);
	    }
	}
	out.print(JSON.toJSON(result).toString());
	out.close();
	return null;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setAreaId(String areaId) {
	this.areaId = areaId;
    }

    public void setAreas(String[] areas) {
	this.areas = areas;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setBeginNum(Integer beginNum) {
	this.beginNum = beginNum;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setDayReports(List<KPIDayReport> dayReports) {
	this.dayReports = dayReports;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setGameId(int gameId) {
	this.gameId = gameId;
    }

    public void setKpiService(IKPIReportService kpiService) {
	this.kpiService = kpiService;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOssServerList(List<OssServer> ossServerList) {
	this.ossServerList = ossServerList;
    }

    public void setOssServersPt(List<OssServer> ossServersPt) {
	OssServersPt = ossServersPt;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setProjectList(List<Project> projectList) {
	this.projectList = projectList;
    }

    public void setPtCode(String ptCode) {
	this.ptCode = ptCode;
    }

    public void setPtId(int ptId) {
	this.ptId = ptId;
    }

    public void setSelectArray(Integer[] selectArray) {
	this.selectArray = selectArray;
    }

    public void setServerMap(Map<String, String> serverMap) {
	this.serverMap = serverMap;
    }

    public void setSex(int sex) {
	this.sex = sex;
    }

    public void setSumDayReports(List<KPIDayReport> sumDayReports) {
	this.sumDayReports = sumDayReports;
    }

    public Map<String, Object> getLoginSum() {
        return loginSum;
    }

    public void setLoginSum(Map<String, Object> loginSum) {
        this.loginSum = loginSum;
    }

    public Map<String, Object> getPaySum() {
        return paySum;
    }

    public void setPaySum(Map<String, Object> paySum) {
        this.paySum = paySum;
    }
    
    
    @Action(value = "query_getKPIDayReportByPtCode", results = { @Result(name = "success", location = "../../zhxy/sta/kpi_day_report_ptcode.jsp") })
    public String getKPIDayReportByPtCode() throws Exception {
	Map<String,Object> params = initParam();
	dayReports = kpiService.getKPIDayReportListByPtCode(params);
	return SUCCESS;
    }

    

    
    @Action(value = "query_getPtKPIDayReport", results = { @Result(name = "success", location = "../../zhxy/sta/pt_kpi_day_report.jsp") })
    public String getPtKPIDayReport() throws Exception {
	
	Map<String,Object> params =initParam();
	dayReports = kpiService.getPtKPIDayReportList(params);
	return SUCCESS;
    }
    private Map<String,Object> initParam() throws Exception {
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	if (getOnePageNum() == null || getOnePageNum().intValue() == 0) {
	    setOnePageNum(20);
	}
	beginNum = (currPageNO - 1) * onePageNum;
	Date beginTime = new Date();
	Date endTime = new Date();
	if (beginDate == null || "".equals(beginDate)) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	}
	Map<String,Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	BaseAdminContext baseAdminContext=getBaseAdminContext();
	gameId = baseAdminContext.getProject().getProjectId();
	params.put("gameId", gameId);
	params.put("beginNum", beginNum);
	params.put("onePageNum", onePageNum);
	OssServersPt = baseAdminContext.getOssServersPt();
	List<OssServer> list = new ArrayList<OssServer>();
	list = baseAdminContext.getOssServers();
	 
	if (ptCode != null && !"".equals(ptCode)) {
	    
	    serverMap = ServerListToMap.convert(list, ptCode);
	} else {
	    serverMap = ServerListToMap.convert(list);
	}
	
	areaId = ServerListToMap.arrayToString(list, areas, ptCode);
	if (areas != null && areas.length == 1) {
	    areas = ServerListToMap.strToArr(areaId);
	}
	params.put("areaIds", areaId);
	return params;
    }
}
