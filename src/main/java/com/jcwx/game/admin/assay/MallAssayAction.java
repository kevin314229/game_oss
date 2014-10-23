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

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;


/**
 * 商城分析
 * 
 * @author Administrator
 * 
 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class MallAssayAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 开始时间 */
    private String beginDate;

    /** 结束时间 */
    private String endDate;

    private List<Map<String, Object>> list;

    /**
     * 所查询的表是否存在
     */
    private Boolean tableFlag;

    public String getBeginDate() {
	return beginDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public List<Map<String, Object>> getList() {
	return list;
    }

    public Boolean getTableFlag() {
	return tableFlag;
    }

    /**
     * 商城销售分析
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "mallSellAssay", results = { @Result(name = "success", location = "/admin/assay/mallSellAssay.jsp") })
    public String mallSellAssay() throws Exception {

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

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "MallAssayHandler");
	object = CONNECTION.sendMsg(object);

	tableFlag = (Boolean) object.get("tableFlag");
	list = (List) object.get("list");// 商城销售分析 mallId商品id,c数量

	return "success";
    }

    public void setBeginDate(String beginDate) {
	this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setList(List<Map<String, Object>> list) {
	this.list = list;
    }

    public void setTableFlag(Boolean tableFlag) {
	this.tableFlag = tableFlag;
    }

}
