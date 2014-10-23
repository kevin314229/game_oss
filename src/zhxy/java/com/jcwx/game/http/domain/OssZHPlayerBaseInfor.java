package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 玩家角色详情
 * 
 * @author csp
 * 
 */
public class OssZHPlayerBaseInfor implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 符文精华数量 **/
    private Integer amulet;
    /** 符文格子数，默认为8 */
    private Integer amuletNumber;
    /**
     * 军团ID
     */
    private Integer armyId;
    /**
     * 军团名称
     */
    private String armyName;

    /** 背包格子时间 **/
    private Date backPackTime;
    /** 仓库格子时间 **/
    private Date backWareTime;
    /** 当前魔法 **/
    // private Integer mp;
    /** 背包格子 **/
    private Integer bagNumber;
    /** 绑定金币 */
    private Integer bindGold;
    /** 角色创建时间 **/
    private Date createTime;
    /** 玩家经验 */
    private Long experience;
    private Integer gold;
    /****/
    private byte[] guide;
    /** 当前血量 **/
    private Integer hp;
    /** 玩家等级 */
    private Integer level;
    /** 登录时间 */
    private Date loginTime;
    /** 最后登出时间 */
    private Date logoutTime;
    /**
     * 昨天遗失经验
     */
    private int loseExp;
    /** 玩家昵称 */
    private String nickName;
    /** 游戏职业 */
    private Integer occupation;

    /** 玩家离线标识0-在线，1-离线但是数据还在内存中 **/
    private int offline = -1;

    /** 领奖时间 **/
    private Date onlineTime;

    /**
     * 军团信息oss
     */
    private PlayerArmy playerArmy;

    /** 角色ID */
    private Integer playerBaseId;

    /** 玩家ID */
    private Integer playerId;
    /** 体力值 */
    private Integer power;
    /** 许愿时间 */
    private Date prayTime;
    /** 银币 */
    private Integer silver;
    /** 星石数量 */
    private Integer starStone;
    /** 洗练石 */
    private Integer stone;
    /** 服务器版本号 **/
    private String version;
    /** 是否是 vip */
    private Integer vip;

    /** vip 等级 */
    private Integer vipLevel;
    /** vip 积分 */
    private Integer vipScore;
    /** 仓库格子 **/
    private Integer wareNumber;
    private Integer status;

    public Integer getAmulet() {
	return amulet;
    }

    public Integer getAmuletNumber() {
	return amuletNumber;
    }

    public Integer getArmyId() {
	return armyId;
    }

    public String getArmyName() {
	return armyName;
    }

    public Date getBackPackTime() {
	return backPackTime;
    }

    public Date getBackWareTime() {
	return backWareTime;
    }

    public Integer getBagNumber() {
	return bagNumber;
    }

    public Integer getBindGold() {
	return bindGold;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Long getExperience() {
	return experience;
    }

    public Integer getGold() {
	return gold;
    }

    public byte[] getGuide() {
	return guide;
    }

    public Integer getHp() {
	return hp;
    }

    public Integer getLevel() {
	return level;
    }

    public Date getLoginTime() {
	return loginTime;
    }

    public Date getLogoutTime() {
	return logoutTime;
    }

    public int getLoseExp() {
	return loseExp;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getOccupation() {
	return occupation;
    }

    // public String getGameCopyId() {
    // return gameCopyId;
    // }
    // public void setGameCopyId(String gameCopyId) {
    // this.gameCopyId = gameCopyId;
    // }
    public int getOffline() {
	return offline;
    }

    public Date getOnlineTime() {
	return onlineTime;
    }

    public PlayerArmy getPlayerArmy() {
	return playerArmy;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public Integer getPower() {
	return power;
    }

    public Date getPrayTime() {
	return prayTime;
    }

    public Integer getSilver() {
	return silver;
    }

    public Integer getStarStone() {
	return starStone;
    }

    public Integer getStone() {
	return stone;
    }

    public String getVersion() {
	return version;
    }

    public Integer getVip() {
	return vip;
    }

    public Integer getVipLevel() {
	return vipLevel;
    }

    public Integer getVipScore() {
	return vipScore;
    }

    public Integer getWareNumber() {
	return wareNumber;
    }

    public void setAmulet(Integer amulet) {
	this.amulet = amulet;
    }

    public void setAmuletNumber(Integer amuletNumber) {
	this.amuletNumber = amuletNumber;
    }

    public void setArmyId(Integer armyId) {
	this.armyId = armyId;
    }

    public void setArmyName(String armyName) {
	this.armyName = armyName;
    }

    public void setBackPackTime(Date backPackTime) {
	this.backPackTime = backPackTime;
    }

    public void setBackWareTime(Date backWareTime) {
	this.backWareTime = backWareTime;
    }

    public void setBagNumber(Integer bagNumber) {
	this.bagNumber = bagNumber;
    }

    public void setBindGold(Integer bindGold) {
	this.bindGold = bindGold;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setExperience(Long experience) {
	this.experience = experience;
    }

    public void setGold(Integer gold) {
	this.gold = gold;
    }

    public void setGuide(byte[] guide) {
	this.guide = guide;
    }

    public void setHp(Integer hp) {
	this.hp = hp;
    }

    // public Integer getMp() {
    // return mp;
    // }
    //
    // public void setMp(Integer mp) {
    // this.mp = mp;
    // }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setLoginTime(Date loginTime) {
	this.loginTime = loginTime;
    }

    public void setLogoutTime(Date logoutTime) {
	this.logoutTime = logoutTime;
    }

    public void setLoseExp(int loseExp) {
	this.loseExp = loseExp;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOccupation(Integer occupation) {
	this.occupation = occupation;
    }

    public void setOffline(int offline) {
	this.offline = offline;
    }

    public void setOnlineTime(Date onlineTime) {
	this.onlineTime = onlineTime;
    }

    public void setPlayerArmy(PlayerArmy playerArmy) {
	this.playerArmy = playerArmy;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

    public void setPower(Integer power) {
	this.power = power;
    }

    public void setPrayTime(Date prayTime) {
	this.prayTime = prayTime;
    }

    public void setSilver(Integer silver) {
	this.silver = silver;
    }

    public void setStarStone(Integer starStone) {
	this.starStone = starStone;
    }

    public void setStone(Integer stone) {
	this.stone = stone;
    }

    public void setVersion(String version) {
	this.version = version;
    }

    public void setVip(Integer vip) {
	this.vip = vip;
    }

    public void setVipLevel(Integer vipLevel) {
	this.vipLevel = vipLevel;
    }

    public void setVipScore(Integer vipScore) {
	this.vipScore = vipScore;
    }

    public void setWareNumber(Integer wareNumber) {
	this.wareNumber = wareNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
