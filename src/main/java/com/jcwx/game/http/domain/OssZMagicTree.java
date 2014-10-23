package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

public class OssZMagicTree implements Serializable {
    /** 神树金币点击数量 */
    private Integer coinNub;
    /** 创建时间 */
    private Date createTime;
    /** 神树经验点击数量 */
    private Integer expNub;
    /** 高级祈福点击数量 */
    private Integer highNub;
    /** 主键Id */
    private Integer id;
    /** 统计日期 */
    private Date idDate;
    /** 神树参与玩家数 */
    private Integer joininNub;
    /** 最后修改时间 */
    private Date modifyTime;
    /** 神树星石点击数量 */
    private Integer starNub;

    public OssZMagicTree() {
    }

    public OssZMagicTree(Integer id, Date idDate, Integer joininNub,
	    Integer coinNub, Integer expNub, Integer starNub, Integer highNub,
	    Date createTime, Date modifyTime) {
	this.id = id;
	this.idDate = idDate;
	this.joininNub = joininNub;
	this.coinNub = coinNub;
	this.expNub = expNub;
	this.starNub = starNub;
	this.highNub = highNub;
	this.createTime = createTime;
	this.modifyTime = modifyTime;
    }

    public Integer getCoinNub() {
	return coinNub;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Integer getExpNub() {
	return expNub;
    }

    public Integer getHighNub() {
	return highNub;
    }

    public Integer getId() {
	return id;
    }

    public Date getIdDate() {
	return idDate;
    }

    public Integer getJoininNub() {
	return joininNub;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public Integer getStarNub() {
	return starNub;
    }

    public void setCoinNub(Integer coinNub) {
	this.coinNub = coinNub;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setExpNub(Integer expNub) {
	this.expNub = expNub;
    }

    public void setHighNub(Integer highNub) {
	this.highNub = highNub;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setIdDate(Date idDate) {
	this.idDate = idDate;
    }

    public void setJoininNub(Integer joininNub) {
	this.joininNub = joininNub;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

    public void setStarNub(Integer starNub) {
	this.starNub = starNub;
    }

}
