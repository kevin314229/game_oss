package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;

public class OssDictType implements Serializable {

    private static final long serialVersionUID = 1924025514595170609L;
    /** 创建时间 */
    private Date createTime;
    /** 字典描述 */
    private String dictDesc;
    /** 字典名称 */
    private String dictName;
    /** 字典类型 */
    private int dictType;
    /** 主键ID */
    private Integer gameId;
    /** 修改时间 */
    private Date modifyTime;
    /** 排序值 */
    private Integer orderSort;
    /** 主键ID */
    private Integer typeId;

    public OssDictType() {
    }

    public Date getCreateTime() {
	return createTime;
    }

    public String getDictDesc() {
	return dictDesc;
    }

    public String getDictName() {
	return dictName;
    }

    public int getDictType() {
	return dictType;
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

    public Integer getTypeId() {
	return typeId;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setDictDesc(String dictDesc) {
	this.dictDesc = dictDesc;
    }

    public void setDictName(String dictName) {
	this.dictName = dictName;
    }

    public void setDictType(int dictType) {
	this.dictType = dictType;
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

    public void setTypeId(Integer typeId) {
	this.typeId = typeId;
    }

}