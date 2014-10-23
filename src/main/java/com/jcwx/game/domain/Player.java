package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class Player implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 大区ID */
    private Integer areaId;
    /** 玩家创建时间 */
    private Date createTime;
    /** 玩家首次登录时间 */
    private Date firstLoginTime;
    /** 玩家首充登出时间 */
    private Date firstLogoutTime;
    /** 游戏ID */
    private Integer gameId;
    /** 主键 */
    private Integer gamePlayerId;
    /** 帐号 */
    private String loginName;
    /** 手机型号 */
    private String phoneType;
    /** 玩家ID */
    private Integer playerId;
    /** 平台标识 */
    private String ptId;

    public Player() {
    }

    public Player(Integer gamePlayerId, Integer gameId, String ptId,
	    Integer areaId, String loginName, Integer playerId,
	    String phoneType, Date firstLoginTime, Date firstLogoutTime,
	    Date createTime) {
	this.gamePlayerId = gamePlayerId;
	this.gameId = gameId;
	this.ptId = ptId;
	this.areaId = areaId;
	this.loginName = loginName;
	this.playerId = playerId;
	this.phoneType = phoneType;
	this.firstLoginTime = firstLoginTime;
	this.firstLogoutTime = firstLogoutTime;
	this.createTime = createTime;
    }

    public Integer getAreaId() {
	return areaId;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Date getFirstLoginTime() {
	return firstLoginTime;
    }

    public Date getFirstLogoutTime() {
	return firstLogoutTime;
    }

    public Integer getGameId() {
	return gameId;
    }

    public Integer getGamePlayerId() {
	return gamePlayerId;
    }

    public String getLoginName() {
	return loginName;
    }

    public String getPhoneType() {
	return phoneType;
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

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setFirstLoginTime(Date firstLoginTime) {
	this.firstLoginTime = firstLoginTime;
    }

    public void setFirstLogoutTime(Date firstLogoutTime) {
	this.firstLogoutTime = firstLogoutTime;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setGamePlayerId(Integer gamePlayerId) {
	this.gamePlayerId = gamePlayerId;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setPhoneType(String phoneType) {
	this.phoneType = phoneType;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

}