package com.jcwx.game.admin.oss.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jcwx.game.domain.Project;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_oss_menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OssMenu implements Serializable {

    /** 功能名称 */
    @Column(name = "NAME")
    private String name;
    /** 功能编号 */
    @Id
    @GeneratedValue
    @Column(name = "OSS_MENU_ID")
    private String ossMenuID;

    /** 用于修改 */
    @Transient
    private String menuId;

    /** 功能URL */
    @Column(name = "PAGE_URL")
    private String pageUrl;

    /** 所属项目ID */
    /* @
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentMenu", fetch = FetchType.EAGER)
    private Set<OssMenu> children = new LinkedHashSet<OssMenu>();

   ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "PARENT_MENU_ID")
    private OssMenu parentMenu;*/

    /** 所属项目ID */
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;
    /** 0:显示在左边菜单,1:页面内按钮 */
    @Column(name = "type")
    private Integer type;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getOssMenuID() {
	return ossMenuID;
    }

    public void setOssMenuID(String ossMenuID) {
	this.ossMenuID = ossMenuID;
    }

    public String getMenuId() {
	return menuId;
    }

    public void setMenuId(String menuId) {
	this.menuId = menuId;
    }

    public String getPageUrl() {
	return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
	this.pageUrl = pageUrl;
    }

    public Project getProject() {
	return project;
    }

    public void setProject(Project project) {
	this.project = project;
    }

    public Integer getType() {
	return type;
    }

    public void setType(Integer type) {
	this.type = type;
    }

    @Override
    public String toString() {
	return "OssMenu [name=" + name + ", ossMenuID=" + ossMenuID
		+ ", menuId=" + menuId + ", pageUrl=" + pageUrl + ", project="
		+ project + ", type=" + type + "]";
    }
    
    
}
