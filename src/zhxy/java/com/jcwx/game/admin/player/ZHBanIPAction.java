package com.jcwx.game.admin.player;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.constants.BlackListConstant;
import com.jcwx.game.domain.BlackList;
import com.jcwx.game.domain.LoginLog;


/** 封禁IP */
@ParentPackage("base")
@Namespace("/zhxy/base")
@ResultPath("/")
public class ZHBanIPAction extends BasalAction {

    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private Integer allNum;

    /** 封禁到期时间 */
    private Date banEndTime;

    /** 被封状态 */
    private Integer banState = -1;

    /** 黑名单列表 */
    private List<BlackList> blackListList;

    /** 当前页数 */
    private Integer currPageNO;

    /** 封禁到期时间 */
    private String endDate;

    /** 搜索关键字 */
    private String keyword;

    private LoginLog loginlog;

    /** 每页记录数 */
    private Integer onePageNum;

    /** 排序标记 */
    private String orderFlag;

    /** 总页数 */
    private Integer pages;

    private String playerId;

    /** IP */
    private String playerIP;

    private String temp;

    // @Autowired
    // private IAuthorizationLimitService authorizationLimitService;

    /** 添加封禁 */
    @Action(value = "ZHbanIP_addBanIP", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "ZHbanIP_browseBanIP", "namespace",
		    "/zhxy/base", "actionMsg", "${actionMsg}" }),
	    @Result(name = "input", location = "/admin/player/addBanIP.jsp") })
    public String AddBanIP() {
	if (endDate != null && !"".equals(endDate)) {
	    banEndTime = DateService.getDateLastTime(endDate);
	} else {
	    banEndTime = null;

	    this.setActionMsg("日期不能为空");
	    return "input";
	}
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("playerIP", playerIP);
	object.put("banEndTime", banEndTime);
	object.put("handlerName", "BanIPHandler");
	object.put("methodName", "addBanIP");
	Integer result = 0;
	try {
	    object = CONNECTION.sendMsg(object);
	    result = (Integer) object.get("result");
	} catch (Exception e) {
	    e.printStackTrace();
	}

	if (playerIP != null && !"".equals(playerIP) && banEndTime != null) {
	    // IAuthorizationLimitService authorizationLimitService =
	    // (IAuthorizationLimitService)SpringService.getBean("authorizationLimitService");
	    // authorizationLimitService.createBlackList(playerIP.trim(),banEndTime);
	    this.setActionMsg("添加封禁IP成功！");
	} else {
	    this.setActionMsg("请输入正确的信息！");
	}
	return SUCCESS;
    }

    /**
     * 封禁
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "ZHbanIP_banIP", results = { @Result(name = "banState", type = "redirectAction", params = {
	    "actionName", "ZHbanIP_browseBanIP", "namespace", "/zhxy/base",
	    "actionMsg", "${actionMsg}", "banState", "${banState}" }) })
    public String banIP() throws Exception {

	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}
	banState = BlackListConstant.STOPUSE;
	// banEndTime = DateService.dateIncreaseByDay(blackList.getBanEndTime(),
	// 30);
	// IAuthorizationLimitService authorizationLimitService =
	// (IAuthorizationLimitService)SpringService.getBean("authorizationLimitService");
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("playerIP", playerIP);
	object.put("banState", banState);
	object.put("handlerName", "BanIPHandler");
	object.put("methodName", "banIP");
	// object.put("banEndTime", banEndTime);
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
	    this.setActionMsg("封禁IP成功！");
	} else {
	    this.setActionMsg("封禁IP失败！");
	}
	// banState = BlackListConstant.STOPUSE;
	return "banState";
    }

    /**
     * 浏览被封禁的IP
     * 
     * @throws Exception
     */
    @Action(value = "ZHbanIP_browseBanIP", results = { @Result(name = "success", location = "../../admin/player/ZHbrowseBanIP.jsp") })
    public String browseBanIP() throws Exception {

	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}
	// 排序
	if (orderFlag == null || "".equals(orderFlag)) {
	    orderFlag = "DESC";
	}

	if (keyword != null)
	    keyword = keyword.trim();

	// IAuthorizationLimitService authorizationLimitService =
	// (IAuthorizationLimitService)SpringService.getBean("authorizationLimitService");
	// 总记录数
	// allNum = authorizationLimitService.getAllBlackListPageCount(keyword,
	// banState);
	// onePageNum = 20; //每页20条记录
	// Integer beginNum = (currPageNO -1) * onePageNum;
	// if(beginNum<0) beginNum = 0;
	// 总记录数
	onePageNum = 20; // 每页20条记录
	if (currPageNO == null || currPageNO.intValue() == 0) {
	    currPageNO = 1;
	}
	Integer beginNum = (currPageNO - 1) * onePageNum;
	if (beginNum < 0)
	    beginNum = 0;

	// blackListList =
	// authorizationLimitService.getAllBlackListPage(keyword, banState,
	// orderFlag, beginNum, onePageNum);
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("keyword", keyword);
	if (banState == -1) {
	    banState = null;
	}
	object.put("banState", banState);
	object.put("orderFlag", orderFlag);
	object.put("beginNum", beginNum);
	object.put("onePageNum", onePageNum);
	object.put("handlerName", "BanIPHandler");
	try {
	    object = CONNECTION.sendMsg(object);
	    allNum = (Integer) object.get("allNum");
	    blackListList = (List<BlackList>) object.get("blackListList");
	    pages = allNum % onePageNum > 0 ? allNum / onePageNum + 1 : allNum
		    / onePageNum;
	    // 当前页设置
	    if (currPageNO.intValue() > pages) {
		currPageNO = pages;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;

    }

    public Integer getAllNum() {
	return allNum;
    }

    public Date getBanEndTime() {
	return banEndTime;
    }

    public Integer getBanState() {
	return banState;
    }

    public List<BlackList> getBlackListList() {
	return blackListList;
    }

    public Integer getCurrPageNO() {
	return currPageNO;
    }

    public String getEndDate() {
	return endDate;
    }

    public String getKeyword() {
	return keyword;
    }

    public LoginLog getLoginlog() {
	return loginlog;
    }

    public Integer getOnePageNum() {
	return onePageNum;
    }

    /*
	*//** 添加封禁 */
    /*
     * @Action(value="ZHbanIP_addBanIP",results={@Result(name="success",location=
     * "../../admin/player/ZHaddBanIP.jsp")}) public String addBanIP(){
     * 
     * if (endDate != null && !"".equals(endDate)) { banEndTime =
     * DateService.getDateLastTime(endDate); } else { banEndTime = null;
     * 
     * this.setActionMsg("日期不能为空"); return "input"; } BaseAdminContext
     * baseAdminContext = (BaseAdminContext) ServletActionContext
     * .getRequest().getSession()
     * .getAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY); Map<String,
     * Object> object = new HashMap<String, Object>(); object.put("playerIP",
     * playerIP); object.put("banEndTime", banEndTime);
     * object.put("handlerName", "BanIPHandler"); object.put("methodName",
     * "addBanIP"); Integer result = 0; try { object =
     * getConnection().sendMsg(baseAdminContext, object); result = (Integer)
     * object.get("result"); } catch (Exception e) { e.printStackTrace(); }
     * 
     * if(playerIP!=null && !"".equals(playerIP) && banEndTime!=null){
     * //IAuthorizationLimitService authorizationLimitService =
     * (IAuthorizationLimitService
     * )SpringService.getBean("authorizationLimitService"); //
     * authorizationLimitService.createBlackList(playerIP.trim(),banEndTime);
     * this.setActionMsg("添加封禁IP成功！"); }else{ this.setActionMsg("请输入正确的信息！"); }
     * return SUCCESS; }
     */
    public String getOrderFlag() {
	return orderFlag;
    }

    public Integer getPages() {
	return pages;
    }

    public String getPlayerId() {
	return playerId;
    }

    public String getPlayerIP() {
	return playerIP;
    }

    public String getTemp() {
	return temp;
    }

    /**
     * 查询账号信息
     * 
     * @throws Exception
     */
    @Action(value = "ZHbanIP_select", results = { @Result(name = "success", location = "../../admin/player/ZHaddBanIP.jsp") })
    public String selectBanIP() throws Exception {
	if (playerId != null || !playerId.equals("")) {
	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("playerId", playerId);
	    object.put("handlerName", "LoginLogHandler");
	    object.put("methodName", "browLoginLogSelectPlayer");
	    object = CONNECTION.sendMsg(object);
	    try {
		if (object != null) {
		    loginlog = (LoginLog) object.get("loginlog");
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
	return SUCCESS;
    }

    public void setAllNum(Integer allNum) {
	this.allNum = allNum;
    }

    public void setBanEndTime(Date banEndTime) {
	this.banEndTime = banEndTime;
    }

    public void setBanState(Integer banState) {
	this.banState = banState;
    }

    public void setBlackListList(List<BlackList> blackListList) {
	this.blackListList = blackListList;
    }

    public void setCurrPageNO(Integer currPageNO) {
	this.currPageNO = currPageNO;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setKeyword(String keyword) {
	this.keyword = keyword;
    }

    public void setLoginlog(LoginLog loginlog) {
	this.loginlog = loginlog;
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

    public void setPlayerId(String playerId) {
	this.playerId = playerId;
    }

    public void setPlayerIP(String playerIP) {
	this.playerIP = playerIP;
    }

    public void setTemp(String temp) {
	this.temp = temp;
    }

    /** 添加封禁 */
    @Action(value = "ZHbanIP_toAddBanIP", results = { @Result(name = "success", location = "../../admin/player/ZHaddBanIP.jsp") })
    public String toAddBanIP() {
	return SUCCESS;
    }

    /**
     * 解除封禁
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "ZHbanIP_UnbanIP", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "ZHbanIP_browseBanIP", "namespace", "/zhxy/base",
	    "actionMsg", "${actionMsg}" }) })
    public String UnbanIP() throws Exception {

	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}
	banState = BlackListConstant.NORMAL;
	// IAuthorizationLimitService authorizationLimitService =
	// (IAuthorizationLimitService)SpringService.getBean("authorizationLimitService");
	// BlackList blackList =
	// authorizationLimitService.getBlackListByIP(playerIP);
	Map<String, Object> object = new HashMap<String, Object>();
	object.put("playerIP", playerIP);
	object.put("banState", banState);
	object.put("handlerName", "BanIPHandler");
	object.put("methodName", "banIP");
	// object.put("banEndTime", banEndTime);
	Integer result = 0;
	try {
	    object = CONNECTION.sendMsg(object);
	    result = (Integer) object.get("result");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	// Integer result =
	// authorizationLimitService.updateBlackList(blackList);
	if (result != null && result > 0) {
	    this.setActionMsg("解除封禁IP成功！");
	} else {
	    this.setActionMsg("解除封禁IP失败！");
	}
	return "success";
    }

}
