package com.jcwx.game.common;

import com.jcwx.game.service.oss.IOssLogService;
import com.jcwx.game.service.oss.impl.OssLogService;
import com.jcwx.game.util.Connection;

/**
 * 业务接口
 * 封装核心用法对象
 * @author Administrator
 *
 */
public interface IBusiness {
    /**连接对象*/
    Connection CONNECTION = Connection.getInstance();
    
    IOssLogService OSS_LOG_SERVICE = SpringService.getBean(OssLogService.class);
}
