package com.jcwx.game.service.oss;

import java.util.List;

import org.hibernate.sql.Update;

import com.jcwx.game.domain.CenterServer;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.OssUserLastServer;
import com.jcwx.game.domain.OssUserServer;

public interface IOssServerService {

    /**
     * 创建Oss
     */
    public void createOssLastServer(Integer gameId, Integer areaId,
	    String userName);

    /**
     * 创建Oss
     */
    public void createOssServer(OssServer ossServer);

    public void createOssUserServer(OssUserServer ossUserServer);

    /**
     * 删除Oss
     */
    public int deleteOssServerByID(Integer integer);

    public List<OssServer> findAllOssServer();

    /** 根据项目ID */
    public List<OssServer> findAllOssServerByProjectId(Integer projectId);

    public OssUserLastServer getOssLastServerByID(Integer gameId,
	    String userName);

    /**
     * 获取Oss
     */
    public OssServer getOssServerByID(Integer integer);

    /**
     * 获取Oss列表
     */
    public List<OssServer> getOssServerList();

    /**
     * 根据平台ID 获取Oss列表
     */
    public List<OssServer> getOssServerListByOperationId(int operationId);

    public List<OssServer> getOssServerListByProjectIdAndOperationId(
	    int projectId, int operationId);

    public void updateOssLastServer(Integer gameId, Integer areaId,
	    String userName);

    /**
     * 更新Oss
     */
    public Integer updateOssServer(OssServer ossServer);
    
    
    public void updateCenterServer(CenterServer centerServer);
    
    public void insertCenterServer(CenterServer centerServer);
    
    public void updateOssUserServer(CenterServer centerServer) ;

    public void insertOssUserServer(CenterServer centerServer) ;
}
