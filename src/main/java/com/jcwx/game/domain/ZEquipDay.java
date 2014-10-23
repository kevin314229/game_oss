package com.jcwx.game.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ZEquipDay implements Serializable {

    private Integer baseEquipId;
    private Integer equipa;
    private Integer equipb;

    private Integer equipc;
    private Integer equipd;

    private String equipStringA;
    private String equipStringB;
    private String equipStringC;
    private String equipStringD;
    private Integer itemId;

    private String name;
    private Integer playerBaseId;
    private Integer playerItemId;
    private Integer strongLevel;

    public Integer getBaseEquipId() {
	return this.baseEquipId;
    }

    public Integer getEquipa() {
	return this.equipa;
    }

    public Integer getEquipb() {
	return this.equipb;
    }

    public Integer getEquipc() {
	return this.equipc;
    }

    public Integer getEquipd() {
	return this.equipd;
    }

    public String getEquipStringA() {
	return this.equipStringA;
    }

    public String getEquipStringB() {
	return this.equipStringB;
    }

    public String getEquipStringC() {
	return this.equipStringC;
    }

    public String getEquipStringD() {
	return this.equipStringD;
    }

    public Integer getItemId() {
	return this.itemId;
    }

    public String getName() {
	return this.name;
    }

    public Integer getPlayerBaseId() {
	return this.playerBaseId;
    }

    public Integer getPlayerItemId() {
	return this.playerItemId;
    }

    public Integer getStrongLevel() {
	return this.strongLevel;
    }

    public void setBaseEquipId(Integer baseEquipId) {
	this.baseEquipId = baseEquipId;
    }

    public void setEquipa(Integer equipa) {
	this.equipa = equipa;
    }

    public void setEquipb(Integer equipb) {
	this.equipb = equipb;
    }

    public void setEquipc(Integer equipc) {
	this.equipc = equipc;
    }

    public void setEquipd(Integer equipd) {
	this.equipd = equipd;
    }

    public void setEquipStringA(String equipStringA) {
	this.equipStringA = equipStringA;
    }

    public void setEquipStringB(String equipStringB) {
	this.equipStringB = equipStringB;
    }

    public void setEquipStringC(String equipStringC) {
	this.equipStringC = equipStringC;
    }

    public void setEquipStringD(String equipStringD) {
	this.equipStringD = equipStringD;
    }

    public void setItemId(Integer itemId) {
	this.itemId = itemId;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
    }

    public void setPlayerItemId(Integer playerItemId) {
	this.playerItemId = playerItemId;
    }

    public void setStrongLevel(Integer strongLevel) {
	this.strongLevel = strongLevel;
    }

}