package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ZMuticopyDieDay implements Serializable {

    /** 创建时间 */
    private Date createTime;
    /** 日期 */
    private Date dateTime;
    /** 50级副本死亡次数 */
    private Integer fiftyDie;
    /** 40级副本死亡次数 */
    private Integer fortyDie;
    /** 主键ID */
    private Integer id;
    /** 最后修改时间 */
    private Date modifyTime;
    /** 70级副本死亡次数 */
    private Integer seventyDie;
    /** 60级副本死亡次数 */
    private Integer sixtyDie;
    /** 10级副本死亡次数 */
    private Integer tenDie;
    /** 30级副本死亡次数 */
    private Integer thirtyDie;
    /** 20级副本死亡次数 */
    private Integer twentyDie;

    public ZMuticopyDieDay() {
    }

    public ZMuticopyDieDay(Integer id, Integer tenDie, Integer twentyDie,
	    Integer thirtyDie, Integer fortyDie, Integer fiftyDie,
	    Integer sixtyDie, Integer seventyDie, Date dateTime,
	    Date createTime, Date modifyTime) {
	this.id = id;
	this.tenDie = tenDie;
	this.twentyDie = twentyDie;
	this.thirtyDie = thirtyDie;
	this.fortyDie = fortyDie;
	this.fiftyDie = fiftyDie;
	this.sixtyDie = sixtyDie;
	this.seventyDie = seventyDie;
	this.dateTime = dateTime;
	this.createTime = createTime;
	this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Date getDateTime() {
	return dateTime;
    }

    public Integer getFiftyDie() {
	return fiftyDie;
    }

    public Integer getFortyDie() {
	return fortyDie;
    }

    public Integer getId() {
	return id;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public Integer getSeventyDie() {
	return seventyDie;
    }

    public Integer getSixtyDie() {
	return sixtyDie;
    }

    public Integer getTenDie() {
	return tenDie;
    }

    public Integer getThirtyDie() {
	return thirtyDie;
    }

    public Integer getTwentyDie() {
	return twentyDie;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
    }

    public void setFiftyDie(Integer fiftyDie) {
	this.fiftyDie = fiftyDie;
    }

    public void setFortyDie(Integer fortyDie) {
	this.fortyDie = fortyDie;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

    public void setSeventyDie(Integer seventyDie) {
	this.seventyDie = seventyDie;
    }

    public void setSixtyDie(Integer sixtyDie) {
	this.sixtyDie = sixtyDie;
    }

    public void setTenDie(Integer tenDie) {
	this.tenDie = tenDie;
    }

    public void setThirtyDie(Integer thirtyDie) {
	this.thirtyDie = thirtyDie;
    }

    public void setTwentyDie(Integer twentyDie) {
	this.twentyDie = twentyDie;
    }

}
