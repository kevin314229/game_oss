package com.jcwx.game.system;

import jxl.common.Logger;

import com.jcwx.game.system.initial.InitialSystemSet;


public class SystemInitial{
    
    private static Logger logger = Logger.getLogger(SystemInitial.class);
    public static void initial() {
	try {
	    /**系统设置初始化*/
	    new InitialSystemSet().initial();
	} catch (Exception e) {
	    logger.error("系统初始化内容失败,自动退出",e);
	    
	}
    }

}
