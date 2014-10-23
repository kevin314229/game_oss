package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class ZConsumeDay implements Serializable {

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

    /** 是否是临时数据,true1临时数据，false0正式数据 */
    private Boolean tmpFlag;

    public ZConsumeDay() {
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

    public Boolean getTmpFlag() {
	return tmpFlag;
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

    public void setTmpFlag(Boolean tmpFlag) {
	this.tmpFlag = tmpFlag;
    }

}