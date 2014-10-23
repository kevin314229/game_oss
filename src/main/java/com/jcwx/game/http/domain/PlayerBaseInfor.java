/**
 * 
 */
package com.jcwx.game.http.domain;

import java.util.Date;

//import com.jcwx.game.core.dto.copy.PlayerCopyBase;
//import com.jcwx.game.core.dto.copy.WorldBossInfo;
//import com.jcwx.game.core.schedule.runnable.CopyRunnable;
//import com.jcwx.game.domain.PlayerBaseEquip;
//import com.jcwx.game.domain.PlayerVip;

/**
 * @author Administrator
 * 
 */
public class PlayerBaseInfor {

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
    /** 背包格子 **/
    private Integer bagNumber;
    /** 绑定金币 */
    private Integer bindGold;
    /** 角色创建时间 **/
    private Date createTime;
    /** 玩家经验 */
    private Long experience;
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
    /** 玩家副本对象 **/
    // private PlayerCopyBase playerCopyBase;
    // /** 世界boss对象 **/
    // private WorldBossInfo worldBossInfo;
    // /** 玩家副本扫荡对象 **/
    // private CopyRunnable copyRunnable;
    /**
     * 昨天遗失经验
     */
    private int loseExp;
    /** 当前魔法 **/
    private Integer mp;
    /** 玩家昵称 */
    private String nickName;
    /** 游戏职业 */
    private Integer occupation;

    /** vip标识0-非VIP>0 VIP **/
    // private Integer vip;
    // /** 玩家属性对象 **/
    // private PlayerAttribute playerAttribute;
    // /** 玩家装备列表 **/
    // private PlayerBaseEquip playerBaseEquip;
    // /**玩家Vip*/
    // private PlayerVip playerVip;

    // /** 玩家当前所在副本ID **/
    // private String gameCopyId;
    /** 玩家离线标识0-在线，1-离线但是数据还在内存中 **/
    private int offline = -1;
    /** 领奖时间 **/
    private Date onlineTime;

    /**
     * 军团信息oss
     * */
    private PlayerArmy playerArmy;

    /** 角色ID */
    private Integer playerBaseId;

    /** 玩家ID */
    private Integer playerId;

    /** 体力值 */
    private Integer power;

    /** 许愿时间 */
    private Date prayTime;
    /** 玩家性别,1-男，2-女 */
    private Integer sex;

    /** 银币 */
    private Integer silver;
    /** 星石数量 */
    private Integer starStone;
    /** 洗练石 */
    private Integer stone;
    /** 技能点 */
    private Integer techPoint;
    /** 服务器版本号 **/
    private String version;

    /** 仓库格子 **/
    private Integer wareNumber;

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

    public Integer getMp() {
	return mp;
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

    public Integer getSex() {
	return sex;
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

    public Integer getTechPoint() {
	return techPoint;
    }

    public String getVersion() {
	return version;
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

    public void setGuide(byte[] guide) {
	this.guide = guide;
    }

    public void setHp(Integer hp) {
	this.hp = hp;
    }

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

    public void setMp(Integer mp) {
	this.mp = mp;
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

    public void setSex(Integer sex) {
	this.sex = sex;
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

    public void setTechPoint(Integer techPoint) {
	this.techPoint = techPoint;
    }

    public void setVersion(String version) {
	this.version = version;
    }

    public void setWareNumber(Integer wareNumber) {
	this.wareNumber = wareNumber;
    }

}
