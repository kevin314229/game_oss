/*
 * MessageRunnable.java 
 * 创建于 2013-4-4
 * 
 * 版权所有@深圳市精彩无限数码科技有限公司
 */
package com.jcwx.game.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.SystemNotice;
import com.jcwx.game.util.Connection;
import com.jcwx.game.util.ConnectionUtil;
import com.jcwx.game.util.transdata.ITransfer;

/**
 * @author Administrator
 * 
 */
public class MessageRunnable implements Runnable {
    private static Logger logger = Logger.getLogger(MessageRunnable.class);

    // 系统消息Id
    private int systemNoticeId;

    public MessageRunnable(Integer systemNoticeId) {
	this.systemNoticeId = systemNoticeId;
    }

    public int getSystemNoticeId() {
	return systemNoticeId;
    }

    @Override
    public void run() {

	Map<Integer, SystemNotice> systemNoticesMap = SystemGlobal.get(
		SystemGlobal.SYSTEM_NOTICES_MAP, Map.class);
	SystemNotice systemNotice = systemNoticesMap.get(getSystemNoticeId());
	if (systemNotice != null) {
	    Map<Integer, OssServer> ossServerMap = SystemGlobal.get(
		    SystemGlobal.OSSSERVER_MAP, Map.class);
	    OssServer ossServer = ossServerMap.get(systemNotice.getServerId());

	    Map<String, Object> object = new HashMap<String, Object>();
	    object.put("systemMessage", systemNotice.getContent());
	    object.put("handlerName", "SendSysMsgHandler");
	    try {
		// 判断时间是否过期
		System.out.println(systemNotice.getNoticeTime().getTime()
			+ "---" + System.currentTimeMillis());

		if (systemNotice.getNoticeTime().getTime() > System
			.currentTimeMillis()) {
		    
		    object = ConnectionUtil.sendMsg(
			    ossServer.getUrl(),
			    ossServer.getCommunicateKey(),
			    ITransfer.ContentTypeEnum.convertToContentType(
				    Connection.getInstance()
					    .getContentTypeString(ossServer))
				    .getContentType(),object);
		    Object code = object.get("code");
		    if (code.toString().equals("0")) {
			logger.info("发送系统消息成功！ID为："
				+ systemNotice.getSystemNoticeId() + "内容为："
				+ systemNotice.getContent());
		    }
		}
	    } catch (Exception e) {
		logger.error("发送系统消息失败！ID为：" + systemNotice.getSystemNoticeId(),e);
		e.printStackTrace();
	    }
	    if (systemNotice.getNoticeTime().getTime() > System
		    .currentTimeMillis()) {
		GameSchedule.put(this, systemNotice.getDelay(),
			TimeUnit.MINUTES);
	    }
	}

    }

    public void setSystemNoticeId(int systemNoticeId) {
	this.systemNoticeId = systemNoticeId;
    }

}
