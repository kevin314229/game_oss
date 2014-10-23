package com.jcwx.game.service.oss;

import java.util.List;
import java.util.Map;

import com.jcwx.game.domain.ConsumeData;
import com.jcwx.game.domain.ConsumeFirstData;

/** kpi统计 */
public interface IConsumeService {
    /**
     * 查询消费总数
     * 
     * @param params
     * @return
     */
    public Integer getConsumeDataCount(Integer beginNum, Integer onePageNum,
	    String beginDate, String endDate, Integer gameId, String areaId,
	    String ptId, String loginName, String nickName, Integer sourceType);

    /**
     * 查询消费列表
     * 
     * @param params
     * @return
     */
    public List<ConsumeData> getConsumeDataList(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, String loginName,
	    String nickName, Integer sourceType);

    /**
     * 查询首次消费总数
     * 
     * @param params
     * @return
     */
    public Integer getConsumeFirstDataCount(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, String loginName,
	    String nickName);

    /**
     * 查询首次消费列表
     * 
     * @param params
     * @return
     */
    public List<ConsumeFirstData> getConsumeFirstDataList(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, String loginName,
	    String nickName);

    /**
     * 统计玩家首次消费与距首登陆天数
     * 
     * @param params
     * @return
     */
    public List<Map> getFromFirstDaysCountList(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, String loginName,
	    String nickName);

    /**
     * 统计玩家首次消费等级分布
     * 
     * @param params
     * @return
     */
    public List<Map> getLevelCountList(Integer beginNum, Integer onePageNum,
	    String beginDate, String endDate, Integer gameId, String areaId,
	    String ptId, String loginName, String nickName);

    /**
     * 统计玩家首次消费点分布
     * 
     * @param params
     * @return
     */
    public List<Map> getOperationCountList(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, String loginName,
	    String nickName);

    /**
     * 统计服务器消费总额
     * 
     * @param params
     * @return
     */
    public List<ConsumeData> sumAreaConsumeList(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, String loginName,
	    String nickName, Integer sourceType);
    /**
     * 统计服务器消费总额
     * 
     * @param params
     * @return
     */
    public List sumAreaConsumeList(String beginDate, String endDate,
	    Integer gameId, String areaId ,Integer sourceType);

    
    public  List<ConsumeData>  queryCycConsumeGoldList(Map<String, Object> params);
    
    
    public Double queryCycConsumeGoldSum(Map<String, Object> params);
}
