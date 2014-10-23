package com.jcwx.game.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jcwx.game.domain.ZQueryLostUserLevelDetail;

@Service
public interface IQueryLostUserLevelDetail {

    /**
     * 
     * @param firstTime
     * @param lastTime
     * @param gameId
     * @param areaId
     * @param playerLeve
     * @return
     */
    public List<ZQueryLostUserLevelDetail> getQueryActiveUserLevelDetailList(
	    Date firstTime, Date lastTime, Integer gameId, String areaId,
	    Integer playerLeve);

    /**
     * 通过时间查询每天的登陆人数
     * 
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<ZQueryLostUserLevelDetail> getQueryLostUserLevelDetailList(
	    Date firstTime, Date lastTime, Integer gameId, String areaId,
	    Integer playerLeve);
}
