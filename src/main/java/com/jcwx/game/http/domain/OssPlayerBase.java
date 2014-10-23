package com.jcwx.game.http.domain;

import java.util.List;

/**
 * @author Administrator
 * 
 */
public class OssPlayerBase {
    /** 背包仓库物品 */
    List<OssPlayeBagItem> ossPlayeBagItemList;

    /** 属性 */
    private OssPlayerAttribute ossPlayerAttribute;
    List<OssPlayerCopyTimes> ossPlayerCopyTimesList;

    /**
     * 日常操作记录
     * */
    private OssPlayerData ossPlayerData;

    /** 装备 */
    List<OssPlayerEquip> ossPlayerEquipList;

    List<OssPlayerTask> ossPlayerTaskList;

    private Integer playerId;

    public List<OssPlayeBagItem> getOssPlayeBagItemList() {
	return ossPlayeBagItemList;
    }

    public OssPlayerAttribute getOssPlayerAttribute() {
	return ossPlayerAttribute;
    }

    public List<OssPlayerCopyTimes> getOssPlayerCopyTimesList() {
	return ossPlayerCopyTimesList;
    }

    public OssPlayerData getOssPlayerData() {
	return ossPlayerData;
    }

    public List<OssPlayerEquip> getOssPlayerEquipList() {
	return ossPlayerEquipList;
    }

    public List<OssPlayerTask> getOssPlayerTaskList() {
	return ossPlayerTaskList;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public void setOssPlayeBagItemList(List<OssPlayeBagItem> ossPlayeBagItemList) {
	this.ossPlayeBagItemList = ossPlayeBagItemList;
    }

    public void setOssPlayerAttribute(OssPlayerAttribute ossPlayerAttribute) {
	this.ossPlayerAttribute = ossPlayerAttribute;
    }

    public void setOssPlayerCopyTimesList(
	    List<OssPlayerCopyTimes> ossPlayerCopyTimesList) {
	this.ossPlayerCopyTimesList = ossPlayerCopyTimesList;
    }

    public void setOssPlayerData(OssPlayerData ossPlayerData) {
	this.ossPlayerData = ossPlayerData;
    }

    public void setOssPlayerEquipList(List<OssPlayerEquip> ossPlayerEquipList) {
	this.ossPlayerEquipList = ossPlayerEquipList;
    }

    public void setOssPlayerTaskList(List<OssPlayerTask> ossPlayerTaskList) {
	this.ossPlayerTaskList = ossPlayerTaskList;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

}
