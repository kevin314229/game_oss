package com.jcwx.game.service.oss.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseStaDAO;
import com.jcwx.game.domain.PayHistory;
import com.jcwx.game.domain.PayHistorySta;
import com.jcwx.game.domain.PayRank;
import com.jcwx.game.domain.PayStatistic;
import com.jcwx.game.service.oss.IPayHistoryService;

/**
 * 充值记录
 * 
 * @author csp
 * 
 */
@Service
public class PayHistoryService implements IPayHistoryService {

    @Autowired
    private IBaseStaDAO baseStaDAO;

    @Override
    public Integer createPayHistory(PayHistory payHistory) {
	return (Integer) this.baseStaDAO.insert("PayHistory.createPayHistory",
		payHistory);
    }

    @Override
    public void deletePayHistoryByID(String payHistoryId) {
	this.baseStaDAO.delete("PayHistory.deletePayHistoryByID", payHistoryId);
    }

    @Override
    public List<PayRank> getAreaPayList(Integer gameId, String areaId,
	    String ptId, String loginName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	if ("".equals(ptId)) {
	    params.put("ptId", null);
	} else {
	    params.put("ptId", ptId);
	}
	params.put("loginName", loginName);
	return baseStaDAO.queryForList("PayReport.getAreaPayList", params);
    }

    @Override
    public List<PayStatistic> getAreaRecordList(Integer gameId, Integer areaId,
	    String beginDate, String endDate) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	return baseStaDAO.queryForList("PayReport.getAreaRecordList", params);
    }

    @Override
    public PayHistory getPayHistoryByID(String payHistoryId) {
	return (PayHistory) this.baseStaDAO.queryForObject(
		"PayHistory.getPayHistoryByID", payHistoryId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PayHistory> getPayHistoryList() {
	return this.baseStaDAO.queryForList("PayHistory.getPayHistoryList");
    }

    @Override
    public List<PayHistorySta> getPayHistorySta(Integer gameID, String ptID,
	    String selectArray, String beginDate, String endDate) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameID", gameID);
	params.put("ptID", ptID);
	params.put("areaID", selectArray);
	params.put("beginDate", beginDate + " 00:00:00");
	params.put("endDate", endDate + " 23:59:59");
	return baseStaDAO.queryForList("PayHistory.getPayHistorySta", params);

    }

    @Override
    public Integer getPayInfoCount(Integer gameId, String areaId, String ptId,
	    String beginDate, String endDate, String loginName, String nickName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	if ("".equals(ptId)) {
	    params.put("ptId", null);
	} else {
	    params.put("ptId", ptId);
	}
	params.put("loginName", loginName);
	params.put("nickName", nickName);
	return (Integer) baseStaDAO.queryForObject("PayReport.getPayInfoCount",
		params);
    }

    @Override
    public List<PayHistory> getPayInfoList(Integer beginNum,
	    Integer onePageNum, Integer gameId, String areaId, String ptId,
	    String beginDate, String endDate, String loginName, String nickName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	if ("".equals(ptId)) {
	    params.put("ptId", null);
	} else {
	    params.put("ptId", ptId);
	}
	params.put("loginName", loginName);
	params.put("nickName", nickName);
	params.put("beginNum", beginNum);
	params.put("onePageNum", onePageNum);
	return baseStaDAO.queryForList("PayReport.getPayInfoList", params);
    }

    @Override
    public Integer getPayRankCount(Integer gameId, String areaId, String ptId,
	    String loginName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	if ("".equals(ptId)) {
	    params.put("ptId", null);
	} else {
	    params.put("ptId", ptId);
	}
	params.put("loginName", loginName);
	return (Integer) baseStaDAO.queryForObject("PayReport.getPayRankCount",
		params);
    }

    @Override
    public List<PayRank> getPayRankList(Integer beginNum, Integer onePageNum,
	    Integer gameId, String areaId, String ptId, String loginName) {
	Map<String, Object> params = new HashMap<String, Object>();
	// params.put("beginDate", beginDate);
	// params.put("endDate", endDate);
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	if ("".equals(ptId)) {
	    params.put("ptId", null);
	} else {
	    params.put("ptId", ptId);
	}
	params.put("beginNum", beginNum);
	params.put("onePageNum", onePageNum);
	params.put("loginName", loginName);
	return baseStaDAO.queryForList("PayReport.getPayRankList", params);
    }

    @Override
    public void updatePayHistory(PayHistory payHistory) {
	this.baseStaDAO.update("PayHistory.updatePayHistory", payHistory);
    }

}
