package com.jcwx.game.http.domain;

import java.io.Serializable;
import java.util.List;

public class OssZHPlayerBase implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 背包仓库物品 */
    List<OssZHPlayeBagItem> ossPlayeBagItemList;

    /** 属性 */
    private OssZHPlayerAttribute ossPlayerAttribute;
    List<OssZHPlayerCopyTimes> ossPlayerCopyTimesList;

    /**
     * 日常操作记录
     * */
    private OssZHPlayerData ossPlayerData;

    /** 装备 */
    List<OssZHPlayerEquip> ossPlayerEquipList;

    List<OssZHPlayerTask> ossPlayerTaskList;

    private Integer playerId;

    public List<OssZHPlayeBagItem> getOssPlayeBagItemList() {
	return ossPlayeBagItemList;
    }

    public OssZHPlayerAttribute getOssPlayerAttribute() {
	return ossPlayerAttribute;
    }

    public List<OssZHPlayerCopyTimes> getOssPlayerCopyTimesList() {
	return ossPlayerCopyTimesList;
    }

    public OssZHPlayerData getOssPlayerData() {
	return ossPlayerData;
    }

    public List<OssZHPlayerEquip> getOssPlayerEquipList() {
	return ossPlayerEquipList;
    }

    public List<OssZHPlayerTask> getOssPlayerTaskList() {
	return ossPlayerTaskList;
    }

    public Integer getPlayerId() {
	return playerId;
    }

    public void setOssPlayeBagItemList(
	    List<OssZHPlayeBagItem> ossPlayeBagItemList) {
	this.ossPlayeBagItemList = ossPlayeBagItemList;
    }

    public void setOssPlayerAttribute(OssZHPlayerAttribute ossPlayerAttribute) {
	this.ossPlayerAttribute = ossPlayerAttribute;
    }

    public void setOssPlayerCopyTimesList(
	    List<OssZHPlayerCopyTimes> ossPlayerCopyTimesList) {
	this.ossPlayerCopyTimesList = ossPlayerCopyTimesList;
    }

    public void setOssPlayerData(OssZHPlayerData ossPlayerData) {
	this.ossPlayerData = ossPlayerData;
    }

    public void setOssPlayerEquipList(List<OssZHPlayerEquip> ossPlayerEquipList) {
	this.ossPlayerEquipList = ossPlayerEquipList;
    }

    public void setOssPlayerTaskList(List<OssZHPlayerTask> ossPlayerTaskList) {
	this.ossPlayerTaskList = ossPlayerTaskList;
    }

    public void setPlayerId(Integer playerId) {
	this.playerId = playerId;
    }

}
