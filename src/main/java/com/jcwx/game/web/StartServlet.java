package com.jcwx.game.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.jcwx.game.common.JSONValidate;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.system.SystemInitial;

//import flex.messaging.FlexContext;

public class StartServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(StartServlet.class);

    private static final long serialVersionUID = 7710818052045484509L;

    /**
     * Constructor of the object.
     */
    public StartServlet() {
	super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    @Override
    public void destroy() {
	super.destroy(); // Just puts "destroy" string in log
	// Put your code here
    }

    /**
     * The doGet method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to get.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	this.doPost(request, response);
    }

    /**
     * The doPost method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to
     * post.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

    /**
     * Initialization of the servlet. <br>
     * 
     * @throws ServletException
     *             if an error occurs
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException,
	    UnavailableException {
	// 初始化服务器装备道具配置
	// Global.init();
	//初始化提示缓存
	InitHint.init();
	
	JSONValidate.initialFile();
	
	SystemInitial.initial();

	float fFreeMemory = Runtime.getRuntime().freeMemory();
	float fTotalMemory = Runtime.getRuntime().totalMemory();
	float maxMemory = Runtime.getRuntime().maxMemory();

	StringBuilder buf = new StringBuilder();
	buf.append("fFreeMemory:" + fFreeMemory / (1024 * 1024))
		.append(" fTotalMemory:" + fTotalMemory / (1024 * 1024))
		.append(" maxMemory:" + maxMemory / (1024 * 1024));

	logger.info("系统相关信息：" + buf.toString());

	logger.info("封魔oss启动...");

    }

}
