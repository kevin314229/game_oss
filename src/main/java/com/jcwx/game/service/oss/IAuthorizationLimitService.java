package com.jcwx.game.service.oss;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jcwx.game.domain.BlackList;

public interface IAuthorizationLimitService {
    /**
     * 创建黑名单
     * 
     * @param blackList
     * @return
     */
    public void createBlackList(String ip, Date banEndTime);

    /**
     * 根据IP删除黑名单信息
     * 
     * @param ip
     * @return
     */
    public Integer deleteBlackListByIP(String ip);

    /**
     * 获取黑名单列表
     * 
     * @return
     */
    public List<BlackList> getAllBlackList();

    /**
     * 获取黑名单列表(分页)
     * 
     * @param keyword
     *            关键字查询,ip
     * @param state
     *            状态
     * @param orderFlag
     *            结束时间排序 传递ASC或DESC
     * @param beginNum
     * @param pageNum
     * @return
     */
    public List<BlackList> getAllBlackListPage(String keyword,
	    Integer banState, String orderFlag, Integer beginNum,
	    Integer pageNum);

    /**
     * 查询黑名单总数量
     * 
     * @param keyword
     * @param state
     * @return
     */
    public Integer getAllBlackListPageCount(String keyword, Integer banState);

    /**
     * 得到黑名单数量
     * 
     * @return
     */
    public Integer getBlackListAmount(Integer playerID);

    /**
     * 根据IP得到黑名单信息
     * 
     * @param ip
     * @return
     */
    public BlackList getBlackListByIP(String ip);

    /**
     * 得到黑名单缓存
     * 
     * @return
     */
    public HashMap<String, BlackList> getBlackListCacheMap();

    /**
     * 根据IP从缓存中获取黑名单信息
     * 
     * @return
     */
    public BlackList getBlackListFromCacheByIP(String ip);

    /**
     * 将黑名单信息加入到缓存
     * 
     * @return
     */
    public Map<String, BlackList> initBlackListMap();

    /**
     * 更新黑名单信息
     * 
     * @param blackList
     * @return
     */
    public Integer updateBlackList(BlackList blackList);
}
