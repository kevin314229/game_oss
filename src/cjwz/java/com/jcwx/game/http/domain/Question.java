package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

public class Question implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -6066745166907963390L;
    /** 玩家等级 */
    private Integer level;
    /** 帐号名 */
    private String loginName;
    /** 角色名称 */
    private String nickName;
    /** 充值金额 */
    private double payMoney;
    /** 手机型号 */
    private String phoneType;
    /** 角色ID */
    private Long playerId;
    /** 问题详情 */
    private String question;
    /** 提交问题时间 */
    private Date questionDate;
    /** 主键ID */
    private Integer questionId;
    /** 回复内容 */
    private String questionReply;
    /** 问题状态 0 没有回复 1 已经回复 2 关闭问题 */
    private Integer questionStatus;
    /** 问题类型 1 bug 2 投诉 3 建议 4其它 */
    private Integer questionType;
    /** 回复时间 */
    private Date replyDate;
    /** 回复人 */
    private String replyUserName;
    /** 平台名称 */
    private String serverCode;

    /** 服务器ID */
    private Integer serverId;
    /** 服务器名称 */
    private String serverName;
    /** vip等级 */
    private Integer vipLevel;

    public Question() {
    }

    public Question(Integer questionId, String loginName, Long playerId,
	    String nickName, Integer level, Integer vipLevel, String phoneType,
	    double payMoney, String question, Date questionDate,
	    Integer questionType, Integer questionStatus, String questionReply,
	    String replyUserName, Date replyDate) {
	this.questionId = questionId;
	this.loginName = loginName;
	this.playerId = playerId;
	this.nickName = nickName;
	this.level = level;
	this.vipLevel = vipLevel;
	this.phoneType = phoneType;
	this.payMoney = payMoney;
	this.question = question;
	this.questionDate = questionDate;
	this.questionType = questionType;
	this.questionStatus = questionStatus;
	this.questionReply = questionReply;
	this.replyUserName = replyUserName;
	this.replyDate = replyDate;
    }

    public Integer getLevel() {
	return level;
    }

    public String getLoginName() {
	return loginName;
    }

    public String getNickName() {
	return nickName;
    }

    public double getPayMoney() {
	return payMoney;
    }

    public String getPhoneType() {
	return phoneType;
    }

    public Long getPlayerId() {
	return playerId;
    }

    public String getQuestion() {
	return question;
    }

    public Date getQuestionDate() {
	return questionDate;
    }

    public Integer getQuestionId() {
	return questionId;
    }

    public String getQuestionReply() {
	return questionReply;
    }

    public Integer getQuestionStatus() {
	return questionStatus;
    }

    public Integer getQuestionType() {
	return questionType;
    }

    public Date getReplyDate() {
	return replyDate;
    }

    public String getReplyUserName() {
	return replyUserName;
    }

    public String getServerCode() {
	return serverCode;
    }

    public Integer getServerId() {
	return serverId;
    }

    public String getServerName() {
	return serverName;
    }

    public Integer getVipLevel() {
	return vipLevel;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setPayMoney(double payMoney) {
	this.payMoney = payMoney;
    }

    public void setPhoneType(String phoneType) {
	this.phoneType = phoneType;
    }

    public void setPlayerId(Long playerId) {
	this.playerId = playerId;
    }

    public void setQuestion(String question) {
	this.question = question;
    }

    public void setQuestionDate(Date questionDate) {
	this.questionDate = questionDate;
    }

    public void setQuestionId(Integer questionId) {
	this.questionId = questionId;
    }

    public void setQuestionReply(String questionReply) {
	this.questionReply = questionReply;
    }

    public void setQuestionStatus(Integer questionStatus) {
	this.questionStatus = questionStatus;
    }

    public void setQuestionType(Integer questionType) {
	this.questionType = questionType;
    }

    public void setReplyDate(Date replyDate) {
	this.replyDate = replyDate;
    }

    public void setReplyUserName(String replyUserName) {
	this.replyUserName = replyUserName;
    }

    public void setServerCode(String serverCode) {
	this.serverCode = serverCode;
    }

    public void setServerId(Integer serverId) {
	this.serverId = serverId;
    }

    public void setServerName(String serverName) {
	this.serverName = serverName;
    }

    public void setVipLevel(Integer vipLevel) {
	this.vipLevel = vipLevel;
    }

}
