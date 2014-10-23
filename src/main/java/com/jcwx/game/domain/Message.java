package com.jcwx.game.domain;

import java.util.Date;

import com.jcwx.game.common.domain.BaseDomain;

public class Message extends BaseDomain {

    /** 邮件内容 */
    private String content;
    /**  */
    private String messageId;
    /** 邮费 */
    private Integer msgCost;
    /** 邮件物品ID列表，以#分隔 */
    private String msgItem;
    /** 是否已读 0-未读 1-已读 */
    private Integer msgRead;
    /** 邮件类型 0-系统 1-玩家 */
    private Integer msgType;
    /** 接受者ID */
    private String receiveId;
    /** 发送者昵称 */
    private String sender;
    /** 邮件发送时间 */
    private Date sendTime;
    /** 邮件标题 */
    private String title;

    public Message() {
    }

    public Message(String messageId, String receiveId, String sender,
	    String title, String content, Integer msgType, Integer msgCost,
	    String msgItem, Date sendTime, Integer msgRead) {
	this.messageId = messageId;
	this.receiveId = receiveId;
	this.sender = sender;
	this.title = title;
	this.content = content;
	this.msgType = msgType;
	this.msgCost = msgCost;
	this.msgItem = msgItem;
	this.sendTime = sendTime;
	this.msgRead = msgRead;
    }

    public String getContent() {
	exeGet();
	return content;
    }

    public String getMessageId() {
	exeGet();
	return messageId;
    }

    public Integer getMsgCost() {
	exeGet();
	return msgCost;
    }

    public String getMsgItem() {
	exeGet();
	return msgItem;
    }

    public Integer getMsgRead() {
	exeGet();
	return msgRead;
    }

    public Integer getMsgType() {
	exeGet();
	return msgType;
    }

    public String getReceiveId() {
	exeGet();
	return receiveId;
    }

    public String getSender() {
	exeGet();
	return sender;
    }

    public Date getSendTime() {
	exeGet();
	return sendTime;
    }

    public String getTitle() {
	exeGet();
	return title;
    }

    public void setContent(String content) {
	this.content = content;
	exeSet();
    }

    public void setMessageId(String messageId) {
	this.messageId = messageId;
	exeSet();
    }

    public void setMsgCost(Integer msgCost) {
	this.msgCost = msgCost;
	exeSet();
    }

    public void setMsgItem(String msgItem) {
	this.msgItem = msgItem;
	exeSet();
    }

    public void setMsgRead(Integer msgRead) {
	this.msgRead = msgRead;
	exeSet();
    }

    public void setMsgType(Integer msgType) {
	this.msgType = msgType;
	exeSet();
    }

    public void setReceiveId(String receiveId) {
	this.receiveId = receiveId;
	exeSet();
    }

    public void setSender(String sender) {
	this.sender = sender;
	exeSet();
    }

    public void setSendTime(Date sendTime) {
	this.sendTime = sendTime;
	exeSet();
    }

    public void setTitle(String title) {
	this.title = title;
	exeSet();
    }

}