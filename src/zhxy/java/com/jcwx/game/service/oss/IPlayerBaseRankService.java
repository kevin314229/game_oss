package com.jcwx.game.service.oss;

import java.util.List;
import java.util.Map;

import com.jcwx.game.domain.PayInfo;
import com.jcwx.game.domain.PlayerBaseRank;

public interface IPlayerBaseRankService {

    /**
	 * 创建PlayerBaseRank
	 * @param PlayerBaseRank
	 * @return 数据影响条数
	 */
	public Integer createPlayerBaseRank(PlayerBaseRank PlayerBaseRank);

	/**
	 * 修改PlayerBaseRank
	 * @param PlayerBaseRank
	 * @return 数据影响条数
	 */
	public Integer updatePlayerBaseRank(PlayerBaseRank PlayerBaseRank);

	/**
	 * 通过主键ID删除PlayerBaseRank
	 * @param gameId
	 * @return 数据影响条数
	 */
	public Integer deletePlayerBaseRankByID(String gameId);
	
	/**
	 * 通过主键ID查询PlayerBaseRank
	 * @param gameId
	 * @return PlayerBaseRank
	 */
	public PlayerBaseRank getPlayerBaseRankByID(String gameId);

	/**
	 * 查询所有的PlayerBaseRank
	 * @return PlayerBaseRank的集合
	 */
	public List<PlayerBaseRank> getPlayerBaseRankList(Map params);


	public Integer getPlayerBaseRankCount(Map params);
	/**
	 * 查询充值记录
	 * @param params
	 * @return
	 */
	 public List<PayInfo> getPayInfoList(Map params);
	 
	 public Integer getPayInfoCount(Map params);
}
