package com.jcwx.game.service.oss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.OssMenu;
import com.jcwx.game.domain.OssRoleMenu;
import com.jcwx.game.service.oss.IOssRoleMenuService;

@Service
public class OssRoleMenuService implements IOssRoleMenuService {
    @Autowired
    private IBaseDAO baseDao;

    @Override
    public void createOssRoleMenu(OssRoleMenu ossRoleMenu) {
	baseDao.insert("OssRoleMenu.createOssRoleMenu", ossRoleMenu);

    }

    @Override
    public Integer deleteOssRoleMenu(OssRoleMenu ossRoleMenu) {
	return baseDao.delete("OssRoleMenu.deleteOssRoleMenu", ossRoleMenu);
    }

    @Override
    public Integer deleteOssRoleMenuByRoleId(Integer roleId) {
	return baseDao.delete("OssRoleMenu.deleteOssRoleMenuByRoleId", roleId);
    }

    @Override
    public List<OssMenu> getOssMenuByRoleId(Integer roleId) {
	return baseDao.queryForList("OssRoleMenu.getOssMenuByRoleId", roleId);
    }

    @Override
    public List<OssRoleMenu> getOssRoleMenuList() {
	return baseDao.queryForList("OssRoleMenu.getOssRoleMenuList");
    }

    @Override
    public Integer updateOssRoleMenu(OssRoleMenu ossRoleMenu) {
	return baseDao.update("OssRoleMenu.updateOssRoleMenu", ossRoleMenu);
    }

}
