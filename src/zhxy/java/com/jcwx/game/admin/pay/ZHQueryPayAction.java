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
import com.jcwx.game.domain.PayHistoryNew;
import com.jcwx.game.service.oss.IOssOperationService;


@ParentPackage("base")
@Namespace("/zhxy/pay")
@ResultPath("/")
@Action(value = "queryPay", results = { @Result(name = "success", location = "../../zhxy/pay/queryPay.jsp") })
public class ZHQueryPayAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private Integer allNum;
    /** 开始时间 */
    private String beginDate;

    /** 当前页数 */
    private Integer currPageNO;

    /** 结束时间 */
    private String endDate;

    // 登录名
    private String loginName;

    // 角色名
    private String nickName;

    /** 每页记录数 */
    private Integer onePageNum;

    /** 总页数 */
    private Integer pages;

    /** 充值集合 */
    private List<PayHistoryNew> payHistoryList;
    @Autowired
    private IOssOperationService ossOperationService;
    
    /** 查询平台标识 */
    private List<OssOperation> ossOperationList;
    private String ptCode;
    
    private String ptOrder;
    @Override
    @SuppressWarnings("unchecked")
    public String execute() throws Exception {

	// 开始时间和结束时间
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
	if (nickName != null && loginName != null && !nickName.equals("")
		&& !loginName.equals("")) {
	    setErrorInfo("帐号和角色名只能填写一个!");
	    return "success";
	}
	ossOperationList=ossOperationService.getOssOperationList();
	// 每页记录数
	if (onePageNum == null || getOnePageNum().intValue() == 0) {
	    onePageNum = 20; // 每页20条记录
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;
	// 充值集合
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("loginName", loginName);
	object.put("nickName", nickName);
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("beginNum", beginNum);
	if(ptCode==null||"".equals(ptCode)){
	    object.put("ptCode", null);
	}else{
	    object.put("ptCode", ptCode);
	}
	if(ptOrder==null||"".equals(ptOrder)){
	    object.put("ptOrder", null);
	}else{
	    object.put("ptOrder", ptOrder);
	}
	object.put("onePageNum", onePageNum);
	object.put("handlerName", "QueryPayHandler");
	object = CONNECTION.sendMsg(object);
	if (object.containsKey("payHistoryNewList")
		&& object.containsKey("allNum")) {
	    allNum = (Integer) object.get("allNum");
	    payHistoryList = (List<PayHistoryNew>) object
		    .get("payHistoryNewList");
	    pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		    / onePageNum;
	    // 当前页设置
	    if (currPageNO.intValue() > pages) {
		currPageNO = pages;
	    }
	}
	return "success";
    }

    public Integer getAllNum() {
	return allNum;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public String getEndDate() {
	return endDate;
    }

    public String getLoginName() {
	return loginName;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public Integer getPages() {
	return pages;
    }

    public List<PayHistoryNew> getPayHistoryList() {
	return payHistoryList;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setPayHistoryList(List<PayHistoryNew> payHistoryList) {
	this.payHistoryList = payHistoryList;
    }

    public List<OssOperation> getOssOperationList() {
        return ossOperationList;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
        this.ossOperationList = ossOperationList;
    }

    public String getPtCode() {
        return ptCode;
    }

    public void setPtCode(String ptCode) {
        this.ptCode = ptCode;
    }

    public String getPtOrder() {
        return ptOrder;
    }

    public void setPtOrder(String ptOrder) {
        this.ptOrder = ptOrder;
    }

}
