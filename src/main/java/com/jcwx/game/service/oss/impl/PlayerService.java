package com.jcwx.game.service.oss.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseStaDAO;
import com.jcwx.game.domain.PlayerCreateStat;
import com.jcwx.game.service.oss.IPlayerService;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private IBaseStaDAO baseStaDAO;

    @Override
    public List<PlayerCreateStat> getPlayerCreateStatListByDay(Date beginDate,
	    Date endDate, Integer projectId, String ossServerId, String ptCode) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("projectId", projectId);
	params.put("ossServerId", ossServerId);
	params.put("ptCode", ptCode);
	return baseStaDAO.queryForList("Player.getPlayerCreateStatListByDay",
		params);
    }

    @Override
    public List getPlayerCreateStatListByPtCode(Date beginDate, Date endDate,
	    Integer projectId, String ossServerId, String ptCode) {

	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("projectId", projectId);
	params.put("ossServerId", ossServerId);
	params.put("ptCode", ptCode);
	return baseStaDAO.queryForList(
		"Player.getPlayerCreateStatListByPtCode", params);
    }

    @Override
    public List getPlayerCreateStatListByServerId(Date beginDate, Date endDate,
	    Integer projectId, String ossServerId, String ptCode) {

	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("projectId", projectId);
	params.put("ossServerId", ossServerId);
	params.put("ptCode", ptCode);
	return baseStaDAO.queryForList(
		"Player.getPlayerCreateStatListByServerId", params);
    }

    @Override
    public Integer getPlayerNum(Integer gameId) {
	return (Integer) baseStaDAO.queryForObject("Player.getPlayerNum",gameId);
    }

}
