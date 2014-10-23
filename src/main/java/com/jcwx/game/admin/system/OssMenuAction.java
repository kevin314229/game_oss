package com.jcwx.game.admin.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.CommonUtils;
import com.jcwx.game.common.JSONService;
import com.jcwx.game.common.SystemConfig;
import com.jcwx.game.domain.OssMenu;
import com.jcwx.game.domain.OssRole;
import com.jcwx.game.domain.OssRoleMenu;
import com.jcwx.game.service.oss.IOssMenuService;
import com.jcwx.game.service.oss.IOssRoleMenuService;
import com.jcwx.game.service.oss.IOssRoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/** 系统菜单 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class OssMenuAction extends BasalAction implements ModelDriven<OssMenu> {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String id;
    /** 系统菜单列表(树) */
    private List<OssMenu> menuAllListTree;

    private OssMenu model = new OssMenu();

    private OssMenu ossMenu;
    private List<OssMenu> ossMenuList;

    @Autowired
    private IOssMenuService ossMenuService;

    @Autowired
    private IOssRoleMenuService ossRoleMenuService;
    @Autowired
    private IOssRoleService ossRoleService;

    /** 跳转到添加页面 */
    @Action(value = "ossMenu_addIndex", results = { @Result(name = "success", location = "../../admin/system/menu/addOssMenu.jsp") })
    public String addIndex() throws Exception {
	return SUCCESS;
    }

    /**
     * 添加菜单
     * 
     * @return
     */
    @Action(value = "ossMenu_addOssMenu", results = { @Result(name = "success", location = "../../admin/system/menu/ossMenu_manage.jsp") })
    public void addOssMenu() {
	ossMenu.setOssMenuID(RandomStringUtils.randomNumeric(32));
	
	String menuId = ossMenu.getOssMenuID();
	ossMenu.setProjectId(getBaseAdminContext().getProject().getProjectId());

	String str = ossMenuService.createOssMenu(ossMenu);
	if (!"0".equals(ossMenu.getParentMenuID())) {
	    OssRoleMenu roleMenu = new OssRoleMenu();
	    List<OssRole> roles=ossRoleService.getOssRoleListByProjectIdAndUserName(getBaseAdminContext().getProject().getProjectId(),
		    getBaseAdminContext().getOssUser().getUsername());
	    for(OssRole ossRole:roles){
		if(ossRole.getParentRoleId()==0){
		    roleMenu.setOssRoleID(ossRole.getOssRoleID());
		}
	    }
	    roleMenu.setOssMenuID(menuId);
	    ossRoleMenuService.createOssRoleMenu(roleMenu);
	}

	if (str == null || str.equals("")) {
	    getPageMessage().setStatusCode(100);
	}
	getJSONResponse().responseJson(getPageMessage());
    }

    /** 浏览系统菜单 */
    @Action(value = "ossMenu_browseOssMenu", results = { @Result(name = "success", location = "../../admin/system/menu/browseOssMenu.jsp") })
    public String browseOssMenu() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}
	// IOssMenuService ossMenuService =
	// (IOssMenuService)SpringService.getBean("ossMenuService");
	List<OssMenu> menuAllListTemp = ossMenuService.getOssMenuList();
	menuAllListTree = ossMenuService.createOssMenuTree(menuAllListTemp);

	return SUCCESS;
    }

    /** 修改功能表 */
    @Action(value = "ossMenu_deleteOssMenu", results = { @Result(name = "success", location = "../../admin/system/menu/ossMenu_manage.jsp") })
    public void deleteOssMenu() throws Exception {
	String menuId = ossMenu.getOssMenuID();
	ossMenuService.deleteOssMenuByID(menuId);
	OssRoleMenu roleMenu = new OssRoleMenu();
	roleMenu.setOssMenuID(menuId);
	roleMenu.setOssRoleID(1);
	ossRoleMenuService.deleteOssRoleMenu(roleMenu);
	getJSONResponse().responseJson(getPageMessage());
    }

    /** 跳转到修改页面 */
    @Action(value = "ossMenu_editOssMenu", results = { @Result(name = "success", location = "../../admin/system/menu/updateOssMenu.jsp") })
    public String editOssMenu() throws Exception {
	ossMenu = ossMenuService.getOssMenuByID(ossMenu.getOssMenuID());
	ossMenuList = ossMenuService.getOssMenuListByProjectIdAndParentMenuID(
		getBaseAdminContext().getProject().getProjectId(), "0");
	return SUCCESS;
    }

    /** 管理二级菜单的路径映射 */
    @Action(value = "ossMenu_editSecondOssMenuMapping", results = { @Result(name = "success", location = "../../admin/system/menu/editSecondOssMenuMapping.jsp") })
    public String editSecondOssMenuMapping() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}
	// IOssMenuService ossMenuService =
	// (IOssMenuService)SpringService.getBean("ossMenuService");
	OssMenu ossMenuTemp = ossMenuService.getOssMenuByID(model
		.getOssMenuID());
	CommonUtils.copyPropertiesExtNull(model, ossMenuTemp);
	if (ossMenuTemp.getPageUrl() != null
		&& !"".equals(ossMenuTemp.getPageUrl())) {
	    List<JSONObject> jsonList = JSONService
		    .stringToJSONList(ossMenuTemp.getPageUrl());
	    ActionContext.getContext().put("jsonList", jsonList);
	}

	return SUCCESS;
    }

    public String getId() {
	return id;
    }

    public List<OssMenu> getMenuAllListTree() {
	return menuAllListTree;
    }

    // 采用模型驱动
    @Override
    public OssMenu getModel() {
	return model;
    }

    public OssMenu getOssMenu() {
	return ossMenu;
    }

    public List<OssMenu> getOssMenuList() {
	return ossMenuList;
    }

    /** 浏览功能表菜单 */
    @Action(value = "ossMenu_manage", results = { @Result(name = "success", location = "../../admin/system/menu/ossMenu_manage.jsp") })
    public String manage() throws Exception {
	Integer ProjectId = getBaseAdminContext().getProject().getProjectId();
	menuAllListTree = ossMenuService
		.getOssParentMenuListByProjectId(ProjectId);

	if (menuAllListTree != null && !menuAllListTree.isEmpty()) {
	    int n = menuAllListTree.size();
	    // String lastParentId = null;
	    OssMenu ossMenu = null;
	    for (int i = 0; i < n; i++) {
		ossMenu = menuAllListTree.get(i);
		List<OssMenu> temp = ossMenuService
			.getOssMenuListByProjectIdAndParentMenuID(ProjectId,
				ossMenu.getOssMenuID());

		if (temp != null && temp.size() > 0) {
		    menuAllListTree.addAll(i + 1, temp);
		    n += temp.size();
		    i += temp.size();
		}
	    }
	}
	// menuAllListTree =
	// ossMenuService.getOssMenuListByProjectId(baseAdminContext.getProject().getProjectId());
	return SUCCESS;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setMenuAllListTree(List<OssMenu> menuAllListTree) {
	this.menuAllListTree = menuAllListTree;
    }

    public void setOssMenu(OssMenu ossMenu) {
	this.ossMenu = ossMenu;
    }

    public void setOssMenuList(List<OssMenu> ossMenuList) {
	this.ossMenuList = ossMenuList;
    }

    /** 修改功能表 */
    @Action(value = "ossMenu_updateOssMenu", results = { @Result(name = "success", location = "../../admin/system/menu/ossMenu_manage.jsp") })
    public void updateOssMenu() throws Exception {
	String menuId = ossMenu.getOssMenuID();
	int projectId = getBaseAdminContext().getProject().getProjectId();

	if (id.equals(ossMenu.getParentMenuID())) {
	    ossMenu.setParentMenuID(id);
	} else {
	    Integer OssMenuID = Integer.valueOf(ossMenuService.getOssMenuID(
		    ossMenu.getParentMenuID(), projectId + ""));
	    String menu = String.valueOf(OssMenuID + 1);
	    if (projectId == 1)
		ossMenu.setOssMenuID("0" + menu);
	    else {
		ossMenu.setOssMenuID(menu);
	    }
	}
	ossMenu.setProjectId(projectId);
	ossMenu.setMenuId(menuId);
	ossMenuService.updateOssMenu(ossMenu);

	OssRoleMenu roleMenu = new OssRoleMenu();
	roleMenu.setOssRoleID(1);
	roleMenu.setOssMenuID(ossMenu.getOssMenuID());
	roleMenu.setMenuId(menuId);
//	ossRoleMenuService.updateOssRoleMenu(roleMenu);
	getJSONResponse().responseJson(getPageMessage());
    }

    /** 修改二级菜单的路径映射 */
    @Action(value = "ossMenu_updateSecondOssMenuMapping", results = { @Result(name = "toEditSecondOssMenuMapping", type = "redirectAction", params = {
	    "actionName", "ossMenu_editSecondOssMenuMapping", "namespace",
	    "/admin/base", "actionMsg", "${actionMsg}", "ossMenuID",
	    "${ossMenuID}" }) })
    public String updateSecondOssMenuMapping() throws Exception {

	if (SystemConfig.devMode == false) {
	    setActionMsg("修改功能已关闭，此功能只在开发过程中开放！");
	    return "toEditSecondOssMenuMapping";
	}
	setActionMsg("修改失败！");
	HttpServletRequest request = ServletActionContext.getRequest();
	String[] maping = request.getParameterValues("m");
	String[] desc = request.getParameterValues("d");
	if (maping != null && desc != null) {
	    List<JSONObject> list = new ArrayList<JSONObject>();
	    for (int i = 0; i < maping.length; i++) {
		if (!maping[i].trim().equals("")) {
		    JSONObject o = new JSONObject();
		    o.put("url", maping[i]);
		    if (desc != null && desc[i] != null) {
			o.put("desc", desc[i]);
		    }
		    list.add(o);
		}
	    }

	    String jsonString = JSONService.JSONListToString(list);
	    // IOssMenuService ossMenuService =
	    // (IOssMenuService)SpringService.getBean("ossMenuService");
	    OssMenu ossMenuTemp = ossMenuService.getOssMenuByID(model
		    .getOssMenuID());
	    if (list.size() != 0) {
		ossMenuTemp.setPageUrl(jsonString);
	    } else {
		ossMenuTemp.setPageUrl(null);
	    }
	    ossMenuService.updateOssMenu(ossMenuTemp);
	    setActionMsg("修改成功！");
	}

	return "toEditSecondOssMenuMapping";
    }

}
