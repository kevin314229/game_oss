package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.OssUser;
import com.jcwx.game.domain.OssUserServer;

public interface IOssUserService {

    /**
     * 创建oss用户
     * 
     * @param ossUser
     */
    public void createOssUser(OssUser ossUser);

    /** 删除指定管理员 */
    public Integer deleteOssUserByID(String username);

    /**
     * 获得oss用户
     * 
     * @param username
     * @return
     */
    public OssUser getOssUserByID(String username);

    /** 用户列表 */
    public List<OssUser> getOssUserList();

    public List<OssUserServer> getOssUserServerListGrouyById(Integer id);

    /** 修改管理员 */
    public Integer updateOssUser(OssUser ossUser);

    /**
     * 修改用户最后登录信息
     * 
     * @param ossUser
     */
    public void updateOssUserLastLoginInfo(OssUser ossUser);

    /**
     * 修改密码
     * 
     * @param ossUser
     */
    public void updateOssUserPassword(OssUser ossUser);

}