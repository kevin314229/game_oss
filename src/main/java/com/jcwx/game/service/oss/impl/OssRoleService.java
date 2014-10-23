package com.jcwx.game.service.oss.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.OssRole;
import com.jcwx.game.service.oss.IOssRoleService;

@Service
public class OssRoleService implements IOssRoleService {
    @Autowired
    private IBaseDAO baseDao;

    @Override
    public Integer createOssRole(OssRole ossRole) {
	return (Integer) baseDao.insert("OssRole.createOssRole", ossRole);
    }

    @Override
    public Integer deleteOssRoleByID(Integer ossRoleID) {
	return baseDao.delete("OssRole.deleteOssRoleByID", ossRoleID);

    }

    @Override
    public OssRole getOssRoleByID(Integer ossRoleID) {
	return (OssRole) baseDao.queryForObject("OssRole.getOssRoleByID",
		ossRoleID);
    }

    @Override
    public OssRole getOssRoleByProjectIdAndUserName(Integer projectId,
	    String userName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("projectId", projectId);
	params.put("userName", userName);
	return (OssRole) baseDao.queryForObject(
		"OssRole.getOssRoleByProjectIdAndUserName", params);
    }

    @Override
    public List<OssRole> getOssRoleList() {
	return baseDao.queryForList("OssRole.getOssRoleList");
    }

    @Override
    public List<OssRole> getOssRoleListByProjectId(Integer projectId) {
	return baseDao.queryForList("OssRole.getOssRoleListByProjectId",
		projectId);
    }

    @Override
    public List<OssRole> getOssRoleListByProjectIdAndUserName(
	    Integer projectId, String userName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("projectId", projectId);
	params.put("userName", userName);
	return baseDao.queryForList("OssRole.getOssRoleByProjectIdAndUserName",
		params);
    }

    @Override
    public Integer updateOssRole(OssRole ossRole) {
	return baseDao.update("OssRole.updateOssRole", ossRole);

    }

}
