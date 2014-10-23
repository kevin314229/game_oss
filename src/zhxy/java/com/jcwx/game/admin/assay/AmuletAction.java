package com.jcwx.game.admin.assay;

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
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;


/**
 * 玩家符文信息
 * 
 * @author 小平 2013-11-18
 */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/zhxy/assay")
@ResultPath("/")
public class AmuletAction extends BasalAction {

    /**
     * 已经装备的符文列表
     */
    private JSONArray amuletEquip = new JSONArray();
    /**
     * 背包符文列表
     */
    private JSONArray amuletList = new JSONArray();

    private String playerName;

    /**
     * 玩家符文
     * 
     * @throws Exception
     */
    @Action(value = "amulet_index", results = { @Result(name = "success", location = "/zhxy/assay/amulet.jsp") })
    public String amulet() throws Exception {
	if (StringUtils.isBlank(playerName)) {
	    return SUCCESS;
	}

	try {
	    Map<String, Object> object = new HashMap<String, Object>();
	    /*
	     * object.put("handlerName", "AmuletHandler");
	     * object.put("nickName", playerName);
	     */
	    object.put("handlerName", "oss.QueryPlayerAmuletHandler");
	    object.put("nickName", playerName);
	    Map<String, Object> result = CONNECTION.sendMsg(object);
	    /*
	     * amuletEquip = JSONArray.parseArray(String.valueOf(result
	     * .get("equipList")));
	     * 
	     * amuletList = JSONArray.parseArray(String.valueOf(result
	     * .get("bagList")));
	     */

	    amuletEquip = JSON.parseArray(String.valueOf(result
		    .get("posAmulet")));

	    amuletList = JSON
		    .parseArray(String.valueOf(result.get("bagAmulet")));

	    filterQualityLevel(amuletList);
	    filterQualityLevel(amuletEquip);

	} catch (Exception e) {
	    setErrorInfo(e.getCause().getMessage());
	}
	return SUCCESS;
    }

    private String convertAmuletLevel(String value) {
	if (StringUtils.isBlank(value)) {
	    return StringUtils.EMPTY;
	}
//	value = value.substring(value.length() - 1);
	return value;
    }

    private String convertQualityLevel(String value) {
	String result = StringUtils.EMPTY;
	if ("1".equals(value)) {
	    result = "碎片";
	} else if ("2".equals(value)) {
	    result = "绿色";
	} else if ("3".equals(value)) {
	    result = "蓝色";
	} else if ("4".equals(value)) {
	    result = "紫色";
	} else if ("5".equals(value)) {
	    result = "金色";
	} else if ("6".equals(value)) {
	    result = "精华";
	}
	return result;
    }

    private void filterQualityLevel(JSONArray amuletEquip2) {
	if (amuletEquip2 == null || amuletEquip2.size() == 0) {
	    return;
	}
	for (Object obj : amuletEquip2) {
	    JSONObject jsonObj = (JSONObject) obj;
//	    String amuletLevel = convertAmuletLevel(String.valueOf(jsonObj
//		    .get("amuletId")));
//	    if (amuletLevel != null) {
//		jsonObj.put("amuletLevel", amuletLevel);
//	    }

	    String qualityLevel = convertQualityLevel(String.valueOf(jsonObj
		    .get("amuletQuality")));
	    if (qualityLevel != null) {
		jsonObj.put("qualityLevel", qualityLevel);
	    }
	}

    }

    public JSONArray getAmuletEquip() {
	return this.amuletEquip;
    }

    public JSONArray getAmuletList() {
	return this.amuletList;
    }

    public String getPlayerName() {
	return this.playerName;
    }

    public void setAmuletEquip(JSONArray amuletEquip) {
	this.amuletEquip = amuletEquip;
    }

    public void setAmuletList(JSONArray amuletList) {
	this.amuletList = amuletList;
    }

    public void setPlayerName(String playerName) {
	this.playerName = playerName;
    }

}
