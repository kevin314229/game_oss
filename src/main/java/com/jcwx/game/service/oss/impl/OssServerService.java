package com.jcwx.game.service.oss.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.CenterServer;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.OssUserLastServer;
import com.jcwx.game.domain.OssUserServer;
import com.jcwx.game.service.oss.IOssServerService;

@Service
public class OssServerService implements IOssServerService {

    @Autowired
    private IBaseDAO baseDao;

    @Override
    public void createOssLastServer(Integer gameId, Integer areaId,
	    String userName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameId", gameId);
	params.put("userName", userName);
	params.put("areaId", areaId);
	baseDao.insert("OssServer.createOssLastServer", params);
    }

    @Override
    public void createOssServer(OssServer ossServer) {
	baseDao.insert("OssServer.createOssServer", ossServer);
    }

    @Override
    public void createOssUserServer(OssUserServer ossUserServer) {
	baseDao.insert("OssUserServer.createOssUserServer", ossUserServer);
    }

    @Override
    public int deleteOssServerByID(Integer id) {
	return baseDao.delete("OssServer.deleteOssServerByID", id);
    }

    @Override
    public List<OssServer> findAllOssServer() {
	return baseDao.queryForList("OssServer.getOssServerList");
    }

    @Override
    public List<OssServer> findAllOssServerByProjectId(Integer projectId) {
	return baseDao.queryForList("OssServer.getOssServerListByProjectId",
		projectId);
    }

    @Override
    public OssUserLastServer getOssLastServerByID(Integer gameId,
	    String userName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameId", gameId);
	params.put("userName", userName);
	return (OssUserLastServer) baseDao.queryForObject(
		"OssServer.getOssLastServerByID", params);

    }

    @Override
    public OssServer getOssServerByID(Integer integer) {
	return (OssServer) baseDao.queryForObject("OssServer.getOssServerByID",
		integer);
    }

    @Override
    public List<OssServer> getOssServerList() {
	return baseDao.queryForList("OssServer.getOssServerList");
    }

    @Override
    public List<OssServer> getOssServerListByOperationId(int operationId) {
	return baseDao.queryForList("OssServer.getOssServerListByOperationId",
		operationId);
    }

    @Override
    public List<OssServer> getOssServerListByProjectIdAndOperationId(
	    int projectId, int operationId) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("projectId", projectId);
	params.put("operationId", operationId);
	return baseDao.queryForList(
		"OssServer.getOssServerListByProjectIdAndOperationId", params);
    }

    @Override
    public void updateOssLastServer(Integer gameId, Integer areaId,
	    String userName) {
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("gameId", gameId);
	params.put("userName", userName);
	params.put("areaId", areaId);
	baseDao.update("OssServer.updateOssLastServer", params);
    }

    @Override
    public Integer updateOssServer(OssServer ossServer) {
	return baseDao.update("OssServer.updateOssServer", ossServer);
    }

    @Override
    public void updateCenterServer(CenterServer centerServer) {
	// TODO Auto-generated method stub
	  baseDao.update("OssServer.updateCenterServer", centerServer);
    }

    @Override
    public void insertCenterServer(CenterServer centerServer) {
	  baseDao.insert("OssServer.insertCenterServer", centerServer);
	 
    }
    public void updateOssUserServer(CenterServer centerServer) {
	// TODO Auto-generated method stub
	  baseDao.update("OssServer.updateOssUserServer", centerServer);
    }

    public void insertOssUserServer(CenterServer centerServer) {
	  baseDao.insert("OssServer.insertOssUserServer", centerServer);
	 
    }
}
