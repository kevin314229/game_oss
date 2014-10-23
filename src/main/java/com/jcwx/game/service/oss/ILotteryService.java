package com.jcwx.game.service.oss;

import com.jcwx.game.domain.OssLottery;

/**
 * 抽奖奖励表
 * 
 * @author Administrator
 * 
 */
public interface ILotteryService {

    /**
	 * 创建OssLottery
	 * @param ossLottery
	 * @return 数据影响条数
	 */
	public Integer createOssLottery(OssLottery ossLottery);
	/**
	 * 通过主键ID查询OssLottery
	 * @param lotteryId
	 * @return OssLottery
	 */
	public OssLottery getOssLotteryByServialNo(String ServialNo);

}
