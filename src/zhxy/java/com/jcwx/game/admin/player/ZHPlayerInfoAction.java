package com.jcwx.game.admin.player;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.protobuf.Message;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.http.domain.OssZHPlayeBagItem;
import com.jcwx.game.http.domain.OssZHPlayerAttribute;
import com.jcwx.game.http.domain.OssZHPlayerBase;
import com.jcwx.game.http.domain.OssZHPlayerBaseInfor;
import com.jcwx.game.http.domain.OssZHPlayerClass;
import com.jcwx.game.http.domain.OssZHPlayerCopyTimes;
import com.jcwx.game.http.domain.OssZHPlayerData;
import com.jcwx.game.http.domain.OssZHPlayerEquip;
import com.jcwx.game.http.domain.OssZHPlayerInfor;
import com.jcwx.game.http.domain.OssZHPlayerTask;
import com.jcwx.game.http.domain.OssZHPlayerVipInfor;
import com.jcwx.game.http.domain.PlayerArmy;
import com.jcwx.game.http.domain.PlayerBaseClass;
import com.jcwx.game.service.oss.IOssOperationService;


/** 战魂西游玩家详细信息 */
@ParentPackage("base")
@Namespace("/zhxy/player")
@ResultPath("/")
public class ZHPlayerInfoAction extends BasalAction {

    private static Logger logger = Logger.getLogger(PlayerInfoAction.class);

    private static final long serialVersionUID = -70539059362263612L;
    /** 总记录数 */
    private Integer allNum;

    // 开始时间
    private Date beginTime;

    /** 当前页数 */
    private Integer currPageNO;

    // 结束时间
    private Date endTime;

    /** 搜索关键字 */
    private String keyword;

    /** 玩家名称 */
    private String loginName;

    /** 每页记录数 */
    private Integer onePageNum;
    /** 操作标识 */
    private String operateFlag;

    /** 排序标记 */
    private String orderFlag;

    /** 背包仓库物品 */
    private List<OssZHPlayeBagItem> ossPlayeBagItemList;

    private OssZHPlayerAttribute ossPlayerAttribute;

    private OssZHPlayerBase ossPlayerBase;

    List<OssZHPlayerCopyTimes> ossPlayerCopyTimesList;

    private OssZHPlayerData ossPlayerData;

    /** 装备 */
    private List<OssZHPlayerEquip> ossPlayerEquipList;

    private List<OssZHPlayerTask> ossPlayerTaskList;

    /** 总页数 */
    private Integer pages;

    /** 平台标识 */
    private String plat;

    private List<PlayerArmy> playerArmys = new ArrayList<PlayerArmy>();

    private List<PlayerBaseClass> playerBaseClassList;

    private Integer playerBaseId;

    private List<OssZHPlayerBaseInfor> playerBaseInfors;
    private OssZHPlayerClass playerClass;

    /** 玩家信息 */
    private List<OssZHPlayerClass> playerClassList;

    private JSONArray playerFriendInfo;

    /** 玩家id */
    private Integer playerId;

    private OssZHPlayerInfor playerInfor;

    private OssZHPlayerVipInfor playerVipInfor;

    /** 实时在线，scoket连接 */
    private boolean realTimeOnline = false;
    @Autowired
    private IOssOperationService ossOperationService;
    
    /** 查询平台标识 */
    private List<OssOperation> ossOperationList;
    
    private String ptCode;
    
    private int level;
    
    private String itemName;
    
    private int status;
    private List<OssZHPlayerInfor> playerInfors;
    private List<OssZHPlayerInfor> delPlayerInfors;
    
    private String beginDate;
    
    private String endDate;
    
    private List<Map<String, Object>> levelList;
    
    private List<Map<String, Object>> playerList;
    /***
     * 查看所有角色
     * */
    @Action(value = "zhplayerInfo_browsePlayerBaseInfo", results = { @Result(name = "success", location = "../../zhxy/player/browsePlayerBaseInfo.jsp") })
    public String browsePlayerBaseInfo() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}

	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	if (onePageNum == null || onePageNum == 0) {
	    onePageNum = 20; // 每页20条记录
	}
	ossOperationList = ossOperationService.getOssOperationList();
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;
	// 排序
	if (orderFlag == null || "".equals(orderFlag)) {
	    orderFlag = "DESC";
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("keyword", keyword);
	object.put("orderFlag", orderFlag);
	if(ptCode==null||"".equals(ptCode)){
	    object.put("ptCode", null);
	}else{
	    object.put("ptCode", ptCode);
	}
	if(level>0){
	    object.put("level", level);
	}
	if(itemName==null||"".equals(itemName)){
	    object.put("itemName", null);
	}else{
	    object.put("itemName", itemName);
	}
	object.put("beginNum", beginNum);
	object.put("onePageNum", onePageNum);
	object.put("handlerName", "PlayerInfoHandler");
	object.put("methodName", "queryAllPlayerBase");
	try {
	    object = CONNECTION.sendMsg(object);
	    playerBaseClassList = (List<PlayerBaseClass>) object
		    .get("playerBaseClassList"); // 玩家集合
	    allNum = (Integer) object.get("allNum");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	return SUCCESS;
    }

    @Action(value = "zhplayerInfo_browsePlayerInfo", results = { @Result(name = "success", location = "../../zhxy/player/browsePlayerInfo.jsp") })
    public String browsePlayerInfo() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}

	if (beginTime == null || "".equals(beginTime)) {
	    beginTime = DateService.getCurrentMonthFirstUtilDate();
	} else {
	    beginTime = DateService.getDateFirstTime(beginTime);
	}

	if (endTime == null || "".equals(endTime)) {
	    endTime = DateService.getCurrentDayLastUtilDate();

	} else {
	    endTime = DateService.getDateLastTime(endTime);
	}

	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	if (onePageNum == null || onePageNum.intValue() == 0) {
	    onePageNum = 20; // 每页20条记录
	}

	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;
	// 排序
	if (orderFlag == null || "".equals(orderFlag)) {
	    orderFlag = "DESC";
	}
	if (plat != null && plat.equals("0")) {
	    plat = null;
	}
	ossOperationList = ossOperationService.getOssOperationList();
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("keyword", keyword);
	object.put("plat", plat);
	object.put("orderFlag", orderFlag);
	object.put("beginNum", beginNum);
	object.put("onePageNum", onePageNum);
	object.put("handlerName", "PlayerInfoHandler");
	try {
	    object = CONNECTION.sendMsg(object);
	    playerClassList = (List<OssZHPlayerClass>) object
		    .get("playerClassList"); // 玩家集合
	    allNum = (Integer) object.get("allNum");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		/ onePageNum;
	// 当前页设置
	if (currPageNO.intValue() > pages) {
	    currPageNO = pages;
	}
	return SUCCESS;
    }

    @Action(value = "zhplayerInfo_clearPlayerEmail")
    public String clearPlayerEmail() throws Exception {
	HttpServletResponse reponse = ServletActionContext.getResponse();
	reponse.setContentType("text/html; charset=utf-8");
	PrintWriter out = reponse.getWriter();
	String code = "error";
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("handlerName", "PlayerInfoHandler");
	object.put("methodName", "clearPlayerEmail");
	object.put("playerId", playerId);

	try {
	    object = CONNECTION.sendMsg(object);
	    if (object.get("code").equals(0)) {
		code = "ok";
	    }
	} catch (Exception e) {
	    code = "error";
	    e.printStackTrace();
	}
	out.print(JSON.toJSON(code).toString());
	out.close();
	return null;
    }

    public Integer getAllNum() {
	return allNum;
    }

    public Date getBeginTime() {
	return beginTime;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public Date getEndTime() {
	return endTime;
    }

    public String getKeyword() {
	return keyword;
    }

    public String getLoginName() {
	return loginName;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public String getOperateFlag() {
	return operateFlag;
    }

    public String getOrderFlag() {
	return orderFlag;
    }

    public List<OssZHPlayeBagItem> getOssPlayeBagItemList() {
	return ossPlayeBagItemList;
    }

    public OssZHPlayerAttribute getOssPlayerAttribute() {
	return ossPlayerAttribute;
    }

    public OssZHPlayerBase getOssPlayerBase() {
	return ossPlayerBase;
    }

    public List<OssZHPlayerCopyTimes> getOssPlayerCopyTimesList() {
	return ossPlayerCopyTimesList;
    }

    public OssZHPlayerData getOssPlayerData() {
	return ossPlayerData;
    }

    public List<OssZHPlayerEquip> getOssPlayerEquipList() {
	return ossPlayerEquipList;
    }

    public List<OssZHPlayerTask> getOssPlayerTaskList() {
	return ossPlayerTaskList;
    }

    public Integer getPages() {
	return pages;
    }

    public String getPlat() {
	return plat;
    }

    public List<PlayerArmy> getPlayerArmys() {
	return playerArmys;
    }

    public List<PlayerBaseClass> getPlayerBaseClassList() {
	return playerBaseClassList;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public List<OssZHPlayerBaseInfor> getPlayerBaseInfors() {
	return playerBaseInfors;
    }

    public OssZHPlayerClass getPlayerClass() {
	return playerClass;
    }

    public List<OssZHPlayerClass> getPlayerClassList() {
	return playerClassList;
    }

    public JSONArray getPlayerFriendInfo() {
	return playerFriendInfo;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public OssZHPlayerInfor getPlayerInfor() {
	return playerInfor;
    }

    public OssZHPlayerVipInfor getPlayerVipInfor() {
	return playerVipInfor;
    }

    public boolean isRealTimeOnline() {
	return realTimeOnline;
    }

    @Action(value = "zhplayerInfo_playerFriendInfo", results = { @Result(name = "success", location = "../../zhxy/player/playerFriendInfo.jsp") })
    public String playerFriendInfo() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("playerBaseId", playerBaseId);
	object.put("handlerName", "PlayerInfoHandler");
	object.put("methodName", "playerFriendInfo");
	try {
	    object = CONNECTION.sendMsg(object);
	    if (object != null && object.get("playerFriendInfo") != null) {
		playerFriendInfo = (JSONArray) object.get("playerFriendInfo"); // 玩家集合
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setBeginTime(Date beginTime) {
	this.beginTime = beginTime;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public void setKeyword(String keyword) {
	this.keyword = keyword;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOperateFlag(String operateFlag) {
	this.operateFlag = operateFlag;
    }

    public void setOrderFlag(String orderFlag) {
	this.orderFlag = orderFlag;
    }

    public void setOssPlayeBagItemList(
	    List<OssZHPlayeBagItem> ossPlayeBagItemList) {
	this.ossPlayeBagItemList = ossPlayeBagItemList;
    }

    public void setOssPlayerAttribute(OssZHPlayerAttribute ossPlayerAttribute) {
	this.ossPlayerAttribute = ossPlayerAttribute;
    }

    public void setOssPlayerBase(OssZHPlayerBase ossPlayerBase) {
	this.ossPlayerBase = ossPlayerBase;
    }

    public void setOssPlayerCopyTimesList(
	    List<OssZHPlayerCopyTimes> ossPlayerCopyTimesList) {
	this.ossPlayerCopyTimesList = ossPlayerCopyTimesList;
    }

    public void setOssPlayerData(OssZHPlayerData ossPlayerData) {
	this.ossPlayerData = ossPlayerData;
    }

    public void setOssPlayerEquipList(List<OssZHPlayerEquip> ossPlayerEquipList) {
	this.ossPlayerEquipList = ossPlayerEquipList;
    }

    public void setOssPlayerTaskList(List<OssZHPlayerTask> ossPlayerTaskList) {
	this.ossPlayerTaskList = ossPlayerTaskList;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setPlat(String plat) {
	this.plat = plat;
    }

    public void setPlayerArmys(List<PlayerArmy> playerArmys) {
	this.playerArmys = playerArmys;
    }

    public void setPlayerBaseClassList(List<PlayerBaseClass> playerBaseClassList) {
	this.playerBaseClassList = playerBaseClassList;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPlayerBaseInfors(List<OssZHPlayerBaseInfor> playerBaseInfors) {
	this.playerBaseInfors = playerBaseInfors;
    }

    public void setPlayerClass(OssZHPlayerClass playerClass) {
	this.playerClass = playerClass;
    }

    public void setPlayerClassList(List<OssZHPlayerClass> playerClassList) {
	this.playerClassList = playerClassList;
    }

    public void setPlayerFriendInfo(JSONArray playerFriendInfo) {
	this.playerFriendInfo = playerFriendInfo;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setPlayerInfor(OssZHPlayerInfor playerInfor) {
	this.playerInfor = playerInfor;
    }

    public void setPlayerVipInfor(OssZHPlayerVipInfor playerVipInfor) {
	this.playerVipInfor = playerVipInfor;
    }

    public void setRealTimeOnline(boolean realTimeOnline) {
	this.realTimeOnline = realTimeOnline;
    }

    /** 查看玩家角色的详细信息 */
    @Action(value = "zhplayerInfo_viewPlayBaseInfo", results = { @Result(name = "success", location = "../../zhxy/player/viewPlayBaseInfo.jsp") })
    public String viewPlayBaseInfo() throws Exception {

	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("playerBaseId", playerBaseId);
	object.put("handlerName", "PlayerInfoHandler");
	object.put("methodName", "queryPlayerBaseDetail");
	try {
	    object = CONNECTION.sendMsg(object);
	    if (object != null && object.get("ossPlayerBase") != null) {
		ossPlayerBase = (OssZHPlayerBase) object.get("ossPlayerBase"); // 玩家集合
		ossPlayerAttribute = ossPlayerBase.getOssPlayerAttribute();
		ossPlayerEquipList = ossPlayerBase.getOssPlayerEquipList();
		ossPlayeBagItemList = ossPlayerBase.getOssPlayeBagItemList();
		ossPlayerData = ossPlayerBase.getOssPlayerData();
		ossPlayerTaskList = ossPlayerBase.getOssPlayerTaskList();
		ossPlayerCopyTimesList = ossPlayerBase
			.getOssPlayerCopyTimesList();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    /** 查看玩家详细信息 */
    @Action(value = "zhplayerInfo_viewPlayInfo", results = { @Result(name = "success", location = "../../zhxy/player/viewPlayInfo.jsp") })
    public String viewPlayInfo() throws Exception {

	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("playerId", playerId);
	object.put("handlerName", "PlayerInfoHandler");
	object.put("methodName", "queryPlayerDetail");
	try {
	    object = CONNECTION.sendMsg(object);
	    if (object != null && object.get("playerInfor") != null) {
		playerInfor = (OssZHPlayerInfor) object.get("playerInfor"); // 玩家集合
		playerClass = playerInfor.getPlayerClass();
		playerBaseInfors = playerInfor.getPlayerBaseInfors();
		playerVipInfor = playerInfor.getPlayerVipInfor();
		for (OssZHPlayerBaseInfor playerBaseInfor : playerBaseInfors) {
		    PlayerArmy playerArmy = new PlayerArmy();
		    playerArmy = playerBaseInfor.getPlayerArmy();
		    if (playerArmy != null) {
			playerArmys.add(playerArmy);
		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    @Action(value = "zhplayerInfo_banPlayerBase")
    public void banPlayerBase() throws Exception {

	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("playerBaseId", playerBaseId);
	object.put("playerBaseStatus", status);
	object.put("handlerName", "BanAccountsHandler");
	object.put("methodName", "banPlayerBaseAccount");
	try {
	    object = CONNECTION.sendMsg(object);
	    if(status==-1){
		getPageMessage().setMessage("角色解封成功！");
	    }else{
		getPageMessage().setMessage("角色封禁成功！");
	    }
	    getJSONResponse().responseJson(getPageMessage());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    public List<OssOperation> getOssOperationList() {
        return ossOperationList;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
        this.ossOperationList = ossOperationList;
    }

    public String getPtCode() {
        return ptCode;
    }

    public void setPtCode(String ptCode) {
        this.ptCode = ptCode;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    @Action(value = "zhplayerInfo_queryAllPlayerBase", results = { @Result(name = "success", location = "../../zhxy/player/deletePlayerInfo.jsp") })
    public String queryAllPlayerBase() throws Exception {

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("loginName", loginName);
	if(loginName==null||"".equals(loginName)){
	    return SUCCESS;
	}
	object.put("handlerName", "PlayerInfoHandler");
	object.put("methodName", "queryAllPlayerBaseList");
	try {
	    object = CONNECTION.sendMsg(object);
	 
	    if (object != null && object.get("playerInfors") != null) {
		playerInfors =   (List<OssZHPlayerInfor>) object.get("playerInfors"); // 玩家集合
		delPlayerInfors=(List<OssZHPlayerInfor>) object.get("playerInfors"); 
//		playerClass = playerInfor.getPlayerClass();
//		playerBaseInfors = playerInfor.getPlayerBaseInfors();
//		playerVipInfor = playerInfor.getPlayerVipInfor();
//		for (OssZHPlayerBaseInfor playerBaseInfor : playerBaseInfors) {
//		    PlayerArmy playerArmy = new PlayerArmy();
//		    playerArmy = playerBaseInfor.getPlayerArmy();
//		    if (playerArmy != null) {
//			playerArmys.add(playerArmy);
//		    }
//		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }
    @Action(value = "zhplayerInfo_queryPlayerLevel", results = { @Result(name = "success", location = "../../zhxy/player/queryPlayerLevel.jsp") })
    public String queryPlayerLevel() throws Exception {
	if (beginDate == null || "".equals(beginDate)) {
	    beginTime = DateService.getCurrentMonthFirstUtilDate();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	} else {
	    beginTime = DateService.getDateFirstTime(beginDate);
	}

	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("handlerName", "PlayerInfoHandler");
	object.put("methodName", "queryPlayerLevel");
	try {
	    object = CONNECTION.sendMsg(object);
	 
	    if (object != null && object.get("levelList") != null) {
		levelList = (List<Map<String, Object>>) object.get("levelList");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }
    
    @Action(value = "zhplayerInfo_queryPlayerListByLevel", results = { @Result(name = "success", location = "../../zhxy/player/queryPlayerListByLevel.jsp") })
    public String queryPlayerListByLevel() throws Exception {
	if (beginDate == null || "".equals(beginDate)) {
	    beginTime = DateService.getCurrentMonthFirstUtilDate();
	    beginDate = DateService.getDateFormatStr(beginTime, "yyyy-MM-dd");
	} else {
	    beginTime = DateService.getDateFirstTime(beginDate);
	}

	if (endDate == null || "".equals(endDate)) {
	    endTime = DateService.getCurrentDayLastUtilDate();
	    endDate = DateService.getDateFormatStr(endTime, "yyyy-MM-dd");
	} else {
	    endTime = DateService.getDateLastTime(endDate);
	}

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("beginTime", beginTime);
	object.put("endTime", endTime);
	object.put("level", level);
	object.put("handlerName", "PlayerInfoHandler");
	object.put("methodName", "queryPlayerListByLevel");
	try {
	    object = CONNECTION.sendMsg(object);
	 
	    if (object != null && object.get("playerList") != null) {
		playerList = (List<Map<String, Object>>) object.get("playerList");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }
    public List<OssZHPlayerInfor> getPlayerInfors() {
        return playerInfors;
    }

    public void setPlayerInfors(List<OssZHPlayerInfor> playerInfors) {
        this.playerInfors = playerInfors;
    }

    public List<OssZHPlayerInfor> getDelPlayerInfors() {
        return delPlayerInfors;
    }

    public void setDelPlayerInfors(List<OssZHPlayerInfor> delPlayerInfors) {
        this.delPlayerInfors = delPlayerInfors;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Map<String, Object>> getLevelList() {
        return levelList;
    }

    public void setLevelList(List<Map<String, Object>> levelList) {
        this.levelList = levelList;
    }

    public List<Map<String, Object>> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Map<String, Object>> playerList) {
        this.playerList = playerList;
    }
    
}
