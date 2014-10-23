package com.jcwx.game.admin.sta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.OperationStatistic;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.Project;
import com.jcwx.game.http.domain.SendBaseProperty;
import com.jcwx.game.service.oss.IOperationReportService;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.service.oss.IOssServerService;
import com.jcwx.game.service.oss.IPayHistoryService;
import com.jcwx.game.service.oss.IProjectService;
import com.jcwx.game.service.oss.impl.OssUserServerService;
import com.jcwx.game.util.ServerListToMap;
import com.jcwx.game.web.Global;

/**
 * 充值记录action
 * 
 * @author csp
 * 
 */
@ParentPackage("base")
@Namespace("/admin/operReport")
@ResultPath("/")
public class OperationReportAction extends BasalAction {

    private static final long serialVersionUID = 8227187703469342101L;
    // 大区 ID
    private String areaId;
    // 大区 ID列表多选
    private String[] areas;
    private int baseEquip;
    private int baseProperty;

  
    // 开始时间
    private String beginDate;

    private int consumeNum;
    private List<OperationStatistic> consumeOperList;
    private String consumeSum;
    private List<OperationStatistic> detailOperList;
    // 结束时间
    private String endDate;
    private Integer gameCode;
    // 游戏ID
    private Integer gameId;

    private int itemId;

    private String[] itemIds;

    private String nickName;
    @Autowired
    private IOperationReportService operReportService;

    @Autowired
    private IOssOperationService ossOperationService;

    /** 服务器列表 */
    private List<OssServer> ossServerList;
    @Autowired
    private IOssServerService ossServerService;

    private List<OssServer> OssServersPt;

    @Autowired
    private OssUserServerService ossUserServerService;
    @Autowired
    private IPayHistoryService payHistoryService;

    private int produceNum;
    private List<OperationStatistic> produceOperList;
    private String produceSum;
    // 用户管理的项目列表
    private List<Project> projectList;

    @Autowired
    private IProjectService projectService;

    // 平台标识
    private String ptId;

    private Map<String, String> serverMap;

    private List<OperationStatistic> sumOperList;

    @Action(value = "operationReport_currencyIndex", results = { @Result(name = "success", location = "../../admin/sta/currency_report_index.jsp") })
    public String currencyIndex() throws Exception {
	try {
	    BaseAdminContext baseAdminContext = getBaseAdminContext();
	    // 初始化日期信息
	    Date beginTime = new Date();
	    Date endTime = new Date();

	    if (beginDate == null) {
		beginTime = DateService.getCurrentMonthFirstDay();
		beginDate = DateService.getDateFormatStr(beginTime,
			"yyyy-MM-dd") + " 00";
	    }
	    if (endDate == null) {
		endTime = DateService.getCurrentDayLastUtilDate();
		endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd")
			+ " 23";
	    }
	    // 初始化项目ID
	    List<OssServer> list = new ArrayList<OssServer>();
	    if (gameId == null) {
		gameId = baseAdminContext.getProject().getProjectId();
		OssServersPt = baseAdminContext.getOssServersPt();
		list = baseAdminContext.getOssServers();
	    } else {
		OssServersPt = ossUserServerService
			.getOssServerPtListByUserAndProjectId(baseAdminContext
				.getOssUser().getUsername(), gameId);
		list = ossUserServerService.getOssServerListByUserAndProjectId(
			baseAdminContext.getOssUser().getUsername(), gameId);
	    }
	    if (ptId != null && !"".equals(ptId)) {
		serverMap = ServerListToMap.convert(list, ptId);
	    } else {
		serverMap = ServerListToMap.convert(list);
	    }
	    areaId = ServerListToMap.arrayToString(list, areas, ptId);
	    // 获取游戏列表
	    projectList = new ArrayList<Project>();
	    projectList = projectService
		    .getProjectListbyUserName(baseAdminContext.getOssUser()
			    .getUsername());
	    String itemStr = null;
	    if (itemIds != null && itemIds.length > 0) {
		StringBuffer sb = new StringBuffer();
		for (String item : itemIds) {
		    sb.append(item + ",");
		}
		itemStr = sb.toString();
		itemStr = itemStr.substring(0, itemStr.length() - 1);
	    }
	    if (baseProperty > 0) {
		itemStr = itemStr
			+ ","
			+ getItemIdString("property", baseAdminContext
				.getCurrentOssServer().getUrl());
	    }
	    if (baseEquip > 0) {
		itemStr = itemStr
			+ ","
			+ getItemIdString("equip", baseAdminContext
				.getCurrentOssServer().getUrl());
	    }
	    if (nickName == null || "".equals(nickName.trim())) {
		sumOperList = operReportService.getOperationStaSum(beginDate,
			endDate, gameId, areaId, itemStr);
		detailOperList = operReportService.getOperationDaySum(
			beginDate, endDate, gameId, areaId, itemStr);
	    } else {
		sumOperList = operReportService.getOperationSumByNickName(
			beginDate, endDate, gameId, areaId, itemStr, nickName);
		detailOperList = operReportService
			.getOperationDaySumByNickName(beginDate, endDate,
				gameId, areaId, itemStr, nickName);

	    }

	    gameCode = gameId;
	    // 出来分页信息
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "operationReport_currencyOperList", results = { @Result(name = "success", location = "../../admin/sta/currency_report_List.jsp") })
    public String currencyOperList() throws Exception {
	try {
	    BaseAdminContext baseAdminContext = getBaseAdminContext();
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
	    if (gameId == null) {
		gameId = baseAdminContext.getProject().getProjectId();
		OssServersPt = baseAdminContext.getOssServersPt();
		list = baseAdminContext.getOssServers();

	    } else {
		OssServersPt = ossUserServerService
			.getOssServerPtListByUserAndProjectId(baseAdminContext
				.getOssUser().getUsername(), gameId);
		list = ossUserServerService.getOssServerListByUserAndProjectId(
			baseAdminContext.getOssUser().getUsername(), gameId);
	    }
	    if (ptId != null && !"".equals(ptId)) {
		serverMap = ServerListToMap.convert(list, ptId);
	    } else {
		serverMap = ServerListToMap.convert(list);
	    }
	    areaId = ServerListToMap.arrayToString(list, areas, ptId);
	    // 获取游戏列表
	    projectList = new ArrayList<Project>();
	    projectList = projectService
		    .getProjectListbyUserName(baseAdminContext.getOssUser()
			    .getUsername());
	    String itemStr = null;

	    if (nickName == null || "".equals(nickName.trim())) {
		produceOperList = operReportService.getOperationStaList(
			beginDate, endDate, gameId, areaId, itemId, 1);
		consumeOperList = operReportService.getOperationStaList(
			beginDate, endDate, gameId, areaId, itemId, 2);
	    } else {
		produceOperList = operReportService
			.getOperationListByNickName(beginDate, endDate, gameId,
				areaId, itemId, nickName, 1);
		consumeOperList = operReportService
			.getOperationListByNickName(beginDate, endDate, gameId,
				areaId, itemId, nickName, 2);
	    }
	    produceNum = produceOperList.size();
	    consumeNum = consumeOperList.size();
	    for (int i =0;i<produceOperList.size();i++) {
		OperationStatistic operation = produceOperList.get(i);
		BigDecimal b = new BigDecimal(Long.valueOf(operation.getProduceNum().toString())*100/Double.valueOf(produceSum));
		produceOperList.get(i).setPercent(b.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
	    }
	    for (int i =0;i<consumeOperList.size();i++) {
		OperationStatistic operation = consumeOperList.get(i);
		BigDecimal b = new BigDecimal(Long.valueOf(operation.getProduceNum().toString())*100/Double.valueOf(consumeSum));
		consumeOperList.get(i).setPercent(b.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
	    }
	    // 出来分页信息
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public String getAreaId() {
	return areaId;
    }

    public String[] getAreas() {
	return areas;
    }

    public int getBaseEquip() {
	return baseEquip;
    }

    public int getBaseProperty() {
	return baseProperty;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public int getConsumeNum() {
	return consumeNum;
    }

    public List<OperationStatistic> getConsumeOperList() {
	return consumeOperList;
    }

    public String getConsumeSum() {
	return consumeSum;
    }

    public List<OperationStatistic> getDetailOperList() {
	return detailOperList;
    }

    public String getEndDate() {
	return endDate;
    }

    public Integer getGameCode() {
	return gameCode;
    }

    public Integer getGameId() {
	return gameId;
    }

    public int getItemId() {
	return itemId;
    }

    public String[] getItemIds() {
	return itemIds;
    }

    private String getItemIdString(String type, String url) {
	List<SendBaseProperty> list = null;
	if ("equip".equals(type)) {
	    list = Global.getEquipList(url);
	} else if ("property".equals(type)) {
	    list = Global.getPropertyList(url,getBaseAdminContext().getProject().getProjectId());
	}

	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < list.size(); i++) {
	    SendBaseProperty temp = list.get(i);
	    sb.append(temp.getValue());
	    if (i < list.size() - 1) {
		sb.append(",");
	    }
	}
	return sb.toString();
    }

    public String getNickName() {
	return nickName;
    }

    public List<OssServer> getOssServerList() {
	return ossServerList;
    }

    public List<OssServer> getOssServersPt() {
	return OssServersPt;
    }

    public OssUserServerService getOssUserServerService() {
	return ossUserServerService;
    }

    public int getProduceNum() {
	return produceNum;
    }

    public List<OperationStatistic> getProduceOperList() {
	return produceOperList;
    }

    public String getProduceSum() {
	return produceSum;
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

    public List<OperationStatistic> getSumOperList() {
	return sumOperList;
    }

    public void setAreaId(String areaId) {
	this.areaId = areaId;
    }

    public void setAreas(String[] areas) {
	this.areas = areas;
    }

    public void setBaseEquip(int baseEquip) {
	this.baseEquip = baseEquip;
    }

    public void setBaseProperty(int baseProperty) {
	this.baseProperty = baseProperty;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setConsumeNum(int consumeNum) {
	this.consumeNum = consumeNum;
    }

    public void setConsumeOperList(List<OperationStatistic> consumeOperList) {
	this.consumeOperList = consumeOperList;
    }

    public void setConsumeSum(String consumeSum) {
	this.consumeSum = consumeSum;
    }

    public void setDetailOperList(List<OperationStatistic> detailOperList) {
	this.detailOperList = detailOperList;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setGameCode(Integer gameCode) {
	this.gameCode = gameCode;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setItemId(int itemId) {
	this.itemId = itemId;
    }

    public void setItemIds(String[] itemIds) {
	this.itemIds = itemIds;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOssServerList(List<OssServer> ossServerList) {
	this.ossServerList = ossServerList;
    }

    public void setOssServersPt(List<OssServer> ossServersPt) {
	OssServersPt = ossServersPt;
    }

    public void setOssUserServerService(
	    OssUserServerService ossUserServerService) {
	this.ossUserServerService = ossUserServerService;
    }

    public void setProduceNum(int produceNum) {
	this.produceNum = produceNum;
    }

    public void setProduceOperList(List<OperationStatistic> produceOperList) {
	this.produceOperList = produceOperList;
    }

    public void setProduceSum(String produceSum) {
	this.produceSum = produceSum;
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

    public void setSumOperList(List<OperationStatistic> sumOperList) {
	this.sumOperList = sumOperList;
    }

   
    

}
