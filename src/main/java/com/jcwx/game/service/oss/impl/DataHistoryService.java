package com.jcwx.game.service.oss.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.common.DateService;
import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.DataHistory;
import com.jcwx.game.service.oss.IDataHistoryService;

@Service
public class DataHistoryService implements IDataHistoryService {
    @Autowired
    private IBaseDAO baseDao;

    @Override
    public Integer createDataHistory(Integer playerID, DataHistory dataHistory) {
	if (dataHistory == null) {
	    return null;
	}

	String currDateStr = DateService
		.getCurrentDateAsStringCustom("yyyyMMddHHmm");
	Long begin = Long.parseLong(currDateStr.toString());

	dataHistory.setDataHistoryID(begin);

	return (Integer) baseDao.insert("DataHistory.createDataHistory",
		dataHistory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<HashMap> DataHistoryListByDay(Long begin, Long end) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("begin", begin);
	params.put("end", end);
	return baseDao.queryForList("DataHistory.DataHistoryListByDay", params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<HashMap> DataHistoryListByHour(Long begin, Long end) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("begin", begin);
	params.put("end", end);
	return baseDao
		.queryForList("DataHistory.DataHistoryListByHour", params);
    }

    @Override
    public void deleteDataHistoryByID(Integer playerID, Long dataHistoryID) {
	baseDao.delete("DataHistory.deleteDataHistoryByID", dataHistoryID);
    }

    @Override
    public Integer getAvgOnlinePlayerNum(Integer playerID) {
	return (Integer) baseDao
		.queryForObject("DataHistory.getAvgOnlinePlayerNum");
    }

    @Override
    public Integer getAvgOnlinePlayerNumByTime(Integer playerID, Long begin,
	    Long end) {
	if (begin > end) {
	    return 0;
	}

	Map<String, Long> params = new HashMap<String, Long>();
	params.put("begin", begin);
	params.put("end", end);
	return (Integer) this.baseDao.queryForObject(
		"DataHistory.getAvgOnlinePlayerNumByTime", params);
    }

    @Override
    public DataHistory getDataHistoryByID(Integer playerID, Long dataHistoryID) {
	return (DataHistory) this.baseDao.queryForObject(
		"DataHistory.getDataHistoryByID", dataHistoryID);
    }

    @Override
    public List<DataHistory> getDataHistoryList(Integer playerID) {
	return this.baseDao.queryForList("DataHistory.getDataHistoryList");
    }

    @Override
    public List<DataHistory> getDataHistoryListByTime(Integer playerID,
	    Long begin, Long end) {
	if (begin > end) {
	    return null;
	}

	Map<String, Long> params = new HashMap<String, Long>();
	params.put("begin", begin);
	params.put("end", end);
	return this.baseDao.queryForList(
		"DataHistory.getDataHistoryListByTime", params);
    }

    @Override
    public List<DataHistory> getDataHistoryListByTimeAndOnePage(Long begin,
	    Long end, String orderFlag, Integer beginNum, Integer pageNum) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("begin", begin);
	params.put("end", end);
	params.put("orderFlag", orderFlag);
	params.put("beginNum", beginNum);
	params.put("pageNum", pageNum);
	return this.baseDao.queryForList(
		"DataHistory.getDataHistoryListByTimeAndOnePage", params);
    }

    @Override
    public Integer getDataHistoryListNumByTimeAndOnePage(Long begin, Long end) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("begin", begin);
	params.put("end", end);
	return (Integer) this.baseDao.queryForObject(
		"DataHistory.getDataHistoryListNumByTimeAndOnePage", params);
    }

    @Override
    public Integer getMaxOnlinePlayerNum(Integer playerID) {
	return (Integer) this.baseDao
		.queryForObject("DataHistory.getMaxOnlinePlayerNum");
    }

    @Override
    public Integer getMaxOnlinePlayerNumByTime(Integer playerID, Long begin,
	    Long end) {
	if (begin > end) {
	    return 0;
	}

	Map<String, Long> params = new HashMap<String, Long>();
	params.put("begin", begin);
	params.put("end", end);
	return (Integer) this.baseDao.queryForObject(
		"DataHistory.getMaxOnlinePlayerNumByTime", params);
    }

    @Override
    public Map<String, Object> getOnlinePlayerNumInfo() {
	return (Map<String, Object>) this.baseDao
		.queryForObject("DataHistory.getOnlinePlayerNumInfo");
    }

    @Override
    public Map<String, Object> getOnlinePlayerNumInfoByTime(Long begin, Long end) {
	Map<String, Long> params = new HashMap<String, Long>();
	params.put("begin", begin);
	params.put("end", end);
	return (Map<String, Object>) this.baseDao.queryForObject(
		"DataHistory.getOnlinePlayerNumInfoByTime", params);
    }

    @Override
    public void updateDataHistory(Integer playerID, DataHistory dataHistory) {
	if (dataHistory == null) {
	    return;
	}

	baseDao.update("DataHistory.updateDataHistory", dataHistory);
    }

}
