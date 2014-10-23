package com.jcwx.game.service.oss.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.OssDictData;
import com.jcwx.game.domain.OssDictType;
import com.jcwx.game.service.oss.IOssDictService;

/**
 * 数据字典
 * 
 * @author tanfl
 * 
 */
@Service
public class OssDictService implements IOssDictService {

    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public Integer createOssDictData(OssDictData ossDictData) {
	return (Integer) baseDAO.insert("OssDict.createOssDictData",
		ossDictData);
    }

    @Override
    public Integer createOssDictType(OssDictType ossDictType) {
	return (Integer) baseDAO.insert("OssDict.createOssDictType",
		ossDictType);
    }

    @Override
    public Integer deleteOssDictDataByID(Integer dictId) {

	return baseDAO.delete("OssDict.deleteOssDictDataByID", dictId);

    }

    @Override
    public Integer deleteOssDictTypeByID(Integer typeId) {
	return baseDAO.delete("OssDict.deleteOssDictTypeByID", typeId);
    }

    @Override
    public OssDictData getOssDictDataByID(Integer dictId) {
	return (OssDictData) baseDAO.queryForObject(
		"OssDict.getOssDictDataByID", dictId);
    }

    @Override
    public OssDictData getOssDictDataByName(Integer gameId, Integer dictType,
	    String dataName, Integer dictId) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameId", gameId);
	params.put("dictType", dictType);
	params.put("dataName", dataName);
	params.put("dictId", dictId);
	return (OssDictData) baseDAO.queryForObject(
		"OssDict.getOssDictDataByName", params);
    }

    @Override
    public OssDictData getOssDictDataByValue(Integer gameId, Integer dictType,
	    String dictValue) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameId", gameId);
	params.put("dictType", dictType);
	params.put("dictValue", dictValue);
	return (OssDictData) baseDAO.queryForObject(
		"OssDict.getOssDictDataByValue", params);
    }

    @Override
    public List<OssDictData> getOssDictDataList(int gameId, int dictType) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameId", gameId);
	params.put("dictType", dictType);
	return baseDAO.queryForList("OssDict.getOssDictDataList", params);
    }

    @Override
    public OssDictType getOssDictTypeByID(Integer typeId) {
	return (OssDictType) baseDAO.queryForObject(
		"OssDict.getOssDictTypeByID", typeId);
    }

    @Override
    public OssDictType getOssDictTypeByName(Integer gameId, String typeName,
	    Integer typeId) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameId", gameId);
	params.put("typeName", typeName);
	params.put("typeId", typeId);
	return (OssDictType) baseDAO.queryForObject(
		"OssDict.getOssDictTypeByName", params);
    }

    @Override
    public OssDictType getOssDictTypeByType(Integer gameId, Integer dictType,
	    Integer typeId) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameId", gameId);
	params.put("dictType", dictType);
	params.put("typeId", typeId);
	return (OssDictType) baseDAO.queryForObject(
		"OssDict.getOssDictTypeByType", params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OssDictType> getOssDictTypeList(int gameId) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameId", gameId);
	return baseDAO.queryForList("OssDict.getOssDictTypeList", params);
    }

    @Override
    public Integer updateOssDictData(OssDictData ossDictData) {
	return baseDAO.update("OssDict.updateOssDictData", ossDictData);
    }

    @Override
    public Integer updateOssDictType(OssDictType ossDictType) {
	return baseDAO.update("OssDict.updateOssDictType", ossDictType);
    }

}
