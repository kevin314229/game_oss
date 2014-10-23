package com.jcwx.game.domain;

import java.io.Serializable;

public class OssRole implements Serializable {

    private static final long serialVersionUID = -8306538055639627867L;
    /**  */
    private Integer ossRoleID;
    /** 上机岗位ID */
    private Integer parentRoleId;
    /** 项目ID */
    private Integer projectId;
    /** 编码 */
    private String roleCode;
    /** 描述 */
    private String roleDesc;
    /** 角色名 */
    private String roleName;
    /** 类型 */
    private Integer roleType;

    public Integer getOssRoleID() {
	return ossRoleID;
    }

    public Integer getParentRoleId() {
	return parentRoleId;
    }

    public Integer getProjectId() {
	return projectId;
    }

    public String getRoleCode() {
	return roleCode;
    }

    public String getRoleDesc() {
	return roleDesc;
    }

    public String getRoleName() {
	return roleName;
    }

    public Integer getRoleType() {
	return roleType;
    }

    public void setOssRoleID(Integer ossRoleID) {
	this.ossRoleID = ossRoleID;
    }

    public void setParentRoleId(Integer parentRoleId) {
	this.parentRoleId = parentRoleId;
    }

    public void setProjectId(Integer projectId) {
	this.projectId = projectId;
    }

    public void setRoleCode(String roleCode) {
	this.roleCode = roleCode;
    }

    public void setRoleDesc(String roleDesc) {
	this.roleDesc = roleDesc;
    }

    public void setRoleName(String roleName) {
	this.roleName = roleName;
    }

    public void setRoleType(Integer roleType) {
	this.roleType = roleType;
    }

}
