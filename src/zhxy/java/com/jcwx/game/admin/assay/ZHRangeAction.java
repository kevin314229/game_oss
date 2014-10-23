/**
 * 
 */
package com.jcwx.game.admin.assay;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSONArray;
import com.jcwx.game.admin.BasalAction;


@ParentPackage("base")
@Namespace("/zhxy/range")
@ResultPath("/")
public class ZHRangeAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 233344L;

    private JSONArray rangeArmyBattle;
    private JSONArray rangeArmyProsperity;
    // private JSONArray rangeSilverArray;
    private JSONArray rangeFight;
    private JSONArray rangeLevel;
    private JSONArray rangeSilver;

    public JSONArray getRangeArmyBattle() {
	return rangeArmyBattle;
    }

    public JSONArray getRangeArmyProsperity() {
	return rangeArmyProsperity;
    }

    public JSONArray getRangeFight() {
	return rangeFight;
    }

    public JSONArray getRangeLevel() {
	return rangeLevel;
    }

    public JSONArray getRangeSilver() {
	return rangeSilver;
    }

    /**
     * 查询个人、门派排行榜信息
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "queryRange_index", results = { @Result(name = "success", location = "/zhxy/assay/queryRange_index.jsp") })
    public String queryRange() throws Exception {
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "AssayRangeHandler");
	object = CONNECTION.sendMsg(object);
	rangeLevel = (JSONArray) object.get("rangeLevel");
	rangeSilver = (JSONArray) object.get("rangeSilver");
	rangeFight = (JSONArray) object.get("rangeFight");
	rangeArmyBattle = (JSONArray) object.get("rangeArmyBattle");
	rangeArmyProsperity = (JSONArray) object.get("rangeArmyProsperity");
	return "success";

    }

    public void setRangeArmyBattle(JSONArray rangeArmyBattle) {
	this.rangeArmyBattle = rangeArmyBattle;
    }

    public void setRangeArmyProsperity(JSONArray rangeArmyProsperity) {
	this.rangeArmyProsperity = rangeArmyProsperity;
    }

    public void setRangeFight(JSONArray rangeFight) {
	this.rangeFight = rangeFight;
    }

    public void setRangeLevel(JSONArray rangeLevel) {
	this.rangeLevel = rangeLevel;
    }

    public void setRangeSilver(JSONArray rangeSilver) {
	this.rangeSilver = rangeSilver;
    }

}