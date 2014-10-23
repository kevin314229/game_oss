package com.jcwx.game.admin.online;

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
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.AverageOnline;
import com.jcwx.game.service.oss.IALoginLogService;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.service.oss.IOssServerService;
import com.jcwx.game.util.ServerListToMap;

/**
 * 大数据分析 查询平均在线时长
 * */
@ParentPackage("base")
@Namespace("/admin/online")
@ResultPath("/")
public class getOnlineCountTimeAction extends BasalAction {

    /**
          * 
          */
    private static final long serialVersionUID = 1L;

    @Autowired
    private IALoginLogService aLoginService;

    // 大区 ID列表多选
    private String[] areas;

    private List<AverageOnline> averageList = new ArrayList<AverageOnline>();

    private String beginDate;

    private String endDate;

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

    /** 平台标识 */
    private String ptCode;;

    private Map<String, String> serverMap;

    public IALoginLogService getaLoginService() {
	return aLoginService;
    }

    public String[] getAreas() {
	return areas;
    }

    public List<AverageOnline> getAverageList() {
	return averageList;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    @Action(value = "getOnlineCountTime_index", results = { @Result(name = "success", location = "../../admin/online/getOnlineCountTime.jsp") })
    public String getOnlineCountTime() throws Exception {
	// 开始时间和结束时间
	Date beginTime = new Date();
	Date endTime = new Date();
	if (beginDate == null || "".equals(beginDate)) {
	    beginTime = DateService.getCurrentMonthFirstDay();
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

	@SuppressWarnings("unchecked")
	List<Map<String, Object>> list = aLoginService.getOnlineCountTime(
		beginTime, endTime, getBaseAdminContext().getProject()
			.getProjectId(), areaId, ptCode);

	if (list.size() > 0) {
	    for (int i = 0; i < list.size(); i++) {
		AverageOnline averageOnline = new AverageOnline();
		@SuppressWarnings("rawtypes")
		Map map = list.get(i);
		averageOnline
			.setCreateDate(String.valueOf(map.get("everyday")));
		averageOnline
			.setOnlineUser(String.valueOf(map.get("loginname")));
		averageOnline.setOnlinePlayer(String.valueOf(map
			.get("playerId")));
		Double avg = Double.valueOf(map.get("average").toString())
			/ Double.valueOf(map.get("playerId").toString());
		Double average = (double) Math.round(avg / 60);
		averageOnline.setAverage(Integer.valueOf(average.intValue()));
		averageList.add(averageOnline);
	    }
	}

	return "success";
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

    public Map<String, String> getServerMap() {
	return serverMap;
    }

    @Action(value = "getOnlineCountTime_ajax")
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

    public void setaLoginService(IALoginLogService aLoginService) {
	this.aLoginService = aLoginService;
    }

    public void setAreas(String[] areas) {
	this.areas = areas;
    }

    public void setAverageList(List<AverageOnline> averageList) {
	this.averageList = averageList;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
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

    public void setServerMap(Map<String, String> serverMap) {
	this.serverMap = serverMap;
    }

}
