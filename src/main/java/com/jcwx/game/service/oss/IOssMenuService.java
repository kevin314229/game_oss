package com.jcwx.game.service.oss;

import java.util.List;

import com.jcwx.game.domain.OssMenu;

public interface IOssMenuService {
    /** 创建新菜单 */
    public String createOssMenu(OssMenu ossMenu);

    /** 构建菜单树 */
    public List<OssMenu> createOssMenuTree(List<OssMenu> list);

    /** 删除菜单 */
    public Integer deleteOssMenuByID(String ossMenuID);

    /** 获取指定菜单 */
    public OssMenu getOssMenuByID(String ossMenuID);

    /**
     * 获得当前子集菜单最后一个
     * 
     * @param Oss_Menu_Id
     * @param Project_id
     * @return
     */
    public String getOssMenuID(String parentMenuID, String Project_id);

    /** 菜单列表 */
    public List<OssMenu> getOssMenuList();

    /** 根据项目ID查询菜单列表 */
    public List<OssMenu> getOssMenuListByProjectId(Integer porjectID);

    /** ---------工具------------------------- */

    /**
     * 获得当前项目一级菜单集合
     * 
     * @return
     */
    public List<OssMenu> getOssMenuListByProjectIdAndParentMenuID(
	    Integer ProjectId, String ParentMenuID);

    /** 根据项目岗位ID查询菜单列表 */
    public List<OssMenu> getOssMenuListByProjectIdAndRoleId(Integer projectId,
	    Integer roleId);

    public List<OssMenu> getOssParentMenuListByProjectId(Integer ProjectId);

    /**
     * 根据用户名获取用户权限菜单
     * 
     * @param userName
     * @return
     */
    public List<OssMenu> getUserOssLeftMenuListByUserName(String userName);

    /**
     * 根据用户名获取用户权限菜单
     * 
     * @param userName
     * @return
     */
    public List<OssMenu> getUserOssMenuListByUserName(String userName);

    /**
     * 根据用户名获取用户项目管理权限菜单
     * 
     * @param userName
     * @return
     */
    public List<OssMenu> getUserOssMenuListByUserNameAndProjectId(
	    String userName, Integer projectId);

    /**
     * 根据用户名获取用户项目管理权限菜单 包含页面跳转的
     * 
     * @param userName
     * @return
     */
    public List<OssMenu> getUserOssMenuListsByUserNameAndProjectId(
	    String userName, Integer projectId, Integer type);

    /** 修改菜单 */
    public Integer updateOssMenu(OssMenu ossMenu);
    
    
    public OssMenu getOssMenuByPageUrl(Integer projectId,String pageUrl);
    

}
