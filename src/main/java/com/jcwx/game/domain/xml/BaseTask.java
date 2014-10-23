package com.jcwx.game.domain.xml;

public class BaseTask {
    private String answerText; // 提交任务时NPC显示文本
    private Integer deliverNpcId;// 交任务NPC
    private String description;// 描述
    private Integer id;// 任务ID

    private String name;
    private String questionText; // 接任务按钮显示文本
    private Integer recvNpcId;// 接任务NPC

    private String recvText; // 接任务对白文本
    private String submitButtonText; // 交任务按钮文本

    private String target;// 目标
    private Integer type;// 类型 1-主线，2-支线，3-日常任务

    public String getAnswerText() {
	return answerText;
    }

    public Integer getDeliverNpcId() {
	return deliverNpcId;
    }

    public String getDescription() {
	return description;
    }

    public Integer getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public String getQuestionText() {
	return questionText;
    }

    public Integer getRecvNpcId() {
	return recvNpcId;
    }

    public String getRecvText() {
	return recvText;
    }

    public String getSubmitButtonText() {
	return submitButtonText;
    }

    public String getTarget() {
	return target;
    }

    public Integer getType() {
	return type;
    }

    public void setAnswerText(String answerText) {
	this.answerText = answerText;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setQuestionText(String questionText) {
	this.questionText = questionText;
    }

    public void setRecvNpcId(Integer recvNpcId) {
	this.recvNpcId = recvNpcId;
    }

    public void setRecvText(String recvText) {
	this.recvText = recvText;
    }

    public void setSubmitButtonText(String submitButtonText) {
	this.submitButtonText = submitButtonText;
    }

    public void setTarget(String target) {
	this.target = target;
    }

    public void setType(Integer type) {
	this.type = type;
    }

}
