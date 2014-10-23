package com.jcwx.game.domain;

import java.util.Date;

import com.jcwx.game.common.domain.BaseDomain;

public class KPIDayReport extends BaseDomain {

    private static final long serialVersionUID = 5782655945813483544L;
    /** 活跃用户数 */
    private Integer activeUserNum;
    private Integer allConsumeNum;
    /** 本日充入金额 */
    private Double allMoney;
    /** 总新登录数(不去重 登录帐号) */
    private Integer allNewLoginNum;
    /** 充值账号数 */
    private Integer allPayNum;
    /** 安卓充值金额 */
    private Double androidMoney;
    /** 大区ID */
    private Integer areaId;
    /** 平均在线时长 */
    private Integer avgOnlineTime;
    /** 查询开始日期 */
    private Date beginDate;
    /** 购买道具人数 */
    private Integer buyPeopleNum;
    /** 购买道具数量 */
    private Integer buyPropsNum;
    /** 本日平均在线人数 */
    private Integer CA;
    /** 消耗ARPU */
    private Double consumeARPU;
    /** 本日消耗金额 */
    private Double consumeMoney;
    /** 排重登录数 */
    private Integer DAU;
    /** 查询结束日期 */
    private Date endDate;
    /** 游戏ID */
    private Integer gameId;
    /** 本日最高人数 */
    private Integer HPC;
    /** IOS充值金额 */
    private Double IOSMoney;
    /** 排重IP登录数 */
    private Integer IPAU;
    /** kpi日报日期 */
    private Date kpiDate;
    /** kpi日报ID */
    private Integer kpiId;
    /** 登录名 */
    private String loginName;
    /** 新登录数 */
    private Integer newLoginNum;
    /** 新付费账号数 */
    private Integer newPayNum;
    /** 新建角色用户数 */
    private Integer newRoleNum;
    /** 次日登录率 */
    private Double nextLoginRate;
    /** 次日注册登录率 */
    private Double nextRegLoginRate;
    /** 充值ARPU */
    private Double payARPU;
    /** 平台标识 */
    private String ptId;

    /** 帐号剩余金额 */
    private Double remainMoney;
    
    private Integer registeNum;
    
    
    private Integer nextLoginNum;
    
    private Integer loginNumDay3;

    private Integer loginNumDay7;
    
    private Integer consumeGold;
    
    private Integer  consumeNum;
    
    private Integer  allRemainGold;
    
    private Double  newPayMoney;
    
    public KPIDayReport() {
    }

    public KPIDayReport(Integer kpiId, Date kpiDate, Integer gameId,
	    String ptId, Integer areaId, String loginName, Integer dAU,
	    Integer iPAU, Integer newLoginNum, Integer activeUserNum,
	    Integer newRoleNum, Integer allNewLoginNum, Double nextLoginRate,
	    Integer avgOnlineTime, Integer hPC, Integer cA, Double allMoney,
	    Double androidMoney, Double iOSMoney, Double consumeMoney,
	    Double remainMoney, Double consumeARPU, Double payARPU,
	    Integer newPayNum, Integer allPayNum, Integer buyPropsNum,
	    Integer buyPeopleNum) {
	super();
	this.kpiId = kpiId;
	this.kpiDate = kpiDate;
	this.gameId = gameId;
	this.ptId = ptId;
	this.areaId = areaId;
	this.loginName = loginName;
	DAU = dAU;
	IPAU = iPAU;
	this.newLoginNum = newLoginNum;
	this.activeUserNum = activeUserNum;
	this.newRoleNum = newRoleNum;
	this.allNewLoginNum = allNewLoginNum;
	this.nextLoginRate = nextLoginRate;
	this.avgOnlineTime = avgOnlineTime;
	HPC = hPC;
	CA = cA;
	this.allMoney = allMoney;
	this.androidMoney = androidMoney;
	IOSMoney = iOSMoney;
	this.consumeMoney = consumeMoney;
	this.remainMoney = remainMoney;
	this.consumeARPU = consumeARPU;
	this.payARPU = payARPU;
	this.newPayNum = newPayNum;
	this.allPayNum = allPayNum;
	this.buyPropsNum = buyPropsNum;
	this.buyPeopleNum = buyPeopleNum;
    }

    public Integer getActiveUserNum() {
	return activeUserNum;
    }

    public Integer getAllConsumeNum() {
	return allConsumeNum;
    }

    public Double getAllMoney() {
	return allMoney;
    }

    public Integer getAllNewLoginNum() {
	return allNewLoginNum;
    }

    public Integer getAllPayNum() {
	return allPayNum;
    }

    public Double getAndroidMoney() {
	return androidMoney;
    }

    public Integer getAreaId() {
	return areaId;
    }

    public Integer getAvgOnlineTime() {
	return avgOnlineTime;
    }

    public Date getBeginDate() {
	return beginDate;
    }

    public Integer getBuyPeopleNum() {
	return buyPeopleNum;
    }

    public Integer getBuyPropsNum() {
	return buyPropsNum;
    }

    public Integer getCA() {
	return CA;
    }

    public Double getConsumeARPU() {
	return consumeARPU;
    }

    public Double getConsumeMoney() {
	return consumeMoney;
    }

    public Integer getDAU() {
	return DAU;
    }

    public Date getEndDate() {
	return endDate;
    }

    public Integer getGameId() {
	return gameId;
    }

    public Integer getHPC() {
	return HPC;
    }

    public Double getIOSMoney() {
	return IOSMoney;
    }

    public Integer getIPAU() {
	return IPAU;
    }

    public Date getKpiDate() {
	return kpiDate;
    }

    public Integer getKpiId() {
	return kpiId;
    }

    public String getLoginName() {
	return loginName;
    }

    public Integer getNewLoginNum() {
	return newLoginNum;
    }

    public Integer getNewPayNum() {
	return newPayNum;
    }

    public Integer getNewRoleNum() {
	return newRoleNum;
    }

    public Double getNextLoginRate() {
	return nextLoginRate;
    }

    public Double getNextRegLoginRate() {
	return nextRegLoginRate;
    }

    public Double getPayARPU() {
	return payARPU;
    }

    public String getPtId() {
	return ptId;
    }

    public Double getRemainMoney() {
	return remainMoney;
    }

    public void setActiveUserNum(Integer activeUserNum) {
	this.activeUserNum = activeUserNum;
    }

    public void setAllConsumeNum(Integer allConsumeNum) {
	this.allConsumeNum = allConsumeNum;
    }

    public void setAllMoney(Double allMoney) {
	this.allMoney = allMoney;
    }

    public void setAllNewLoginNum(Integer allNewLoginNum) {
	this.allNewLoginNum = allNewLoginNum;
    }

    public void setAllPayNum(Integer allPayNum) {
	this.allPayNum = allPayNum;
    }

    public void setAndroidMoney(Double androidMoney) {
	this.androidMoney = androidMoney;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public void setAvgOnlineTime(Integer avgOnlineTime) {
	this.avgOnlineTime = avgOnlineTime;
    }

    public void setBeginDate(Date beginDate) {
	this.beginDate = beginDate;
    }

    public void setBuyPeopleNum(Integer buyPeopleNum) {
	this.buyPeopleNum = buyPeopleNum;
    }

    public void setBuyPropsNum(Integer buyPropsNum) {
	this.buyPropsNum = buyPropsNum;
    }

    public void setCA(Integer cA) {
	CA = cA;
    }

    public void setConsumeARPU(Double consumeARPU) {
	this.consumeARPU = consumeARPU;
    }

    public void setConsumeMoney(Double consumeMoney) {
	this.consumeMoney = consumeMoney;
    }

    public void setDAU(Integer dAU) {
	DAU = dAU;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setHPC(Integer hPC) {
	HPC = hPC;
    }

    public void setIOSMoney(Double iOSMoney) {
	IOSMoney = iOSMoney;
    }

    public void setIPAU(Integer iPAU) {
	IPAU = iPAU;
    }

    public void setKpiDate(Date kpiDate) {
	this.kpiDate = kpiDate;
    }

    public void setKpiId(Integer kpiId) {
	this.kpiId = kpiId;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void setNewLoginNum(Integer newLoginNum) {
	this.newLoginNum = newLoginNum;
    }

    public void setNewPayNum(Integer newPayNum) {
	this.newPayNum = newPayNum;
    }

    public void setNewRoleNum(Integer newRoleNum) {
	this.newRoleNum = newRoleNum;
    }

    public void setNextLoginRate(Double nextLoginRate) {
	this.nextLoginRate = nextLoginRate;
    }

    public void setNextRegLoginRate(Double nextRegLoginRate) {
	this.nextRegLoginRate = nextRegLoginRate;
    }

    public void setPayARPU(Double payARPU) {
	this.payARPU = payARPU;
    }

    public void setPtId(String ptId) {
	this.ptId = ptId;
    }

    public void setRemainMoney(Double remainMoney) {
	this.remainMoney = remainMoney;
    }

    public Integer getRegisteNum() {
        return registeNum;
    }

    public void setRegisteNum(Integer registeNum) {
        this.registeNum = registeNum;
    }

    public Integer getNextLoginNum() {
        return nextLoginNum;
    }

    public void setNextLoginNum(Integer nextLoginNum) {
        this.nextLoginNum = nextLoginNum;
    }

    public Integer getLoginNumDay3() {
        return loginNumDay3;
    }

    public void setLoginNumDay3(Integer loginNumDay3) {
        this.loginNumDay3 = loginNumDay3;
    }

    public Integer getLoginNumDay7() {
        return loginNumDay7;
    }

    public void setLoginNumDay7(Integer loginNumDay7) {
        this.loginNumDay7 = loginNumDay7;
    }

    public Integer getConsumeGold() {
        return consumeGold;
    }

    public void setConsumeGold(Integer consumeGold) {
        this.consumeGold = consumeGold;
    }

    public Integer getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(Integer consumeNum) {
        this.consumeNum = consumeNum;
    }

    public Integer getAllRemainGold() {
        return allRemainGold;
    }

    public void setAllRemainGold(Integer allRemainGold) {
        this.allRemainGold = allRemainGold;
    }

    public Double getNewPayMoney() {
        return newPayMoney;
    }

    public void setNewPayMoney(Double newPayMoney) {
        this.newPayMoney = newPayMoney;
    }


}