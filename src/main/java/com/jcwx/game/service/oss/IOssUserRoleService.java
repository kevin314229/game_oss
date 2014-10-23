package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.OssUserRole;

public interface IOssUserRoleService {

    /** 创建新角色管理员映射 */
    public void createOssUserRole(OssUserRole ossUserRole);

    /** 删除指定角色的所有授权用户映射 */
    public Integer deleteOssUserRoleByRoleId(Integer ossUserRoleID);

    /** 删除用户的所有角色映射 */
    public Integer deleteOssUserRoleByUserID(String username);

    /** 用户的所有角色 */
    public List getOssUserByRoleUserID(String username);

    /** 角色的所有授权用户映射 */
    public List getOssUserRoleByRoleID(Integer ossUserRoleID);

    /** 映射列表 */
    public List<OssUserRole> getOssUserRoleList();

    /** 更新映射 */
    public Integer updateOssUserRole(OssUserRole ossUserRole);

}
