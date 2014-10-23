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
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.LoginReport;
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.service.oss.IALoginLogService;
import com.jcwx.game.service.oss.IKPIReportService;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.service.oss.IOssServerService;
import com.jcwx.game.util.ServerListToMap;

/**
 * 在线与注册：登录统计
 * */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class QueryLoginReportAction extends BasalAction {

    @Autowired
    private IALoginLogService aLoginLogService;

    // 大区 ID列表多选
    private String[] areas;

    /** 开始时间 */
    private String beginDate;

    /** 结束时间 */
    private String endDate;

    private List<LoginReport> loginReport;

    private Integer operationId;

    /** 查询平台标识 */
    private List<OssOperation> ossOperationList;

    @Autowired
    private IOssOperationService ossOperationService;

    /** 大区Id */
    private String ossServerId;

    /** 服务器列表 */
    private List<OssServer> ossServerList;;
    @Autowired
    private IOssServerService ossServerService;
    @Autowired
    private IKPIReportService kpiService;
    
    private Map<String, Object> loginSum;

    /** 平台标识 */
    private String ptCode;

    private LoginReport report = new LoginReport();

    private Map<String, String> serverMap;

    public String[] getAreas() {
	return areas;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<LoginReport> getLoginReport() {
	return loginReport;
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

    public String getPtCode() {
	return ptCode;
    }

    public LoginReport getReport() {
	return report;
    }

    public Map<String, String> getServerMap() {
	return serverMap;
    }

    @Action(value = "queryLoginReport_ajax")
    public String queryOssServerList() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	ossServerList = new ArrayList<OssServer>();
	List<OssServer> list = getBaseAdminContext().getOssServers();
	if (operationId != null && operationId.intValue() != -1) {
	    for (OssServer ossServer : list) {
		if (ossServer.getServerId() == operationId.intValue()) {
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

    @Action(value = "queryLoginReport_report", results = { @Result(name = "success", location = "../../admin/sta/querylogin_report.jsp") })
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
	List<OssServer> list = getBaseAdminContext().getOssServers();
	if (operationId != null && !"".equals(operationId)) {
	    for (OssServer ossServer : list) {
		if (ossServer.getServerId().equals(operationId)) {
		    ossServerList.add(ossServer);
		}
	    }
	} else {
	    ossServerList = list;
	}

	serverMap = ServerListToMap.convert(ossServerList);

	String areaId = ServerListToMap.arrayToString(list, areas, ptCode);
	if (areas != null && areas.length == 1) {
	    areas = areas[0].split(",");
	}

	// 获取所有平台标识
	this.ossOperationList = ossOperationService.getOssOperationList();

	loginReport = aLoginLogService.queryLoginReport(beginTime, endTime,
		getBaseAdminContext().getProject().getProjectId(), areaId,
		ptCode);

	Integer loginNum = 0;
	Integer playerNum = 0;
	for (int i = 0; i < loginReport.size(); i++) {
	    loginNum += loginReport.get(i).getLoginNum();
	    playerNum += loginReport.get(i).getPlayerNum();
	    // loginNum++;
	    // playerNum++;
	}
	report.setLoginNum(loginNum);
	report.setPlayerNum(playerNum);
	Map<String,Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate+" 00:00:00");
	params.put("endDate", endDate+" 23:59:59");
	params.put("gameId", getBaseAdminContext().getProject().getProjectId());
	params.put("areaIds", areaId);
	params.put("ptCode", ptCode);
	loginSum = kpiService.querySumLogin(params);
	return "success";
    }

    public void setAreas(String[] areas) {
	this.areas = areas;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setLoginReport(List<LoginReport> loginReport) {
	this.loginReport = loginReport;
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

    public void setPtCode(String ptCode) {
	this.ptCode = ptCode;
    }

    public void setReport(LoginReport report) {
	this.report = report;
    }

    public void setServerMap(Map<String, String> serverMap) {
	this.serverMap = serverMap;
    }

    public Map<String, Object> getLoginSum() {
        return loginSum;
    }

    public void setLoginSum(Map<String, Object> loginSum) {
        this.loginSum = loginSum;
    }

}
