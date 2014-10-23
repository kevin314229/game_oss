package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.OssDictData;
import com.jcwx.game.domain.OssDictType;

public interface IOssDictService {
    /**
     * 创建OssDictData
     * 
     * @param ossDictData
     * @return 数据影响条数
     */
    public Integer createOssDictData(OssDictData ossDictData);

    /**
     * 创建OssDictType
     * 
     * @param ossDictType
     * @return 数据影响条数
     */
    public Integer createOssDictType(OssDictType ossDictType);

    /**
     * 通过主键ID删除OssDictData
     * 
     * @param id
     * @return 数据影响条数
     */
    public Integer deleteOssDictDataByID(Integer dictId);

    /**
     * 通过主键ID删除OssDictType
     * 
     * @param typeId
     * @return 数据影响条数
     */
    public Integer deleteOssDictTypeByID(Integer typeId);

    /**
     * 通过主键ID查询OssDictData
     * 
     * @param id
     * @return OssDictData
     */
    public OssDictData getOssDictDataByID(Integer dictId);

    public OssDictData getOssDictDataByName(Integer gameId, Integer dictType,
	    String dataName, Integer dictId);

    public OssDictData getOssDictDataByValue(Integer gameId, Integer dictType,
	    String dictValue);

    /**
     * 查询所有的OssDictData
     * 
     * @return OssDictData的集合
     */
    public List<OssDictData> getOssDictDataList(int gameId, int dictType);

    /**
     * 通过主键ID查询OssDictType
     * 
     * @param typeId
     * @return OssDictType
     */
    public OssDictType getOssDictTypeByID(Integer typeId);

    public OssDictType getOssDictTypeByName(Integer gameId, String typeName,
	    Integer typeId);

    public OssDictType getOssDictTypeByType(Integer gameId, Integer dictType,
	    Integer typeId);

    /**
     * 查询所有的OssDictType
     * 
     * @return OssDictType的集合
     */
    public List<OssDictType> getOssDictTypeList(int gameId);

    /**
     * 修改OssDictData
     * 
     * @param ossDictData
     * @return 数据影响条数
     */
    public Integer updateOssDictData(OssDictData ossDictData);

    /**
     * 修改OssDictType
     * 
     * @param ossDictType
     * @return 数据影响条数
     */
    public Integer updateOssDictType(OssDictType ossDictType);

}
