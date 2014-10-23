package com.jcwx.game.domain;

import com.jcwx.game.common.domain.BaseDomain;

public class PlayerEquipProp extends BaseDomain {

    /** 闪避值 */
    private Integer avoid;
    /** 体质 */
    private Integer body;
    /** 智慧 */
    private Integer clever;
    /** 暴击值 */
    private Integer critical;
    /** 闪避 */
    private Integer dodge;
    /** 血量 */
    private Integer hp;
    /** 魔法攻击 */
    private Integer magicAttack;
    /** 魔法防御 */
    private Integer magicDefend;
    /** 魔法 */
    private Integer mp;
    /** 物理攻击 */
    private Integer physicalAttack;
    /** 物理防御 */
    private Integer physicalDefend;
    /**  */
    private Integer playerEquipId;
    /** 敏捷 */
    private Integer sharp;
    /** 力量 */
    private Integer strength;
    /** 装备的强化等级 */
    private Integer strongLevel;
    /** 副属性锁定标识，0-未锁定，1-锁定 */
    private Integer viceLock1;
    /**  */
    private Integer viceLock2;
    /**  */
    private Integer viceLock3;
    /**  */
    private Integer viceLock4;
    /**  */
    private Integer viceLock5;
    /**  */
    private Integer viceLock6;
    /**  */
    private Integer viceLock7;
    /** 副属性类型，1，物理攻击2，法术攻击3，物理防御4，法术防御5暴击6闪避7hp8mp9力量10体质11智慧12敏捷 */
    private Integer viceType1;
    /**  */
    private Integer viceType2;
    /**  */
    private Integer viceType3;
    /**  */
    private Integer viceType4;
    /**  */
    private Integer viceType5;
    /**  */
    private Integer viceType6;
    /**  */
    private Integer viceType7;
    /** 副属性值 */
    private Integer viceValue1;
    /**  */
    private Integer viceValue2;
    /**  */
    private Integer viceValue3;
    /**  */
    private Integer viceValue4;
    /**  */
    private Integer viceValue5;
    /**  */
    private Integer viceValue6;
    /**  */
    private Integer viceValue7;

    public PlayerEquipProp() {
    }

    public PlayerEquipProp(Integer playerEquipId, Integer physicalAttack,
	    Integer magicAttack, Integer physicalDefend, Integer magicDefend,
	    Integer critical, Integer avoid, Integer dodge, Integer hp,
	    Integer mp, Integer strength, Integer body, Integer clever,
	    Integer sharp, Integer strongLevel, Integer viceType1,
	    Integer viceValue1, Integer viceLock1, Integer viceLock7,
	    Integer viceValue7, Integer viceType7, Integer viceLock6,
	    Integer viceValue6, Integer viceType6, Integer viceLock5,
	    Integer viceValue5, Integer viceType5, Integer viceLock4,
	    Integer viceValue4, Integer viceType4, Integer viceLock3,
	    Integer viceValue3, Integer viceType3, Integer viceLock2,
	    Integer viceValue2, Integer viceType2) {
	this.playerEquipId = playerEquipId;
	this.physicalAttack = physicalAttack;
	this.magicAttack = magicAttack;
	this.physicalDefend = physicalDefend;
	this.magicDefend = magicDefend;
	this.critical = critical;
	this.avoid = avoid;
	this.dodge = dodge;
	this.hp = hp;
	this.mp = mp;
	this.strength = strength;
	this.body = body;
	this.clever = clever;
	this.sharp = sharp;
	this.strongLevel = strongLevel;
	this.viceType1 = viceType1;
	this.viceValue1 = viceValue1;
	this.viceLock1 = viceLock1;
	this.viceLock7 = viceLock7;
	this.viceValue7 = viceValue7;
	this.viceType7 = viceType7;
	this.viceLock6 = viceLock6;
	this.viceValue6 = viceValue6;
	this.viceType6 = viceType6;
	this.viceLock5 = viceLock5;
	this.viceValue5 = viceValue5;
	this.viceType5 = viceType5;
	this.viceLock4 = viceLock4;
	this.viceValue4 = viceValue4;
	this.viceType4 = viceType4;
	this.viceLock3 = viceLock3;
	this.viceValue3 = viceValue3;
	this.viceType3 = viceType3;
	this.viceLock2 = viceLock2;
	this.viceValue2 = viceValue2;
	this.viceType2 = viceType2;
    }

    public Integer getAvoid() {
	exeGet();
	return avoid;
    }

    public Integer getBody() {
	exeGet();
	return body;
    }

    public Integer getClever() {
	exeGet();
	return clever;
    }

    public Integer getCritical() {
	exeGet();
	return critical;
    }

    public Integer getDodge() {
	exeGet();
	return dodge;
    }

    public Integer getHp() {
	exeGet();
	return hp;
    }

    public Integer getMagicAttack() {
	exeGet();
	return magicAttack;
    }

    public Integer getMagicDefend() {
	exeGet();
	return magicDefend;
    }

    public Integer getMp() {
	exeGet();
	return mp;
    }

    public Integer getPhysicalAttack() {
	exeGet();
	return physicalAttack;
    }

    public Integer getPhysicalDefend() {
	exeGet();
	return physicalDefend;
    }

    public Integer getPlayerEquipId() {
	exeGet();
	return playerEquipId;
    }

    public Integer getSharp() {
	exeGet();
	return sharp;
    }

    public Integer getStrength() {
	exeGet();
	return strength;
    }

    public Integer getStrongLevel() {
	exeGet();
	return strongLevel;
    }

    public Integer getViceLock1() {
	exeGet();
	return viceLock1;
    }

    public Integer getViceLock2() {
	exeGet();
	return viceLock2;
    }

    public Integer getViceLock3() {
	exeGet();
	return viceLock3;
    }

    public Integer getViceLock4() {
	exeGet();
	return viceLock4;
    }

    public Integer getViceLock5() {
	exeGet();
	return viceLock5;
    }

    public Integer getViceLock6() {
	exeGet();
	return viceLock6;
    }

    public Integer getViceLock7() {
	exeGet();
	return viceLock7;
    }

    public Integer getViceType1() {
	exeGet();
	return viceType1;
    }

    public Integer getViceType2() {
	exeGet();
	return viceType2;
    }

    public Integer getViceType3() {
	exeGet();
	return viceType3;
    }

    public Integer getViceType4() {
	exeGet();
	return viceType4;
    }

    public Integer getViceType5() {
	exeGet();
	return viceType5;
    }

    public Integer getViceType6() {
	exeGet();
	return viceType6;
    }

    public Integer getViceType7() {
	exeGet();
	return viceType7;
    }

    public Integer getViceValue1() {
	exeGet();
	return viceValue1;
    }

    public Integer getViceValue2() {
	exeGet();
	return viceValue2;
    }

    public Integer getViceValue3() {
	exeGet();
	return viceValue3;
    }

    public Integer getViceValue4() {
	exeGet();
	return viceValue4;
    }

    public Integer getViceValue5() {
	exeGet();
	return viceValue5;
    }

    public Integer getViceValue6() {
	exeGet();
	return viceValue6;
    }

    public Integer getViceValue7() {
	exeGet();
	return viceValue7;
    }

    public void setAvoid(Integer avoid) {
	this.avoid = avoid;
	exeSet();
    }

    public void setBody(Integer body) {
	this.body = body;
	exeSet();
    }

    public void setClever(Integer clever) {
	this.clever = clever;
	exeSet();
    }

    public void setCritical(Integer critical) {
	this.critical = critical;
	exeSet();
    }

    public void setDodge(Integer dodge) {
	this.dodge = dodge;
	exeSet();
    }

    public void setHp(Integer hp) {
	this.hp = hp;
	exeSet();
    }

    public void setMagicAttack(Integer magicAttack) {
	this.magicAttack = magicAttack;
	exeSet();
    }

    public void setMagicDefend(Integer magicDefend) {
	this.magicDefend = magicDefend;
	exeSet();
    }

    public void setMp(Integer mp) {
	this.mp = mp;
	exeSet();
    }

    public void setPhysicalAttack(Integer physicalAttack) {
	this.physicalAttack = physicalAttack;
	exeSet();
    }

    public void setPhysicalDefend(Integer physicalDefend) {
	this.physicalDefend = physicalDefend;
	exeSet();
    }

    public void setPlayerEquipId(Integer playerEquipId) {
	this.playerEquipId = playerEquipId;
	exeSet();
    }

    public void setSharp(Integer sharp) {
	this.sharp = sharp;
	exeSet();
    }

    public void setStrength(Integer strength) {
	this.strength = strength;
	exeSet();
    }

    public void setStrongLevel(Integer strongLevel) {
	this.strongLevel = strongLevel;
	exeSet();
    }

    public void setViceLock1(Integer viceLock1) {
	this.viceLock1 = viceLock1;
	exeSet();
    }

    public void setViceLock2(Integer viceLock2) {
	this.viceLock2 = viceLock2;
	exeSet();
    }

    public void setViceLock3(Integer viceLock3) {
	this.viceLock3 = viceLock3;
	exeSet();
    }

    public void setViceLock4(Integer viceLock4) {
	this.viceLock4 = viceLock4;
	exeSet();
    }

    public void setViceLock5(Integer viceLock5) {
	this.viceLock5 = viceLock5;
	exeSet();
    }

    public void setViceLock6(Integer viceLock6) {
	this.viceLock6 = viceLock6;
	exeSet();
    }

    public void setViceLock7(Integer viceLock7) {
	this.viceLock7 = viceLock7;
	exeSet();
    }

    public void setViceType1(Integer viceType1) {
	this.viceType1 = viceType1;
	exeSet();
    }

    public void setViceType2(Integer viceType2) {
	this.viceType2 = viceType2;
	exeSet();
    }

    public void setViceType3(Integer viceType3) {
	this.viceType3 = viceType3;
	exeSet();
    }

    public void setViceType4(Integer viceType4) {
	this.viceType4 = viceType4;
	exeSet();
    }

    public void setViceType5(Integer viceType5) {
	this.viceType5 = viceType5;
	exeSet();
    }

    public void setViceType6(Integer viceType6) {
	this.viceType6 = viceType6;
	exeSet();
    }

    public void setViceType7(Integer viceType7) {
	this.viceType7 = viceType7;
	exeSet();
    }

    public void setViceValue1(Integer viceValue1) {
	this.viceValue1 = viceValue1;
	exeSet();
    }

    public void setViceValue2(Integer viceValue2) {
	this.viceValue2 = viceValue2;
	exeSet();
    }

    public void setViceValue3(Integer viceValue3) {
	this.viceValue3 = viceValue3;
	exeSet();
    }

    public void setViceValue4(Integer viceValue4) {
	this.viceValue4 = viceValue4;
	exeSet();
    }

    public void setViceValue5(Integer viceValue5) {
	this.viceValue5 = viceValue5;
	exeSet();
    }

    public void setViceValue6(Integer viceValue6) {
	this.viceValue6 = viceValue6;
	exeSet();
    }

    public void setViceValue7(Integer viceValue7) {
	this.viceValue7 = viceValue7;
	exeSet();
    }

}