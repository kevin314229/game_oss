package com.jcwx.game.service.oss.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseStaDAO;
import com.jcwx.game.dao.impl.BaseStaDAO;
import com.jcwx.game.domain.IpReport;
import com.jcwx.game.domain.LoginReport;
import com.jcwx.game.service.oss.IALoginLogService;

@Service
public class ALoginLogService implements IALoginLogService {
    @Autowired
    private IBaseStaDAO baseStaDAO;

    @SuppressWarnings("rawtypes")
    @Override
    public List getOnlineCountTime(Date beginDate, Date endDate,
	    Integer projectId, String ossServerId, String ptCode) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("projectId", projectId);
	params.put("ossServerId", ossServerId);
	params.put("ptCode", ptCode);
	return baseStaDAO.queryForList("ALoginLog.getOnlineCountTime", params);
    }

    @Override
    public List<IpReport> ipReport(Integer gameId, String areaId, String ptId,
	    Date beginDate, Date endDate) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	params.put("ptId", ptId);
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	return baseStaDAO.queryForList("ALoginLog.getIpreport", params);
    }

    @Override
    public List<LoginReport> queryLoginReport(Date beginDate, Date endDate,
	    Integer projectId, String ossServerId, String ptCode) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("projectId", projectId);
	params.put("ossServerId", ossServerId);
	params.put("ptCode", ptCode);
	return baseStaDAO.queryForList("ALoginLog.queryLoginReport", params);
    }
    @Override
    public List getPlayerBehavior(Date beginDate, Date endDate,Date beginDate2,Date endDate2,
	    Integer projectId, String ossServerId) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("beginDate2", beginDate2);
	params.put("endDate2", endDate2);
	params.put("projectId", projectId);
	params.put("ossServerId", ossServerId);
	return baseStaDAO.queryForList("ALoginLog.getPlayerBehavior", params);
    }

}
