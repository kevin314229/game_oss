/*
 * ScheduleThreadPool.java 
 * 创建于  2013-6-20
 * 
 * 版权所有@深圳市精彩无限数码科技有限公司
 */
package com.jcwx.game.common;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * 
 */
public class ScheduleThreadPool {
    private static Logger logger = LoggerFactory
	    .getLogger(ScheduleThreadPool.class);
    // 处理线程池
    private static ExecutorService pool = Executors.newFixedThreadPool(30);

    public static void execute(Runnable run) {
	try {
	    pool.execute(run);
	} catch (Exception e) {
	    logger.error("");
	}
    }

    public static void shutdown() throws InterruptedException {
	List<Runnable> runs = pool.shutdownNow();
	if (runs != null) {
	    logger.info("线程池正在执行，大小：" + runs.size());
	}
	pool.shutdown();
	while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
	}
	logger.info("线程池已关闭!");
    }

}
