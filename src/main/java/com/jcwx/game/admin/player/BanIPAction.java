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
import com.jcwx.game.util.Page;


/** 封禁IP */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class BanIPAction extends BasalAction {

    private static final long serialVersionUID = 1L;


    /** 封禁到期时间 */
    private Date banEndTime;

    /** 被封状态 */
    private Integer banState = -1;

    /** 黑名单列表 */
    private List<BlackList> blackListList;

    /** 封禁到期时间 */
    private String endDate;

    /** 搜索关键字 */
    private String keyword;

    /** 排序标记 */
    private String orderFlag;

    /** IP */
    private String playerIP;

    private String temp;
    
    private Page page = Page.createDefaultPage();
    

    // @Autowired
    // private IAuthorizationLimitService authorizationLimitService;

    /** 添加封禁 */
    @Action(value = "banIP_addBanIP", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "banIP_browseBanIP", "namespace",
		    "/admin/base", "actionMsg", "${actionMsg}" }),
	    @Result(name = "input", location = "/admin/player/addBanIP.jsp")

    })
    public String addBanIP() {

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
    @Action(value = "banIP_banIP", results = { @Result(name = "banState", type = "redirectAction", params = {
	    "actionName", "banIP_browseBanIP", "namespace", "/admin/base",
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
    @Action(value = "banIP_browseBanIP", results = { @Result(name = "success", location = "../../admin/player/browseBanIP.jsp") })
    public String browseBanIP() throws Exception {

	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    addActionMessage(getActionMsg());
	}
	// 排序
	if (orderFlag == null || "".equals(orderFlag)) {
	    orderFlag = "DESC";
	}

	if (keyword != null)
	    keyword = keyword.trim();

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("keyword", keyword);
	if (banState == -1) {
	    banState = null;
	}
	object.put("banState", banState);
	object.put("orderFlag", orderFlag);
	object.put("beginNum", page.getBeginNum());
	object.put("onePageNum", page.getOnePageNum());
	object.put("handlerName", "BanIPHandler");
	try {
	    object = CONNECTION.sendMsg(object);
	    
	    blackListList = (List<BlackList>) object.get("blackListList");
	    
	    page.setAllNum((Integer) object.get("allNum"));
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;

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


    public String getEndDate() {
	return endDate;
    }

    public String getKeyword() {
	return keyword;
    }


    public String getOrderFlag() {
	return orderFlag;
    }


    public String getPlayerIP() {
	return playerIP;
    }

    public String getTemp() {
	return temp;
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


    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setKeyword(String keyword) {
	this.keyword = keyword;
    }


    public void setOrderFlag(String orderFlag) {
	this.orderFlag = orderFlag;
    }


    public void setPlayerIP(String playerIP) {
	this.playerIP = playerIP;
    }

    public void setTemp(String temp) {
	this.temp = temp;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    /** 添加封禁 */
    @Action(value = "banIP_toAddBanIP", results = { @Result(name = "success", location = "../../admin/player/addBanIP.jsp") })
    public String toAddBanIP() {
	return SUCCESS;
    }

    /**
     * 解除封禁
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "banIP_UnbanIP", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "banIP_browseBanIP", "namespace", "/admin/base",
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
