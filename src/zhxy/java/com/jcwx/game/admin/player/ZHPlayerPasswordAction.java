package com.jcwx.game.admin.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.OssContext;
import com.jcwx.game.common.constants.DictTypeConstant;
import com.jcwx.game.http.domain.PlayerClass;
import com.jcwx.game.util.BocHttpClient;
import com.jcwx.game.util.ServerListToMap;

import com.jcwx.game.util.PropertiesUtil;
import com.opensymphony.xwork2.ActionContext;

/** 战魂西游玩家详细信息 */
@ParentPackage("base")
@Namespace("/zhxy/player")
@ResultPath("/")
public class ZHPlayerPasswordAction extends BasalAction {

    private static Logger logger = Logger.getLogger(PlayerInfoAction.class);

    private static final long serialVersionUID = -70539059362263612L;
    /** 玩家名称 */
    private String loginName;

    private String newPW;

    /** 玩家名称 */
    private Map<String, Object> player;

    private String uid;

    private String url;

    public String getLoginName() {
	return loginName;
    }

    public String getNewPW() {
	return newPW;
    }

    public Map<String, Object> getPlayer() {
	return player;
    }

    public String getUid() {
	return uid;
    }

    public String getUrl() {
	return url;
    }

    @Action(value = "password_index", results = { @Result(name = "success", location = "../../zhxy/player/password_reset.jsp") })
    public String initPassword() throws Exception {
	return SUCCESS;
    }

    /** 查询玩家 */
    @Action(value = "password_reset", results = { @Result(name = "success", location = "../../zhxy/player/password_reset.jsp") })
    public String resetPassword() throws Exception {
	Map<String,String> dictMap = ServerListToMap.queryDictMap(OssContext.getBaseAdminContext().getProject().getProjectId(), DictTypeConstant.PASSWORD_RESET);
	url=dictMap.get("password_reset_url");
	
	ActionContext context = ActionContext.getContext();
	if(url==null||"".equals(url)){
	    url = PropertiesUtil.getValue("password_reset_url");
	}
	Map<String, String> params = new HashMap<String, String>();
	params.put("uid", uid);
	params.put("newPw", newPW);
	String jsonObject = BocHttpClient.sendPost(url, params);
	JSONObject json = JSON.parseObject(jsonObject);
	logger.info(json.toJSONString());
	getJSONResponse().responseJson(json);
	return null;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNewPW(String newPW) {
	this.newPW = newPW;
    }

    public void setPlayer(Map<String, Object> player) {
	this.player = player;
    }

    public void setUid(String uid) {
	this.uid = uid;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    /** 查询玩家 */
    @Action(value = "password_query", results = { @Result(name = "success", location = "../../zhxy/player/password_reset.jsp") })
    public String viewPlayInfo() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	//object.put("loginName", loginName);
	//object.put("handlerName", "PlayerPasswordHandler");
	System.out.println("----start-------");
	 if(loginName==null||"".equals(loginName)){
	     return SUCCESS;
	 }
	object.put("beginTime", DateService.getDateByStrAndFormat("2014-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
	object.put("endTime", DateService.getCurrentDayLastUtilDate());
	object.put("keyword", loginName);
	 
	object.put("beginNum", 0);
	object.put("onePageNum", 20);
	object.put("handlerName", "PlayerInfoHandler");
	try {
	    object = CONNECTION.sendMsg(object);
	    List<PlayerClass> playerClassList = (List<PlayerClass>) object.get("playerClassList"); // 玩家集合
	    if (playerClassList != null && playerClassList.size()>0) {
		player = new HashMap<String, Object>();
	    }
	    System.out.println("----process-------"+playerClassList.size());
	} catch (Exception e) {
	    e.printStackTrace(); 
	}
	url = PropertiesUtil.getValue("password_reset_url");
	System.out.println("----end-------");
	return SUCCESS;
    }

}
