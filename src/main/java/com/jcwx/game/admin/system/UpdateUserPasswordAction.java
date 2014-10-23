package com.jcwx.game.admin.system;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.MD5Service;
import com.jcwx.game.domain.OssUser;
import com.jcwx.game.service.oss.IOssUserService;

@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/admin/system")
@ResultPath("/")
public class UpdateUserPasswordAction extends BasalAction {

    /** 确认新密码 */
    private String confirmNewPassword;

    /** 用户新密码 */
    private String newPassword;

    @Autowired
    private IOssUserService ossUserService;

    /** 用户密码 */
    private String password;

    @Override
    @Action(value = "password_updateUserPassword", results = { @Result(name = "success", location = "../../admin/system/updateUserPassword.jsp") })
    public String execute() throws Exception {

	// 确认密码一致
	if (newPassword == null || "".equals(newPassword)
		|| !newPassword.equals(confirmNewPassword)) {
	    newPassword = "";
	    confirmNewPassword = "";
	    setErrorInfo("两次输入的新密码不正确，请重新输入！");
	    return "success";
	}

	// 登录环境
	// 验证密码
	// IOssUserService ossUserService =
	// (IOssUserService)SpringService.getBean("ossUserService");
	OssUser ossUser = ossUserService.getOssUserByID(getBaseAdminContext()
		.getOssUser().getUsername());
	String md5Password = MD5Service.encryptString(password);
	if (!ossUser.getPassword().equals(md5Password)) {
	    password = "";
	    setErrorInfo("旧密码错误，请重新输入！");
	    return "success";
	}

	// 修改密码
	ossUser.setPassword(MD5Service.encryptString(newPassword));
	ossUserService.updateOssUserPassword(ossUser);
	setSuccessInfo("密码修改成功！");
	return "success";
    }

    public String getConfirmNewPassword() {
	return confirmNewPassword;
    }

    public String getNewPassword() {
	return newPassword;
    }

    public String getPassword() {
	return password;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
	this.confirmNewPassword = confirmNewPassword;
    }

    public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Action(value = "password_index", results = { @Result(name = "success", location = "../../admin/system/updateUserPassword.jsp") })
    public String updatePasswordIndex() {
	return "success";
    }

}
