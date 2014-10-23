package com.jcwx.game.service;

import java.util.List;

import com.jcwx.game.domain.Message;

/**
 * 消息Service接口
 * 
 * @author derek
 * @version 1.0
 */

public interface IMessageService {
    /**
     * 创建Message
     * 
     * @param message
     * @return 数据影响条数
     */
    public Integer createMessage(Message message);

    /**
     * 通过主键ID删除Message
     * 
     * @param messageId
     * @return 数据影响条数
     */
    public Integer deleteMessageByID(String messageId);

    /**
     * 通过主键ID查询Message
     * 
     * @param messageId
     * @return Message
     */
    public Message getMessageByID(String messageId);

    // 查询所有邮件
    public List<Message> getMessageList();

    /**
     * 查询所有的Message
     * 
     * @return Message的集合
     */
    public List<Message> getMessageListByPlayerId(String receiveId);

    /**
     * 查询玩家发送的Message
     */
    public List<Message> getPlayerMessageList(String receiveId);

    public void sendMessageToAllPlayer(String msgTitle, String msgContent);

    public Integer sendPlayerMessage(int type, String playerName,
	    String msgTitle, String msgContent);

    /**
     * 修改Message
     * 
     * @param message
     * @return 数据影响条数
     */
    public Integer updateMessage(Message message);
}
