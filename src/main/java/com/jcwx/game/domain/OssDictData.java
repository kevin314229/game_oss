package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class OssDictData implements Serializable {
    private static final long serialVersionUID = 6204268823155110865L;
    /** 创建时间 */
    private Date createTime;
    /** 主键ID */
    private Integer dictId;
    /** 字典名称 */
    private String dictName;

    /** 字典类型 */
    private Integer dictType;
    /** 字典真实值 */
    private String dictValue;
    /** 游戏ID */
    private Integer gameId;
    /** 修改时间 */
    private Date modifyTime;
    private Integer orderSort;

    public OssDictData() {
    }

    public OssDictData(Integer dictId, Integer gameId, Integer dictType,
	    String dictName, String dictValue, Date createTime, Date modifyTime) {
	this.dictId = dictId;
	this.gameId = gameId;
	this.dictType = dictType;
	this.dictName = dictName;
	this.dictValue = dictValue;
	this.createTime = createTime;
	this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public Integer getDictId() {
	return dictId;
    }

    public String getDictName() {
	return dictName;
    }

    public Integer getDictType() {
	return dictType;
    }

    public String getDictValue() {
	return dictValue;
    }

    public Integer getGameId() {
	return gameId;
    }

    public Date getModifyTime() {
	return modifyTime;
    }

    public Integer getOrderSort() {
	return orderSort;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDictId(Integer dictId) {
	this.dictId = dictId;
    }

    public void setDictName(String dictName) {
	this.dictName = dictName;
    }

    public void setDictType(Integer dictType) {
	this.dictType = dictType;
    }

    public void setDictValue(String dictValue) {
	this.dictValue = dictValue;
    }

    public void setGameId(Integer gameId) {
	this.gameId = gameId;
    }

    public void setModifyTime(Date modifyTime) {
	this.modifyTime = modifyTime;
    }

    public void setOrderSort(Integer orderSort) {
	this.orderSort = orderSort;
    }

}