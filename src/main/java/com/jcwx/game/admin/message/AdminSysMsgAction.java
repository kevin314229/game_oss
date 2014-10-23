package com.jcwx.game.admin.message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.common.GameSchedule;
import com.jcwx.game.common.MessageRunnable;
import com.jcwx.game.common.SystemGlobal;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.SystemNotice;
import com.jcwx.game.service.oss.ISystemNoticeService;
import com.jcwx.game.service.oss.impl.OssLogService;

@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin/message")
@ResultPath("/")
public class AdminSysMsgAction extends BasalAction {

    private static final Logger logger = Logger
	    .getLogger(AdminSysMsgAction.class);
    /** 时间间隔 分钟(界面显示仍然按小时) */
    private Integer delay;

    /** 播放截止时间 */
    private String endDate;
    /** 播放截止时间 */
    private String startDate;
    /** 系统消息 */
    private String msgContent;
    @Autowired
    private OssLogService ossLogService;
    /** 系统公告集合 */
    private SystemNotice systemNotice;
    /** 系统消息ID */
    private Integer systemNoticeID;
    private List<SystemNotice> systemNoticeList;
    @Autowired
    private ISystemNoticeService systemNoticeService;

    /** 播报次数 */
    private Integer times;

    private Integer type;

    /**
     * 发送系统消息，由原来的由游戏定时调度改为oss自身quartz定时任务调度
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "adminSysMsg_addSysMsg", results = { @Result(name = "success", type = "redirectAction", location = "adminSysMsg_adminSysMsg") })
    public String addSysMsg() throws Exception {
	OssServer ossServer = getBaseAdminContext().getCurrentOssServer();
	resetInfos();
	if ("".equals(msgContent) || msgContent == null) {
	    setErrorInfo("消息不能为空");
	    return "success";
	}
	if (endDate == null) {
	    setErrorInfo("截止时间不能为空");
	    return "success";
	}

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	try {
	    Date endTime = formatter.parse(endDate);
	    Date startTime = formatter.parse(startDate);
	    SystemNotice systemNotice = new SystemNotice();
	    systemNotice.setContent(msgContent);
	    System.out.print(msgContent);
	    if (ossServer.getId() != null) {
		systemNotice.setServerId(ossServer.getId());
	    }
	    if (ossServer.getName() != null) {
		systemNotice.setServerName(ossServer.getName());
	    }
	    Date now = new Date(); 
	    systemNotice.setCreateTime(new Date());
	    systemNotice.setDelay(delay);
	    systemNotice.setNoticeTime(endTime);
	    systemNotice.setType(type);
	    systemNotice.setStartTime(startTime);
	    // 返回新增系统消息Id
	    systemNoticeID = systemNoticeService
		    .createSystemNotice(systemNotice);
	    systemNotice.setSystemNoticeId(systemNoticeID);
	    // 将系统消息加入延迟队列
	    MessageRunnable mr = new MessageRunnable(systemNoticeID);
	    long time=0;
	    if(startTime!=null&&(startTime.getTime()>(now.getTime()+5*60*1000))){
		time=startTime.getTime()-now.getTime();
	    }
	    GameSchedule.put(mr, time, TimeUnit.SECONDS); // 0代表立即发布，不延时
	    systemNotice.setMessageRunnable(mr);
	    // 将数据加入缓存中
	    Map<Integer, SystemNotice> map = SystemGlobal.get(
		    SystemGlobal.SYSTEM_NOTICES_MAP, Map.class);
	    map.put(systemNoticeID, systemNotice);
	    setSuccessInfo("系统消息发布成功！");
	    ossLogService.createOssLog(OssLogConstant.ADMIN_SEND_MSG,
		    msgContent);
	} catch (Exception e) {
	    setErrorInfo("系统消息发布失败！");
	    ossLogService.createOssLog(OssLogConstant.ERROR_SERVER,
		    e.getMessage());
	    logger.error(e.getMessage(), e);
	}
	msgContent = "";
	return "success";
    }

    /** 删除系统消息 */
    @Action(value = "adminSysMsg_delSystemNotice", results = { @Result(name = "toAdminSysMsg", type = "redirectAction", params = {
	    "actionName", "adminSysMsg_adminSysMsg", "namespace",
	    "/admin/message" }) })
    public String delSystemNotice() {

	Map<Integer, SystemNotice> snMap = SystemGlobal.get(
		SystemGlobal.SYSTEM_NOTICES_MAP, Map.class);
	SystemNotice systemNotice = snMap.get(systemNoticeID);
	if (systemNotice != null && systemNotice.getMessageRunnable() != null) {
	    // 删除延时队列中的该条记录
	    GameSchedule.remove(systemNotice.getMessageRunnable());
	}
	// 从系统消息缓存中清除该条记录
	snMap.remove(systemNoticeID);
	// 删除数据库中该条系统消息记录
	int num = systemNoticeService.deleteSystemNoticeByID(systemNoticeID);

	getPageMessage().setNavTabId("w_系统消息管理");
	if (num == 1) {
	    getPageMessage().setMessage("数据库消息删除成功！systemNoticeID为：" + systemNoticeID);
	    getJSONResponse().responseJson(getPageMessage());
	    return null;

	    // setSuccessInfo("数据库消息删除成功！systemNoticeID为：" + systemNoticeID);
	} else {
//	    return getPageMessage().ajaxForwardError(
//		    "数据库消息删除失败！systemNoticeID为：" + systemNoticeID);
	    getPageMessage().setMessage("数据库消息删除失败！systemNoticeID为：" + systemNoticeID); 
	    getJSONResponse().responseJson(getPageMessage());
	    return null;
	    // setErrorInfo("数据库消息删除失败！systemNoticeID为：" + systemNoticeID);
	}
	// return "toAdminSysMsg";

    }

    /**
     * 新增消息以弹出方式新增
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "adminSysMsg_toAddSysMsg", results = { @Result(name = "success", location = "../../admin/message/doAddSysMsg.jsp") })
    public String doAddSysMsg() throws Exception {
	return "success";
    }

    /** 修改系统消息页面 */
    @Action(value = "adminSysMsg_editSystemNoticeId", results = { @Result(name = "success", location = "../../admin/message/editAdminSysMsg.jsp") })
    public String editSysMsg() throws Exception {
	systemNotice = systemNoticeService.getSystemNoticeByID(systemNoticeID);
	return "success";
    }

    public Integer getDelay() {
	return delay;
    }

    public String getEndDate() {
	return endDate;
    }

    public String getMsgContent() {
	return msgContent;
    }

    public SystemNotice getSystemNotice() {
	return systemNotice;
    }

    public Integer getSystemNoticeID() {
	return systemNoticeID;
    }

    public List<SystemNotice> getSystemNoticeList() {
	return systemNoticeList;
    }

    public Integer getTimes() {
	return times;
    }

    public Integer getType() {
	return type;
    }

    @Action(value = "adminSysMsg_adminSysMsg", results = {
	    @Result(name = "success", location = "../../admin/message/adminSysMsg.jsp"),
	    // @Result(name = "add", location =
	    // "../../admin/message/addSysMsg.jsp"),
	    @Result(name = "error", type = "redirectAction", location = "logout.action") })
    public String query() throws Exception {
	if (getBaseAdminContext() == null
		|| StringUtils.isBlank(String.valueOf(getBaseAdminContext()
			.getServerId()))) {
	    return "error"; // 退出
	}
	// 根据服务器Id查询系统公告
	systemNoticeList = systemNoticeService
		.getSystemNoticeListByServerId(getBaseAdminContext()
			.getServerId());
	return "success";
    }

    public void setDelay(Integer delay) {
	this.delay = delay;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public void setMsgContent(String msgContent) {
	this.msgContent = msgContent;
    }

    public void setSystemNotice(SystemNotice systemNotice) {
	this.systemNotice = systemNotice;
    }

    public void setSystemNoticeID(Integer systemNoticeID) {
	this.systemNoticeID = systemNoticeID;
    }

    public void setSystemNoticeList(List<SystemNotice> systemNoticeList) {
	this.systemNoticeList = systemNoticeList;
    }

    public void setTimes(Integer times) {
	this.times = times;
    }

    public void setType(Integer type) {
	this.type = type;
    }

    /** 修改系统消息 */
    @Action(value = "adminSysMsg_updateSysMsg", results = { @Result(name = "toAdminSysMsg", type = "redirectAction", params = {
	    "actionName", "adminSysMsg_adminSysMsg", "namespace",
	    "/admin/message" }) })
    public String updateSysMsg() throws Exception {

	OssServer ossServer = getBaseAdminContext().getCurrentOssServer();
	resetInfos();
	if ("".equals(msgContent) || msgContent == null) {
	    setErrorInfo("消息不能为空");
	    return "success";
	}
	if (endDate == null) {
	    setErrorInfo("截止时间不能为空");
	    return "success";
	}

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	try {
	    Date endTime = formatter.parse(endDate);
	    Date startTime = formatter.parse(startDate);
	    SystemNotice systemNotice = new SystemNotice();
	    systemNotice.setContent(msgContent);
	    if (ossServer.getId() != null) {
		systemNotice.setServerId(ossServer.getId());
	    }
	    if (ossServer.getName() != null) {
		systemNotice.setServerName(ossServer.getName());
	    }
	    Date now = new Date();
	    systemNotice.setCreateTime(now);
	    systemNotice.setDelay(delay);
	    systemNotice.setNoticeTime(endTime);
	    systemNotice.setType(type);
	    systemNotice.setSystemNoticeId(systemNoticeID);
	    
	    systemNotice.setStartTime(startTime);
	    long time=0;
	    
	    if(startTime!=null&&(startTime.getTime()>(now.getTime()+5*60*1000))){
		time=startTime.getTime()-now.getTime();
	    }
	    systemNoticeService.updateSystemNotice(systemNotice);
	    Map<Integer, SystemNotice> map = SystemGlobal.get(
		    SystemGlobal.SYSTEM_NOTICES_MAP, Map.class);
	    // 删除延时队列中的该条记录
	    SystemNotice oldsystemNotice = map.get(systemNoticeID);
	    if (oldsystemNotice != null
		    && oldsystemNotice.getMessageRunnable() != null) {
		GameSchedule.remove(oldsystemNotice.getMessageRunnable());
	    }
	    // 更新缓存中记录信息
	    map.put(systemNoticeID, systemNotice);

	    // 将系统消息加入延迟队列
	    MessageRunnable mr = new MessageRunnable(systemNoticeID);
	    GameSchedule.put(mr, time, TimeUnit.SECONDS); // 0代表立即发布，不延时
	    systemNotice.setMessageRunnable(mr);

	    setSuccessInfo("系统消息更新成功！");
	} catch (Exception e) {
	    setErrorInfo("系统消息更新失败！");
	    e.printStackTrace();
	}
	msgContent = "";
	return "toAdminSysMsg";
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

}
