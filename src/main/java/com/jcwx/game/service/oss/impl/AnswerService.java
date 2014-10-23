package com.jcwx.game.service.oss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.Answer;
import com.jcwx.game.service.oss.IAnswerService;

@Service
public class AnswerService implements IAnswerService {
    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public Integer addAnswer(Answer answer) {
	return (Integer) baseDAO.insert("Answer.createAnswer", answer);
    }

    @Override
    public Integer deleteAnswerById(Integer answerID) {
	baseDAO.delete("Answer.deleteAnswerByID", answerID);
	return null;
    }

    @Override
    public Answer getAnswerById(Integer answerID) {
	return (Answer) baseDAO
		.queryForObject("Answer.getAnswerByID", answerID);
    }

    @Override
    public Integer getCountNum() {

	return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Answer> getListAnswerByCommentId(Integer commentID) {

	return baseDAO.queryForList("Answer.getAnswerListByCommentId",
		commentID);
    }

    @Override
    public Integer updateAnswer(Answer answer) {
	baseDAO.update("Answer.updateAnswer", answer);
	return null;

    }

}
