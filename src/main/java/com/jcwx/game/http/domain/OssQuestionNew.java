package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 玩家反馈记录表
 * 
 * @author Administrator 2013-12-31
 */
public class OssQuestionNew implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 玩家等级 */
    private Integer level;
    /** 帐号名 */
    private String loginName;
    /** 角色名称 */
    private String nickName;
    /** 充值金额 */
    private double payMoney;
    /** 手机型号 **/
    private String phoneType;
    /** 角色ID */
    private Integer playerBaseId;
    /** 提交问题时间 */
    private Date queDate;
    /** 问题详情 */
    private String question;
    /** 主键ID */
    private Integer questionId;
    /** 回复内容 */
    private String questionReplay;
    /** 问题状态 0 没有回复 1 已经回复 */
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

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Date getQueDate() {
	return queDate;
    }

    public String getQuestion() {
	return question;
    }

    public Integer getQuestionId() {
	return questionId;
    }

    public String getQuestionReplay() {
	return questionReplay;
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

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setQueDate(Date queDate) {
	this.queDate = queDate;
    }

    public void setQuestion(String question) {
	this.question = question;
    }

    public void setQuestionId(Integer questionId) {
	this.questionId = questionId;
    }

    public void setQuestionReplay(String questionReplay) {
	this.questionReplay = questionReplay;
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
