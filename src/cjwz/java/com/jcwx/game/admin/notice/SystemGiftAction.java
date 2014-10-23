package com.jcwx.game.admin.notice;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.http.domain.SystemGift;


@ParentPackage("base")
@Namespace("/cjwz/notice")
@ResultPath("/")
public class SystemGiftAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private SystemGift systemGift;

    private JSONArray jsonArrayList;

    @Action(value = "systemGift_index", results = { @Result(name = "success", location = "../../cjwz/notice/systemGift.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "SystemGiftHandler");
	object.put("methodName", "querySystemGift");
	try {
	    object = CONNECTION.sendMsg(object);
	    jsonArrayList = (JSONArray) object.get("systemGiftList");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "systemGift_toAdd", results = { @Result(name = "success", location = "../../cjwz/notice/systemGift_add.jsp") })
    public String addSystemGift() throws Exception {

	return SUCCESS;
    }

    @Action(value = "systemGift_doAdd", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "systemGift_index", "namespace", "/cjwz/notice",
	    "actionMsg", "${actionMsg}" }),@Result(name = "error", type = "redirectAction", params = {
		    "actionName", "systemGift_index", "namespace", "/cjwz/notice",
		    "actionMsg", "${actionMsg}" }) })
    public String doAddSystemGift() throws Exception {
	//Map<String, Object> object = new HashMap<String, Object>();
	JSONObject object = new JSONObject();
	object.put("handlerName", "SystemGiftHandler");
	object.put("methodName", "addSystemGift");
	object.put("systemGift", JSONObject.toJSONString(systemGift));
	int code=1;
	try {
	    object = (JSONObject) CONNECTION.sendMsg(object);
	    code=(Integer) object.get("code");
	    if(code==0){
		setSuccessInfo("success");
	    }else{
		setSuccessInfo("error");
		return "error";
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    this.addActionError("error");
	    return "error";
	}
	return SUCCESS;
    }

    
    @Action(value = "systemGift_toUpdate", results = { @Result(name = "success", location = "../../cjwz/notice/systemGift_edit.jsp") })
    public String toUpdateSystemGift() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "SystemGiftHandler");
	object.put("methodName", "query");
	object.put("systemGiftId", systemGift.getId());
	try {
	    object = CONNECTION.sendMsg(object);
	    systemGift=JSONObject.toJavaObject((JSONObject)object.get("systemGift"), SystemGift.class)  ;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "systemGift_doUpdate", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "systemGift_index", "namespace", "/cjwz/notice",
	    "actionMsg", "${actionMsg}" }) })
    public String doUpdateSystemGift() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "SystemGiftHandler");
	object.put("methodName", "updateSystemGift");
	object.put("systemGift", JSONObject.toJSONString(systemGift));
	try {
	    object = CONNECTION.sendMsg(object);
	    object.get("code");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }
    
    @Action(value = "systemGift_doDel", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "systemGift_index", "namespace", "/cjwz/notice",
	    "actionMsg", "${actionMsg}" }) })
    public String doDeleteSystemGift() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "SystemGiftHandler");
	object.put("methodName", "deleteSystemGift");
	object.put("systemGiftId", systemGift.getId());
	try {
	    object = CONNECTION.sendMsg(object);
	    object.get("code");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }
    
    
    
    public SystemGift getSystemGift() {
	return systemGift;
    }

    public void setSystemGift(SystemGift systemGift) {
	this.systemGift = systemGift;
    }

    public JSONArray getJsonArrayList() {
	return jsonArrayList;
    }

    public void setJsonArrayList(JSONArray jsonArrayList) {
	this.jsonArrayList = jsonArrayList;
    }

}
