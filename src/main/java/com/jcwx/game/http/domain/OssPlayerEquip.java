/**
 * 
 */
package com.jcwx.game.http.domain;

/**
 * @author Administrator
 * 
 */
public class OssPlayerEquip {

    /** 体质 */
    private Integer body;
    /** 智慧 */
    private Integer clever;
    /** 血量 */
    private Integer hp;
    /** 物品ID */
    private Integer itemId;
    /** 魔法攻击 */
    private Integer magicAttack;
    /** 魔法防御 */
    private Integer magicDefend;
    /** 魔法 */
    private Integer mp;
    /**  */
    private String name;
    /** 物理攻击 */
    private Integer physicalAttack;
    /** 物理防御 */
    private Integer physicalDefend;
    /**
     * 装备属性
     * */
    private Integer playerEquipId;
    /** 敏捷 */
    private Integer sharp;
    /** 力量 */
    private Integer strength;
    /** 装备的强化等级 */
    private Integer strongLevel;

    public Integer getBody() {
	return body;
    }

    public Integer getClever() {
	return clever;
    }

    public Integer getHp() {
	return hp;
    }

    public Integer getItemId() {
	return itemId;
    }

    public Integer getMagicAttack() {
	return magicAttack;
    }

    public Integer getMagicDefend() {
	return magicDefend;
    }

    public Integer getMp() {
	return mp;
    }

    public String getName() {
	return name;
    }

    public Integer getPhysicalAttack() {
	return physicalAttack;
    }

    public Integer getPhysicalDefend() {
	return physicalDefend;
    }

    public Integer getPlayerEquipId() {
	return playerEquipId;
    }

    public Integer getSharp() {
	return sharp;
    }

    public Integer getStrength() {
	return strength;
    }

    public Integer getStrongLevel() {
	return strongLevel;
    }

    public void setBody(Integer body) {
	this.body = body;
    }

    public void setClever(Integer clever) {
	this.clever = clever;
    }

    public void setHp(Integer hp) {
	this.hp = hp;
    }

    public void setItemId(Integer itemId) {
	this.itemId = itemId;
    }

    public void setMagicAttack(Integer magicAttack) {
	this.magicAttack = magicAttack;
    }

    public void setMagicDefend(Integer magicDefend) {
	this.magicDefend = magicDefend;
    }

    public void setMp(Integer mp) {
	this.mp = mp;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setPhysicalAttack(Integer physicalAttack) {
	this.physicalAttack = physicalAttack;
    }

    public void setPhysicalDefend(Integer physicalDefend) {
	this.physicalDefend = physicalDefend;
    }

    public void setPlayerEquipId(Integer playerEquipId) {
	this.playerEquipId = playerEquipId;
    }

    public void setSharp(Integer sharp) {
	this.sharp = sharp;
    }

    public void setStrength(Integer strength) {
	this.strength = strength;
    }

    public void setStrongLevel(Integer strongLevel) {
	this.strongLevel = strongLevel;
    }
}
