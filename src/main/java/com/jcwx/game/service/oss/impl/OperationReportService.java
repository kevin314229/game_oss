package com.jcwx.game.service.oss.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseStaDAO;
import com.jcwx.game.domain.OperationStatistic;
import com.jcwx.game.service.oss.IOperationReportService;

@Service
public class OperationReportService implements IOperationReportService {
    @Autowired
    private IBaseStaDAO baseDAO;

    @Override
    public List<OperationStatistic> getOperationDaySum(String beginDate,
	    String endDate, Integer gameId, String areaId, String itemIds) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	params.put("itemIds", itemIds);
	return baseDAO.queryForList("OperationStatistic.getOperationDaySum",
		params);
    }

    @Override
    public List<OperationStatistic> getOperationDaySumByNickName(
	    String beginDate, String endDate, Integer gameId, String areaId,
	    String itemIds, String nickName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate + ":00:00");
	params.put("endDate", endDate + ":59:59");
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	params.put("itemIds", itemIds);
	params.put("nickName", nickName);
	return baseDAO.queryForList(
		"OperationStatistic.getOperationDaySumByNickName", params);
    }

    @Override
    public List<OperationStatistic> getOperationListByNickName(
	    String beginDate, String endDate, Integer gameId, String areaId,
	    int itemId, String nickName, int flowType) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate + ":00:00");
	params.put("endDate", endDate + ":59:59");
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	params.put("itemId", itemId);
	params.put("nickName", nickName);
	params.put("flowType", flowType);
	return baseDAO.queryForList(
		"OperationStatistic.getOperationListByNickName", params);
    }

    @Override
    public List<OperationStatistic> getOperationStaList(String beginDate,
	    String endDate, Integer gameId, String areaId, int itemId,
	    int flowType) {
	Map<String, Object> params = new HashMap<String, Object>();
	if (beginDate.length() == 13) {
	    params.put("beginDate", beginDate);
	} else {
	    params.put("beginDate", beginDate + " 00");
	}
	if (beginDate.length() == 13) {
	    params.put("endDate", endDate);
	} else {
	    params.put("endDate", endDate + " 23");
	}
	// params.put("endDate", endDate+":59:59");
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	params.put("itemId", itemId);
	params.put("flowType", flowType);
	return baseDAO.queryForList("OperationStatistic.getOperationStaList",
		params);
    }

    @Override
    public List<OperationStatistic> getOperationStaSum(String beginDate,
	    String endDate, Integer gameId, String areaId, String itemIds) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	params.put("itemIds", itemIds);
	return baseDAO.queryForList("OperationStatistic.getOperationStaSum",
		params);
    }

    @Override
    public List<OperationStatistic> getOperationSumByNickName(String beginDate,
	    String endDate, Integer gameId, String areaId, String itemIds,
	    String nickName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate + ":00:00");
	params.put("endDate", endDate + ":59:59");
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	params.put("itemIds", itemIds);
	params.put("nickName", nickName);
	return baseDAO.queryForList(
		"OperationStatistic.getOperationSumByNickName", params);
    }

}
