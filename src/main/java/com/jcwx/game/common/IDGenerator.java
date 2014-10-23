package com.jcwx.game.common;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class IDGenerator {
    private static long curTime = 0;
    private static final Lock lock = new ReentrantLock();
    private static Logger logger = Logger.getLogger(IDGenerator.class);
    private static int roomNumber = 1;

    // /**
    // * 根据文件生成唯一整型ID
    // * 注：暂不用
    // * @return
    // */
    // public static int generateId() {
    // int id = 0;
    // lock.lock();
    // id = FileUtil.readWriteData();
    // lock.unlock();
    // if ( id == 0 ) throw new GameException("战斗ID生成错误");
    // return id;
    // }

    /**
     * 生成唯一String类型ID 生成规则： 表后缀_日期_随机数(0-99) 例子：20117_653265487542_2518
     * 
     * @return
     */
    // public static String generateId() {
    // lock.lock();
    // try {
    // String id = "";
    // long tmpTime = System.currentTimeMillis();
    // if ( tmpTime == curTime ) {
    // Thread.currentThread().sleep(1);
    // tmpTime = tmpTime+1;
    // curTime = tmpTime;
    // }
    // id = DateService.getTableSuffixByType( SystemConfig.battleLogTableSplit )
    // + "_" // 分表类型
    // + tmpTime + "_" // 系统时间
    // + (int) (Math.random() * 10000); // 4位数的随机数
    //
    // return id;
    // } catch (Exception e) {
    // logger.error( ResourceBundleService.getString("txt.exception"), e );
    // } finally {
    // lock.unlock();
    // }
    // return null;
    // }

    public static int generateNumber() {
	lock.lock();
	try {
	    long tmpTime = System.currentTimeMillis();
	    if (tmpTime == curTime) {
		Thread.currentThread();
		Thread.sleep(1);
		tmpTime = tmpTime + 1;
		curTime = tmpTime;
	    }
	    return roomNumber++;
	} catch (Exception e) {
	    logger.error(ResourceBundleService.getString("txt.exception"), e);
	} finally {
	    lock.unlock();
	}
	return 0;
    }

    public static void main(String[] args) {
	for (int i = 0; i < 100; i++) {
	    System.out.println(generateNumber());
	}
    }

}
