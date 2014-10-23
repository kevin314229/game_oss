package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.OperationStatistic;

/** 消耗与产出统计 */
public interface IOperationReportService {
    /**
     * 查询货币产出与消耗根据游戏和服务器查询从
     * 
     * @return
     */
    public List<OperationStatistic> getOperationDaySum(String beginDate,
	    String endDate, Integer gameId, String areaId, String itemIds);

    /**
     * 查询货币产出与消耗根据游戏、服务器ID、登录角色名查询
     * 
     * @return
     */
    public List<OperationStatistic> getOperationDaySumByNickName(
	    String beginDate, String endDate, Integer gameId, String areaId,
	    String itemIds, String loginName);

    /**
     * 查询货币产出与消耗各个消费点详情（只查登录角色名的）
     * 
     * @param params
     * @return
     */
    public List<OperationStatistic> getOperationListByNickName(
	    String beginDate, String endDate, Integer gameId, String areaId,
	    int itemId, String loginName, int flowType);

    /**
     * 查询货币产出与消耗各个消费点详情
     * 
     * @param params
     * @return
     */
    public List<OperationStatistic> getOperationStaList(String beginDate,
	    String endDate, Integer gameId, String areaId, int itemId,
	    int flowType);

    /**
     * 查询货币产出与消耗根据游戏和服务器查询从
     * 
     * @return
     */
    public List<OperationStatistic> getOperationStaSum(String beginDate,
	    String endDate, Integer gameId, String areaId, String itemIds);

    /**
     * 查询货币产出与消耗根据游戏、服务器ID、登录角色名查询
     * 
     * @return
     */
    public List<OperationStatistic> getOperationSumByNickName(String beginDate,
	    String endDate, Integer gameId, String areaId, String itemIds,
	    String loginName);

}
