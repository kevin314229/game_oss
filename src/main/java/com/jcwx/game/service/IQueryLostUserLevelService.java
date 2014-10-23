package com.jcwx.game.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jcwx.game.domain.ZQueryLostUserLevel;

@Service
public interface IQueryLostUserLevelService {

    /**
     * 调度数据
     * 
     * @param quarzTime
     *            调度时间
     * @param runFlag
     *            执行调度程序的标识
     * @return
     */
    public boolean QuarzQueryLostUserLevel(Date quarzTime, Integer runFlag);

    /**
     * 
     * @param beginTime
     * @param endTime
     * @param beginNum
     * @param onePageNum
     * @param gameId
     * @param selectString
     * @return
     */
    public List<ZQueryLostUserLevel> QueryActiveUserLevelByDay(Date beginTime,
	    Date endTime, Integer beginNum, Integer onePageNum, Integer gameId,
	    String selectString);

    /**
     * 
     * @param beginTime
     * @param endTime
     * @return
     */
    public Integer QueryLostUserLevelAllrecord(Date beginTime, Date endTime,
	    Integer gameId, String selectString);

    /**
     * 查询对应游戏项目，对应大区，相应时间范围内的数据
     * 
     * @param beginTime
     * @param endTime
     * @param beginNum
     * @param onePageNum
     * @param gameId
     * @param areaId
     * @return
     */
    public List<ZQueryLostUserLevel> QueryLostUserLevelByDay(Date beginTime,
	    Date endTime, Integer beginNum, Integer onePageNum, Integer gameId,
	    String selectString);

}
