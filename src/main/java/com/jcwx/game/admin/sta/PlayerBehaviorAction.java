package com.jcwx.game.admin.sta;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.alibaba.fastjson.JSONArray;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.PlayerBehavior;
import com.jcwx.game.service.oss.IALoginLogService;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.util.ServerListToMap;

/**
 * 查询玩家周期行为
 * 
 * @author csp
 * 
 */
@SuppressWarnings("unused")
@ParentPackage("base")
@Namespace("/admin/sta")
@ResultPath("/")
public class PlayerBehaviorAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 活跃开始时间 */
    private String beginDate;

    /** 结束时间 */
    private String endDate;
    
    /** 开始时间 */
    private String beginDate2;
    
    /** 结束时间 */
    private String endDate2;

    private Integer operationId;

    /** 查询平台标识 */
    private List<OssOperation> ossOperationList;

    // 大区 ID列表多选
    private String[] areas;
    
    /** 大区Id */
    private String ossServerId;

    /** 服务器列表 */
    private List<OssServer> ossServerList;
    
    private Map<String, String> serverMap;
    /** 平台标识 */
    private String ptCode;
    
    private Integer period;
    
    @Autowired
    private IOssOperationService ossOperationService;
    @Autowired
    private IALoginLogService aLoginLogService;
    
    private List<PlayerBehavior> playerBehaviorList;

    @Action(value = "playerBehavior_list", results = { @Result(name = "success", location = "../../admin/sta/playerBehavior_list.jsp") })
    public String getConsumeDataList() throws Exception {
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
	Date beginTime2 = new Date();
	Date endTime2 = new Date();
	
	
	if (operationId == null || operationId == -1) {
	    operationId = null;
	}

	if (ossServerId == null || ossServerId.equals("-1")) {
	    ossServerId = null;
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
	
	if(period==null){
	    return SUCCESS;
	}
	switch (period) {
    		case 2:
		    beginTime2=DateService.dateIncreaseByDay(endTime, 8);
		    endTime2=DateService.dateIncreaseByDay(endTime, 14);
    		    break;
    		case 3:
    		    beginTime2=DateService.dateIncreaseByDay(endTime, 15);
		    endTime2=DateService.dateIncreaseByDay(endTime, 21);
    		    break;
    		case 4:
    		    beginTime2=DateService.dateIncreaseByDay(endTime, 22);
		    endTime2=DateService.dateIncreaseByDay(endTime, 27);
    		    break;
    
    		default:
    		    beginTime2= DateService.dateIncreaseByDay(endTime, 1);
		    endTime2=DateService.dateIncreaseByDay(endTime, 7);
    		    break;
	}
	beginTime2 = DateService.getDateFirstTime(beginTime2);
	endTime2 = DateService.getDateLastTime(endTime2);
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	beginDate2=sdf.format(beginTime2);
	endDate2 =sdf.format(endTime2);
	
	// 获取所有平台标识
	List list2 = aLoginLogService.getPlayerBehavior(beginTime, endTime,beginTime2,endTime2,getBaseAdminContext().getProject().getProjectId(), areaId);
	playerBehaviorList=new ArrayList<PlayerBehavior>();
	
	for (int i=0;i< list2.size();i++) {
	    Map map = (Map) list2.get(i);
	    PlayerBehavior playerBehavior=new PlayerBehavior();
	    playerBehavior.setLonginNum(map.get("num1")==null?"0":map.get("num1").toString());
	    playerBehavior.setPayNum(map.get("num2")==null?"0":map.get("num2").toString());
	    if(map.get("num1")!=null&&map.get("num2")!=null&&map.get("num3")!=null){
		BigDecimal a = new BigDecimal(Double.valueOf(map.get("num3").toString())/Double.valueOf(map.get("num2").toString()));
		playerBehavior.setArppu(a.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
		BigDecimal b = new BigDecimal(Double.valueOf(map.get("num2").toString())*100/Double.valueOf(map.get("num1").toString()));
		playerBehavior.setPayRate(b.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
	    }else{
		playerBehavior.setArppu("0");
		playerBehavior.setPayRate("0");
	    }
	    playerBehavior.setPaySum(map.get("num3")==null?"0":map.get("num3").toString());
	    playerBehaviorList.add(playerBehavior);
	}
	return SUCCESS;
    }

    @Action(value = "playerBehavior_ajax")
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
    
    
    public String getBeginDate() {
	return beginDate;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public Integer getOperationId() {
	return operationId;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
    }

    public List<OssOperation> getOssOperationList() {
	return ossOperationList;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
	this.ossOperationList = ossOperationList;
    }

    public String getOssServerId() {
	return ossServerId;
    }

    public void setOssServerId(String ossServerId) {
	this.ossServerId = ossServerId;
    }

    public List<OssServer> getOssServerList() {
	return ossServerList;
    }

    public void setOssServerList(List<OssServer> ossServerList) {
	this.ossServerList = ossServerList;
    }

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public Map<String, String> getServerMap() {
        return serverMap;
    }

    public void setServerMap(Map<String, String> serverMap) {
        this.serverMap = serverMap;
    }

    public String getPtCode() {
        return ptCode;
    }

    public void setPtCode(String ptCode) {
        this.ptCode = ptCode;
    }

    public IOssOperationService getOssOperationService() {
        return ossOperationService;
    }

    public void setOssOperationService(IOssOperationService ossOperationService) {
        this.ossOperationService = ossOperationService;
    }

    public String getBeginDate2() {
        return beginDate2;
    }

    public void setBeginDate2(String beginDate2) {
        this.beginDate2 = beginDate2;
    }

    public String getEndDate2() {
        return endDate2;
    }

    public void setEndDate2(String endDate2) {
        this.endDate2 = endDate2;
    }

    public List<PlayerBehavior> getPlayerBehaviorList() {
        return playerBehaviorList;
    }

    public void setPlayerBehaviorList(List<PlayerBehavior> playerBehaviorList) {
        this.playerBehaviorList = playerBehaviorList;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
    

    
}
