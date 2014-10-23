package com.jcwx.game.admin.player;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSONArray;
import com.jcwx.game.admin.BasalAction;


/** 查询玩家称号 **/
@ParentPackage("base")
@Namespace("/zhxy/achieve")
@ResultPath("/")
public class QueryPlayerAchieveAction extends BasalAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // /已完成的成就
    private JSONArray finished;
    // 已获得的称号
    private JSONArray finishedTitle;
    // 玩家名
    private String nickName;
    // 未完成的成就
    private JSONArray unfinished;
    // 未获得的称号
    private JSONArray unfinishedTitle;

    public JSONArray getFinished() {
	return finished;
    }

    public JSONArray getFinishedTitle() {
	return finishedTitle;
    }

    public String getNickName() {
	return nickName;
    }

    public JSONArray getUnfinished() {
	return unfinished;
    }

    public JSONArray getUnfinishedTitle() {
	return unfinishedTitle;
    }

    // 查询文件称号，成就
    @Action(value = "queryPlayerAchieve_index", results = { @Result(name = "success", location = "../../zhxy/player/queryPlayerAchieve.jsp") })
    public String playerFriendInfo() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "oss.QueryPlayerAchieveHandler");
	object.put("nickName", nickName);
	try {
	    object = CONNECTION.sendMsg(object);
	    finished = (JSONArray) object.get("finished");
	    unfinished = (JSONArray) object.get("unfinished");
	    finishedTitle = (JSONArray) object.get("finishedTitle");
	    unfinishedTitle = (JSONArray) object.get("unfinishedTitle");

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setFinished(JSONArray finished) {
	this.finished = finished;
    }

    public void setFinishedTitle(JSONArray finishedTitle) {
	this.finishedTitle = finishedTitle;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setUnfinished(JSONArray unfinished) {
	this.unfinished = unfinished;
    }

    public void setUnfinishedTitle(JSONArray unfinishedTitle) {
	this.unfinishedTitle = unfinishedTitle;
    }

}
