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
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.PayHistorySta;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.service.oss.IOssServerService;
import com.jcwx.game.service.oss.IPayHistoryService;
import com.jcwx.game.service.oss.impl.OssUserServerService;
import com.jcwx.game.util.ServerListToMap;

/**
 * 充值记录action
 * 
 * @author csp
 * 
 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class PayHistoryAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 大区 ID
    private String areaId;
    private String[] areas;
    // 开始时间
    private String beginDate;
    // 结束时间
    private String endDate;
    // 游戏ID
    private int gameId;
    // 选择的平台ID
    private Integer operationId;

    /** 查询平台标识 */
    private List<OssOperation> ossOperationList;

    @Autowired
    private IOssOperationService ossOperationService;

    /** 服务区列表 */
    //private List<OssServer> ossServerList;

    @Autowired
    private IOssServerService ossServerService;
    /** 平台列表 */
    private List<OssServer> OssServersPt;

    @Autowired
    private OssUserServerService ossUserServerService;

    @Autowired
    private IPayHistoryService payHistoryService;

    /** 统计的集合 */
    private List<PayHistorySta> payHistoryStaList;

    private String ptCode;

    // 平台标识
    private String ptId;

    private Map<String, String> serverMap;

    public String getAreaId() {
	return areaId;
    }

    /*
     * //获取服务器列表方法
     * 
     * @Action(value = "payHistory_queryOssServerList") public String
     * queryOssServerList() throws IOException { HttpServletResponse reponse =
     * ServletActionContext.getResponse();
     * reponse.setContentType("text/html; charset=utf-8"); PrintWriter out =
     * reponse.getWriter(); if (operationId == null) { operationId = -1; } else
     * { OssServersPt = new ArrayList<OssServer>(); List<OssServer> list =
     * ossServerService .getOssServerListByProjectIdAndOperationId(
     * Integer.valueOf(gameId), operationId); for (OssServer ossServer : list) {
     * OssServersPt.add(ossServer); } }
     * out.print(JSONArray.toJSON(OssServersPt).toString()); out.close(); return
     * null; }
     * 
     * @Action(value = "query_OssServerListByServerCode") public String
     * getEquipPropertyList() throws IOException{ BaseAdminContext
     * baseAdminContext =getBaseAdminContext(); HttpServletResponse reponse =
     * ServletActionContext.getResponse();
     * reponse.setContentType("text/html; charset=utf-8"); PrintWriter out =
     * reponse.getWriter(); ossServerList=new ArrayList<OssServer>();
     * List<OssServer> list= baseAdminContext.getOssServers();
     * if(ptCode!=null&&!"".equals(ptCode)){ for (OssServer ossServer : list) {
     * if(ossServer.getServerCode().equals(ptCode)){
     * ossServerList.add(ossServer); } } }else{ ossServerList=list; }
     * out.print(JSONArray.toJSON(ossServerList).toString()); out.close();
     * return null; }
     * 
     * @Action(value = "query_queryOssPtlist") public String queryOssPtlist()
     * throws IOException{ BaseAdminContext baseAdminContext
     * =getBaseAdminContext(); HttpServletResponse reponse =
     * ServletActionContext.getResponse();
     * reponse.setContentType("text/html; charset=utf-8"); PrintWriter out =
     * reponse.getWriter(); OssServersPt=new ArrayList<OssServer>();
     * if(gameId!=-1){ List<OssServer>
     * list=ossUserServerService.getOssServerPtListByUserAndProjectId(
     * baseAdminContext.getOssUser().getUsername(),gameId); for (OssServer
     * ossServer : list) { if(ossServer.getProjectId().equals(gameId)){
     * OssServersPt.add(ossServer); } } }else{
     * OssServersPt=baseAdminContext.getOssServersPt(); }
     * out.print(JSONArray.toJSON(OssServersPt).toString()); out.close(); return
     * null; }
     * 
     * @Action(value = "query_queryOssServerByPtId") public String
     * queryOssServerByPtId() throws IOException{ BaseAdminContext
     * baseAdminContext =getBaseAdminContext(); HttpServletResponse reponse =
     * ServletActionContext.getResponse();
     * reponse.setContentType("text/html; charset=utf-8"); PrintWriter out =
     * reponse.getWriter(); ossServerList=new ArrayList<OssServer>();
     * if(ptId!=-1){ List<OssServer>
     * list=ossUserServerService.getOssServerListByUserAndProjectId(
     * baseAdminContext.getOssUser().getUsername(),gameId); for (OssServer
     * ossServer : list) { if(ossServer.getServerId()==ptId){
     * ossServerList.add(ossServer); } } }else{
     * ossServerList=ossUserServerService.getOssServerListByUserAndProjectId(
     * baseAdminContext.getOssUser().getUsername(),gameId); }
     * out.print(JSONArray.toJSON(ossServerList).toString()); out.close();
     * return null; }
     * 
     * @Action(value = "query_queryOssServerByPtCode") public String
     * queryOssServerByPtCode() throws IOException{ BaseAdminContext
     * baseAdminContext =getBaseAdminContext(); HttpServletResponse reponse =
     * ServletActionContext.getResponse();
     * reponse.setContentType("text/html; charset=utf-8"); PrintWriter out =
     * reponse.getWriter(); ossServerList=new ArrayList<OssServer>();
     * if(ptCode!=null&&!"".equals(ptCode)){ List<OssServer>
     * list=ossUserServerService.getOssServerListByUserAndProjectId(
     * baseAdminContext.getOssUser().getUsername(),gameId); for (OssServer
     * ossServer : list) { if(ossServer.getServerCode().equals(ptCode)){
     * ossServerList.add(ossServer); } } }else{
     * ossServerList=ossUserServerService.getOssServerListByUserAndProjectId(
     * baseAdminContext.getOssUser().getUsername(),gameId); }
     * out.print(JSONArray.toJSON(ossServerList).toString()); out.close();
     * return null; }
     */

    public String[] getAreas() {
	return areas;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public int getGameId() {
	return gameId;
    }

    public Integer getOperationId() {
	return operationId;
    }

    public List<OssOperation> getOssOperationList() {
	return ossOperationList;
    }

    public List<OssServer> getOssServersPt() {
	return OssServersPt;
    }

    public List<PayHistorySta> getPayHistoryStaList() {
	return payHistoryStaList;
    }

    public String getPtCode() {
	return ptCode;
    }

    public String getPtId() {
	return ptId;
    }

    public Map<String, String> getServerMap() {
	return serverMap;
    }

    @Action(value = "payHistory_interval", results = { @Result(name = "success", location = "../../admin/sta/payHistory_interval_report.jsp") })
    public String index() throws Exception {
	BaseAdminContext baseAdminContext = getBaseAdminContext();
	try {
	    Date beginTime = new Date();
	    Date endTime = new Date();
	    if (beginDate == null || "".equals(beginDate)) {
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
	    gameId = baseAdminContext.getProject().getProjectId();
	    OssServersPt = baseAdminContext.getOssServersPt();
	    list = baseAdminContext.getOssServers();
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
	    if (ptCode != null && !"".equals(ptCode)) {
		serverMap = ServerListToMap.convert(list, ptCode);
	    } else {
		serverMap = ServerListToMap.convert(list);
	    }
	    areaId = ServerListToMap.arrayToString(list, areas, ptCode);
	    if (areas != null && areas.length == 1) {
		areas = areas[0].split(",");
	    }
	    this.ossOperationList = ossOperationService.getOssOperationList();
	    // 逻辑
	    payHistoryStaList = payHistoryService.getPayHistorySta(gameId,
		    ptId, areaId, beginDate, endDate);
	    PayHistorySta payHistory = new PayHistorySta();
	    payHistory.setInterval("总计");
	    payHistory.setAmountSum(0.0);
	    payHistory.setPayNum(0);
	    payHistory.setPayPlayerNum(0);
	    for (PayHistorySta payHistorySta : payHistoryStaList) {
		if (payHistorySta.getAmountSum() == null) {
		    payHistorySta.setAmountSum(0.0);
		    payHistorySta.setArpu("0");
		} else {
		    BigDecimal b = new BigDecimal(payHistorySta.getAmountSum()
			    / payHistorySta.getPayPlayerNum());
		    payHistorySta.setArpu(String.valueOf(b.setScale(2,
			    BigDecimal.ROUND_HALF_UP).doubleValue()));
		}
		payHistory.setPayPlayerNum(payHistorySta.getPayPlayerNum()
			+ payHistory.getPayPlayerNum());
		payHistory.setPayNum(payHistory.getPayNum()
			+ payHistorySta.getPayNum());
		payHistory.setAmountSum(payHistory.getAmountSum()
			+ payHistorySta.getAmountSum());
	    }
	    if (payHistory.getPayPlayerNum() != 0) {
		BigDecimal b = new BigDecimal(payHistory.getAmountSum()
			/ payHistory.getPayPlayerNum());
		payHistory.setArpu(String.valueOf(b.setScale(2,
			BigDecimal.ROUND_HALF_UP).doubleValue()));
	    }

	    payHistoryStaList.add(payHistory);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    
    @Action(value = "payHistory_registerPay", results = { @Result(name = "success", location = "../../admin/sta/payHistory_interval_report.jsp") })
    public String registerPay() throws Exception {
	
	
	
	
	return SUCCESS;
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

    public void setGameId(int gameId) {
	this.gameId = gameId;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
	this.ossOperationList = ossOperationList;
    }

    public void setOssServersPt(List<OssServer> ossServersPt) {
	OssServersPt = ossServersPt;
    }

    public void setPayHistoryStaList(List<PayHistorySta> payHistoryStaList) {
	this.payHistoryStaList = payHistoryStaList;
    }

    public void setPtCode(String ptCode) {
	this.ptCode = ptCode;
    }

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

    public void setServerMap(Map<String, String> serverMap) {
	this.serverMap = serverMap;
    }

}
