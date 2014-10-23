package com.jcwx.webserver;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.webapp.WebAppContext;

public class FengmoOss {
    public static void main(String[] args) throws Exception {

	Server server = new Server(9000); // 声明端口

	WebAppContext context = new WebAppContext(); // 声明上下文对象
	
	context.setCopyWebDir(true);

	context.setDefaultsDescriptor( "webdefault.xml" );
	
	context.setDescriptor("src/main/webapp/WEB-INF/web.xml"); // 指定web.xml文件，可选

	context.setResourceBase("src/main/webapp"); // 设置项目路径

	context.setClassLoader(Thread.currentThread().getContextClassLoader());
	
	context.setConfigurationDiscovered(true);
	context.setParentLoaderPriority(true);

	HashSessionManager sessionManager = new HashSessionManager();
	sessionManager.setStoreDirectory(new File("target/here"));
	sessionManager.setIdleSavePeriod(1);

	sessionManager.setSessionIdManager(new HashSessionIdManager());

	context.setSessionHandler(new SessionHandler(sessionManager));
	
	server.setHandler(context); // 设置句柄

	server.start(); // 启动

	server.join();

    }
}
