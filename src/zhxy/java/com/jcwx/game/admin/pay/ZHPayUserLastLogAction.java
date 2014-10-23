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
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.PayUserLastLoginInfo;
import com.jcwx.game.service.oss.impl.OssUserServerService;


@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/zhxy/payUser")
@ResultPath("/")
public class ZHPayUserLastLogAction extends BasalAction {

    private Integer areaId;
    private String areaIds;
    private Date compareDate;
    private Integer gameId;
    private List<OssServer> operationPtList;
    private List<OssServer> ossServerList;
    @Autowired
    private OssUserServerService ossUserServerService;
    private List<PayUserLastLoginInfo> payUserLastLoginInfoList;
    private String ptId;

    /** 大区列表 */
    private String[] selectArray;

    private Map<String, String> serverMap;

    @Override
    @SuppressWarnings("unchecked")
    @Action(value = "ZHpayUserLastLoginInfo_index", results = { @Result(name = "success", location = "../../zhxy/pay/payUserLastLoginInfo.jsp") })
    public String execute() throws Exception {
	if (compareDate == null) {
	    compareDate = DateService.getCurrentUtilDate();
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("compareDate", compareDate);
	object.put("handlerName", "QueryPayRankHandler");
	object.put("methodName", "payUserLastLoginIfo");
	object = CONNECTION.sendMsg(object);
	payUserLastLoginInfoList = (List<PayUserLastLoginInfo>) object
		.get("payUserLastLoginInfoList");
	return SUCCESS;
    }

    public Integer getAreaId() {
	return areaId;
    }

    public Date getCompareDate() {
	return compareDate;
    }

    public Integer getGameId() {
	return gameId;
    }

    public List<OssServer> getOperationPtList() {
	return operationPtList;
    }

    public List<OssServer> getOssServerList() {
	return ossServerList;
    }

    public List<PayUserLastLoginInfo> getPayUserLastLoginInfoList() {
	return payUserLastLoginInfoList;
    }

    public String getPtId() {
	return ptId;
    }

    public String[] getSelectArray() {
	return selectArray;
    }

    public Map<String, String> getServerMap() {
	return serverMap;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setCompareDate(Date compareDate) {
	this.compareDate = compareDate;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setOperationPtList(List<OssServer> operationPtList) {
	this.operationPtList = operationPtList;
    }

    public void setOssServerList(List<OssServer> ossserverList) {
	ossServerList = ossserverList;
    }

    public void setPayUserLastLoginInfoList(
	    List<PayUserLastLoginInfo> payUserLastLoginInfoList) {
	this.payUserLastLoginInfoList = payUserLastLoginInfoList;
    }

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

    public void setSelectArray(String[] selectArray) {
	this.selectArray = selectArray;
    }

    public void setServerMap(Map<String, String> serverMap) {
	this.serverMap = serverMap;
    }

}
