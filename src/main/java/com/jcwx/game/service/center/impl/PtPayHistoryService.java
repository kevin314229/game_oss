package com.jcwx.game.service.center.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseCenterDAO;
import com.jcwx.game.service.center.IPtPayHistoryService;


@Service("ptPayHistoryService")
public class PtPayHistoryService implements IPtPayHistoryService{

    @Autowired
    private IBaseCenterDAO baseCenterDAO;

    @Override
    public List getPtPayHistorySta(Date beginDate, Date endDate) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	return baseCenterDAO.queryForList("PtPayHistory.getPayHistorysta", params);
    }

    @Override
    public List getPtPayHistoryUserSta(Date beginDate, Date endDate) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	return baseCenterDAO.queryForList("PtPayHistory.getPayHistoryUser", params);
    }

    @Override
    public List getPtPayHistoryNewSta(Date beginDate, Date endDate) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	return baseCenterDAO.queryForList("PtPayHistory.getPayHistoryNew", params);
    }

    
    
    

}
