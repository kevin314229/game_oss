package com.jcwx.game.service.oss.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseCenterDAO;
import com.jcwx.game.dao.IBaseStaDAO;
import com.jcwx.game.domain.CPPayOrder;
import com.jcwx.game.domain.ServerNotice;
import com.jcwx.game.service.oss.ICPPayOrderService;
@Service
public class CPPayOrderService implements ICPPayOrderService {

    @Autowired
    private IBaseStaDAO staDAO;

    public List<CPPayOrder> getAllPayOrder(){
	
	return (List<CPPayOrder>) staDAO.queryForList("CPPayOrder.getAllPayOrder");
    }

    @Override
    public List<CPPayOrder> getPayOrderByDay(Map<String, Object> params) {
	// TODO Auto-generated method stub
	return (List<CPPayOrder>) staDAO.queryForList("CPPayOrder.getPayOrderByDay",params);
    }

    
}
