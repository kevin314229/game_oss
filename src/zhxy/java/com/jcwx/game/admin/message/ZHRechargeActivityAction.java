package com.jcwx.game.admin.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.http.domain.OssRechargeActivity;
import com.jcwx.game.http.domain.OssRechargeActivityDetail;
import com.jcwx.game.service.oss.impl.OssLogService;


/**
 * 首充奖励
 * 
 * @author csp
 * 
 */
@ParentPackage("base")
@Namespace("/zhxy/message")
@ResultPath("/")
public class ZHRechargeActivityAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static boolean checkItem(String item) {
	if (StringUtils.isBlank(item)) {
	    return false;
	}
	if (!StringUtils.contains(item, ",")) {
	    return false;
	}
	String[] items = StringUtils.split(item, "#");
	if (items.length == 0) {
	    return false;
	}
	return true;
    }

    private String activityStr;

    @Autowired
    private OssLogService ossLogService;

    private OssRechargeActivity ossRechargeActivity;

    private List<OssRechargeActivity> ossRechargeActivityList;

    @Action(value = "ZHrechargeActivity_delete", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "ZHrechargeActivity_index", "namespace",
	    "/zhxy/message", "actionMsg", "${actionMsg}" }) })
    public String delete() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "RechargeActivityHandler");
	object.put("methodName", "deleteRechargeActivity");
	object.put("rechargeActivityId",
		ossRechargeActivity.getRecharegeActivityId());
	if (object != null && !object.isEmpty()) {
	    object = CONNECTION.sendMsg(object);
	    ossLogService.createOssLog(OssLogConstant.ADD_MODIFY_ACTIVITY,
		    activityStr);
	    setActionMsg(CodeUtil.getActionMsgByMap(object));
	}
	return SUCCESS;
    }

    @Action(value = "ZHrechargeActivity_doAdd", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "ZHrechargeActivity_index", "namespace",
		    "/zhxy/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", type = "redirectAction", params = {
		    "actionName", "ZHrechargeActivity_index", "namespace",
		    "/zhxy/message", "errorInfo", "${errorInfo}" }) })
    public String doAdd() throws Exception {
	int code = 0;
	List<OssRechargeActivityDetail> ossRechargeActivityDetailList = new ArrayList<OssRechargeActivityDetail>();
	List<OssRechargeActivityDetail> ossRechargeActivityDetails = JSON
		.parseArray(activityStr, OssRechargeActivityDetail.class);
	for (OssRechargeActivityDetail ossRechargeActivityDetail : ossRechargeActivityDetails) {
	    if (!StringUtils.isNotBlank(ossRechargeActivityDetail.getItem())) {
		continue;
	    }
	    if (!checkItem(ossRechargeActivityDetail.getItem())) {
		setActionMsg("Please input correct Reward items format");
		return "success";
	    }
	    ossRechargeActivityDetailList.add(ossRechargeActivityDetail);
	}
	ossRechargeActivity
		.setOssRechargeActivityDetailList(ossRechargeActivityDetailList);
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "RechargeActivityHandler");
	object.put("methodName", "addRechargeActivity");
	object.put("ossRechargeActivity", ossRechargeActivity);
	if (object != null && !object.isEmpty()) {
	    object = CONNECTION.sendMsg(object);
	    code = (Integer) object.get("code");
	    ossLogService.createOssLog(OssLogConstant.ADD_RECHARGE_ACTIVITY,
		    activityStr);
	    if (code == 0) {
		setActionMsg("OK");
	    } else if (code == 2) {
		setErrorInfo("Do not allow to repeat ");
		return ERROR;
	    } else {
		setErrorInfo("ERROR");
		return ERROR;
	    }
	}
	return SUCCESS;
    }

    @Action(value = "ZHrechargeActivity_doUpdate", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "ZHrechargeActivity_index", "namespace",
		    "/zhxy/message", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", location = "../../admin/message/rechargeActivity_edit.jsp") })
    public String doUpdate() throws Exception {
	List<OssRechargeActivityDetail> ossRechargeActivityDetailList = new ArrayList<OssRechargeActivityDetail>();
	List<OssRechargeActivityDetail> ossRechargeActivityDetails = JSON
		.parseArray(activityStr, OssRechargeActivityDetail.class);
	for (OssRechargeActivityDetail ossRechargeActivityDetail : ossRechargeActivityDetails) {
	    if (!StringUtils.isNotBlank(ossRechargeActivityDetail.getItem())) {
		continue;
	    }
	    if (!checkItem(ossRechargeActivityDetail.getItem())) {
		setActionMsg("Please input correct Reward items format");
		return "ERROR";
	    }
	    ossRechargeActivityDetailList.add(ossRechargeActivityDetail);
	}
	ossRechargeActivity
		.setOssRechargeActivityDetailList(ossRechargeActivityDetailList);
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "RechargeActivityHandler");
	object.put("methodName", "updateRechargeActivity");
	object.put("ossRechargeActivity", ossRechargeActivity);
	if (object != null && !object.isEmpty()) {
	    object = CONNECTION.sendMsg(object);
	    ossLogService.createOssLog(OssLogConstant.ADD_RECHARGE_ACTIVITY,
		    activityStr);
	    setActionMsg(CodeUtil.getActionMsgByMap(object));
	}
	return SUCCESS;
    }

    public String getActivityStr() {
	return activityStr;
    }

    public OssRechargeActivity getOssRechargeActivity() {
	return ossRechargeActivity;
    }

    public List<OssRechargeActivity> getOssRechargeActivityList() {
	return ossRechargeActivityList;
    }

    /** 刷新服务器活动 */
    @Action(value = "ZHrechargeActivity_reflash", results = { @Result(name = "success", type = "chain", location = "rechargeActivity_index") })
    public String modifyActivity() throws Exception {
	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("handlerName", "RechargeActivityHandler");
	    object.put("methodName", "reflashActivity");
	    object = CONNECTION.sendMsg(object);
	    setActionMsg(CodeUtil.getActionMsgByMap(object));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "ZHmodifyActivity_select", results = { @Result(name = "success", location = "../../admin/message/ZHmodifyActivity_select.jsp") })
    public String modifyActivity_select() {
	return SUCCESS;
    }

    @Action(value = "ZHrechargeActivity_index", results = { @Result(name = "success", location = "../../admin/message/ZHrechargeActivity.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "RechargeActivityHandler");
	if (object != null && !object.isEmpty()) {
	    object = CONNECTION.sendMsg(object);
	    ossRechargeActivityList = (List<OssRechargeActivity>) object
		    .get("ossRechargeActivityList");
	}
	return SUCCESS;
    }

    public void setActivityStr(String activityStr) {
	this.activityStr = activityStr;
    }

    public void setOssRechargeActivity(OssRechargeActivity ossRechargeActivity) {
	this.ossRechargeActivity = ossRechargeActivity;
    }

    public void setOssRechargeActivityList(
	    List<OssRechargeActivity> ossRechargeActivityList) {
	this.ossRechargeActivityList = ossRechargeActivityList;
    }

    @Action(value = "ZHrechargeActivity_toAdd", results = { @Result(name = "success", location = "../../admin/message/ZHrechargeActivity_add.jsp") })
    public String toAdd() throws Exception {
	return SUCCESS;
    }

    @Action(value = "ZHrechargeActivity_toUpdate", results = { @Result(name = "success", location = "../../admin/message/ZHrechargeActivity_edit.jsp") })
    public String toUpdate() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "RechargeActivityHandler");
	object.put("methodName", "queryRechargeActivity");
	object.put("recharegeActivityId",
		ossRechargeActivity.getRecharegeActivityId());
	if (object != null && !object.isEmpty()) {
	    object = CONNECTION.sendMsg(object);
	    ossRechargeActivity = (OssRechargeActivity) object
		    .get("ossRechargeActivity");
	}
	return SUCCESS;
    }

}
