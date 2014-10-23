package com.jcwx.game.admin.pay;

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

import com.alibaba.fastjson.JSON;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.constants.DictTypeConstant;
import com.jcwx.game.domain.OssDictData;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.OssOperationLog;
import com.jcwx.game.http.domain.SendBaseProperty;
import com.jcwx.game.service.oss.IOssDictService;
import com.jcwx.game.util.ChineseCharToEn;

import com.jcwx.game.util.ExportExcel;
import com.jcwx.game.web.Global;
import com.opensymphony.xwork2.ActionContext;

/** 玩家日志查询 */
@ParentPackage("base")
@Namespace("/zhxy/pay")
@ResultPath("/")
public class ZHQueryPlayerLogAction extends BasalAction {

    private static final long serialVersionUID = -70539059362263612L;

    /** 总记录数 */
    private Integer allNum;
    // 开始时间
    private String beginDate;

    /** 起始页号 */
    private Integer beginNum;

    /** 当前页数 */
    private Integer currPageNO;

    // 结束时间
    private String endDate;

    /** 每页记录数 */
    private Integer onePageNum;

    private OssOperationLog operationLog;

    private String operationName;

    @Autowired
    private IOssDictService ossDictService;

    private List<OssOperationLog> ossOperationLogList;

    /** 总页数 */
    private Integer pages;

    private String resultJson;

    private String resultJson2;

    @SuppressWarnings("unchecked")
    @Action(value = "ZHQueryPlayerLog_index", results = { @Result(name = "success", location = "../../admin/pay/ZHbrowseLogList.jsp") })
    public String browsePlayerInfo() throws Exception {
	// 开始时间和结束时间
	Date beginTime = new Date();
	Date endTime = new Date();

	if (beginDate == null || "".equals(beginDate)) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	    beginDate = DateService.getDateFormatStr(beginTime,
		    "yyyy-MM-dd HH:mm:ss");

	} else {
	    beginTime = DateService.getDateFirstTime(beginDate);
	}

	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime,
		    "yyyy-MM-dd HH:mm:ss");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 20; // 每页20条记录
	}

	OssServer ossServer = getBaseAdminContext().getCurrentOssServer();
	List<SendBaseProperty> baseProperty = new ArrayList<SendBaseProperty>();
	List<SendBaseProperty> sendBaseProperty = Global
		.getPropertyList(ossServer.getUrl(),getBaseAdminContext().getProject().getProjectId());
	List<SendBaseProperty> sendBase = Global.getEquipList(ossServer
		.getUrl());
	baseProperty.addAll(sendBaseProperty);
	baseProperty.addAll(sendBase);

	List<Map> result = new ArrayList<Map>();
	// Map<String, String> map = new HashMap<String, String>();
	for (SendBaseProperty key : baseProperty) {
	    Map temp = new HashMap();
	    temp.put("code", key.getValue());
	    temp.put("name", key.getName() + "-" + key.getValue());
	    temp.put("help", ChineseCharToEn.getChinesePy(key.getName())
		    .toUpperCase(getLocale()));
	    result.add(temp);
	}
	resultJson = JSON.toJSONString(result);

	List<OssDictData> dictDateList = ossDictService.getOssDictDataList(
		getBaseAdminContext().getProject().getProjectId(),
		DictTypeConstant.OPERTION_TYPE);
	List<Map> result2 = new ArrayList<Map>();
	for (OssDictData ossDictData : dictDateList) {
	    Map temp1 = new HashMap();
	    temp1.put("code", ossDictData.getDictValue());
	    temp1.put("name", ossDictData.getDictName());
	    temp1.put("help", ChineseCharToEn.getChinesePy(ossDictData
		    .getDictName().toUpperCase(getLocale())));
	    result2.add(temp1);
	}
	resultJson2 = JSON.toJSONString(result2);

	String tableName = DateService
		.getTableSuffixByTypeAndDate(1, beginTime);

	beginNum = (currPageNO - 1) * onePageNum;
	if (operationLog != null) {
	    if (operationLog.getFlowType() == -1
		    || operationLog.getFlowType().equals("-1")) {
		operationLog.setFlowType(null);
	    }
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("beginDate", beginTime);
	    object.put("endDate", endTime);
	    object.put("loginName", operationLog.getLoginName());
	    object.put("nickName", operationLog.getNickName());
	    object.put("operation", operationLog.getOperation());
	    object.put("itemId", operationLog.getItemId());
	    object.put("flowType", operationLog.getFlowType());
	    object.put("tableName", tableName);
	    object.put("onePageNum", onePageNum);
	    object.put("beginNum", beginNum);
	    object.put("handlerName", "OperationLogHandler");
	    object = CONNECTION.sendMsg(object);
	    ossOperationLogList = (List<OssOperationLog>) object
		    .get("ossOperationLogList");
	    allNum = (Integer) object.get("allNum");

	}
	if (ossOperationLogList == null || ossOperationLogList.size() == 0) {
	    allNum = 0;
	}
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    @Action(value = "ZHQueryPlayerLog_data")
    public void exportData() throws Exception {
	// 开始时间和结束时间
	Date beginTime = new Date();
	Date endTime = new Date();

	if (beginDate == null || "".equals(beginDate)) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	    beginDate = DateService.getDateFormatStr(beginTime,
		    "yyyy-MM-dd HH:mm:ss");

	} else {
	    beginTime = DateService.getDateFirstTime(beginDate);
	}

	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime,
		    "yyyy-MM-dd HH:mm:ss");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 20; // 每页20条记录
	}

	String tableName = DateService
		.getTableSuffixByTypeAndDate(1, beginTime);
	beginNum = (currPageNO - 1) * onePageNum;
	if (operationLog.getFlowType() == -1
		|| operationLog.getFlowType().equals("-1")) {
	    operationLog.setFlowType(null);
	}
	Map<String, Object> object = new HashMap<String, Object>();
	Map<String, Object> result = new HashMap<String, Object>();
	object.put("beginDate", beginTime);
	object.put("endDate", endTime);
	object.put("loginName", operationLog.getLoginName());
	object.put("nickName", operationLog.getNickName());
	object.put("operation", operationLog.getOperation());
	object.put("itemId", operationLog.getItemId());
	object.put("flowType", operationLog.getFlowType());
	object.put("tableName", tableName);
	object.put("onePageNum", onePageNum);
	object.put("beginNum", beginNum);
	object.put("onePageNum", 10000);
	object.put("methodName", "queryPlayerLogExportData");
	object.put("handlerName", "OperationLogHandler");
	object = CONNECTION.sendMsg(object);	
	List<OssOperationLog> ossOperationLogList = (List<OssOperationLog>) object.get("ossOperationLogList");		
	String[] titles = { "平台标识", "玩家ID", "账号", "角色ID", "角色昵称", "当前角色等级",
		"物品ID", "物品名称", "增减类型 ", "数量", "操作类型", "操作说明", "创建时间" };
	String[] elements = { "ptId", "playerId", "loginName", "playerBaseId",
		"nickName", "level", "itemId", "itemName", "flowType",
		"number", "operation", "operationDetail", "createTime" };
	String fileName = beginDate + "-" + endDate + "物品流向查询";
	String sheetName = "物品流向查询";
	String FileType = ".xls";
	ActionContext context = ActionContext.getContext();
	HttpServletResponse response = (HttpServletResponse) context
		.get(StrutsStatics.HTTP_RESPONSE);
	response.setCharacterEncoding("utf-8");

	ExportExcel.export(titles, elements, ossOperationLogList, fileName,
		sheetName, response, OssOperationLog.class, FileType);
//	return null;
    }
    @Action(value = "ZHQueryPlayerLog_vilidateNum")
    public void vilidateNum() throws Exception{
	// 开始时间和结束时间
		Date beginTime = new Date();
		Date endTime = new Date();

		if (beginDate == null || "".equals(beginDate)) {
		    beginTime = DateService.getCurrentMonthFirstDay();
		    beginDate = DateService.getDateFormatStr(beginTime,
			    "yyyy-MM-dd HH:mm:ss");

		} else {
		    beginTime = DateService.getDateFirstTime(beginDate);
		}

		if (endDate == null || "".equals(endDate)) {
		    endTime = DateService.getCurrentDayLastUtilDate();
		    endDate = DateService.getDateFormatStr(endTime,
			    "yyyy-MM-dd HH:mm:ss");
		} else {
		    endTime = DateService.getDateLastTime(endDate);
		}

		if (currPageNO == null || currPageNO.intValue() == 0) {
		    currPageNO = 1;
		}
		if (onePageNum == null || onePageNum.intValue() == 0) {
		    onePageNum = 20; // 每页20条记录
		}

		String tableName = DateService
			.getTableSuffixByTypeAndDate(1, beginTime);
		beginNum = (currPageNO - 1) * onePageNum;
		if (operationLog.getFlowType() == -1
			|| operationLog.getFlowType().equals("-1")) {
		    operationLog.setFlowType(null);
		}
		Map<String, Object> object = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		object.put("beginDate", beginTime);
		object.put("endDate", endTime);
		object.put("loginName", operationLog.getLoginName());
		object.put("nickName", operationLog.getNickName());
		object.put("operation", operationLog.getOperation());
		object.put("itemId", operationLog.getItemId());
		object.put("flowType", operationLog.getFlowType());
		object.put("tableName", tableName);
		object.put("onePageNum", onePageNum);
		object.put("beginNum", beginNum);
		object.put("methodName", "queryAllNum");
		object.put("handlerName", "OperationLogHandler");
		result = CONNECTION.sendMsg(object);
		Integer allNum =(Integer) result.get("allNum");
		if(allNum>10000){
		    getPageMessage().setStatusCode(100);
		    getPageMessage().setMessage("导出的数据大于10000条，请选择查询条件再进行导出！");
		}
		getJSONResponse().responseJson(getPageMessage());
    }
    public Integer getAllNum() {
	return allNum;
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

    public String getEndDate() {
	return endDate;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public OssOperationLog getOperationLog() {
	return operationLog;
    }

    public String getOperationName() {
	return operationName;
    }

    public List<OssOperationLog> getOssOperationLogList() {
	return ossOperationLogList;
    }

    public Integer getPages() {
	return pages;
    }

    public String getResultJson() {
	return resultJson;
    }

    public String getResultJson2() {
	return resultJson2;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
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

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOperationLog(OssOperationLog operationLog) {
	this.operationLog = operationLog;
    }

    public void setOperationName(String operationName) {
	this.operationName = operationName;
    }

    public void setOssOperationLogList(List<OssOperationLog> ossOperationLogList) {
	this.ossOperationLogList = ossOperationLogList;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setResultJson(String resultJson) {
	this.resultJson = resultJson;
    }

    public void setResultJson2(String resultJson2) {
	this.resultJson2 = resultJson2;
    }

}
