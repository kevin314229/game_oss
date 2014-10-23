package com.jcwx.game.service.oss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jcwx.game.domain.DataHistory;

public interface IDataHistoryService {
    /**
     * 创建数据历史
     * 
     * @param dataHistory
     * @return
     */
    public Integer createDataHistory(Integer playerID, DataHistory dataHistory);

    /**
     * 在线数据统计，分时段-天
     * 
     * @param begin
     * @param end
     * @return
     * @author Run
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> DataHistoryListByDay(Long begin, Long end);

    /**
     * 在线数据统计，分时段-时
     * 
     * @param begin
     * @param end
     * @return
     * @author Run
     */
    @SuppressWarnings("unchecked")
    public List<HashMap> DataHistoryListByHour(Long begin, Long end);

    /**
     * 根据编号删除数据历史
     * 
     * @param dataHistoryID
     */
    public void deleteDataHistoryByID(Integer playerID, Long dataHistoryID);

    /**
     * 获得平均在线玩家数量
     * 
     * @return
     */
    public Integer getAvgOnlinePlayerNum(Integer playerID);

    /**
     * 根据时间段获得平均在线玩家数量
     * 
     * @return
     */
    public Integer getAvgOnlinePlayerNumByTime(Integer playerID, Long begin,
	    Long end);

    /**
     * 根据编号获得数据历史
     * 
     * @param dataHistoryID
     * @return
     */
    public DataHistory getDataHistoryByID(Integer playerID, Long dataHistoryID);

    /**
     * 获得数据历史列表
     * 
     * @return
     */
    public List<DataHistory> getDataHistoryList(Integer playerID);

    /**
     * 根据时间段取得数据历史
     * 
     * @param begin
     * @param end
     * @return
     */
    public List<DataHistory> getDataHistoryListByTime(Integer playerID,
	    Long begin, Long end);

    /**
     * 根据时间段取得数据历史（排序，分页）
     * 
     * @param begin
     * @param end
     * @param orderFlag
     * @param beginNum
     * @param pageNum
     * @return
     */
    public List<DataHistory> getDataHistoryListByTimeAndOnePage(Long begin,
	    Long end, String orderFlag, Integer beginNum, Integer pageNum);

    /**
     * 根据时间段取得数据历史数量
     * 
     * @param begin
     * @param end
     * @return
     */
    public Integer getDataHistoryListNumByTimeAndOnePage(Long begin, Long end);

    /**
     * 获得最高在线玩家数量
     * 
     * @return
     */
    public Integer getMaxOnlinePlayerNum(Integer playerID);

    /**
     * 根据时间段获得最高在线玩家数量
     * 
     * @return
     */
    public Integer getMaxOnlinePlayerNumByTime(Integer playerID, Long begin,
	    Long end);

    /**
     * 获得在线玩家数量信息（最大值，最小值，平均值）
     * 
     * @return
     */
    public Map<String, Object> getOnlinePlayerNumInfo();

    /**
     * 根据时间段获得在线玩家数量信息（最大值，最小值，平均值）
     * 
     * @return
     */
    public Map<String, Object> getOnlinePlayerNumInfoByTime(Long begin, Long end);

    /**
     * 更新数据历史
     * 
     * @param dataHistory
     */
    public void updateDataHistory(Integer playerID, DataHistory dataHistory);

}
