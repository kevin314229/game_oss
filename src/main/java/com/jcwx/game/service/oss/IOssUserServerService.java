package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.OssUserServer;

public interface IOssUserServerService {
    /** 创建新游戏区管理员映射 */
    public void createOssUserServer(OssUserServer ossUserServer);

    /** 删除指定游戏区的所有授权用户映射 */
    public Integer deleteOssUserServerByID(Integer id);

    /** 删除用户的所有游戏区映射 */
    public Integer deleteOssUserServerByServerID(Integer serverId);

    /** 删除用户关联的服务器 */
    Integer deleteOssUserServerByUsername(String username);

    public List<OssServer> getOssServerListByUser(String username);

    /** 获取该项目下的 服务器列表 **/
    public List<OssServer> getOssServerListByUserAndProjectId(String username,
	    Integer projectId);

    /** 根据平台缩写和用户名获取列表 */
    public List<OssServer> getOssServerListByUserAndProjectIdAndPt(
	    String username, String serverCode, Integer projectId);

    /** 根据平台缩写和用户名获取列表 */
    public List<OssServer> getOssServerListByUserAndPt(String username,
	    String serverCode);

    /** 获取平台入口列表 */
    public List<OssServer> getOssServerPtListByUser(String username);

    /** 获取平台入口列表 */
    public List<OssServer> getOssServerPtListByUserAndProjectId(
	    String username, Integer projectId);

    /** 用户的所有游戏区 */
    public List<OssUserServer> getOssUserByServerUserID(Integer id);

    /** 游戏区的所有授权用户映射 */
    public List<OssUserServer> getOssUserServerByID(Integer id);

    /**
     * 找到用户分配的服务器，2014/01/16注
     **/
    public List<OssUserServer> getOssUserServerByUsername(String username);

    /** 映射列表 */
    public List<OssUserServer> getOssUserServerList();

    public List<OssUserServer> getOssUserServerListGrouyById(Integer id);

    /**
     * 获取用户管理的服务器
     * 
     * @param username
     * @param serverId
     * @return
     */
    public OssServer getUserOssServer(String username, Integer serverId);

    /** 更新映射 */
    public Integer updateOssUserServer(OssUserServer ossUserServer);
}
