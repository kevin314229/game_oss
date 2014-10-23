package com.jcwx.game.admin.sta;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.jcwx.game.admin.BaseInfoAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.StaMallRecord;
import com.jcwx.game.http.domain.OssMallRecord;
import com.jcwx.game.http.domain.OssStaMallRecord;
import com.jcwx.game.service.oss.IStaMallRecordService;
import com.jcwx.game.util.ServerListToMap;

/**
 * 商城消耗记录
 * 
 * @author 小平 2013-11-18
 */
@ParentPackage("base")
@Namespace("/admin/sta")
@ResultPath("/")
public class StaMallRecordAction extends BaseInfoAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 活跃开始时间 */
    private String beginDate;

    /** 结束时间 */
    private String endDate;

    // 大区 ID列表多选
    private String[] areas;

    /** 大区Id */
    private String ossServerId;

    /** 服务器列表 */
    private List<OssServer> ossServerList;

    /** 查询平台标识 */
    // private List<OssOperation> ossOperationList;

    private Integer operationId;

   // private List<OssMallRecord> mallRecordList = new ArrayList<OssMallRecord>();

    // private PageMessage pageMessage = PageMessage.getOkMessage();

    private Map<String, String> serverMap;
    /** 平台标识 */
    private String ptCode;

    private OssStaMallRecord sumMallRecord ;
    
    private List<OssStaMallRecord> ossMallRecordList;

    @Autowired
    private IStaMallRecordService staMallRecordService;

    @Action(value = "staMallRecord_index", results = { @Result(name = "success", location = "../../admin/sta/staMallRecordAssay.jsp") })
    public String query() throws Exception {
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
	// 给服务器赋值
	ossServerList = new ArrayList<OssServer>();
	List<OssServer> ossList = getBaseAdminContext().getOssServers();
	if (operationId != null && !"".equals(operationId)) {
	    for (OssServer ossServer : ossList) {
		if (ossServer.getServerId().equals(operationId)) {
		    ossServerList.add(ossServer);
		}
	    }
	} else {
	    ossServerList = ossList;
	}
	serverMap = ServerListToMap.convert(ossServerList);
	String areaId = ServerListToMap.arrayToString(ossList, areas, ptCode);
	if (areas != null && areas.length == 1) {
	    areas = areas[0].split(",");
	}

	try {

	    List<StaMallRecord> list = staMallRecordService.getAMallRecordList(
		    beginTime, endTime, getBaseAdminContext().getProject()
			    .getProjectId(), areaId);

	    ossMallRecordList = new ArrayList<OssStaMallRecord>();
	     sumMallRecord = new OssStaMallRecord();
	    int oldItemId = 0;
	    OssStaMallRecord ossMallRecord = new OssStaMallRecord();
	    for (int i = 0; i < list.size(); i++) {
		StaMallRecord staMallRecord = list.get(i); // storeMap
		int itemId = staMallRecord.getGoodId();
		int type = staMallRecord.getType();
		String goodName = itemId + "";

		if (oldItemId != itemId && oldItemId != 0) {
		    sumMallRecord.setNumber(ossMallRecord.getNumber()+sumMallRecord.getNumber());
		    sumMallRecord.setVipNumber(sumMallRecord.getVipNumber()+ossMallRecord.getVipNumber());
		    sumMallRecord.setSecretNumber(sumMallRecord.getSecretNumber()+ossMallRecord.getSecretNumber());
		    sumMallRecord.setNormalSum(sumMallRecord.getNormalSum()+ossMallRecord.getNormalSum());
		    sumMallRecord.setVipSum(sumMallRecord.getVipSum()+ossMallRecord.getVipSum());
		    sumMallRecord.setSecretSum(sumMallRecord.getSecretSum()+ossMallRecord.getSecretSum());
		    sumMallRecord.setGoldSum(sumMallRecord.getGoldSum()+ossMallRecord.getGoldSum());
		    ossMallRecordList.add(ossMallRecord);
		    ossMallRecord = new OssStaMallRecord();
		}
		ossMallRecord.setGoodName(goodName);

		ossMallRecord.setGoldSum(staMallRecord.getGoldSum() + ossMallRecord.getGoldSum());
		ossMallRecord.setGoodId(itemId);
		ossMallRecord.setType(type);
		if (type == OssMallRecord.type_vip商店) {
		    ossMallRecord.setVipNumber(ossMallRecord.getVipNumber()+ staMallRecord.getNumber());
		    ossMallRecord.setVipPrice(staMallRecord.getPrice());
		    ossMallRecord.setVipSum(ossMallRecord.getVipSum()+ staMallRecord.getGoldSum());

		} else if (type == OssMallRecord.type_普通价格) {
		    ossMallRecord.setNumber(ossMallRecord.getNumber()+ staMallRecord.getNumber());
		    ossMallRecord.setPrice(staMallRecord.getPrice());
		    ossMallRecord.setNormalSum(ossMallRecord.getNormalSum()+ staMallRecord.getGoldSum());
		} else {
		    ossMallRecord.setSecretNumber(ossMallRecord.getSecretNumber()+ staMallRecord.getNumber());
		    ossMallRecord.setSecretPrice(staMallRecord.getPrice());
		    ossMallRecord.setSecretSum(ossMallRecord.getSecretSum()+ staMallRecord.getGoldSum());
		}
		if (i == list.size() - 1) {
		    
		    sumMallRecord.setNumber(ossMallRecord.getNumber()+sumMallRecord.getNumber());
		    sumMallRecord.setVipNumber(sumMallRecord.getVipNumber()+ossMallRecord.getVipNumber());
		    sumMallRecord.setSecretNumber(sumMallRecord.getSecretNumber()+ossMallRecord.getSecretNumber());
		    sumMallRecord.setNormalSum(sumMallRecord.getNormalSum()+ossMallRecord.getNormalSum());
		    sumMallRecord.setVipSum(sumMallRecord.getVipSum()+ossMallRecord.getVipSum());
		    sumMallRecord.setSecretSum(sumMallRecord.getSecretSum()+ossMallRecord.getSecretSum());
		    sumMallRecord.setGoldSum(sumMallRecord.getGoldSum()+ossMallRecord.getGoldSum());
		    ossMallRecordList.add(ossMallRecord);
		}
		oldItemId = itemId;
	    }
	   
	    for (int j=0;j<ossMallRecordList.size();j++) {
		OssStaMallRecord ossStaMallRecord = ossMallRecordList.get(j);
		BigDecimal a = new BigDecimal(ossStaMallRecord.getNormalSum()*100/sumMallRecord.getNormalSum());
		ossStaMallRecord.setScale(a.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		
		BigDecimal b = new BigDecimal(ossStaMallRecord.getVipSum()*100/sumMallRecord.getVipSum());
		ossStaMallRecord.setVipScale(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		
		BigDecimal c = new BigDecimal(ossStaMallRecord.getGoldSum()*100/sumMallRecord.getGoldSum());
		ossStaMallRecord.setTotalScale(c.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	    }
	    
	    Collections.sort(ossMallRecordList, new Comparator<OssStaMallRecord>() {
		@Override
		public int compare(OssStaMallRecord p2, OssStaMallRecord p1) {
		    return (int) (p1.getGoldSum() - p2.getGoldSum());
		}
	    });

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    /**
     * 
     BigDecimal c = new BigDecimal(mrd.getVipPrice() mrd.getVipNumber() * 100
     * / vipGoldSum); mrd.setVipScale(c.setScale(2, BigDecimal.ROUND_HALF_UP)
     * .doubleValue());
     * 
     * */

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

    public String[] getAreas() {
	return areas;
    }

    public void setAreas(String[] areas) {
	this.areas = areas;
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

    public Integer getOperationId() {
	return operationId;
    }

    public void setOperationId(Integer operationId) {
	this.operationId = operationId;
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

    public List<OssStaMallRecord> getOssMallRecordList() {
	return ossMallRecordList;
    }

    public void setOssMallRecordList(List<OssStaMallRecord> ossMallRecordList) {
	this.ossMallRecordList = ossMallRecordList;
    }

    public OssStaMallRecord getSumMallRecord() {
        return sumMallRecord;
    }

    public void setSumMallRecord(OssStaMallRecord sumMallRecord) {
        this.sumMallRecord = sumMallRecord;
    }

    
}