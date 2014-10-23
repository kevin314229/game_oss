/**
 * 
 */
package com.jcwx.game.http.domain;

/**
 * @author Administrator
 * 
 */
public class PlayerBaseClass {
    private Integer level;

    private String nickName;

    private Integer occupation;

    private Integer playerBaseId;
    
    private String carrierOperator;

    public Integer getLevel() {
	return level;
    }

    public String getNickName() {
	return nickName;
    }

    public Integer getOccupation() {
	return occupation;
    }

    public Integer getPlayerBaseId() {
	return playerBaseId;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setNickName(String nickName) {
	this.nickName = nickName;
    }

    public void setOccupation(Integer occupation) {
	this.occupation = occupation;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public String getCarrierOperator() {
        return carrierOperator;
    }

    public void setCarrierOperator(String carrierOperator) {
        this.carrierOperator = carrierOperator;
    }
    
}
