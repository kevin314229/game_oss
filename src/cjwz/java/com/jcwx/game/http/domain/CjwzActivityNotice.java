package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

public class CjwzActivityNotice implements Serializable {
    private static final long serialVersionUID = 1L;
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
    /** url */
    private String url;

    public CjwzActivityNotice() {
    }

    public CjwzActivityNotice(Integer activityNoticeId, Date startTime,
	    Date endTime, String content, String url, Date createTime,
	    Date modifyTime) {
	this.activityNoticeId = activityNoticeId;
	this.startTime = startTime;
	this.endTime = endTime;
	this.content = content;
	this.url = url;
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

    public String getUrl() {
	return url;
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

    public void setUrl(String url) {
	this.url = url;
    }

    @Override
    public String toString() {
	return "ActivityNotice:[activityNoticeId=" + activityNoticeId
		+ ", startTime=" + startTime + ", " + ", endTime=" + endTime
		+ ", " + ", content=" + content + ", url=" + url
		+ ", createTime=" + createTime + "]";
    }
}
