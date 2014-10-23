package com.jcwx.game.domain;

import java.util.Date;

import com.jcwx.game.common.MessageRunnable;
import com.jcwx.game.common.domain.BaseDomain;

public class SystemNotice extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /** 公告内容 */
    private String content;
    /** 创建时间 */
    private Date createTime;
    /** 播放间隔 */
    private Integer delay;
    private MessageRunnable messageRunnable;
    // /** 播报次数 */
    // private Integer times;
    /** 上次截止播报时间 */
    private Date noticeTime;
    /** 服务器Id */
    private Integer serverId;
    /** 服务器名称 */
    private String serverName;
    /**  */
    private Integer systemNoticeId;

    private Date  startTime;
    /** 公告类型,1--服务器公告 2-- 系统公告 */
    private Integer type;

    public SystemNotice() {
    }

    public SystemNotice(Integer systemNoticeId, Integer serverId,
	    String serverName, Integer type, String content, Date createTime,
	    Integer delay, Date noticeTime) {
	this.systemNoticeId = systemNoticeId;
	this.serverId = serverId;
	this.serverName = serverName;
	this.type = type;
	this.content = content;
	this.createTime = createTime;
	this.delay = delay;
	this.noticeTime = noticeTime;
    }

    public String getContent() {
	exeGet();
	return content;
    }

    public Date getCreateTime() {
	exeGet();
	return createTime;
    }

    public Integer getDelay() {
	exeGet();
	return delay;
    }

    public MessageRunnable getMessageRunnable() {
	return messageRunnable;
    }

    public Date getNoticeTime() {
	exeGet();
	return noticeTime;
    }

    public Integer getServerId() {
	exeGet();
	return serverId;
    }

    public String getServerName() {
	exeGet();
	return serverName;
    }

    public Integer getSystemNoticeId() {
	exeGet();
	return systemNoticeId;
    }

    public Integer getType() {
	return type;
    }

    public void setContent(String content) {
	this.content = content;
	exeSet();
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
	exeSet();
    }

    public void setDelay(Integer delay) {
	this.delay = delay;
	exeSet();
    }

    public void setMessageRunnable(MessageRunnable messageRunnable) {
	this.messageRunnable = messageRunnable;
    }

    public void setNoticeTime(Date noticeTime) {
	this.noticeTime = noticeTime;
	exeSet();
    }

    public void setServerId(Integer serverId) {
	this.serverId = serverId;
	exeGet();
    }

    public void setServerName(String serverName) {
	this.serverName = serverName;
	exeGet();
    }

    public void setSystemNoticeId(Integer systemNoticeId) {
	this.systemNoticeId = systemNoticeId;
	exeSet();
    }

    public void setType(Integer type) {
	this.type = type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

}