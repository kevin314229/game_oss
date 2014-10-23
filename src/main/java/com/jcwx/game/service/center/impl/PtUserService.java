package com.jcwx.game.service.center.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseCenterDAO;
import com.jcwx.game.service.center.IPtUserService;
@Service
public class PtUserService implements IPtUserService{

    @Autowired
    private IBaseCenterDAO baseCenterDAO;
    
    
    @Override
    public List getPtregister(Date beginDate, Date endDate) {

	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	return baseCenterDAO.queryForList("PtUser.getPtregister", params);
    }

}
