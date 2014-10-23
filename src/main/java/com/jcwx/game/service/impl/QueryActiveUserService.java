package com.jcwx.game.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseStaDAO;
import com.jcwx.game.domain.ZQueryActiveUser;
import com.jcwx.game.service.IQueryActiveUserService;

@Service
public class QueryActiveUserService implements IQueryActiveUserService {

    @Autowired
    private IBaseStaDAO baseDAO;

    @Override
    public List<ZQueryActiveUser> NewRegisterLoginAnalyse(Date beginTime,
	    Date endTime, Integer beginNum, Integer onePageNum, Integer gameId,
	    String selectString) {
	Map<String, Object> parms = new HashMap<String, Object>();
	parms.put("beginTime", beginTime);
	parms.put("endTime", endTime);
	parms.put("beginNum", beginNum);
	parms.put("onePageNum", onePageNum);
	parms.put("gameId", gameId);
	parms.put("selectString", selectString);
	return baseDAO.queryForList("QueryActiveUser.NewRegisterLoginAnalyse",
		parms);
    }

    @Override
    public Integer NewRegisterLoginAnalyseAllNum(Date beginTime, Date endTime,
	    Integer gameId, String selectString) {
	Map<String, Object> parms = new HashMap<String, Object>();
	parms.put("beginTime", beginTime);
	parms.put("endTime", endTime);
	parms.put("gameId", gameId);
	parms.put("selectString", selectString);
	return (Integer) baseDAO.queryForObject(
		"QueryActiveUser.NewRegisterLoginAnalyseAllNum", parms);
    }

    @Override
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
    public Integer QueryActiveUserAllNum(Date beginTime, Date endTime,
	    Integer gameId, String selectString) {
	// TODO Auto-generated method stub
	Map<String, Object> parms = new HashMap<String, Object>();
	parms.put("beginTime", beginTime);
	parms.put("endTime", endTime);
	parms.put("gameId", gameId);
	parms.put("selectString", selectString);
	return (Integer) baseDAO.queryForObject(
		"QueryActiveUser.queryActiveUserAllnum", parms);
    }

    @Override
    public List<ZQueryActiveUser> QueryActiveUserByDay(Date beginTime,
	    Date endTime, Integer beginNum, Integer onePageNum, Integer gameId,
	    String selectString) {
	Map<String, Object> parms = new HashMap<String, Object>();
	parms.put("beginTime", beginTime);
	parms.put("endTime", endTime);
	parms.put("beginNum", beginNum);
	parms.put("onePageNum", onePageNum);
	parms.put("gameId", gameId);
	parms.put("selectString", selectString);
	return baseDAO.queryForList("QueryActiveUser.getLoginNumPerDay", parms);
    }

    public List<ZQueryActiveUser> queryRegisterGroupByPtId(Date beginTime,
	    Date endTime, Integer beginNum, Integer onePageNum, Integer gameId,
	    String selectString){
	Map<String, Object> parms = new HashMap<String, Object>();
	parms.put("beginTime", beginTime);
	parms.put("endTime", endTime);
	parms.put("beginNum", beginNum);
	parms.put("onePageNum", onePageNum);
	parms.put("gameId", gameId);
	parms.put("selectString", selectString);
	return baseDAO.queryForList("QueryActiveUser.queryRegisterGroupByPtId", parms);
    }
}
