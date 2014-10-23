package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统公告表
 * 
 * @author Administrator
 * 
 */

public class OssActivityNotice implements Serializable {

    private static final long serialVersionUID = 1000L;

    /** 活动公告ID */
    private Integer activityNoticeId;
    /** 活动内容 */
    private String content;
    /** 创建时间 */
    private Date createTime;
    /** 结束时间 */
    private Date endTime;
    /** 最后修改时间 */
    private Date modifyTime;
    /** 开始时间 */
    private Date startTime;
    /**通知栏位类型：0-公告栏，1-更新栏**/
    private int noticeType;
    public OssActivityNotice() {
    }

    public OssActivityNotice(Integer activityNoticeId, Date startTime,
	    Date endTime, String content, Date createTime, Date modifyTime) {
	this.activityNoticeId = activityNoticeId;
	this.startTime = startTime;
	this.endTime = endTime;
	this.content = content;
	this.createTime = createTime;
	this.modifyTime = modifyTime;
    }

    public Integer getActivityNoticeId() {
	return activityNoticeId;
    }

    public String getContent() {
	return content;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Date getEndTime() {
	return endTime;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public Date getStartTime() {
	return startTime;
    }

    public void setActivityNoticeId(Integer activityNoticeId) {
	this.activityNoticeId = activityNoticeId;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

    public void setStartTime(Date startTime) {
	this.startTime = startTime;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }
}
