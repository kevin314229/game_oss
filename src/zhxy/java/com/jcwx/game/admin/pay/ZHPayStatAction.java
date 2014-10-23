package com.jcwx.game.admin.pay;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.PayHistoryStat;
import com.jcwx.game.service.oss.IOssOperationService;


@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/zhxy/pay")
@ResultPath("/")
public class ZHPayStatAction extends BasalAction {

    /** 开始时间 */
    private String beginDate;

    /** 结束时间 */
    private String endDate;

    /** 统计的集合 */
    private List<PayHistoryStat> payHistoryStatList;
    
    @Autowired
    private IOssOperationService ossOperationService;
    
    /** 查询平台标识 */
    private List<OssOperation> ossOperationList;
    private String ptCode;

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<PayHistoryStat> getPayHistoryStatList() {
	return payHistoryStatList;
    }

    /**
     * 充值区间统计
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "payStat_assay", results = { @Result(name = "success", location = "../../zhxy/pay/payStatAssay.jsp") })
    public String payAssay() throws Exception {

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
	
	ossOperationList = ossOperationService.getOssOperationList();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	if(ptCode==null||"".equals(ptCode)){
	    object.put("ptCode", null);
	}else{
	    object.put("ptCode", ptCode);
	}
	object.put("handlerName", "PayStatHandler");
	object.put("methodName", "payAssay");
	object = CONNECTION.sendMsg(object);
	payHistoryStatList = (List<PayHistoryStat>) object
		.get("payHistoryStatList");
	return "success";

    }

    @Action(value = "payStat", results = { @Result(name = "success", location = "../../zhxy/pay/payStat.jsp") })
    public String payStat() throws Exception {
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
	ossOperationList=ossOperationService.getOssOperationList();
	Map<String, Object> object = new HashMap<String, Object>();
	if(ptCode==null||"".equals(ptCode)){
	    object.put("ptCode", null);
	}else{
	    object.put("ptCode", ptCode);
	}
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "PayStatHandler");
	object = CONNECTION.sendMsg(object);
	payHistoryStatList = (List<PayHistoryStat>) object
		.get("payHistoryStatList");

	return SUCCESS;

    }

    @Action(value = "payStat_Query", results = { @Result(name = "success", type = "json", location = "../../zhxy/pay/payStat.jsp") })
    public String payStatQuery() throws Exception {
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
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "PayStatHandler");
	object = CONNECTION.sendMsg(object);
	payHistoryStatList = (List<PayHistoryStat>) object
		.get("payHistoryStatList");

	return getPageMessage().ajaxForwardSuccess("操作成功");

    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public String getPtCode() {
        return ptCode;
    }

    public void setPtCode(String ptCode) {
        this.ptCode = ptCode;
    }

    public void setPayHistoryStatList(List<PayHistoryStat> payHistoryStatList) {
        this.payHistoryStatList = payHistoryStatList;
    }

    public List<OssOperation> getOssOperationList() {
        return ossOperationList;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
        this.ossOperationList = ossOperationList;
    }
    
}
