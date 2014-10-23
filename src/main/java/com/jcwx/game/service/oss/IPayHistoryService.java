package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.PayHistory;
import com.jcwx.game.domain.PayHistorySta;
import com.jcwx.game.domain.PayRank;
import com.jcwx.game.domain.PayStatistic;

public interface IPayHistoryService {

    /**
     * 创建PayHistory
     * 
     * @param aPayHistory
     * @return 数据影响条数
     */
    public Integer createPayHistory(PayHistory payHistory);

    /**
     * 通过主键ID删除PayHistory
     * 
     * @param payHistoryId
     * @return 数据影响条数
     */
    public void deletePayHistoryByID(String payHistoryId);

    /**
     * 查询服务器的PayRank
     * 
     * @return PayRank的集合
     */
    public List<PayRank> getAreaPayList(Integer gameId, String areaId,
	    String ptId, String loginName);

    /**
     * 根据服务器ID查询充值统计数据
     * 
     * @param gameId
     * @param areaId
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<PayStatistic> getAreaRecordList(Integer gameId, Integer areaId,
	    String beginDate, String endDate);

    /**
     * 通过主键ID查询PayHistory
     * 
     * @param payHistoryId
     * @return PayHistory
     */
    public PayHistory getPayHistoryByID(String payHistoryId);

    /**
     * 查询所有的PayHistory
     * 
     * @return PayHistory的集合
     */
    public List<PayHistory> getPayHistoryList();

    /**
     * 查询所有的PayRank
     * 
     * @return PayRank的集合
     */
    public List<PayHistorySta> getPayHistorySta(Integer gameID, String ptID,
	    String selectArray, String beginDate, String endDate);

    /**
     * 查询所有的PayInfo总数量
     * 
     * @return Integer
     */
    public Integer getPayInfoCount(Integer gameId, String areaId, String ptId,
	    String beginDate, String endDate, String loginName, String nickName);

    /**
     * 查询所有的PayInfo
     * 
     * @return PayInfo的集合
     */
    public List<PayHistory> getPayInfoList(Integer beginNum,
	    Integer onePageNum, Integer gameId, String areaId, String ptId,
	    String beginDate, String endDate, String loginName, String nickName);

    /**
     * 查询所有的PayRank总数
     * 
     * @return Integer
     */
    public Integer getPayRankCount(Integer gameId, String areaId, String ptId,
	    String loginName);

    /**
     * 查询所有的PayRank
     * 
     * @return PayRank的集合
     */
    public List<PayRank> getPayRankList(Integer beginNum, Integer onePageNum,
	    Integer gameId, String areaId, String ptId, String loginName);

    /**
     * 修改PayHistory
     * 
     * @param aPayHistory
     * @return 数据影响条数
     */
    public void updatePayHistory(PayHistory payHistory);
}
