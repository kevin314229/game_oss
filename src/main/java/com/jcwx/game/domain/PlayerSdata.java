package com.jcwx.game.domain;

import java.util.Date;

import com.jcwx.game.common.domain.BaseDomain;

public class PlayerSdata extends BaseDomain {

    private Date date1;
    /**  */
    private Integer id;
    /**  */
    private Integer playerId;
    /**  */
    private Integer type;
    /**  */
    private Integer val;

    /**  */
    private String val2;

    public PlayerSdata() {
    }

    public PlayerSdata(Integer id, Integer playerId, Integer type, Integer val,
	    String val2, Date date1) {
	this.id = id;
	this.playerId = playerId;
	this.type = type;
	this.val = val;
	this.val2 = val2;
	this.date1 = date1;
    }

    public Date getDate1() {
	exeSet();
	return date1;
    }

    public Integer getId() {
	exeGet();
	return id;
    }

    public Integer getPlayerId() {
	exeGet();
	return playerId;
    }

    public Integer getType() {
	exeGet();
	return type;
    }

    public Integer getVal() {
	exeGet();
	return val;
    }

    public String getVal2() {
	exeGet();
	return val2;
    }

    public void setDate1(Date date1) {
	this.date1 = date1;
	exeSet();
    }

    public void setId(Integer id) {
	this.id = id;
	exeSet();
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
	exeSet();
    }

    public void setType(Integer type) {
	this.type = type;
	exeSet();
    }

    public void setVal(Integer val) {
	this.val = val;
	exeSet();
    }

    public void setVal2(String val2) {
	this.val2 = val2;
	exeSet();
    }

}