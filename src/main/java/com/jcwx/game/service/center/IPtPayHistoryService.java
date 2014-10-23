package com.jcwx.game.service.center;

import java.util.Date;
import java.util.List;

public interface IPtPayHistoryService {
    
    public List getPtPayHistorySta(Date beginDate ,Date endDate);
    
    public List getPtPayHistoryUserSta(Date beginDate ,Date endDate);
    
    public List getPtPayHistoryNewSta(Date beginDate ,Date endDate);

}
