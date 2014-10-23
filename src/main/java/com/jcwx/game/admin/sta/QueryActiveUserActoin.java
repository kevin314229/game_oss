package com.jcwx.game.admin.sta;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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
import com.jcwx.game.common.SpringService;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.ZQueryActiveUser;
import com.jcwx.game.service.IQueryActiveUserService;
import com.jcwx.game.service.oss.impl.OssUserServerService;

@ParentPackage("base")
@Namespace("/admin/activeUser")
@ResultPath("/")
/**
 * 查看活跃用户留存率
 * @author Administrator
 *
 */
public class QueryActiveUserActoin extends BasalAction {

    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private Integer allNum;

    /** 服务器id */
    private String areaId;
    private Date beginTime;
    /** 当前页数 */
    private Integer currPageNO;

    private Date endTime;

    /** 游戏标识 **/
    private Integer gameId;

    /** 每页记录数 */
    private Integer onePageNum;

    private Integer operationId;
    /** 服务器列表 */
    private List<OssServer> ossServerList;

    /** 平台列表 */
    private List<OssServer> OssServersPt;
    @Autowired
    private OssUserServerService ossUserServerService;
    /** 总页数 */
    private Integer pages;

    /** 平台id */
    private String ptId;

    private List<ZQueryActiveUser> queryActiveUserList;
    /** 大区列表 */
    private String[] selectArray;
    private String selectString;
    private Map<String, String> serverMap;

    @Override
    @Action(value = "queryActiveUser_index", results = { @Result(name = "success", location = "../../admin/sta/queryActiveUser.jsp") })
    public String execute() throws Exception {
	IQueryActiveUserService queryActiveUserService = (IQueryActiveUserService) SpringService
		.getBean("queryActiveUserService");

	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 20;
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0) {
	    beginNum = 0;
	}
	if (beginTime == null || beginTime.toString().equals("")) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	}
	if (endTime == null || endTime.toString().equals("")) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	}
	List<OssServer> list = getBaseAdminContext().getOssServers();
	if (gameId == null || gameId == 0) {
	    gameId = getBaseAdminContext().getProject().getProjectId();
	    OssServersPt = getBaseAdminContext().getOssServersPt();
	    list = getBaseAdminContext().getOssServers();

	} else {
	    OssServersPt = ossUserServerService
		    .getOssServerPtListByUserAndProjectId(getBaseAdminContext()
			    .getOssUser().getUsername(), gameId);
	    list = ossUserServerService.getOssServerListByUserAndProjectId(
		    getBaseAdminContext().getOssUser().getUsername(), gameId);
	}
	if (ptId != null && !ptId.equals("-1")) {
	    serverMap = new LinkedHashMap<String, String>();
	    for (OssServer ossServer : list) {
		if (ossServer.getServerId().toString().equals(ptId)) {
		    serverMap.put(ossServer.getId().toString(),
			    ossServer.getName());
		}
	    }
	    if (selectArray != null && selectArray.length > 0) {
		StringBuffer sb = new StringBuffer();
		if (selectArray[0].equals("-1")) { // -1 为全选
		    for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getServerId().toString().equals(ptId)) {
			    sb.append(list.get(i).getId().toString() + ",");
			}
		    }
		    sb.deleteCharAt(sb.length() - 1);
		    selectString = sb.toString();
		} else {
		    for (int i = 0; i < selectArray.length; i++) {
			sb.append(selectArray[i] + ",");
		    }
		    sb.deleteCharAt(sb.length() - 1);
		    selectString = sb.toString();
		}
	    } else {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
		    if (list.get(i).getServerId().toString().equals(ptId)) {
			sb.append(list.get(i).getId().toString() + ",");
		    }
		}
		sb.deleteCharAt(sb.length() - 1);
		selectString = sb.toString();
	    }
	    // 将字符串数组转化为字符串传到sql

	} else {
	    if (selectArray != null && selectArray.length > 0) {
		StringBuffer sb = new StringBuffer();
		if (selectArray[0].equals("-1")) { // -1 为全选
		    String serverArray[] = new String[list.size()];
		    for (int i = 0; i < list.size(); i++) {
			OssServer ossServer = list.get(i);
			serverArray[i] = ossServer.getId().toString();
		    }
		    selectArray = serverArray;
		}
		for (int i = 0; i < selectArray.length; i++) {
		    sb.append(selectArray[i] + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		selectString = sb.toString();
	    } else {
		StringBuffer sb = new StringBuffer();
		String serverArray[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
		    OssServer ossServer = list.get(i);
		    serverArray[i] = ossServer.getId().toString();
		}
		selectArray = serverArray;
		for (int i = 0; i < selectArray.length; i++) {
		    sb.append(selectArray[i] + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		selectString = sb.toString();
	    }
	    serverMap = new LinkedHashMap<String, String>();
	    for (OssServer ossServer : list) {
		serverMap
			.put(ossServer.getId().toString(), ossServer.getName());
	    }
	}

	queryActiveUserList = queryActiveUserService.QueryActiveUserByDay(
		beginTime, endTime, beginNum, onePageNum, gameId, selectString);
	allNum = queryActiveUserService.QueryActiveUserAllNum(beginTime,
		endTime, gameId, selectString);// 总记录数
	if (allNum == null) {
	    allNum = 0;
	}
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	return SUCCESS;
    }

    public Integer getAllNum() {
	return allNum;
    }

    @Action(value = "queryActiveUser_getAllOssServerList")
    public String getAllOssServerList() throws IOException {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	int ptId = Integer.valueOf(ServletActionContext.getRequest()
		.getParameter("ossServerId"));
	ossServerList = new ArrayList<OssServer>();
	List<OssServer> list = getBaseAdminContext().getOssServers();
	if (ptId == -1) {
	    for (OssServer ossServer : list) {
		ossServerList.add(ossServer);
	    }
	} else {
	    for (OssServer ossServer : list) {
		if (ossServer.getServerId() == ptId) {
		    ossServerList.add(ossServer);
		}
	    }
	}
	out.print(JSON.toJSON(ossServerList).toString());
	out.close();
	return null;

    }

    public String getAreaId() {
	return areaId;
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

    public Integer getGameId() {
	return gameId;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public Integer getOperationId() {
	return operationId;
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

    public String getPtId() {
	return ptId;
    }

    public List<ZQueryActiveUser> getQueryActiveUserList() {
	return queryActiveUserList;
    }

    public String[] getSelectArray() {
	return selectArray;
    }

    public String getSelectString() {
	return selectString;
    }

    public Map<String, String> getServerMap() {
	return serverMap;
    }

    @Action(value = "activeUser_newRegisterLoginAnalyse", results = { @Result(name = "success", location = "../../admin/sta/newRegisterLoginAnalyse.jsp") })
    public String newRegisterLoginAnalyse() {
	IQueryActiveUserService queryActiveUserService = (IQueryActiveUserService) SpringService
		.getBean("queryActiveUserService");

	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 20;
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0) {
	    beginNum = 0;
	}
	if (beginTime == null || beginTime.toString().equals("")) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	}
	if (endTime == null || endTime.toString().equals("")) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	}
	List<OssServer> list = getBaseAdminContext().getOssServers();
	if (gameId == null || gameId == 0) {
	    gameId = getBaseAdminContext().getProject().getProjectId();
	    OssServersPt = getBaseAdminContext().getOssServersPt();
	    list = getBaseAdminContext().getOssServers();

	} else {
	    OssServersPt = ossUserServerService
		    .getOssServerPtListByUserAndProjectId(getBaseAdminContext()
			    .getOssUser().getUsername(), gameId);
	    list = ossUserServerService.getOssServerListByUserAndProjectId(
		    getBaseAdminContext().getOssUser().getUsername(), gameId);
	}
	if (ptId != null && !ptId.equals("-1")) {
	    serverMap = new LinkedHashMap<String, String>();
	    for (OssServer ossServer : list) {
		if (ossServer.getServerId().toString().equals(ptId)) {
		    serverMap.put(ossServer.getId().toString(),
			    ossServer.getName());
		}
	    }
	    if (selectArray != null && selectArray.length > 0) {
		StringBuffer sb = new StringBuffer();
		if (selectArray[0].equals("-1")) { // -1 为全选
		    for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getServerId().toString().equals(ptId)) {
			    sb.append(list.get(i).getId().toString() + ",");
			}
		    }
		    sb.deleteCharAt(sb.length() - 1);
		    selectString = sb.toString();
		} else {
		    for (int i = 0; i < selectArray.length; i++) {
			sb.append(selectArray[i] + ",");
		    }
		    sb.deleteCharAt(sb.length() - 1);
		    selectString = sb.toString();
		}
	    } else {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
		    if (list.get(i).getServerId().toString().equals(ptId)) {
			sb.append(list.get(i).getId().toString() + ",");
		    }
		}
		sb.deleteCharAt(sb.length() - 1);
		selectString = sb.toString();
	    }
	    // 将字符串数组转化为字符串传到sql

	} else {
	    if (selectArray != null && selectArray.length > 0) {
		StringBuffer sb = new StringBuffer();
		if (selectArray[0].equals("-1")) { // -1 为全选
		    String serverArray[] = new String[list.size()];
		    for (int i = 0; i < list.size(); i++) {
			OssServer ossServer = list.get(i);
			serverArray[i] = ossServer.getId().toString();
		    }
		    selectArray = serverArray;
		}
		for (int i = 0; i < selectArray.length; i++) {
		    sb.append(selectArray[i] + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		selectString = sb.toString();
	    } else {
		StringBuffer sb = new StringBuffer();
		String serverArray[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
		    OssServer ossServer = list.get(i);
		    serverArray[i] = ossServer.getId().toString();
		}
		selectArray = serverArray;
		for (int i = 0; i < selectArray.length; i++) {
		    sb.append(selectArray[i] + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		selectString = sb.toString();
	    }
	    serverMap = new LinkedHashMap<String, String>();
	    for (OssServer ossServer : list) {
		serverMap
			.put(ossServer.getId().toString(), ossServer.getName());
	    }
	}

	queryActiveUserList = queryActiveUserService.NewRegisterLoginAnalyse(
		beginTime, endTime, beginNum, onePageNum, gameId, selectString);
	allNum = queryActiveUserService.NewRegisterLoginAnalyseAllNum(
		beginTime, endTime, gameId, selectString);// 总记录数
	if (allNum == null) {
	    allNum = 0;
	}
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	return SUCCESS;

    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setAreaId(String areaId) {
	this.areaId = areaId;
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

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
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

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

    public void setQueryActiveUserList(
	    List<ZQueryActiveUser> queryActiveUserList) {
	this.queryActiveUserList = queryActiveUserList;
    }

    public void setSelectArray(String[] selectArray) {
	this.selectArray = selectArray;
    }

    public void setSelectString(String selectString) {
	this.selectString = selectString;
    }

    public void setServerMap(Map<String, String> serverMap) {
	this.serverMap = serverMap;
    }
}
