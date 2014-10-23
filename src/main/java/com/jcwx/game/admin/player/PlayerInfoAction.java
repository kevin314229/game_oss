package com.jcwx.game.admin.player;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.http.domain.OssPlayeBagItem;
import com.jcwx.game.http.domain.OssPlayerAttribute;
import com.jcwx.game.http.domain.OssPlayerBase;
import com.jcwx.game.http.domain.OssPlayerCopyTimes;
import com.jcwx.game.http.domain.OssPlayerData;
import com.jcwx.game.http.domain.OssPlayerEquip;
import com.jcwx.game.http.domain.OssPlayerTask;
import com.jcwx.game.http.domain.PlayerArmy;
import com.jcwx.game.http.domain.PlayerBaseClass;
import com.jcwx.game.http.domain.PlayerBaseInfor;
import com.jcwx.game.http.domain.PlayerClass;
import com.jcwx.game.http.domain.PlayerInfor;
import com.jcwx.game.http.domain.PlayerVipInfor;


/** 玩家详细信息 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class PlayerInfoAction extends BasalAction {

    /** 氏族 */
    // private ClanCache clanCache;

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
    private List<OssPlayeBagItem> ossPlayeBagItemList;

    private OssPlayerAttribute ossPlayerAttribute;

    private OssPlayerBase ossPlayerBase;

    List<OssPlayerCopyTimes> ossPlayerCopyTimesList;

    private OssPlayerData ossPlayerData;

    /** 装备 */
    private List<OssPlayerEquip> ossPlayerEquipList;

    private List<OssPlayerTask> ossPlayerTaskList;

    /** 总页数 */
    private Integer pages;

    /** 平台标识 */
    private String plat;

    private List<PlayerArmy> playerArmys = new ArrayList<PlayerArmy>();

    private List<PlayerBaseClass> playerBaseClassList;

    private Integer playerBaseId;

    private List<PlayerBaseInfor> playerBaseInfors;
    private PlayerClass playerClass;

    /** 玩家信息 */
    private List<PlayerClass> playerClassList;

    /** 玩家id */
    private Integer playerId;

    // /**玩家缓存信息*/
    // private PlayerCache playerCache;

    // /**玩家在线缓存信息*/
    // private PlayerOnlineCache playerOnlineCache;

    // /**指定玩家的所有宠物列表*/
    // private List<PlayerPet> playerPetList;
    //

    /** 指定玩家的村庄资源 */
    // private Village village;

    /** 所在部落 */
    // private Kampong kampong;

    /** 玩家扩展信息 */
    // private PlayerExt playerExt;

    private PlayerInfor playerInfor;

    private PlayerVipInfor playerVipInfor;

    // private OperateNumByThatDay operateNum;

    /** 实时在线，scoket连接 */
    private boolean realTimeOnline = false;

    /***
     * 查看所有角色
     * */
    @Action(value = "playerInfo_browsePlayerBaseInfo", results = { @Result(name = "success", location = "../../admin/player/browsePlayerBaseInfo.jsp") })
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
	object.put("playerBaseId", playerBaseId);
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

    @Action(value = "playerInfo_browsePlayerInfo", results = { @Result(name = "success", location = "../../admin/player/browsePlayerInfo.jsp") })
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
	    playerClassList = (List<PlayerClass>) object.get("playerClassList"); // 玩家集合
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

    /** 清理缓存 */
    @Action(value = "playerOperator_cleanerCache", results = { @Result(name = "toViewPlayInfo", type = "redirectAction", params = {
	    "actionName", "playerInfo_viewPlayInfo", "namespace",
	    "/admin/base", "actionMsg", "${actionMsg}", "playerID",
	    "${playerID}" }) })
    public String cleanerCache() {
	setActionMsg("");
	if (playerId != null) {
	    // if(GameSocketService.isSessionExist(playerID)){
	    // GameSocketService.removeSessionByPlayerID(playerID);
	    // actionMsg = "玩家被迫下线,";
	    // }
	    // IPlayerService playerService =
	    // (IPlayerService)SpringService.getBean("playerService");
	    // Map map =
	    // (Map)CacheService.getFromCache(CacheConstant.PLAYERID_PLAYERONLINECACHE_MAP);
	    // if(map!=null && map.get(playerID)!=null){
	    // //System.out.println("clear playerID:"+playerID);
	    // map.remove(playerID);
	    // actionMsg += "清除缓存成功！";
	    // }else{
	    // actionMsg += "缓存不存在不用清理！";
	    // }

	} else {
	    setActionMsg("操作失败！");
	}

	return "toViewPlayInfo";
    }

    /** 直接登录游戏 */
    public String directLoginGame() throws Exception {
	// IPlayerService playerService =
	// (IPlayerService)SpringService.getBean("playerService");
	// playerCache =
	// playerService.getPlayerCacheByPlayerID(playerID);//玩家缓存信息
	// HttpServletRequest request = ServletActionContext.getRequest();
	// HttpServletResponse response = ServletActionContext.getResponse();
	// try{
	// FlexContext.setThreadLocalHttpRequest(request);
	// FlexContext.setThreadLocalHttpResponse(response);
	// HttpSession session = FlexContext.getHttpRequest().getSession();
	// PlayerSession playerSession = null;
	// if(session.getAttribute(FlexSessionConstant.SESSION_KEY) != null) {
	// playerSession =
	// (PlayerSession)session.getAttribute(FlexSessionConstant.SESSION_KEY);
	// } else {
	// playerSession = new PlayerSession();
	// }
	// playerSession.setUsername(playerCache.getUserName());
	// session.setAttribute(FlexSessionConstant.SESSION_KEY, playerSession);
	// }catch (Exception e) {
	//
	// }finally{
	// FlexContext.clearThreadLocalObjects();
	// }
	//
	// String path = request.getContextPath();
	// String basePath =
	// request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	// if (basePath.indexOf(":80/") > 0) {
	// basePath.replaceAll(":80/", "/");
	// }
	// response.sendRedirect(basePath);

	return null;
    }

    /** 踢下线 */
    @Action(value = "playerOperator_forceDownline", results = { @Result(name = "toViewPlayInfo", type = "redirectAction", params = {
	    "actionName", "playerInfo_viewPlayInfo", "namespace",
	    "/admin/base", "actionMsg", "${actionMsg}", "playerID",
	    "${playerID}" }) })
    public String forceDownline() {

	// if(playerID!=null && GameSocketService.isSessionExist(playerID)){
	// GameSocketService.removeSessionByPlayerID(playerID);
	// actionMsg = "踢下线成功!";
	// }else{
	// actionMsg = "玩家不在线！";
	// }
	return "toViewPlayInfo";
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

    public List<OssPlayeBagItem> getOssPlayeBagItemList() {
	return ossPlayeBagItemList;
    }

    public OssPlayerAttribute getOssPlayerAttribute() {
	return ossPlayerAttribute;
    }

    public OssPlayerBase getOssPlayerBase() {
	return ossPlayerBase;
    }

    public List<OssPlayerCopyTimes> getOssPlayerCopyTimesList() {
	return ossPlayerCopyTimesList;
    }

    public OssPlayerData getOssPlayerData() {
	return ossPlayerData;
    }

    public List<OssPlayerEquip> getOssPlayerEquipList() {
	return ossPlayerEquipList;
    }

    public List<OssPlayerTask> getOssPlayerTaskList() {
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

    public List<PlayerBaseInfor> getPlayerBaseInfors() {
	return playerBaseInfors;
    }

    public PlayerClass getPlayerClass() {
	return playerClass;
    }

    public List<PlayerClass> getPlayerClassList() {
	return playerClassList;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public PlayerInfor getPlayerInfor() {
	return playerInfor;
    }

    public PlayerVipInfor getPlayerVipInfor() {
	return playerVipInfor;
    }

    public boolean isRealTimeOnline() {
	return realTimeOnline;
    }

    /** 修改当前资源值,注意权限控制 */
    @Action(value = "playerCurOper_modifyCurrentVillageResource", results = { @Result(name = "toViewPlayInfo", type = "redirectAction", params = {
	    "actionName", "playerInfo_viewPlayInfo", "namespace",
	    "/admin/base", "actionMsg", "${actionMsg}", "playerID",
	    "${playerID}" }) })
    public String modifyCurrentVillageResource() {
	if (operateFlag != null && playerId != null) {
	    // IPlayerService playerService = (IPlayerService)
	    // SpringService.getBean("playerService");
	    // playerCache =
	    // playerService.getPlayerCacheByPlayerID(playerID);//玩家缓存信息
	    // IVillageService villageService =
	    // (IVillageService)SpringService.getBean("villageService");
	    // village =
	    // villageService.getVillageWithVillageResourceByPlayerID(playerID);
	    // // if (operateFlag.equals("battleFlagNum")) { // 石牌
	    // // Integer formerNum =
	    // playerService.getBattleFlagNum(playerID);//原先数量
	    // // playerService.updateBattleFlagNum(playerID, formerNum+30);
	    // // actionMsg = "修改石牌数量成功！";
	    // // }
	    // if (operateFlag.equals("moneyNum")) {
	    // if(village.getVillageResource().getMoneyNumMax()-village.getVillageResource().getMoneyNum()<=0){
	    // actionMsg = "银币已达到上限，不用修改!";
	    // }else{
	    // playerService.addPlayerResource(playerCache.getVillageID(),0,0,village.getVillageResource().getMoneyNumMax()-village.getVillageResource().getMoneyNum(),0);
	    // actionMsg = "修改银币数量成功！";
	    // }
	    // } else if (operateFlag.equals("foodNum")) {
	    // if(village.getVillageResource().getFoodNumMax()-village.getVillageResource().getFoodNum()<=0){
	    // actionMsg = "食物数量已达到上限，不用修改!";
	    // }else{
	    // playerService.addPlayerResource(playerCache.getVillageID(),0,village.getVillageResource().getFoodNumMax()-village.getVillageResource().getFoodNum(),0,0);
	    // actionMsg = "修改食物数量成功！";
	    // }
	    // } else if (operateFlag.equals("woodNum")) {
	    // if(village.getVillageResource().getWoodNumMax()-village.getVillageResource().getWoodNum()<=0){
	    // actionMsg = "木材数量已达到上限，不用修改！";
	    // }else{
	    // playerService.addPlayerResource(playerCache.getVillageID(),village.getVillageResource().getWoodNumMax()-village.getVillageResource().getWoodNum(),0,0,0);
	    // actionMsg = "修改木材数量成功！";
	    // }
	    // }else if (operateFlag.equals("petHpNum")) {
	    // if(village.getVillageResource().getPetHpNumMax()-village.getVillageResource().getPetHpNum()<=0){
	    // actionMsg = "修改体力数量已达到上限，不用修改！";
	    // }else{
	    // playerService.addPlayerResource(playerCache.getVillageID(),0,0,0,village.getVillageResource().getPetHpNumMax()-village.getVillageResource().getPetHpNum());
	    // actionMsg = "修改体力数量成功！";
	    // }
	    // }
	    //
	}
	return "toViewPlayInfo";
    }

    /** 修改当前资源值，手动数据输入，后面加上数据推送至游戏 */
    @Action(value = "playerCurOper_modifyResourceByManual", results = { @Result(name = "toViewPlayInfo", type = "redirectAction", params = {
	    "actionName", "playerInfo_viewPlayInfo", "namespace",
	    "/admin/base", "actionMsg", "${actionMsg}", "playerID",
	    "${playerID}" }) })
    public String modifyResourceByManual() {
	setActionMsg("");
	if (playerId != null) {
	    // IPlayerService playerService = (IPlayerService)
	    // SpringService.getBean("playerService");
	    // playerCache =
	    // playerService.getPlayerCacheByPlayerID(playerID);//玩家缓存信息
	    // IVillageService villageService =
	    // (IVillageService)SpringService.getBean("villageService");
	    // village =
	    // villageService.getVillageWithVillageResourceByPlayerID(playerID);
	    //
	    // HttpServletRequest request = ServletActionContext.getRequest();
	    //
	    // Integer battleFlagNum =
	    // CommonUtils.getInteger(request.getParameter("battleFlagNum_1"));
	    // Integer exploit =
	    // CommonUtils.getInteger(request.getParameter("exploit_1"));
	    // Integer money =
	    // CommonUtils.getInteger(request.getParameter("money_1"));
	    // Integer enthrallmentAttestTime
	    // =CommonUtils.getInteger(request.getParameter("enthrallmentAttestTime_1"));//TODO
	    // 防沉迷时间设置 临时功能
	    // if(battleFlagNum != null){
	    // if(battleFlagNum<0 || battleFlagNum>100000){
	    // actionMsg +="石牌数值不正确,应该在于或等于0,小于100000,";
	    // }else{
	    //
	    // playerService.updateBattleFlagNum(playerID,playerService.getBattleFlagNum(playerID)+battleFlagNum);
	    // actionMsg +="增加石牌成功,";
	    // }
	    //
	    // if (GameSocketService.isSessionExist(playerID)) {
	    // //如果玩家在线。推送数据到客户端
	    // JSONObject json = new JSONObject();
	    // try {
	    // json.put("type", GameSocketConstant.REFRESH_BATTLE_FLAG_NNM);
	    // json.put("battleFlagNum",
	    // playerService.getBattleFlagNum(playerID));
	    // } catch (JSONException e) {
	    // logger.error(ResourceBundleService.getString("txt.exception"),
	    // e);
	    // }
	    //
	    // GameSocketService.sendDataToClient(playerID, json);
	    // }
	    //
	    // }
	    // if(exploit != null){
	    // if(exploit<0 || exploit>100000){
	    // actionMsg +="荣誉数值不正确,应该在于或等于0,小于100000,";
	    // }else{
	    // playerService.addPlayerExploit(playerID, exploit.longValue(), 1);
	    // actionMsg +="增加荣誉成功,";
	    // }
	    //
	    // }
	    // if(money != null){
	    // if(money<0 || money>100000){
	    // actionMsg +="金币数值增加不正确,应该在于或等于0,小于100000,";
	    // }else{
	    // IPayMoneyService payMoneyService =
	    // (IPayMoneyService)SpringService.getBean("payMoneyService");
	    // String username =
	    // playerService.getPlayerByID(playerID).getUserName();
	    // payMoneyService.addPlayerMoneyByAdmin(username, money, "后台充值");
	    // actionMsg +="增加金币成功,";
	    // }
	    //
	    // }
	    // if(enthrallmentAttestTime!=null){
	    // playerService.getPlayerCacheByPlayerID(playerID).setOnlineTimeByEnthrallment(enthrallmentAttestTime);
	    // actionMsg += "修改防沉迷时间缓存成功,";
	    // }
	    // if(actionMsg!=""){
	    // actionMsg += "以上操作已记录日志。";
	    // }else{
	    // actionMsg += "没有修改数值。";
	    // }
	}

	return "toViewPlayInfo";
    }

    // public PlayerCache getPlayerCache() {
    // return playerCache;
    // }
    //
    // public void setPlayerCache(PlayerCache playerCache) {
    // this.playerCache = playerCache;
    // }
    //
    // public PlayerOnlineCache getPlayerOnlineCache() {
    // return playerOnlineCache;
    // }
    //
    // public void setPlayerOnlineCache(PlayerOnlineCache playerOnlineCache) {
    // this.playerOnlineCache = playerOnlineCache;
    // }
    //
    // public List<PlayerPet> getPlayerPetList() {
    // return playerPetList;
    // }
    //
    // public void setPlayerPetList(List<PlayerPet> playerPetList) {
    // this.playerPetList = playerPetList;
    // }
    // public Village getVillage() {
    // return village;
    // }
    //
    // public void setVillage(Village village) {
    // this.village = village;
    // }
    //
    // public Kampong getKampong() {
    // return kampong;
    // }
    //
    // public void setKampong(Kampong kampong) {
    // this.kampong = kampong;
    // }
    //
    // public PlayerExt getPlayerExt() {
    // return playerExt;
    // }
    //
    // public void setPlayerExt(PlayerExt playerExt) {
    // this.playerExt = playerExt;
    // }

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

    public void setOssPlayeBagItemList(List<OssPlayeBagItem> ossPlayeBagItemList) {
	this.ossPlayeBagItemList = ossPlayeBagItemList;
    }

    public void setOssPlayerAttribute(OssPlayerAttribute ossPlayerAttribute) {
	this.ossPlayerAttribute = ossPlayerAttribute;
    }

    public void setOssPlayerBase(OssPlayerBase ossPlayerBase) {
	this.ossPlayerBase = ossPlayerBase;
    }

    public void setOssPlayerCopyTimesList(
	    List<OssPlayerCopyTimes> ossPlayerCopyTimesList) {
	this.ossPlayerCopyTimesList = ossPlayerCopyTimesList;
    }

    public void setOssPlayerData(OssPlayerData ossPlayerData) {
	this.ossPlayerData = ossPlayerData;
    }

    public void setOssPlayerEquipList(List<OssPlayerEquip> ossPlayerEquipList) {
	this.ossPlayerEquipList = ossPlayerEquipList;
    }

    public void setOssPlayerTaskList(List<OssPlayerTask> ossPlayerTaskList) {
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

    public void setPlayerBaseInfors(List<PlayerBaseInfor> playerBaseInfors) {
	this.playerBaseInfors = playerBaseInfors;
    }

    public void setPlayerClass(PlayerClass playerClass) {
	this.playerClass = playerClass;
    }

    public void setPlayerClassList(List<PlayerClass> playerClassList) {
	this.playerClassList = playerClassList;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setPlayerInfor(PlayerInfor playerInfor) {
	this.playerInfor = playerInfor;
    }

    public void setPlayerVipInfor(PlayerVipInfor playerVipInfor) {
	this.playerVipInfor = playerVipInfor;
    }

    public void setRealTimeOnline(boolean realTimeOnline) {
	this.realTimeOnline = realTimeOnline;
    }

    /** 查看玩家角色的详细信息 */
    @Action(value = "playerInfo_viewPlayBaseInfo", results = { @Result(name = "success", location = "../../admin/player/viewPlayBaseInfo.jsp") })
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
		ossPlayerBase = (OssPlayerBase) object.get("ossPlayerBase"); // 玩家集合
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
    @Action(value = "playerInfo_viewPlayInfo", results = { @Result(name = "success", location = "../../admin/player/viewPlayInfo.jsp") })
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
		playerInfor = (PlayerInfor) object.get("playerInfor"); // 玩家集合
		playerClass = playerInfor.getPlayerClass();
		playerBaseInfors = playerInfor.getPlayerBaseInfors();
		playerVipInfor = playerInfor.getPlayerVipInfor();
		for (PlayerBaseInfor playerBaseInfor : playerBaseInfors) {
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
	// IPlayerService playerService =
	// (IPlayerService)SpringService.getBean("playerService");
	// player = playerService.getPlayerInfo(playerID); //玩家信息
	// playerCache =
	// playerService.getPlayerCacheByPlayerID(playerID);//玩家缓存信息
	// playerOnlineCache =
	// playerService.getPlayerOnlineCacheByPlayerID(playerID);//玩家在线缓存
	// IPlayerPetService playerPetService
	// =(IPlayerPetService)SpringService.getBean("playerPetService");
	// playerPetList =
	// playerPetService.getPlayerPetListByPlayerID(playerID);//玩家的所有宠物
	// IEquipService equipService =
	// (IEquipService)SpringService.getBean("equipService");
	// equipList =
	// equipService.getPlayerEquipListByPlayerID(playerID);//指定玩家的装备列表
	// IVillageService villageService =
	// (IVillageService)SpringService.getBean("villageService");
	// village =
	// villageService.getVillageWithVillageResourceByPlayerID(playerID);
	// kampong =villageService.getKampong(village.getKampongID());//所在部落
	// playerExt = playerService.getPlayerExtByID(playerID);//玩家扩展信息
	// realTimeOnline = GameSocketService.isSessionExist(playerID);
	//
	// operateNum = playerOnlineCache.getOperateNumByThatDay();//当日操作次数记录
	//
	// IClanService clanService =
	// (IClanService)SpringService.getBean("clanService");
	// clanCache =
	// clanService.getClanCacheByClanID(playerCache.getGuildID());
	//
	return SUCCESS;
    }

    // public OperateNumByThatDay getOperateNum() {
    // return operateNum;
    // }
    //
    // public void setOperateNum(OperateNumByThatDay operateNum) {
    // this.operateNum = operateNum;
    // }
    //
    // public ClanCache getClanCache() {
    // return clanCache;
    // }
    //
    // public void setClanCache(ClanCache clanCache) {
    // this.clanCache = clanCache;
    // }

}
