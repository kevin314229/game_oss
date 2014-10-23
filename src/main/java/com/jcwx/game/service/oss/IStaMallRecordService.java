package com.jcwx.game.service.oss;

import java.util.Date;
import java.util.List;

import com.jcwx.game.domain.StaMallRecord;

public interface IStaMallRecordService {
    
    /**
     * 查询所有的AMallRecord
     * 
     * @return AMallRecord的集合
     */
    public List<StaMallRecord> getAMallRecordList(Date beginTime,Date endTime, Integer projectId, String ossServerId);

}
