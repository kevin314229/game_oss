package com.jcwx.game.service.oss;

import java.util.List;
import java.util.Map;

import com.jcwx.game.domain.KPIDayReport;

/** kpi统计 */
public interface IKPIReportService {
    /**
     * 查询kpi日报总数
     * 
     * @param params
     * @return
     */
    public Integer getKPIDayReportCount(Integer beginNum, Integer onePageNum,
	    String beginDate, String endDate, Integer gameId, String areaId,
	    String ptId);

    /**
     * 查询kpi日报列表
     * 
     * @param params
     * @return
     */
    public List<KPIDayReport> getKPIDayReportList(Integer beginNum,
	    Integer onePageNum, String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId, Integer[] selectArray);

    /**
     * 查询kpi日报列表日统计数据
     * 
     * @param params
     * @return
     */
    public List<KPIDayReport> sumKPIDayData(String beginDate, String endDate,
	    Integer gameId, String areaId, String ptId);

    
    public Map<String, Object> querySumLogin(Map<String, Object> params);
    
    public Map<String, Object> querySumPay(Map<String, Object> params);
    
    public List<KPIDayReport> getKPIDayReportListByPtCode(Map<String, Object> params);
    
    public List<KPIDayReport> getPtKPIDayReportList(Map<String, Object> params);
}
