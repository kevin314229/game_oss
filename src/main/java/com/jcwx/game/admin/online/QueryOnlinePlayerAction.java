package com.jcwx.game.admin.online;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.PerformanceTimer;
import com.jcwx.game.http.domain.OnlinePlayer;


/**
 * 当前在线用户
 * */
@ParentPackage("base")
@Namespace("/admin/online")
@ResultPath("/")
@Action(value = "queryOnlinePlayer", results = { @Result(name = "success", location = "../../admin/online/queryOnlinePlayer.jsp") })
public class QueryOnlinePlayerAction extends BasalAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 饼图json数据-区域 */
    private List<JSONObject> addressJSonList;

    /** 区域分析 */
    private Map<String, Integer> addressMap;

    /** 总在线玩家记录数 */
    private Integer allNum;

    /** 不同IP数 */
    private Integer distinctIPNum;

    /**
     * 不同ip数
     * */
    private Integer ips;

    /** 精彩无限在线人数 */
    private Integer jcwxNum;

    /** json数据-时长分析 */
    private List<JSONObject> onlineJSonList;

    /** 在线玩家信息 */
    private List<OnlinePlayer> playerOnlineInfoList;

    @Override
    @SuppressWarnings("unchecked")
    public String execute() throws Exception {
	PerformanceTimer timer = new PerformanceTimer();
	jcwxNum = 0;
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "QueryOnlinePlayerHandler");
	object = CONNECTION.sendMsg(object);
	super.handleKryoMap(object);

	allNum = (Integer) object.get("allNum");
	ips = (Integer) object.get("ips");
	playerOnlineInfoList = (List<OnlinePlayer>) object
		.get("playerOnlineInfoList");

	// 排序一下在线时长
	Collections.sort(playerOnlineInfoList, new Comparator<OnlinePlayer>() {
	    @Override
	    public int compare(OnlinePlayer p2, OnlinePlayer p1) {
		return p2.getOnlineMinutes().compareTo(p1.getOnlineMinutes());
	    }
	});

	// 在线时长图表数据
	onlineJSonList = new ArrayList<JSONObject>();
	Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
	for (OnlinePlayer o : playerOnlineInfoList) {
	    // 在哪个时段 (10分钟区间)
	    int num = o.getOnlineMinutes() / 10;
	    if (map.containsKey(num)) {
		map.put(num, map.get(num) + 1);
	    } else {
		map.put(num, 1);
	    }
	    if (o.getLastLoginIP().equals("218.17.158.13")) {
		o.setAddress(o.getAddress() + "精彩无限公司");
		jcwxNum++;
	    }
	}
	for (Integer a : map.keySet()) {
	    JSONObject json = new JSONObject();
	    json.put("t", a * 10 + "-" + (a + 1) * 10);
	    json.put("n", map.get(a));// 数值
	    onlineJSonList.add(json);
	}

	// 区域分析
	addressMap = new HashMap<String, Integer>();
	// 用于表格显示
	for (OnlinePlayer o : playerOnlineInfoList) {
	    String address = o.getAddress();

	    String[] s1 = address.trim().split(" ");
	    if (s1.length >= 1) {
		if (addressMap.containsKey(s1[0])) {
		    int num = addressMap.get(s1[0]) + 1;
		    addressMap.put(s1[0], num);
		} else {
		    addressMap.put(s1[0], 1);
		}
	    }
	}

	// 用于图表显示
	addressJSonList = new ArrayList<JSONObject>();
	for (String o : addressMap.keySet()) {
	    JSONObject json = new JSONObject();
	    json.put("t", o); // 名称
	    json.put("n", addressMap.get(o));// 数值
	    addressJSonList.add(json);
	}

	// 图表排序
	Collections.sort(addressJSonList, new Comparator<JSONObject>() {
	    @Override
	    public int compare(JSONObject p1, JSONObject p2) {
		return p2.getInteger("n").compareTo(p1.getInteger("n"));
	    }
	});

	setLocalRunTime(timer.get());

	return "success";
    }

    public List<JSONObject> getAddressJSonList() {
	return addressJSonList;
    }

    public Map<String, Integer> getAddressMap() {
	return addressMap;
    }

    public Integer getAllNum() {
	return allNum;
    }

    public Integer getDistinctIPNum() {
	return distinctIPNum;
    }

    public Integer getIps() {
	return ips;
    }

    public Integer getJcwxNum() {
	return jcwxNum;
    }

    public List<JSONObject> getOnlineJSonList() {
	return onlineJSonList;
    }

    public List<OnlinePlayer> getPlayerOnlineInfoList() {
	return playerOnlineInfoList;
    }

    public void setAddressJSonList(List<JSONObject> addressJSonList) {
	this.addressJSonList = addressJSonList;
    }

    public void setAddressMap(Map<String, Integer> addressMap) {
	this.addressMap = addressMap;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setDistinctIPNum(Integer distinctIPNum) {
	this.distinctIPNum = distinctIPNum;
    }

    public void setIps(Integer ips) {
	this.ips = ips;
    }

    public void setJcwxNum(Integer jcwxNum) {
	this.jcwxNum = jcwxNum;
    }

    public void setOnlineJSonList(List<JSONObject> onlineJSonList) {
	this.onlineJSonList = onlineJSonList;
    }

    public void setPlayerOnlineInfoList(List<OnlinePlayer> playerOnlineInfoList) {
	this.playerOnlineInfoList = playerOnlineInfoList;
    }

}
