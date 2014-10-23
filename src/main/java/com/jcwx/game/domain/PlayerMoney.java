package com.jcwx.game.domain;

public class PlayerMoney {
    // 元宝
    private Integer gold;
    // 金票
    private Integer goldTicket;

    public Integer getGold() {
	return gold;
    }

    public Integer getGoldTicket() {
	return goldTicket;
    }

    public void setGold(Integer gold) {
	this.gold = gold;
    }

    public void setGoldTicket(Integer goldTicket) {
	this.goldTicket = goldTicket;
    }
}
