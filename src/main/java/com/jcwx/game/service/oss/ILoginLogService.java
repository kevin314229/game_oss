package com.jcwx.game.service.oss;

import java.util.Date;
import java.util.List;

import com.jcwx.game.domain.IPStats;
import com.jcwx.game.domain.LoginLog;

public interface ILoginLogService {

    /**
     * 记录日志
     * 
     * @param loginLog
     */
    public Integer createLoginLog(LoginLog loginLog);

    /**
     * 删除过期登陆日志
     * 
     * @return
     */
    public void deleteLoginLogOverdue();

    /**
     * 统计IP
     * 
     * @param orderFlag
     *            排序标识
     * @param beginNum
     * @param pageNum
     * @return
     */
    public List<IPStats> getIPStats(Date beginTime, Date endTime,
	    String orderFlag, Integer beginNum, Integer pageNum);

    /** 统计IP总数(分页) */
    public Integer getIPStatsCount(Date beginTime, Date endTime);

    /**
     * 查询最后一条日志
     * 
     * @return
     */
    public LoginLog getLastLoginLog();

    /** 分时段统计总登录次数-日 */
    public List getLoginLogCountByDay(Date beginTime, Date endTime);

    /** 分时段统计总登录次数-月 */
    public List getLoginLogCountByMonth(Date beginTime, Date endTime);

    /** 分时段统计总登录次数-年 */
    public List getLoginLogCountByYear(Date beginTime, Date endTime);

    /**
     * 浏览登录信息
     * 
     * @param keyword
     * @param orderFlag
     * @param beginNum
     * @param pageNum
     * @return
     */
    @SuppressWarnings("unchecked")
    public List getLoginLogInfoList(String keyword, String orderFlag,
	    Integer beginNum, Integer pageNum);

    /** 浏览登录信息统计(分页) */
    public Integer getLoginLogInfoListCount(String keyword);

    /**
     * 浏览指定会员登录历史
     * 
     * @param playerID
     * @param keyword
     * @param orderFlag
     * @param beginTime
     * @param endTime
     * @param beginNum
     * @param pageNum
     * @return
     */
    public List<LoginLog> getLoginLogList(Integer playerID, String keyword,
	    String orderFlag, Date beginTime, Date endTime, Integer beginNum,
	    Integer pageNum);

    /**
     * 浏览指定会员登录历史统计(分页)
     * 
     * @param playerID
     * @param keyword
     * @param orderFlag
     * @param beginTime
     * @param endTime
     * @return
     */
    public Integer getLoginLogListCount(Integer playerID, String keyword,
	    String orderFlag, Date beginTime, Date endTime);

    /**
     * 更新玩家登出时间
     * 
     * @param playerID
     * @return
     */
    public void updateQuitTimeOfLoginLogByPlayerID(Integer playerID);

}
