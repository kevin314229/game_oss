package com.jcwx.game.admin.assay;

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
import com.jcwx.game.http.domain.OssZAmuletDay;


@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class ZAmuletDayAction extends BaseInfoAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    // 开始时间
    private Date beginTime;
    // 结束时间
    private Date endTime;

    private List<OssZAmuletDay> ossZAmuletDayList;
    private PageMessage pageMessage = PageMessage.getOkMessage();

    public Date getBeginTime() {
	return beginTime;
    }

    public Date getEndTime() {
	return endTime;
    }

    public List<OssZAmuletDay> getOssZAmuletDayList() {
	return ossZAmuletDayList;
    }

    @Action(value = "zAmuletDay_index", results = { @Result(name = "success", location = "../../admin/assay/zAmuletDayAssay.jsp") })
    public String query() throws Exception {
	if (beginTime == null) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	} else {
	    beginTime = DateService.getDateFirstTime(beginTime);
	}
	if (endTime == null) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	} else {
	    endTime = DateService.getDateLastTime(endTime);
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "ZAmuletDayHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossZAmuletDayList = (List<OssZAmuletDay>) object
			.get("ossZAmuletDayList");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setBeginTime(Date beginTime) {
	this.beginTime = beginTime;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public void setOssZAmuletDayList(List<OssZAmuletDay> ossZAmuletDayList) {
	this.ossZAmuletDayList = ossZAmuletDayList;
    }

    public PageMessage getPageMessage() {
        return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
    }

}
