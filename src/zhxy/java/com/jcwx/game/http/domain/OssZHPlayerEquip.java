package com.jcwx.game.http.domain;

import java.io.Serializable;

public class OssZHPlayerEquip implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
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
    /** 装备内嵌宝石 1-4个位置 **/
    private String position1;
    private String position2;
    private String position3;
    private String position4;
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

    public String getPosition1() {
	return position1;
    }

    public String getPosition2() {
	return position2;
    }

    public String getPosition3() {
	return position3;
    }

    public String getPosition4() {
	return position4;
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

    public void setPosition1(String position1) {
	this.position1 = position1;
    }

    public void setPosition2(String position2) {
	this.position2 = position2;
    }

    public void setPosition3(String position3) {
	this.position3 = position3;
    }

    public void setPosition4(String position4) {
	this.position4 = position4;
    }

    public void setStrength(Integer strength) {
	this.strength = strength;
    }

    public void setStrongLevel(Integer strongLevel) {
	this.strongLevel = strongLevel;
    }

}
