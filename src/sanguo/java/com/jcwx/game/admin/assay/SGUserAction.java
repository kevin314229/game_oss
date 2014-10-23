package com.jcwx.game.admin.assay;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jcwx.game.admin.BasalAction;


@ParentPackage("base")
@Namespace("/sanguo/assay")
@ResultPath("/")
public class SGUserAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Date beginDate;
    
    private Date endDate;
    
    //玩家注册数据
    private JSONArray registerArray;
    //角色注册数据
    private JSONArray ruleRegisterArray;
    //职业注册数据
    private JSONArray professionArray;
    
    @Action(value = "sguser_register", results = { @Result(name = "success", location = "/sanguo/user/sgUser_register.jsp") })
    public String amulet() throws Exception {
	
	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	   
	    object.put("handlerName", "oss.QueryPlayerAmuletHandler");
	    object.put("beginDate", beginDate);
	    object.put("endDate", endDate);
	    
	    Map<String, Object> result = null;//CONNECTION.sendMsg(object);
	    registerArray = JSON.parseArray(String.valueOf(result.get("registerArray")));
	    ruleRegisterArray = JSON.parseArray(String.valueOf(result.get("ruleRegisterArray")));
	    professionArray = JSON.parseArray(String.valueOf(result.get("professionArray")));

	} catch (Exception e) {
	    setErrorInfo(e.getCause().getMessage());
	}
	return SUCCESS;
    }

    
    
    
    public JSONArray getRegisterArray() {
        return registerArray;
    }

    public void setRegisterArray(JSONArray registerArray) {
        this.registerArray = registerArray;
    }

    public JSONArray getRuleRegisterArray() {
        return ruleRegisterArray;
    }

    public void setRuleRegisterArray(JSONArray ruleRegisterArray) {
        this.ruleRegisterArray = ruleRegisterArray;
    }

    public JSONArray getProfessionArray() {
        return professionArray;
    }

    public void setProfessionArray(JSONArray professionArray) {
        this.professionArray = professionArray;
    }
    
    

}
