package com.jcwx.game.admin.assay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.http.domain.OssPlayerPhoneType;


/** 玩家分析 */
@ParentPackage("base")
@Namespace("/admin/assay")
@ResultPath("/")
public class PlayerAssayAction extends BasalAction {

    private static final long serialVersionUID = 1L;
    // 手机型号列表
    private List<OssPlayerPhoneType> OssPlayerPhoneTypeList;
    // 手机型号
    private String phoneType;

    public List<OssPlayerPhoneType> getOssPlayerPhoneTypeList() {
	return OssPlayerPhoneTypeList;
    }

    public String getPhoneType() {
	return phoneType;
    }

    @SuppressWarnings("unchecked")
    @Action(value = "queryPhoneType", results = { @Result(name = "success", location = "../../admin/assay/queryPhoneType.jsp") })
    public String query() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    addActionMessage(getActionMsg());
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("phoneType", phoneType);
	object.put("handlerName", "PlayerInfoHandler");
	object.put("methodName", "queryPlayerPhoneType");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		OssPlayerPhoneTypeList = (List<OssPlayerPhoneType>) object
			.get("ossPlayerPhoneTypeList");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setOssPlayerPhoneTypeList(
	    List<OssPlayerPhoneType> ossPlayerPhoneTypeList) {
	OssPlayerPhoneTypeList = ossPlayerPhoneTypeList;
    }

    public void setPhoneType(String phoneType) {
	this.phoneType = phoneType;
    }

}
