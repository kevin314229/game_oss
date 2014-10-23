package com.jcwx.game.admin.assay;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BaseInfoAction;
import com.jcwx.game.admin.vo.PageMessage;
import com.jcwx.game.common.DateService;
import com.jcwx.game.http.domain.OssZBossDay;


/** 击杀boss数据分析 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class ZBossDayAction extends BaseInfoAction {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 总记录数 */
    private Integer allNum;
    // 开始时间
    private Date beginTime;
    /** 当前页数 */
    private Integer currPageNO;
    // 结束时间
    private Date endTime;

    /** 每页记录数 */
    private Integer onePageNum;

    /** 排序标记 */
    private String orderFlag;

    private List<OssZBossDay> ossZBossDayList = new ArrayList<OssZBossDay>();
    /** 总页数 */
    private Integer pages;
    private PageMessage pageMessage = PageMessage.getOkMessage();

    public Integer getAllNum() {
	return allNum;
    }

    public Date getBeginTime() {
	return new Date(beginTime.getTime());
	// return beginTime;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public Date getEndTime() {
	return new Date(endTime.getTime());
	// return endTime;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public String getOrderFlag() {
	return orderFlag;
    }

    public List<OssZBossDay> getOssZBossDayList() {
	return ossZBossDayList;
    }

    @Action(value = "zBossDay_index", results = { @Result(name = "success", location = "../../admin/assay/zBossDayAssay.jsp") })
    public String query() throws Exception {
	/* 开始时间和结束时间 */
	if (beginTime == null) {

	    beginTime = DateService.getCurrentMonthFirstDay();
	}
	if (endTime == null) {
	    endTime = new java.util.Date();
	}
	// 排序
	if (orderFlag == null || "".equals(orderFlag)) {
	    orderFlag = "visitNumDESC";
	}
	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 20;
	}
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0) {
	    beginNum = 0;
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	// object.put("orderFlag", orderFlag);
	object.put("beginNum", beginNum);
	object.put("onePageNum", onePageNum);
	// object.put("allNum", allNum);
	object.put("handlerName", "ZBossDayHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		allNum = (Integer) object.get("allNum");// 总记录数
		ossZBossDayList = (List<OssZBossDay>) object
			.get("ossZBossDayList");
		pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1
			: allNum / onePageNum;
		// 当前页设置
		if (currPageNO.intValue() > pages) {
		    currPageNO = pages;
		}
	    }
	    return SUCCESS;
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return SUCCESS;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setBeginTime(Date beginTime) {
	this.beginTime = beginTime;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOrderFlag(String orderFlag) {
	this.orderFlag = orderFlag;
    }

    public void setOssZBossDayList(List<OssZBossDay> ossZBossDayList) {
	this.ossZBossDayList = ossZBossDayList;
    }

    public PageMessage getPageMessage() {
        return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
    }
}
