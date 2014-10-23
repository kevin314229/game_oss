package com.jcwx.game.service.oss.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.OssMenu;
import com.jcwx.game.service.oss.IOssMenuService;

@SuppressWarnings("unchecked")
@Service
public class OssMenuService implements IOssMenuService {
    @Autowired
    private IBaseDAO baseDao;

    @Override
    public String createOssMenu(OssMenu ossMenu) {
	return (String) baseDao.insert("OssMenu.createOssMenu", ossMenu);
    }

    /** -------工具-------------- */
    @Override
    public List<OssMenu> createOssMenuTree(List<OssMenu> list) {
	List ossMenuList = new ArrayList();
	HashMap hashMap = new HashMap();
	for (OssMenu m : list) {
	    if (m.getParentMenuID().equals("0")) {// 一级菜单
		ossMenuList.add(m);
		hashMap.put(m.getOssMenuID(), m);
	    }
	}

	for (OssMenu m : list) {
	    Object obj = hashMap.get(m.getParentMenuID());
	    if (obj != null) {
		OssMenu ossMenu = (OssMenu) obj;
		ossMenu.getChildOssMenu().add(m);
	    }
	}

	return ossMenuList;
    }

    @Override
    public Integer deleteOssMenuByID(String ossMenuID) {
	return baseDao.delete("OssMenu.deleteOssMenuByID", ossMenuID);
    }

    @Override
    public OssMenu getOssMenuByID(String ossMenuID) {
	return (OssMenu) baseDao.queryForObject("OssMenu.getOssMenuByID",
		ossMenuID);
    }

    @Override
    public String getOssMenuID(String parentMenuID, String Project_id) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("parentMenuID", parentMenuID);
	params.put("Project_id", Project_id);
	return (String) baseDao.queryForObject("OssMenu.getOssMenuID", params);
    }

    @Override
    public List<OssMenu> getOssMenuList() {
	return baseDao.queryForList("OssMenu.getOssMenuList");
    }

    @Override
    public List<OssMenu> getOssMenuListByProjectId(Integer projectId) {
	return baseDao.queryForList("OssMenu.getOssMenuListByProjectId",
		projectId);
    }

    @Override
    public List<OssMenu> getOssMenuListByProjectIdAndParentMenuID(
	    Integer ProjectId, String ParentMenuID) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("ProjectId", ProjectId);
	params.put("ParentMenuID", ParentMenuID);
	return baseDao.queryForList(
		"OssMenu.getOssMenuListByProjectIdAndParentMenuID", params);
    }

    @Override
    public List<OssMenu> getOssMenuListByProjectIdAndRoleId(Integer projectId,
	    Integer roleId) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("roleId", roleId);
	params.put("projectId", projectId);
	return baseDao.queryForList(
		"OssMenu.getOssMenuListByProjectIdAndRoleId", params);
    }

    @Override
    public List<OssMenu> getOssParentMenuListByProjectId(Integer ProjectId) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("ProjectId", ProjectId);
	return baseDao.queryForList("OssMenu.selectMenuParentList", params);

    }

    @Override
    public List<OssMenu> getUserOssLeftMenuListByUserName(String userName) {
	if (StringUtils.isEmpty(userName)) {
	    return null;
	}
	return baseDao.queryForList("OssMenu.getUserOssLeftMenuListByUserName",
		userName);
    }

    @Override
    public List<OssMenu> getUserOssMenuListByUserName(String userName) {
	if (StringUtils.isEmpty(userName)) {
	    return null;
	}
	return baseDao.queryForList("OssMenu.getUserOssMenuListByUserName",
		userName);
    }

    @Override
    public List<OssMenu> getUserOssMenuListByUserNameAndProjectId(
	    String userName, Integer projectId) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("userName", userName);
	params.put("projectId", projectId);
	return baseDao.queryForList(
		"OssMenu.getUserOssMenuListByUserNameAndProjectId", params);
    }

    @Override
    public List<OssMenu> getUserOssMenuListsByUserNameAndProjectId(
	    String userName, Integer projectId, Integer type) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("userName", userName);
	params.put("projectId", projectId);
	params.put("type", type);
	return baseDao.queryForList(
		"OssMenu.getUserOssMenuListsByUserNameAndProjectId", params);
    }

    @Override
    public Integer updateOssMenu(OssMenu ossMenu) {
	return baseDao.update("OssMenu.updateOssMenu", ossMenu);
    }
    @Override
    public OssMenu getOssMenuByPageUrl(Integer projectId , String pageUrl){
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("pageUrl", pageUrl);
	params.put("projectId", projectId);
	List<OssMenu> list=baseDao.queryForList("OssMenu.getOssMenuByPageUrl", params);
	return list.size()>0?list.get(0):null;
    }

}
