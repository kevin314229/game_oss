package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 世界boss 排行榜
 * 
 * @author Administrator
 * 
 */
public class OssZBossRanking implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer bossRankingId;
    /** 创建时间 */
    private Date createTime;
    /** 伤害值 */
    private Integer damage;
    /** 登录名 */
    private String loginName;
    /** 角色名称 */
    private String nickName;
    /** 角色ID */
    private Integer playerBaseId;
    /** 本次排名 */
    private Integer ranking;

    public Integer getBossRankingId() {
	return bossRankingId;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Integer getDamage() {
	return damage;
    }

    public String getLoginName() {
	return loginName;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Integer getRanking() {
	return ranking;
    }

    public void setBossRankingId(Integer bossRankingId) {
	this.bossRankingId = bossRankingId;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDamage(Integer damage) {
	this.damage = damage;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setRanking(Integer ranking) {
	this.ranking = ranking;
    }

}
