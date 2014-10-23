/**
 * 
 */
package com.jcwx.game.http.domain;

import java.util.List;

/**
 * @author Administrator
 * 
 */
public class PlayerInfor {
    private List<PlayerBaseInfor> playerBaseInfors;

    /*** Oss 传输类 */
    private PlayerClass playerClass;

    private PlayerVipInfor playerVipInfor;

    public List<PlayerBaseInfor> getPlayerBaseInfors() {
	return playerBaseInfors;
    }

    public PlayerClass getPlayerClass() {
	return playerClass;
    }

    public PlayerVipInfor getPlayerVipInfor() {
	return playerVipInfor;
    }

    public void setPlayerBaseInfors(List<PlayerBaseInfor> playerBaseInfors) {
	this.playerBaseInfors = playerBaseInfors;
    }

    public void setPlayerClass(PlayerClass playerClass) {
	this.playerClass = playerClass;
    }

    public void setPlayerVipInfor(PlayerVipInfor playerVipInfor) {
	this.playerVipInfor = playerVipInfor;
    }

}
