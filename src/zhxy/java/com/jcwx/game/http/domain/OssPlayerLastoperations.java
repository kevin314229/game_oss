package com.jcwx.game.http.domain;

import java.io.Serializable;

public class OssPlayerLastoperations implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 2381442532087242086L;
    /** 操作记录ID */
    private Integer operationNo;
    /** 操作时间 */
    private Integer playerCount;

    public OssPlayerLastoperations() {
    }

    public Integer getOperationNo() {
	return operationNo;
    }

    public Integer getPlayerCount() {
	return playerCount;
    }

    public void setOperationNo(Integer operationNo) {
	this.operationNo = operationNo;
    }

    public void setPlayerCount(Integer playerCount) {
	this.playerCount = playerCount;
    }

}