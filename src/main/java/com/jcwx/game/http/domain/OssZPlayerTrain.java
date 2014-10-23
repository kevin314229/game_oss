package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 每人玩家角色培养统计
 * 
 * @author Administrator
 * 
 */
public class OssZPlayerTrain implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    // 日期
    private Date dateTime;
    // 天赋培养
    private int giftNub;
    // 20魔晶培养
    private int goldNub2;
    // 50魔晶培养
    private int goldNub5;
    // 金币培养
    private int silverNub;

    public Date getDateTime() {
	return dateTime;
    }

    public int getGiftNub() {
	return giftNub;
    }

    public int getGoldNub2() {
	return goldNub2;
    }

    public int getGoldNub5() {
	return goldNub5;
    }

    public int getSilverNub() {
	return silverNub;
    }

    public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
    }

    public void setGiftNub(int giftNub) {
	this.giftNub = giftNub;
    }

    public void setGoldNub2(int goldNub2) {
	this.goldNub2 = goldNub2;
    }

    public void setGoldNub5(int goldNub5) {
	this.goldNub5 = goldNub5;
    }

    public void setSilverNub(int silverNub) {
	this.silverNub = silverNub;
    }
}
