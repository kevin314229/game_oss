package com.jcwx.game.web;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.jcwx.game.common.GameSchedule;
import com.jcwx.game.common.ScheduleThreadPool;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.common.SystemGlobal;
import com.jcwx.game.util.ThreadLocalCleanUtil;

public class InitSystemListener implements ServletContextListener {
    private static Logger logger = Logger.getLogger(InitSystemListener.class);
    // FlashSecurityXMLSocketServer flashSecurityXMLSocketServer = null;

    public static int SYSTEM_OPEN_FLAG = 0;

    public static String getDateStr() {
	Calendar now = Calendar.getInstance();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	return df.format(now.getTime());
    }

    // private static Logger logger =
    // Logger.getLogger(InitSystemListener.class);
    GameSchedule gameSchedule = null;

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
	// 处理服务器关闭时玩家的处理
	// IPlayerService playerService =
	// (IPlayerService)SpringService.getBean("playerService");
	// playerService.handleServerShutdown();
	SYSTEM_OPEN_FLAG = 1;

	// //更新服务器关闭日期
	// ISystemParamService systemService =
	// (ISystemParamService)SpringService.getBean("systemParamService");
	// systemService.updateSystemParam("SYSTEM_CLOSE_DATE", getDateStr());

	try {
	    /** 重新部署时，关掉线程 */
	    ScheduleThreadPool.shutdown();
	    /** Web服务器关闭时，终端线程 */

	} catch (InterruptedException e) {
	    logger.error("destory webapp error:" + e.getMessage(), e);
	}

	try {
	    if(gameSchedule!=null){
		gameSchedule.interrupted();
	    }
	} catch (Exception e) {
	    logger.error("gameSchedule.interrupted:" + e.getMessage(), e);
	}

	destoryDriver();

	LogFactory.release(Thread.currentThread().getContextClassLoader());

	ThreadLocalCleanUtil.clearThreadLocals();

	logger.info("停止web服务...");
	logger.info("保存数据库...");
	logger.info("保存数据库OK...");

	// 关闭聊天Socket
	// gameSocketServer.shutdown();
    }

    /*
     * public static boolean needQuartz(){ SystemParam sp =
     * Global.get(Global.SYSTEM_PARAM, "SYSTEM_CLOSE_DATE", SystemParam.class );
     * String lastCloseDate = sp.getSystemParamValue();
     * 
     * if ( lastCloseDate!=null && !"0".equals(lastCloseDate) &&
     * !getDateStr().equals(lastCloseDate)) { return true; } return false; }
     */

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
	ServletContext servletContext = servletContextEvent.getServletContext();

	// 初始化SpringService.servletContext
	SpringService.setServletContext(servletContext);

	// TODO ..每次打包前放开begin
	gameSchedule = new GameSchedule();
	// 初始化全局缓存
	SystemGlobal.init();
	// TODO ..每次打包前放开end

	// 初始化系统参数
	// SystemConfig.initSystemConfigParams();

	// 获取服务器的基础数据-道具&装备
	// Global.init();

	// //初始化全局缓存
	// Global g;

	// //
	// // if ( needQuartz() ) {
	// // System.out.println("隔日重启，需要调度:");
	// // QuartzUtil.quartzEveryDayZero();
	// // }
	//
	// // 开启游戏Socket
	// gameSocketServer = new GameSocketServer();
	// ServletContext servletContext =
	// servletContextEvent.getServletContext();
	//
	// // 初始化SpringService.servletContext
	// SpringService.setServletContext(servletContext);
	//
	// // 初始化系统参数
	// SystemConfig.initSystemConfigParams();

	// 初始化全局缓存
	// Global g;

	//
	// if ( needQuartz() ) {
	// System.out.println("隔日重启，需要调度:");
	// QuartzUtil.quartzEveryDayZero();
	// }

	// 开启游戏Socket
	// gameSocketServer = new GameSocketServer();

    }

    private void destoryDriver() {
	Enumeration<Driver> drivers = DriverManager.getDrivers();
	Driver d = null;
	while (drivers.hasMoreElements()) {
	    try {
		d = drivers.nextElement();
		DriverManager.deregisterDriver(d);
		logger.warn(String.format("Driver %s deregistered", d));
	    } catch (SQLException ex) {
		logger.warn(String.format("Error deregistering driver %s", d),
			ex);
	    }
	}
	Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
	Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
	for (Thread t : threadArray) {
	    if (t.getName().contains("Abandoned connection cleanup thread")) {
		synchronized (t) {
		    t.stop(); // don't complain, it works
		}
	    }
	}
	logger.warn("卸载JDBC驱动成功!");
    }

}
