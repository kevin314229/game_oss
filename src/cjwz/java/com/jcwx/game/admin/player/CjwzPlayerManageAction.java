package com.jcwx.game.admin.player;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.util.Page;

/**
 * 玩家操作类
 * 
 * @author csp
 * 
 */
@ParentPackage("base")
@Namespace("/cjwz/player")
@ResultPath("/")
public class CjwzPlayerManageAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static int status_banAccount = 1; // 冻结帐号
    private static int status_normal = 0; // 正常状态

    private String loginName;

    private String nickName;
    
    private Long playerId;
    // 玩家状态
    private Integer playerStatus;
    
    private Date endTime;

    private Page page = Page.createPageCurrentOneSize(1,20);
    
    private JSONArray jsonArrayList;
    
    /**
     * 查询要封禁的玩家信息
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "playerManage_banAccount", results = { @Result(name = "success", location = "../../cjwz/player/playerManage.jsp") })
    public String query() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("loginName", loginName);
	object.put("status", playerStatus==null?null:playerStatus==status_banAccount?status_banAccount:null);
	object.put("beginNum", page.getBeginNum());
	object.put("onePageNum", page.getOnePageNum());
	object.put("handlerName", "OperateHandler");
	object.put("methodName", "queryBlackUser");
	try {
	    object = CONNECTION.sendMsg(object);
	    jsonArrayList =(JSONArray)object.get("userList");
	    page.setAllNum((Integer)object.get("allNum"));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

   

    
    @Action(value = "playerManage_banUser")
    public String banAccounts() throws Exception {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("user", loginName);
	object.put("endTime", endTime);
	object.put("handlerName", "OperateHandler");
	object.put("methodName", "addBlackUser");
	Integer code=1;
	try {
	    object = CONNECTION.sendMsg(object);
	    code = (Integer) object.get("code");
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	out.print(JSON.toJSON(code));
	out.close();
	return null;
    }
    
    
    
    /** 解封账号 */
    @Action(value = "playerManage_unBanUser", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "playerManage_banAccount", "namespace",
	    "/cjwz/player", "actionMsg", "${actionMsg}", "playerStatus",
	    "${playerStatus}" }) })
    public String removeBlackUser() {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("user", loginName);
	object.put("playerStatus", status_normal);
	object.put("handlerName", "OperateHandler");
	object.put("methodName", "removeBlackUser");
	Integer result = 0;
	try {
	    object = CONNECTION.sendMsg(object);
	    result = (Integer) object.get("result");
	} catch (Exception e) {
	    e.printStackTrace();
	}

	if (result != null && result > 0) {
	    this.setActionMsg("操作成功 !");
	} else {
	    this.setActionMsg("操作失败 !");
	}
	return SUCCESS;
    }
    
    
    
    /**
     * 查询要禁言的玩家信息
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "playerManage_banChat", results = { @Result(name = "success", location = "../../cjwz/player/playerBanChat.jsp") })
    public String queryChat() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("nickName", nickName);
	object.put("status", status_banAccount);
	object.put("beginNum", page.getBeginNum());
	object.put("onePageNum", page.getOnePageNum());
	object.put("handlerName", "OperateHandler");
	object.put("methodName", "queryBlackPlayer");
	try {
	    object = CONNECTION.sendMsg(object);
	    jsonArrayList =(JSONArray)object.get("playerList");
	    page.setAllNum((Integer)object.get("allNum"));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }
    
    /** 禁言 */
    @Action(value = "playerManage_banPlayerChat", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "playerManage_banChat", "namespace",
	    "/cjwz/player", "actionMsg", "${actionMsg}", "playerStatus",
	    "${playerStatus}" }) })
    public String banChat() {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("nickName", nickName);
	object.put("playerStatus", playerStatus);
	object.put("endTime", endTime);
	object.put("handlerName", "OperateHandler");
	if(playerStatus==status_normal){
	    object.put("methodName", "removeBlackPlayer");
	    object.put("playerId", playerId);
	}else{
	    object.put("methodName", "addBlackPlayer");
	}
	Integer result = 0;
	try {
	    object = CONNECTION.sendMsg(object);
	    result = (Integer) object.get("code");
	} catch (Exception e) {
	    e.printStackTrace();
	}

	if (result != null && result == 0) {
	    this.setActionMsg("操作成功 !");
	} else {
	    this.setActionMsg("操作失败 !");
	}
	return SUCCESS;
    }
    
    public String getLoginName() {
	return loginName;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public Long getPlayerId() {
	return playerId;
    }

    public void setPlayerId(Long playerId) {
	this.playerId = playerId;
    }

    public Integer getPlayerStatus() {
	return playerStatus;
    }

    public void setPlayerStatus(Integer playerStatus) {
	this.playerStatus = playerStatus;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public static int getStatus_banAccount() {
        return status_banAccount;
    }

    public static void setStatus_banAccount(int status_banAccount) {
        CjwzPlayerManageAction.status_banAccount = status_banAccount;
    }

    public static int getStatus_normal() {
        return status_normal;
    }

    public static void setStatus_normal(int status_normal) {
        CjwzPlayerManageAction.status_normal = status_normal;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public JSONArray getJsonArrayList() {
        return jsonArrayList;
    }

    public void setJsonArrayList(JSONArray jsonArrayList) {
        this.jsonArrayList = jsonArrayList;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    
}
