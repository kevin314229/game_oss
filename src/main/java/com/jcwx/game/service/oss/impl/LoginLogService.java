package com.jcwx.game.service.oss.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.common.DateService;
import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.IPStats;
import com.jcwx.game.domain.LoginLog;
import com.jcwx.game.service.oss.ILoginLogService;

@Service
public class LoginLogService implements ILoginLogService {

    @Autowired
    private IBaseDAO baseDao;

    @Override
    public Integer createLoginLog(LoginLog loginLog) {
	return (Integer) baseDao.insert("loginLog.createLoginLog", loginLog);
    }

    /**
     * 删除过期登陆日志
     */
    @Override
    public void deleteLoginLogOverdue() {
	Date currentDate = DateService.getCurrentUtilDate();
	System.out.println(DateService.getDateFormatStr(currentDate,
		"yyyy-MM-dd HH:mm:ss"));
	Date createTime = new Date(currentDate.getTime() - 60L * 1000 * 60 * 24
		* 30);
	while (true) {
	    System.out.println(DateService.getDateFormatStr(createTime,
		    "yyyy-MM-dd HH:mm:ss"));
	    Integer deleteNum = baseDao.delete(
		    "loginLog.deleteLoginLogOverdue", createTime);
	    if (deleteNum.intValue() < 1000) {
		break;
	    }
	}
    }

    @Override
    public List<IPStats> getIPStats(Date beginTime, Date endTime,
	    String orderFlag, Integer beginNum, Integer pageNum) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginTime", beginTime);
	params.put("endTime", endTime);
	params.put("orderFlag", orderFlag);
	params.put("beginNum", beginNum);
	params.put("pageNum", pageNum);
	return baseDao.queryForList("loginLog.getIPStats", params);
    }

    @Override
    public Integer getIPStatsCount(Date beginTime, Date endTime) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginTime", beginTime);
	params.put("endTime", endTime);
	return (Integer) baseDao.queryForObject("loginLog.getIPStatsCount",
		params);
    }

    @Override
    public LoginLog getLastLoginLog() {
	return (LoginLog) baseDao.queryForObject("loginLog.getLastLoginLog");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List getLoginLogCountByDay(Date beginTime, Date endTime) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginTime", beginTime);
	params.put("endTime", endTime);
	return baseDao.queryForList("loginLog.getLoginLogCountByDay", params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List getLoginLogCountByMonth(Date beginTime, Date endTime) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginTime", beginTime);
	params.put("endTime", endTime);
	return baseDao.queryForList("loginLog.getLoginLogCountByMonth", params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List getLoginLogCountByYear(Date beginTime, Date endTime) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("beginTime", beginTime);
	params.put("endTime", endTime);
	return baseDao.queryForList("loginLog.getLoginLogCountByYear", params);
    }

    @Override
    public List getLoginLogInfoList(String keyword, String orderFlag,
	    Integer beginNum, Integer pageNum) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("keyword", keyword);
	params.put("orderFlag", orderFlag);
	params.put("beginNum", beginNum);
	params.put("pageNum", pageNum);
	return baseDao.queryForList("loginLog.getLoginLogInfoList", params);
    }

    @Override
    public Integer getLoginLogInfoListCount(String keyword) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("keyword", keyword);
	return (Integer) baseDao.queryForObject(
		"loginLog.getLoginLogInfoListCount", params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LoginLog> getLoginLogList(Integer playerID, String keyword,
	    String orderFlag, Date beginTime, Date endTime, Integer beginNum,
	    Integer pageNum) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("playerID", playerID);
	params.put("keyword", keyword);
	params.put("orderFlag", orderFlag);
	params.put("beginTime", beginTime);
	params.put("endTime", endTime);
	params.put("beginNum", beginNum);
	params.put("pageNum", pageNum);
	return baseDao.queryForList("loginLog.getLoginLogList", params);
    }

    @Override
    public Integer getLoginLogListCount(Integer playerID, String keyword,
	    String orderFlag, Date beginTime, Date endTime) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("playerID", playerID);
	params.put("keyword", keyword);
	params.put("orderFlag", orderFlag);
	params.put("beginTime", beginTime);
	params.put("endTime", endTime);
	return (Integer) baseDao.queryForObject(
		"loginLog.getLoginLogListCount", params);
    }

    /**
     * 更新玩家登出时间
     */
    @Override
    public void updateQuitTimeOfLoginLogByPlayerID(Integer playerID) {
	LoginLog loginLog = (LoginLog) baseDao.queryForObject(
		"loginLog.getLastLoginLogByPlayerID", playerID);
	if (loginLog == null) {
	    return;
	}

	Date currentDate = DateService.getCurrentUtilDate();
	loginLog.setQuitTime(currentDate);
	baseDao.update("loginLog.updateQuitTimeOfLoginLog", loginLog);
    }

}
