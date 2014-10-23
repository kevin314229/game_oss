package com.jcwx.game.service.oss.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseStaDAO;
import com.jcwx.game.domain.PlayerChatLog;
import com.jcwx.game.service.oss.IPlayerChatLogService;

@Service
public class PlayerChatLogService implements IPlayerChatLogService {

    @Autowired
    private IBaseStaDAO baseDAO;

    @Override
    public Integer createPlayerChatLog(PlayerChatLog playerChatLog) {

	return (Integer) this.baseDAO.insert(
		"PlayerChatLog.createPlayerChatLog", playerChatLog);
    }

    @Override
    public void deletePlayerChatLogByID(String playerChatLogId) {
	this.baseDAO.delete("PlayerChatLog.deletePlayerChatLogByID",
		playerChatLogId);

    }

    @Override
    public PlayerChatLog getPlayerChatLogByID(String playerChatLogId) {
	return (PlayerChatLog) this.baseDAO.queryForObject(
		"PlayerChatLog.getPlayerChatLogByID", playerChatLogId);

    }

    @Override
    public Integer getPlayerChatLogCountByInfo(Integer beginNum,
	    Integer onePageNum, Integer gameId, String areaId,
	    String beginDate, String endDate, String loginName,
	    String nickName, String content) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	params.put("loginName", loginName);
	params.put("nickName", nickName);
	params.put("content", content);
	params.put("beginNum", beginNum);
	params.put("onePageNum", onePageNum);
	return (Integer) this.baseDAO.queryForObject(
		"PlayerChatLog.getPlayerChatLogCountByInfo", params);
    }

    @Override
    public List<PlayerChatLog> getPlayerChatLogList() {
	return this.baseDAO.queryForList("PlayerChatLog.getPlayerChatLogList");

    }

    @Override
    public List<PlayerChatLog> getPlayerChatLogListByInfo(Integer beginNum,
	    Integer onePageNum, Integer gameId, String areaId,
	    String beginDate, String endDate, String loginName,
	    String nickName, String content) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginDate", beginDate);
	params.put("endDate", endDate);
	params.put("gameId", gameId);
	params.put("areaId", areaId);
	params.put("loginName", loginName);
	params.put("nickName", nickName);
	params.put("content", content);
	params.put("beginNum", beginNum);
	params.put("onePageNum", onePageNum);
	return this.baseDAO.queryForList(
		"PlayerChatLog.getPlayerChatLogListByInfo", params);
    }

    @Override
    public void updatePlayerChatLog(PlayerChatLog playerChatLog) {
	this.baseDAO.update("PlayerChatLog.updatePlayerChatLog", playerChatLog);

    }

}
