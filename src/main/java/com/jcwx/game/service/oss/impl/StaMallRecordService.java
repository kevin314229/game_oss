package com.jcwx.game.service.oss.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseStaDAO;
import com.jcwx.game.domain.StaMallRecord;
import com.jcwx.game.service.oss.IStaMallRecordService;

@SuppressWarnings("unchecked")
@Service
public class StaMallRecordService implements IStaMallRecordService{

    
    @Autowired
    private IBaseStaDAO baseStaDAO;
    /**
     * 查询所有的AMallRecord
     * 
     * @return AMallRecord的集合
     */
    public List<StaMallRecord> getAMallRecordList(Date beginTime,Date endTime, Integer projectId, String ossServerId){
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginTime);
	params.put("endDate", endTime);
	params.put("projectId", projectId);
	params.put("ossServerId", ossServerId);
	return baseStaDAO.queryForList("StaMallRecord.getAMallRecordList", params);
    }

}
