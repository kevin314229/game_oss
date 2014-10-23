package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 玩家聊天查询
 * 
 * @author csp
 * 
 */
public class PlayerChatLog implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 游戏Id */
    private Integer areaId;
    /** 发言时间 */
    private Date chatDate;
    /** 聊天内容 */
    private String content;
    /** 游戏ID */
    private Integer gameId;
    /** 发言玩家登陆账号 */
    private String loginName;
    /** 发言玩家playerBaseID */
    private Integer playerBaseId;
    /** 主键ID */
    private Integer playerChatLogId;
    /** 发言玩家ID */
    private Integer playerId;
    /** 平台ID */
    private Integer ptId;
    /** 私聊玩家昵称 */
    private String recipient;
    /** 聊天玩家playerBaseID */
    private Integer recipientId;
    /** 发言玩家昵称 */
    private String sender;
    /** 状态 0正常7等级不足在频道说话8被禁言9说话CD */
    private Integer status;
    /** 聊天频道 1私聊 2队伍3场景4门派5世界6广播 */
    private Integer type;

    public PlayerChatLog() {
    }

    public PlayerChatLog(Integer playerChatLogId, Integer gameId, Integer ptId,
	    Integer areaId, String loginName, Integer playerId, String sender,
	    Integer playerBaseId, String content, Integer recipientId,
	    String recipient, Integer type, Integer status, Date chatDate) {
	this.playerChatLogId = playerChatLogId;
	this.gameId = gameId;
	this.ptId = ptId;
	this.areaId = areaId;
	this.loginName = loginName;
	this.playerId = playerId;
	this.sender = sender;
	this.playerBaseId = playerBaseId;
	this.content = content;
	this.recipientId = recipientId;
	this.recipient = recipient;
	this.type = type;
	this.status = status;
	this.chatDate = chatDate;
    }

    public Integer getAreaId() {
	return areaId;
    }

    public Date getChatDate() {
	return chatDate;
    }

    public String getContent() {
	return content;
    }

    public Integer getGameId() {
	return gameId;
    }

    public String getLoginName() {
	return loginName;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Integer getPlayerChatLogId() {
	return playerChatLogId;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public Integer getPtId() {
	return ptId;
    }

    public String getRecipient() {
	return recipient;
    }

    public Integer getRecipientId() {
	return recipientId;
    }

    public String getSender() {
	return sender;
    }

    public Integer getStatus() {
	return status;
    }

    public Integer getType() {
	return type;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setChatDate(Date chatDate) {
	this.chatDate = chatDate;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPlayerChatLogId(Integer playerChatLogId) {
	this.playerChatLogId = playerChatLogId;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setPtId(Integer ptId) {
	this.ptId = ptId;
    }

    public void setRecipient(String recipient) {
	this.recipient = recipient;
    }

    public void setRecipientId(Integer recipientId) {
	this.recipientId = recipientId;
    }

    public void setSender(String sender) {
	this.sender = sender;
    }

    public void setStatus(Integer status) {
	this.status = status;
    }

    public void setType(Integer type) {
	this.type = type;
    }

}
