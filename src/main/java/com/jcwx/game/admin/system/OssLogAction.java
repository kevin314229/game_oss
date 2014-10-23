package com.jcwx.game.admin.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.jcwx.game.domain.OssLog;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.service.oss.IOssLogService;
import com.jcwx.game.service.oss.IOssServerService;

/** 操作日志 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class OssLogAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private Integer allNum;

    /** 起始页号 */
    private Integer beginNum;

    private Date beginTime;

    /** 当前页数 */
    private Integer currPageNO;

    private Date endTime;

    /** 账号,用于增加模糊查找功能 */
    private String name;

    /** 每页记录数 */
    private Integer onePageNum;

    private Integer operationId;

    /** 操作类型 */
    private String operationType;

    private List<OssLog> ossLogList;
    @Autowired
    private IOssLogService ossLogService;

    /** 服务器ID */
    private Integer ossServerId;

    /** 服务器列表 */
    private List<OssServer> ossServerList;

    private IOssServerService ossServerService;

    /** 总页数 */
    private Integer pages;

    private Integer projectId;

    /** 浏览系统菜单 */
    @Action(value = "ossLog_browseLogList", results = { @Result(name = "success", location = "/admin/system/log/browseLogList.jsp") })
    public String browseLogList() throws Exception {
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	if (getOnePageNum() == null || getOnePageNum().intValue() == 0) {
	    setOnePageNum(20);
	}
	if (beginTime == null) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	} else {
	    beginTime = DateService.getDateFirstTime(beginTime);
	}
	if (endTime == null) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	} else {
	    endTime = DateService.getDateLastTime(endTime);
	}
	if (name == null || name.equals("")) {
	    name = null;
	}
	if (operationId == null || operationId == -1) {
	    operationId = -1;
	}
	if (ossServerId == null || ossServerId == -1) {
	    ossServerId = -1;
	}
	if (operationType == null || operationType.equals("")) {
	    operationType = null;
	}

	// 给服务器赋值
	if (operationId != -1) {
	    ossServerList = new ArrayList<OssServer>();
	    List<OssServer> list = getBaseAdminContext().getOssServers();
	    for (OssServer ossServer : list) {
		if (ossServer.getServerId().intValue() == operationId
			.intValue()) {
		    ossServerList.add(ossServer);
		}
	    }
	}

	beginNum = (currPageNO - 1) * onePageNum;

	// allNum = ossLogService.getOssLogCount();
	// ossLogList = ossLogService.getOssLogListByPage(beginNum, onePageNum);
	allNum = ossLogService.getAllNumByQueryCondition(beginTime, endTime,
		name, operationType, ossServerId);
	ossLogList = ossLogService.getOssLogListByQueryCondition(beginNum,
		onePageNum, beginTime, endTime, name, operationType,
		ossServerId);
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}

	// for(OssLog o : ossLogList){
	//
	// o.setOperationType(OssLogConstant.maptype.get(o.getOperationType()));
	// }

	// Map ma= OssLogConstant.maptype;
	return SUCCESS;
    }

    public Integer getAllNum() {
	return allNum;
    }

    public Date getBeginTime() {
	return beginTime;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public Date getEndTime() {
	return endTime;
    }

    public String getName() {
	return name;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public Integer getOperationId() {
	return operationId;
    }

    public String getOperationType() {
	return operationType;
    }

    public List<OssLog> getOssLogList() {
	return ossLogList;
    }

    public IOssLogService getOssLogService() {
	return ossLogService;
    }

    public Integer getOssServerId() {
	return ossServerId;
    }

    public List<OssServer> getOssServerList() {
	return ossServerList;
    }

    @Action(value = "ossLog_getOssServerListByOperationId")
    public String getOssServerLists() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	if (operationId == null) {
	    operationId = -1;
	} else {
	    operationId = Integer.valueOf(ServletActionContext.getRequest()
		    .getParameter("operationId"));
	    ossServerList = new ArrayList<OssServer>();
	    System.out.print(operationId);
	    List<OssServer> list = getBaseAdminContext().getOssServers();
	    for (OssServer ossServer : list) {
		if (ossServer.getServerId().intValue() == operationId) {
		    ossServerList.add(ossServer);
		}
	    }
	}
	out.print(JSON.toJSON(ossServerList).toString());
	out.close();
	return null;
    }

    public IOssServerService getOssServerService() {
	return ossServerService;
    }

    public Integer getPages() {
	return pages;
    }

    public Integer getProjectId() {
	return projectId;
    }

    @Action(value = "ossLog_queryOssServerList")
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

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setBeginTime(Date beginTime) {
	this.beginTime = beginTime;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
    }

    public void setOperationType(String operationType) {
	this.operationType = operationType;
    }

    public void setOssLogList(List<OssLog> ossLogList) {
	this.ossLogList = ossLogList;
    }

    public void setOssLogService(IOssLogService ossLogService) {
	this.ossLogService = ossLogService;
    }

    public void setOssServerId(Integer ossServerId) {
	this.ossServerId = ossServerId;
    }

    public void setOssServerList(List<OssServer> ossServerList) {
	this.ossServerList = ossServerList;
    }

    public void setOssServerService(IOssServerService ossServerService) {
	this.ossServerService = ossServerService;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setProjectId(Integer projectId) {
	this.projectId = projectId;
    }

}
