package com.jcwx.game.service.oss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcwx.game.dao.IBaseDAO;
import com.jcwx.game.domain.OperationServer;
import com.jcwx.game.service.oss.IOperationServerServer;

@Service
public class OperationServerServer implements IOperationServerServer {

    @Autowired
    private IBaseDAO baseDao;

    @Override
    public void createOperationServer(OperationServer operationServer) {
	baseDao.insert("OperationServer.createOperationServer", operationServer);

    }

    @Override
    public void deleteOperationServerByID(String operationServerId) {
	baseDao.delete("OperationServer.deleteOperationServerByID",
		operationServerId);
    }

    @Override
    public void deleteOperationServerByoperationID(String operationId) {
	baseDao.delete("OperationServer.deleteOperationServerByoperationID",
		operationId);
    }

    @Override
    public OperationServer getOperationServerByID(String operationServerId) {
	return (OperationServer) baseDao.queryForObject(
		"OperationServer.getOperationServerByID", operationServerId);
    }

    @Override
    public List getOperationServerByOperationID(int operationId) {
	return baseDao.queryForList(
		"OperationServer.getOperationServerListByOperationID",
		operationId);
    }

    @Override
    public void updateOperationServer(OperationServer operationServer) {
	baseDao.update("OperationServer.updateOperationServer", operationServer);
    }

}
