package com.jcwx.game.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseStaDAO;
import com.jcwx.game.domain.ZQueryLostUserLevelDetail;
import com.jcwx.game.service.IQueryLostUserLevelDetail;

@Service
public class QueryLostUserLevelDetail implements IQueryLostUserLevelDetail {

    private static Logger logger = Logger
	    .getLogger(QueryLostUserLevelDetail.class);

    @Autowired
    private IBaseStaDAO baseDAO;

    @Override
    public List<ZQueryLostUserLevelDetail> getQueryActiveUserLevelDetailList(
	    Date firstTime, Date lastTime, Integer gameId, String areaId,
	    Integer playerLevel) {
	Map<String, Object> paraMap = new HashMap<String, Object>();
	paraMap.put("gameId", gameId);
	paraMap.put("areaId", areaId);
	paraMap.put("firstTime", firstTime);
	paraMap.put("lastTime", lastTime);
	paraMap.put("playerLevel", playerLevel);
	return baseDAO.queryForList(
		"QueryLostUserLevelDetail.getQueryActiveUserLevelDetailList",
		paraMap);

    }

    @Override
    public List<ZQueryLostUserLevelDetail> getQueryLostUserLevelDetailList(
	    Date firstTime, Date lastTime, Integer gameId, String areaId,
	    Integer playerLevel) {
	Map<String, Object> paraMap = new HashMap<String, Object>();
	paraMap.put("gameId", gameId);
	paraMap.put("areaId", areaId);
	paraMap.put("firstTime", firstTime);
	paraMap.put("lastTime", lastTime);
	paraMap.put("playerLevel", playerLevel);
	return baseDAO.queryForList(
		"QueryLostUserLevelDetail.getQueryLostUserLevelDetailList",
		paraMap);

    }

}
