package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.Answer;

/** 意见回复 */
public interface IAnswerService {

    /** 新增回复 */
    public Integer addAnswer(Answer answer);

    /**
     * 删除回复
     * 
     * @param
     */
    public Integer deleteAnswerById(Integer answerId);

    /**
     * 获取回复
     * 
     * @param AnswerId
     *            id
     * @return
     */
    public Answer getAnswerById(Integer answerId);

    /**
     * 回复总数
     * 
     * @param example
     * @return
     */
    public Integer getCountNum();

    /**
     * 获取回复列表
     * 
     * @return
     */
    public List<Answer> getListAnswerByCommentId(Integer commentID);

    /** 修改回复 */
    public Integer updateAnswer(Answer answer);

}
