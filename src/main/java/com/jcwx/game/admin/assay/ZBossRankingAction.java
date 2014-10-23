package com.jcwx.game.admin.assay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.ibm.icu.text.SimpleDateFormat;
import com.jcwx.game.admin.BaseInfoAction;
import com.jcwx.game.admin.vo.PageMessage;
import com.jcwx.game.common.DateService;
import com.jcwx.game.http.domain.OssZBossRanking;


/** 击杀boss数据排行 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class ZBossRankingAction extends BaseInfoAction {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    // 开始时间
    private String beginTime;
    // 结束时间
    private String endTime;

    private List<OssZBossRanking> ossZBossRankingList;

    private PageMessage pageMessage = PageMessage.getOkMessage();

    public String getBeginTime() {
	return beginTime;
    }

    public String getEndTime() {
	return endTime;
    }

    public List<OssZBossRanking> getOssZBossRankingList() {
	return ossZBossRankingList;
    }

    @Action(value = "zBossRanking_index", results = { @Result(name = "success", location = "../../admin/assay/zBossRanking.jsp") })
    public String query() throws Exception {
	if (beginTime == null) {
	    beginTime = DateService.getDateFormatStr(
		    DateService.getCurrentDayFirstUtilDate(),
		    "yyyy-MM-dd HH:ss:mm");
	}
	if (endTime == null) {
	    endTime = DateService.getDateFormatStr(
		    DateService.getCurrentDayLastUtilDate(),
		    "yyyy-MM-dd HH:ss:mm");
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", sdf.parse(beginTime));
	object.put("endTime", sdf.parse(endTime));
	object.put("handlerName", "ZBossRankingHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossZBossRankingList = (List<OssZBossRanking>) object
			.get("ossZBossRankingList");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setBeginTime(String beginTime) {
	this.beginTime = beginTime;
    }

    public void setEndTime(String endTime) {
	this.endTime = endTime;
    }

    public void setOssZBossRankingList(List<OssZBossRanking> ossZBossRankingList) {
	this.ossZBossRankingList = ossZBossRankingList;
    }

    public PageMessage getPageMessage() {
        return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
    }

}
