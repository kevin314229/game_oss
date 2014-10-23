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
import com.jcwx.game.domain.ZCopynubDay;


/**
 * <精英地下城>副本进入人数统计
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class ZCopynubAction extends BaseInfoAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    // 开始时间
    private Date beginTime;
    private List<ZCopynubDay> copynubDays = new ArrayList<ZCopynubDay>();

    // 结束时间
    private Date endTime;

    private PageMessage pageMessage = PageMessage.getOkMessage();

    public Date getBeginTime() {
	return beginTime;
    }

    public List<ZCopynubDay> getCopynubDays() {
	return copynubDays;
    }

    public Date getEndTime() {
	return endTime;
    }

    @Action(value = "copynub_index", results = { @Result(name = "success", location = "../../admin/assay/zCopynubDayAssay.jsp") })
    public String query() throws Exception {
	if (beginTime == null) {

	    beginTime = DateService.getCurrentMonthFirstDay();
	}
	if (endTime == null) {
	    endTime = new java.util.Date();
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "ZCopynubHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		copynubDays = (List<ZCopynubDay>) object.get("ossZCopynubDays");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setBeginTime(Date beginTime) {
	this.beginTime = beginTime;
    }

    public void setCopynubDays(List<ZCopynubDay> copynubDays) {
	this.copynubDays = copynubDays;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public PageMessage getPageMessage() {
        return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
    }

}
