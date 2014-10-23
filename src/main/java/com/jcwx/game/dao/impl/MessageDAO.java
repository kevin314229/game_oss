package com.jcwx.game.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.dao.IMessageDAO;
import com.jcwx.game.domain.Message;

@Repository
public class MessageDAO implements IMessageDAO {
    @Autowired
    private IBaseDAO baseDao;

    @Override
    public Integer createMessage(Message message) {
	// TODO Auto-generated method stub
	baseDao.insert("Message.createMessage", message);
	return null;
    }

    @Override
    public Integer deleteMessageByID(String messageId) {
	// TODO Auto-generated method stub
	baseDao.delete("Message.deleteMessageByID", messageId);
	return null;
    }

    @Override
    public Message getMessageByID(String messageId) {
	// TODO Auto-generated method stub
	return (Message) baseDao.queryForObject("Message.getMessageByID",
		messageId);
    }

    @Override
    public List<Message> getMessageList() {
	// TODO Auto-generated method stub
	return baseDao.queryForList("Message.getMessageList");
    }

    @Override
    public List<Message> getMessageListByPlayerId(String receiveId) {
	// TODO Auto-generated method stub
	return baseDao.queryForList("Message.getMessageListByPlayerId",
		receiveId);
    }

    @Override
    public List<Message> getPlayerMessageList(String receiveId) {
	// TODO Auto-generated method stub
	return baseDao.queryForList("Message.getMessageListByPlayerId",
		receiveId);
    }

    @Override
    public Integer updateMessage(Message message) {
	// TODO Auto-generated method stub
	return baseDao.update("Message.updateMessage", message);
    }

}
