package com.jcwx.game.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.constant.AdminSystemConstant;
import com.jcwx.game.admin.constant.FastjsonConstant;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.common.OssContext;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.Project;
import com.jcwx.game.exception.GameException;
import com.jcwx.game.service.oss.IOssServerService;
import com.jcwx.game.service.oss.impl.OssLogService;
import com.jcwx.game.util.transdata.ITransfer;
import com.jcwx.game.util.transdata.ITransfer.TransferHandler;

public class ConnectionUtil {
    /** 辅助服务器默认端口 */
    private static final Integer ASSIST_PORT = 10086;

    /**
     * 是否加密
     */
    private static final boolean IS_CRYPT = false;

    private static final Logger logger = LoggerFactory
	    .getLogger(ConnectionUtil.class);
    @Autowired
    static OssLogService ossLogService = SpringService
	    .getBean(OssLogService.class);

    public static String getContentTypeString(OssServer ossServer)
	    throws MalformedURLException, IOException, Exception {
	return getContentTypeString(new URL(ossServer.getUrl()));
    }

    /***
     * 得到服务器的Content-type
     * 
     * @param url
     * @return
     * @throws Exception
     */
    public static String getContentTypeString(URL url) throws Exception {
	HttpURLConnection urlConnection = (HttpURLConnection) url
		.openConnection();
	urlConnection.addRequestProperty(AdminSystemConstant.CONTENT_TYPE, AdminSystemConstant.CONTENT_JSON);

	urlConnection.setConnectTimeout(5000);
	urlConnection.setUseCaches(false);
	if (urlConnection.getResponseCode() == HttpServletResponse.SC_OK) {
	    return urlConnection.getContentType();
	}
	throw new Exception("do not get contentType!");
    }

    /**
     * 发送 请求，给外部接口调用
     * 
     * @param baseAdminContext
     * @param serverId
     * @param paraMap
     * @return
     * @throws Exception
     */
    public static Map<String, Object> interfaceSendMsg(Integer serverId,
	    Map<String, Object> paraMap) throws Exception {

	IOssServerService ossServerService = (IOssServerService) SpringService
		.getBean("ossServerService");
	OssServer ossServer = ossServerService.getOssServerByID(serverId);
	if (ossServer == null)
	    throw new GameException("没有该服务器");
	try {
	    return sendMsg(
		    ossServer.getUrl(),
		    ossServer.getCommunicateKey(),
		    ITransfer.ContentTypeEnum.convertToContentType(
			    getContentTypeString(ossServer)).getContentType(),
		    paraMap);
	} catch (ConnectException e) {
	    throw new GameException("网络连接失败,请检查目标机器", e);
	} catch (Exception e) {
	    ossLogService.createOssLog(OssLogConstant.ERROR_SERVER,
		    e.getMessage());
	    throw e;
	}
    }

    /**
     * 判断是否能够连接
     * 
     * @param ossServer
     * @return
     */
    public static boolean isConnection(OssServer ossServer) {
	try {
	    getContentTypeString(ossServer);
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    public static boolean isConnection(URL url) {
	try {
	    getContentTypeString(url);
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    /**
     * 发送 请求，自动判断是否有权限
     * 
     * @param serverId
     * @param paraMap
     * 
     * @return
     * @throws Exception
     */
    public static Map<String, Object> sendMsg(Integer serverId,
	    Map<String, Object> paraMap) throws Exception {
	return sendMsg(serverId, OssContext.getBaseAdminContext()
		.getContentType(), paraMap);
    }

    /**
     * 发送 请求，自动判断是否有权限
     * 
     * @param serverId
     * @param contentType
     *            TODO
     * @param paraMap
     * @return
     * @throws Exception
     */
    public static Map<String, Object> sendMsg(Integer serverId,
	    String contentType, Map<String, Object> paraMap) throws Exception {

	OssServer ossServer = OssContext.getBaseAdminContext()
		.getOssServerById(serverId);

	return sendMsg(ossServer, contentType, paraMap);
    }

    /**
     * 发送消息给指定服务器,从服务器返回数据结果。
     * 
     * @param paraMap
     *            要调用的handlerName,methodName,以及其他参数
     * @return 服务器返回的Map数据。
     * @throws Exception
     *             服务器可能没有打开，而造成的ConnectException, SocketTimeoutException,
     *             也有因为服务器根本没有相关类，或者服务器执行错误而造成的错误，会返回，并且抛出错误。 最后封装成GameException
     */
    public static Map<String, Object> sendMsg(Map<String, Object> paraMap)
	    throws Exception {
	return sendMsg(OssContext.getBaseAdminContext().getContentType(),
		paraMap);
    }

    public static Map<String, Object> sendMsg(OssServer ossServer,
	    String contentType, Map<String, Object> paraMap) throws Exception {
	Validate.notNull(ossServer, "没有权限");
	try {
	    return sendMsg(ossServer.getUrl(), ossServer.getCommunicateKey(),
		    contentType, paraMap);
	} catch (ConnectException e) {
	    throw new GameException("网络连接失败,请检查目标机器", e);
	} catch (Exception e) {
	    ossLogService.createOssLog(OssLogConstant.ERROR_SERVER,
		    e.getMessage());
	    throw e;
	}
    }

    /**
     * 发送 请求，自动判断是否有权限
     * 
     * @param paraMap
     * @param serverId
     * @return
     * @throws Exception
     */
    public static Map<String, Object> sendMsg(String contentType,
	    Map<String, Object> paraMap) throws Exception {

	OssServer ossServer = OssContext.getBaseAdminContext()
		.getCurrentOssServer();

	return sendMsg(ossServer, contentType, paraMap);
    }

    public static Map<String, Object> sendMsg(String address, String key,
	    Map<String, Object> paraMap) throws Exception {
	if (org.apache.commons.lang3.StringUtils.isEmpty(OssContext
		.getBaseAdminContext().getContentType())) {
	    OssContext.getBaseAdminContext().setContentType(
		    getContentTypeString(new URL(address)));
	}
	return sendMsg(address, key, OssContext.getBaseAdminContext()
		.getContentType(), paraMap);
    }

    public static Map<String, Object> sendMsg(String address, String key,
	    String contentType, Map<String, Object> paraMap) throws Exception {
	
	Validate.notNull(contentType, "Content-type must not be null!");
	Validate.isTrue(MapUtils.isNotEmpty(paraMap),"paraMap must not be null!");
//	if(OssContext.getBaseAdminContext().getProject().getProjectId()==Project.projectId_嘟嘟熊){
//	    address = address+paraMap.get("handlerName")+".action";
//		    String paramString = JSON.toJSONString(paraMap,
//			    FastjsonConstant.getSerializeConfig(),
//			    FastjsonConstant.getSerializerFeatures());
//			    
//		   address += "?param="+URLEncoder.encode(paramString,"UTF-8");
//	}
	ITransfer transfer = TransferHandler.getTransfer(contentType);
	URL url;
	InputStream inputStream = null;
	HttpURLConnection urlConnection = null;
	OutputStream outputStream = null;

	try {
	    // logger.info("url:"+baseAdminContext.getCurrentOssServer().getUrl()+",handlerName:"+paraMap.get("handlerName")+",jsonObject:"+paraMap.toString());
	    // logger.info("handlerName:"+paraMap.get("handlerName")+",jsonObject:"+paraMap.toString());
	    url = new URL(address);
	    urlConnection = (HttpURLConnection) url.openConnection();
	    // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
	    // http正文内，因此需要设为true, 默认情况下是false;
	    urlConnection.setDoOutput(true);
	    // 设置是否从httpUrlConnection读入，默认情况下是true;
	    urlConnection.setDoInput(true);
	    // Post 请求不能使用缓存
	    urlConnection.setUseCaches(false);
	    // 设定传送的内容类型是可序列化的java对象
	    // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
	    // urlConnection.setRequestProperty("Content-type",
	    // "application/octet-stream");
	    urlConnection.setRequestProperty(AdminSystemConstant.CONTENT_TYPE, contentType);
	    // 设置连接主机超时
	    urlConnection.setConnectTimeout(10000);// 10s
	    // 从主机读取数据超时
	    urlConnection.setReadTimeout(30000000);
	    outputStream = urlConnection.getOutputStream();

	    transfer.writeObject(outputStream, paraMap, key);

	    int size = 0;
	    int responseCode = urlConnection.getResponseCode();
	    if (responseCode == HttpServletResponse.SC_OK) {
		size = urlConnection.getContentLength();
		// 查看http头文件，在游戏中规定失败会把信息放在头里
		String herror = urlConnection.getHeaderField("errorMsg");
		if (herror != null) {
		    throw new GameException("请求失败:游戏服务器head:error:" + herror);
		}
		inputStream = urlConnection.getInputStream();

		Map<String, Object> resultMap = null;// transfer.readObject(inputStream);
		/** 根据服务器返回的头部，errorClass={true,false}判断是读取Object还是Exception */

		String errorClass = urlConnection.getHeaderField("errorClass");
		if (transfer.isException(inputStream, errorClass)) {
		    throw transfer.readException(inputStream);
		} else {
		    resultMap = transfer.readObject(inputStream);
		}

		resultMap.put("contentLength", size);// 数据长度，用于分析
		return resultMap;
	    } else {
		logger.error("address:" + address + ";responseCode:"
			+ responseCode);
	    }

	} catch (JSONException jsonE) {
	    /** 服务器发生JSON传递错误时，记录在日志中，用以修复 */
	    logger.error(jsonE.getMessage(), jsonE);
	} catch (java.net.ConnectException e) {
	    throw new GameException("网络连接失败,请检查目标机器", e);
	} catch (java.net.SocketTimeoutException e2) {
	    throw new GameException("网络连接失败,请检查目标机器", e2);
	} catch (Exception e) {
	    throw new GameException(e);

	} finally {
	    IOUtils.closeQuietly(outputStream);
	    IOUtils.closeQuietly(inputStream);
	    if (urlConnection != null) {
		urlConnection.disconnect();
	    }

	}
	return null;
    }

    public static void setContentTypesendMsg(String address, String key) {
	try {
	    URL url = new URL(address);
	    HttpURLConnection urlConnection = (HttpURLConnection) url
		    .openConnection();

	    urlConnection = (HttpURLConnection) url.openConnection();

	    urlConnection.setConnectTimeout(3000);
	    urlConnection.setUseCaches(false);
	    if (urlConnection.getResponseCode() == HttpServletResponse.SC_OK) {
		String contentType = urlConnection.getContentType();
		if (StringUtils.isBlank(contentType)) {
		    OssContext.getBaseAdminContext().setContentType(
			    ITransfer.ContentTypeEnum.STREAM.getContentType());
		} else {
		    OssContext.getBaseAdminContext().setContentType(
			    ITransfer.ContentTypeEnum.JSON.getContentType());
		}

	    }
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
