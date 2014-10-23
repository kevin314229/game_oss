package com.jcwx.game.admin.sta;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.constants.DictTypeConstant;
import com.jcwx.game.common.constants.OperationLogConstant;
import com.jcwx.game.domain.ConsumeData;
import com.jcwx.game.domain.ConsumeFirstData;
import com.jcwx.game.domain.OssDictData;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.Project;
import com.jcwx.game.service.oss.IConsumeService;
import com.jcwx.game.service.oss.IOssDictService;
import com.jcwx.game.service.oss.IProjectService;
import com.jcwx.game.service.oss.impl.OssUserServerService;
import com.jcwx.game.util.ExportExcel;
import com.jcwx.game.util.ServerListToMap;
import com.opensymphony.xwork2.ActionContext;

/** 统计查询KPI */
@SuppressWarnings("unused")
@ParentPackage("base")
@Namespace("/admin/consume")
@ResultPath("/")
public class ConsumeAction extends BasalAction {

    private static final long serialVersionUID = -8911270861604805583L;

    /** 总记录数 */
    private Integer allNum;

    private int areaId;
    private String areaIdStr;
    // private Str
    private String[] areas;
    /** 查询消费魔晶参数对象 */
    private String beginDate;
    /** 起始页号 */
    private Integer beginNum;
    private List<ConsumeData> consumeAreaDatas;
    private List<ConsumeData> consumeDatas;
    private List<ConsumeFirstData> consumeFirstDatas;
    @Autowired
    private IConsumeService consumeService;
    /** 当前页数 */
    private Integer currPageNO;

    /** 距首登陆分布 */
    private List<JSONObject> daysJsonList;

    @Autowired
    private IOssDictService dictService;

    private String endDate;

    private int gameId;
    /** 等级分布JSON */
    private List<JSONObject> levelJsonList;
    /** 登录名 */
    private String loginName;

    /** 角色名 */
    private String nickName;

    /** 每页记录数 */
    private Integer onePageNum;
    /** 操作点分布 */
    private List<JSONObject> operationJsonList;
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
    private String ptId;
    private Map<String, String> serverMap;
    private JSONArray jsonArray;
    
    
    /** 元宝来源 */
    private Integer sourceType;

    private Integer  cycle;
    
    private List<ConsumeData> resultList;
    
    private Integer allConsumeGold;
    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public Integer getAllNum() {
	return allNum;
    }

    public int getAreaId() {
	return areaId;
    }

    public String getAreaIdStr() {
	return areaIdStr;
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

    public List<ConsumeData> getConsumeAreaDatas() {
	return consumeAreaDatas;
    }

    @Action(value = "query_consume_data")
    public String getConsumeData() throws Exception {
	// 初始化项目ID
	List<OssServer> list = new ArrayList<OssServer>();
	// if(gameId==0){
	list = getBaseAdminContext().getOssServers();

	// }else{
	// list=ossUserServerService.getOssServerListByUserAndProjectId(
	// baseAdminContext.getOssUser().getUsername(),gameId);
	//
	areaIdStr = ServerListToMap.arrayToString(list, areas, ptId);

	consumeDatas = consumeService.getConsumeDataList(0, 0, beginDate,
		endDate, gameId, areaIdStr , ptId, loginName, nickName,
		sourceType);
	ConsumeData data = new ConsumeData();
	// data.setConsumeDate(consumeDate)
	consumeAreaDatas = consumeService.sumAreaConsumeList(beginNum,
		onePageNum, beginDate, endDate, gameId, areaIdStr, ptId,
		loginName, nickName, sourceType);
	consumeDatas.addAll(consumeAreaDatas);
	String fileName = "消费日志" + beginDate + "-" + endDate;
	String[] titles = { "日期", "游戏", "游戏平台", "服务器ID", "账户ID(登录名)", "角色ID",
		"角色名称", "消费点", "消费金额", "折合人民币", "消费时间" };
	String[] elements = { "consumeDate", "gameId", "ptId", "areaId",
		"loginName", "playerBaseId", "nickName", "operation", "number",
		"moneyNum", "createTime" };
	ActionContext context = ActionContext.getContext();
	HttpServletResponse response = (HttpServletResponse) context
		.get(StrutsStatics.HTTP_RESPONSE);
	response.setCharacterEncoding("utf-8");
	ExportExcel.export(titles, elements, consumeDatas, fileName, "消费统计",
		response, ConsumeData.class, ".xls");
	return null;
    }

    /*
     * 查询消费记录
     */
    @Action(value = "query_consume_data_list", results = { @Result(name = "success", location = "../../admin/sta/consume_data_report.jsp") })
    public String getConsumeDataList() throws Exception {
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

	// projectList = new ArrayList<Project>();
	// projectList =
	// projectService.getProjectListbyUserName(baseAdminContext.getOssUser().getUsername());
	// 设置要查询的服务器
	// if(ptId!=null&&!"".equals(ptId)){
	// ossServerList=new ArrayList<OssServer>();
	// List<OssServer> list= baseAdminContext.getOssServers();
	// for (OssServer ossServer : list) {
	// if(ossServer.getServerCode().equals(ptId)){
	// ossServerList.add(ossServer);
	// }
	// }
	// }
	// 将大区ID列表转换为字符串
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
	areaIdStr = ServerListToMap.arrayToString(list, areas, ptId);
	if (areas != null && areas.length == 1) {
	    areas = areas[0].split(",");
	}
	beginNum = (currPageNO - 1) * onePageNum;
	consumeDatas = consumeService.getConsumeDataList(beginNum, onePageNum,
		beginDate, endDate, gameId, areaIdStr, ptId, loginName,
		nickName, sourceType);
	allNum = consumeService.getConsumeDataCount(beginNum, onePageNum,
		beginDate, endDate, gameId, areaIdStr, ptId, loginName,
		nickName, sourceType);
	consumeAreaDatas = consumeService.sumAreaConsumeList(beginNum,
		onePageNum, beginDate, endDate, gameId, areaIdStr, ptId,
		loginName, nickName, sourceType);
	List consumeList=consumeService.sumAreaConsumeList(beginDate, endDate, gameId, areaIdStr,sourceType);
	jsonArray = (JSONArray) JSONArray.toJSON(consumeList);
	
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// // 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	return SUCCESS;
    }

    public List<ConsumeData> getConsumeDatas() {
	return consumeDatas;
    }

    @Action(value = "query_consume_first")
    public String getConsumeFirst() throws Exception {
	// 初始化项目ID
	List<OssServer> list = new ArrayList<OssServer>();
	// if(gameId==0){
	list = getBaseAdminContext().getOssServers();
	//
	// }else{
	// list=ossUserServerService.getOssServerListByUserAndProjectId(
	// baseAdminContext.getOssUser().getUsername(),gameId);
	// }
	areaIdStr = ServerListToMap.arrayToString(list, areas, ptId);
	consumeFirstDatas = consumeService.getConsumeFirstDataList(0, 0,
		beginDate, endDate, gameId, areaIdStr, ptId, loginName,
		nickName);
	String fileName = "首次消费" + beginDate + "-" + endDate;
	String[] titles = { "日期", "游戏", "游戏平台", "服务器ID", "账户ID(登录名)", "角色ID",
		"角色名称", "消费点", "充值金额", "首次登陆日期", "距首登天数" };
	String[] elements = { "consumeDate", "gameId", "ptId", "areaId",
		"loginName", "playerBaseId", "nickName", "operation",
		"moneyNum", "firstLoginTime", "fromFirstDays" };
	ActionContext context = ActionContext.getContext();
	HttpServletResponse response = (HttpServletResponse) context
		.get(StrutsStatics.HTTP_RESPONSE);
	response.setCharacterEncoding("utf-8");
	ExportExcel.export(titles, elements, consumeFirstDatas, fileName,
		"首次消费统计", response, ConsumeFirstData.class, ".xls");
	return null;
    }

    /*
     * 查询首次消费记录
     */
    @Action(value = "query_consume_first_data_list", results = { @Result(name = "success", location = "../../admin/sta/consume_first_report.jsp") })
    public String getConsumeFirstDataList() throws Exception {
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	if (getOnePageNum() == null || getOnePageNum().intValue() == 0) {
	    setOnePageNum(20);
	}
	// if(beginDate ==null||"".equals(beginDate)){
	// Calendar cal = Calendar.getInstance();
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// endDate = sdf.format(cal.getTime());
	// cal.add(Calendar.DATE, -7);
	// beginDate = sdf.format(cal.getTime());
	// }
	// 将大区ID列表转换为字符串
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
	areaIdStr = ServerListToMap.arrayToString(list, areas, ptId);
	if (areas != null && areas.length == 1) {
	    areas = areas[0].split(",");
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
	// if(ptId!=null&&!"".equals(ptId)){
	// ossServerList=new ArrayList<OssServer>();
	// List<OssServer> list= baseAdminContext.getOssServers();
	// for (OssServer ossServer : list) {
	// if(ossServer.getServerCode().equals(ptId)){
	// ossServerList.add(ossServer);
	// }
	// }
	// }

	projectList = new ArrayList<Project>();
	projectList = projectService
		.getProjectListbyUserName(getBaseAdminContext().getOssUser()
			.getUsername());
	beginNum = (currPageNO - 1) * onePageNum;

	consumeFirstDatas = consumeService.getConsumeFirstDataList(beginNum,
		onePageNum, beginDate, endDate, gameId, areaIdStr, ptId,
		loginName, nickName);

	allNum = consumeService.getConsumeFirstDataCount(beginNum, onePageNum,
		beginDate, endDate, gameId, areaIdStr, ptId, loginName,
		nickName);

	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// // 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	levelJsonList = new ArrayList<JSONObject>();
	List<Map> leveList = consumeService.getLevelCountList(0, 0, beginDate,
		endDate, gameId, areaIdStr, ptId, loginName, nickName);
	for (int i = 0; i < leveList.size(); i++) {
	    JSONObject leveJson = new JSONObject();
	    leveJson.put("level", leveList.get(i).get("level"));
	    leveJson.put("levelNum", leveList.get(i).get("levelNum"));
	    levelJsonList.add(leveJson);
	}
	operationJsonList = new ArrayList<JSONObject>();
	List<OssDictData> dictList = dictService.getOssDictDataList(gameId,DictTypeConstant.OPERTION_TYPE);
	Map<String, String> dictMap = new HashMap<String, String>();
	if (dictList != null) {
	    for (OssDictData dictData : dictList) {
		dictMap.put(dictData.getDictValue(), dictData.getDictName());
	    }
	}
	List<Map> operationList = consumeService.getOperationCountList(0, 0,
		beginDate, endDate, gameId, areaIdStr, ptId, loginName,
		nickName);
	for (int i = 0; i < operationList.size(); i++) {
	    JSONObject operationJson = new JSONObject();
	    if (dictList != null && dictList.size() > 0){
		String operName=dictMap.get(operationList.get(i).get("operation"));
			
		if(operName!=null&&!"".equals(operName)){
		    operationJson.put("operation",
				dictMap.get(operationList.get(i).get("operation")));
		}else{
		    operationJson.put("operation",operationList.get(i).get("operation"));
		}
		
	    }else {
		String oper = OperationLogConstant.maptype.get(operationList
			.get(i).get("operation"));
		if (oper != null)
		    operationJson.put(
			    "operation",
			    OperationLogConstant.maptype.get(operationList.get(
				    i).get("operation")));
		else {
		    operationJson.put("operation",
			    operationList.get(i).get("operation"));
		}
	    }
	    operationJson.put("operationNum",
		    operationList.get(i).get("operationNum"));
	    operationJsonList.add(operationJson);
	}
	daysJsonList = new ArrayList<JSONObject>();
	List<Map> daysList = consumeService.getFromFirstDaysCountList(0, 0,
		beginDate, endDate, gameId, areaIdStr, ptId, loginName,
		nickName);
	for (int i = 0; i < daysList.size(); i++) {
	    JSONObject daysJson = new JSONObject();
	    daysJson.put("fromFirstDays", daysList.get(i).get("fromFirstDays"));
	    daysJson.put("fromFirstDaysNum",
		    daysList.get(i).get("fromFirstDaysNum"));
	    daysJsonList.add(daysJson);
	}
	return SUCCESS;
    }

    public List<ConsumeFirstData> getConsumeFirstDatas() {
	return consumeFirstDatas;
    }

    public IConsumeService getConsumeService() {
	return consumeService;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public List<JSONObject> getDaysJsonList() {
	return daysJsonList;
    }

    public String getEndDate() {
	return endDate;
    }

    public int getGameId() {
	return gameId;
    }

    public List<JSONObject> getLevelJsonList() {
	return levelJsonList;
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

    public List<JSONObject> getOperationJsonList() {
	return operationJsonList;
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

    public String getPtId() {
	return ptId;
    }

    public Map<String, String> getServerMap() {
	return serverMap;
    }

    public Integer getSourceType() {
	return sourceType;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setAreaId(int areaId) {
	this.areaId = areaId;
    }

    public void setAreaIdStr(String areaIdStr) {
	this.areaIdStr = areaIdStr;
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

    public void setConsumeAreaDatas(List<ConsumeData> consumeAreaDatas) {
	this.consumeAreaDatas = consumeAreaDatas;
    }

    public void setConsumeDatas(List<ConsumeData> consumeDatas) {
	this.consumeDatas = consumeDatas;
    }

    public void setConsumeFirstDatas(List<ConsumeFirstData> consumeFirstDatas) {
	this.consumeFirstDatas = consumeFirstDatas;
    }

    public void setConsumeService(IConsumeService consumeService) {
	this.consumeService = consumeService;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setDaysJsonList(List<JSONObject> daysJsonList) {
	this.daysJsonList = daysJsonList;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setGameId(int gameId) {
	this.gameId = gameId;
    }

    public void setLevelJsonList(List<JSONObject> levelJsonList) {
	this.levelJsonList = levelJsonList;
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

    public void setOperationJsonList(List<JSONObject> operationJsonList) {
	this.operationJsonList = operationJsonList;
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

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

    public void setServerMap(Map<String, String> serverMap) {
	this.serverMap = serverMap;
    }

    public void setSourceType(Integer sourceType) {
	this.sourceType = sourceType;
    }

    @Action(value = "query_CycConsumeGoldList", results = { @Result(name = "success", location = "../../zhxy/sta/new_login_player_consumeList.jsp") })
    public String queryCycConsumeGoldList() throws Exception {
	if(endDate==null||"".equals(endDate)){
	    initParam();
	    return SUCCESS;
	}
	Map<String,Object> params = initParam();
	resultList=consumeService.queryCycConsumeGoldList(params);
	allConsumeGold=0;
	for(ConsumeData temp:resultList){
	    allConsumeGold+=temp.getConsumeNum();
	  //  temp.put(key, value)
	}
	//allConsumeGold = consumeService.queryCycConsumeGoldSum(params);
	return SUCCESS;
    }
    
    private Map<String,Object> initParam() throws Exception {
	List<OssServer> list = new ArrayList<OssServer>();
	gameId = getBaseAdminContext().getProject().getProjectId();
	OssServersPt = getBaseAdminContext().getOssServersPt();
	list = getBaseAdminContext().getOssServers();
	 
	if (ptId != null && !"".equals(ptId)) {

	    serverMap = ServerListToMap.convert(list, ptId);
	} else {
	    serverMap = ServerListToMap.convert(list);
	}
	// if(areaId!=null&&!"null".equals(areaId)&&!"".equals(areaId)){
	// areas=areaId.split(",");
	// }
	areaIdStr = ServerListToMap.arrayToString(list, areas, ptId);
	if (areas != null && areas.length == 1) {
	    areas = areas[0].split(",");
	}
	//beginNum = (currPageNO - 1) * onePageNum;
	if(endDate==null)
	    return null;
	Map<String,Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	Date tempDate = DateService.getDateByStrAndFormat(endDate, "yyyy-MM-dd");
	
	Date cycBeginDate= DateService.dateIncreaseByDay(tempDate, (cycle-1)*7+1);
	Date cycEndDate= DateService.dateIncreaseByDay(tempDate, 7*cycle);
	
	params.put("gameId", gameId);
	params.put("areaId", areaIdStr);
	params.put("cycBeginDate", DateService.getDateFirstTime(cycBeginDate));
	params.put("cycEndDate", DateService.getDateLastTime(cycEndDate));
	
 	return params;
     }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public List<ConsumeData> getResultList() {
        return resultList;
    }

    public void setResultList(List<ConsumeData> resultList) {
        this.resultList = resultList;
    }

    public Integer getAllConsumeGold() {
        return allConsumeGold;
    }

    public void setAllConsumeGold(Integer allConsumeGold) {
        this.allConsumeGold = allConsumeGold;
    }

}
