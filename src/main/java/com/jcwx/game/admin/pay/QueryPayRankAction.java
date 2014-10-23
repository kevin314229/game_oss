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

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.PayHistoryRank;


@ParentPackage("base")
@Namespace("/admin/pay")
@ResultPath("/")
@Action(value = "queryPayRank", results = { @Result(name = "success", location = "../../admin/pay/queryPayRank.jsp") })
public class QueryPayRankAction extends BasalAction {

    /** 总记录数 */
    private Integer allNum;
    /** 开始时间 */
    private String beginDate;
    /** 当前页数 */
    private Integer currPageNO;

    /** 结束时间 */
    private String endDate;

    private String loginName;

    /** 每页记录数 */
    private Integer onePageNum;

    /** 总页数 */
    private Integer pages;

    /** 充值集合 */
    private List<PayHistoryRank> payHistoryRankList;

    private Integer playerId;

    @Override
    @SuppressWarnings("unchecked")
    public String execute() throws Exception {
	// 开始时间和结束时间
	Date beginTime = null, endTime = null;
	if (beginDate != null && !"".equals(beginDate)) {
	    beginTime = DateService.getDateFirstTime(beginDate);
	}
	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 20;
	}

	// IPayHistoryService payHistoryService =
	// (IPayHistoryService)SpringService.getBean("payHistoryService");
	// onePageNum = 20; //每页20条记录
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("beginNum", beginNum);
	object.put("onePageNum", onePageNum);
	object.put("handlerName", "QueryPayRankHandler");
	object = CONNECTION.sendMsg(object);
	allNum = (Integer) object.get("allNum"); // 总记录数
	payHistoryRankList = (List<PayHistoryRank>) object
		.get("payHistoryRankList");// 充值集合
	// allNum = payHistoryService.getPayHistoryRankNum(beginTime,
	// endTime).intValue();
	// payHistoryRankList =
	// payHistoryService.getPayHistoryRankList(beginTime, endTime, beginNum,
	// onePageNum);
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
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

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public Integer getPages() {
	return pages;
    }

    public List<PayHistoryRank> getPayHistoryRankList() {
	return payHistoryRankList;
    }

    public Integer getPlayerId() {
	return playerId;
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

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

}
