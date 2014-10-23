package com.jcwx.game.service.oss;

import java.util.Date;
import java.util.List;

import com.jcwx.game.domain.Comment;

/** 会员意见 */
public interface ICommentService {

    /** 新增意见 */
    public Integer addComment(Comment comment);

    /**
     * 删除意见
     * 
     * @param commentId
     *            意见id
     */
    public Integer deleteCommentById(Integer commentID);

    /**
     * 获取意见
     * 
     * @param commentId
     *            意见id
     * @return
     */
    public Comment getCommentById(Integer commentID);

    /**
     * 意见总数
     * 
     * @param example
     * @return
     */
    public Long getCommentListCountNum(String flag, String state,
	    Date beginDate, Date endDate);

    /**
     * 获取意见列表
     * 
     * @return
     */
    public List getListComment(String flag, String state, Date beginDate,
	    Date endDate, Integer beginNum, Integer pageNum, String orderFlag);

    /**
     * 会员某时间段发布的数量
     * 
     * @param playerID
     *            会员id
     * @param beginDate
     *            开始时间
     * @param endDate
     *            结束时间
     * @return 数量
     */
    public Integer getUserBetweenDayPutCountNum(Integer playerID,
	    Date beginDate, Date endDate);

    /**
     * 游戏调用，查调已提交的意见
     * 
     * @param playerID
     *            玩家id
     * @param flag
     *            问题类型
     * @param state
     *            问题状态
     * @param beginNum
     * @param pageNum
     * @return
     */
    public List<Comment> queryCommentByPlayerID(Integer playerID, String flag,
	    String state, Integer beginNum, Integer pageNum);

    /** 游戏调用，查调已提交的意见 记录总数 */
    public Integer queryCommentByPlayerIDCountNum(Integer playerID,
	    String flag, String state, Integer beginNum, Integer pageNum);

    /** 修改意见 */
    public Integer updateComment(Comment comment);

}
