package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OssMenu implements Serializable {

    private static final long serialVersionUID = -1369142696282669712L;
    /** 下级菜单，用于菜单树 */
    private List childOssMenu = new ArrayList();
    /** 用于修改 */
    private String menuId;
    /** 功能名称 */
    private String name;
    /** 功能编号 */
    private String ossMenuID;

    /** 功能URL */
    private String pageUrl;

    /**  */
    private String parentMenuID;

    /** 所属项目ID */
    private Integer projectId;
    /** 0:显示在左边菜单,1:页面内按钮 */
    private Integer type;

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (obj == this) {
	    return true;
	}
	if (obj.getClass() == this.getClass()) {
	    OssMenu ossMenu = (OssMenu) obj;
	    if (this.getOssMenuID().equals(ossMenu.getOssMenuID())) {
		return true;
	    }
	}
	return false;
    }

    public List getChildOssMenu() {
	return childOssMenu;
    }


    public String getName() {
	return name;
    }

    public String getOssMenuID() {
	return ossMenuID;
    }

    public String getPageUrl() {
	return pageUrl;
    }

    public String getParentMenuID() {
	return parentMenuID;
    }

    public Integer getProjectId() {
	return projectId;
    }

    public Integer getType() {
	return type;
    }

    @Override
    public int hashCode() {
	return ossMenuID.hashCode();
    }

    public void setChildOssMenu(List childOssMenu) {
	this.childOssMenu = childOssMenu;
    }


    public void setName(String name) {
	this.name = name;
    }

    public void setOssMenuID(String ossMenuID) {
	this.ossMenuID = ossMenuID;
    }

    public void setPageUrl(String pageUrl) {
	this.pageUrl = pageUrl;
    }

    public void setParentMenuID(String parentMenuID) {
	this.parentMenuID = parentMenuID;
    }

    public void setProjectId(Integer projectId) {
	this.projectId = projectId;
    }

    public void setType(Integer type) {
	this.type = type;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
    
}
