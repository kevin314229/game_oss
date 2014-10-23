package com.jcwx.game.admin.assay;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSONArray;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.AdminSystemConstant;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.OssContext;
import com.jcwx.game.util.ConnectionUtil;
import com.jcwx.game.util.Page;

/*
 * 嘟嘟熊玩家查询
 */
@ParentPackage("base")
@Namespace("/dudubear/assay")
@ResultPath("/")
public class DudubearPlayerAction extends BasalAction {

    
    // 分页控件
    private Page page = Page.createPageCurrentOneSize(1,20);
    
    // 渠道id
    private String opeatorId;

    private Date beginTime;

    private Date endTime;
    //省份
    private String province;
    //地区
    private String city;
    
    private JSONArray jsonarrayArray;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Action(value = "dudubear_player", results = { @Result(name = "success", location = "/dudubear/assay/dudubearPlayer.jsp") })
    public String dudubearPlayer() throws Exception {

	if (beginTime == null) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	}
	if (endTime == null) {
	    endTime = new java.util.Date();
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("opeatorId", opeatorId);
	object.put("province",province==null?null:province.equals("请选择省份名")?null:province);
	object.put("city", city==null?null:city.equals("请选择城市名")?null:city);
	object.put("beginNum", page.getBeginNum());
	object.put("onePageNum", page.getOnePageNum());
	object.put("handlerName", "playerHandler");
	OssContext.getBaseAdminContext().setContentType(AdminSystemConstant.CONTENT_JSON);
	object = ConnectionUtil.sendMsg(object);
	jsonarrayArray = (JSONArray)PropertyUtils.getProperty(object, "message.player");
	page.setAllNum((Integer)PropertyUtils.getProperty(object, "message.allNum"));
	return SUCCESS;
    }

    @Action(value = "dudubear_pay", results = { @Result(name = "success", location = "/dudubear/assay/dudubearPay.jsp") })
    public String dudubearPay() throws Exception {

	if (beginTime == null) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	}
	if (endTime == null) {
	    endTime = new java.util.Date();
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("opeatorId", opeatorId);
	object.put("province", province);
	object.put("city", city);
	object.put("beginNum", page.getBeginNum());
	object.put("onePageNum", page.getOnePageNum());
	object.put("handlerName", "payHandler");
	OssContext.getBaseAdminContext().setContentType(AdminSystemConstant.CONTENT_JSON);
	object = ConnectionUtil.sendMsg(object);
	jsonarrayArray = (JSONArray)PropertyUtils.getProperty(object, "message.pay");
	page.setAllNum((Integer)PropertyUtils.getProperty(object, "message.allNum"));
	return SUCCESS;
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    /*@Action(value = "dudubear_oprator", results = { @Result(name = "success", location = "/dudubear/assay/dudubearOprator.jsp") })*/
    @Action(value = "dudubear_oprator", results = { @Result(name = "success", location = "/dudubear/assay/dudubearOprator.jsp") })
    public String dudubearOprator() throws Exception {

	if (beginTime == null) {
	    beginTime = DateService.getCurrentMonthFirstDay();
	}
	if (endTime == null) {
	    endTime = new java.util.Date();
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("opeatorId", opeatorId);
	object.put("handlerName", "opeatorHandler");
	object.put("methodName", "opeator");
	object = ConnectionUtil.sendMsg(object);
	jsonarrayArray = (JSONArray) object.get("message.opeator");
	return SUCCESS;
    }

   
    
    public String getOpeatorId() {
	return opeatorId;
    }

    public void setOpeatorId(String opeatorId) {
	this.opeatorId = opeatorId;
    }

    public Date getBeginTime() {
	return beginTime;
    }

    public void setBeginTime(Date beginTime) {
	this.beginTime = beginTime;
    }

    public Date getEndTime() {
	return endTime;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public JSONArray getJsonarrayArray() {
	return jsonarrayArray;
    }

    public void setJsonarrayArray(JSONArray jsonarrayArray) {
	this.jsonarrayArray = jsonarrayArray;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
    
    
}
