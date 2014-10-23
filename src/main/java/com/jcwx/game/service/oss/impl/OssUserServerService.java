package com.jcwx.game.service.oss.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.OssUserServer;
import com.jcwx.game.service.oss.IOssUserServerService;

@Service
public class OssUserServerService implements IOssUserServerService {
    @Autowired
    private IBaseDAO baseDao;

    @Override
    public void createOssUserServer(OssUserServer ossUserServer) {
	baseDao.insert("OssUserServer.createOssUserServer", ossUserServer);
    }

    @Override
    public Integer deleteOssUserServerByID(Integer id) {
	return baseDao.delete("OssUserServer.deleteOssUserServerByID", id);
    }

    @Override
    public Integer deleteOssUserServerByServerID(Integer serverId) {
	return baseDao.delete("OssUserServer.deleteOssUserServerByServerID",
		serverId);
    }

    @Override
    public Integer deleteOssUserServerByUsername(String username) {
	return baseDao.delete("OssUserServer.deleteOssUserServerByUsername",
		username);
    }

    @Override
    public List<OssServer> getOssServerListByUser(String username) {
	return baseDao.queryForList("OssServer.getOssServerListByUser",
		username);
    }

    @Override
    public List<OssServer> getOssServerListByUserAndProjectId(String username,
	    Integer projectId) {
	Map<String, Object> paraMap = new HashMap<String, Object>();
	paraMap.put("username", username);
	paraMap.put("projectId", projectId);
	return baseDao.queryForList(
		"OssServer.getOssServerListByUserAndProjectId", paraMap);
    }

    @Override
    public List<OssServer> getOssServerListByUserAndProjectIdAndPt(
	    String username, String serverCode, Integer projectId) {
	Map<String, Object> paraMap = new HashMap<String, Object>();
	paraMap.put("username", username);
	paraMap.put("serverCode", serverCode);
	paraMap.put("projectId", projectId);
	return baseDao.queryForList(
		"OssServer.getOssServerListByUserAndProjectIdAndPt", paraMap);
    }

    @Override
    public List<OssServer> getOssServerListByUserAndPt(String username,
	    String serverCode) {
	Map<String, Object> paraMap = new HashMap<String, Object>();
	paraMap.put("username", username);
	paraMap.put("serverCode", serverCode);
	return baseDao.queryForList("OssServer.getOssServerPtListByUserAndPt",
		paraMap);
    }

    @Override
    public List<OssServer> getOssServerPtListByUser(String username) {
	return baseDao.queryForList("OssServer.getOssServerPtListByUser",
		username);
    }

    @Override
    public List<OssServer> getOssServerPtListByUserAndProjectId(
	    String username, Integer projectId) {
	Map<String, Object> paraMap = new HashMap<String, Object>();
	paraMap.put("username", username);
	paraMap.put("projectId", projectId);
	return baseDao.queryForList(
		"OssServer.getOssServerPtListByUserAndProjectId", paraMap);
    }

    @Override
    public List<OssUserServer> getOssUserByServerUserID(Integer id) {
	return baseDao.queryForList("OssUserServer.getOssUserByServerUserID",
		id);
    }

    @Override
    public List<OssUserServer> getOssUserServerByID(Integer id) {
	return baseDao.queryForList("OssUserServer.getOssUserServerByID", id);
    }

    @Override
    public List<OssUserServer> getOssUserServerByUsername(String username) {
	return baseDao.queryForList(
		"OssUserServer.getOssUserServerListByUsername", username);
    }

    @Override
    public List<OssUserServer> getOssUserServerList() {
	return baseDao.queryForList("OssUserServer.getOssUserServerList");
    }

    @Override
    public List<OssUserServer> getOssUserServerListGrouyById(Integer id) {
	return baseDao.queryForList(
		"OssUserServer.getOssUserServerListGrouyById", id);
    }

    @Override
    public OssServer getUserOssServer(String username, Integer serverId) {
	Map<String, Object> paraMap = new HashMap<String, Object>();
	paraMap.put("username", username);
	paraMap.put("id", serverId);
	return (OssServer) baseDao.queryForObject(
		"OssServer.checkUserOssServer", paraMap);
    }

    @Override
    public Integer updateOssUserServer(OssUserServer ossUserServer) {
	return baseDao.update("OssUserServer.updateOssUserServer",
		ossUserServer);
    }

}
