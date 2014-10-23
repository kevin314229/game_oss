package com.jcwx.game.admin.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.http.domain.PlayerBank;


/** 封禁账户 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class BanAccountsAction extends BasalAction {

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

    /** 封禁账号 */
    @Action(value = "banAccounts_banAccounts", results = { @Result(name = "playerStatus", type = "redirectAction", params = {
	    "actionName", "banAccounts_browseBanAccounts", "namespace",
	    "/admin/base", "actionMsg", "${actionMsg}", "playerStatus",
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
    @Action(value = "banAccounts_browseBanAccounts", results = { @Result(name = "success", location = "../../admin/player/browseBanAccounts.jsp") })
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
    @Action(value = "banAccounts_unBanAccounts", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "banAccounts_browseBanAccounts", "namespace",
	    "/admin/base", "actionMsg", "${actionMsg}", "playerStatus",
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

}
