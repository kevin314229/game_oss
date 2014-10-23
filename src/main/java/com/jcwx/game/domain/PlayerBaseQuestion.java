package com.jcwx.game.domain;

import java.util.Date;

import com.jcwx.game.common.domain.BaseDomain;

public class PlayerBaseQuestion extends BaseDomain {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 提问时间 */
    private Date createTime;
    /** 主键 */
    private Integer id;
    /**
     * 角色名称
     * */
    private String nickName;

    /** 玩家id */
    private Integer playerBaseId;
    /** 问题描述 */
    private String questionComment;
    /** 问题回复 */
    private String questionReplay;
    /** 问题状态 0 没有回复 1 已经回复 */
    private Integer questionStatus;
    /** 问题类型 1 bug 2 投诉 3 建议 4其它 */
    private Integer questionType;

    public PlayerBaseQuestion() {
    }

    public PlayerBaseQuestion(Integer id, Integer playerBaseId,
	    Integer questionType, String questionComment,
	    String questionReplay, Integer questionStatus, Date createTime) {
	this.id = id;
	this.playerBaseId = playerBaseId;
	this.questionType = questionType;
	this.questionComment = questionComment;
	this.questionReplay = questionReplay;
	this.questionStatus = questionStatus;
	this.createTime = createTime;
    }

    public Date getCreateTime() {
	exeGet();
	return createTime;
    }

    public Integer getId() {
	exeGet();
	return id;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getPlayerBaseId() {
	exeGet();
	return playerBaseId;
    }

    public String getQuestionComment() {
	exeGet();
	return questionComment;
    }

    public String getQuestionReplay() {
	exeGet();
	return questionReplay;
    }

    public Integer getQuestionStatus() {
	exeGet();
	return questionStatus;
    }

    public Integer getQuestionType() {
	exeGet();
	return questionType;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
	exeSet();
    }

    public void setId(Integer id) {
	this.id = id;
	exeSet();
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
	exeSet();
    }

    public void setQuestionComment(String questionComment) {
	this.questionComment = questionComment;
	exeSet();
    }

    public void setQuestionReplay(String questionReplay) {
	this.questionReplay = questionReplay;
	exeSet();
    }

    public void setQuestionStatus(Integer questionStatus) {
	this.questionStatus = questionStatus;
	exeSet();
    }

    public void setQuestionType(Integer questionType) {
	this.questionType = questionType;
	exeSet();
    }

}