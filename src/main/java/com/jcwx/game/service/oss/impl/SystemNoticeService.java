/**
 * 
 */
package com.jcwx.game.service.oss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.SystemNotice;
import com.jcwx.game.service.oss.ISystemNoticeService;

/**
 * @author Administrator
 * 
 */
@Service
public class SystemNoticeService implements ISystemNoticeService {

    @Autowired
    private IBaseDAO baseDao;

    @Override
    public Integer createSystemNotice(SystemNotice systemNotice) {
	return (Integer) baseDao.insert("SystemNotice.createSystemNotice",
		systemNotice);
    }

    @Override
    public Integer deleteSystemNoticeByID(Integer systemNoticeId) {
	return baseDao.delete("SystemNotice.deleteSystemNoticeByID",
		systemNoticeId);
    }

    @Override
    public SystemNotice getSystemNoticeByID(Integer systemNoticeId) {
	return (SystemNotice) baseDao.queryForObject(
		"SystemNotice.getSystemNoticeByID", systemNoticeId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SystemNotice> getSystemNoticeList() {
	return baseDao.queryForList("SystemNotice.getSystemNoticeList");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SystemNotice> getSystemNoticeListByServerId(Integer serverId) {
	return baseDao.queryForList(
		"SystemNotice.getSystemNoticeListByServerId", serverId);
    }

    @Override
    public Integer updateSystemNotice(SystemNotice systemNotice) {
	return baseDao.update("SystemNotice.updateSystemNotice", systemNotice);
    }

}
