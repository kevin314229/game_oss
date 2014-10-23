package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class ALoginLog implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 大区ID */
    private Integer areaId;
    /** 游戏ID */
    private Integer gameId;
    /** IP地址 */
    private String ip;
    /** IP地区 */
    private String ipAddr;
    /** 玩家等级 */
    private Integer level;
    /** 登录日志ID */
    private Integer loginLogId;
    /** 帐号 */
    private String loginName;
    /** 登出时间 */
    private Date loginOutTime;
    /** 登录时间 */
    private Date loginTime;
    /** 角色昵称 */
    private String nickName;
    /** 这次在线时间(秒)，在退出时计算 */
    private Integer onlineTime;
    /** 角色ID */
    private Integer playerBaseId;
    /** 玩家Id--t_player 主键 */
    private Integer playerId;
    /** 平台标识 */
    private String ptId;

    public ALoginLog() {
    }

    public ALoginLog(Integer loginLogId, Integer gameId, String ptId,
	    Integer areaId, Integer playerId, String loginName, Integer level,
	    Integer playerBaseId, String nickName, String ip, String ipAddr,
	    Date loginTime, Date loginOutTime, Integer onlineTime) {
	this.loginLogId = loginLogId;
	this.gameId = gameId;
	this.ptId = ptId;
	this.areaId = areaId;
	this.playerId = playerId;
	this.loginName = loginName;
	this.level = level;
	this.playerBaseId = playerBaseId;
	this.nickName = nickName;
	this.ip = ip;
	this.ipAddr = ipAddr;
	this.loginTime = loginTime;
	this.loginOutTime = loginOutTime;
	this.onlineTime = onlineTime;
    }

    public Integer getAreaId() {
	return areaId;
    }

    public Integer getGameId() {
	return gameId;
    }

    public String getIp() {
	return ip;
    }

    public String getIpAddr() {
	return ipAddr;
    }

    public Integer getLevel() {
	return level;
    }

    public Integer getLoginLogId() {
	return loginLogId;
    }

    public String getLoginName() {
	return loginName;
    }

    public Date getLoginOutTime() {
	return loginOutTime;
    }

    public Date getLoginTime() {
	return loginTime;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getOnlineTime() {
	return onlineTime;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public String getPtId() {
	return ptId;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setIp(String ip) {
	this.ip = ip;
    }

    public void setIpAddr(String ipAddr) {
	this.ipAddr = ipAddr;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setLoginLogId(Integer loginLogId) {
	this.loginLogId = loginLogId;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setLoginOutTime(Date loginOutTime) {
	this.loginOutTime = loginOutTime;
    }

    public void setLoginTime(Date loginTime) {
	this.loginTime = loginTime;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOnlineTime(Integer onlineTime) {
	this.onlineTime = onlineTime;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

}