package com.jcwx.game.admin.system;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.code.util.CodeUtil;
import com.jcwx.game.http.domain.OssSystemParam;


/** 系统参数 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class SystemConfigAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    //
    private OssSystemParam ossSystemParam;
    // 系统参数 集合
    private List<OssSystemParam> ossSystemParamList;
    // 系统参数 主键
    private String systemKey;
    // 只暴露需要的参数
    private String systemValue;

    /**
     * 查部部分系统参数
     * 
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("unchecked")
    @Action(value = "systemConfig_browseSystemConfig", results = { @Result(name = "success", location = "../../admin/system/systemConfig/browseSystemConfig.jsp") })
    public String browseSystemConfig() throws UnsupportedEncodingException {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}
	Map<String, Object> object = new HashMap<String, Object>();
	Integer ok = 0;
	object.put("ok", ok);
	object.put("handlerName", "SystemConfigHandler");
	object.put("methodName", "browse");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossSystemParamList = (List<OssSystemParam>) object
			.get("ossSystemParamList");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "systemConfig_doAdd", results = { @Result(name = "success", location = "../../admin/system/systemConfig/systemConfig_add.jsp") })
    public String doAdd() {
	Integer code = 0;
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "SystemConfigHandler");
	object.put("methodName", "doAdd");
	object.put("ossSystemParam", ossSystemParam);
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		setActionMsg(CodeUtil.getActionMsgByMap(object));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "success";
    }

    public OssSystemParam getOssSystemParam() {
	return ossSystemParam;
    }

    public List<OssSystemParam> getOssSystemParamList() {
	return ossSystemParamList;
    }

    public String getSystemKey() {
	return systemKey;
    }

    public String getSystemValue() {
	return systemValue;
    }

    @Action(value = "systemConfig_reflash")
    public String modifyActivity() throws Exception {
	OutputStream outputStream = ServletActionContext.getResponse()
		.getOutputStream();
	String code = "ok";
	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("handlerName", "SystemConfigHandler");
	    object.put("methodName", "refurbish");
	    object = CONNECTION.sendMsg(object);
	    if (!object.get("code").equals(0)) {
		code = "ok";
	    }
	} catch (Exception e) {
	    code = "no";
	    e.printStackTrace();
	}
	outputStream.write(code.getBytes());
	outputStream.flush();
	return null;
    }

    public void setOssSystemParam(OssSystemParam ossSystemParam) {
	this.ossSystemParam = ossSystemParam;
    }

    public void setOssSystemParamList(List<OssSystemParam> ossSystemParamList) {
	this.ossSystemParamList = ossSystemParamList;
    }

    public void setSystemKey(String systemKey) {
	this.systemKey = systemKey;
    }

    public void setSystemValue(String systemValue) {
	this.systemValue = systemValue;
    }

    @Action(value = "systemConfig_toAdd", results = { @Result(name = "success", location = "../../admin/system/systemConfig/systemConfig_add.jsp") })
    public String toAdd() {
	return "success";
    }

    /** 修改系统参数 */
    @Action(value = "systemConfig_updateSystemConfig")
    public void updateSystemConfig() {
	Integer code = 0;
	if (systemValue != null) {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("value", systemValue);
	    object.put("key", systemKey);
	    object.put("handlerName", "SystemConfigHandler");
	    try {
		if (object != null && !object.isEmpty()) {
		    object = CONNECTION.sendMsg(object);
		    code = (Integer) object.get("code");
		}
	    } catch (Exception e) {
		code = 1;
		e.printStackTrace();
	    }
	}
	if (code == 0) {
//	    setActionMsg("修改成功");
	    getPageMessage().setMessage("修改成功");
	    getJSONResponse().responseJson(getPageMessage());
	}
    }

}
