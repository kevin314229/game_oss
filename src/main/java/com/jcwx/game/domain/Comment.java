package com.jcwx.game.domain;

import java.util.Date;

import com.jcwx.game.common.domain.BaseDomain;

public class Comment extends BaseDomain {

    private Answer answer;
    /**  */
    private Integer commentID;
    /** 问题类型，0-bug,1-建议，2-投拆，3-其它 */
    private char flag;
    /** 玩家编号 */
    private Integer playerID;
    /** 提交问题时间 */
    private Date queDate;
    /** 详细问题 */
    private String question;
    /** 问题状态，0-未解决，1-回复，2-关闭 */
    private char state;
    /** 标题 */
    private String title;

    public Comment() {
    }

    public Comment(Integer commentId, Integer playerId, String title,
	    String question, Date queDate, char flag, char state) {
	this.commentID = commentId;
	this.playerID = playerId;
	this.title = title;
	this.question = question;
	this.queDate = queDate;
	this.flag = flag;
	this.state = state;
    }

    public Answer getAnswer() {
	return answer;
    }

    public Integer getCommentID() {
	exeGet();
	return commentID;
    }

    public char getFlag() {
	exeGet();
	return flag;
    }

    public Integer getplayerID() {
	exeGet();
	return playerID;
    }

    public Date getQueDate() {
	exeGet();
	return queDate;
    }

    public String getQuestion() {
	exeGet();
	return question;
    }

    public char getState() {
	exeGet();
	return state;
    }

    public String getTitle() {
	exeGet();
	return title;
    }

    public void setAnswer(Answer answer) {
	this.answer = answer;
    }

    public void setCommentID(Integer commentId) {
	this.commentID = commentId;
	exeSet();
    }

    public void setFlag(char flag) {
	this.flag = flag;
	exeSet();
    }

    public void setplayerID(Integer playerId) {
	this.playerID = playerId;
	exeSet();
    }

    public void setQueDate(Date queDate) {
	this.queDate = queDate;
	exeSet();
    }

    public void setQuestion(String question) {
	this.question = question;
	exeSet();
    }

    public void setState(char state) {
	this.state = state;
	exeSet();
    }

    public void setTitle(String title) {
	this.title = title;
	exeSet();
    }

}