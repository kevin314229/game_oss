package com.jcwx.game.service.oss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.OssUserRole;
import com.jcwx.game.service.oss.IOssUserRoleService;

@Service
public class OssUserRoleService implements IOssUserRoleService {
    @Autowired
    private IBaseDAO baseDao;

    @Override
    public void createOssUserRole(OssUserRole ossUserRole) {
	baseDao.insert("OssUserRole.createOssUserRole", ossUserRole);
    }

    @Override
    public Integer deleteOssUserRoleByRoleId(Integer ossUserRoleID) {
	return baseDao.delete("OssUserRole.deleteOssUserRoleByRoleId",
		ossUserRoleID);
    }

    /** 删除用户的所有角色映射 */
    @Override
    public Integer deleteOssUserRoleByUserID(String username) {
	return baseDao
		.delete("OssUserRole.deleteOssUserRoleByUserID", username);
    }

    @Override
    public List getOssUserByRoleUserID(String username) {
	return baseDao.queryForList("OssUserRole.getOssUserByRoleUserID",
		username);
    }

    @Override
    public List getOssUserRoleByRoleID(Integer ossUserRoleID) {
	return baseDao.queryForList("OssUserRole.getOssUserRoleByRoleID",
		ossUserRoleID);
    }

    @Override
    public List<OssUserRole> getOssUserRoleList() {
	return baseDao.queryForList("OssUserRole.getOssUserRoleList");
    }

    @Override
    public Integer updateOssUserRole(OssUserRole ossUserRole) {
	return baseDao.update("OssUserRole.updateOssUserRole", ossUserRole);
    }

}
