package com.jcwx.game.admin.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.CommonUtils;
import com.jcwx.game.common.MD5Service;
import com.jcwx.game.domain.OssRole;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.OssUser;
import com.jcwx.game.domain.OssUserRole;
import com.jcwx.game.domain.OssUserServer;
import com.jcwx.game.exception.ActionValidateException;
import com.jcwx.game.service.oss.IOssRoleService;
import com.jcwx.game.service.oss.IOssUserRoleService;
import com.jcwx.game.service.oss.IOssUserService;
import com.jcwx.game.service.oss.impl.OssServerService;
import com.jcwx.game.service.oss.impl.OssUserServerService;
import com.opensymphony.xwork2.ModelDriven;

/** 后台管理员 */
@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class OssUserAction extends BasalAction implements ModelDriven<OssUser> {

    private static final String AUTHORIZE_SUCCESS = "授权成功！";

    private static final Logger logger = Logger.getLogger(OssUserAction.class);

    private static final long serialVersionUID = 1L;

    /** 在线管理员列表 */
    private HashMap<String, HttpSession> adminOnlineSessionMap;

    /** OGNL表达式必须在后台传入该参数，否则调试模式报错。但仍然可以正常执行，无实际意义 */
    private String[] leftSideCartoonCharacters;

    private OssUser model = new OssUser();

    @Autowired
    private IOssRoleService ossRoleService;

    private List<OssServer> ossServerLeftList;

    private List<OssServer> ossServerList;

    @Autowired
    private OssServerService ossServerService;

    /** 管理员列表 */
    private List<OssUser> ossUserList;

    @Autowired
    private IOssUserRoleService ossUserRoleService;

    @Autowired
    private OssUserServerService ossUserServerService;

    @Autowired
    private IOssUserService ossUserService;

    /** OGNL表达式必须在后台传入该参数，否则调试模式报错。但仍然可以正常执行，无实际意义 */
    private String[] rightSideCartoonCharacters;

    /** OGNL表达式必须在后台传入该参数，否则调试模式报错。但仍然可以正常执行，无实际意义 */
    private String serverInfo;

    /** 管理员sessionID,用于踢下线 */
    private String sessionID;

    /**
     * 跳转到分配服务器页面，并初始化相关属性
     * */
    @Action(value = "ossUser_addOssServerOperationIndex", results = { @Result(name = "success", location = "../../admin/system/user/addOssUserServer.jsp") })
    public String addOssServerOperationIndex() {
	/** validate param */
	String username = getHttpServletRequest().getParameter("username");
	Validate.notBlank(username, "param username must not be null");
	/** OssUser model */
	model = ossUserService.getOssUserByID(username);
	/** struts control right list */
	ossServerList = ossUserServerService
		.getOssServerListByUserAndProjectId(username,
			getBaseAdminContext().getProject().getProjectId());
	/** struts control left list */
	ossServerLeftList = ossUserServerService
		.getOssServerListByUserAndProjectIdAndPt(getBaseAdminContext()
			.getOssUser().getUsername(), getBaseAdminContext()
			.getServerCode(), getBaseAdminContext().getProject()
			.getProjectId());

	return SUCCESS;
    }

    /** 新增管理员 */
    @Action(value = "ossUser_addOssUser", results = { @Result(name = "toBrowseOssUser", type = "redirectAction", params = {
	    "actionName", "ossUser_browseOssUser", "namespace", "/admin/base",
	    "actionMsg", "${actionMsg}" }) })
    public String addOssUser() throws Exception {
	OssUser ossuser = ossUserService.getOssUserByID(model.getUsername());
	if (ossuser != null) {
	    setActionMsg("账号已存在，请重新填写账号！");
	    model.setUsername("");
	} else {
	    OssUser tempOssUser = new OssUser();
	    tempOssUser.setCreateTime(new Date());
	    tempOssUser.setLastLoginIp(null);
	    tempOssUser.setLastLoginTime(null);
	    tempOssUser.setLoginNum(0);
	    tempOssUser.setPassword(MD5Service.encryptString(model
		    .getPassword()));
	    tempOssUser.setRealnames(model.getRealnames());
	    tempOssUser.setStatus(1);
	    tempOssUser.setCarrierOperator(model.getCarrierOperator());
	    tempOssUser.setIsOperator(model.getIsOperator());
	    tempOssUser.setUsername(model.getUsername());
	    ossUserService.createOssUser(tempOssUser);
	    setActionMsg("创建成功！");
	    model.setUsername("");
	    model.setRealnames("");
	    model.setPassword("");
	}
	return "toBrowseOssUser";
    }

    @Action(value = "ossUser_addOssUserIndex", results = { @Result(name = "success", location = "../../admin/system/user/addOssUser.jsp") })
    public String addUser_index() {
	return SUCCESS;
    }

    /**
     * 浏览所有管理员
     * 
     * @throws Exception
     */
    @Action(value = "ossUser_browseOssUser", results = { @Result(name = "success", location = "../../admin/system/user/browseOssUser.jsp") })
    public String browseOssUser() throws Exception {
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	    addActionMessage(getActionMsg());
	}
	ossUserList = ossUserService.getOssUserList();
	for (OssUser u : ossUserList) {// 查找管理员拥有的角色
	    List<OssRole> ossRoleListTemp = new ArrayList<OssRole>();
	    List<OssUserRole> tempList1 = ossUserRoleService
		    .getOssUserByRoleUserID(u.getUsername());// 该管理员的所有角色映射
	    for (OssUserRole r : tempList1) {
		OssRole ossRole = ossRoleService.getOssRoleByID(r
			.getOssRoleID());
		ossRoleListTemp.add(ossRole);
	    }
	    u.setOssRoleList(ossRoleListTemp);
	}
	return SUCCESS;
    }

    /** 删除管理员 */
    @Action(value = "ossUser_delOssUser", results = { @Result(name = "toBrowseOssUser", type = "redirectAction", params = {
	    "actionName", "ossUser_browseOssUser", "namespace", "/admin/base",
	    "actionMsg", "${actionMsg}" }) })
    public String delOssUser() {
	ossUserRoleService.deleteOssUserRoleByUserID(model.getUsername());// 删除角色映射
	int k = ossUserService.deleteOssUserByID(model.getUsername());
	if (k > 0) {
	    setActionMsg("删除成功！");
	} else {
	    setActionMsg("删除失败！");
	}
	return "toBrowseOssUser";

    }

    /** 修改管理员页面 */
    @Action(value = "ossUser_editOssUser", results = { @Result(name = "success", location = "../../admin/system/user/editOssUser.jsp") })
    public String editOssUser() throws Exception {
	OssUser ossUserTmp = ossUserService.getOssUserByID(model.getUsername());
	CommonUtils.copyPropertiesExtNull(model, ossUserTmp);
	return SUCCESS;
    }

    public HashMap<String, HttpSession> getAdminOnlineSessionMap() {
	return adminOnlineSessionMap;
    }

    public String[] getLeftSideCartoonCharacters() {
	return this.leftSideCartoonCharacters;
    }

    // 采用模型驱动
    @Override
    public OssUser getModel() {
	return model;
    }

    public List<OssServer> getOssServerLeftList() {
	return this.ossServerLeftList;
    }

    public List<OssServer> getOssServerList() {
	return this.ossServerList;
    }

    /**
     * 切换平台方法
     * 
     * @throws Exception
     */
    @Action(value = "ossUser_getOssServerPt")
    public void getOssServerPt() throws Exception {
	String serverCode = ServletActionContext.getRequest().getParameter(
		"serverCode");

	Validate.isTrue(StringUtils.isNotBlank(serverCode),
		"param serverCode must not be null");

	// 查询平台下的服务器
	setOssServerList(ossUserServerService
		.getOssServerListByUserAndProjectIdAndPt(getBaseAdminContext()
			.getOssUser().getUsername(), serverCode,
			getBaseAdminContext().getProject().getProjectId()));

	getJSONResponse().responseJson(getOssServerList());
    }

    public List<OssUser> getOssUserList() {
	return ossUserList;
    }

    public String[] getRightSideCartoonCharacters() {
	return this.rightSideCartoonCharacters;
    }

    public String getServerInfo() {
	return this.serverInfo;
    }

    public String getSessionID() {
	return sessionID;
    }

    /** 踢其它管理员下线 */
    @Action(value = "ossUser_kickOssUser", results = { @Result(name = "toOnlinOssUser", type = "redirectAction", params = {
	    "actionName", "ossUser_onlinOssUser", "namespace", "/admin/base",
	    "actionMsg", "${actionMsg}" }) })
    public String kickOssUser() {
	// adminOnlineSessionMap =
	// (HashMap<String,HttpSession>)CacheService.getFromCache(CacheConstant.ADMIN_ONLINE_SESSION_MAP);
	// HttpSession remoteSession = adminOnlineSessionMap.get(sessionID);
	// //通知被踢的管理员
	// HttpSession localSession =
	// ServletActionContext.getRequest().getSession();
	// BaseAdminContext baseAdminContext =
	// (BaseAdminContext)localSession.getAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY);
	//
	// remoteSession.removeAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY);
	// remoteSession.setAttribute(AdminSystemConstant.ADMIN_SESSION_COMMUNICATION_MESSAGE,
	// "Sorry,你被管理员:"+baseAdminContext.getOssUser().getUsername()+" 踢下线,如有疑问，请联系他！");
	//
	// adminOnlineSessionMap.remove(sessionID);
	// actionMsg = "操作成功！";
	return "toOnlinOssUser";
    }

    /** 在线管理员列表 */
    @Action(value = "ossUser_onlinOssUser", results = { @Result(name = "success", location = "../../admin/system/user/onlinOssUser.jsp") })
    public String onlinOssUser() throws Exception {

	// if(actionMsg!=null && !"".equals(actionMsg)){
	// actionMsg = new String(actionMsg.getBytes("ISO8859-1"),"utf-8");
	// addActionMessage(actionMsg);
	// }
	//
	// adminOnlineSessionMap =
	// (HashMap<String,HttpSession>)CacheService.getFromCache(CacheConstant.ADMIN_ONLINE_SESSION_MAP);
	// for(String s : adminOnlineSessionMap.keySet()){
	// HttpSession session = adminOnlineSessionMap.get(s);
	// BaseAdminContext baseAdminContext =
	// (BaseAdminContext)session.getAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY);
	// }
	return SUCCESS;
    }

    public void setAdminOnlineSessionMap(
	    HashMap<String, HttpSession> adminOnlineSessionMap) {
	this.adminOnlineSessionMap = adminOnlineSessionMap;
    }

    public void setLeftSideCartoonCharacters(String[] leftSideCartoonCharacters) {
	this.leftSideCartoonCharacters = leftSideCartoonCharacters;
    }

    public void setOssServerLeftList(List<OssServer> ossServerLeftList) {
	this.ossServerLeftList = ossServerLeftList;
    }

    public void setOssServerList(List<OssServer> ossServerList) {
	this.ossServerList = ossServerList;
    }

    public void setOssUserList(List<OssUser> ossUserList) {
	this.ossUserList = ossUserList;
    }

    public void setRightSideCartoonCharacters(
	    String[] rightSideCartoonCharacters) {
	this.rightSideCartoonCharacters = rightSideCartoonCharacters;
    }

    public void setServerInfo(String serverInfo) {
	this.serverInfo = serverInfo;
    }

    public void setSessionID(String sessionID) {
	this.sessionID = sessionID;
    }

    /**
     * 分配服务器给用户
     * 
     * @return
     */
    @Action(value = "ossUser_updateAddOssServerOperation", results = { @Result(name = SUCCESS, type = "redirectAction", params = {
	    "actionName", "ossUser_addOssServerOperationIndex", "namespace",
	    "/admin/base", "actionMsg", "${actionMsg}", "id", "${id}",
	    "username", "${username}" }) })
    public String updateAddOssServerManagerr() {

	String username = ServletActionContext.getRequest().getParameter(
		"username");

	String serverinfo = ServletActionContext.getRequest().getParameter(
		"serverinfo");

	Validate.isTrue(StringUtils.isNotBlank(username),
		"param username must not be null!");

	Validate.isTrue(StringUtils.isNotBlank(serverinfo),
		"param serverinfo must not be null!");

	Date dateTime = new java.util.Date();

	/** 删除用户关联的所有服务器 */
	ossUserServerService.deleteOssUserServerByUsername(username);
	/**
	 * 要更改成的服务器
	 */
	String serverIds[] = serverinfo.split(",");
	/**
	 * 全部删除时
	 */
	if (serverIds.length == 1 && serverIds[0].equals("-1")) {
	    throw new ActionValidateException(ActionValidateException.SUCCESS,
		    AUTHORIZE_SUCCESS);
	}
	/**
	 * 赋值权限，加入数据库
	 */
	if (serverIds.length > 0 && StringUtils.isNotBlank(serverIds[0])) {
	    for (String serverId : serverIds) {

		OssUserServer server = new OssUserServer();
		server.setServerId(Integer.valueOf(serverId));
		server.setCreateTime(dateTime);
		server.setUsername(username);
		server.setCreateUser(getBaseAdminContext().getOssUser()
			.getUsername());
		ossUserServerService.createOssUserServer(server);
	    }
	}

	setActionMsg(AUTHORIZE_SUCCESS);

	return SUCCESS;
    }

    /** 修改管理员 */
    @Action(value = "ossUser_updateOssUser", results = { @Result(name = "toBrowseOssUser", type = "redirectAction", params = {
	    "actionName", "ossUser_browseOssUser", "namespace", "/admin/base",
	    "actionMsg", "${actionMsg}" }) })
    public String updateOssUser() {
	OssUser ossUserTmp = ossUserService.getOssUserByID(model.getUsername());
	if (ossUserTmp != null) {
	    ossUserTmp.setRealnames(model.getRealnames());
	    ossUserTmp.setStatus(model.getStatus());
	    ossUserTmp.setIsOperator(model.getIsOperator());
	    ossUserTmp.setCarrierOperator(model.getCarrierOperator());
	    ossUserTmp.setStatus(model.getStatus());
	    int k = ossUserService.updateOssUser(ossUserTmp);
	    if (k > 0) {
		setActionMsg("修改成功！");
	    } else {
		setActionMsg("修改失败！");
	    }
	} else {
	    setActionMsg("修改失败！");
	}
	return "toBrowseOssUser";
    }

}
