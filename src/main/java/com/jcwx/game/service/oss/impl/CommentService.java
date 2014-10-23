package com.jcwx.game.service.oss.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.Answer;
import com.jcwx.game.domain.Comment;
import com.jcwx.game.service.oss.IAnswerService;
import com.jcwx.game.service.oss.ICommentService;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private IAnswerService answerService;
    @Autowired
    private IBaseDAO baseDao;

    @Override
    public Integer addComment(Comment comment) {
	return (Integer) this.baseDao.insert("Comment.createComment", comment);

    }

    @Override
    public Integer deleteCommentById(Integer commentID) {
	this.baseDao.delete("Comment.deleteCommentByID", commentID);
	return null;
    }

    public IAnswerService getAnswerService() {
	return answerService;
    }

    @Override
    public Comment getCommentById(Integer commentID) {
	return (Comment) this.baseDao.queryForObject("Comment.getCommentByID",
		commentID);
    }

    @Override
    public Long getCommentListCountNum(String flag, String state,
	    Date beginDate, Date endDate) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("flag", flag);
	params.put("state", state);
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	return (Long) baseDao.queryForObject("Comment.getCommentListCountNum",
		params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List getListComment(String flag, String state, Date beginDate,
	    Date endDate, Integer beginNum, Integer pageNum, String orderFlag) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("flag", flag);
	params.put("state", state);
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("beginNum", beginNum);
	params.put("pageNum", pageNum);
	params.put("orderFlag", orderFlag);

	List<Comment> commentList = this.baseDao.queryForList(
		"Comment.getCommentList", params);
	// for(Comment comment:commentList){
	// PlayerCache playerCache =
	// playerService.getPlayerCacheByPlayerID(comment.getPlayerID());
	// comment.setPlayerName(playerCache.getName());
	// comment.setUserName(playerCache.getUserName());
	// }
	return commentList;
    }

    @Override
    public Integer getUserBetweenDayPutCountNum(Integer playerID,
	    Date beginDate, Date endDate) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("playerID", playerID);
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	return (Integer) baseDao.queryForObject(
		"Comment.getUserBetweenDayPutCountNum", params);
    }

    @Override
    public List<Comment> queryCommentByPlayerID(Integer playerID, String flag,
	    String state, Integer beginNum, Integer pageNum) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("playerID", playerID);
	params.put("flag", flag);
	params.put("state", state);
	params.put("beginNum", beginNum);
	params.put("pageNum", pageNum);
	List<Comment> commentList = this.baseDao.queryForList(
		"Comment.queryCommentByPlayerID", params);
	for (Comment comment : commentList) {
	    List<Answer> answerList = answerService
		    .getListAnswerByCommentId(comment.getCommentID());
	    if (answerList != null && answerList.size() > 0) {
		comment.setAnswer(answerList.get(0));
	    }
	}
	return commentList;
    }

    @Override
    public Integer queryCommentByPlayerIDCountNum(Integer playerID,
	    String flag, String state, Integer beginNum, Integer pageNum) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("playerID", playerID);
	params.put("flag", flag);
	params.put("state", state);
	return (Integer) baseDao.queryForObject(
		"Comment.queryCommentByPlayerIDCountNum", params);
    }

    public void setAnswerService(IAnswerService answerService) {
	this.answerService = answerService;
    }

    @Override
    public Integer updateComment(Comment comment) {
	baseDao.update("Comment.updateComment", comment);
	return null;

    }

}
