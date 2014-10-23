package com.jcwx.game.admin.player;

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
import com.jcwx.game.domain.IPStats;


/** IP统计 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class IPStatsAction extends BasalAction {

    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private Integer allNum;

    /** 开始时间 */
    private String beginDate;

    /** 当前页数 */
    private Integer currPageNO;

    /** 结束时间 */
    private String endDate;

    private List<IPStats> ipstatsList;

    /** 每页记录数 */
    private Integer onePageNum;

    /** 排序标记 */
    private String orderFlag;

    /** 总页数 */
    private Integer pages;

    // @Autowired
    // private ILoginLogService loginLogService;

    /** IP统计 */
    @SuppressWarnings("unchecked")
    @Action(value = "iPStats_browseIP", results = { @Result(name = "success", location = "../../admin/player/ip/browseIP.jsp") })
    public String browseIP() {
	// 排序
	if (orderFlag == null || "".equals(orderFlag)) {
	    orderFlag = "visitNumDESC";
	}
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
	// ILoginLogService loginLogService =
	// (ILoginLogService)SpringService.getBean("loginLogService");
	// allNum = loginLogService.getIPStatsCount(beginTime, endTime);//总计录数
	if (onePageNum == null || currPageNO.intValue() <= 0) {
	    onePageNum = 20; // 每页20条记录
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;
	/** 修改 **/
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("orderFlag", orderFlag);
	object.put("beginNum", beginNum);
	object.put("onePageNum", onePageNum);
	object.put("handlerName", "IPStatsHandler");
	try {
	    object = CONNECTION.sendMsg(object);
	    allNum = (Integer) object.get("allNum");// 总记录数
	    ipstatsList = (List<IPStats>) object.get("ipstatsList");// ip 集合
	} catch (Exception e) {
	    e.printStackTrace();
	}

	// ipstatsList = loginLogService.getIPStats(beginTime, endTime,
	// orderFlag, beginNum, onePageNum);
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

    public String getBeginDate() {
	return beginDate;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<IPStats> getIpstatsList() {
	return ipstatsList;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public String getOrderFlag() {
	return orderFlag;
    }

    public Integer getPages() {
	return pages;
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

    public void setIpstatsList(List<IPStats> ipstatsList) {
	this.ipstatsList = ipstatsList;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOrderFlag(String orderFlag) {
	this.orderFlag = orderFlag;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

}
