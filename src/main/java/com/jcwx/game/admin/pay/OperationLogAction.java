package com.jcwx.game.admin.pay;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
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
import com.jcwx.game.http.domain.OssOperationLogAssay;


/**
 * 消费类型统计
 * 
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/pay")
@ResultPath("/")
public class OperationLogAction extends BaseInfoAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    // 开始时间
    private Date beginTime;
    // 结束时间
    private Date endTime;

    private List<OssOperationLogAssay> ossOperationLogAssayList;

    // 消费类型
    private String target;
    private PageMessage pageMessage = PageMessage.getOkMessage();

    public Date getBeginTime() {
	return beginTime;
    }

    public Date getEndTime() {
	return endTime;
    }

    public List<OssOperationLogAssay> getOssOperationLogAssayList() {
	return ossOperationLogAssayList;
    }

    public String getTarget() {
	return target;
    }

    @Action(value = "operationLog_index", results = { @Result(name = "success", location = "../../admin/pay/operationLog.jsp") })
    public String query() throws Exception {
	if (beginTime == null) {
	    beginTime = new java.util.Date();
	}
	beginTime = DateService.getDateFirstTime(beginTime);

	if (endTime == null) {
	    endTime = new java.util.Date();
	}
	endTime = DateService.getDateLastTime(endTime);
	if (target == null) {
	    target = "addGold";
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("target", target);
	object.put("handlerName", "QueryMoneyConsumeHandler");
	object.put("methodName", "payAssay");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		long AllGold = Long.valueOf(object.get("AllGold").toString());
		ossOperationLogAssayList = (List<OssOperationLogAssay>) object
			.get("ossOperationLogAssayList");
		Collections.sort(ossOperationLogAssayList,
			new Comparator<OssOperationLogAssay>() {
			    @Override
			    public int compare(OssOperationLogAssay p2,
				    OssOperationLogAssay p1) {
				return (int) (p1.getSum() - p2.getSum());
			    }
			});

		OssOperationLogAssay operationLogAssay = new OssOperationLogAssay();
		for (OssOperationLogAssay ossOperationLogAssay : ossOperationLogAssayList) {
		    operationLogAssay.setSum(operationLogAssay.getSum()
			    + ossOperationLogAssay.getSum());
		    operationLogAssay.setNub(operationLogAssay.getNub()
			    + ossOperationLogAssay.getNub());
		    BigDecimal e = new BigDecimal(
			    ((double) ossOperationLogAssay.getSum() / AllGold) * 100);
		    ossOperationLogAssay.setRatio(e.setScale(2,
			    BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		operationLogAssay.setOperation("total");
		operationLogAssay.setRatio(100);
		ossOperationLogAssayList.add(operationLogAssay);
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

    public void setOssOperationLogAssayList(
	    List<OssOperationLogAssay> ossOperationLogAssayList) {
	this.ossOperationLogAssayList = ossOperationLogAssayList;
    }

    public void setTarget(String target) {
	this.target = target;
    }

    public PageMessage getPageMessage() {
        return this.pageMessage;
    }

    public void setPageMessage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
    }

}
