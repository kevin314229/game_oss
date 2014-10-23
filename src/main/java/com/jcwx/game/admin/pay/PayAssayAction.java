package com.jcwx.game.admin.pay;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.PerformanceTimer;
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.ZPayDay;
import com.jcwx.game.http.domain.OssOperationServer;
import com.jcwx.game.http.domain.ZPayDayDto;
import com.jcwx.game.service.oss.IOperationServerServer;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.util.ChineseCharToEn;


@ParentPackage("base")
@Namespace("/admin/pay")
@ResultPath("/")
public class PayAssayAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 开始时间 */
    private String beginDate;

    /** 查询总充值数 */
    private Double countRmb;

    /** 结束时间 */
    private String endDate;
    /** 运营商平台关联server **/
    @Autowired
    private IOperationServerServer operationServerServer;

    private List<OssOperation> ossOperationList;

    /** 运营商关联的服务器 **/
    private List<OssOperationServer> ossOperationServerList;

    /** 运营商 平台服务器 */
    @Autowired
    private IOssOperationService ossOperationService;

    /** 服务器列表 */
    private List<OssServer> ossServersList;

    /** 平台code **/
    private String ptCode;

    /** 平台ID **/
    private String ptId;

    /** 平台名字 */
    private String ptName;
    private String resultJson;

    /** 大区列表 */
    private Integer[] selectArray;

    private List<ZPayDayDto> zpayDayDtoList;

    public String getBeginDate() {
	return beginDate;
    }

    public Double getCountRmb() {
	return countRmb;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<OssOperation> getOssOperationList() {
	return ossOperationList;
    }

    public List<OssOperationServer> getOssOperationServerList() {
	return ossOperationServerList;
    }

    public List<OssServer> getOssServersList() {
	return ossServersList;
    }

    public String getPtCode() {
	return ptCode;
    }

    public String getPtId() {
	return ptId;
    }

    public String getPtName() {
	return ptName;
    }

    public String getResultJson() {
	return resultJson;
    }

    public Integer[] getSelectArray() {
	return selectArray;
    }

    public List<ZPayDayDto> getZpayDayDtoList() {
	return zpayDayDtoList;
    }

    /**
     * 充值分析
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "payAssay", results = { @Result(name = "success", location = "../../admin/pay/payAssay.jsp") })
    public String payAssay() throws Exception {

	PerformanceTimer timer = new PerformanceTimer();

	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	ossServersList = getBaseAdminContext().getOssServers();
	Integer serverArray[] = new Integer[ossServersList.size()];
	for (int i = 0; i < ossServersList.size(); i++) {
	    OssServer ossServer = ossServersList.get(i);
	    serverArray[i] = ossServer.getId();
	}
	if (selectArray == null || selectArray.length == 0)
	    return "success";

	if (selectArray[0] == -1) { // -1 为全选
	    selectArray = serverArray;
	}
	countRmb = 0.0;
	StringBuffer buf = new StringBuffer();
	setRemoteRunTime(0L);
	setContentLength(0);
	zpayDayDtoList = new ArrayList<ZPayDayDto>();
	for (Integer id : selectArray) {
	    OssServer ossServer = getBaseAdminContext().getOssServerById(id);
	    if (ossServer == null) {
		buf.append("<span class='color-red'>serverId" + id
			+ " not find  </span>");
		continue;
	    }
	    try {
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("beginTime", beginTime);
		object.put("endTime", endTime);
		object.put("handlerName", "AssayPayHandler");
		object.put("methodName", "payAssay");
		object = CONNECTION.interfaceSendMsg(id, object);
		long rtime = (Long) object.get("remoteRunTime");
		super.handleKryoMap(object);

		List<ZPayDay> list = (List<ZPayDay>) object.get("zPayDayList");

		// 封装计算所需数据
		for (ZPayDay z : list) {
		    ZPayDayDto dto = new ZPayDayDto();
		    BeanUtils.copyProperties(dto, z);
		    dto.setServerName(ossServer.getName());
		    dto.setArpu(dto.getLoginTotal() == 0 ? 0.0 : dto
			    .getMoneyTotal() * 1.0 / dto.getLoginTotal());
		    dto.setArppu(dto.getPayUserNum() == 0 ? 0.0 : dto
			    .getMoneyTotal() * 1.0 / dto.getPayUserNum());
		    dto.setActivePayRate(dto.getLoginTotal() == 0 ? 0.0 : dto
			    .getPayUserNum() * 1.0 / dto.getLoginTotal());
		    dto.setSinglePay(dto.getPayTimes() == 0 ? 0.0 : dto
			    .getMoneyTotal() * 1.0 / dto.getPayTimes());
		    zpayDayDtoList.add(dto);
		    countRmb += dto.getMoneyTotal();
		}

		buf.append("<span class='color-gr'>" + ossServer.getName()
			+ "</span> success " + rtime + "(ms)  ");
	    } catch (Exception e) {
		// e.printStackTrace();
		buf.append("<span class='color-red'>" + ossServer.getName()
			+ "</span> fail " + "error:" + e.getMessage() + " ");
	    }
	}

	setActionMsg(buf.toString());

	setLocalRunTime(timer.get());

	return "success";

    }

    /**
     * 充值分析
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Action(value = "payAssay_ptId", results = { @Result(name = "success", location = "../../admin/pay/payAssay_ptId.jsp") })
    public String payAssay_pt() throws Exception {
	PerformanceTimer timer = new PerformanceTimer();
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}
	ossOperationList = ossOperationService.getOssOperationList();
	List<Map> result = new ArrayList<Map>();
	for (OssOperation key : ossOperationList) {
	    Map temp = new HashMap();
	    temp.put("code", key.getCarrierOperator());
	    temp.put("name", key.getOperationName());
	    temp.put("help",
		    ChineseCharToEn.getChinesePy(key.getOperationName())
			    .toUpperCase(getLocale()));
	    result.add(temp);
	}
	setResultJson(JSON.toJSONString(result));
	ossServersList = getBaseAdminContext().getOssServers();
	Integer serverArray[] = new Integer[ossServersList.size()];
	for (int i = 0; i < ossServersList.size(); i++) {
	    OssServer ossServer = ossServersList.get(i);
	    serverArray[i] = ossServer.getId();
	}
	if (selectArray == null || selectArray.length == 0) {
	    return "success";
	}
	if (selectArray[0] == -1) {
	    selectArray = serverArray;
	}

	countRmb = 0.0;
	StringBuffer buf = new StringBuffer();
	setRemoteRunTime(0L);
	setContentLength(0);
	zpayDayDtoList = new ArrayList<ZPayDayDto>();
	for (Integer id : selectArray) {
	    OssServer ossServer = getBaseAdminContext().getOssServerById(id);
	    if (ossServer == null) {
		buf.append("<span class='color-red'>serverId" + id
			+ " not find  </span>");
		continue;
	    }
	    try {
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("beginTime", beginTime);
		object.put("endTime", endTime);
		object.put("operationCode", ptCode);
		object.put("handlerName", "AssayPayHandler");
		object.put("methodName", "ptPayAssay");
		object = CONNECTION.interfaceSendMsg(id, object);
		long rtime = (Long) object.get("remoteRunTime");
		super.handleKryoMap(object);
		List<ZPayDay> list = (List<ZPayDay>) object.get("zPayDayList");
		// 封装计算所需数据
		for (ZPayDay z : list) {
		    ZPayDayDto dto = new ZPayDayDto();
		    BeanUtils.copyProperties(dto, z);
		    dto.setServerName(ossServer.getName());
		    dto.setArpu(dto.getPayUserNum() == 0 ? 0.0 : dto
			    .getMoneyTotal() * 1.0 / dto.getPayUserNum());
		    zpayDayDtoList.add(dto);
		    countRmb += dto.getMoneyTotal();
		}
		buf.append("<span class='color-gr'>" + ossServer.getName()
			+ "</span> success " + rtime + "(ms)  ");
	    } catch (Exception e) {
		// e.printStackTrace();
		buf.append("<span class='color-red'>" + ossServer.getName()
			+ "</span> fail " + "error:" + e.getMessage() + " ");
	    }
	}

	setActionMsg( buf.toString());

	setLocalRunTime(timer.get());

	return "success";

    }

    /**
     * 充值分析
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "payAssay_pt", results = { @Result(name = "success", location = "../../admin/pay/ptPayAssay.jsp") })
    public String ptPayAssay() throws Exception {
	PerformanceTimer timer = new PerformanceTimer();
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	} else {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}
	ossServersList = getBaseAdminContext().getOssServers();
	Integer serverArray[] = new Integer[ossServersList.size()];
	for (int i = 0; i < ossServersList.size(); i++) {
	    OssServer ossServer = ossServersList.get(i);
	    serverArray[i] = ossServer.getId();
	}
	if (selectArray == null || selectArray.length == 0) {
	    return "success";
	}
	if (selectArray[0] == -1) {
	    selectArray = serverArray;
	}
	countRmb = 0.0;
	StringBuffer buf = new StringBuffer();
	setRemoteRunTime(0L);
	setContentLength(0);
	zpayDayDtoList = new ArrayList<ZPayDayDto>();
	for (Integer id : selectArray) {
	    OssServer ossServer = getBaseAdminContext().getOssServerById(id);
	    if (ossServer == null) {
		buf.append("<span class='color-red'>serverId" + id
			+ " not find  </span>");
		continue;
	    }
	    try {
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("beginTime", beginTime);
		object.put("endTime", endTime);
		object.put("operationCode", getBaseAdminContext().getOssUser()
			.getCarrierOperator());
		object.put("handlerName", "AssayPayHandler");
		object.put("methodName", "ptPayAssay");
		object = CONNECTION.interfaceSendMsg(id, object); 
		long rtime = (Long) object.get("remoteRunTime");
		super.handleKryoMap(object);
		List<ZPayDay> list = (List<ZPayDay>) object.get("zPayDayList");
		// 封装计算所需数据
		for (ZPayDay z : list) {
		    ZPayDayDto dto = new ZPayDayDto();
		    BeanUtils.copyProperties(dto, z);
		    dto.setServerName(ossServer.getName());
		    dto.setArpu(dto.getPayUserNum() == 0 ? 0.0 : dto
			    .getMoneyTotal() * 1.0 / dto.getPayUserNum());
		    zpayDayDtoList.add(dto);
		    countRmb += dto.getMoneyTotal();
		}
		buf.append("<span class='color-gr'>" + ossServer.getName()
			+ "</span> success " + rtime + "(ms)  ");
	    } catch (Exception e) {
		// e.printStackTrace();
		buf.append("<span class='color-red'>" + ossServer.getName()
			+ "</span> fail " + "error:" + e.getMessage() + " ");
	    }
	}

	setActionMsg(buf.toString());

	setLocalRunTime(timer.get());

	return "success";

    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setCountRmb(Double countRmb) {
	this.countRmb = countRmb;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
	this.ossOperationList = ossOperationList;
    }

    public void setOssOperationServerList(
	    List<OssOperationServer> ossOperationServerList) {
	this.ossOperationServerList = ossOperationServerList;
    }

    public void setOssServersList(List<OssServer> ossServersList) {
	this.ossServersList = ossServersList;
    }

    public void setPtCode(String ptCode) {
	this.ptCode = ptCode;
    }

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

    public void setPtName(String ptName) {
	this.ptName = ptName;
    }

    public void setResultJson(String resultJson) {
	this.resultJson = resultJson;
    }

    public void setSelectArray(Integer[] selectArray) {
	this.selectArray = selectArray;
    }

    public void setZpayDayDtoList(List<ZPayDayDto> zpayDayDtoList) {
	this.zpayDayDtoList = zpayDayDtoList;
    }

}
