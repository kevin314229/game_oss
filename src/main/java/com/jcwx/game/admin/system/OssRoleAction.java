package com.jcwx.game.admin.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.Validate;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.CommonUtils;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.domain.OssMenu;
import com.jcwx.game.domain.OssRole;
import com.jcwx.game.domain.OssRoleMenu;
import com.jcwx.game.domain.OssUser;
import com.jcwx.game.domain.OssUserRole;
import com.jcwx.game.service.oss.IOssMenuService;
import com.jcwx.game.service.oss.IOssRoleMenuService;
import com.jcwx.game.service.oss.IOssRoleService;
import com.jcwx.game.service.oss.IOssUserRoleService;
import com.jcwx.game.service.oss.IOssUserService;
import com.opensymphony.xwork2.ModelDriven;

/** Oss角色 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class OssRoleAction extends BasalAction implements ModelDriven<OssRole> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 左边选择列表 */
    private String[] leftSideCartoonCharacters;

    private String level;

    /** 系统菜单列表(树) */
    private List<OssMenu> menuAllListTree;

    private OssRole model = new OssRole();

    @Autowired
    private IOssMenuService ossMenuService;

    /** 角色列表 */
    private List<OssRole> ossRoleList;

    @Autowired
    private IOssRoleMenuService ossRoleMenuService;

    @Autowired
    private IOssRoleService ossRoleService;

    /** 未授权管理员列表 */
    private List<OssUser> ossUserOffList;

    /** 已授权管理员列表 */
    private List<OssUser> ossUserOnList;

    /** 右边选择列表 */
    private String[] rightSideCartoonCharacters;

    /** 选中的菜单集合 */
    private String[] selectMenuArray;

    private String treeString;

    @Action(value = "ossRole_addOssRole", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "ossRole_browseOssRole", "namespace",
		    "/admin/base", "actionMsg", "${actionMsg}" }),
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "ossRole_browseOssRole", "namespace",
		    "/admin/base", "errorInfo", "${errorInfo}" }) })
    public String addOssRole() {
	OssRole tempOssRole = new OssRole();
	tempOssRole.setParentRoleId(model.getParentRoleId());
	tempOssRole.setRoleCode(model.getRoleCode());
	tempOssRole.setRoleDesc(model.getRoleDesc());
	tempOssRole.setRoleName(model.getRoleName());
	tempOssRole.setProjectId(getBaseAdminContext().getProject()
		.getProjectId());
	tempOssRole.setRoleType(0);// 默认
	int k = ossRoleService.createOssRole(tempOssRole);
	if (k > 0) {
	    setActionMsg("添加成功！");
	} else {
	    setErrorInfo("添加失败！");
	    return ERROR;
	}
	return SUCCESS;
    }

    @Action(value = "ossRole_addIndex", results = { @Result(name = "success", location = "../../admin/system/role/addOssRole.jsp") })
    public String addRoleIndex() {
	return SUCCESS;
    }

    /** 角色用户映射 */
    @Action(value = "ossRole_addRoleUser", results = { @Result(name = "success", location = "../../admin/system/role/addRoleUser.jsp") })
    public String addRoleUser() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    addActionMessage(getActionMsg());
	}
	Validate.notNull(model.getOssRoleID(),
		"model.ossRoleID must not be null!");
	IOssUserService ossUserService = (IOssUserService) SpringService
		.getBean("ossUserService");
	IOssUserRoleService ossUserRoleService = (IOssUserRoleService) SpringService
		.getBean("ossUserRoleService");
	OssRole ossRoleTemp = ossRoleService.getOssRoleByID(model
		.getOssRoleID());
	BeanUtils.copyProperties(model, ossRoleTemp);
	List<OssUser> ossUserAllListTemp = ossUserService.getOssUserList();// 所有管理员
	List<OssUserRole> warrantListTemp = ossUserRoleService
		.getOssUserRoleByRoleID(model.getOssRoleID());// 该角色已授权的管理员
	ossUserOnList = new ArrayList<OssUser>();
	ossUserOffList = new ArrayList<OssUser>();
	for (OssUser u : ossUserAllListTemp) {
	    boolean b = false;
	    for (OssUserRole r : warrantListTemp) {
		if (r.getUsername().equals(u.getUsername())) {
		    b = true;
		    break;
		}
	    }
	    if (b) {// 已授权的管理员
		ossUserOnList.add(u);
	    } else {
		ossUserOffList.add(u);
	    }
	}

	return SUCCESS;
    }

    /**
     * 浏览所有角色
     * 
     * @throws Exception
     */
    @Action(value = "ossRole_browseOssRole", results = { @Result(name = "success", location = "../../admin/system/role/browseOssRole.jsp") })
    public String browseOssRole() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    addActionMessage(getActionMsg());
	}
	ossRoleList = ossRoleService
		.getOssRoleListByProjectId(getBaseAdminContext().getProject()
			.getProjectId());

	List<OssRole> ossRoles = ossRoleService
		.getOssRoleListByProjectIdAndUserName(getBaseAdminContext()
			.getProject().getProjectId(), getBaseAdminContext()
			.getOssUser().getUsername());

	Validate.isTrue(ossRoleList.size() > 0, "ossRole not found!projectid:"
		+ getBaseAdminContext().getProject().getProjectId()
		+ ";username:"
		+ getBaseAdminContext().getOssUser().getUsername());

	StringBuffer buffer = new StringBuffer();
	for (OssRole ossRole : ossRoles) {
	    buffer.append("<li><a onclick='javascript:checkRoleId("
		    + ossRole.getOssRoleID()
		    + ",0)' href='javascript:checkRoleId("
		    + ossRole.getOssRoleID() + ",0)' >" + ossRole.getRoleName()
		    + "</a>");
	    buffer.append(getChildrenList(ossRole, ossRoleList));
	    this.treeString = buffer.toString() + "</li>";
	}

	return SUCCESS;
    }

    /** 删除角色 */
    @Action(value = "ossRole_delOssRole", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "ossRole_browseOssRole", "namespace", "/admin/base",
	    "errorInfo", "${errorInfo}" }) })
    public String delOssRole() {

	ossRoleMenuService.deleteOssRoleMenuByRoleId(model.getOssRoleID());
	int k = ossRoleService.deleteOssRoleByID(model.getOssRoleID());
	getPageMessage().setNavTabId("w_角色管理");
	if (k > 0) {
//	    String msg = getPageMessage().ajaxForwardSuccess("删除成功！");
	    
	    getJSONResponse().responseJson(getPageMessage());
	    return null;
	} else {
	    getJSONResponse().responseJson(getPageMessage());
	    return null;
//	    return getPageMessage().ajaxForwardSuccess("删除失败！");
	}

    }

    /** 修改角色 */
    @Action(value = "ossRole_editOssRole", results = { @Result(name = "success", location = "../../admin/system/role/editOssRole.jsp") })
    public String editOssRole() throws Exception {
	OssRole tempOssRole = ossRoleService.getOssRoleByID(model
		.getOssRoleID());
	BeanUtils.copyProperties(model, tempOssRole);
	return SUCCESS;
    }

    // 添加
    /*
     * @Action(value = "ossRole_addOssRole", results = { @Result(name =
     * "success", location = "../../admin/system/role/addOssRole.jsp") })
     */

    // 树形菜单
    public String getChildrenList(OssRole ossRole, List<OssRole> ossRoleList) {
	StringBuffer buffer = new StringBuffer();
	for (int i = 0; i < ossRoleList.size(); i++) {
	    OssRole role = ossRoleList.get(i);
	    if (ossRole.getOssRoleID().intValue() == role.getParentRoleId()) {
		buffer.append("<li><a onclick='javascript:checkRoleId("
			+ role.getOssRoleID()
			+ ",1)' href='javascript:checkRoleId("
			+ role.getOssRoleID() + ",1)'>" + role.getRoleName()
			+ "</a>");
		buffer.append(getChildrenList(role, ossRoleList) == null ? "</li>"
			: getChildrenList(role, ossRoleList) + "</li>");
	    }
	}
	return buffer.length() > 0 ? "<ul>" + buffer.toString() + "</ul>"
		: null;
    }

    public String[] getLeftSideCartoonCharacters() {
	return leftSideCartoonCharacters;
    }

    public String getLevel() {
	return level;
    }

    public List<OssMenu> getMenuAllListTree() {
	return menuAllListTree;
    }

    // 采用模型驱动
    @Override
    public OssRole getModel() {
	return model;
    }

    public List<OssRole> getOssRoleList() {
	return ossRoleList;
    }

    public List<OssUser> getOssUserOffList() {
	return ossUserOffList;
    }

    public List<OssUser> getOssUserOnList() {
	return ossUserOnList;
    }

    public String[] getRightSideCartoonCharacters() {
	return rightSideCartoonCharacters;
    }

    public String[] getSelectMenuArray() {
	return selectMenuArray;
    }

    public String getTreeString() {
	return treeString;
    }

    /** 进入修改角色菜单映射 */
    /*
     * @Action(value = "ossRole_modifyOssRoleMenu", results = { @Result(name =
     * "success", location = "../../admin/system/role/modifyOssRoleMenu.jsp") })
     */
    @Action(value = "ossRole_modifyOssRoleMenu", results = { @Result(name = "success", location = "../../admin/system/role/modifyOssRoleMenu.jsp") })
    public String modifyOssRoleMenu() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    addActionMessage(getActionMsg());
	}
	Integer roleId = 0;
	OssRole ossRole = ossRoleService.getOssRoleByID(model.getOssRoleID());
	if (this.level.equals("0")) {
	    roleId = ossRole.getOssRoleID();
	} else {
	    roleId = ossRole.getParentRoleId();
	}
	// 对象传递
	CommonUtils.copyPropertiesExtNull(model,
		ossRoleService.getOssRoleByID(model.getOssRoleID()));

	// 菜单列表
	List<OssMenu> menuAllListTemp = ossMenuService
		.getOssMenuListByProjectIdAndRoleId(getBaseAdminContext()
			.getProject().getProjectId(), roleId);
	menuAllListTree = ossMenuService.createOssMenuTree(menuAllListTemp);
	// 选中的菜单
	List<OssMenu> ossmenuListTemp = ossRoleMenuService
		.getOssMenuByRoleId(model.getOssRoleID());

	String[] selitem = new String[ossmenuListTemp.size()];
	for (int i = 0; i < ossmenuListTemp.size(); i++) {
	    OssMenu ossMenu = ossmenuListTemp.get(i);
	    selitem[i] = ossMenu.getOssMenuID();
	}
	selectMenuArray = selitem;
	return SUCCESS;
    }

    public void setLeftSideCartoonCharacters(String[] leftSideCartoonCharacters) {
	this.leftSideCartoonCharacters = leftSideCartoonCharacters;
    }

    public void setLevel(String level) {
	this.level = level;
    }

    public void setMenuAllListTree(List<OssMenu> menuAllListTree) {
	this.menuAllListTree = menuAllListTree;
    }

    public void setModel(OssRole model) {
	this.model = model;
    }

    public void setOssRoleList(List<OssRole> ossRoleList) {
	this.ossRoleList = ossRoleList;
    }

    public void setOssUserOffList(List<OssUser> ossUserOffList) {
	this.ossUserOffList = ossUserOffList;
    }

    public void setOssUserOnList(List<OssUser> ossUserOnList) {
	this.ossUserOnList = ossUserOnList;
    }

    public void setRightSideCartoonCharacters(
	    String[] rightSideCartoonCharacters) {
	this.rightSideCartoonCharacters = rightSideCartoonCharacters;
    }

    public void setSelectMenuArray(String[] selectMenuArray) {
	this.selectMenuArray = selectMenuArray;
    }

    public void setTreeString(String treeString) {
	this.treeString = treeString;
    }

    @Action(value = "ossRole_updateAddOssUser", results = { @Result(name = "success", type = "chain", location = "ossRole_addRoleUser") })
    public String updateAddOssUser() {
	IOssUserRoleService ossUserRoleService = (IOssUserRoleService) SpringService
		.getBean("ossUserRoleService");
	OssUserRole ossUserRoleTemp = null;
	ossUserRoleService.deleteOssUserRoleByRoleId(model.getOssRoleID());
	for (String username : rightSideCartoonCharacters) {// 已分配人员
	    ossUserRoleTemp = new OssUserRole();
	    ossUserRoleTemp.setOssRoleID(model.getOssRoleID());
	    ossUserRoleTemp.setUsername(username);
	    ossUserRoleService.createOssUserRole(ossUserRoleTemp);
	}

	setActionMsg("授权成功！");

	return SUCCESS;
    }

    /** 修改角色 */

    @Action(value = "ossRole_updateOssRole", results = { @Result(name = "toBrowseOssRole", type = "redirectAction", params = {
	    "actionName", "ossRole_browseOssRole", "namespace", "/admin/base",
	    "actionMsg", "${actionMsg}" }) })
    public String updateOssRole() {
	// IOssRoleService ossRoleService =
	// (IOssRoleService)SpringService.getBean("ossRoleService");
	OssRole tempOssRole = ossRoleService.getOssRoleByID(model
		.getOssRoleID());
	tempOssRole.setRoleCode(model.getRoleCode());
	tempOssRole.setRoleDesc(model.getRoleDesc());
	tempOssRole.setRoleName(model.getRoleName());
	ossRoleService.updateOssRole(tempOssRole);
	setActionMsg("修改成功!");
	return "toBrowseOssRole";
    }

    /** 修改角色的菜单映射 */
    @Action(value = "ossRole_updateOssRoleMenu", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "ossRole_browseOssRole", "namespace", "/admin/base",
	    "actionMsg", "${actionMsg}" }) })
    public String updateOssRoleMenu() {
	try {
	    // 删除原先的角色-菜单映射
	    ossRoleMenuService.deleteOssRoleMenuByRoleId(model.getOssRoleID());
	    // 添加新的映射
	    for (String menuId : selectMenuArray) {
		OssRoleMenu ossRoleMenu = new OssRoleMenu();
		ossRoleMenu.setOssRoleID(model.getOssRoleID());
		ossRoleMenu.setOssMenuID(menuId);
		ossRoleMenuService.createOssRoleMenu(ossRoleMenu);
	    }
	    getPageMessage().setNavTabId("w_角色管理");
	    setActionMsg("修改成功");
	} catch (Exception e) {
	    setActionMsg("修改失败");
	}
	return SUCCESS;
    }

}
