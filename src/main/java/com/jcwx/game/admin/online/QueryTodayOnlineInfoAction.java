package com.jcwx.game.admin.online;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.DateService;
import com.jcwx.game.domain.DataHistory;
import com.jcwx.game.domain.LoginLog;
import com.jcwx.game.service.oss.ILoginLogService;


/**
 * 在线与注册：查看今天在线
 * */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin/online")
@ResultPath("/")
@Action(value = "queryTodayOnlineInfo", results = { @Result(name = "success", location = "../../admin/online/queryTodayOnlineInfo.jsp") })
public class QueryTodayOnlineInfoAction extends BasalAction {

    /** 结果集 */
    private List<DataHistory> dataHistoryList;

    /** 最后登录玩家ID */
    private Integer lastLoginPlayerID;

    /** 最后登录玩家名字 */
    private String lastLoginPlayerName;

    /** 最后登录玩家登录时间 */
    private Date lastLoginTime;

    /** 最后登录玩家帐号 */
    private String lastLoginUsername;

    @Autowired
    private ILoginLogService loginLogService;

    /** 系统时间 */
    private Date systemTime;

    @Override
    @SuppressWarnings("unchecked")
    public String execute() throws Exception {
	// IPlayerService playerService =
	// (IPlayerService)SpringService.getBean("playerService");
	// PlayerCache playerCache =
	// playerService.getPlayerCacheByPlayerID(loginLog.getPlayerID());
	// lastLoginPlayerName = playerCache.getName();
	// lastLoginUsername = playerCache.getUserName();
	systemTime = DateService.getCurrentUtilDate();
	String currDateStr = DateService
		.getCurrentDateAsStringCustom("yyyyMMddHHmm");
	Long begin = Long.parseLong(currDateStr.substring(0, 8) + "0000");
	Long end = Long.parseLong(currDateStr.substring(0, 8) + "2359");
	// IDataHistoryService dataHistoryService =
	// (IDataHistoryService)SpringService.getBean("dataHistoryService");
	// dataHistoryList = dataHistoryService.getDataHistoryListByTime(0,
	// begin, end);//修改

	Map<String, Object> object = new HashMap<String, Object>();
	object.put("systemTime", systemTime);
	object.put("begin", begin);
	object.put("end", end);
	object.put("handlerName", "QueryTodayOnlineInfoHandler");
	object = CONNECTION.sendMsg(object);
	dataHistoryList = (List<DataHistory>) object.get("dataHistoryList");
	LoginLog loginLog = (LoginLog) object.get("loginLog");
	lastLoginPlayerID = loginLog.getPlayerID();
	lastLoginPlayerName = loginLog.getNickName();
	lastLoginTime = loginLog.getLoginTime();
	lastLoginUsername = loginLog.getNickName();
	return "success";
    }

    public List<DataHistory> getDataHistoryList() {
	return dataHistoryList;
    }

    public Integer getLastLoginPlayerID() {
	return lastLoginPlayerID;
    }

    public String getLastLoginPlayerName() {
	return lastLoginPlayerName;
    }

    public Date getLastLoginTime() {
	return lastLoginTime;
    }

    public String getLastLoginUsername() {
	return lastLoginUsername;
    }

    public Date getSystemTime() {
	return systemTime;
    }

    public void setLastLoginPlayerID(Integer lastLoginPlayerID) {
	this.lastLoginPlayerID = lastLoginPlayerID;
    }

    public void setLastLoginPlayerName(String lastLoginPlayerName) {
	this.lastLoginPlayerName = lastLoginPlayerName;
    }

    public void setLastLoginTime(Date lastLoginTime) {
	this.lastLoginTime = lastLoginTime;
    }

    public void setLastLoginUsername(String lastLoginUsername) {
	this.lastLoginUsername = lastLoginUsername;
    }

    public void setSystemTime(Date systemTime) {
	this.systemTime = systemTime;
    }

}
