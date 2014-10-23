package com.jcwx.game.http.domain;

import java.util.List;

public class OssZHPlayerInfor {

    private List<OssZHPlayerBaseInfor> playerBaseInfors;

    /*** Oss 传输类 */
    private OssZHPlayerClass playerClass;

    private OssZHPlayerVipInfor playerVipInfor;

    public List<OssZHPlayerBaseInfor> getPlayerBaseInfors() {
	return playerBaseInfors;
    }

    public OssZHPlayerClass getPlayerClass() {
	return playerClass;
    }

    public OssZHPlayerVipInfor getPlayerVipInfor() {
	return playerVipInfor;
    }

    public void setPlayerBaseInfors(List<OssZHPlayerBaseInfor> playerBaseInfors) {
	this.playerBaseInfors = playerBaseInfors;
    }

    public void setPlayerClass(OssZHPlayerClass playerClass) {
	this.playerClass = playerClass;
    }

    public void setPlayerVipInfor(OssZHPlayerVipInfor playerVipInfor) {
	this.playerVipInfor = playerVipInfor;
    }

}
