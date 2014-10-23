package com.jcwx.game.admin.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcwx.game.admin.BasalAction;
import com.jcwx.game.common.SpringService;
import com.jcwx.game.domain.CenterServer;
import com.jcwx.game.domain.OssOperation;
import com.jcwx.game.domain.OssServer;
import com.jcwx.game.domain.OssUser;
import com.jcwx.game.domain.OssUserServer;
import com.jcwx.game.service.ICenterServerService;
import com.jcwx.game.service.impl.CenterServerService;
import com.jcwx.game.service.oss.IOssOperationService;
import com.jcwx.game.service.oss.IOssServerService;
import com.jcwx.game.service.oss.IOssUserServerService;
import com.jcwx.game.service.oss.IOssUserService;

@ParentPackage("base")
@Namespace("/admin/base")
@ResultPath("/")
public class OssServerAction extends BasalAction {
    private static Logger logger = Logger
	    .getLogger(OssServerAction.class);
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String id;

    /** 左边选择列表 */
    private String[] leftSideCartoonCharacters;

    private OssServer model = new OssServer();

    private OssUserServer model1 = new OssUserServer();

    private OssOperation ossOperation;

    private List<OssOperation> ossOperationList;
    @Autowired
    private IOssOperationService ossOperationService;
    private OssServer ossServer;

    /** 服务区列表 */
    private List<OssServer> ossServerList;

    @Autowired
    private IOssServerService ossServerService;

    /** 管理员列表 */
    private List<OssUserServer> ossUserServerList;

    /** 未授权管理员列表 */
    private List<OssUser> ossUserServerOffList;
    /** 已授权管理员列表 */
    private List<OssUser> ossUserServerOnList;

    @Autowired
    private IOssUserServerService ossUserServerService;

    /** 右边选择列表 */
    private String[] rightSideCartoonCharacters;

    /** 选中的菜单集合 */
    private String[] selectMenuArray;

    private List<OssServer> servers;
    @Autowired
    private ICenterServerService centerServerService;

    /** 新增游戏区 */
    @Action(value = "ossServer_addOssServer", results = {
	    @Result(name = "success", type = "redirectAction", params = {
		    "actionName", "ossServer_serverList", "namespace",
		    "/admin/base", "actionMsg", "${actionMsg}" }),
	    @Result(name = "error", location = "../../admin/server/addOssServer.jsp") })
    public String addOssServer() throws Exception {
	// 登录环境
	Integer idInteger = Integer
		.parseInt(ossServer.getServerId().toString());
	ossOperation = ossOperationService.getOssOperationByID(idInteger);
	ossServer.setServerCode(ossOperation.getCarrierOperator());
	ossServer.setProjectId(getBaseAdminContext().getProject()
		.getProjectId());
	ossServer.setCreateTime(new Date());
	ossServer.setCreateUser(getBaseAdminContext().getOssUser()
		.getUsername());
	ossServer.setUpdateTime(new Date());
	ossServer.setUpdateUser(getBaseAdminContext().getOssUser()
		.getUsername());
	ossServer.setServerId(idInteger);
	ossServer.setServerProvider(ossOperation.getOperationName());
	ossServerService.createOssServer(ossServer);
	setActionMsg("创建成功！");
	return SUCCESS;
    }

    @Action(value = "ossServer_addOssServerIndex", results = { @Result(name = "success", location = "../../admin/server/addOssServer.jsp") })
    public String addOssServerIndex() {
	ossOperationList = ossOperationService.getOssOperationList();
	return SUCCESS;
    }

    /* 设置服务区管理人员 */
    @Action(value = "ossServer_addOssServerManager", results = { @Result(name = "success", location = "../../admin/server/addOssServerManager.jsp") })
    public String addOssServerManager() throws Exception {
	id = ServletActionContext.getRequest().getParameter("id");

	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    addActionMessage(getActionMsg());
	}
	IOssUserService ossUserService = (IOssUserService) SpringService
		.getBean("ossUserService");
	IOssUserServerService ossUserServerService = (IOssUserServerService) SpringService
		.getBean("ossUserServerService");
	OssServer OssServerTemp = ossServerService.getOssServerByID(Integer
		.parseInt(id));
	BeanUtils.copyProperties(model, OssServerTemp);
	// 可选择的用户列表
	List<OssUser> ossUserAllListTemp = ossUserService.getOssUserList();// 所有管理员
	// 已选择的用户列表
	List<OssUserServer> ossUserServerList = ossUserServerService
		.getOssUserServerListGrouyById(model.getId());

	ossUserServerOnList = new ArrayList<OssUser>();
	ossUserServerOffList = new ArrayList<OssUser>();
	for (OssUser u : ossUserAllListTemp) {
	    boolean b = false;
	    for (OssUserServer r : ossUserServerList) {
		if (r.getUsername().equals(u.getUsername())) {
		    b = true;
		    break;
		}
	    }
	    if (b) {// 已授权的管理员
		ossUserServerOnList.add(u);
	    } else {
		ossUserServerOffList.add(u);
	    }
	}

	return SUCCESS;

    }

    /** 删除游戏区 */
    @Action(value = "ossServer_deleteOssServer", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "ossServer_serverList", "namespace", "/admin/base",
	    "actionMsg", "${actionMsg}" }) })
    public String deleteOssServer() throws Exception {
	// 登录环境
	String id = ServletActionContext.getRequest().getParameter("id");
	int k = ossServerService.deleteOssServerByID(Integer.parseInt(id));
	if (k > 0) {
	    setActionMsg("删除成功！");
	} else {
	    setActionMsg("删除失败！");
	}
	return SUCCESS;
    }

    /** 修改游戏区 */
    @Action(value = "ossServer_doUpdateOssServer", results = { @Result(name = "success", type = "redirectAction", params = {
	    "actionName", "ossServer_serverList", "namespace", "/admin/base",
	    "actionMsg", "${actionMsg}" }) })
    public String doUpdateOssServer() throws Exception {
	// 登录环境
	if (getActionMsg() != null && !"".equals(getActionMsg())) {
	    // actionMsg = new String(actionMsg.getBytes("ISO8859-1"), "utf-8");
	    addActionMessage(getActionMsg());
	}
	int idInteger = Integer.valueOf(ossServer.getServerId());
	ossOperation = ossOperationService.getOssOperationByID(idInteger);
	ossServer.setServerCode(ossOperation.getCarrierOperator());
	ossServer.setCreateTime(new Date());
	ossServer.setCreateUser(getBaseAdminContext().getOssUser()
		.getUsername());
	ossServer.setUpdateTime(new Date());
	ossServer.setUpdateUser(getBaseAdminContext().getOssUser()
		.getUsername());
	ossServer.setServerId(idInteger);
	ossServer.setProjectId(getBaseAdminContext().getProject()
		.getProjectId());
	ossServer.setServerProvider(ossOperation.getOperationName());
	ossServer.setUpdateTime(new Date());
	ossServer.setUpdateUser(getBaseAdminContext().getOssUser()
		.getUsername());
	ossServerService.updateOssServer(ossServer);
	this.setActionMsg("修改成功!");
	return SUCCESS;
    }

    // /** 修改游戏区页面 */
    @Action(value = "ossServer_editOssServer", results = { @Result(name = "success", location = "../../admin/server/editOssServer.jsp") })
    public String editOssServer() throws Exception {

	return SUCCESS;
    }

    public String getId() {
	return id;
    }

    public String[] getLeftSideCartoonCharacters() {
	return leftSideCartoonCharacters;
    }

    public OssServer getModel() {
	return model;
    }

    // 采用模型驱动
    public OssUserServer getModel1() {
	return model1;
    }

    public OssOperation getOssOperation() {
	return ossOperation;
    }

    public List<OssOperation> getOssOperationList() {
	return ossOperationList;
    }

    public OssServer getOssServer() {
	return ossServer;
    }

    public List<OssServer> getOssServerList() {
	return ossServerList;
    }

    public IOssServerService getOssServerService() {
	return ossServerService;
    }

    public List<OssUserServer> getOssUserServerList() {
	return ossUserServerList;
    }

    public List<OssUser> getOssUserServerOffList() {
	return ossUserServerOffList;
    }

    public List<OssUser> getOssUserServerOnList() {
	return ossUserServerOnList;
    }

    public String[] getRightSideCartoonCharacters() {
	return rightSideCartoonCharacters;
    }

    public String[] getSelectMenuArray() {
	return selectMenuArray;
    }

    public List<OssServer> getServers() {
	return servers;
    }

    @Action(value = "ossServer_serverList", results = { @Result(name = "success", location = "../../admin/server/listOssServer.jsp") })
    public String index() throws Exception {
	try {
	    if (getActionMsg() != null && !"".equals(getActionMsg())) {
		// actionMsg = new String(actionMsg.getBytes("ISO8859-1"),
		// "utf-8");
		addActionMessage(getActionMsg());
	    }
	    this.servers = ossServerService
		    .findAllOssServerByProjectId(getBaseAdminContext()
			    .getProject().getProjectId());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return SUCCESS;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setLeftSideCartoonCharacters(String[] leftSideCartoonCharacters) {
	this.leftSideCartoonCharacters = leftSideCartoonCharacters;
    }

    public void setModel(OssServer model) {
	this.model = model;
    }

    public void setOssOperation(OssOperation ossOperation) {
	this.ossOperation = ossOperation;
    }

    public void setOssOperationList(List<OssOperation> ossOperationList) {
	this.ossOperationList = ossOperationList;
    }

    public void setOssServer(OssServer ossServer) {
	this.ossServer = ossServer;
    }

    public void setOssServerList(List<OssServer> ossServerList) {
	this.ossServerList = ossServerList;
    }

    public void setOssServerService(IOssServerService ossServerService) {
	this.ossServerService = ossServerService;
    }

    public void setOssUserServerList(List<OssUserServer> ossUserServerList) {
	this.ossUserServerList = ossUserServerList;
    }

    public void setOssUserServerOffList(List<OssUser> ossUserServerOffList) {
	this.ossUserServerOffList = ossUserServerOffList;
    }

    public void setOssUserServerOnList(List<OssUser> ossUserServerOnList) {
	this.ossUserServerOnList = ossUserServerOnList;
    }

    public void setRightSideCartoonCharacters(
	    String[] rightSideCartoonCharacters) {
	this.rightSideCartoonCharacters = rightSideCartoonCharacters;
    }

    public void setSelectMenuArray(String[] selectMenuArray) {
	this.selectMenuArray = selectMenuArray;
    }

    public void setServers(List<OssServer> servers) {
	this.servers = servers;
    }

    // 授权用户角色
    @Action(value = "ossServer_updateAddOssServerManagerr", results = { @Result(name = "toAddOssServerManagerr", type = "redirectAction", params = {
	    "actionName", "ossServer_addOssServerManager", "namespace",
	    "/admin/base", "actionMsg", "${actionMsg}", "id", "${id}" }) })
    public String updateAddOssServerManagerr() {
	// 登录环境
	String id = ServletActionContext.getRequest().getParameter("id");
	Integer ID = Integer.parseInt(id);// 服务器ID
	ossServer = ossServerService.getOssServerByID(ID);
	IOssUserServerService ossUserServerService = (IOssUserServerService) SpringService
		.getBean("ossUserServerService");
	OssUserServer OssUserServerTemp = null;
	ossUserServerService.deleteOssUserServerByServerID(ID);
	for (String username : rightSideCartoonCharacters) {// 已分配人员
	    OssUserServerTemp = new OssUserServer();
	    OssUserServerTemp.setServerId(ID);// 服务器ID
	    OssUserServerTemp.setOperationId(ossServer.getServerId());// 运营商Id
	    OssUserServerTemp.setUsername(username);
	    OssUserServerTemp.setCreateTime(new Date());
	    OssUserServerTemp.setCreateUser(getBaseAdminContext().getOssUser()
		    .getUsername());
	    ossUserServerService.createOssUserServer(OssUserServerTemp);
	}

	setActionMsg("授权成功！");

	return "toAddOssServerManagerr";
    }

    /** 修改游戏区 */
    @Action(value = "ossServer_updateOssServer", results = { @Result(name = "success", location = "../../admin/server/editOssServer.jsp") })
    public String updateOssServer() throws Exception {
	ossOperationList = ossOperationService.getOssOperationList();
	HttpSession session = ServletActionContext.getRequest().getSession();
	// 登录环境
	String id = ServletActionContext.getRequest().getParameter("id");
	if (StringUtils.isNumeric(id)) {
	    ossServer = ossServerService.getOssServerByID(Integer.parseInt(id));
	    session.setAttribute("ossServer", ossServer);
	}
	return SUCCESS;
    }

    /** 修改游戏区 */
    @Action(value = "ossServer_reflash", results = { @Result(name = "success", type = "chain", location = "ossServer_serverList") })
    public String reflashOssServer() throws Exception {
	ossOperationList = ossOperationService.getOssOperationList();
	HttpSession session = ServletActionContext.getRequest().getSession();
	// 登录环境
	String id = ServletActionContext.getRequest().getParameter("id");
	if (StringUtils.isNumeric(id)) {
	    ossServer = ossServerService.getOssServerByID(Integer.parseInt(id));
	    session.setAttribute("ossServer", ossServer);
	}
	
	List<CenterServer> centerServers = centerServerService.queryCenterServers();
	int projectId = getBaseAdminContext().getProject().getProjectId();
	List<OssServer> list = ossServerService.findAllOssServerByProjectId(projectId);
	
	int num=0;
	StringBuffer sbBuffer = new StringBuffer();
	for(CenterServer centerServer:centerServers){
	    String socket=centerServer.getIp();
	    int port = centerServer.getSocketPort()+1000;
	    String url="http://"+socket+":"+port;
	    boolean flag = true;
	    centerServer.setProjectId(projectId);
	    centerServer.setUpdateUser(getBaseAdminContext().getOssUser().getUsername());
	    try {
        	    for(OssServer ossServer:list){
        		
        		if(ossServer.getUrl().equals(centerServer.getUrl())&&ossServer.getProjectId().intValue()==projectId){
        		    ossServerService.updateCenterServer(centerServer);
        		    centerServer.setServerId(ossServer.getId());
        		    flag=false;
        		    break;
        		} 
        	    }
        	    if(flag){
        		ossUserServerService.deleteOssUserServerByServerID(centerServer.getAreaId());
        		ossServerService.insertCenterServer(centerServer);
        		ossServerService.insertOssUserServer(centerServer);
        	    }else{
        		ossServerService.updateOssUserServer(centerServer);
        		
        	    }
	    } catch (Exception e) {
		sbBuffer.append(centerServer.getAreaId()+",");
		    System.out.println(num++);
		    System.err.println("存在服务器ID"+centerServer.getAreaId());
		    logger.error("存在服务器ID"+centerServer.getAreaId(), e);
	    }
//	    OssUserServerTemp = new OssUserServer();
//	    OssUserServerTemp.setServerId(ID);// 服务器ID
//	    OssUserServerTemp.setOperationId(ossServer.getServerId());// 运营商Id
//	    OssUserServerTemp.setUsername(username);
//	    OssUserServerTemp.setCreateTime(new Date());
//	    OssUserServerTemp.setCreateUser(getBaseAdminContext().getOssUser()
//		    .getUsername());
//	    ossUserServerService.createOssUserServer(OssUserServerTemp);
	    
	}
	return SUCCESS;
    }

}
