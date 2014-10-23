package com.jcwx.game.util;

import com.jcwx.game.common.SpringService;
import com.jcwx.game.service.oss.IOssServerService;
import com.jcwx.game.service.oss.ISystemNoticeService;

public abstract class SpringBeansUtil {

    protected static IOssServerService ossServerService = (IOssServerService) SpringService
	    .getBean("ossServerService");

    protected static ISystemNoticeService systemNoticeService = (ISystemNoticeService) SpringService
	    .getBean("systemNoticeService");
}
