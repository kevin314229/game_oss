package com.jcwx.game.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseCenterDAO;
import com.jcwx.game.domain.CenterPt;
import com.jcwx.game.domain.CenterServer;
import com.jcwx.game.service.ICenterServerService;

@Service
public class CenterServerService implements ICenterServerService {
    private static Logger logger = Logger
	    .getLogger(CenterServerService.class);

    @Autowired
    private IBaseCenterDAO centerDAO;

    public List<CenterServer> queryCenterServers() {
	 
	return centerDAO.queryForList("CenterServer.queryCenterServers");
    }

    @Override
    public List<CenterPt> queryCenterPts(Integer areaId) {
	// TODO Auto-generated method stub
	return   centerDAO.queryForList("CenterServer.queryCenterPts",areaId);
    }

    @Override
    public Map<String, Object> queryCenterArea(Integer areaId, String ptId) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("areaId", areaId);
	params.put("ptId", ptId);
	return    (Map<String, Object>) centerDAO.queryForObject("CenterServer.queryCenterArea", params);
    }


}
