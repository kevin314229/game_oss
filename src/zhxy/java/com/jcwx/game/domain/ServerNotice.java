package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统维护公告
 * 
 * @author csp
 * 
 */
public class ServerNotice implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String createUserId;
    /** 主键ID */
    private Integer noticeId;
    /** 公告内容 */
    private String notiveContent;
    /** 服务器ID */
    private Integer serverId;
    /** 修改时间 */
    private Date updateTime;
    /** 最后修改人 */
    private String updateUserId;

    public ServerNotice() {
    }

    public ServerNotice(Integer noticeId, Integer serverId,
	    String notiveContent, Date createTime, Date updateTime,
	    String createUserId, String updateUserId) {
	this.noticeId = noticeId;
	this.serverId = serverId;
	this.notiveContent = notiveContent;
	this.createTime = createTime;
	this.updateTime = updateTime;
	this.createUserId = createUserId;
	this.updateUserId = updateUserId;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public String getCreateUserId() {
	return createUserId;
    }

    public Integer getNoticeId() {
	return noticeId;
    }

    public String getNotiveContent() {
	return notiveContent;
    }

    public Integer getServerId() {
	return serverId;
    }

    public Date getUpdateTime() {
	return updateTime;
    }

    public String getUpdateUserId() {
	return updateUserId;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setCreateUserId(String createUserId) {
	this.createUserId = createUserId;
    }

    public void setNoticeId(Integer noticeId) {
	this.noticeId = noticeId;
    }

    public void setNotiveContent(String notiveContent) {
	this.notiveContent = notiveContent;
    }

    public void setServerId(Integer serverId) {
	this.serverId = serverId;
    }

    public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
    }

    public void setUpdateUserId(String updateUserId) {
	this.updateUserId = updateUserId;
    }

}
