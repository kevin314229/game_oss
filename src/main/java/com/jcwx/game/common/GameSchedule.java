package com.jcwx.game.common;

import java.util.Iterator;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 游戏调度类，采用延迟队列DelayQueue实现定时器功能，主要用于扫荡,修炼,缓存删除，怪物AI等定时功能
 * 
 * @author 500
 * 
 */
public class GameSchedule {
    private static Logger logger = LoggerFactory.getLogger(GameSchedule.class);

    private static final DelayQueue<DelayItem<Runnable>> q = new DelayQueue<DelayItem<Runnable>>();

    /**
     * 向延迟队列插入一个对象
     * 
     * @param run
     * @param time
     * @param unit
     * @Added by
     */
    public static void put(Runnable run, long time, TimeUnit unit) {
	long nanoTime = TimeUnit.NANOSECONDS.convert(time, unit);
	q.put(new DelayItem<Runnable>(run, nanoTime));
    }

    /**
     * 删除延迟队列中的对象，不管是否过期
     * 
     * @param run
     * @return
     * @Added by
     */
    public static boolean remove(Runnable run) {

	DelayItem<Runnable> temp = null;
	Iterator<DelayItem<Runnable>> t = q.iterator();

	while (t.hasNext()) {
	    DelayItem<Runnable> t1 = t.next();
	    if (t1.getItem() == run) {
		temp = t1;
		break;
	    }
	}
	if (temp != null) {
	    return q.remove(temp);
	}
	return false;
    }

    // 调度线程
    private Thread handleThread;
    // //处理线程池
    // private static ExecutorService pool = Executors.newFixedThreadPool( 50 );

    private boolean isAlive = true;

    public GameSchedule() {
	Runnable daemonTask = new Runnable() {
	    @Override
	    public void run() {
		handle();
	    }
	};

	handleThread = new Thread(daemonTask);
	handleThread.start();
    }

    @SuppressWarnings("static-access")
    private void handle() {
	while (isAlive) {
	    // logger.info("Before Take, Queue=size:"+q.size());
	    try {
		DelayItem<Runnable> delayItem = q.take();
		// logger.info("After Take, Queue=size:"+q.size());
		if (delayItem != null) {
		    ScheduleThreadPool.execute(delayItem.getItem());
		}

		// logger.info("After Execute, Queue=size:"+q.size());
		// Runnable run = delayItem.getItem();
		// pool.execute( run );
	    } catch (Exception e) {
		logger.error("Schedule Runnable, Delay Queue size:" + q.size());
		logger.error("Schedule Runnable Error:" + e);
		try {
		    Thread.currentThread();
		    Thread.sleep(200);
		} catch (InterruptedException e1) {
		    logger.error(
			    "==================================GameSchedule is Down.",
			    e1);
		}
	    }
	}
    }

    public void interrupted() {
	isAlive = false;
	handleThread.interrupt();
    }
}

// public static void main(String[] args) throws Exception {
// // CopyRunnable run1 = new CopyRunnable("hello",4,4);
// // CopyRunnable run2 = new CopyRunnable("world",6,3);
// // GameSchedule.put( run1, run1.getInterval(), TimeUnit.SECONDS);
// // GameSchedule.put( run2, run2.getInterval(), TimeUnit.SECONDS);
// //
// // while (true) {
// // Thread.currentThread().sleep(1000);
// // }
// }
