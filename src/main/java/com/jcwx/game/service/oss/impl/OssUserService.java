package com.jcwx.game.service.oss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.OssUser;
import com.jcwx.game.domain.OssUserServer;
import com.jcwx.game.service.oss.IOssUserService;

@Service
public class OssUserService implements IOssUserService {

    @Autowired
    private IBaseDAO baseDao;

    @Override
    public void createOssUser(OssUser ossUser) {
	baseDao.insert("OssUser.createOssUser", ossUser);
    }

    @Override
    public Integer deleteOssUserByID(String username) {
	return baseDao.delete("OssUser.deleteOssUserByID", username);
    }

    @Override
    public OssUser getOssUserByID(String username) {
	return (OssUser) baseDao.queryForObject("OssUser.getOssUserByID",
		username);
    }

    @Override
    public List<OssUser> getOssUserList() {
	return baseDao.queryForList("OssUser.getOssUserList");
    }

    @Override
    public List<OssUserServer> getOssUserServerListGrouyById(Integer id) {
	return baseDao
		.queryForList("OssUser.getOssUserServerListGrouyById", id);
    }

    @Override
    public Integer updateOssUser(OssUser ossUser) {
	return baseDao.update("OssUser.updateOssUser", ossUser);
    }

    @Override
    public void updateOssUserLastLoginInfo(OssUser ossUser) {
	baseDao.update("OssUser.updateOssUserLastLoginInfo", ossUser);
    }

    @Override
    public void updateOssUserPassword(OssUser ossUser) {
	baseDao.update("OssUser.updateOssUserPassword", ossUser);
    }

}
