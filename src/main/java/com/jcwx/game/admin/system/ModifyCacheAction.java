package com.jcwx.game.admin.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSON;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.http.domain.OssCache;


/** Oss玩家缓存管理 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class ModifyCacheAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** action对象之间通信 */
    protected String actionMsg;
    // key
    private String key;
    // 玩家名称
    private String nickName;
    private List<OssCache> ossCachesList;

    public String getKey() {
	return key;
    }

    public String getNickName() {
	return nickName;
    }

    public List<OssCache> getOssCachesList() {
	return ossCachesList;
    }

    /**
     * 移除所有玩家 不封号
     * 
     * @return
     * @throws IOException
     */
    @Action(value = "playerCacheManage_modifyAll")
    public String modifyAllCache() throws IOException {

	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	String code = "";
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "ModifyCacheHandler");
	object.put("methodName", "putAllPlayerBaseOffLine");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		int result = Integer.valueOf(object.get("result").toString()); // 0
									       // 成功
									       // 1
									       // 失败
		if (result == 0) {
		    code = "ok";
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	out.print(JSON.toJSON(code).toString());
	out.close();
	return null;
	/*
	 * Map<String, Object> object = new HashMap<String, Object>();
	 * object.put("handlerName", "ModifyCacheHandler");
	 * object.put("methodName", "banAccountAll"); try { if(object != null &&
	 * !object.isEmpty()){ object = getConnection().sendMsg(baseAdminContext,
	 * object); int result
	 * =Integer.valueOf(object.get("result").toString()); //0 成功 1 失败
	 * if(result == 0){ this.setActionMsg("操作成功 !"); }else{
	 * this.setActionMsg("操作失败 !"); }
	 * 
	 * } } catch(Exception e) { e.printStackTrace(); } return "success";
	 */
    }

    /**
     * 移除玩家
     * 
     * @throws UnsupportedEncodingException
     **/
    @Action(value = "playerCacheManage_modify", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "playerCacheManage_index", "namespace",
	    "/admin/base", "actionMsg", "${actionMsg}" }) })
    public String modifyCache() throws UnsupportedEncodingException {
	if (actionMsg != null && !"".equals(actionMsg)) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(actionMsg);
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("key", key);
	object.put("nickName", nickName);
	object.put("handlerName", "ModifyCacheHandler");
	object.put("methodName", "banAccount");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		int result = Integer.valueOf(object.get("result").toString()); // 0
									       // 成功
									       // 1
									       // 失败
		if (result == 0) {
		    this.setActionMsg("操作成功 !");
		} else {
		    this.setActionMsg("操作失败 !");
		}

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "success";
    }

    /**
     * 浏览所有角色
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Action(value = "playerCacheManage_index", results = { @Result(name = "success", location = "../../admin/system/modifyCache.jsp") })
    public String queryPlayerCache() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("key", key);
	object.put("nickName", nickName);
	object.put("handlerName", "ModifyCacheHandler");
	try {
	    if (object != null && !object.isEmpty()) {
		object = CONNECTION.sendMsg(object);
		ossCachesList = (List<OssCache>) object.get("ossCaches");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setKey(String key) {
	this.key = key;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOssCachesList(List<OssCache> ossCachesList) {
	this.ossCachesList = ossCachesList;
    }

}
