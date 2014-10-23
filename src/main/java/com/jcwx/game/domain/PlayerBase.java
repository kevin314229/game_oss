package com.jcwx.game.domain;

import com.jcwx.game.common.domain.BaseDomain;

public class PlayerBase extends BaseDomain {

    /** 玩家经验 */
    private Integer experience;
    /** 金币 */
    private Integer gold;
    /** 玩家等级 */
    private Integer level;
    /** 场景ID */
    private String mapId;
    /**  */
    private Integer movex;
    /**  */
    private Integer movey;
    /** 玩家昵称 */
    private String nickName;
    /** 游戏职业 */
    private Integer occupation;
    /** 玩家属性对象 **/
    private PlayerAttribute playerAttribute;
    /**  */
    private Integer playerBaseId;
    /**  */
    private Integer playerId;
    /** 体力值 */
    private Integer power;
    /** 玩家性别,1-男，2-女 */
    private Integer sex;
    /** 银币 */
    private Integer silver;
    /** 角色状态0-正常，1-战斗，2-挂机 */
    private Integer status;

    /** 技能点 */
    private Integer techPoint;

    public PlayerBase() {
    }

    public PlayerBase(Integer playerBaseId, Integer playerId, Integer sex,
	    Integer occupation, String nickName, Integer gold, Integer silver,
	    Integer status, Integer movex, Integer movey, String mapId,
	    Integer experience, Integer level) {
	this.playerBaseId = playerBaseId;
	this.playerId = playerId;
	this.sex = sex;
	this.occupation = occupation;
	this.nickName = nickName;
	this.gold = gold;
	this.silver = silver;
	this.status = status;
	this.movex = movex;
	this.movey = movey;
	this.mapId = mapId;
	this.experience = experience;
	this.level = level;
    }

    public Integer getExperience() {
	exeGet();
	return experience;
    }

    public Integer getGold() {
	exeGet();
	return gold;
    }

    public Integer getLevel() {
	exeGet();
	return level;
    }

    public String getMapId() {
	exeGet();
	return mapId;
    }

    public Integer getMovex() {
	exeGet();
	return movex;
    }

    public Integer getMovey() {
	exeGet();
	return movey;
    }

    public String getNickName() {
	exeGet();
	return nickName;
    }

    public Integer getOccupation() {
	exeGet();
	return occupation;
    }

    public PlayerAttribute getPlayerAttribute() {
	return playerAttribute;
    }

    public Integer getPlayerBaseId() {
	exeGet();
	return playerBaseId;
    }

    public Integer getPlayerId() {
	exeGet();
	return playerId;
    }

    public Integer getPower() {
	return power;
    }

    public Integer getSex() {
	exeGet();
	return sex;
    }

    public Integer getSilver() {
	exeGet();
	return silver;
    }

    public Integer getStatus() {
	exeGet();
	return status;
    }

    public Integer getTechPoint() {
	return techPoint;
    }

    public void setExperience(Integer experience) {
	this.experience = experience;
	exeSet();
    }

    public void setGold(Integer gold) {
	this.gold = gold;
	exeSet();
    }

    public void setLevel(Integer level) {
	this.level = level;
	exeSet();
    }

    public void setMapId(String mapId) {
	this.mapId = mapId;
	exeSet();
    }

    public void setMovex(Integer movex) {
	this.movex = movex;
	exeSet();
    }

    public void setMovey(Integer movey) {
	this.movey = movey;
	exeSet();
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
	exeSet();
    }

    public void setOccupation(Integer occupation) {
	this.occupation = occupation;
	exeSet();
    }

    public void setPlayerAttribute(PlayerAttribute playerAttribute) {
	this.playerAttribute = playerAttribute;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
	exeSet();
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
	exeSet();
    }

    public void setPower(Integer power) {
	this.power = power;
    }

    public void setSex(Integer sex) {
	this.sex = sex;
	exeSet();
    }

    public void setSilver(Integer silver) {
	this.silver = silver;
	exeSet();
    }

    public void setStatus(Integer status) {
	this.status = status;
	exeSet();
    }

    public void setTechPoint(Integer techPoint) {
	this.techPoint = techPoint;
    }

}