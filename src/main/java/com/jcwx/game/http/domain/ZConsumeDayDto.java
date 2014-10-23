package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

public class ZConsumeDayDto implements Serializable {

    /**
     * 单位时间内，每用户平均消耗=消耗总额/总消耗用户
     */
    private Double acppu;
    /**
     * 单位时间内，每用户平均消耗=消耗总额/总登陆
     */
    private Double acpu;
    /** 存留魔晶总数量 */
    private Long allGoldTotal;
    /** 总消耗魔晶数 */
    private Long consumeGoldTotal;
    /** 消耗次数 */
    private Integer consumeTimes;
    /** 消耗账号数 */
    private Integer consumeUserNum;

    /**  */
    private Date id;

    /** 登录数 */
    private Integer loginTotal;

    /** 所在服务器名字 */
    private String serverName;

    /** 平均单次消耗 */
    private Double singleConsume;

    /** 是否是临时数据,true1临时数据，false0正式数据 */
    private Boolean tmpFlag;

    public Double getAcppu() {
	return acppu;
    }

    public Double getAcpu() {
	return acpu;
    }

    public Long getAllGoldTotal() {
	return allGoldTotal;
    }

    public Long getConsumeGoldTotal() {
	return consumeGoldTotal;
    }

    public Integer getConsumeTimes() {
	return consumeTimes;
    }

    public Integer getConsumeUserNum() {
	return consumeUserNum;
    }

    public Date getId() {
	return id;
    }

    public Integer getLoginTotal() {
	return loginTotal;
    }

    public String getServerName() {
	return serverName;
    }

    public Double getSingleConsume() {
	return singleConsume;
    }

    public Boolean getTmpFlag() {
	return tmpFlag;
    }

    public void setAcppu(Double acppu) {
	this.acppu = acppu;
    }

    public void setAcpu(Double acpu) {
	this.acpu = acpu;
    }

    public void setAllGoldTotal(Long allGoldTotal) {
	this.allGoldTotal = allGoldTotal;
    }

    public void setConsumeGoldTotal(Long consumeGoldTotal) {
	this.consumeGoldTotal = consumeGoldTotal;
    }

    public void setConsumeTimes(Integer consumeTimes) {
	this.consumeTimes = consumeTimes;
    }

    public void setConsumeUserNum(Integer consumeUserNum) {
	this.consumeUserNum = consumeUserNum;
    }

    public void setId(Date id) {
	this.id = id;
    }

    public void setLoginTotal(Integer loginTotal) {
	this.loginTotal = loginTotal;
    }

    public void setServerName(String serverName) {
	this.serverName = serverName;
    }

    public void setSingleConsume(Double singleConsume) {
	this.singleConsume = singleConsume;
    }

    public void setTmpFlag(Boolean tmpFlag) {
	this.tmpFlag = tmpFlag;
    }

}