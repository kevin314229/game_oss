package com.jcwx.game.admin.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.common.constants.DictTypeConstant;
import com.jcwx.game.domain.OssDictData;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.http.domain.PlayerBank;
import com.jcwx.game.service.oss.IOssDictService;
import com.jcwx.game.service.oss.IOssLogService;
import com.jcwx.game.util.BocHttpClient;
import com.jcwx.game.util.PropertiesUtil;


/** 封禁账户 */
@ParentPackage("base")
@Namespace("/zhxy/base")
@ResultPath("/")
public class ZHBanAccountsAction extends BasalAction {

    private static final long serialVersionUID = -5546260838626507001L;

    /** 总记录数 */
    private Integer allNum;

    /** 当前页数 */
    private Integer currPageNO;

    /** 搜索关键字 */
    private String keyword;

    /** 每页记录数 */
    private Integer onePageNum;

    /** 排序标记 */
    private String orderFlag;

    /** 总页数 */
    private Integer pages;

    private String plat;

    /** 玩家信息 */
    private List<PlayerBank> playerBankList;

    /** 玩家id */
    private Integer playerId;

    /** 玩家状态 */
    private Integer playerStatus = -1;
    @Autowired
    private IOssLogService ossLogService;
    
    private String loginName;

    /** 封禁账号 */
    @Action(value = "zhbanAccounts_banAccounts", results = { @Result(name = "playerStatus", type = "redirectAction", params = {
	    "actionName", "zhbanAccounts_browseBanAccounts", "namespace",
	    "/zhxy/base", "actionMsg", "${actionMsg}", "playerStatus",
	    "${playerStatus}" }) })
    public String banAccounts() {
	// IPlayerService playerService =
	// (IPlayerService)SpringService.getBean("playerService");
	// Integer result = playerService.updatePlayerState(playerID,
	// PlayerStateConstant.STOPUSE);
	// if(result>0){
	// this.setActionMsg("封禁成功！");
	// }else{
	// this.setActionMsg("封禁失败！");
	// }
	// playerStatus = 0;//回到正常选项的页面
	Map<String, Object> object = new HashMap<String, Object>();
	playerStatus = 1;// 封禁
	object.put("playerId", playerId);
	object.put("playerStatus", playerStatus);
	object.put("handlerName", "BanAccountsHandler");
	object.put("methodName", "banAccount");
	Integer result = 0;
	try {
	    object = CONNECTION.sendMsg(object);
	    result = (Integer) object.get("result");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// BlackList blackList =
	// authorizationLimitService.getBlackListByIP(playerIP);
	// blackList.setBanState(BlackListConstant.STOPUSE);
	// blackList.setBanTime(blackList.getBanTime()+1);
	// blackList.setBanEndTime(DateService.dateIncreaseByDay(blackList.getBanEndTime(),
	// 30));//默认加30天
	// Integer result =
	// authorizationLimitService.updateBlackList(blackList);
	if (result != null && result > 0) {
	    this.setActionMsg("操作成功 !");
	} else {
	    this.setActionMsg("操作失败 !");
	}
	// banState = BlackListConstant.STOPUSE;
	return "playerStatus";
    }

    /** 查看封禁账号 */
    @Action(value = "zhbanAccounts_browseBanAccounts", results = { @Result(name = "success", location = "../../zhxy/player/browseBanAccounts.jsp") })
    public String browseBanAccounts() throws Exception {

	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}

	// 排序
	if (orderFlag == null || "".equals(orderFlag)) {
	    orderFlag = "DESC";
	}
	onePageNum = 20; // 每页20条记录
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	if (playerStatus == -1)
	    playerStatus = null;
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("keyword", keyword);
	object.put("plat", plat);// TODO ..这个暂时不用
	object.put("playerStatus", playerStatus);
	object.put("orderFlag", orderFlag);
	object.put("beginNum", beginNum);
	object.put("onePageNum", onePageNum);
	object.put("handlerName", "BanAccountsHandler");
	try {
	    object = CONNECTION.sendMsg(object);
	    allNum = (Integer) object.get("allNum");
	    playerBankList = (List<PlayerBank>) object.get("playerBankList");
	    pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		    / onePageNum;
	    // 当前页设置
	    if (currPageNO.intValue() > pages) {
		currPageNO = pages;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// IPlayerService playerService =
	// (IPlayerService)SpringService.getBean("playerService");
	// 总记录数
	// 玩家集合
	// playerList =
	// playerService.getPlayerLastLoginInfoListByState(keyword,state,
	// orderFlag, beginNum, onePageNum);
	// System.out.println("测试::"+playerList.size());
	// 测试用
	// PlayerLastLoginInfo pll = new PlayerLastLoginInfo();
	// pll.setCreateTime(new Date());
	// pll.setUserName("diego");
	// pll.setLastLoginIP("192.168.1.168");
	// pll.setLevel(20);
	// pll.setOnlineMinutes(100);
	// //pll.setPlayerID(1);
	// pll.setPlayerName("diego");
	// playerList.add( pll );
	return SUCCESS;
    }

    // @Autowired
    // private IPlayerService playerService;
    /** 封禁账号 */
    @Action(value = "zhbanAccounts_delAccounts", results = { @Result(name = "playerStatus", type = "redirectAction", params = {
	    "actionName", "zhbanAccounts_browseBanAccounts", "namespace",
	    "/zhxy/base", "actionMsg", "${actionMsg}", "playerStatus",
	    "${playerStatus}" }) })
    public String delAccounts() {
	try {
	    String url =null;
	    Map<String,String> dictMap=queryDictMap(DictTypeConstant.GAME_XY, DictTypeConstant.ACCOUNT_DELETE);
	    url=dictMap.get("center_account_url");
	    if(loginName==null||"".equals(loginName)||loginName.split(",").length<2){
		return "playerStatus";
	    }
	    if(url==null||"".equals(url)){
		url = PropertiesUtil.getValue("center_account_url");
	    }
	    Map<String, String> params = new HashMap<String, String>();
	    String userName =  loginName.split(",")[1];
	    String ptCode =  loginName.split(",")[0];
	    params.put("userName", userName);
	    params.put("ptCode", ptCode);
	
	    String jsonObject = BocHttpClient.sendPost(url, params);
	   
	    JSONObject json = JSON.parseObject(jsonObject);
	    Integer code = json.getInteger("code");
	    if (code != null && code == 0) {
		ossLogService.createOssLog("删除账号", "删除账号" + loginName + "成功！");
		this.setActionMsg("操作成功 !");
	    } else {
		ossLogService.createOssLog("删除账号", "删除账号" + loginName + "失败！"+json.getString("msg"));
		String msg = json.getString("actionErrors").replace("\"", "\'");
		this.setActionMsg("操作失败 !"+msg);
		return "playerStatus";
	    }
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("loginName", userName);
	    object.put("methodName", "deleteAccount");
	    object.put("handlerName", "oss.AccountDeleteHandler");
	    List<OssServer> serverList=getBaseAdminContext().getOssServers();
	    for(OssServer ossServer:serverList){
		 object = CONNECTION.sendMsg(ossServer.getId(), object);
	    }
	 //   object = CONNECTION.sendMsg(object);
	   
	} catch (Exception e) {
	    LOG.error("删除账号" + loginName + "失败！", e);
	}
	return "playerStatus";
    }
    
    /** 封禁账号 */
    @Action(value = "zhbanAccounts_recoveryAccount", results = { @Result(name = "playerStatus", type = "redirectAction", params = {
	    "actionName", "zhbanAccounts_browseBanAccounts", "namespace",
	    "/zhxy/base", "actionMsg", "${actionMsg}", "playerStatus",
	    "${playerStatus}" }) })
    public String recoveryAccount() {
	try {
	    String url = PropertiesUtil.getValue("center_account_url");
	    if(url==null||"".equals(url)){
		 Map<String,String> dictMap=queryDictMap(DictTypeConstant.GAME_XY, 102);
		 url=dictMap.get("center_account_url");
	    }
	    Map<String, String> params = new HashMap<String, String>();
	    if(loginName==null||"".equals(loginName)||loginName.split(",").length<2){
		return "playerStatus";
	    }
	    String userName =  loginName.split(",")[1];
	    String ptCode =  loginName.split(",")[0];
	    params.put("userName", userName);
	    params.put("ptCode", "kr_google");
	    String jsonObject = BocHttpClient.sendPost(url, params);
	    JSONObject json = JSON.parseObject(jsonObject);
	    Integer code = json.getInteger("code");
	    if (code != null && code == 0) {
		ossLogService.createOssLog("恢复账号", "恢复账号" + loginName + "成功！");
		this.setActionMsg("操作成功 !");
	    } else {
		ossLogService.createOssLog("恢复账号", "恢复账号" + loginName + "失败！"+json.getString("msg"));
		String msg = json.getString("actionErrors").replace("\"", "\'");
		this.setActionMsg("操作失败 !"+msg);
		return "playerStatus";
	    }
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("loginName", userName);
	    object.put("methodName", "recoveryAccount");
	    object.put("handlerName", "oss.AccountDeleteHandler");
	    List<OssServer> serverList=getBaseAdminContext().getOssServers();
	    for(OssServer ossServer:serverList){
		 object = CONNECTION.sendMsg(ossServer.getId(), object);
	    }
	    //object = CONNECTION.sendMsg(object);
	} catch (Exception e) {
	    LOG.error("恢复账号" + loginName + "失败！", e);
	}
	return "playerStatus";
    }
    public Integer getAllNum() {
	return allNum;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public String getKeyword() {
	return keyword;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    public String getOrderFlag() {
	return orderFlag;
    }

    public Integer getPages() {
	return pages;
    }

    public String getPlat() {
	return plat;
    }

    public List<PlayerBank> getPlayerBankList() {
	return playerBankList;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public Integer getPlayerStatus() {
	return playerStatus;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setKeyword(String keyword) {
	this.keyword = keyword;
    }

    public void setOnePageNum(Integer onePageNum) {
	this.onePageNum = onePageNum;
    }

    public void setOrderFlag(String orderFlag) {
	this.orderFlag = orderFlag;
    }

    public void setPages(Integer pages) {
	this.pages = pages;
    }

    public void setPlat(String plat) {
	this.plat = plat;
    }

    public void setPlayerBankList(List<PlayerBank> playerBankList) {
	this.playerBankList = playerBankList;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setPlayerStatus(Integer playerStatus) {
	this.playerStatus = playerStatus;
    }

    /** 解除封禁账号 */
    @Action(value = "zhbanAccounts_unBanAccounts", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "zhbanAccounts_browseBanAccounts", "namespace",
	    "/zhxy/base", "actionMsg", "${actionMsg}", "playerStatus",
	    "${playerStatus}" }) })
    public String unBanAccounts() {
	// IPlayerService playerService =
	// (IPlayerService)SpringService.getBean("playerService");
	// Integer result = playerService.updatePlayerState(playerID,
	// PlayerStateConstant.NORMAL);
	// if(result>0){
	// this.setActionMsg("解除封禁成功！");
	// }else{
	// this.setActionMsg("解除封禁失败！");
	// }
	Map<String, Object> object = new HashMap<String, Object>();
	playerStatus = 0;
	object.put("playerId", playerId);
	object.put("playerStatus", playerStatus);
	object.put("handlerName", "BanAccountsHandler");
	object.put("methodName", "banAccount");
	Integer result = 0;
	try {
	    object = CONNECTION.sendMsg(object);
	    result = (Integer) object.get("result");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// BlackList blackList =
	// authorizationLimitService.getBlackListByIP(playerIP);
	// blackList.setBanState(BlackListConstant.STOPUSE);
	// blackList.setBanTime(blackList.getBanTime()+1);
	// blackList.setBanEndTime(DateService.dateIncreaseByDay(blackList.getBanEndTime(),
	// 30));//默认加30天
	// Integer result =
	// authorizationLimitService.updateBlackList(blackList);
	if (result != null && result > 0) {
	    this.setActionMsg("操作成功 !");
	} else {
	    this.setActionMsg("操作失败  !");
	}
	playerStatus = 1;
	return "success";
    }
    private  Map<String, String> queryDictMap(Integer gameId, Integer type) {
   	IOssDictService dictService = (IOssDictService) SpringService
   		.getBean("ossDictService");
   	List<OssDictData> dictList = dictService.getOssDictDataList(gameId,
   		type);
   	Map<String, String> dictMap = new HashMap<String, String>();
   	if (dictList != null) {
   	    for (OssDictData dictData : dictList) {
   		dictMap.put(dictData.getDictValue(), dictData.getDictName());
   	    }
   	}
   	return dictMap;
       }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    
}
