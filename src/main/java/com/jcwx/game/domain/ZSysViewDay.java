package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ZSysViewDay implements Serializable {

    /** 充值金额总数 */
    private Integer accoMCount;
    /** 筛选时间段 付费玩家总人数/两次以上登录总人数 */
    private double activePayRatio;
    /** 炼金消耗魔晶总数 */
    private Integer alcheConsumeGold;
    /** 当天充值金额/充值人数 */
    private double arpu;
    /** 平均在线人数 */
    private Integer avgOnline;
    /** 系统金币消耗数. */
    private Integer coinConsume;
    /** 系统金币产出数. */
    private Integer coinOutput;
    /** 系统金币留存数. */
    private Integer coinRetained;
    /** 消耗金额总数 */
    private Integer consMCount;
    /** 日常任务消耗魔晶总数 */
    private Integer dailyConsumeGold;
    /** 日期 */
    private Date dateTime;
    /** 宝石合成购买总数 */
    private Integer gemPurchaseGold;
    /** 系统魔晶消耗总数 */
    private Integer goldConsume;
    /** 系统魔晶产出总数 */
    private Integer goldOutput;
    /** 系统魔晶留存数 */
    private Integer goldRetained;
    /** 主键ID */
    private Integer id;
    /** 登录人数 */
    private Integer loginNub;
    /** 商城消耗魔晶总数 */
    private Integer mallConsumeGold;
    /** 消耗最多角色金额 */
    private Integer maxConsRAmount;
    /** 消耗最多角色名称 */
    private String maxConsRName;
    /** 获得单笔金币角色名 */
    private String maxGName;
    /** 获得单笔金币金额数量 */
    private Integer maxGNub;
    /** 峰值在线人数 */
    private Integer maxOnline;
    /** 充值最多角色金额 */
    private Integer maxRecRAmount;
    /** 充值最多角色名称 */
    private String maxRecRName;
    /** 大区名称 */
    private String name;
    /** 新注册玩家人数 */
    private Integer newRegplayNub;
    /** 筛选时间段 付费玩家总人数/注册总人数 */
    private double payRatio;
    /** 洗练消耗魔晶总数 */
    private Integer polishConsumeGold;
    /** 属性培养消耗总数 */
    private Integer propConsumeMoney;
    /** 充值帐号次数 */
    private Integer rechAccoFnub;
    /** 充值帐号个数 */
    private Integer rechAccoPnub;
    /** 符文消耗魔晶总数 */
    private Integer runeConsuleGold;

    public ZSysViewDay() {
    }

    public ZSysViewDay(Integer id, Date dateTime, String name,
	    Integer newRegplayNub, Integer loginNub, Integer maxOnline,
	    Integer avgOnline, Integer rechAccoPnub, Integer rechAccoFnub,
	    Integer accoMCount, Integer consMCount, double arpu,
	    double payRatio, double activePayRatio, String maxGName,
	    Integer maxGNub, String maxRecRName, Integer maxRecRAmount,
	    String maxConsRName, Integer maxConsRAmount, Integer coinOutput,
	    Integer coinConsume, Integer coinRetained, Integer goldOutput,
	    Integer goldConsume, Integer goldRetained, Integer mallConsumeGold,
	    Integer polishConsumeGold, Integer runeConsuleGold,
	    Integer propConsumeMoney, Integer alcheConsumeGold,
	    Integer gemPurchaseGold, Integer dailyConsumeGold) {
	this.id = id;
	this.dateTime = dateTime;
	this.name = name;
	this.newRegplayNub = newRegplayNub;
	this.loginNub = loginNub;
	this.maxOnline = maxOnline;
	this.avgOnline = avgOnline;
	this.rechAccoPnub = rechAccoPnub;
	this.rechAccoFnub = rechAccoFnub;
	this.accoMCount = accoMCount;
	this.consMCount = consMCount;
	this.arpu = arpu;
	this.payRatio = payRatio;
	this.activePayRatio = activePayRatio;
	this.maxGName = maxGName;
	this.maxGNub = maxGNub;
	this.maxRecRName = maxRecRName;
	this.maxRecRAmount = maxRecRAmount;
	this.maxConsRName = maxConsRName;
	this.maxConsRAmount = maxConsRAmount;
	this.coinOutput = coinOutput;
	this.coinConsume = coinConsume;
	this.coinRetained = coinRetained;
	this.goldOutput = goldOutput;
	this.goldConsume = goldConsume;
	this.goldRetained = goldRetained;
	this.mallConsumeGold = mallConsumeGold;
	this.polishConsumeGold = polishConsumeGold;
	this.runeConsuleGold = runeConsuleGold;
	this.propConsumeMoney = propConsumeMoney;
	this.alcheConsumeGold = alcheConsumeGold;
	this.gemPurchaseGold = gemPurchaseGold;
	this.dailyConsumeGold = dailyConsumeGold;
    }

    public Integer getAccoMCount() {
	return accoMCount;
    }

    public double getActivePayRatio() {
	return activePayRatio;
    }

    public Integer getAlcheConsumeGold() {
	return alcheConsumeGold;
    }

    public double getArpu() {
	return arpu;
    }

    public Integer getAvgOnline() {
	return avgOnline;
    }

    public Integer getCoinConsume() {
	return coinConsume;
    }

    public Integer getCoinOutput() {
	return coinOutput;
    }

    public Integer getCoinRetained() {
	return coinRetained;
    }

    public Integer getConsMCount() {
	return consMCount;
    }

    public Integer getDailyConsumeGold() {
	return dailyConsumeGold;
    }

    public Date getDateTime() {
	return dateTime;
    }

    public Integer getGemPurchaseGold() {
	return gemPurchaseGold;
    }

    public Integer getGoldConsume() {
	return goldConsume;
    }

    public Integer getGoldOutput() {
	return goldOutput;
    }

    public Integer getGoldRetained() {
	return goldRetained;
    }

    public Integer getId() {
	return id;
    }

    public Integer getLoginNub() {
	return loginNub;
    }

    public Integer getMallConsumeGold() {
	return mallConsumeGold;
    }

    public Integer getMaxConsRAmount() {
	return maxConsRAmount;
    }

    public String getMaxConsRName() {
	return maxConsRName;
    }

    public String getMaxGName() {
	return maxGName;
    }

    public Integer getMaxGNub() {
	return maxGNub;
    }

    public Integer getMaxOnline() {
	return maxOnline;
    }

    public Integer getMaxRecRAmount() {
	return maxRecRAmount;
    }

    public String getMaxRecRName() {
	return maxRecRName;
    }

    public String getName() {
	return name;
    }

    public Integer getNewRegplayNub() {
	return newRegplayNub;
    }

    public double getPayRatio() {
	return payRatio;
    }

    public Integer getPolishConsumeGold() {
	return polishConsumeGold;
    }

    public Integer getPropConsumeMoney() {
	return propConsumeMoney;
    }

    public Integer getRechAccoFnub() {
	return rechAccoFnub;
    }

    public Integer getRechAccoPnub() {
	return rechAccoPnub;
    }

    public Integer getRuneConsuleGold() {
	return runeConsuleGold;
    }

    public void setAccoMCount(Integer accoMCount) {
	this.accoMCount = accoMCount;
    }

    public void setActivePayRatio(double activePayRatio) {
	this.activePayRatio = activePayRatio;
    }

    public void setAlcheConsumeGold(Integer alcheConsumeGold) {
	this.alcheConsumeGold = alcheConsumeGold;
    }

    public void setArpu(double arpu) {
	this.arpu = arpu;
    }

    public void setAvgOnline(Integer avgOnline) {
	this.avgOnline = avgOnline;
    }

    public void setCoinConsume(Integer coinConsume) {
	this.coinConsume = coinConsume;
    }

    public void setCoinOutput(Integer coinOutput) {
	this.coinOutput = coinOutput;
    }

    public void setCoinRetained(Integer coinRetained) {
	this.coinRetained = coinRetained;
    }

    public void setConsMCount(Integer consMCount) {
	this.consMCount = consMCount;
    }

    public void setDailyConsumeGold(Integer dailyConsumeGold) {
	this.dailyConsumeGold = dailyConsumeGold;
    }

    public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
    }

    public void setGemPurchaseGold(Integer gemPurchaseGold) {
	this.gemPurchaseGold = gemPurchaseGold;
    }

    public void setGoldConsume(Integer goldConsume) {
	this.goldConsume = goldConsume;
    }

    public void setGoldOutput(Integer goldOutput) {
	this.goldOutput = goldOutput;
    }

    public void setGoldRetained(Integer goldRetained) {
	this.goldRetained = goldRetained;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setLoginNub(Integer loginNub) {
	this.loginNub = loginNub;
    }

    public void setMallConsumeGold(Integer mallConsumeGold) {
	this.mallConsumeGold = mallConsumeGold;
    }

    public void setMaxConsRAmount(Integer maxConsRAmount) {
	this.maxConsRAmount = maxConsRAmount;
    }

    public void setMaxConsRName(String maxConsRName) {
	this.maxConsRName = maxConsRName;
    }

    public void setMaxGName(String maxGName) {
	this.maxGName = maxGName;
    }

    public void setMaxGNub(Integer maxGNub) {
	this.maxGNub = maxGNub;
    }

    public void setMaxOnline(Integer maxOnline) {
	this.maxOnline = maxOnline;
    }

    public void setMaxRecRAmount(Integer maxRecRAmount) {
	this.maxRecRAmount = maxRecRAmount;
    }

    public void setMaxRecRName(String maxRecRName) {
	this.maxRecRName = maxRecRName;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setNewRegplayNub(Integer newRegplayNub) {
	this.newRegplayNub = newRegplayNub;
    }

    public void setPayRatio(double payRatio) {
	this.payRatio = payRatio;
    }

    public void setPolishConsumeGold(Integer polishConsumeGold) {
	this.polishConsumeGold = polishConsumeGold;
    }

    public void setPropConsumeMoney(Integer propConsumeMoney) {
	this.propConsumeMoney = propConsumeMoney;
    }

    public void setRechAccoFnub(Integer rechAccoFnub) {
	this.rechAccoFnub = rechAccoFnub;
    }

    public void setRechAccoPnub(Integer rechAccoPnub) {
	this.rechAccoPnub = rechAccoPnub;
    }

    public void setRuneConsuleGold(Integer runeConsuleGold) {
	this.runeConsuleGold = runeConsuleGold;
    }

}