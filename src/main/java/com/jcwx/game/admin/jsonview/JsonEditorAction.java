package com.jcwx.game.admin.jsonview;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;

@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
@SuppressWarnings("serial")
public class JsonEditorAction extends BasalAction {
    private String actionRedirect;
    private String callBackObjName;
    private String jsonString;

    @Action(value = "jsoneditor", results = { @Result(name = SUCCESS, location = "../../admin/public/jsoneditor.jsp") })
    public String editor() {
	Validate.notBlank(callBackObjName);
	if (StringUtils.isBlank(jsonString)) {
	    jsonString = "{}";
	}
	return SUCCESS;
    }

    public String getActionRedirect() {
	return this.actionRedirect;
    }

    public String getCallBackObjName() {
	return this.callBackObjName;
    }

    public String getJsonString() {
	return this.jsonString;
    }

    public void setActionRedirect(String actionRedirect) {
	this.actionRedirect = actionRedirect;
    }

    public void setCallBackObjName(String callBackObjName) {
	this.callBackObjName = callBackObjName;
    }

    public void setJsonString(String jsonString) {
	this.jsonString = jsonString;
    }

}
