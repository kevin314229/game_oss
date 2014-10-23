package com.jcwx.game.service.oss;

import java.util.Date;
import java.util.List;

import com.jcwx.game.domain.PlayerCreateStat;

public interface IPlayerService {

    public List<PlayerCreateStat> getPlayerCreateStatListByDay(Date beginDate,
	    Date endDate, Integer projectId, String ossServerId, String ptCode);

    public List getPlayerCreateStatListByPtCode(Date beginDate, Date endDate,
	    Integer projectId, String ossServerId, String ptCode);

    public List getPlayerCreateStatListByServerId(Date beginDate, Date endDate,
	    Integer projectId, String ossServerId, String ptCode);

    public Integer getPlayerNum(Integer gameId);
}
