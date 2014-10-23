package com.jcwx.game.service.oss;

import java.util.List;
import java.util.Map;

import com.jcwx.game.domain.CPPayOrder;

public interface ICPPayOrderService {

    

   
    public List<CPPayOrder> getAllPayOrder();

    
    
    public List<CPPayOrder> getPayOrderByDay(Map<String, Object> params);

}
