package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

public class OssZHPlayerData implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /** 成就点 */
    private int achieveNum;
    /** 点石成金次数 */
    private Integer alchemyTimes;
    /** 玩家获取符文总次数(暂时加到这，以后有了成就之后弄过去) */
    // TODO ....做成就的时候把这个字段弄过去
    private Integer amuletAllTimes;
    /** VIP符文专家使用次数 */
    @Deprecated
    private Integer amuletMiddle;
    /** VIP符文大师使用次数 */
    @Deprecated
    private Integer amulteBig;
    /***** 竞技场每天战斗次数 ******/
    @Deprecated
    private Integer arenaTimes;
    /** 是否领取军团福利 */
    private Integer armyReword;
    /** 是否购买遗失经验 0 没有购买 1已购买 */
    @Deprecated
    private Integer buyExp;
    /**
     * 已经免费召唤次数
     * */
    private Integer callFree;
    /**
     * 已经召唤次数
     * */
    private Integer callStart;
    /** 玩家当天已使用副本复活次数，0点清空 **/
    private Integer copyRecoverTime;
    /**
     * 日常任务完成次数
     */
    private Integer dailyTaskTime;
    /** 退出军团时间 */
    private Date exitArmyTime;
    /**
     * 角色首充双倍标识 0 未享受双倍奖励 1 已
     */
    private Integer firstChargeDoubleReward;
    /**
     * 角色领取首充奖励标识 0 未领 1 已领
     */
    private Integer firstChargeReward;
    /**
     * 角色领取每日首充奖励标识 0 未领 1 已领
     */
    private Integer firstChargeRewardPreDay;
    /** 免费刷新种子次数 **/
    private int freeSeedCount;
    /**
     * 是否已收货天星 1--是 0 否
     */
    private Integer gains;
    /** 军团成员每天施肥次数 */
    @Deprecated
    private Integer godtreeFertilizeNum;
    /** 小助手领奖字段 */
    private Integer helpStep;
    /**  */
    private Integer id;
    /**
     * 是否已召唤 0--未召唤 1 已召唤
     * */
    private Integer isCall;
    /** 军团成员上次施肥时间 */
    @Deprecated
    private Date lastFertilizationTime;
    /** 当天领取登录奖励状态 0没有领取 1已经领取 0点重置 */
    @Deprecated
    private Integer loginReward;
    /************** 百宝箱幸运积分 *************/
    private Integer luckScore;

    /**** 多人副本购买次数 ****/
    private volatile Integer manyCopyCost;

    /** 玩家进入多人副本次数 */
    private volatile Integer manyCopyNum;
    /** 玩家下次多人副本恢复收溢时间 */
    private volatile Date manyCopytime;
    /** 玩家每天挖矿次数 */
    @Deprecated
    private Integer miningNumber;
    /** 玩家当天寻矿次数 */
    @Deprecated
    private Integer miningSearchNum;
    /** 玩家协助挖矿时间 */
    @Deprecated
    private Date miningTime;
    /** 玩家当前拥有的矿山 */
    @Deprecated
    private Integer miningType;
    /************** 百宝箱开启多次次数 *************/
    private Integer moreOpen;

    /**
     * 是否有新技能 如果为空 :没有
     */
    private Integer newSkillId;
    /** 在线奖励-领奖阶段 */
    private Integer onlineStep;
    /** 在线奖励-已在线时间 */
    private Integer onlineTime;
    /** 角色ID */
    private Integer playerBaseId;
    /** 购买活力次数 */
    private Integer powerTimes;
    /** 许愿次数 */
    @Deprecated
    private Integer prayStep;
    /** 上次许愿时间 */
    @Deprecated
    private Integer prayTime;
    /** 远征深渊活动刷新次数 */
    @Deprecated
    private Integer refreshExpeditionTimes;
    /**
     * 登天路 当日已经购买次数
     * */
    private Integer ringAllbuy;
    /**
     * 登天路购买的剩余次数
     * */
    private Integer ringBuy;
    /**
     * 登天路已经使用免费次数
     * */
    private Integer ringFree;

    /**
     * 登天路挑战时间
     * */
    private Date ringTime;
    /************** 神秘商店杂货刷新次数 *************/
    private Integer secretZahuoCount;

    /************** 神秘商店珍品刷新次数 *************/
    private Integer secretZhenpinCount;
    /** 当天是否签到0-否，1-是，凌晨0点刷新为0 */
    private Integer signFlag;
    /************** 百宝箱开启单次次数 *************/
    private Integer singleOpen;
    /**
     * 当前玩家拥有的 星尘数量
     * */
    private String starDust;
    /**
     * 玩家选择的称号
     * */
    private Integer title;
    /** 今日挂机总经验 */
    private Integer todayExp;
    /** 玩家培养经验值 */
    private Integer trainExp;

    /******* 36重天每日重置次数 ***********/
    private Integer trialsLandTimes;
    /** 玩家当天活跃度 */
    private Integer vitality;
    /** 祭拜神恩，每天一次，零点清空 */
    private Integer worshipGod;
    /** 昨天挂机总经验 */
    private Integer yesterdayExp;

    public int getAchieveNum() {
	return achieveNum;
    }

    public Integer getAlchemyTimes() {
	return alchemyTimes;
    }

    public Integer getAmuletAllTimes() {
	return amuletAllTimes;
    }

    public Integer getAmuletMiddle() {
	return amuletMiddle;
    }

    public Integer getAmulteBig() {
	return amulteBig;
    }

    public Integer getArenaTimes() {
	return arenaTimes;
    }

    public Integer getArmyReword() {
	return armyReword;
    }

    public Integer getBuyExp() {
	return buyExp;
    }

    public Integer getCallFree() {
	return callFree;
    }

    public Integer getCallStart() {
	return callStart;
    }

    public Integer getCopyRecoverTime() {
	return copyRecoverTime;
    }

    public Integer getDailyTaskTime() {
	return dailyTaskTime;
    }

    public Date getExitArmyTime() {
	return exitArmyTime;
    }

    public Integer getFirstChargeDoubleReward() {
	return firstChargeDoubleReward;
    }

    public Integer getFirstChargeReward() {
	return firstChargeReward;
    }

    public Integer getFirstChargeRewardPreDay() {
	return firstChargeRewardPreDay;
    }

    public int getFreeSeedCount() {
	return freeSeedCount;
    }

    public Integer getGains() {
	return gains;
    }

    public Integer getGodtreeFertilizeNum() {
	return godtreeFertilizeNum;
    }

    public Integer getHelpStep() {
	return helpStep;
    }

    public Integer getId() {
	return id;
    }

    public Integer getIsCall() {
	return isCall;
    }

    public Date getLastFertilizationTime() {
	return lastFertilizationTime;
    }

    public Integer getLoginReward() {
	return loginReward;
    }

    public Integer getLuckScore() {
	return luckScore;
    }

    public Integer getManyCopyCost() {
	return manyCopyCost;
    }

    public Integer getManyCopyNum() {
	return manyCopyNum;
    }

    public Date getManyCopytime() {
	return manyCopytime;
    }

    public Integer getMiningNumber() {
	return miningNumber;
    }

    public Integer getMiningSearchNum() {
	return miningSearchNum;
    }

    public Date getMiningTime() {
	return miningTime;
    }

    public Integer getMiningType() {
	return miningType;
    }

    public Integer getMoreOpen() {
	return moreOpen;
    }

    public Integer getNewSkillId() {
	return newSkillId;
    }

    public Integer getOnlineStep() {
	return onlineStep;
    }

    public Integer getOnlineTime() {
	return onlineTime;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public Integer getPowerTimes() {
	return powerTimes;
    }

    public Integer getPrayStep() {
	return prayStep;
    }

    public Integer getPrayTime() {
	return prayTime;
    }

    public Integer getRefreshExpeditionTimes() {
	return refreshExpeditionTimes;
    }

    public Integer getRingAllbuy() {
	return ringAllbuy;
    }

    public Integer getRingBuy() {
	return ringBuy;
    }

    public Integer getRingFree() {
	return ringFree;
    }

    public Date getRingTime() {
	return ringTime;
    }

    public Integer getSecretZahuoCount() {
	return secretZahuoCount;
    }

    public Integer getSecretZhenpinCount() {
	return secretZhenpinCount;
    }

    public Integer getSignFlag() {
	return signFlag;
    }

    public Integer getSingleOpen() {
	return singleOpen;
    }

    public String getStarDust() {
	return starDust;
    }

    public Integer getTitle() {
	return title;
    }

    public Integer getTodayExp() {
	return todayExp;
    }

    public Integer getTrainExp() {
	return trainExp;
    }

    public Integer getTrialsLandTimes() {
	return trialsLandTimes;
    }

    public Integer getVitality() {
	return vitality;
    }

    public Integer getWorshipGod() {
	return worshipGod;
    }

    public Integer getYesterdayExp() {
	return yesterdayExp;
    }

    public void setAchieveNum(int achieveNum) {
	this.achieveNum = achieveNum;
    }

    public void setAlchemyTimes(Integer alchemyTimes) {
	this.alchemyTimes = alchemyTimes;
    }

    public void setAmuletAllTimes(Integer amuletAllTimes) {
	this.amuletAllTimes = amuletAllTimes;
    }

    public void setAmuletMiddle(Integer amuletMiddle) {
	this.amuletMiddle = amuletMiddle;
    }

    public void setAmulteBig(Integer amulteBig) {
	this.amulteBig = amulteBig;
    }

    public void setArenaTimes(Integer arenaTimes) {
	this.arenaTimes = arenaTimes;
    }

    public void setArmyReword(Integer armyReword) {
	this.armyReword = armyReword;
    }

    public void setBuyExp(Integer buyExp) {
	this.buyExp = buyExp;
    }

    public void setCallFree(Integer callFree) {
	this.callFree = callFree;
    }

    public void setCallStart(Integer callStart) {
	this.callStart = callStart;
    }

    public void setCopyRecoverTime(Integer copyRecoverTime) {
	this.copyRecoverTime = copyRecoverTime;
    }

    public void setDailyTaskTime(Integer dailyTaskTime) {
	this.dailyTaskTime = dailyTaskTime;
    }

    public void setExitArmyTime(Date exitArmyTime) {
	this.exitArmyTime = exitArmyTime;
    }

    public void setFirstChargeDoubleReward(Integer firstChargeDoubleReward) {
	this.firstChargeDoubleReward = firstChargeDoubleReward;
    }

    public void setFirstChargeReward(Integer firstChargeReward) {
	this.firstChargeReward = firstChargeReward;
    }

    public void setFirstChargeRewardPreDay(Integer firstChargeRewardPreDay) {
	this.firstChargeRewardPreDay = firstChargeRewardPreDay;
    }

    public void setFreeSeedCount(int freeSeedCount) {
	this.freeSeedCount = freeSeedCount;
    }

    public void setGains(Integer gains) {
	this.gains = gains;
    }

    public void setGodtreeFertilizeNum(Integer godtreeFertilizeNum) {
	this.godtreeFertilizeNum = godtreeFertilizeNum;
    }

    public void setHelpStep(Integer helpStep) {
	this.helpStep = helpStep;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setIsCall(Integer isCall) {
	this.isCall = isCall;
    }

    public void setLastFertilizationTime(Date lastFertilizationTime) {
	this.lastFertilizationTime = lastFertilizationTime;
    }

    public void setLoginReward(Integer loginReward) {
	this.loginReward = loginReward;
    }

    public void setLuckScore(Integer luckScore) {
	this.luckScore = luckScore;
    }

    public void setManyCopyCost(Integer manyCopyCost) {
	this.manyCopyCost = manyCopyCost;
    }

    public void setManyCopyNum(Integer manyCopyNum) {
	this.manyCopyNum = manyCopyNum;
    }

    public void setManyCopytime(Date manyCopytime) {
	this.manyCopytime = manyCopytime;
    }

    public void setMiningNumber(Integer miningNumber) {
	this.miningNumber = miningNumber;
    }

    public void setMiningSearchNum(Integer miningSearchNum) {
	this.miningSearchNum = miningSearchNum;
    }

    public void setMiningTime(Date miningTime) {
	this.miningTime = miningTime;
    }

    public void setMiningType(Integer miningType) {
	this.miningType = miningType;
    }

    public void setMoreOpen(Integer moreOpen) {
	this.moreOpen = moreOpen;
    }

    public void setNewSkillId(Integer newSkillId) {
	this.newSkillId = newSkillId;
    }

    public void setOnlineStep(Integer onlineStep) {
	this.onlineStep = onlineStep;
    }

    public void setOnlineTime(Integer onlineTime) {
	this.onlineTime = onlineTime;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPowerTimes(Integer powerTimes) {
	this.powerTimes = powerTimes;
    }

    public void setPrayStep(Integer prayStep) {
	this.prayStep = prayStep;
    }

    public void setPrayTime(Integer prayTime) {
	this.prayTime = prayTime;
    }

    public void setRefreshExpeditionTimes(Integer refreshExpeditionTimes) {
	this.refreshExpeditionTimes = refreshExpeditionTimes;
    }

    public void setRingAllbuy(Integer ringAllbuy) {
	this.ringAllbuy = ringAllbuy;
    }

    public void setRingBuy(Integer ringBuy) {
	this.ringBuy = ringBuy;
    }

    public void setRingFree(Integer ringFree) {
	this.ringFree = ringFree;
    }

    public void setRingTime(Date ringTime) {
	this.ringTime = ringTime;
    }

    public void setSecretZahuoCount(Integer secretZahuoCount) {
	this.secretZahuoCount = secretZahuoCount;
    }

    public void setSecretZhenpinCount(Integer secretZhenpinCount) {
	this.secretZhenpinCount = secretZhenpinCount;
    }

    public void setSignFlag(Integer signFlag) {
	this.signFlag = signFlag;
    }

    public void setSingleOpen(Integer singleOpen) {
	this.singleOpen = singleOpen;
    }

    public void setStarDust(String starDust) {
	this.starDust = starDust;
    }

    public void setTitle(Integer title) {
	this.title = title;
    }

    public void setTodayExp(Integer todayExp) {
	this.todayExp = todayExp;
    }

    public void setTrainExp(Integer trainExp) {
	this.trainExp = trainExp;
    }

    public void setTrialsLandTimes(Integer trialsLandTimes) {
	this.trialsLandTimes = trialsLandTimes;
    }

    public void setVitality(Integer vitality) {
	this.vitality = vitality;
    }

    public void setWorshipGod(Integer worshipGod) {
	this.worshipGod = worshipGod;
    }

    public void setYesterdayExp(Integer yesterdayExp) {
	this.yesterdayExp = yesterdayExp;
    }

}
