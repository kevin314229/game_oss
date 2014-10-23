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
import com.jcwx.game.http.domain.OssZPlayerTrain;


/**
 * 日常培养次数统计
 * 
 * @author Administrator 2013-10-16
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class ZPlayerTrainAction extends BaseInfoAction {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    // 开始时间
    private Date beginTime;
    // 结束时间
    private Date endTime;

    private List<OssZPlayerTrain> ossZPlayerTrainList;
    private PageMessage pageMessage = PageMessage.getOkMessage();

    public Date getBeginTime() {
	return beginTime;
    }

    public Date getEndTime() {
	return endTime;
    }

    public List<OssZPlayerTrain> getOssZPlayerTrainList() {
	return ossZPlayerTrainList;
    }

    @Action(value = "zPlayerTrain_index", results = { @Result(name = "success", location = "../../admin/assay/zPlayerTrainAssay.jsp") })
    public String query() throws Exception {
	if (beginTime == null) {
	    beginTime = DateService.getDateFirstTime(new java.util.Date());
	}
	if (endTime == null) {
	    endTime = DateService.getDateFirstTime(new java.util.Date());
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "ZPlayerTrainHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossZPlayerTrainList = (List<OssZPlayerTrain>) object
			.get("ossZPlayerTrainList");
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

    public void setOssZPlayerTrainList(List<OssZPlayerTrain> ossZPlayerTrainList) {
	this.ossZPlayerTrainList = ossZPlayerTrainList;
    }

    public PageMessage getPageMessage() {
        return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
    }

}
