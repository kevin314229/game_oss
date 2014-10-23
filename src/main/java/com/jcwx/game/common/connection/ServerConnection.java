package com.jcwx.game.common.connection;

import java.util.Map;

import com.jcwx.game.common.connection.message.Message;

public interface ServerConnection {
    /**
     * 发送 请求，自动判断是否有权限 发送消息给指定服务器,从服务器返回数据结果。
     * 
     * @param serverId
     *            指定服务器ID
     * @param paraMap
     *            要调用的handlerName,methodName,以及其他参数
     * @return 服务器返回的Map数据。
     * @throws Exception
     *             服务器可能没有打开，而造成的ConnectException, SocketTimeoutException,
     *             也有因为服务器根本没有相关类，或者服务器执行错误而造成的错误，会返回，并且抛出错误。 最后封装成GameException
     */
    Map<String, Object> sendMsg(Integer serverId, Map<String, Object> paraMap)
	    throws Exception;

    /**
     * 发送消息给当前选择服务器,从服务器返回数据结果。
     * 
     * @param baseAdminContext
     *            上下文信息，当前服务器信息，加密等信息。
     * @param paraMap
     *            要调用的handlerName,methodName,以及其他参数
     * @return 服务器返回的Map数据。
     * @throws Exception
     *             服务器可能没有打开，而造成的ConnectException, SocketTimeoutException,
     *             也有因为服务器根本没有相关类，或者服务器执行错误而造成的错误，会返回，并且抛出错误。 最后封装成GameException
     */
    Map<String, Object> sendMsg(Map<String, Object> paraMap) throws Exception;

    Map<String, Object> sendMsg(Message message) throws Exception;

    /**
     * 根据地址address发送消息。
     * 
     * @param address
     *            服务器地址，如:192.168.1.250:6088
     * @param key
     *            加密key
     * @param paraMap
     *            要调用的handlerName,methodName,以及其他参数
     * @return 服务器返回的Map数据。
     * @throws Exception
     *             服务器可能没有打开，而造成的ConnectException, SocketTimeoutException,
     *             也有因为服务器根本没有相关类，或者服务器执行错误而造成的错误，会返回，并且抛出错误。 最后封装成GameException
     */
    Map<String, Object> sendMsg(String address, String key,
	    Map<String, Object> paraMap) throws Exception;

}
