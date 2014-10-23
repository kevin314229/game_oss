package com.jcwx.game.admin.pay;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.common.constants.DictTypeConstant;
import com.jcwx.game.common.constants.OperationLogConstant;
import com.jcwx.game.domain.OssDictData;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.OperationClass;
import com.jcwx.game.http.domain.OssOperationLog;
import com.jcwx.game.http.domain.SendBaseProperty;
import com.jcwx.game.service.oss.IOssDictService;
import com.jcwx.game.util.ChineseCharToEn;
import com.jcwx.game.util.ExportExcel;
import com.jcwx.game.web.Global;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.opensymphony.xwork2.ActionContext;

@ParentPackage("base")
@Namespace("/admin/pay")
@ResultPath("/")
@Action(value = "queryMoneyConsume", results = { @Result(name = "success", location = "../../admin/pay/queryMoneyConsume.jsp") })
public class QueryMoneyConsumeAction extends BasalAction {

    private static final String QUERY_ALL = "queryAll";

    private static final Logger logger = LoggerFactory
		.getLogger(QueryMoneyConsumeAction.class);
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private Integer allNum;

    /** 开始时间 */
    private String beginDate;

    private Integer consumeTotal;

    /** 当前页数 */
    private Integer currPageNO;
    /** 结束时间 */
    private String endDate;
    /** 玩家帐号 */
    private String loginName;

    /** 角色名字 */
    private String nickName;

    /** 每页记录数 */
    private Integer onePageNum;

    /** 操作 */
    private String operation;

    /** 自动补齐查询带过来操作 */
    private String operation1;

    /** 充值集合 */
    private List<OperationClass> operationClassList;
    
    private List<OssOperationLog> operationLogList;

    /** 操作细节 */
    private String operationDetail;

    /** 总页数 */
    private Integer pages;

    /** 玩家Id */
    private Integer playerId;

    private String resultJson;

    /** 消费类型 **/
    private String target;

    private Integer flowType;
    
    private Integer itemId;
    /** 平台标识 */
    // private String plat;
    @Autowired
    private IOssDictService ossDictService;
    @Override
    @SuppressWarnings("unchecked")
    public String execute() throws Exception {
	List<Map> result = new ArrayList<Map>();
	Map<String, String> map = OperationLogConstant.maptype;
	
	Map<String,String> dictMap=queryDictMap(DictTypeConstant.GAME_FM, DictTypeConstant.OPERTION_TYPE);
	for (String key : map.keySet()) {
	    Map temp = new HashMap();
	    temp.put("code", key);
	    String name="";
	    if(dictMap.get(key)!=null&&!"".equals(dictMap.get(key))){
		name = dictMap.get(key);
	    }else{
		name = map.get(key);
	    }
	    temp.put("name", name);
	    temp.put("help", ChineseCharToEn.getChinesePy(name)
		    .toUpperCase(getLocale()));
	    result.add(temp);
	}
	resultJson = JSON.toJSONString(result);
	
	if (map.containsValue(operation1)) {
	    Set<String> kset = map.keySet();
	    for (String ks : kset) {
		if (map.get(ks).equals(operation1)) {
		    operation = ks;
		}
	    }
	}
	// 开始时间和结束时间
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
	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 50;
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;

	if (nickName != null && loginName != null && !nickName.equals("")
		&& !loginName.equals("")) {
	    setErrorInfo("帐号和角色名只能填写一个!");
	} else {

	    // 充值集合
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("nickName", nickName);
	    object.put("loginName", loginName);
	    object.put("beginTime", beginTime);
	    object.put("endTime", endTime);
	    object.put("operation", operation);
	    // object.put("plat", plat);//平台入口
	    object.put("serverId", getBaseAdminContext().getServerId());// 服务器Id
	    if(QUERY_ALL.equals(target)){
			target=null;
	    }
	    object.put("target", target);
	    object.put("beginNum", beginNum);
	    object.put("onePageNum", onePageNum);
	    object.put("handlerName", "QueryMoneyConsumeHandler");
	    try {
		if (loginName != null) {
		    object = CONNECTION.sendMsg(object);
		    if (object != null && !object.isEmpty()) {
			if (object.containsKey("operationClassList")
				&& object.containsKey("allNum")) {
			    allNum = (Integer) object.get("allNum");
			    operationClassList = (List<OperationClass>) object
				    .get("operationClassList");
			}
			pages = allNum % onePageNum > 0 ? allNum / onePageNum
				+ 1 : allNum / onePageNum;
			// 当前页设置
			if (currPageNO.intValue() > pages) {
			    currPageNO = pages;
			}
		    }
		    return "success";
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return "success";
    }

    public Integer getAllNum() {
	return allNum;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public Integer getConsumeTotal() {
	return consumeTotal;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public String getEndDate() {
	return endDate;
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

    public String getOperation() {
	return operation;
    }

    public String getOperation1() {
	return operation1;
    }

    public List<OperationClass> getOperationClassList() {
	return operationClassList;
    }

    public String getOperationDetail() {
	return operationDetail;
    }

    public Integer getPages() {
	return pages;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public String getResultJson() {
	return resultJson;
    }

    public String getTarget() {
	return target;
    }

    /**
     * 消费统计
     * */
    @SuppressWarnings("unchecked")
    @Action(value = "queryMoneyConsume_Total", results = { @Result(name = "success", location = "../../admin/pay/queryMoneyConsumeTotal.jsp") })
    public String queryMoneyConsumeTotal() throws Exception {
	List<Map> result = new ArrayList<Map>();
	Map<String, String> map = OperationLogConstant.maptype;
	
	Map<String,String> dictMap=queryDictMap(DictTypeConstant.GAME_FM, DictTypeConstant.OPERTION_TYPE);
	for (String key : map.keySet()) {
	    Map temp = new HashMap();
	    temp.put("code", key);
	    String name="";
	    if(dictMap.get(key)!=null&&!"".equals(dictMap.get(key))){
		name = dictMap.get(key);
	    }else{
		name = map.get(key);
	    }
	    temp.put("name", name);
	    temp.put("help", ChineseCharToEn.getChinesePy(name)
		    .toUpperCase(getLocale()));
	    result.add(temp);
	}
	resultJson = JSON.toJSONString(result);
	if (map.containsValue(operation1)) {
	    Set<String> kset = map.keySet();
	    for (String ks : kset) {
		if (map.get(ks).equals(operation1)) {
		    operation = ks;
		}
	    }
	}

	// 开始时间和结束时间
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

	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 50;
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;

	if (nickName != null && loginName != null && !nickName.equals("")
		&& !loginName.equals("")) {
	    setErrorInfo("帐号和角色名只能填写一个!");
	} else {
	    // 充值集合
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("nickName", nickName);
	    object.put("loginName", loginName);
	    object.put("beginTime", beginTime);
	    object.put("endTime", endTime);
	    if(QUERY_ALL.equals(operation)||StringUtils.isBlank(operation)){
		operation = null;
	    }
	    object.put("operation", operation);
	    if(QUERY_ALL.equals(target)||StringUtils.isBlank(target)){
		target=null;
	    }
	    object.put("target", target);
	    
	    object.put("beginNum", beginNum);
	    object.put("onePageNum", onePageNum);
	    // object.put("plat", plat);
	    object.put("handlerName", "QueryMoneyConsumeHandler");
	    object.put("serverId", getBaseAdminContext().getServerId());
	    try {
		if (loginName != null) {
		    object = CONNECTION.sendMsg(object);
		    if (object != null && !object.isEmpty()) {
			if (object.containsKey("operationClassList")
				&& object.containsKey("allNum")) {
			    allNum = (Integer) object.get("allNum");
			    operationClassList = (List<OperationClass>) object
				    .get("operationClassList");
			    consumeTotal = Integer.valueOf(object.get(
				    "consumeTotal").toString());// (Integer)
								// object.get("consumeTotal");
			}
			pages = allNum % onePageNum > 0 ? allNum / onePageNum
				+ 1 : allNum / onePageNum;
			// 当前页设置
			if (currPageNO.intValue() > pages) {
			    currPageNO = pages;
			}
		    }
		    return "success";
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return "success";
    }
    
    
    /**
     * 导出日志处理
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Action(value = "queryMoneyConsume_date", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "queryMoneyConsume", "namespace", "/admin/pay",
	    "actionMsg", "${actionMsg}" }) })
    public String exportData() throws Exception {
	// 开始时间和结束时间
	Date beginTime = new Date();
	Date endTime = new Date();

	if (beginDate == null || "".equals(beginDate)) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	   
	} else {
	    beginTime = DateService.getDateFirstTime(beginDate);
	}

	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	   
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}
	if(target==null || target.equals("")||(!target.equals("addGold")&&!target.equals("useGold"))){
	    
	    setActionMsg("只支持导出增加魔晶和消耗魔晶");
	    return SUCCESS;
	}
	
	
	onePageNum = 10000; //导出最大值 10000条
	
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime); 	// 开始时间
	object.put("endTime", endTime);		// 结束时间
	object.put("beginNum", 0);		// 
	object.put("onePageNum", onePageNum);		// 
	object.put("target", target);		// 
	object.put("handlerName", "QueryMoneyConsumeHandler");
	object.put("methodName", "exportExcel");
	object = CONNECTION.sendMsg(object);
	
	Integer allNum=(Integer) object.get("allNum");
	if(allNum!=null && allNum>100){
	    // 数据量过大  请缩小时间范围
	    setActionMsg("数据量过大  请缩小时间范围");
	    return SUCCESS;
	    
	}
	
	operationClassList = (List<OperationClass>) object.get("operationClassList");
	String[] titles = { "操作时间", "消耗数量", "操作类型", "操作详细说明", "帐号", "角色名",
		 "目标2", "数量2", "玩家等级", "平台标识" };
	String[] elements = { "createTime", "number", "operation", "remark",
		"loginName", "nickName",  "target2", "number2",
		"level", "ptFlag" };
	String fileName = beginDate + "-" + endDate + "物品流向查询";
	String sheetName = "OperationLog";
	String FileType = ".xls";
	ActionContext context = ActionContext.getContext();
	HttpServletResponse response = (HttpServletResponse) context
		.get(StrutsStatics.HTTP_RESPONSE);
	response.setCharacterEncoding("utf-8");
	
	ExportExcel.export(titles, elements, operationClassList, fileName,
		sheetName, response, OperationClass.class, FileType);
	return null;
    }
    

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setConsumeTotal(Integer consumeTotal) {
	this.consumeTotal = consumeTotal;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
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

    public void setOperation(String operation) {
	this.operation = operation;
    }

    public void setOperation1(String operation1) {
	this.operation1 = operation1;
    }

    public void setOperationDetail(String operationDetail) {
	this.operationDetail = operationDetail;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setResultJson(String resultJson) {
	this.resultJson = resultJson;
    }

    public void setTarget(String target) {
	this.target = target;
    }
    /**
     * 查询数据字典map
     * 
     * @param gameId
     * @param type
     * @return
     */
    private  Map<String, String> queryDictMap(Integer gameId, Integer type) {
	IOssDictService dictService = (IOssDictService) SpringService
		.getBean("ossDictService");
	List<OssDictData> dictList = dictService.getOssDictDataList(gameId,
		type);
	Map<String, String> dictMap = new HashMap<String, String>();
	if (dictList != null) {
	    for (OssDictData dictData : dictList) {
		dictMap.put(dictData.getDictValue(),dictData.getDictName());
	    }
	}
	return dictMap;
    }
    private  Map<String, String> queryDictValueMap(Integer gameId, Integer type) {
   	IOssDictService dictService = (IOssDictService) SpringService
   		.getBean("ossDictService");
   	List<OssDictData> dictList = dictService.getOssDictDataList(gameId,
   		type);
   	Map<String, String> dictMap = new HashMap<String, String>();
   	if (dictList != null) {
   	    for (OssDictData dictData : dictList) {
   		dictMap.put(dictData.getDictName(),dictData.getDictValue());
   	    }
   	}
   	return dictMap;
       }
    
    @Action(value = "queryMoneyConsume_List", results = { @Result(name = "success", location = "../../admin/pay/queryMoneyConsume_list.jsp") })
    public String queryConsumeList() throws Exception {
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

	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 50;
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;

	if (nickName != null && loginName != null && !nickName.equals("")
		&& !loginName.equals("")) {
	    setErrorInfo("帐号和角色名只能填写一个!");
	    return "success";
	} 
	Map<String, String> dictMap = queryDictValueMap(1,111);
	Map<String, String> dictMap2 = queryDictValueMap(1,112);
	int dd = getBaseAdminContext().getCurrentOssServer().getId();
	String dateBase = dictMap.get(getBaseAdminContext().getCurrentOssServer().getId()+"");
	String url = dateBase ;    
	String username = dictMap2.get("username") ;   
	String password = dictMap2.get("password") ; 
	try {
//		List<OperationClass> operationClassList = new ArrayList<OperationClass>();
		
		//启用jdbc查询
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = (Connection) DriverManager.getConnection(url, username, password);
		StringBuffer sql =new StringBuffer("SELECT operation_Log_Id as operationLogId, game_id AS gameId, "+
				"	pt_id AS ptId, area_id AS areaId, player_id AS playerId,"+
		"login_name AS loginName, player_base_id AS playerBaseId, nick_name AS nickName , "+
		"level AS level, item_id AS itemId, item_name AS itemName,"+
		"flow_type AS flowType, number AS number, operation AS operation,"+
		"operation_detail AS operationDetail, create_time AS createTime"+
		" from t_operation_log_" + getTableSuffixByTypeAndDate(1,beginTime)+" where 1=1");
		if(operation!=null&&!"".equals(operation)){
			sql.append(" and operation='"+operation+"'");
		}
		if(beginTime!=null){
			sql.append(" and CREATE_TIME>='"+beginDate+" 00:00:00'");
		}
		if(endTime!=null){
			sql.append(" and CREATE_TIME<='"+endDate+" 23:59:59'");
		}
		//if(target!=null&&!"".equals(target)){
		//	sql.append(" and flow_type="+target);
		//}
		if(nickName!=null&&!"".equals(nickName)){
		    sql.append(" and nick_Name='"+nickName+"'");
		}
		if(loginName!=null&&!"".equals(loginName)){
		    sql.append(" and login_Name='"+loginName+"'");
		}
		if(flowType!=null&&flowType>0){
			sql.append(" and flow_type="+flowType);
		}
		if(itemId!=null&&itemId>0){
			sql.append(" and item_Id="+itemId);
		}
		Statement   st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(sql.toString());    //执行sql查询语句，返回查询数据的结果集 
		rs.last(); 
		allNum=rs.getRow();
		sql.append(" ORDER BY CREATE_TIME DESC LIMIT "+beginNum+","+onePageNum);
		rs = st.executeQuery(sql.toString());   
		operationClassList= new ArrayList<OperationClass>();
		while (rs.next()) { // 判断是否还有下一个数据  
		    OperationClass operationClass = new OperationClass();
		    operationClass.setNumber(rs.getInt("number"));
		    operationClass.setOperation(rs.getString("operation"));
		    operationClass.setOperationDetail(rs.getString("operationDetail"));
		   // operationClass.setCreateTime(rs.getDate("createTime"));
		    if(rs.getTimestamp("createTime")!=null){
			operationClass.setCreateTime(DateService.changeDateFormat(rs.getTimestamp("createTime"), "yyyy-MM-dd hh:mm:ss"));
		    }
		    operationClass.setLevel(rs.getInt("level"));
		    operationClass.setNickName(rs.getString("nickName"));
		    operationClass.setLoginName(rs.getString("loginName"));
		    operationClass.setTarget2(rs.getString("itemName"));
		    operationClass.setPtFlag(rs.getString("ptId"));
		    operationClassList.add(operationClass); 
      
		}  
		pages = allNum % onePageNum > 0 ? allNum / onePageNum
			+ 1 : allNum / onePageNum;
		// 当前页设置
		if (currPageNO.intValue() > pages) {
		    currPageNO = pages;
		}
		
	} catch(Exception e) {
	  logger.error("数据库地址："+url+";用户名："+username+";账户名"+password,e);
	}
   	return "success";
       }
    
    
    /**
     * 消费统计
     * */
    @SuppressWarnings("unchecked")
    @Action(value = "queryMoneyConsume_TotalList", results = { @Result(name = "success", location = "../../admin/pay/queryMoneyConsumeTotal_list.jsp") })
    public String queryMoneyConsumeTotalList() throws Exception {
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

	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 50;
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;

	if (nickName != null && loginName != null && !nickName.equals("")
		&& !loginName.equals("")) {
	    setErrorInfo("帐号和角色名只能填写一个!");
	    return "success";
	} 
	Map<String, String> dictMap = queryDictValueMap(1,111);
	Map<String, String> dictMap2 = queryDictValueMap(1,112);
	int dd = getBaseAdminContext().getCurrentOssServer().getId();
	String dateBase = dictMap.get(getBaseAdminContext().getCurrentOssServer().getId()+"");
	String url = dateBase ;    
	String username = dictMap2.get("username") ;   
	String password = dictMap2.get("password") ;
	try {
//		List<OperationClass> operationClassList = new ArrayList<OperationClass>();
		 
		System.out.println(username+"----"+username+dateBase);
		//启用jdbc查询
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = (Connection) DriverManager.getConnection(url, username, password);
		StringBuffer sql =new StringBuffer("SELECT operation_Log_Id as operationLogId, game_id AS gameId, "+
				"	pt_id AS ptId, area_id AS areaId, player_id AS playerId,"+
		"login_name AS loginName, player_base_id AS playerBaseId, nick_name AS nickName , "+
		"level AS level, item_id AS itemId, item_name AS itemName,"+
		"flow_type AS flowType, number AS number, operation AS operation,"+
		"operation_detail AS operationDetail, create_time AS createTime"+
		" from t_operation_log_" + getTableSuffixByTypeAndDate(1,beginTime)+" where 1=1");
		if(operation!=null&&!"".equals(operation)){
			sql.append(" and operation='"+operation+"'");
		}
		if(beginTime!=null){
			sql.append(" and CREATE_TIME>='"+beginDate+" 00:00:00'");
		}
		if(endTime!=null){
			sql.append(" and CREATE_TIME<='"+endDate+" 23:59:59'");
		}
		if(flowType!=null&&flowType>0){
			sql.append(" and flow_type="+flowType);
		}
		if(itemId!=null&&itemId>0){
			sql.append(" and item_Id="+itemId);
		}
		//if(target!=null&&!"".equals(target)){
		//	sql.append(" and flow_type="+target);
		//}
		if(nickName!=null&&!"".equals(nickName)){
		    sql.append(" and nick_Name='"+nickName+"'");
		}
		if(loginName!=null&&!"".equals(loginName)){
		    sql.append(" and login_Name='"+loginName+"'");
		}
		Statement   st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(sql.toString());    //执行sql查询语句，返回查询数据的结果集 
		rs.last(); 
		allNum=rs.getRow();
		sql.append(" ORDER BY CREATE_TIME DESC LIMIT "+beginNum+","+onePageNum);
		rs = st.executeQuery(sql.toString());   
		operationClassList= new ArrayList<OperationClass>();
		while (rs.next()) { // 判断是否还有下一个数据  
		    OperationClass operationClass = new OperationClass();
		    operationClass.setNumber(rs.getInt("number"));
		    operationClass.setOperation(rs.getString("operation"));
		    operationClass.setOperationDetail(rs.getString("operationDetail"));
		    if(rs.getTimestamp("createTime")!=null){
			operationClass.setCreateTime(DateService.changeDateFormat(rs.getTimestamp("createTime"), "yyyy-MM-dd hh:mm:ss"));
		    }
		  //  System.out.println(DateService.changeDateFormat(rs.getTimestamp("createTime"), "yyyy-MM-dd hh:mm:ss"));
		   
		    operationClass.setLevel(rs.getInt("level"));
		    operationClass.setNickName(rs.getString("nickName"));
		    operationClass.setLoginName(rs.getString("loginName"));
		    operationClass.setTarget2(rs.getString("itemName"));
		    operationClass.setPtFlag(rs.getString("ptId"));
		    operationClassList.add(operationClass); 
      
		}  
		pages = allNum % onePageNum > 0 ? allNum / onePageNum
			+ 1 : allNum / onePageNum;
		// 当前页设置
		if (currPageNO.intValue() > pages) {
		    currPageNO = pages;
		}
		
	} catch(Exception e) {
	    logger.error("数据库地址："+url+";用户名："+username+";账户名"+password,e);
	}
   	return "success";
    }
    public static String getTableSuffixByTypeAndDate(int type,Date date){
	Calendar c = Calendar.getInstance();
	c.setTime(date);
	String tableSuffix = "";
	
	if ( type == 1 ) {
		String strMonth = c.get( Calendar.MONTH ) +1 + "";
		if ( strMonth.length() == 1 ) strMonth = "0"+strMonth;
		
		tableSuffix =  c.get( Calendar.YEAR ) + "" + ( strMonth + "01");
		return tableSuffix;
	}
	else if ( type == 2 ) {
    	int a = c.get(Calendar.DAY_OF_WEEK);
    	c.add( Calendar.DATE, 2-a ); //本周的第一天
    	String strMonth = c.get( Calendar.MONTH ) +1 + "";
		if ( strMonth.length() == 1 ) strMonth = "0"+strMonth;
		String strDay = c.get(Calendar.DAY_OF_MONTH) + "";
		if ( strDay.length() == 1 ) strDay = "0" + strDay;
    	
		tableSuffix = c.get( Calendar.YEAR ) + "" + ( strMonth + strDay );
		return tableSuffix;
	}
	else if ( type == 3 ) {
		String strMonth = c.get( Calendar.MONTH ) +1 + "";
		if ( strMonth.length() == 1 ) strMonth = "0"+strMonth;
		
		tableSuffix =  c.get( Calendar.YEAR ) + "" + ( strMonth + c.get( Calendar.DAY_OF_MONTH ) +1);
		return tableSuffix;
	}
	 
	return tableSuffix;
    }

    
    /**
     * 消费统计
     * */
    @SuppressWarnings("unchecked")
    @Action(value = "queryMoneyConsume_operationList", results = { @Result(name = "success", location = "../../admin/pay/queryOperation_List.jsp") })
    public String queryOperationList() throws Exception {
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

	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 50;
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;

	if (nickName != null && loginName != null && !nickName.equals("")
		&& !loginName.equals("")) {
	    setErrorInfo("帐号和角色名只能填写一个!");
	    return "success";
	} 
//	OssServer ossServer = getBaseAdminContext().getCurrentOssServer();
//	List<SendBaseProperty> baseProperty = new ArrayList<SendBaseProperty>();
//	List<SendBaseProperty> sendBaseProperty = Global
//		.getPropertyList(ossServer.getUrl(),getBaseAdminContext().getProject().getProjectId());
//	List<SendBaseProperty> sendBase = Global.getEquipList(ossServer
//		.getUrl());
//	baseProperty.addAll(sendBaseProperty);
//	baseProperty.addAll(sendBase);
//
//	List<Map> result = new ArrayList<Map>();
//	// Map<String, String> map = new HashMap<String, String>();
//	for (SendBaseProperty key : baseProperty) {
//	    Map temp = new HashMap();
//	    temp.put("code", key.getValue());
//	    temp.put("name", key.getName() + "-" + key.getValue());
//	    temp.put("help", ChineseCharToEn.getChinesePy(key.getName())
//		    .toUpperCase(getLocale()));
//	    result.add(temp);
//	}
//	resultJson = JSON.toJSONString(result);
//
//	List<OssDictData> dictDateList = ossDictService.getOssDictDataList(
//		getBaseAdminContext().getProject().getProjectId(),
//		DictTypeConstant.OPERTION_TYPE);
//	List<Map> result2 = new ArrayList<Map>();
//	for (OssDictData ossDictData : dictDateList) {
//	    Map temp1 = new HashMap();
//	    temp1.put("code", ossDictData.getDictValue());
//	    temp1.put("name", ossDictData.getDictName());
//	    temp1.put("help", ChineseCharToEn.getChinesePy(ossDictData
//		    .getDictName().toUpperCase(getLocale())));
//	    result2.add(temp1);
//	}
//	resultJson2 = JSON.toJSONString(result2);

	String tableName = "t_operation_log_"+DateService
		.getTableSuffixByTypeAndDate(1, beginTime);

	beginNum = (currPageNO - 1) * onePageNum;
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginDate", beginTime);
	object.put("endDate", endTime);
	object.put("loginName", loginName);
	object.put("nickName", nickName);
	object.put("operation", operation);
	object.put("itemId", itemId);
	object.put("flowType", flowType);
	object.put("tableName", tableName);
	object.put("onePageNum", onePageNum);
	object.put("beginNum", beginNum);
	object.put("methodName", "queryConsumeList");
	object.put("handlerName", "QueryMoneyConsumeHandler");
	object = CONNECTION.sendMsg(object);
	operationLogList = (List<OssOperationLog>) object
		    .get("ossOperationLogList");
	allNum = (Integer) object.get("allNum");

	if (operationLogList == null || operationLogList.size() == 0) {
	    allNum = 0;
	}
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	return SUCCESS;
	
    }
    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public List<OssOperationLog> getOperationLogList() {
        return operationLogList;
    }

    public void setOperationLogList(List<OssOperationLog> operationLogList) {
        this.operationLogList = operationLogList;
    }

    public void setOperationClassList(List<OperationClass> operationClassList) {
        this.operationClassList = operationClassList;
    }
    
}
