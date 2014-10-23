package com.jcwx.game.admin.initGame;

import java.util.TimeZone;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;

/** 时区调整 */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin/system")
@ResultPath("/")
public class GMTAction extends BasalAction {

    public static String CURRENT_GMT_NAME = TimeZone.getDefault()
	    .getDisplayName();

    private String gmtValue;

    public String getGmtValue() {
	return this.gmtValue;
    }

    public void setGmtValue(String gmtValue) {
	this.gmtValue = gmtValue;
    }

    @Action(value = "gmt_index", results = { @Result(name = "success", location = "/admin/system/gmt/updategmt.jsp") })
    public String index() {
	return SUCCESS;

    }
    
    @Action(value = "gmt_update", results = { @Result(name = "success",type="chain", location = "gmt_index") })
    public String updateGMT() {
	TimeZone.setDefault(TimeZone.getTimeZone(getGmtValue()));
	setActionMsg("设置成功");
	return SUCCESS;

    }

}
