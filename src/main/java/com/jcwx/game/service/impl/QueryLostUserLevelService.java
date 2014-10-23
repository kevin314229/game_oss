package com.jcwx.game.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseStaDAO;
import com.jcwx.game.domain.ZQueryLostUserLevel;
import com.jcwx.game.service.IQueryLostUserLevelService;

@Service
public class QueryLostUserLevelService implements IQueryLostUserLevelService {

    @Autowired
    private IBaseStaDAO baseDAO;

    public boolean QuarzQueryActiveUser(Date quarzTime, Integer runFlag) {
	// TODO Auto-generated method stub
	Map<String, Object> parms = new HashMap<String, Object>();
	parms.put("quartz_time", quarzTime);
	parms.put("run_flag", runFlag);
	Map resultMap = (Map) baseDAO.insert(
		"QueryActiveUser.quarzQueryActiveUser", parms);
	if ((Integer) resultMap.get("run_flag") == 1) {
	    return true;
	} else {
	    return false;
	}
    }

    @Override
    public boolean QuarzQueryLostUserLevel(Date quarzTime, Integer runFlag) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public List<ZQueryLostUserLevel> QueryActiveUserLevelByDay(Date beginTime,
	    Date endTime, Integer beginNum, Integer onePageNum, Integer gameId,
	    String selectString) {
	// TODO Auto-generated method stub
	Map<String, Object> parms = new HashMap<String, Object>();
	parms.put("beginTime", beginTime);
	parms.put("endTime", endTime);
	parms.put("beginNum", beginNum);
	parms.put("onePageNum", onePageNum);
	parms.put("gameId", gameId);
	parms.put("selectString", selectString);
	return baseDAO.queryForList(
		"QueryLostUserLevel.queryActiveUserLeveByDateGameIdAreaId",
		parms);
    }

    @Override
    public Integer QueryLostUserLevelAllrecord(Date beginTime, Date endTime,
	    Integer gameId, String selectString) {
	// TODO Auto-generated method stub
	Map<String, Object> parms = new HashMap<String, Object>();
	parms.put("beginTime", beginTime);
	parms.put("endTime", endTime);
	parms.put("gameId", gameId);
	parms.put("selectString", selectString);
	return (Integer) baseDAO.queryForObject(
		"QueryLostUserLevel.queryLostUserLevelAllrecord", parms);

    }

    @Override
    public List<ZQueryLostUserLevel> QueryLostUserLevelByDay(Date beginTime,
	    Date endTime, Integer beginNum, Integer onePageNum, Integer gameId,
	    String selectString) {
	// TODO Auto-generated method stub
	Map<String, Object> parms = new HashMap<String, Object>();
	parms.put("beginTime", beginTime);
	parms.put("endTime", endTime);
	parms.put("beginNum", beginNum);
	parms.put("onePageNum", onePageNum);
	parms.put("gameId", gameId);
	parms.put("selectString", selectString);
	return baseDAO
		.queryForList(
			"QueryLostUserLevel.queryLostUserLeveByDateGameIdAreaId",
			parms);
    }

}
