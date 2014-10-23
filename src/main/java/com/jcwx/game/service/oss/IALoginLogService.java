package com.jcwx.game.service.oss;

import java.util.Date;
import java.util.List;

import com.jcwx.game.domain.IpReport;
import com.jcwx.game.domain.LoginReport;

public interface IALoginLogService {

    /**
     * 平均在线时长
     * 
     * @param beginDate
     * @param endDate
     * @return
     */
    public List getOnlineCountTime(Date beginDate, Date endDate,
	    Integer projectId, String ossServerId, String ptCode);

    /**
     * 登录日志IP去重复统计
     * 
     * @param gameId
     * @param areaId
     * @param ptId
     * @param startDate
     * @param endDate
     * @return
     */
    public List<IpReport> ipReport(Integer gameId, String areaId, String ptId,
	    Date beginDate, Date endDate);

    /**
     * 登录统计
     * 
     * @return
     */
    public List<LoginReport> queryLoginReport(Date beginDate, Date endDate,
	    Integer projectId, String ossServerId, String ptCode);
    
    /**
     * 登录统计
     * 
     * @return
     */
    public List getPlayerBehavior(Date beginDate, Date endDate,Date beginDate2,Date endDate2,
	    Integer projectId, String ossServerId);
    
    
}
