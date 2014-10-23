package com.jcwx.game.domain;

import com.jcwx.game.common.domain.BaseDomain;

public class PlayerAttribute extends BaseDomain {

    /** 闪避值 */
    private Integer avoid;
    /** 体质 */
    private Integer body;
    /** 智慧 */
    private Integer clever;
    /** 暴击值 */
    private Integer critical;
    /** 血 */
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
    /** 角色属性ID */
    private Integer playerAttributeId;
    /** 所属角色ID */
    private Integer playerBaseId;
    /** 敏捷 */
    private Integer sharp;
    /** 力量 */
    private Integer strength;

    public PlayerAttribute() {
    }

    public PlayerAttribute(Integer playerAttributeId, Integer playerBaseId,
	    Integer strength, Integer sharp, Integer clever, Integer body,
	    Integer hp, Integer mp, Integer physicalAttack,
	    Integer magicAttack, Integer physicalDefend, Integer magicDefend,
	    Integer critical, Integer avoid) {
	this.playerAttributeId = playerAttributeId;
	this.playerBaseId = playerBaseId;
	this.strength = strength;
	this.sharp = sharp;
	this.clever = clever;
	this.body = body;
	this.hp = hp;
	this.mp = mp;
	this.physicalAttack = physicalAttack;
	this.magicAttack = magicAttack;
	this.physicalDefend = physicalDefend;
	this.magicDefend = magicDefend;
	this.critical = critical;
	this.avoid = avoid;
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

    public Integer getPlayerAttributeId() {
	exeGet();
	return playerAttributeId;
    }

    public Integer getPlayerBaseId() {
	exeGet();
	return playerBaseId;
    }

    public Integer getSharp() {
	exeGet();
	return sharp;
    }

    public Integer getStrength() {
	exeGet();
	return strength;
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

    public void setPlayerAttributeId(Integer playerAttributeId) {
	this.playerAttributeId = playerAttributeId;
	exeSet();
    }

    public void setPlayerBaseId(Integer playerBaseId) {
	this.playerBaseId = playerBaseId;
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

}