package com.jcwx.game.service.oss.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseStaDAO;
import com.jcwx.game.domain.ConsumeData;
import com.jcwx.game.domain.ConsumeFirstData;
import com.jcwx.game.service.oss.IConsumeService;

@Service
public class ConsumeService implements IConsumeService {
    @Autowired
    private IBaseStaDAO baseDAO;

    @Override
    public Integer getConsumeDataCount(Integer beginNum, Integer onePageNum,
	    String beginDate, String endDate, Integer gameId, String areaId,
	    String ptId, String loginName, String nickName, Integer sourceType) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	if ("".equals(ptId)) {
	    params.put("ptId", null);
	} else {
	    params.put("ptId", ptId);
	}

	params.put("areaId", areaId);
	params.put("beginNum", beginNum);
	params.put("onePageNum", onePageNum);
	if (loginName != null && !"".equals(loginName))
	    params.put("loginName", loginName.trim());
	if (nickName != null && !"".equals(nickName))
	    params.put("nickName", nickName);
	params.put("sourceType", sourceType);
	return (Integer) baseDAO.queryForObject(
		"ConsumeData.getConsumeDataCount", params);
    }

    @Override
    public List<ConsumeData> getConsumeDataList(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, String loginName,
	    String nickName, Integer sourceType) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	if ("".equals(ptId)) {
	    params.put("ptId", null);
	} else {
	    params.put("ptId", ptId);
	}
	params.put("areaId", areaId);
	params.put("beginNum", beginNum);
	params.put("onePageNum", onePageNum);
	if (loginName != null && !"".equals(loginName))
	    params.put("loginName", loginName.trim());
	if (nickName != null && !"".equals(nickName))
	    params.put("nickName", nickName);
	params.put("sourceType", sourceType);
	return baseDAO.queryForList("ConsumeData.getConsumeDataList", params);
    }

    @Override
    public Integer getConsumeFirstDataCount(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, String loginName,
	    String nickName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	if ("".equals(ptId)) {
	    params.put("ptId", null);
	} else {
	    params.put("ptId", ptId);
	}
	params.put("areaId", areaId);
	params.put("beginNum", beginNum);
	params.put("onePageNum", onePageNum);
	if (loginName != null && !"".equals(loginName))
	    params.put("loginName", loginName.trim());
	if (nickName != null && !"".equals(nickName))
	    params.put("nickName", nickName);
	return (Integer) baseDAO.queryForObject(
		"ConsumeData.getConsumeFirstCount", params);
    }

    @Override
    public List<ConsumeFirstData> getConsumeFirstDataList(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, String loginName,
	    String nickName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	if ("".equals(ptId)) {
	    params.put("ptId", null);
	} else {
	    params.put("ptId", ptId);
	}
	params.put("areaId", areaId);
	params.put("beginNum", beginNum);
	params.put("onePageNum", onePageNum);
	if (loginName != null && !"".equals(loginName))
	    params.put("loginName", loginName.trim());
	if (nickName != null && !"".equals(nickName))
	    params.put("nickName", nickName);
	return baseDAO.queryForList("ConsumeData.getConsumeFirstList", params);
    }

    @Override
    public List<Map> getFromFirstDaysCountList(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, String loginName,
	    String nickName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	if ("".equals(ptId)) {
	    params.put("ptId", null);
	} else {
	    params.put("ptId", ptId);
	}
	params.put("areaId", areaId);
	params.put("fromFirstDays", 0);
	params.put("fromFirstDaysNum", 0);
	if (loginName != null && !"".equals(loginName))
	    params.put("loginName", loginName.trim());
	if (nickName != null && !"".equals(nickName))
	    params.put("nickName", nickName);
	return baseDAO
		.queryForList("ConsumeData.sumFromFirstDaysCount", params);
    }

    @Override
    public List<Map> getLevelCountList(Integer beginNum, Integer onePageNum,
	    String beginDate, String endDate, Integer gameId, String areaId,
	    String ptId, String loginName, String nickName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	if ("".equals(ptId)) {
	    params.put("ptId", null);
	} else {
	    params.put("ptId", ptId);
	}
	params.put("areaId", areaId);
	params.put("beginNum", beginNum);
	params.put("onePageNum", onePageNum);
	params.put("level", 0);
	params.put("levelNum", 0);
	if (loginName != null && !"".equals(loginName))
	    params.put("loginName", loginName.trim());
	if (nickName != null && !"".equals(nickName))
	    params.put("nickName", nickName);
	return baseDAO.queryForList("ConsumeData.sumLevelCount", params);
    }

    @Override
    public List<Map> getOperationCountList(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, String loginName,
	    String nickName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	if ("".equals(ptId)) {
	    params.put("ptId", null);
	} else {
	    params.put("ptId", ptId);
	}
	params.put("areaId", areaId);
	params.put("operation", "");
	params.put("operationNum", 0);
	if (loginName != null && !"".equals(loginName))
	    params.put("loginName", loginName.trim());
	if (nickName != null && !"".equals(nickName))
	    params.put("nickName", nickName);
	return baseDAO.queryForList("ConsumeData.sumOperationCount", params);
    }

    @Override
    public List<ConsumeData> sumAreaConsumeList(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, String loginName,
	    String nickName, Integer sourceType) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	if ("".equals(ptId)) {
	    params.put("ptId", null);
	} else {
	    params.put("ptId", ptId);
	}
	params.put("areaId", areaId);
	params.put("beginNum", beginNum);
	params.put("onePageNum", onePageNum);
	if (loginName != null && !"".equals(loginName))
	    params.put("loginName", loginName.trim());
	if (nickName != null && !"".equals(nickName))
	    params.put("nickName", nickName);
	params.put("sourceType", sourceType);
	return baseDAO.queryForList("ConsumeData.sumAreaConsumeList", params);
    }

    public List sumAreaConsumeList(String beginDate, String endDate,
	    Integer gameId, String areaId,Integer sourceType
	     ){
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	params.put("sourceType", sourceType);
	return baseDAO.queryForList("ConsumeData.sumAreaGameConsumeList", params);
    }

    @Override
    public List<ConsumeData> queryCycConsumeGoldList(
	    Map<String, Object> params) {
	
	return baseDAO.queryForList("ConsumeData.queryCycConsumeGoldList", params);
    }

    @Override
    public Double queryCycConsumeGoldSum(Map<String, Object> params) {
	// TODO Auto-generated method stub
	return (Double) baseDAO.queryForObject("ConsumeData.queryCycConsumeGoldSum", params);
    }
}
