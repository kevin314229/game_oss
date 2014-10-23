package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.PlayerChatLog;

public interface IPlayerChatLogService {

    /**
     * 创建PlayerChatLog
     * 
     * @param aPlayerChatLog
     * @return 数据影响条数
     */
    public Integer createPlayerChatLog(PlayerChatLog aPlayerChatLog);

    /**
     * 通过主键ID删除PlayerChatLog
     * 
     * @param playerChatLogId
     * @return 数据影响条数
     */
    public void deletePlayerChatLogByID(String playerChatLogId);

    /**
     * 通过主键ID查询PlayerChatLog
     * 
     * @param playerChatLogId
     * @return PlayerChatLog
     */
    public PlayerChatLog getPlayerChatLogByID(String playerChatLogId);

    public Integer getPlayerChatLogCountByInfo(Integer beginNum,
	    Integer onePageNum, Integer gameId, String areaId,
	    String beginDate, String endDate, String loginName,
	    String nickName, String content);

    /**
     * 查询所有的PlayerChatLog
     * 
     * @return PlayerChatLog的集合
     */
    public List<PlayerChatLog> getPlayerChatLogList();

    public List<PlayerChatLog> getPlayerChatLogListByInfo(Integer beginNum,
	    Integer onePageNum, Integer gameId, String areaId,
	    String beginDate, String endDate, String loginName,
	    String nickName, String content);

    /**
     * 修改PlayerChatLog
     * 
     * @param aPlayerChatLog
     * @return 数据影响条数
     */
    public void updatePlayerChatLog(PlayerChatLog aPlayerChatLog);

}
