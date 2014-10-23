package com.jcwx.game.admin.player;

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


/** 查询玩家称号 **/
@ParentPackage("base")
@Namespace("/zhxy/achieve")
@ResultPath("/")
public class PlayerDressAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // /培養等級
    private JSONArray dress;
    // /詳細時裝數值
    private JSONObject dressLv;
    // 玩家名
    private String nickName;

    public JSONArray getDress() {
	return dress;
    }

    public JSONObject getDressLv() {
	return dressLv;
    }

    public String getNickName() {
	return nickName;
    }

    // 查询时装
    @Action(value = "playerDress_index", results = { @Result(name = "success", location = "../../zhxy/player/playerDress.jsp") })
    public String playerFriendInfo() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "oss.QueryPlayerDressHandler");
	object.put("nickName", nickName);
	try {
	    object = CONNECTION.sendMsg(object);
	    dress = (JSONArray) object.get("dress");
	    dressLv = (JSONObject) object.get("dressLv");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setDress(JSONArray dress) {
	this.dress = dress;
    }

    public void setDressLv(JSONObject dressLv) {
	this.dressLv = dressLv;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

}
