package com.jcwx.game.init;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jcwx.game.common.PerformanceTimer;
import com.jcwx.game.domain.OssServer;

/**
 * 初始化OssServer服务器数据缓存
 * 
 * @author Administrator
 * 
 */
public class OssServerUtil extends Util {

    private static Logger logger = Logger.getLogger(OssServerUtil.class);

    /**
     * 初始化系统消息缓存记录
     * 
     * @param map
     */
    public static void initOssServersMap(Map<Integer, OssServer> osMap) {
	PerformanceTimer timer = new PerformanceTimer();
	// 获取Oss列表
	List<OssServer> ossServers = ossServerService.getOssServerList();
	for (OssServer ossServer : ossServers) {
	    try {
		// if(ossServer.getKey() == null){
		// AES.generateKey(ossServer);
		// }
		osMap.put(ossServer.getId(), ossServer);
	    } catch (Exception e) {
		logger.error("initOssServersMap error!");
	    }
	}
	logger.info("初始OssServer服务器数据缓存:" + timer.getStr());
    }

}
