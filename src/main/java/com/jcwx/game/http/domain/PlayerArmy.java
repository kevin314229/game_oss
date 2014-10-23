/**
 * 
 */
package com.jcwx.game.http.domain;

/**
 * @author Administrator
 * 
 */
public class PlayerArmy {

    /** 军团人数 */
    private Integer armyNumber;
    /** 排名 */
    private Integer armyRank;
    /** 军团建设度，影响军团等级 */
    private Integer develop;
    /** 等级 */
    private Integer level;
    private String name;
    /** 军团长 */
    private String owner;

    public Integer getArmyNumber() {
	return armyNumber;
    }

    public Integer getArmyRank() {
	return armyRank;
    }

    public Integer getDevelop() {
	return develop;
    }

    public Integer getLevel() {
	return level;
    }

    public String getName() {
	return name;
    }

    public String getOwner() {
	return owner;
    }

    public void setArmyNumber(Integer armyNumber) {
	this.armyNumber = armyNumber;
    }

    public void setArmyRank(Integer armyRank) {
	this.armyRank = armyRank;
    }

    public void setDevelop(Integer develop) {
	this.develop = develop;
    }

    public void setLevel(Integer level) {
	this.level = level;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setOwner(String owner) {
	this.owner = owner;
    }

}
