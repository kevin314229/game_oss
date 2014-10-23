package com.jcwx.game.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jcwx.game.domain.ZQueryActiveUser;

@Service
public interface IQueryActiveUserService {

    /**
     * 
     * @param beginTime
     * @param endTime
     * @param beginNum
     * @param onePageNum
     * @param gameId
     * @param selectArrary
     * @return
     */
    public List<ZQueryActiveUser> NewRegisterLoginAnalyse(Date beginTime,
	    Date endTime, Integer beginNum, Integer onePageNum, Integer gameId,
	    String selectString);

    /**
     * 
     * @param beginTime
     * @param endTime
     * @param gameId
     * @param slectArrary
     * @return
     */
    public Integer NewRegisterLoginAnalyseAllNum(Date beginTime, Date endTime,
	    Integer gameId, String selectString);

    /**
     * 调度数据
     * 
     * @param quarzTime
     *            调度时间
     * @param runFlag
     *            执行调度程序的标识
     * @return
     */
    public boolean QuarzQueryActiveUser(Date quarzTime, Integer runFlag);

    /**
     * 
     * @param beginTime
     * @param endTime
     * @param gameId
     * @param slectArrary
     * @return
     */
    public Integer QueryActiveUserAllNum(Date beginTime, Date endTime,
	    Integer gameId, String selectString);

    /**
     * 通过时间查询每天的登陆人数
     * 
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<ZQueryActiveUser> QueryActiveUserByDay(Date beginTime,
	    Date endTime, Integer beginNum, Integer onePageNum, Integer gameId,
	    String selectString);

    public List<ZQueryActiveUser> queryRegisterGroupByPtId(Date beginTime,
	    Date endTime, Integer beginNum, Integer onePageNum, Integer gameId,
	    String selectString);
}
