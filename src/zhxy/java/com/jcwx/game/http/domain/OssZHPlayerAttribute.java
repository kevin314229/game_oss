/**
 * 
 */
package com.jcwx.game.http.domain;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 */
public class OssZHPlayerAttribute implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 闪避值 */
    private Integer avoid;
    /** 体质 */
    private Integer body;
    /** 智慧 */
    private Integer clever;
    /** 暴击值 */
    private Integer critical;
    /** 当前等级已达经验 */
    private long currentLevelExp;
    /** 攻击附加伤害 */
    private Integer extraHarm;
    /** 战斗力 */
    private Integer fight;
    /** 命中 */
    private Integer hit;
    /** 血 */
    private Integer hp;
    /** 当前等级经验 */
    private long levelExp;
    /** 魔法攻击 */
    private Integer magicAttack;
    /** 魔法防御 */
    private Integer magicDefend;
    /** 化劲 */
    private Integer magicDerate;
    /** 物理攻击 */
    private Integer physicalAttack;
    /** 物理防御 */
    private Integer physicalDefend;
    /** 普通攻击免伤值 */
    private Integer physicalDerate;
    /** 力量 */
    private Integer strength;
    /** 韧劲 */
    private Integer tenacity;

    public Integer getAvoid() {
	return avoid;
    }

    public Integer getBody() {
	return body;
    }

    public Integer getClever() {
	return clever;
    }

    public Integer getCritical() {
	return critical;
    }

    public long getCurrentLevelExp() {
	return currentLevelExp;
    }

    public Integer getExtraHarm() {
	return extraHarm;
    }

    public Integer getFight() {
	return fight;
    }

    public Integer getHit() {
	return hit;
    }

    public Integer getHp() {
	return hp;
    }

    public long getLevelExp() {
	return levelExp;
    }

    public Integer getMagicAttack() {
	return magicAttack;
    }

    public Integer getMagicDefend() {
	return magicDefend;
    }

    public Integer getMagicDerate() {
	return magicDerate;
    }

    public Integer getPhysicalAttack() {
	return physicalAttack;
    }

    public Integer getPhysicalDefend() {
	return physicalDefend;
    }

    public Integer getPhysicalDerate() {
	return physicalDerate;
    }

    public Integer getStrength() {
	return strength;
    }

    public Integer getTenacity() {
	return tenacity;
    }

    public void setAvoid(Integer avoid) {
	this.avoid = avoid;
    }

    public void setBody(Integer body) {
	this.body = body;
    }

    public void setClever(Integer clever) {
	this.clever = clever;
    }

    public void setCritical(Integer critical) {
	this.critical = critical;
    }

    public void setCurrentLevelExp(long currentLevelExp) {
	this.currentLevelExp = currentLevelExp;
    }

    public void setExtraHarm(Integer extraHarm) {
	this.extraHarm = extraHarm;
    }

    public void setFight(Integer fight) {
	this.fight = fight;
    }

    public void setHit(Integer hit) {
	this.hit = hit;
    }

    public void setHp(Integer hp) {
	this.hp = hp;
    }

    public void setLevelExp(long levelExp) {
	this.levelExp = levelExp;
    }

    public void setMagicAttack(Integer magicAttack) {
	this.magicAttack = magicAttack;
    }

    public void setMagicDefend(Integer magicDefend) {
	this.magicDefend = magicDefend;
    }

    public void setMagicDerate(Integer magicDerate) {
	this.magicDerate = magicDerate;
    }

    public void setPhysicalAttack(Integer physicalAttack) {
	this.physicalAttack = physicalAttack;
    }

    public void setPhysicalDefend(Integer physicalDefend) {
	this.physicalDefend = physicalDefend;
    }

    public void setPhysicalDerate(Integer physicalDerate) {
	this.physicalDerate = physicalDerate;
    }

    public void setStrength(Integer strength) {
	this.strength = strength;
    }

    public void setTenacity(Integer tenacity) {
	this.tenacity = tenacity;
    }
}
