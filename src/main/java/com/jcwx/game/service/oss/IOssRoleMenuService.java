package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.OssMenu;
import com.jcwx.game.domain.OssRoleMenu;

public interface IOssRoleMenuService {

    /** 新建角色-系统功能映射 */
    public void createOssRoleMenu(OssRoleMenu ossRoleMenu);

    /** 删除角色-系统功能映射 */
    public Integer deleteOssRoleMenu(OssRoleMenu ossRoleMenu);

    /** 删除角色的所有功能映射 */
    public Integer deleteOssRoleMenuByRoleId(Integer roleId);

    /** 查询角色的所有功能 */
    public List<OssMenu> getOssMenuByRoleId(Integer roleId);

    /** 映射列表 */
    public List<OssRoleMenu> getOssRoleMenuList();

    /** 更新角色-系统功能映射 */
    public Integer updateOssRoleMenu(OssRoleMenu ossRoleMenu);

}
