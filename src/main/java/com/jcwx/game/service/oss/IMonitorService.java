package com.jcwx.game.service.oss;

import com.jcwx.game.domain.MonitorInfoBean;

/** 系统相关信息 */
public interface IMonitorService {
    public MonitorInfoBean getMonitorInfoBean() throws Exception;
}
