package com.jcwx.game.service.oss.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseStaDAO;
import com.jcwx.game.domain.KPIDayReport;
import com.jcwx.game.service.oss.IKPIReportService;

@Service
public class KPIReportService implements IKPIReportService {
    @Autowired
    private IBaseStaDAO baseDAO;

    @Override
    public Integer getKPIDayReportCount(Integer beginNum, Integer onePageNum,
	    String beginDate, String endDate, Integer gameId, String areaId,
	    String ptId) {
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
	params.put("beginNum", beginNum);
	params.put("onePageNum", onePageNum);
	return (Integer) baseDAO.queryForObject(
		"KPIReport.getKPIDayReportCount", params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<KPIDayReport> getKPIDayReportList(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, Integer[] selectArray) {
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
	params.put("beginNum", beginNum);
	params.put("onePageNum", onePageNum);
	params.put("selectArray", selectArray);
	return baseDAO.queryForList("KPIReport.getKPIDayReport", params);
    }

    @Override
    public List<KPIDayReport> sumKPIDayData(String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId) {
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
	return baseDAO.queryForList("KPIReport.sumKPIDayData", params);
    }

    @Override
    public Map<String, Object> querySumLogin(Map<String, Object> params) {
	// TODO Auto-generated method stub
	return (Map<String, Object>) baseDAO.queryForObject("KPIReport.querySumLogin", params);
    }

    @Override
    public Map<String, Object> querySumPay(Map<String, Object> params) {
	// TODO Auto-generated method stub
	return (Map<String, Object>) baseDAO.queryForObject("KPIReport.querySumPay", params);

    }

    
    @SuppressWarnings("unchecked")
    
    public List<KPIDayReport> getKPIDayReportListByPtCode(Map<String, Object> params) {
	 
	return baseDAO.queryForList("KPIReport.getKPIDayReportByPtCode", params);
    }
    
    public List<KPIDayReport> getPtKPIDayReportList(Map<String, Object> params) {
	 
   	return baseDAO.queryForList("KPIReport.getPtKPIDayReport", params);
    }
}
