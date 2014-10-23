package com.jcwx.game.dao;

import java.util.List;

import com.jcwx.game.domain.Message;

public interface IMessageDAO {

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

    /**
     * 修改Message
     * 
     * @param message
     * @return 数据影响条数
     */
    public Integer updateMessage(Message message);
}
