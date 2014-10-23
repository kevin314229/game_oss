package com.jcwx.game.common.connection;

import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.jcwx.game.common.connection.message.Message;
import com.jcwx.game.util.Connection;


/***
 * 封魔服务端发送消息方式
 * 
 * @author Administrator
 * 
 */
@Component
public class FengmoConnection implements ServerConnection {

    private static final String DEFAULT_NOT_BLANK_EX_MESSAGE = "The validated handlerName is blank";

    @Override
    public Map<String, Object> sendMsg(Integer serverId,
	    Map<String, Object> paraMap) throws Exception {
	return getConnection().sendMsg(paraMap);
    }

    private Connection getConnection() {
	return  Connection.getInstance();
    }

    @Override
    public Map<String, Object> sendMsg(Map<String, Object> paraMap)
	    throws Exception {
	return getConnection().sendMsg(paraMap);
    }

    @Override
    public Map<String, Object> sendMsg(Message message) throws Exception {

	Validate.notBlank(message.getHandlerName(),
		DEFAULT_NOT_BLANK_EX_MESSAGE);

	return getConnection().sendMsg(message.getContent());
    }

    @Override
    public Map<String, Object> sendMsg(String address, String key,
	    Map<String, Object> paraMap) throws Exception {
	return getConnection().sendMsg(address, key, paraMap);
    }

}
