package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.OperationServer;

public interface IOperationServerServer {

    /**
     * 创建OperationServer
     * 
     * @param operationServer
     * @return
     */
    public void createOperationServer(OperationServer operationServer);

    /**
     * 通过主键ID删除OperationServer
     * 
     * @param operationServerId
     * @return 数据影响条数
     */
    public void deleteOperationServerByID(String operationServerId);

    /**
     * 通过运营商ID删除OperationServer
     * 
     * @param operationServerId
     * @return 数据影响条数
     */
    public void deleteOperationServerByoperationID(String operationId);

    /**
     * 通过主键ID查询OperationServer
     * 
     * @param operationServerId
     * @return OperationServer
     */
    public OperationServer getOperationServerByID(String operationServerId);

    /**
     * 根据运营商查询所有的OperationServer
     * 
     * @return OperationServer的集合
     */
    public List<OperationServer> getOperationServerByOperationID(int operationId);

    /**
     * 修改OperationServer
     * 
     * @param operationServer
     * @return
     */
    public void updateOperationServer(OperationServer operationServer);

}
