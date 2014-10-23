package com.jcwx.game.domain;

import java.util.Date;

import com.jcwx.game.common.domain.BaseDomain;

public class Answer extends BaseDomain {

    /**  */
    private Integer answerID;
    /** 意见1*1 */
    private Comment comment;
    /** 外键，会员问题表 */
    private Integer commentID;
    /** 回复内容 */
    private String content;
    /** 创建日期 */
    private Date createDate;

    /** 外键，管理员帐号 */
    private String userID;

    public Answer() {
    }

    public Answer(Integer answerId, Integer commentId, String userId,
	    String content, Date createDate) {
	this.answerID = answerId;
	this.commentID = commentId;
	this.userID = userId;
	this.content = content;
	this.createDate = createDate;
    }

    public Integer getAnswerID() {
	exeGet();
	return answerID;
    }

    public Comment getComment() {
	return comment;
    }

    public Integer getCommentID() {
	exeGet();
	return commentID;
    }

    public String getContent() {
	exeGet();
	return content;
    }

    public Date getCreateDate() {
	exeGet();
	return createDate;
    }

    public String getUserID() {
	exeGet();
	return userID;
    }

    public void setAnswerID(Integer answerId) {
	this.answerID = answerId;
	exeSet();
    }

    public void setComment(Comment comment) {
	this.comment = comment;
    }

    public void setCommentID(Integer commentId) {
	this.commentID = commentId;
	exeSet();
    }

    public void setContent(String content) {
	this.content = content;
	exeSet();
    }

    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
	exeSet();
    }

    public void setUserID(String userId) {
	this.userID = userId;
	exeSet();
    }

}