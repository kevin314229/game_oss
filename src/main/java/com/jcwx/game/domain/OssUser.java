package com.jcwx.game.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OssUser implements Serializable {

    private static final long serialVersionUID = -6899365052450544874L;
    /** 平台标识 */
    private String carrierOperator;
    /** 创建时间 */
    private Date createTime;
    /** 是否是运营商用户 "0" 否“1”是 **/
    private String isOperator;
    /** 最后一次登录IP */
    private String lastLoginIp;
    /** 最后一次登录时间 */
    private Date lastLoginTime;
    /** 登录次数 */
    private Integer loginNum;
    /** 已分配角色 */
    private List<OssRole> ossRoleList;
    /** 密码 */
    private String password;
    /** 实名 */
    private String realnames;
    /** 状态 */
    private Integer status;

    /** 帐号 */
    private String username;

    public String getCarrierOperator() {
	return carrierOperator;
    }

    public Date getCreateTime() {
	return createTime;
    }

    public String getIsOperator() {
	return isOperator;
    }

    public String getLastLoginIp() {
	return lastLoginIp;
    }

    public Date getLastLoginTime() {
	return lastLoginTime;
    }

    public Integer getLoginNum() {
	return loginNum;
    }

    public List<OssRole> getOssRoleList() {
	return ossRoleList;
    }

    public String getPassword() {
	return password;
    }

    public String getRealnames() {
	return realnames;
    }

    public Integer getStatus() {
	return status;
    }

    public String getUsername() {
	return username;
    }

    public void setCarrierOperator(String carrierOperator) {
	this.carrierOperator = carrierOperator;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public void setIsOperator(String isOperator) {
	this.isOperator = isOperator;
    }

    public void setLastLoginIp(String lastLoginIp) {
	this.lastLoginIp = lastLoginIp;
    }

    public void setLastLoginTime(Date lastLoginTime) {
	this.lastLoginTime = lastLoginTime;
    }

    public void setLoginNum(Integer loginNum) {
	this.loginNum = loginNum;
    }

    public void setOssRoleList(List<OssRole> ossRoleList) {
	this.ossRoleList = ossRoleList;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setRealnames(String realnames) {
	this.realnames = realnames;
    }

    public void setStatus(Integer status) {
	this.status = status;
    }

    public void setUsername(String username) {
	this.username = username;
    }
}
