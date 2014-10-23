package com.jcwx.game.admin.assay;

import java.math.BigDecimal;
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
import com.jcwx.game.http.domain.OssZItemUse;


/**
 * 物品消耗统计
 * 
 * @author Administrator 2013-10-16
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class ZItemUseAction extends BaseInfoAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    // 开始时间
    private Date beginTime;
    // 结束时间
    private Date endTime;

    private List<OssZItemUse> ossZItemUseList;

    private PageMessage pageMessage = PageMessage.getOkMessage();

    public Date getBeginTime() {
	return beginTime;
    }

    public Date getEndTime() {
	return endTime;
    }

    public List<OssZItemUse> getOssZItemUseList() {
	return ossZItemUseList;
    }

    @Action(value = "zItemUse_index", results = { @Result(name = "success", location = "../../admin/assay/zItemUse.jsp") })
    public String query() throws Exception {
	if (beginTime == null) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	} else {
	    beginTime = DateService.getDateFirstTime(beginTime);
	}
	if (endTime == null) {
	    endTime = DateService.getDateLastTime(new java.util.Date());
	} else {
	    endTime = DateService.getDateLastTime(endTime);
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "ZItemUseHandler");
	try {

	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossZItemUseList = (List<OssZItemUse>) object
			.get("ossZItemUseList");
		Integer allcNub = Integer.valueOf(object.get("allcNub")
			.toString());
		Integer allTotleFee = Integer.valueOf(object.get("allTotleFee")
			.toString());
		Integer allplyaerBaseNub = Integer.valueOf(object.get(
			"allplyaerBaseNub").toString());
		// 设置所占比
		for (int i = 0; i < ossZItemUseList.size(); i++) {
		    OssZItemUse ossZItemUse = ossZItemUseList.get(i);
		    ossZItemUse.setServerName(getBaseAdminContext().getName());
		    BigDecimal e = new BigDecimal(
			    ((double) ossZItemUse.getcNub() / allcNub) * 100);
		    ossZItemUse.setcNubScale(e.setScale(2,
			    BigDecimal.ROUND_HALF_UP).doubleValue());
		    BigDecimal g = new BigDecimal(
			    ((double) ossZItemUse.getPlyaerBaseNub() / allplyaerBaseNub) * 100);
		    ossZItemUse.setPlayerBaseScale(g.setScale(2,
			    BigDecimal.ROUND_HALF_UP).doubleValue());
		    if (ossZItemUse.getTotleFee() != null
			    && ossZItemUse.getTotleFee() != 0) {
			BigDecimal f = new BigDecimal(
				((double) ossZItemUse.getTotleFee() / allTotleFee) * 100);
			ossZItemUse.setTotleScale(f.setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue());
		    } else {
			ossZItemUse.setTotleFee(null);
			ossZItemUse.setTotleScale(0);
			ossZItemUse.setPrice(null);
		    }
		}
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

    public void setOssZItemUseList(List<OssZItemUse> ossZItemUseList) {
	this.ossZItemUseList = ossZItemUseList;
    }

    public PageMessage getPageMessage() {
        return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
    }

}
