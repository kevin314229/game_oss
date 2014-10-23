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
import com.jcwx.game.http.domain.OssPlayerLastoperations;


/** 功能节点查询 */
@ParentPackage("base")
@Namespace("/zhxy/player")
@ResultPath("/")
public class ZHPlayerLastoperationsAction extends BasalAction {

    private static final long serialVersionUID = -70539059362263612L;

    // 开始时间
    private String beginDate;
    // 结束时间
    private String endDate;

    private List<OssPlayerLastoperations> ossPlayerLastoperationsList;

    @SuppressWarnings("unchecked")
    @Action(value = "ZHFunctionNode", results = { @Result(name = "success", location = "../../zhxy/player/getFunctionNode.jsp") })
    public String FunctionNode() throws Exception {

	Date beginTime = new Date();
	Date endTime = new Date();

	if (beginDate == null) {
	    beginTime = DateService.getCurrentDayFirstUtilDate();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	} else {
	    beginTime = DateService.getDateFirstTime(beginDate);
	}
	if (endDate == null) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "PlayerLastOperationsHandler");
	object = CONNECTION.sendMsg(object);
	try {
	    if (object != null) {
		ossPlayerLastoperationsList = (List<OssPlayerLastoperations>) object
			.get("ossPlayerLastoperations");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return SUCCESS;
    }

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<OssPlayerLastoperations> getOssPlayerLastoperationsList() {
	return ossPlayerLastoperationsList;
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setOssPlayerLastoperationsList(
	    List<OssPlayerLastoperations> ossPlayerLastoperationsList) {
	this.ossPlayerLastoperationsList = ossPlayerLastoperationsList;
    }

}
