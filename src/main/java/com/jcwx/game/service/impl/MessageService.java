package com.jcwx.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IMessageDAO;
import com.jcwx.game.domain.Message;
import com.jcwx.game.service.IMessageService;

@Service
public class MessageService implements IMessageService {
    @Autowired
    private IMessageDAO messageDao;

    @Override
    public Integer createMessage(Message message) {
	// TODO Auto-generated method stub
	messageDao.createMessage(message);
	return null;
    }

    @Override
    public Integer deleteMessageByID(String messageId) {
	// TODO Auto-generated method stub
	messageDao.deleteMessageByID(messageId);
	return null;
    }

    @Override
    public Message getMessageByID(String messageId) {
	// TODO Auto-generated method stub
	return messageDao.getMessageByID(messageId);
    }

    @Override
    public List<Message> getMessageList() {
	// TODO Auto-generated method stub
	return messageDao.getMessageList();
    }

    @Override
    public List<Message> getMessageListByPlayerId(String receiveId) {
	// TODO Auto-generated method stub
	return messageDao.getMessageListByPlayerId(receiveId);
    }

    @Override
    public List<Message> getPlayerMessageList(String receiveId) {
	// TODO Auto-generated method stub
	return messageDao.getPlayerMessageList(receiveId);
    }

    @Override
    public void sendMessageToAllPlayer(String msgTitle, String msgContent) {
    }

    @Override
    public Integer sendPlayerMessage(int type, String playerName,
	    String msgTitle, String msgContent) {
	return 0;
    }

    @Override
    public Integer updateMessage(Message message) {
	// TODO Auto-generated method stub
	messageDao.updateMessage(message);
	return null;
    }

}
