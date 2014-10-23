package com.jcwx.game.web;

import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcwx.game.common.SpringService;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.Project;
import com.jcwx.game.http.domain.MallActivityProperty;
import com.jcwx.game.http.domain.OssChildActivity;
import com.jcwx.game.http.domain.SendBaseProperty;
import com.jcwx.game.service.oss.IOssServerService;
import com.jcwx.game.util.AES;
import com.jcwx.game.util.Connection;


@SuppressWarnings("unused")
public class Global {
    /**2014-08-18 刘聪,谷常青 提出去掉28开头的ID号*/
    private static final String TEMP_START_FILTER = "28";
    // private static final Map<String,Map<String,List<SendBaseProperty>>>
    // global = new HashMap<String, Map<String,List<SendBaseProperty>>>();
    private static final Map<String, Object> global = new HashMap<String, Object>();
    
    private static final Logger logger = LoggerFactory.getLogger(Global.class);

    public static List<SendBaseProperty> getEquipList(String url) {
	if (global.containsKey(url)) {
	    Map<String, Object> propertyMap = (Map<String, Object>) global
		    .get(url);
	    return (List<SendBaseProperty>) propertyMap.get("equipList");
	}
	return null;
    }

    /**
     * 获取 商城缓存
     * 
     * @param url
     * @return
     */
    public static List<MallActivityProperty> getMallActivityList(String url) {
	if (global.containsKey(url)) {
	    Map<String, Object> propertyMap = (Map<String, Object>) global
		    .get(url);
	    return (List<MallActivityProperty>) propertyMap
		    .get("mallActivityList");
	}
	return null;
    }

    /**
     * 获取 商城缓存
     * 
     * @param url
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<OssChildActivity> getOssChildActivityList(String url) {
	if (global.containsKey(url)) {
	    Map<String, Object> propertyMap = (Map<String, Object>) global
		    .get(url);
	    return (List<OssChildActivity>) propertyMap.get("activityList");
	}
	return null;
    }

    @SuppressWarnings("unchecked")
    public static List<SendBaseProperty> getPropertyList(String url,final Integer gameId) {
	if (global.containsKey(url)) {
	    Map<String, Object> propertyMap = (Map<String, Object>) global
		    .get(url);
	    List<SendBaseProperty> baseProperties = (List<SendBaseProperty>) propertyMap.get("propertyList");
	    CollectionUtils.filter(baseProperties, new BeanPredicate("value", new Predicate() {
	        
	        @Override
	        public boolean evaluate(Object object) {
	            String pro = String.valueOf(object);
	            if(pro.startsWith(TEMP_START_FILTER)&&gameId==Project.projectId_战魂西游){
	        	return false;
	            }
	    		return true;
	        }
	    }));
	    return baseProperties;
	}
	return null;
    }

    /**
     * 启动时初始化所有服务器数据
     */
    @SuppressWarnings("unchecked")
    public static void init() {
	IOssServerService ossServerService = (IOssServerService) SpringService
		.getBean("ossServerService");
	List<OssServer> ossServers = ossServerService.findAllOssServer();
	for (OssServer ossServer : ossServers) {
	    try {
		if (ossServer.getKey() == null) {
		    AES.generateKey(ossServer);
		}
		URL url = new URL(ossServer.getUrl());
		URLConnection urlConnection = url.openConnection();
		// 设置连接主机超时
		urlConnection.setConnectTimeout(5000);
		urlConnection.connect();
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("handlerName", "SendPropertyToPlayerHandler");
		object = Connection.getInstance().sendMsg(ossServer.getUrl(),
			ossServer.getCommunicateKey(), object);
		List<SendBaseProperty> propertyList = (List<SendBaseProperty>) object
			.get("allBaseProperty");
		List<SendBaseProperty> equipList = (List<SendBaseProperty>) object
			.get("allBaseEquip");
		// 活动商城商品列表
		List<MallActivityProperty> mallActivityList = (List<MallActivityProperty>) object
			.get("allBaseMall");
		// 系统活动 小活动
		List<OssChildActivity> ossChildActivityList = (List<OssChildActivity>) object
			.get("allBasechildActivity");

		Map<String, Object> sendMap = new HashMap<String, Object>();
		if (propertyList != null) {
		    sendMap.put("propertyList", propertyList);
		}
		if (equipList != null) {
		    sendMap.put("equipList", equipList);
		}
		if (mallActivityList != null) {
		    sendMap.put("mallActivityList", mallActivityList);
		}
		if (ossChildActivityList != null) {
		    sendMap.put("activityList", ossChildActivityList);
		}
		Map<Integer, Object> sendBase = new HashMap<Integer, Object>();
		for(SendBaseProperty sendBaseProperty:propertyList){
		    sendBase.put(sendBaseProperty.getValue(), sendBaseProperty.getName());
		}
		for(SendBaseProperty sendBaseProperty:equipList){
		    sendBase.put(sendBaseProperty.getValue(), sendBaseProperty.getName());
		}
		global.put(ossServer.getUrl()+"Map", sendBase);
		global.put(ossServer.getUrl(), sendMap);
		logger.info("init property success!server:"
			+ ossServer.getName() + ",url:" + ossServer.getUrl());
	    } catch (Exception e) {
		logger.error("can not connection server:" + ossServer.getName()
			+ ",url:" + ossServer.getUrl());
	    }
	}
    }

    public static void initFengMoOssServer(OssServer ossServer) {

	try {

	    synchronized (ossServer) {

		if (global.get(ossServer) != null) {
		    return;
		}
		// URL url = new URL(ossServer.getUrl());
		// URLConnection urlConnection =
		// (HttpURLConnection)url.openConnection();
		// // 设置连接主机超时
		// urlConnection.setConnectTimeout(50000);
		// urlConnection.connect();
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("handlerName", "SendPropertyToPlayerHandler");
		if (ossServer.getKey() == null) {
		    // AES.generateKey(ossServer); //不用这个了
		}

		object = Connection.getInstance().sendMsg(ossServer.getUrl(),
			ossServer.getCommunicateKey(), object);
		List<SendBaseProperty> propertyList = (List<SendBaseProperty>) object
			.get("allBaseProperty");
		List<SendBaseProperty> equipList = (List<SendBaseProperty>) object
			.get("allBaseEquip");
		// 活动商城商品列表
		List<MallActivityProperty> mallActivityList = (List<MallActivityProperty>) object
			.get("allBaseMall");
		// 系统活动 小活动
		List<OssChildActivity> ossChildActivityList = (List<OssChildActivity>) object
			.get("allBasechildActivity");

		Map<String, Object> sendMap = new HashMap<String, Object>();
		if (propertyList != null) {
		    // 排序一下
		    Collections.sort(propertyList,
			    new Comparator<SendBaseProperty>() {
				@Override
				public int compare(SendBaseProperty p2,
					SendBaseProperty p1) {
				    return p2.getValue().compareTo(
					    p1.getValue());
				}
			    });

		    sendMap.put("propertyList", propertyList);
		}
		if (equipList != null) {
		    // 排序一下
		    Collections.sort(equipList,
			    new Comparator<SendBaseProperty>() {
				@Override
				public int compare(SendBaseProperty p2,
					SendBaseProperty p1) {
				    return p2.getValue().compareTo(
					    p1.getValue());
				}
			    });
		    sendMap.put("equipList", equipList);
		}
		if (mallActivityList != null) {
		    sendMap.put("mallActivityList", mallActivityList);
		}
		if (ossChildActivityList != null) {
		    sendMap.put("activityList", ossChildActivityList);
		}
		global.put(ossServer.getUrl(), sendMap);
		Map<Integer, Object> sendBase = new HashMap<Integer, Object>();
		for(SendBaseProperty sendBaseProperty:propertyList){
		    sendBase.put(sendBaseProperty.getValue(), sendBaseProperty.getName());
		    System.err.println(sendBaseProperty.getName()+":"+sendBaseProperty.getQuality());
		}
		for(SendBaseProperty sendBaseProperty:equipList){
		    System.err.println(sendBaseProperty.getName()+":"+sendBaseProperty.getQuality());
		    sendBase.put(sendBaseProperty.getValue(), sendBaseProperty.getName());
		}
		global.put(ossServer.getUrl()+"Map", sendBase);
		logger.info("init property success!server:"
			+ ossServer.getName() + ",url:" + ossServer.getUrl());
	    }
	} catch (Exception e) {
	    logger.error("can not connection server:" + ossServer.getName()
		    + ",url:" + ossServer.getUrl());
	    logger.error(e.getMessage());
	}

    }

    /**
     * 连接时初始化单个OSS数据
     */
    @SuppressWarnings("unchecked")
    public static void initOssServer(OssServer ossServer) {

	if (ossServer.getProjectId().intValue() == Project.projectId_封魔游戏
		|| ossServer.getProjectId().intValue() == Project.projectId_战魂西游) {
	    // 初始化封魔或者 战魂西游 游戏缓存
	    initFengMoOssServer(ossServer);
	}
	if (ossServer.getProjectId().intValue() == Project.projectId_裁决王座) {
	    // 初始化裁决王座游戏缓存

	}

    }

    /**
     * 服务器是否开启
     * 
     * @param ossServer
     */
    public static boolean isOpen(OssServer ossServer) {
	return global.get(ossServer.getUrl()) != null;
    }

    private List<SendBaseProperty> equipList; // 装备

    private List<MallActivityProperty> MallActivityList;

    private List<SendBaseProperty> propertyList; // 道具

    private Global() {
    }
    public static Map<Integer, Object> getSendBaseProperties(String url) {
   	if (global.containsKey(url+"Map")) {
   	    Map<Integer, Object> propertyMap = (Map<Integer, Object>) global
   		    .get(url+"Map");
   	    return propertyMap;
   	}
   	return null;
       }
}
