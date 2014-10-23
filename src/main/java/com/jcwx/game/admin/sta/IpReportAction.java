package com.jcwx.game.admin.sta;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
import com.jcwx.game.domain.IpReport;
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.service.oss.IALoginLogService;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.service.oss.IOssServerService;
import com.jcwx.game.util.ServerListToMap;

/**
 * ip去重统计
 * 
 * @author csp
 * 
 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class IpReportAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 大区 ID
    private String areaId;
    // 大区 ID列表多选
    private String[] areas;

    // 开始时间
    private String beginDate;

    // 结束时间
    private String endDate;
    // 总ip数
    private Integer ipCount;
    // Ip统计类
    private List<IpReport> ipReport;

    // 总登录数
    private Integer loginCount;

    @Autowired
    private IALoginLogService loginLogService;

    private Integer operationId;

    /** 查询平台标识 */
    private List<OssOperation> ossOperationList;
    @Autowired
    private IOssOperationService ossOperationService;

    /** 服务器列表 */
    private List<OssServer> ossServerList;

    @Autowired
    private IOssServerService ossServerService;
    // 平台标识
    private String ptId;
    private Map<String, String> serverMap;

    public String getAreaId() {
	return areaId;
    }

    public String[] getAreas() {
	return areas;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public Integer getIpCount() {
	return ipCount;
    }

    public List<IpReport> getIpReport() {
	return ipReport;
    }

    public Integer getLoginCount() {
	return loginCount;
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

    public String getPtId() {
	return ptId;
    }

    public Map<String, String> getServerMap() {
	return serverMap;
    }

    @Action(value = "ipReport_index", results = { @Result(name = "success", location = "../../admin/sta/ip_report.jsp") })
    public String index() throws Exception {
	try {
	    Date beginTime = new Date();
	    Date endTime = new Date();

	    if (beginDate == null) {
		beginTime = DateService.getCurrentDayFirstUtilDate();
		beginDate = DateService.getDateFormatStr(beginTime,
			"yyyy-MM-dd");
	    } else {
		beginTime = DateService.getDateFirstTime(beginDate);
	    }
	    if (endDate == null) {
		endTime = DateService.getCurrentDayLastUtilDate();
		endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	    } else {
		endTime = DateService.getDateLastTime(endDate);
	    }
	    // 获取所有平台标识
	    this.ossOperationList = ossOperationService.getOssOperationList();

	    if (operationId == null || operationId == -1) {
		operationId = null;
	    }

	    if (areaId == null || areaId.equals("-1")) {
		areaId = null;
	    }

	    if (ptId == null || ptId.equals("-1")) {
		ptId = null;
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

	    String areaId = ServerListToMap.arrayToString(list, areas, ptId);
	    if (areas != null && areas.length == 1) {
		areas = areas[0].split(",");
	    }

	    ipReport = loginLogService.ipReport(getBaseAdminContext()
		    .getProject().getProjectId(), areaId, ptId, beginTime,
		    endTime);
	    Integer loginNum = 0;
	    Integer ipNum = 0;
	    for (int i = 0; i < ipReport.size(); i++) {
		loginNum += ipReport.get(i).getLoginNum();
		ipNum += ipReport.get(i).getIpNum();
	    }
	    loginCount = loginNum;
	    ipCount = ipNum;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "ipReport_queryOssServerList")
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

    public void setAreaId(String areaId) {
	this.areaId = areaId;
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

    public void setIpCount(Integer ipCount) {
	this.ipCount = ipCount;
    }

    public void setIpReport(List<IpReport> ipReport) {
	this.ipReport = ipReport;
    }

    public void setLoginCount(Integer loginCount) {
	this.loginCount = loginCount;
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

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

    public void setServerMap(Map<String, String> serverMap) {
	this.serverMap = serverMap;
    }

}
