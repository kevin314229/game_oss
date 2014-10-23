package com.jcwx.game.admin.initGame;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.CacheManager;
import com.jcwx.game.system.model.SystemSet;

/**
 * 系统设置
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin/system")
@ResultPath("/")
public class SystemSetAction extends BasalAction{
    SystemSet system = new SystemSet();
    @Action(value = "systemset_index", results = { @Result(name = "success", location = "/admin/system/systemSet.jsp") })
    public String index(){
	system = CacheManager.SYSTEM_SET;
	return SUCCESS;
    }
    
    @Action(value = "systemset_update", results = { @Result(name = "success", type="chain" ,location = "systemset_index" ) })
    public String update(){
	CacheManager.SYSTEM_SET = system;
	return SUCCESS;
    }

    public SystemSet getSystem() {
        return system;
    }

    public void setSystem(SystemSet system) {
        this.system = system;
    }
    
    
    
}
