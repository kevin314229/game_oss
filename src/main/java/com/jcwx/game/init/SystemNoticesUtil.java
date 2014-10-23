package com.jcwx.game.init;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.jcwx.game.common.GameSchedule;
import com.jcwx.game.common.MessageRunnable;
import com.jcwx.game.common.PerformanceTimer;
import com.jcwx.game.common.SystemGlobal;
import com.jcwx.game.domain.SystemNotice;

/**
 * 初始化系统消息缓存记录
 * 
 * @author Administrator
 * 
 */
public class SystemNoticesUtil extends Util {

    private static Logger logger = Logger.getLogger(SystemNoticesUtil.class);

    /**
     * 将缓存中的系统消息数据加载到延时队列中
     */
    public static void initSchedule() {
	Date now = new Date();
	Map<Integer, SystemNotice> systemNoticesMap = SystemGlobal.get(
		SystemGlobal.SYSTEM_NOTICES_MAP, Map.class);
	for (SystemNotice sn : systemNoticesMap.values()) {
	    if (sn.getNoticeTime().after(now)) { // 消息未过期
		MessageRunnable mr = new MessageRunnable(sn.getSystemNoticeId());
		sn.setMessageRunnable(mr);
		GameSchedule.put(mr, 10, TimeUnit.SECONDS);
	    }
	}
    }

    /**
     * 初始化系统消息缓存记录
     * 
     * @param map
     */
    public static void initSystemNoticesMap(Map<Integer, SystemNotice> snMap) {
	PerformanceTimer timer = new PerformanceTimer();
	// 获取系统消息
	List<SystemNotice> systemNoticesList = systemNoticeService
		.getSystemNoticeList();
	for (SystemNotice sn : systemNoticesList) {
	    snMap.put(sn.getSystemNoticeId(), sn);
	}

	logger.info("初始系统消息记录缓存:" + timer.getStr());

    }

}
