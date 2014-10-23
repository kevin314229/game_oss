package com.jcwx.game.service.oss.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.common.constants.BlackListConstant;
import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.BlackList;
import com.jcwx.game.service.oss.IAuthorizationLimitService;

@Service
public class AuthorizationLimitService implements IAuthorizationLimitService {
    @Autowired
    private IBaseDAO baseDao;

    @Override
    public void createBlackList(String ip, Date banEndTime) {

	// BlackList blackList = getBlackListFromCacheByIP(ip);
	BlackList blackList = (BlackList) baseDao.queryForObject(
		"BlackList.getBlackListByIP", ip);
	// 首次加黑名单
	if (blackList == null) {
	    blackList = new BlackList();
	    blackList.setIp(ip);
	    blackList.setBanState(1);
	    blackList.setBanTime(BlackListConstant.STOPUSE);
	    blackList.setBanEndTime(banEndTime);

	    // 更新数据库并添加到缓存
	    baseDao.insert("BlackList.createBlackList", blackList);
	    this.getBlackListCacheMap().put(ip, blackList);
	} else {// 曾经列入黑名单

	    blackList.setBanState(BlackListConstant.STOPUSE);
	    blackList.setBanTime(blackList.getBanTime() + 1);
	    baseDao.update("BlackList.updateBlackList", blackList);

	    // 更新缓存
	    getBlackListCacheMap().remove(ip);
	    getBlackListCacheMap().put(ip, blackList);

	}

    }

    @Override
    public Integer deleteBlackListByIP(String ip) {
	return baseDao.delete("BlackList.deleteBlackListByIP", ip);
    }

    @Override
    public List<BlackList> getAllBlackList() {
	return baseDao.queryForList("BlackList.getAllBlackList");
    }

    @Override
    public List<BlackList> getAllBlackListPage(String keyword,
	    Integer banState, String orderFlag, Integer beginNum,
	    Integer pageNum) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("keyword", keyword);
	params.put("banState", banState);
	params.put("orderFlag", orderFlag);
	params.put("beginNum", beginNum);
	params.put("pageNum", pageNum);
	return baseDao.queryForList("BlackList.getAllBlackListPage", params);
    }

    @Override
    public Integer getAllBlackListPageCount(String keyword, Integer banState) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("keyword", keyword);
	params.put("banState", banState);
	return (Integer) baseDao.queryForObject("BlackList.getBlackListCount",
		params);
    }

    @Override
    public Integer getBlackListAmount(Integer playerID) {
	return (Integer) baseDao.queryForObject("BlackList.getBlackListAmount");
    }

    @Override
    public BlackList getBlackListByIP(String ip) {
	return (BlackList) baseDao.queryForObject("BlackList.getBlackListByIP",
		ip);
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<String, BlackList> getBlackListCacheMap() {
	return null;// (HashMap<String,
		    // BlackList>)CacheService.getFromCache(CacheConstant.BLACK_LIST_MAP);
    }

    @Override
    @SuppressWarnings("unchecked")
    public BlackList getBlackListFromCacheByIP(String ip) {
	return null;// ((Map<String,
		    // BlackList>)CacheService.getFromCache(CacheConstant.BLACK_LIST_MAP)).get(ip);
    }

    @Override
    public Map<String, BlackList> initBlackListMap() {
	List<BlackList> blackList = baseDao
		.queryForList("BlackList.getAllBlackList");
	Map<String, BlackList> blackListMap = new HashMap<String, BlackList>();
	for (int i = 0; i < blackList.size(); ++i) {
	    BlackList bl = blackList.get(i);
	    blackListMap.put(bl.getIp(), bl);
	}

	return blackListMap;
    }

    @Override
    public Integer updateBlackList(BlackList blackList) {
	if (blackList == null) {
	    return 0;
	}
	Integer k = baseDao.update("BlackList.updateBlackList", blackList);
	// if(k>0){
	// //更新缓存
	// getBlackListCacheMap().remove(blackList.getIp());
	// getBlackListCacheMap().put(blackList.getIp(), blackList);
	// }
	return k;
    }

}
