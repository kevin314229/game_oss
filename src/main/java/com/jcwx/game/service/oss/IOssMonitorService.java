package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.OssMonitor;

/**
 * @Title: IOssMonitorService.java
 * @Description: 系统信息监控
 * @author Run
 * @date 2011-5-3
 * @version V1.0
 */
public interface IOssMonitorService {

    /** 创建新的记录 */
    public void createOssMonitor();

    public void deleteOssMonitorByID(Integer ossMonitorID);

    public OssMonitor getOssMonitorByID(Integer ossMonitorID);

    public List<OssMonitor> getOssMonitorList();

    /** 根据时间获取系统信息列表 */
    public List<OssMonitor> getOssMonitorMinutesListByMinute(Long begin,
	    Long end);

    public void updateOssMonitor(OssMonitor ossMonitor);

}
