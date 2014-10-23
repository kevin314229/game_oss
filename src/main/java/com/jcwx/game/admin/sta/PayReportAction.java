package com.jcwx.game.admin.sta;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.PayHistory;
import com.jcwx.game.domain.PayRank;
import com.jcwx.game.domain.PayStatistic;
import com.jcwx.game.domain.Project;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.service.oss.IOssServerService;
import com.jcwx.game.service.oss.IPayHistoryService;
import com.jcwx.game.service.oss.IProjectService;
import com.jcwx.game.service.oss.impl.OssUserServerService;
import com.jcwx.game.util.ServerListToMap;

/**
 * 充值记录action
 * 
 * @author csp
 * 
 */
@ParentPackage("base")
@Namespace("/admin/payReport")
@ResultPath("/")
public class PayReportAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private Integer allNum;
    // 大区 ID
    private String areaId;
    // 大区 ID列表多选
    private String[] areas;
    // 开始时间
    private String beginDate;
    /** 起始页号 */
    private Integer beginNum;
    private String categories;
    /** 当前页数 */
    private Integer currPageNO;

    private int days;
    // 结束时间
    private String endDate;

    // 游戏ID
    private int gameId;

    private String jsonString;

    private String loginName;

    private String nickName;

    /** 每页记录数 */
    private Integer onePageNum;
    // 选择的平台ID
    private Integer operationId;
    /** 查询平台标识 */
    private List<OssOperation> ossOperationList;
    @Autowired
    private IOssOperationService ossOperationService;
    /** 服务器列表 */
    private List<OssServer> ossServerList;
    @Autowired
    private IOssServerService ossServerService;
    /** 平台列表 */
    private List<OssServer> OssServersPt;
    @Autowired
    private OssUserServerService ossUserServerService;
    /** 总页数 */
    private Integer pages;
    /** 统计服务器充值排行list */
    private List<PayRank> payAreaList;
    /** 统计服务器充值历史记录list */
    private List<PayHistory> payHistoryList;

    @Autowired
    private IPayHistoryService payHistoryService;

    /** 充值排行list */
    private List<PayRank> payRankList;

    // 用户管理的项目列表
    private List<Project> projectList;
    @Autowired
    private IProjectService projectService;

    // 平台标识
    private String ptId;
    // 平台标识
    private String ptCode;
    private Map<String, String> serverMap;

    public String generateColor() {
	String r, g, b;
	Random random = new Random();
	r = Integer.toHexString(random.nextInt(256)).toUpperCase();
	g = Integer.toHexString(random.nextInt(256)).toUpperCase();
	b = Integer.toHexString(random.nextInt(256)).toUpperCase();

	r = r.length() == 1 ? "0" + r : r;
	g = g.length() == 1 ? "0" + g : g;
	b = b.length() == 1 ? "0" + b : b;

	System.out.println(r + g + b);
	;
	return "#" + r + g + b;
    }

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

    public String getCategories() {
	return categories;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public int getDays() {
	return days;
    }

    public String getEndDate() {
	return endDate;
    }

    public int getGameId() {
	return gameId;
    }

    public String getJsonString() {
	return jsonString;
    }

    public String getLoginName() {
	return loginName;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public Integer getOperationId() {
	return operationId;
    }

    public List<OssOperation> getOssOperationList() {
	return ossOperationList;
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

    public List<PayRank> getPayAreaList() {
	return payAreaList;
    }

    public List<PayHistory> getPayHistoryList() {
	return payHistoryList;
    }

    public List<PayRank> getPayRankList() {
	return payRankList;
    }

    public List<Project> getProjectList() {
	return projectList;
    }

    public String getPtId() {
	return ptId;
    }

    public Map<String, String> getServerMap() {
	return serverMap;
    }

    @Action(value = "payReport_index", results = { @Result(name = "success", location = "../../admin/sta/payReport_index.jsp") })
    public String index() throws Exception {
	try {
	    // 初始化日期信息
	    Date beginTime = new Date();
	    Date endTime = new Date();

	    if (beginDate == null) {
		beginTime = DateService.getCurrentMonthFirstDay();
		beginDate = DateService.getDateFormatStr(beginTime,
			"yyyy-MM-dd");
	    }
	    if (endDate == null) {
		endTime = DateService.getCurrentDayLastUtilDate();
		endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	    }
	    // 初始化项目ID
	    List<OssServer> list = new ArrayList<OssServer>();
	    gameId = getBaseAdminContext().getProject().getProjectId();
	    OssServersPt = getBaseAdminContext().getOssServersPt();
	    list = getBaseAdminContext().getOssServers();
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
	    if (ptId != null && !"".equals(ptId)) {
		serverMap = ServerListToMap.convert(list, ptId);
	    } else {
		serverMap = ServerListToMap.convert(list);
	    }
	    // if(areaId!=null&&!"null".equals(areaId)&&!"".equals(areaId)){
	    // areas=areaId.split(",");
	    // }
	    // 将大区ID列表转换为字符串
	    areaId = ServerListToMap.arrayToString(list, areas, ptId);
	    if (areas != null && areas.length == 1) {
		areas = areas[0].split(",");
	    }
	    // 获取游戏列表
	    projectList = new ArrayList<Project>();
	    projectList = projectService
		    .getProjectListbyUserName(getBaseAdminContext()
			    .getOssUser().getUsername());
	    ossOperationList=ossOperationService.getOssOperationList();
	    // 初始化分页信息
	    if (currPageNO == null || currPageNO.intValue() == 0) {
		currPageNO = 1;
	    }
	    if (getOnePageNum() == null || getOnePageNum().intValue() == 0) {
		setOnePageNum(20);
	    }
	    beginNum = (currPageNO - 1) * onePageNum;
	    // 查询结果集
	    payRankList = payHistoryService.getPayRankList(beginNum,
		    onePageNum, gameId, areaId, ptCode, loginName);

	    // 查询结果集
	    payAreaList = payHistoryService.getAreaPayList(gameId, areaId,
		    ptCode, loginName);
	    allNum = payHistoryService.getPayRankCount(gameId, areaId, ptCode,
		    loginName);
	    // 出来分页信息
	    pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		    / onePageNum;
	    // // 当前页设置
	    if (currPageNO.intValue() > pages) {
		currPageNO = pages;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "payReport_queryAreaRecordIndex", results = { @Result(name = "success", location = "../../admin/sta/area_record_index.jsp") })
    public String queryAreaRecordIndex() throws Exception {
	try {
	    // 初始化日期信息
	    Date beginTime = new Date();
	    Date endTime = new Date();

	    if (beginDate == null) {
		beginTime = DateService.getCurrentMonthFirstDay();
		beginDate = DateService.getDateFormatStr(beginTime,
			"yyyy-MM-dd");
	    }
	    if (endDate == null) {
		endTime = DateService.getCurrentDayLastUtilDate();
		endTime = DateService.dateIncreaseByDay(endTime, -1);
		endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	    }
	    List<OssServer> list = new ArrayList<OssServer>();
	    gameId = getBaseAdminContext().getProject().getProjectId();
	    OssServersPt = getBaseAdminContext().getOssServersPt();
	    list = getBaseAdminContext().getOssServers();
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
	    // 将大区ID列表转换为字符串
	    if (ptId != null && !"".equals(ptId)) {

		serverMap = ServerListToMap.convert(list, ptId);
	    } else {
		serverMap = ServerListToMap.convert(list);
	    }
	    // if(areaId!=null&&!"null".equals(areaId)&&!"".equals(areaId)){
	    // areas=areaId.split(",");
	    // }
	    areaId = ServerListToMap.arrayToString(list, areas, ptId);
	    if (areas == null && ptId == null) {
		return SUCCESS;
	    }
	    String[] areaArr = areaId.split(",");

	    Date[] dates = ServerListToMap.dateToArray(beginDate, endDate);
	    categories = ServerListToMap.dateArrayToStrings(dates);
	    days = dates.length;
	    List<Map<Object, Object>> result = new ArrayList<Map<Object, Object>>();
	    for (int i = 0; i < areaArr.length; i++) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<PayStatistic> payList = payHistoryService
			.getAreaRecordList(gameId,
				Integer.parseInt(areaArr[i]), beginDate,
				endDate);
		// categories[i]=
		// OssServerConstant.getptTypeMap().get(areas[i]);
		double[] data = new double[dates.length];
		String areaName = OssServerConstant.getptTypeMap().get(
			areaArr[i]);
		if (areaName == null) {
		    map.put("name", "" + areaArr[i]);
		} else {
		    map.put("name", areaName);
		}
		if (areaArr.length > 10) {
		    map.put("color", generateColor());
		}
		for (PayStatistic temp : payList) {
		    for (int j = 0; j < dates.length; j++) {
			if (temp.getPayDate().getTime() == dates[j].getTime()) {
			    data[j] = temp.getMoneyNum();
			}
		    }
		}
		map.put("data", data);
		result.add(map);
	    }
	    // 获取游戏列表
	    JSONObject jsonObject = new JSONObject();
	    // jsonObject.put("categories", dates);
	    jsonObject.put("data", result);
	    // HttpServletResponse reponse = ServletActionContext.getResponse();
	    // reponse.setContentType("text/html; charset=utf-8");
	    jsonString = JSON.toJSONString(result);
	    // PrintWriter out = reponse.getWriter();
	    // out.print(jsonObject.toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "payReport_queryAreaRecordList", results = { @Result(name = "success", location = "../../admin/sta/payRecord_list.jsp") })
    public String queryAreaRecordList() throws Exception {
	try {
	    // 初始化日期信息
	    Date beginTime = new Date();
	    Date endTime = new Date();

	    if (beginDate == null) {
		beginTime = DateService.getCurrentMonthFirstDay();
		beginDate = DateService.getDateFormatStr(beginTime,
			"yyyy-MM-dd");
	    }
	    if (endDate == null) {
		endTime = DateService.getCurrentDayLastUtilDate();
		endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	    }
	    List<OssServer> list = new ArrayList<OssServer>();
	    gameId = getBaseAdminContext().getProject().getProjectId();
	    OssServersPt = getBaseAdminContext().getOssServersPt();
	    list = getBaseAdminContext().getOssServers();
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
	    // 将大区ID列表转换为字符串
	    if (ptId != null && !"".equals(ptId)) {

		serverMap = ServerListToMap.convert(list, ptId);
	    } else {
		serverMap = ServerListToMap.convert(list);
	    }
	    // if(areaId!=null&&!"null".equals(areaId)&&!"".equals(areaId)){
	    // areas=areaId.split(",");
	    // }
	    areaId = ServerListToMap.arrayToString(list, areas, ptId);
	    areas = areaId.split(",");
	    // categories = new String[areas.length];
	    Date[] dates = ServerListToMap.dateToArray(beginDate, endDate);
	    List<Map<Object, Object>> result = new ArrayList<Map<Object, Object>>();
	    for (int i = 0; i < areas.length; i++) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<PayStatistic> payList = payHistoryService
			.getAreaRecordList(gameId, Integer.parseInt(areas[i]),
				beginDate, endDate);
		// categories[i]=
		// OssServerConstant.getptTypeMap().get(areas[i]);
		double[] data = new double[dates.length];
		map.put("name", OssServerConstant.getptTypeMap().get(areas[i]));
		for (PayStatistic temp : payList) {
		    for (int j = 0; j < dates.length; j++) {
			if (temp.getPayDate().getTime() == dates[j].getTime()) {
			    data[j] = temp.getMoneyNum();
			}
		    }
		}
		map.put("data", data);
		result.add(map);
	    }
	    // 获取游戏列表
	    JSONObject jsonObject = new JSONObject();
	    // jsonObject.put("categories", dates);
	    jsonObject.put("data", result);
	    // HttpServletResponse reponse = ServletActionContext.getResponse();
	    // reponse.setContentType("text/html; charset=utf-8");
	    jsonString = jsonObject.toString();
	    // PrintWriter out = reponse.getWriter();
	    // out.print(jsonObject.toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    // 获取服务器列表方法
    @Action(value = "ipReport_queryOssServerList")
    public String queryOssServerList() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	if (operationId == null) {
	    operationId = -1;
	} else {
	    ossServerList = new ArrayList<OssServer>();
	    List<OssServer> list = ossServerService
		    .getOssServerListByProjectIdAndOperationId(
			    Integer.valueOf(gameId), operationId);
	    for (OssServer ossServer : list) {
		ossServerList.add(ossServer);
	    }
	}
	out.print(JSON.toJSON(ossServerList).toString());
	out.close();
	return null;
    }

    @Action(value = "payReport_recordList", results = { @Result(name = "success", location = "../../admin/sta/payRecord_list.jsp") })
    public String queryPayRecordList() throws Exception {
	try {
	    // 初始化日期信息
	    Date beginTime = new Date();
	    Date endTime = new Date();

	    if (beginDate == null) {
		beginTime = DateService.getCurrentMonthFirstDay();
		beginDate = DateService.getDateFormatStr(beginTime,
			"yyyy-MM-dd");
	    }
	    if (endDate == null) {
		endTime = DateService.getCurrentDayLastUtilDate();
		endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	    }
	    List<OssServer> list = new ArrayList<OssServer>();
	    gameId = getBaseAdminContext().getProject().getProjectId();
	    OssServersPt = getBaseAdminContext().getOssServersPt();
	    list = getBaseAdminContext().getOssServers();
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
	    // 将大区ID列表转换为字符串
	    if (ptId != null && !"".equals(ptId)) {

		serverMap = ServerListToMap.convert(list, ptId);
	    } else {
		serverMap = ServerListToMap.convert(list);
	    }
	    // if(areaId!=null&&!"null".equals(areaId)&&!"".equals(areaId)){
	    // areas=areaId.split(",");
	    // }
	    areaId = ServerListToMap.arrayToString(list, areas, ptId);
	    if (areas != null && areas.length == 1) {
		areas = areas[0].split(",");
	    }
	    // 获取游戏列表
	    projectList = new ArrayList<Project>();
	    projectList = projectService
		    .getProjectListbyUserName(getBaseAdminContext()
			    .getOssUser().getUsername());
	    ossOperationList=ossOperationService.getOssOperationList();
	    // 初始化分页信息
	    if (currPageNO == null || currPageNO.intValue() == 0) {
		currPageNO = 1;
	    }
	    if (getOnePageNum() == null || getOnePageNum().intValue() == 0) {
		setOnePageNum(20);
	    }
	    beginNum = (currPageNO - 1) * onePageNum;
	    // 查询结果集
	    payHistoryList = payHistoryService.getPayInfoList(beginNum,
		    onePageNum, gameId, areaId, ptCode, beginDate, endDate,
		    loginName, nickName);

	    // 查询结果集
	    // payAreaList = payHistoryService.getAreaPayList(gameId, areaId,
	    // ptId, loginName);
	    allNum = payHistoryService.getPayInfoCount(gameId, areaId, ptCode,
		    beginDate, endDate, loginName, nickName);
	    // 出来分页信息
	    pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		    / onePageNum;
	    // // 当前页设置
	    if (currPageNO.intValue() > pages) {
		currPageNO = pages;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
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

    public void setCategories(String categories) {
	this.categories = categories;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setDays(int days) {
	this.days = days;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setGameId(int gameId) {
	this.gameId = gameId;
    }

    public void setJsonString(String jsonString) {
	this.jsonString = jsonString;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
	this.ossOperationList = ossOperationList;
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

    public void setPayAreaList(List<PayRank> payAreaList) {
	this.payAreaList = payAreaList;
    }

    public void setPayHistoryList(List<PayHistory> payHistoryList) {
	this.payHistoryList = payHistoryList;
    }

    public void setPayRankList(List<PayRank> payRankList) {
	this.payRankList = payRankList;
    }

    public void setProjectList(List<Project> projectList) {
	this.projectList = projectList;
    }

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

    public void setServerMap(Map<String, String> serverMap) {
	this.serverMap = serverMap;
    }

    public String getPtCode() {
        return ptCode;
    }

    public void setPtCode(String ptCode) {
        this.ptCode = ptCode;
    }

}
