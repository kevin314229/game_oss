package com.jcwx.game.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.jcwx.game.admin.constant.AdminSystemConstant;
import com.jcwx.game.admin.constant.OssLogConstant;
import com.jcwx.game.admin.event.LoginVerifyEvent;
import com.jcwx.game.common.AutoCompliteModel;
import com.jcwx.game.common.BaseAdminContext;
import com.jcwx.game.common.CacheManager;
import com.jcwx.game.common.DateService;
import com.jcwx.game.common.MD5Service;
import com.jcwx.game.common.OssContext;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.common.WebUtilService;
import com.jcwx.game.domain.OssMenu;
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.OssUser;
import com.jcwx.game.domain.OssUserLastServer;
import com.jcwx.game.domain.Project;
import com.jcwx.game.http.domain.SendBaseProperty;
import com.jcwx.game.service.oss.IOssLogService;
import com.jcwx.game.service.oss.IOssMenuService;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.service.oss.IOssServerService;
import com.jcwx.game.service.oss.IOssUserServerService;
import com.jcwx.game.service.oss.IOssUserService;
import com.jcwx.game.service.oss.IProjectService;
import com.jcwx.game.util.ChineseCharToEn;
import com.jcwx.game.web.Global;

@ParentPackage("base")
@Namespace("/admin")
@ResultPath("/")
public class LoginAction extends BasalAction {

    private static final int ONE_WEEK = 7 * 24 * 60 * 60;
    private static final long serialVersionUID = 1L;

    /** 验证码 */
    private String checkcode;

    private String id;

    /** 当前服务区的名称 */
    private String name;
    
    @Autowired
    private IOssLogService ossLogService;
   
    private List<OssMenu> ossMenus;
    
    @Autowired
    private IOssMenuService ossMenuService;
   
    @Autowired
    private IOssOperationService ossOperationService;

    private List<OssServer> ossServers;

    @Autowired
    private IOssServerService ossServerService;

    @Autowired
    private IOssUserServerService ossUserServerService ;

    @Autowired
    private IOssUserService ossUserService;
    /** 用户密码 */
    private String password;

    private Project project;

    // 用户管理的项目列表
    private List<Project> projectList;

    @Autowired
    private IProjectService projectService;

    /** 平台标识 */
    private String serverCode;
    /** 当前服务器区号 **/
    private int serverId;

    /** 用户账号 */
    private String username;

    private Boolean verifyPassword;
    /** 保存帐号密码 */
    private Boolean savePassword;

    protected void removeCookie(String cookieKey) {
	Cookie cookie = new Cookie(cookieKey, null);
	cookie.setMaxAge(0);
	httpServletResponse.addCookie(cookie);
    }

    protected void addCookie(String key, String value) {
	Cookie cookie = new Cookie(key, value);
	cookie.setMaxAge(ONE_WEEK);
	httpServletResponse.addCookie(cookie);
    }

    /**
     * 用户登录 自动登录，密码保存一周
     */
    @Override
    @Action(value = "login", results = {
	    @Result(name = "carrierOperator", type = "redirectAction", location = "selectOssPorject.action"),
	    @Result(name = "success", location = "/admin/selectOssServer.jsp"),
	    @Result(name = "project", location = "/admin/projectList.jsp"),
	    @Result(name = "input", location = "/admin/login.jsp"),
	    @Result(name = VERIFY_ERROR, location = "/admin/login.jsp") })
    public String execute() throws Exception {

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	if (CacheManager.SYSTEM_SET.getVerify()) {
	    new LoginVerifyEvent(session, checkcode, "captchaerror", this)
		    .trigger();
	}

	// 验证账号密码
	OssUser ossUser = ossUserService.getOssUserByID(username);
	if (getVerifyPassword() == null) {
	    return INPUT;
	}
	String md5Password = getVerifyPassword() ? MD5Service
		.encryptString(password) : getPassword();
	if (ossUser == null || !ossUser.getPassword().equals(md5Password)) {
	    password = "";
	    checkcode = "";
	    this.setActionMsg(getText("userpasserror"));
	    return "input";
	}

	// 修改登录信息
	ossUser.setLastLoginIp(request.getRemoteAddr());
	ossUser.setLastLoginTime(DateService.getCurrentUtilDate());
	ossUser.setLoginNum(ossUser.getLoginNum() + 1);
	ossUser.setStatus(0);// 在线
	ossUserService.updateOssUserLastLoginInfo(ossUser);

	// 日志
	ossLogService
		.createOssLog(
			request,
			OssLogConstant.LOGIN,
			String.format("login log,User:%s",
				ossUser.getUsername()), null);
	// 用户信息写入
	BaseAdminContext baseAdminContext = new BaseAdminContext();
	baseAdminContext.setOssUser(ossUser);
	// 判断是否只有一个项目的权限 是直接跳到该项目的管理节目
	projectList = new ArrayList<Project>();
	projectList = projectService.getProjectListbyUserName(username);
	baseAdminContext.setProjectList(projectList);
	session.setAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY,
		baseAdminContext);
	if (projectList.size() == 0) { // 没有项目管理权限
	    this.setActionMsg("没有项目管理权限，请联系管理员  ");
	    return INPUT;
	} else if (projectList.size() > 1) { // 多个项目管理权限
	    if (getSavePassword() != null && getSavePassword().booleanValue()) {
		addCookie("username", getUsername());
		addCookie("password", md5Password);
		addCookie("verifyPassword", Boolean.valueOf(false).toString());
		addCookie("savePassword", "true");
	    } else {
		removeCookie("username");
		removeCookie("password");
		removeCookie("verifyPassword");
		removeCookie("savePassword");
	    }
	    return "project";
	}

	baseAdminContext.setProject(projectList.get(0));

	// 获取服务器列表
	ossServers = ossUserServerService.getOssServerListByUserAndProjectId(
		ossUser.getUsername(), projectList.get(0).getProjectId());
	if (ossServers == null || ossServers.size() <= 0) {
	    return "error";
	}
	baseAdminContext.setOssServers(ossServers); // 设置服务区到session

	/**
	 * ----------------------------------权限控制start--------------------------
	 * ------------------------------
	 */
	// 权限范围写入session
	List<OssMenu> tempList1 = ossMenuService
		.getUserOssMenuListsByUserNameAndProjectId(ossUser
			.getUsername(), projectList.get(0).getProjectId(), null);
	Set<String> set = new HashSet<String>(tempList1.size());
	int n = 0;
	String realUrl = null;
	for (OssMenu m : tempList1) {
	    realUrl = m.getPageUrl();
	    n = StringUtils.indexOf(realUrl, "_");
	    if (n != -1) {
		realUrl = StringUtils.substring(realUrl, 0, n + 1);
	    }
	    set.add(realUrl);
	}
	/**
	 * ----------------------------------权限控制end----------------------------
	 * ----------------------------
	 */
	// 登录环境放入session中
	baseAdminContext.setMenuSet(set);
	/* 初始化菜单导航 * */
	initLeftMenu();

	// 平台存入
	List<OssServer> ossServersPtList = ossUserServerService
		.getOssServerPtListByUserAndProjectId(ossUser.getUsername(),
			projectList.get(0).getProjectId());
	// 不在平台下
	if (ossServersPtList == null || ossServersPtList.size() <= 0) {
	    return "error";
	}
	baseAdminContext.setOssServersPt(ossServersPtList);
	List<OssOperation> ossOperationList = ossOperationService
		.getOssOperationList();
	baseAdminContext.setOssOperationList(ossOperationList);
	baseAdminContext.setIpAddr(WebUtilService
		.getIpAddr(ServletActionContext.getRequest()));

	// 获取数据库里面是否保存该游戏上次登录的服务器，没有则保存，有则取数据库的服务器--tanfl
	OssUserLastServer ossUserLastServer = ossServerService
		.getOssLastServerByID(projectList.get(0).getProjectId(),
			ossUser.getUsername());
	OssServer ossServer = null;
	if (ossUserLastServer != null && ossUserLastServer.getAreaId() > 0) {
	    ossServer = ossServerService.getOssServerByID(ossUserLastServer
		    .getAreaId());
	    if (ossServer == null) {
		ossServer = ossServers.get(0);
		ossServerService.createOssLastServer(projectList.get(0)
			.getProjectId(), ossServer.getId(), ossUser
			.getUsername());
	    }

	} else {
	    ossServer = ossServers.get(0);
	    ossServerService.createOssLastServer(projectList.get(0)
		    .getProjectId(), ossServer.getId(), ossUser.getUsername());
	}
	String name = ossServer.getName();
	baseAdminContext.setServerId(ossServer.getId());
	baseAdminContext.setCurrentOssServer(ossServer);

	CONNECTION.setContentTypesendMsg(ossServer.getUrl(),
		ossServer.getCommunicateKey());

	List<SendBaseProperty> sendBaseProperties = Global
		.getEquipList(ossServer.getUrl());
	if (sendBaseProperties == null || sendBaseProperties.isEmpty()) {
	    // 连接时初始化单个OSS数据
	    Global.initOssServer(ossServer);
	}
	baseAdminContext.setOperationId(ossServer.getServerId());// 运营商ID
	OssOperation ossOperation = ossOperationService
		.getOssOperationByID(ossServer.getServerId());
	baseAdminContext.setOperationName(ossOperation.getOperationName());
	baseAdminContext.setServerCode(ossServer.getServerCode());
	baseAdminContext.setName(name);
	ServletActionContext
		.getRequest()
		.getSession()
		.setAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY,
			baseAdminContext);
	/*
	 * if (ossUser.getIsOperator().equals("1")) { // 运营商登录 return
	 * "carrierOperator"; }
	 */
	return "carrierOperator";
    }

    public String getCheckcode() {
	return checkcode;
    }

    public String getId() {
	return id;
    }

    String getName() {
	return name;
    }

    public List<OssMenu> getOssMenus() {
	return ossMenus;
    }

    public IOssOperationService getOssOperationService() {
	return ossOperationService;
    }

    public List<OssServer> getOssServers() {
	return ossServers;
    }

    public String getPassword() {
	return password;
    }

    public Project getProject() {
	return project;
    }

    public List<Project> getProjectList() {
	return projectList;
    }

    public String getServerCode() {
	return serverCode;
    }

    int getServerId() {
	return serverId;
    }

    public String getUsername() {
	return username;
    }

    // 左侧菜单主页面
    @Action(value = "leftMenu", results = {
	    @Result(name = "success", location = "../admin/left1.jsp"),
	    @Result(name = "error", type = "redirectAction", location = "logout.action") })
    public String initLeftMenu() {
	if (getBaseAdminContext() == null) {
	    return "error";
	}
	/*
	 * List<OssMenu> tempList =
	 * ossMenuService.getUserOssLeftMenuListByUserName
	 * (baseAdminContext.getOssUser() .getUsername());
	 */
	List<OssMenu> tempList = ossMenuService
		.getUserOssMenuListsByUserNameAndProjectId(
			getBaseAdminContext().getOssUser().getUsername(),
			getBaseAdminContext().getProject().getProjectId(), 0);

	if (tempList != null && !tempList.isEmpty()) {
	    int n = tempList.size();
	    String lastParentId = null;
	    OssMenu ossMenu = null;
	    for (int i = 0; i < n; i++) {
		ossMenu = tempList.get(i);
		if (!ossMenu.getParentMenuID().equals(lastParentId)) {
		    lastParentId = ossMenu.getParentMenuID();
		    tempList.add(i, ossMenuService.getOssMenuByID(ossMenu
			    .getParentMenuID()));
		    n += 1;// 临时解决
		}
	    }
	}
	getBaseAdminContext().setOssMenus(tempList);

	List<AutoCompliteModel> menuAutoCompliteList = new ArrayList<AutoCompliteModel>();
	if (tempList != null) {
	    for (OssMenu ossMenu : tempList) {
		if (StringUtils.isBlank(ossMenu.getPageUrl())) {
		    continue;
		}
		menuAutoCompliteList.add(AutoCompliteModel
			.createAutoCompliteModel(
				ChineseCharToEn.getChinesePy(
					getText(ossMenu.getName()))
					.toUpperCase(getLocale()),
				getText(ossMenu.getName()),
				ossMenu.getPageUrl() + ".action"));
	    }
	    getBaseAdminContext().setMenuAutoComplite(
		    JSON.toJSONString(menuAutoCompliteList));
	}

	this.ossMenus = tempList;

	return "success";
    }

    // 用户登录
    @Action(value = "login_toLogin", results = { @Result(name = "input", location = "/admin/login.jsp") })
    public String login_toLogin() throws Exception {
	return "input";
    }

    @Action(value = "logout", results = { @Result(name = "success", location = "/admin/login.jsp") })
    public String logout() {
	ServletActionContext.getRequest().getSession().invalidate();
	Cookie cookies[] = ServletActionContext.getRequest().getCookies();
	for (Cookie cookie : cookies) {
	    cookie.setValue("");
	    ServletActionContext.getResponse().addCookie(cookie);
	}

	return SUCCESS;
    }

    // 选择项目
    @Action(value = "selectOssPorject", results = {
	    @Result(name = "success", location = "/admin/index.jsp"),
	    @Result(name = "project", location = "/admin/projectList.jsp") })
    public String selectOssPorject() {
	BaseAdminContext baseAdminContext = OssContext
		.getBaseAdminContext(false);
	if (baseAdminContext == null) {
	    return INPUT;
	}
	if (baseAdminContext.getCurrentOssServer() != null) {
	    return com.opensymphony.xwork2.Action.SUCCESS;
	}
	// 权限范围写入session
	this.project = projectService.getProjectByID(id);

	baseAdminContext.setProject(project);

	/** 获取服务器信息 **/
	ossServers = ossUserServerService.getOssServerListByUserAndProjectId(
		baseAdminContext.getOssUser().getUsername(), baseAdminContext
			.getProject().getProjectId());
	if (ossServers == null || ossServers.size() <= 0) {
	    projectList = projectService
		    .getProjectListbyUserName(baseAdminContext.getOssUser()
			    .getUsername());
	    // 退回到服务器列表
	    this.addActionError("该项目下 没有可用服务器  ");
	    return "project";
	}

	baseAdminContext.setOssServers(ossServers); // 设置服务区到session
	/** 写入功能菜单权限 **/
	/**
	 * ----------------------------------权限控制start--------------------------
	 * ------------------------------
	 */
	List<OssMenu> tempList1 = ossMenuService
		.getUserOssMenuListsByUserNameAndProjectId(baseAdminContext
			.getOssUser().getUsername(), baseAdminContext
			.getProject().getProjectId(), null);
	Set<String> set = new TreeSet<String>();
	int n = 0;
	String realUrl = null;
	for (OssMenu m : tempList1) {
	    realUrl = m.getPageUrl();
	    n = StringUtils.indexOf(realUrl, "_");
	    if (n != -1) {
		realUrl = StringUtils.substring(realUrl, 0, n + 1);
	    }
	    set.add(realUrl);
	}
	/**
	 * ----------------------------------权限控制end----------------------------
	 * ----------------------------
	 */
	// 登录环境放入session中
	baseAdminContext.setMenuSet(set);

	/* 初始化菜单导航 * */
	initLeftMenu();

	// 平台存入
	List<OssServer> ossServersPtList = ossUserServerService
		.getOssServerPtListByUserAndProjectId(baseAdminContext
			.getOssUser().getUsername(), baseAdminContext
			.getProject().getProjectId());

	if (ossServersPtList == null || ossServersPtList.size() <= 0) {
	    projectList = projectService
		    .getProjectListbyUserName(baseAdminContext.getOssUser()
			    .getUsername());
	    return "project";
	}
	// 用于下拉框切换平台
	baseAdminContext.setOssServersPt(ossServersPtList);

	baseAdminContext.setIpAddr(WebUtilService
		.getIpAddr(ServletActionContext.getRequest()));
	// 选择平台，获取上次登录服务器信息，如果有则取上次登录服务器信息，如果没有则将默认服务器创建到登录服务器中----tanfl
	OssServer ossServer = null;
	OssUserLastServer ossUserLastServer = ossServerService
		.getOssLastServerByID(baseAdminContext.getProject()
			.getProjectId(), baseAdminContext.getOssUser()
			.getUsername());
	if (ossUserLastServer != null && ossUserLastServer.getAreaId() > 0) {
	    boolean existServer = CollectionUtils.exists(ossServers,
		    new BeanPredicate("id", new EqualPredicate(
			    ossUserLastServer.getAreaId())));

	    ossServer = existServer ? ossServerService
		    .getOssServerByID(ossUserLastServer.getAreaId())
		    : ossServers.get(0);

	} else {
	    ossServer = baseAdminContext.getCurrentOssServer() != null ? baseAdminContext
		    .getCurrentOssServer() : ossServers.get(0);
	    if (ossServer != null)
		ossServerService.createOssLastServer(baseAdminContext
			.getProject().getProjectId(), ossServer.getId(),
			baseAdminContext.getOssUser().getUsername());
	}
	// OssServer ossServer = baseAdminContext.getCurrentOssServer()
	// !=null?baseAdminContext.getCurrentOssServer():ossServers.get(0);
	if (ossServer == null) {
	    projectList = projectService
		    .getProjectListbyUserName(baseAdminContext.getOssUser()
			    .getUsername());
	    return "project";
	}
	/** 存入平台 code 用于判断所在的平台 */
	baseAdminContext.setServerCode(ossServer.getServerCode());
	String name = ossServer.getName();
	/** 把运营商下的 第一个平台座位初始化平台 */
	baseAdminContext.setServerId(ossServer.getId());
	baseAdminContext.setCurrentOssServer(ossServer);

	// 判断 该服务器是否有 游戏基础缓存

	CONNECTION.setContentTypesendMsg(ossServer.getUrl(),
		ossServer.getCommunicateKey());

	List<SendBaseProperty> sendBaseProperties = Global
		.getEquipList(ossServer.getUrl());
	if (sendBaseProperties == null || sendBaseProperties.isEmpty()) {
	    // 连接时初始化单个OSS数据
	    Global.initOssServer(ossServer);
	}

	/* 存入所有平台信息 * */
	List<OssOperation> ossOperationList = ossOperationService
		.getOssOperationList();
	baseAdminContext.setOssOperationList(ossOperationList);

	baseAdminContext.setOperationId(ossServer.getServerId());// 运营商ID
	OssOperation ossOperation = ossOperationService
		.getOssOperationByID(ossServer.getServerId());
	baseAdminContext.setOperationName(ossOperation.getOperationName());
	baseAdminContext.setServerCode(ossServer.getServerCode());
	baseAdminContext.setName(name);
	// 存入session
	ServletActionContext
		.getRequest()
		.getSession()
		.setAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY,
			baseAdminContext);
	return "success";
    }

    // 选择服务器
    @Action(value = "selectOssServer", results = {
	    @Result(name = "success", location = "/admin/index.jsp"),
	    @Result(name = "selectServer", location = "/admin/selectServer.jsp"),
	    @Result(name = "error", type = "redirectAction", location = "logout.action") })
    public String selectOssServer() {
	BaseAdminContext baseAdminContext = OssContext
		.getBaseAdminContext(false);
	if (baseAdminContext == null) {
	    return "error";
	}
	// String id =
	// ServletActionContext.getRequest().getParameter("id");//服务器ID
	if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
	    return "error";
	}

	OssServer ossServer = ossUserServerService.getUserOssServer(
		baseAdminContext.getOssUser().getUsername(),
		Integer.parseInt(id));
	if (ossServer == null) {
	    return "error";
	}
	if (ossServer.getId().equals(id)) {
	    return "selectServer";
	}
	if (!CONNECTION.isConnection(ossServer)) {
	    setErrorInfo("服务器连接失败,请检查!!");
	    return "selectServer";
	}
	// 更新切换服务器---tanfl
	ossServerService.updateOssLastServer(baseAdminContext.getProject()
		.getProjectId(), ossServer.getId(), baseAdminContext
		.getOssUser().getUsername());
	String name = ossServer.getName();
	baseAdminContext.setCurrentOssServer(ossServer);
	List<SendBaseProperty> sendBaseProperties = Global
		.getEquipList(ossServer.getUrl());

	CONNECTION.setContentTypesendMsg(ossServer.getUrl(),
		ossServer.getCommunicateKey());

	if (sendBaseProperties == null || sendBaseProperties.isEmpty()) {
	    // 连接时初始化单个OSS数据
	    Global.initOssServer(ossServer);
	}

	baseAdminContext.setOperationId(ossServer.getServerId());// 运营商ID
	OssOperation ossOperation = ossOperationService
		.getOssOperationByID(ossServer.getServerId());
	baseAdminContext.setOperationName(ossOperation.getOperationName());
	baseAdminContext.setServerId(Integer.parseInt(id));// 服务器ID
	baseAdminContext.setServerCode(ossServer.getServerCode());

	baseAdminContext.setName(name);
	ServletActionContext
		.getRequest()
		.getSession()
		.setAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY,
			baseAdminContext);
	ServletActionContext.getRequest().setAttribute("autoClose", true);

	return "selectServer";
    }

    // 切换平台方法
    @Action(value = "selectOssServerPt", results = {
	    @Result(name = "success", location = "/admin/index.jsp"),
	    @Result(name = "error", type = "redirectAction", location = "logout.action"),
	    @Result(name = "selectServer", location = "/admin/selectOssServer.jsp") })
    public String selectOssServerPt() {
	BaseAdminContext baseAdminContext = OssContext
		.getBaseAdminContext(false);
	if (baseAdminContext == null) {
	    return "error";
	}
	// String id =
	// ServletActionContext.getRequest().getParameter("id");//服务器ID
	if (StringUtils.isEmpty(serverCode)) {
	    return "error";
	}
	// 查询平台下的服务器
	// ossServers =
	// ossUserServerService.getOssServerListByUserAndPt(baseAdminContext.getOssUser().getUsername(),
	// serverCode);
	ossServers = ossUserServerService
		.getOssServerListByUserAndProjectIdAndPt(baseAdminContext
			.getOssUser().getUsername(), serverCode,
			baseAdminContext.getProject().getProjectId());

	if (ossServers == null || ossServers.size() <= 0) {
	    // 跳到服务器列表
	    // ossServers =
	    // ossUserServerService.getOssServerListByUser(baseAdminContext.getOssUser().getUsername());
	    ossServers = ossUserServerService
		    .getOssServerListByUserAndProjectId(baseAdminContext
			    .getOssUser().getUsername(), baseAdminContext
			    .getProject().getProjectId());
	    return "selectServer";
	}

	OssUserLastServer ossUserLastServer = ossServerService
		.getOssLastServerByID(projectList.get(0).getProjectId(),
			baseAdminContext.getOssUser().getUsername());
	OssServer ossServer = null;
	if (ossUserLastServer != null && ossUserLastServer.getAreaId() > 0) {
	    ossServer = ossServerService.getOssServerByID(ossUserLastServer
		    .getAreaId());
	    if (ossServer == null) {
		ossServer = ossServers.get(0);
		ossServerService.createOssLastServer(projectList.get(0)
			.getProjectId(), ossServer.getId(), baseAdminContext
			.getOssUser().getUsername());
	    }

	} else {
	    ossServer = ossServers.get(0);
	    ossServerService.createOssLastServer(projectList.get(0)
		    .getProjectId(), ossServer.getId(), baseAdminContext
		    .getOssUser().getUsername());
	}
	// OssServer ossServer = ossServers.get(0);

	String name = ossServer.getName();
	baseAdminContext.setCurrentOssServer(ossServer);
	List<SendBaseProperty> sendBaseProperties = Global
		.getEquipList(ossServer.getUrl());

	/** 服务器打开时修改当前的ContentType */
	CONNECTION.setContentTypesendMsg(ossServer.getUrl(),
		ossServer.getCommunicateKey());

	if (sendBaseProperties == null || sendBaseProperties.isEmpty()) {
	    // 连接时初始化单个OSS数据
	    Global.initOssServer(ossServer);
	}

	baseAdminContext.setOperationId(ossServer.getServerId());// 运营商ID
	OssOperation ossOperation = ossOperationService
		.getOssOperationByID(ossServer.getServerId());
	baseAdminContext.setOperationName(ossOperation.getOperationName());
	baseAdminContext.setServerId(ossServer.getId());// 服务器ID
	baseAdminContext.setServerCode(serverCode);
	baseAdminContext.setName(name);
	baseAdminContext.setIpAddr(WebUtilService
		.getIpAddr(ServletActionContext.getRequest()));
	ServletActionContext
		.getRequest()
		.getSession()
		.setAttribute(AdminSystemConstant.ADMIN_SYSTEM_SESSION_KEY,
			baseAdminContext);
	return "success";
    }

    public void setCheckcode(String checkcode) {
	this.checkcode = checkcode;
    }

    public void setId(String id) {
	this.id = id;
    }

    void setName(String name) {
	this.name = name;
    }

    public void setOssMenus(List<OssMenu> ossMenus) {
	this.ossMenus = ossMenus;
    }

    public void setOssOperationService(IOssOperationService ossOperationService) {
	this.ossOperationService = ossOperationService;
    }

    public void setOssServers(List<OssServer> ossServers) {
	this.ossServers = ossServers;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setProject(Project project) {
	this.project = project;
    }

    public void setProjectList(List<Project> projectList) {
	this.projectList = projectList;
    }

    public void setServerCode(String serverCode) {
	this.serverCode = serverCode;
    }

    void setServerId(int serverId) {
	this.serverId = serverId;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public Boolean getVerifyPassword() {
	return verifyPassword;
    }

    public void setVerifyPassword(Boolean verifyPassword) {
	this.verifyPassword = verifyPassword;
    }

    public Boolean getSavePassword() {
	return savePassword;
    }

    public void setSavePassword(Boolean savePassword) {
	this.savePassword = savePassword;
    }

    public IOssLogService getOssLogService() {
        return ossLogService;
    }

    public void setOssLogService(IOssLogService ossLogService) {
        this.ossLogService = ossLogService;
    }

    public IOssMenuService getOssMenuService() {
        return ossMenuService;
    }

    public void setOssMenuService(IOssMenuService ossMenuService) {
        this.ossMenuService = ossMenuService;
    }

    public IOssServerService getOssServerService() {
        return ossServerService;
    }

    public void setOssServerService(IOssServerService ossServerService) {
        this.ossServerService = ossServerService;
    }

    public IOssUserServerService getOssUserServerService() {
        return ossUserServerService;
    }

    public void setOssUserServerService(IOssUserServerService ossUserServerService) {
        this.ossUserServerService = ossUserServerService;
    }

    public IOssUserService getOssUserService() {
        return ossUserService;
    }

    public void setOssUserService(IOssUserService ossUserService) {
        this.ossUserService = ossUserService;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }
    
    
    

}
